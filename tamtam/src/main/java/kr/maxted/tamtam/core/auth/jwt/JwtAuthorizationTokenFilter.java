package kr.maxted.tamtam.core.auth.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import kr.maxted.tamtam.core.utils.StringUtils;

/**
 * 
 * @author devkimsj
 *
 */
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private UserDetailsService userDetailsService;
	private JwtEmployeeService jwtEmployeeService;
	private JwtCompanyService jwtCompanyService;
	private JwtTokenUtil jwtTokenUtil;
	private String tokenHeader;

	public JwtAuthorizationTokenFilter(UserDetailsService userDetailsService, JwtEmployeeService jwtEmployeeService,  JwtTokenUtil jwtTokenUtil, String tokenHeader, JwtCompanyService jwtCompanyService) {
		this.userDetailsService = userDetailsService;
		this.jwtEmployeeService = jwtEmployeeService;
		this.jwtTokenUtil = jwtTokenUtil;
		this.tokenHeader = tokenHeader;
		this.jwtCompanyService = jwtCompanyService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		logger.debug("processing authentication for '{}'", request.getRequestURL());

		final String requestHeader = request.getHeader(this.tokenHeader);

		Long empIdx = null;
		Long comIdx = null;
		String authToken = null;
		Map<String, Object> userInfo = new HashMap<String, Object>();
		Map<String, Object> comInfo = new HashMap<String, Object>();
		
		if(requestHeader != null && requestHeader.startsWith("Bearer ")){
			authToken = requestHeader.substring(7);
			try {
				
				final Claims claims = jwtTokenUtil.getAllClaimsFromToken(authToken);
				
				if(claims.get("userInfo") != null){
					userInfo = jwtTokenUtil.getUserInfoFromToken(authToken);
					empIdx = Long.parseLong(StringUtils.defaultString(userInfo.get("empIdx")));
				}
				if(claims.get("comInfo") != null) {
					comInfo = jwtTokenUtil.getComInfoFromToken(authToken);
					comIdx = Long.parseLong(StringUtils.defaultString(comInfo.get("comIdx")));
				}
			} catch (IllegalArgumentException e) {
				logger.error("an error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("the token is expired and not valid anymore", e);
			}
		} else {
			logger.warn("couldn't find bearer string, will ignore the header");
		}

		logger.debug("checking authentication for user '{}'", empIdx);
		logger.debug("security context was null, so authorizating user");

		// 데이터베이스에서 사용 상세 정보를 로드할 필요 없이 토큰에서 읽음
		if(!userInfo.isEmpty()) {
			JwtEmployee jwtEmployee = this.jwtEmployeeService.loadUserByEmpIdx(empIdx);
	
			// 간단한 유효성 확인을 위해 토큰 무결성을 확인하기만 하면 됨 다시 call할 필요 없음
			if (jwtTokenUtil.validateEmpToken(authToken, jwtEmployee)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtEmployee, null, jwtEmployee.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authorizated user '{}', setting security context", empIdx);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		if(!comInfo.isEmpty()) {
			JwtCompany jwtCompany = this.jwtCompanyService.loadUserByComIdx(comIdx);
			
			// 간단한 유효성 확인을 위해 토큰 무결성을 확인하기만 하면 됨 다시 call할 필요 없음
			if (jwtTokenUtil.validateComToken(authToken, jwtCompany)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(jwtCompany, null, jwtCompany.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				logger.info("authorizated user '{}', setting security context", empIdx);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}
}