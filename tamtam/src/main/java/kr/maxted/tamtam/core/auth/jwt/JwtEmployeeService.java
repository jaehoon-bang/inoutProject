package kr.maxted.tamtam.core.auth.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.admin.employee.service.EmployeeService;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

/**
 * 
 * @author devkimsj
 *
 */
@Service
public class JwtEmployeeService implements UserDetailsService{

	private EmployeeService employeeService;

	public JwtEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public JwtEmployee loadUserByEmpIdx(Long empIdx) throws UsernameNotFoundException {
		
		Employee employee = employeeService.findFirstByEmpIdxAndDelYn(empIdx, EnumYn.N);

		if (employee == null) {
			throw new UsernameNotFoundException(String.format("No user found with empIdx '%s'.", empIdx));
		} else {
			return JwtEmployeeFactory.create(employee);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
