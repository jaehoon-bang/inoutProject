package kr.maxted.tamtam.core.auth.jwt;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;
import lombok.Data;

/**
 * 
 * @author devkimsj
 *
 */
@Data
public class JwtEmployee implements UserDetails {

	private final Long empIdx;
	private final String empId;
	private final String empHp;
	private final String empNm;
	private final String empEmail;
	private final String empPwd;
	private final EnumYn empStat;
	private final String devInfo;
	private final EnumYn delYn;
	private final String regId;
	private final String regDt;
	private final String updId;
	private final String updDt;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public JwtEmployee(Long empIdx, String empId, String empPwd, String empNm, String empHp,
			String empEmail, EnumYn empStat, String devInfo, EnumYn delYn, String regId, String regDt,
			String updId, String updDt, Collection<? extends GrantedAuthority> authorities){
		
		this.empIdx = empIdx;
		this.empId = empId;
		this.empHp = empHp;
		this.empNm = empNm;
		this.empEmail = empEmail;
		this.empPwd = empPwd;
		this.empStat = empStat;
		this.devInfo = devInfo;
		this.delYn = delYn;
		this.regId = regId;
		this.regDt = regDt;
		this.updId = updId;
		this.updDt = updDt;
		this.authorities = authorities;

    }
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
}