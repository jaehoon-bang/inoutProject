package kr.maxted.tamtam.core.auth.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;
import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.admin.employee.model.Employee;

/**
 * 
 * @author devkimsj
 *
 */
@Component
public class JwtTokenUtil implements Serializable {

	static final String CLAIM_KEY_USERNAME = "sub";
	static final String CLAIM_KEY_CREATED = "iat";
	private static final long serialVersionUID = -3301605591108950415L;
	private Clock clock = DefaultClock.INSTANCE;

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long expiration;

	@SuppressWarnings("unchecked")
	public Map<String, Object> getUserInfoFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return (Map<String, Object>) claims.get("userInfo");
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getComInfoFromToken(String token) {
		final Claims claims = getAllClaimsFromToken(token);
		return (Map<String, Object>) claims.get("comInfo");
	}
	
	public String getAudienceFromToken(String token) {
		return getClaimFromToken(token, Claims::getAudience);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getAllClaimsFromToken(String token) {
		return  Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(clock.now());
	}

	private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
		return (lastPasswordReset != null && created.before(lastPasswordReset));
	}

	private Boolean ignoreTokenExpiration(String token) {
		// here you specify tokens, for that the expiration is ignored
		return false;
	}

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	public HashMap<String, Object> generateEmpTokenRtnMap(Employee employee) {
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		HashMap<String, Object> claims = new HashMap<>();
		if(employee != null) {
			userInfo.put("empIdx", employee.getEmpIdx());
			userInfo.put("empId", employee.getEmpId());
			userInfo.put("empNm", employee.getEmpNm());
			userInfo.put("empEmail", employee.getEmpEmail());
			userInfo.put("empHp", employee.getEmpHp());
			claims.put("userInfo", userInfo);
			return doGenerateTokenRtnMap(claims, employee.getEmpNm());
		} else {
			claims.put("userInfo", "");
			claims.put("createdDate", "");
			claims.put("expirationDate", "");
			claims.put("token", "");
			return claims;
		}
		
	}
	
	public HashMap<String, Object> generateComTokenRtnMap(Company company) {
		HashMap<String, Object> comInfo = new HashMap<String, Object>();
		HashMap<String, Object> claims = new HashMap<>();
		if(company != null) {
			comInfo.put("comIdx", company.getComIdx());
			comInfo.put("comId", company.getComId());
			comInfo.put("comNm", company.getComNm());
			comInfo.put("comHp", company.getComHp());
			comInfo.put("comCd", company.getComCd());
			comInfo.put("comEmail", company.getComEmail());
			claims.put("comInfo", comInfo);
			return doGenerateTokenRtnMap(claims, company.getComNm());
		} else {
			claims.put("comInfo", "");
			claims.put("createdDate", "");
			claims.put("expirationDate", "");
			claims.put("token", "");
			return claims;
		}
		
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}
	
	private HashMap<String, Object> doGenerateTokenRtnMap(Map<String, Object> claims, String subject) {
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);
		final String token = Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
		rtn.put("createdDate", createdDate);
		rtn.put("expirationDate", expirationDate);
		rtn.put("token", token);
		
		return rtn;
	}

	public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
		final Date created = getIssuedAtDateFromToken(token);
		return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
				&& (!isTokenExpired(token) || ignoreTokenExpiration(token));
	}

	public String refreshToken(String token) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		final Claims claims = getAllClaimsFromToken(token);
		claims.setIssuedAt(createdDate);
		claims.setExpiration(expirationDate);

		return Jwts.builder()
				.setClaims(claims)
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	public Boolean validateEmpToken(String token, JwtEmployee jwtEmployee) {
		
		final String strEmpIdx = getUserInfoFromToken(token).get("empIdx").toString(); 
		final Date created = getIssuedAtDateFromToken(token);
		
		final Long empIdx = Long.parseLong(strEmpIdx);
		
		//final Date expiration = getExpirationDateFromToken(token);
		return (
				empIdx == jwtEmployee.getEmpIdx()
				&& !isTokenExpired(token)
				//&& !isCreatedBeforeLastPasswordReset(created,)
				);
	}
	
	public Boolean validateComToken(String token, JwtCompany jwtCompany) {
		
		final String strComIdx = getComInfoFromToken(token).get("comIdx").toString(); 
		final Date created = getIssuedAtDateFromToken(token);
		
		final Long comIdx = Long.parseLong(strComIdx);
		
		//final Date expiration = getExpirationDateFromToken(token);
		return (
				comIdx == jwtCompany.getComIdx()
				&& !isTokenExpired(token)
				//&& !isCreatedBeforeLastPasswordReset(created,)
				);
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + expiration * 1000);
	}
}