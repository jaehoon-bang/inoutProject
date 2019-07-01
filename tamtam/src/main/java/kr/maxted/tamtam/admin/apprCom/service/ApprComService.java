package kr.maxted.tamtam.admin.apprCom.service;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.maxted.tamtam.admin.apprCom.model.ApprCom;
import kr.maxted.tamtam.admin.apprCom.repository.ApprComRepository;

@Service
public class ApprComService {
	
	private static final Logger log = LoggerFactory.getLogger(ApprComService.class);

	private ApprComRepository apprComRepository;
	
	public ApprComService() {
		
	}
	
	@Autowired
	public ApprComService(ApprComRepository apprComRepository) {
		this.apprComRepository = apprComRepository;
	}
	
	public List<ApprCom> findAll(){
		return apprComRepository.findAll();
	}
	
	public ApprCom findFirstByComCdOrderByRegDtDesc(Long comCd){
		return apprComRepository.findFirstByComCdOrderByRegDtDesc(comCd);
	}
	
	public List<ApprCom> findAllByComCdAndApprStat(Long comCd, char apprStat){
		return apprComRepository.findByComCdAndApprStat(comCd, apprStat);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public HashMap<String, Object> save(ApprCom apprCom) {
		HashMap<String, Object > rtn = new HashMap<String, Object>();
		try {
			apprComRepository.save(apprCom);
			rtn.put("systemCode", HttpStatus.OK);
		} catch(Exception e) {
			log.error("회원 등록 중 오류가 발생하였습니다.");
			log.error(e.getMessage());
			rtn.put("systemCode", HttpStatus.BAD_REQUEST);
		}
		return rtn;
	}
}
