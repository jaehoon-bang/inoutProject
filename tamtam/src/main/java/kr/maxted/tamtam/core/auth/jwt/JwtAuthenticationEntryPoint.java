package kr.maxted.tamtam.core.auth.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * 
 * @author devkimsj
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

	private static final long serialVersionUID = -8970718410437077606L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		// 사용자가 자격 증명을 제공하지 않고 보안 REST 리소스에 액세스하려고 할 때 호출됨
		// 리다이렉트 할 로그인 페이지가 없어 401 응답을 보냄
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}