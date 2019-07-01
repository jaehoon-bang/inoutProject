package kr.maxted.tamtam.api.controller.employee;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import kr.maxted.tamtam.admin.commute.model.Commute;
import kr.maxted.tamtam.admin.commute.service.CommuteService;
import kr.maxted.tamtam.admin.empSche.service.EmpScheService;
import kr.maxted.tamtam.core.utils.StringUtils;

/**
 * @author bjh89
 *
 */
@RestController
@RequestMapping(value = "/commute")
public class CommuteController {
	
	private static final Logger log = LoggerFactory.getLogger(CommuteController.class);
	
	@Autowired
	private EmpScheService empScheService;
	
	@Autowired
	private CommuteService commuteService;
	
	
	@PostMapping("/getCommute")
	public ResponseEntity<HashMap<String, Object>> getWorkPlace(@RequestBody HashMap<String, Object> param){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Long longEmpIdx = Long.parseLong(param.get("empIdx").toString());
		Long longWkpId = Long.parseLong(param.get("wkpId").toString());
		String startDd = param.get("startDd").toString();
		String endDd = param.get("endDd").toString();
		
		List<Commute> commuteList = commuteService.findAllByEmpIdxAndWkpIdAndAtdDdBetween(longEmpIdx, longWkpId, startDd, endDd);
		
		rtn.put("systemCod", HttpStatus.OK);
		rtn.put("resultData", commuteList);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@GetMapping("/getWorkplace/{empIdx}")
	public ResponseEntity<HashMap<String, Object>> getWorkPlace(@PathVariable Long empIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Long longEmpIdx = Long.parseLong(StringUtils.defaultString(empIdx));
		
		List<HashMap<String, Object>> list = empScheService.findEmployeeWorkPlaceListByEmpIdx(longEmpIdx);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", list);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/{flag}")
	public ResponseEntity<HashMap<String, Object>> saveCommute(@RequestBody Commute commute, @PathVariable String flag, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Long empIdx = commute.getEmpIdx();
		Long wkpId = commute.getWkpId();
		String atdDd = commute.getAtdDd();
		String atdTm = commute.getAtdTm();
		String regId = commute.getRegId();
		String levDd = commute.getLevDd();
		String levTm = commute.getLevTm();
		
		if("comm".equals(StringUtils.defaultString(flag))) {
			
			Commute rtnComm = commuteService.findByEmpIdxAndWkpIdAndAtdDd(empIdx, wkpId, atdDd);
			
			if(rtnComm == null) {
				commute.setEmpIdx(empIdx);
				commute.setWkpId(wkpId);
				commute.setAtdDd(atdDd);
				commute.setAtdTm(atdTm);
				commute.setRegId(regId);
				commute.setRegDt("20190619161154");
				commuteService.save(commute);
				rtn.put("systemCode", HttpStatus.OK);
				rtn.put("resultData", "S");
			} else {
				rtn.put("systemCode", HttpStatus.OK);
				rtn.put("resultData", "F");
			}
			
		} else {
			
			Commute rtnComm = commuteService.findByEmpIdxAndWkpIdAndAtdDd(empIdx, wkpId, atdDd);
			
			if(rtnComm != null && rtnComm.getLevDd() == null) {
				commute.setCommId(rtnComm.getCommId());
				commute.setEmpIdx(empIdx);
				commute.setWkpId(wkpId);
				commute.setAtdDd(rtnComm.getAtdDd());
				commute.setAtdTm(rtnComm.getAtdTm());
				commute.setLevDd(levDd);
				commute.setLevTm(levTm);
				commute.setRegId("admin");
				commute.setRegDt("20190619161154");
				commuteService.save(commute);
				rtn.put("systemCode", HttpStatus.OK);
				rtn.put("resultData", "S");
			} else {
				rtn.put("systemCode", HttpStatus.OK);
				rtn.put("resultData", "F");
			}
		}

		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
}
