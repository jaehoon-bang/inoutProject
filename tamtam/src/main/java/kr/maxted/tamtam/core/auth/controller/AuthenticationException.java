package kr.maxted.tamtam.core.auth.controller;

/**
 * 
 * @author devkimsj
 *
 */
public class AuthenticationException extends RuntimeException {
	public AuthenticationException(String message, Throwable cause) {
		super(message, cause);
	}
}
