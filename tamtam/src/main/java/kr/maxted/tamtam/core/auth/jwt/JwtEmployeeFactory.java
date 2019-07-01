package kr.maxted.tamtam.core.auth.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.core.auth.model.Role;
import kr.maxted.tamtam.core.auth.role.RoleName;

/**
 * 
 * @author devkimsj
 *
 */
public final class JwtEmployeeFactory {
	
	private JwtEmployeeFactory() {
	}

	public static JwtEmployee create(Employee employee) {
		
		List<Role> list = new ArrayList<Role>();
		
		Role roleParam = new Role();
		roleParam.setId((long)1);
		roleParam.setName(RoleName.ROLE_USER);;
		
		list.add(roleParam);
		
		return new JwtEmployee(
					employee.getEmpIdx(),
					employee.getEmpId(),
					employee.getEmpPwd(),
					employee.getEmpNm(),
					employee.getEmpHp(),
					employee.getEmpEmail(),
					employee.getEmpStat(),
					employee.getDevInfo(), 
					employee.getDelYn(),
					employee.getRegId(),
					employee.getRegDt(),
					employee.getUpdId(),
					employee.getUpdDt(),
					mapToGrantedAuthorities(list)
				);
	}

	private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name()))
				.collect(Collectors.toList());
	}
}