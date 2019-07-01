package kr.maxted.tamtam.core.auth.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.core.auth.model.Role;
import kr.maxted.tamtam.core.auth.role.RoleName;

/**
 * 
 * @author devkimsj
 *
 */
public final class JwtCompanyFactory {
	
	private JwtCompanyFactory() {
	}

	public static JwtCompany create(Company company) {
		
		List<Role> list = new ArrayList<Role>();
		
		Role roleParam = new Role();
		roleParam.setId((long)1);
		roleParam.setName(RoleName.ROLE_ADMIN);;
		
		list.add(roleParam);
		
		return new JwtCompany(
					company.getComIdx(),
					company.getComId(),
					company.getComCd(),
					company.getComNm(),
					company.getComHp(),
					company.getComEmail(),
					company.getComPwd(),
					company.getDevInfo(),
					company.getPushTkn(),
					company.getDelYn(),
					company.getRegId(),
					company.getRegDt(),
					company.getUpdId(),
					company.getUpdDt(),
					mapToGrantedAuthorities(list)
				);
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
	}
}