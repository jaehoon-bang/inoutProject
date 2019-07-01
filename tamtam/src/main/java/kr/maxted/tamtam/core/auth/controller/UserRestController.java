package kr.maxted.tamtam.core.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import kr.maxted.tamtam.core.auth.jwt.JwtEmployee;
import kr.maxted.tamtam.core.auth.jwt.JwtEmployeeService;
import kr.maxted.tamtam.core.auth.jwt.JwtTokenUtil;

/**
 * 
 * @author devkimsj
 *
 */
@RestController
public class UserRestController {

	@Value("${jwt.header}")
	private String tokenHeader;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired                                                                                      
	@Qualifier("jwtEmployeeService")
	private JwtEmployeeService jwtEmployeeService;

	@RequestMapping(value = "user", method = RequestMethod.GET)
	public JwtEmployee getAuthenticatedUser(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader).substring(7);
		String strEmpIdx = jwtTokenUtil.getUserInfoFromToken(token).get("empIdx").toString();
		
		Long empIdx = Long.parseLong(strEmpIdx);
		
		return (JwtEmployee) jwtEmployeeService.loadUserByEmpIdx(empIdx);
	}

}
