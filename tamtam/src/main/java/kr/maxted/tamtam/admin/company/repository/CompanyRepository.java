package kr.maxted.tamtam.admin.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long>{

	Company findFirstByComIdx (Long comIdx);
	
	Company findFirstByComIdAndComPwdAndDelYn (String comId, String comPwd, EnumYn delYn);
	
	Company findFirstByComIdAndDelYn (String comId, EnumYn delYn);
	
	Company findFirstByComCdAndComNmAndDelYn(String ComCd, String ComNm, EnumYn delYn);

	Company findFirstByComIdxAndDelYn(Long comIdx, EnumYn delYn);
	
}
