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

import kr.maxted.tamtam.admin.empSche.model.EmpSche;
import kr.maxted.tamtam.admin.empSche.service.EmpScheService;
import kr.maxted.tamtam.admin.workplace.model.Workplace;
import kr.maxted.tamtam.admin.workplace.service.WorkplaceService;
import kr.maxted.tamtam.core.utils.StringUtils;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;
import kr.maxted.tamtam.core.utils.enumUtils.enumDay;

@RestController
@RequestMapping(value="/mngWkp")
public class MngWorkplaceController {

	@Resource
	private WorkplaceService workplaceService;
	
	@Resource
	private EmpScheService empScheService;
	
	@GetMapping("/getMyWkpList/{comIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyWkp(@PathVariable Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		List<Workplace> wkpList  = workplaceService.findAllByComIdxAndDelYnOrderByWkpNm(comIdx, EnumYn.N);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", wkpList);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}

	@PostMapping("/saveWkp")
	public ResponseEntity<HashMap<String, Object>> saveWkp(@RequestBody Workplace workplace) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		workplace.setDelYn(EnumYn.N);
		workplace.setRegId("admin");
		workplace.setRegDt("20190625000000");
		workplaceService.saveAndFlush(workplace);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", "S");
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/updateWkp")
	public ResponseEntity<HashMap<String, Object>> updateWkp(@RequestBody Workplace workplace) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		Workplace wkp =  workplaceService.findFirstByWkpId(workplace.getWkpId());
		wkp.setUpdId("admin");
		wkp.setUpdDt("20190625000000");
		workplaceService.saveAndFlush(wkp);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", "S");
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/deleteWkp")
	public ResponseEntity<HashMap<String, Object>> deleteWkp(@RequestBody Workplace workplace) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		workplace.setDelYn(EnumYn.Y);
		workplace.setUpdId("admin");
		workplace.setUpdDt("20190625000000");
		workplaceService.saveAndFlush(workplace);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", "S");
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/saveWkpEmp")
	public ResponseEntity<HashMap<String, Object>> saveWkpEmp(@RequestBody List<HashMap<String, Object>> paramList) throws Exception{
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		EmpSche empSche = new EmpSche();
		
		for(HashMap<String, Object> m : paramList) {
			empSche.setEmpIdx(Long.parseLong(StringUtils.defaultString(m.get("empIdx"))));
			empSche.setAtdDay(enumDay.valueOf(StringUtils.defaultString(m.get("atdDay"))));
			empSche.setAtdTm(StringUtils.defaultString(m.get("atdTm")));
			empSche.setWkpId(Long.parseLong(StringUtils.defaultString(m.get("wkpId"))));
			empSche.setRetTm(StringUtils.defaultString(m.get("retTm")));
			empSche.setDelYN(EnumYn.N);
			empSche.setRegId("admin");
			empSche.setRegDt("20190625000000");
			empScheService.save(empSche);
		}
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
}
