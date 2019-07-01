package kr.maxted.tamtam.admin.outsideWork.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.maxted.tamtam.admin.outsideWork.model.OutsideWork;
import kr.maxted.tamtam.admin.outsideWork.repository.OutsideWorkRepository;

@Service
@Transactional
public class OutsideWorkService {
	
	private static final Logger log = LoggerFactory.getLogger(OutsideWorkService.class);

	@Autowired
	private OutsideWorkRepository outsideWorkRepository;
	
	private EntityManager em;
	
	public OutsideWorkService() {
		
	}
	
	public OutsideWorkService(EntityManager em) {
		this.em = em;
	}
	
	public OutsideWork findFirsByReqIdx(Long reqIdx) {
		return outsideWorkRepository.findFirsByReqIdx(reqIdx);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public HashMap<String, Object> save(OutsideWork outsideWork) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		try {
			OutsideWork result = outsideWorkRepository.save(outsideWork);
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("result", result);
		}catch(Exception e) {
			log.error("OutsideWorkService.save 오류가 발생하였습니다.");
			log.error(e.getMessage());
			rtn.put("systemCode", HttpStatus.BAD_REQUEST);
		}
		return rtn;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public HashMap<String, Object> saveAndFlush(OutsideWork outsideWork) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		try {
			OutsideWork result = outsideWorkRepository.saveAndFlush(outsideWork);
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("result", result);
		}catch(Exception e) {
			log.error("OutsideWorkService.saveAndFlush 오류가 발생하였습니다.");
			rtn.put("systemCode", HttpStatus.BAD_REQUEST);
		}
		return rtn;
	}
	
	public OutsideWork findFirstByEmpIdxAndWkpIdAndReqDtOrderByReqIdxDesc(Long empIdx, Long wkpId, String reqDt) {
		return outsideWorkRepository.findFirstByEmpIdxAndWkpIdAndReqDtOrderByReqIdxDesc(empIdx, wkpId, reqDt);
	}
	
	public List<HashMap<String, Object>> getOutApprList(Long comIdx, String apprStat) {
		List<Object[]> objList = outsideWorkRepository.getOutApprList(comIdx, apprStat);
		List<HashMap<String, Object>> apprList = new ArrayList<HashMap<String,Object>>();
		for(Object[] obj : objList) {
			HashMap<String, Object> apprMap = new HashMap<String, Object>();
			apprMap.put("reqIdx", obj[0]);
			apprMap.put("comIdx", obj[1]);
			apprMap.put("empIdx", obj[2]);
			apprMap.put("wkpId", obj[3]);
			apprMap.put("reqDt", obj[4]);
			apprMap.put("gpsLat", obj[5]);
			apprMap.put("gpsLon", obj[6]);
			apprMap.put("apprStat", obj[7]);
			apprMap.put("reqDiv", obj[8]);
			apprMap.put("empNm", obj[9]);
			apprMap.put("reqTm", obj[10]);
			apprList.add(apprMap);
		}
		return apprList;
	}
	
	public HashMap<String, Object> getOutApprDeail(Long reqIdx) {
		List<Object[]> objList = outsideWorkRepository.getOutApprDeail(reqIdx);
		HashMap<String, Object> apprMap = new HashMap<String, Object>();
		
		Object[] obj = objList.get(0);
		
		apprMap.put("reqIdx", obj[0]);
		apprMap.put("comIdx", obj[1]);
		apprMap.put("empIdx", obj[2]);
		apprMap.put("wkpId", obj[3]);
		apprMap.put("reqDt", obj[4]);
		apprMap.put("gpsLat", obj[5]);
		apprMap.put("gpsLon", obj[6]);
		apprMap.put("apprStat", obj[7]);
		apprMap.put("reqDiv", obj[8]);
		apprMap.put("empNm", obj[9]);
		apprMap.put("reqTm", obj[10]);
		return apprMap;
	}
}
