package kr.maxted.tamtam.api.controller.company;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.maxted.tamtam.admin.outsideWork.model.OutsideWork;
import kr.maxted.tamtam.admin.outsideWork.service.OutsideWorkService;
import kr.maxted.tamtam.core.utils.StringUtils;
import kr.maxted.tamtam.core.utils.enumUtils.ApprStat;

@RestController
@RequestMapping(value = "/mngOut")
public class MngOutsideWorkController {

	@Resource
	private OutsideWorkService outsideWorkService;
	
	@GetMapping("/getOutAppr/{comIdx}/{apprStat}")
	public ResponseEntity<HashMap<String, Object>> getMyEmployee(@PathVariable Long comIdx, @PathVariable ApprStat apprStat){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		String stat = StringUtils.defaultString(apprStat);
		
		List<HashMap<String, Object>> outApprList = outsideWorkService.getOutApprList(comIdx, stat);
		rtn.put("systemCode", HttpStatus.OK);
		if(outApprList.size() > 0) {
			rtn.put("resultData", outApprList);
		} else {
			rtn.put("resultData", "");
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@GetMapping("/getOutApprDetail/{reqIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyEmployee(@PathVariable Long reqIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		HashMap<String, Object> apprMap = new HashMap<String, Object>();
		
		apprMap = outsideWorkService.getOutApprDeail(reqIdx);
		
		rtn.put("systemCode", HttpStatus.OK);
		if(!apprMap.isEmpty()) {
			rtn.put("resultData", apprMap);
		} else {
			rtn.put("resultData", "");
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/setOutAppr")
	public ResponseEntity<HashMap<String, Object>> setOutAppr(@RequestBody HashMap<String, Object> param) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Long reqIdx = Long.parseLong(StringUtils.defaultString(param.get("reqIdx")));
		String apprStat = StringUtils.defaultString(param.get("apprStat"));
		
		OutsideWork outsideWork = outsideWorkService.findFirsByReqIdx(reqIdx);
		
		if(outsideWork != null) {
			
			ApprStat chgStat = ApprStat.valueOf(apprStat);
			
			outsideWork.setApprStat(chgStat);
			
			rtn = outsideWorkService.save(outsideWork);
		}else {
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", "");
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
}
