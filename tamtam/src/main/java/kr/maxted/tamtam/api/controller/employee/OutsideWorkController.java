package kr.maxted.tamtam.api.controller.employee;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import kr.maxted.tamtam.admin.commute.model.Commute;
import kr.maxted.tamtam.admin.commute.service.CommuteService;
import kr.maxted.tamtam.admin.outsideWork.model.OutsideWork;
import kr.maxted.tamtam.admin.outsideWork.service.OutsideWorkService;
import kr.maxted.tamtam.core.utils.StringUtils;
import kr.maxted.tamtam.core.utils.enumUtils.ApprStat;
import kr.maxted.tamtam.core.utils.enumUtils.outsideWorkReqDiv;

@RestController
@RequestMapping(value = "/outsideWork")
@Transactional
public class OutsideWorkController {

	private static final Logger log = LoggerFactory.getLogger(OutsideWorkController.class);
	
	private OutsideWorkService outsideWorkService;
	
	private CommuteService commuteService;
	
	@Autowired
	public OutsideWorkController(OutsideWorkService outsideWorkService, CommuteService commuteService) {
		this.outsideWorkService = outsideWorkService;
		this.commuteService = commuteService;
	}
	
	
	@GetMapping("")
	public ResponseEntity<HashMap<String, Object>> isRunning() {
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/comm")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<HashMap<String, Object>> saveOutsideWorkComm(@RequestBody HashMap<String, Object> param, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();  
		
		OutsideWork outsideWork = new OutsideWork();
		Commute commute = new Commute();
		
		Long empIdx = Long.parseLong(StringUtils.defaultString(param.get("empIdx")));
		Long comIdx = Long.parseLong(StringUtils.defaultString(param.get("comIdx")));
		Long wkpId = Long.parseLong(StringUtils.defaultString(param.get("wkpId")));
		String reqDt = StringUtils.defaultString(param.get("reqDt"));
		String reqTm = StringUtils.defaultString(param.get("reqTm"));
		String gpsLat = StringUtils.defaultString(param.get("gpsLat"));
		String gpsLon = StringUtils.defaultString(param.get("gpsLon"));
		String regId = "admin";
		String regDt = "20190619161154";
		
		Commute rtnComm = commuteService.findByEmpIdxAndWkpIdAndAtdDd(empIdx, wkpId, reqDt);
		
		if(rtnComm == null) {
			
			outsideWork.setWkpId(wkpId);
			outsideWork.setEmpIdx(empIdx);
			outsideWork.setComIdx(comIdx);
			outsideWork.setReqDt(reqDt);
			outsideWork.setGpsLat(gpsLat);
			outsideWork.setGpsLon(gpsLon);
			outsideWork.setApprStat(ApprStat.W);
			outsideWork.setReqDiv(outsideWorkReqDiv.ATD);
			outsideWork.setRegId(regId);
			outsideWork.setRegDt(regDt);
			rtn = outsideWorkService.save(outsideWork);
			
			OutsideWork rtnOW = outsideWorkService.findFirstByEmpIdxAndWkpIdAndReqDtOrderByReqIdxDesc(empIdx, wkpId, reqDt);
			
			commute.setEmpIdx(empIdx);
			commute.setWkpId(wkpId);
			commute.setAtdDd(reqDt);
			commute.setAtdTm(reqTm);
			commute.setRegId(regId);
			commute.setOutAtdIdx(rtnOW.getReqIdx());
			commute.setRegDt(regDt);
			commuteService.save(commute);
			
			rtn.clear();
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", "S");
		} else {
			rtn.clear();
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", "F");
		}
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/ret")
	public ResponseEntity<HashMap<String, Object>> saveOutsideWorkRet(@RequestBody HashMap<String, Object> param, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) throws Exception {
		HashMap<String, Object> rtn = new HashMap<String, Object>();  
		
		OutsideWork outsideWork = new OutsideWork();
		Commute commute = new Commute();
		
		Long empIdx = Long.parseLong(StringUtils.defaultString(param.get("empIdx")));
		Long comIdx = Long.parseLong(StringUtils.defaultString(param.get("comIdx")));
		Long wkpId = Long.parseLong(StringUtils.defaultString(param.get("wkpId")));
		String reqDt = StringUtils.defaultString(param.get("reqDt"));
		String reqTm = StringUtils.defaultString(param.get("reqTm"));
		String gpsLat = StringUtils.defaultString(param.get("gpsLat"));
		String gpsLon = StringUtils.defaultString(param.get("gpsLon"));
		String regId = "admin";
		String regDt = "20190619161154";
		
		Commute rtnComm = commuteService.findByEmpIdxAndWkpIdAndAtdDd(empIdx, wkpId, reqDt);
		
		if(rtnComm != null && rtnComm.getLevDd() == null) {
			outsideWork.setWkpId(wkpId);
			outsideWork.setComIdx(comIdx);
			outsideWork.setEmpIdx(empIdx);
			outsideWork.setReqDt(reqDt);
			outsideWork.setGpsLat(gpsLat);
			outsideWork.setGpsLon(gpsLon);
			outsideWork.setApprStat(ApprStat.W);
			outsideWork.setReqDiv(outsideWorkReqDiv.RET);
			outsideWork.setRegId(regId);
			outsideWork.setRegDt(regDt);
			rtn = outsideWorkService.save(outsideWork);
			
			OutsideWork rtnOW = outsideWorkService.findFirstByEmpIdxAndWkpIdAndReqDtOrderByReqIdxDesc(empIdx, wkpId, reqDt);
			
			commute.setCommId(rtnComm.getCommId());
			commute.setEmpIdx(empIdx);
			commute.setWkpId(wkpId);
			commute.setAtdDd(rtnComm.getAtdDd());
			commute.setAtdTm(rtnComm.getAtdTm());
			commute.setOutAtdIdx(rtnComm.getOutAtdIdx());
			commute.setLevDd(reqDt);
			commute.setLevTm(reqTm);
			commute.setOutLevIdx(rtnOW.getReqIdx());
			commute.setRegId("admin");
			commute.setRegDt("20190619161154");
			commuteService.save(commute);
			rtn.clear();
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", "S");
		} else {
			rtn.clear();
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", "F");
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	
}
