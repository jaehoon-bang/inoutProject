package kr.maxted.tamtam.admin.company.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.admin.company.repository.CompanyRepository;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

@Service
public class CompanyService {

	private static final Logger log = LoggerFactory.getLogger(CompanyService.class);
	
	@Resource
	private CompanyRepository companyRepository;
	
	public CompanyService() {
	}
	
	public Company save(Company company) {
		return companyRepository.save(company);
	}
	
	public Company findFirstByComIdx(Long comIdx) {
		return companyRepository.findFirstByComIdx(comIdx);
	}
	
	public Company findFirstByComCdAndComNmAndComHp(String ComCd, String ComNm, EnumYn delYn) {
		return companyRepository.findFirstByComCdAndComNmAndDelYn(ComCd, ComNm, delYn);
	}
	
	public Company findFirstByComIdAndDelYn (String comId, EnumYn delYn) {
		return companyRepository.findFirstByComIdAndDelYn (comId, delYn);
	}
	
	public Company findFirstByComIdxAndDelYn(Long comIdx, EnumYn delYn) {
		return companyRepository.findFirstByComIdxAndDelYn(comIdx, delYn);
	}
}
