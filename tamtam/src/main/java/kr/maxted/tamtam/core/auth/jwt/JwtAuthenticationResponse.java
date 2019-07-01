package kr.maxted.tamtam.core.auth.jwt;

import java.io.Serializable;

/**
 * 
 * @author devkimsj
 *
 */
public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private final String token;

	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
