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
public class JwtCompany implements UserDetails {

	private final Long comIdx;
	private final String comdId;
	private final String comCd;
	private final String comNm;
	private final String comHp;
	private final String mngNm;
	private final String comPwd;
	private final String devInfo;
	private final String pushTkn;
	private final EnumYn delYn;
	private final String regId;
	private final String regDt;
	private final String updId;
	private final String updDt;
	private final Collection<? extends GrantedAuthority> authorities;
	
	public JwtCompany(Long comIdx, String comdId, String comCd, String comNm, String comHp,
			String mngNm, String comPwd, String devInfo, String pushTkn, EnumYn delYn,
			String regId, String regDt, String updId, String updDt, Collection<? extends GrantedAuthority> authorities){
		
		this.comIdx = comIdx;
		this.comdId = comdId;
		this.comCd = comCd;
		this.comNm = comNm;
		this.comHp = comHp;
		this.mngNm = mngNm;
		this.comPwd = comPwd;
		this.devInfo = devInfo;
		this.pushTkn = pushTkn;
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