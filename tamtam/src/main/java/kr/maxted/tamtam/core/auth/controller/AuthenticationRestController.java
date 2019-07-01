package kr.maxted.tamtam.core.auth.controller;

import java.util.HashMap;
import java.util.Objects;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.admin.company.service.CompanyService;
import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.admin.employee.service.EmployeeService;
import kr.maxted.tamtam.core.auth.jwt.JwtAuthenticationRequest;
import kr.maxted.tamtam.core.auth.jwt.JwtAuthenticationResponse;
import kr.maxted.tamtam.core.auth.jwt.JwtCompanyService;
import kr.maxted.tamtam.core.auth.jwt.JwtEmployeeService;
import kr.maxted.tamtam.core.auth.jwt.JwtTokenUtil;
import kr.maxted.tamtam.core.utils.StringUtils;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

/**
 * @author bjh89
 *
 */
@RestController
public class AuthenticationRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	@Qualifier("jwtEmployeeService")
	private JwtEmployeeService jwtEmployeeService;
	
	@Autowired
	@Qualifier("jwtCompanyService")
	private JwtCompanyService jwtCompanyService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CompanyService companyService;
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@Resource
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {

		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		//authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		
		// 토큰을 생성할 수 있도록 post-security 후 암호 다시 로드
		String id = authenticationRequest.getUsername();
		String pwd = authenticationRequest.getPassword();
		String flag = authenticationRequest.getFlag();
		
		if("emp".equals(flag)) {
			final Employee employee = employeeService.findFirstByEmpId(id);
			if(bCryptPasswordEncoder.matches(pwd, employee.getEmpPwd())) {
				rtn = jwtTokenUtil.generateEmpTokenRtnMap(employee);
			} else {
				rtn.put("userInfo", "");
				rtn.put("createdDate", "");
				rtn.put("expirationDate", "");
				rtn.put("token", "");
			}
		} else {
			final Company company = companyService.findFirstByComIdAndDelYn(id, EnumYn.N);
			
			if(bCryptPasswordEncoder.matches(pwd, company.getComPwd())) {
				rtn = jwtTokenUtil.generateComTokenRtnMap(company);
			} else {
				rtn.put("comInfo", "");
				rtn.put("createdDate", "");
				rtn.put("expirationDate", "");
				rtn.put("token", "");
			}
		}
		final String token = StringUtils.defaultString(rtn.get("token"));
		// token 리턴
		return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	}

	
	/*	@RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET) 
			public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) { 
			String authToken = request.getHeader(tokenHeader); 
			final String token =authToken.substring(7); String strEmpIdx = jwtTokenUtil.getEmpIdxFromToken(token);
			Long empIdx =Long.parseLong(strEmpIdx); JwtEmployee jwtEmployee = jwtEmployeeService.loadUserByEmpIdx(empIdx);
			 
			 
			if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())){ 
				String refreshedToken = jwtTokenUtil.refreshToken(token); 
				return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken)); 
			}else{
				  return ResponseEntity.badRequest().body(null); 
			}
		}*/
	 

	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<String> handleAuthenticationException(AuthenticationException e) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
	}

	/**
	 * Authenticates the user. If something is wrong, an {@link AuthenticationException} will be thrown
	 * 사용자를 인증한다. 잘못된 경우 {@link AuthenticationException}이 던져짐
	 */
	private void authenticate(String username, String password) {
		
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new AuthenticationException("User is disabled!", e);
		} catch (BadCredentialsException e) {
			throw new AuthenticationException("Bad credentials!", e);
		}
	}
}