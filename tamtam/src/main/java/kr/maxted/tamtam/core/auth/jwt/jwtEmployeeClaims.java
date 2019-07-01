package kr.maxted.tamtam.core.auth.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import io.jsonwebtoken.Claims;

public interface jwtEmployeeClaims extends Claims{

	public static final String ISSUER = "iss";
	

	Object clone() throws CloneNotSupportedException;

	@Override
	String toString();

	void finalize() throws Throwable;

	@Override
	default String getIssuer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Claims setIssuer(String iss) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	default boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	default boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	default boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	default Object get(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Object put(String key, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Object remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default void putAll(Map<? extends String, ? extends Object> m) {
		// TODO Auto-generated method stub
		
	}

	@Override
	default void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	default Set<String> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Collection<Object> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Set<Entry<String, Object>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default String getSubject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Claims setSubject(String sub) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default String getAudience() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Claims setAudience(String aud) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Date getExpiration() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Claims setExpiration(Date exp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Date getNotBefore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Claims setNotBefore(Date nbf) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Date getIssuedAt() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Claims setIssuedAt(Date iat) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default Claims setId(String jti) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	default <T> T get(String claimName, Class<T> requiredType) {
		// TODO Auto-generated method stub
		return null;
	}
    
    
    
}
