package kr.maxted.tamtam.core.auth.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.admin.company.service.CompanyService;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

/**
 * 
 * @author devkimsj
 *
 */
@Service
public class JwtCompanyService implements UserDetailsService{

	private CompanyService companyService;

	public JwtCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public JwtCompany loadUserByComIdx(Long comIdx) throws UsernameNotFoundException {
		
		Company company = companyService.findFirstByComIdxAndDelYn(comIdx, EnumYn.N);

		if (company == null) {
			throw new UsernameNotFoundException(String.format("No user found with comIdx '%s'.", comIdx));
		} else {
			return JwtCompanyFactory.create(company);
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
