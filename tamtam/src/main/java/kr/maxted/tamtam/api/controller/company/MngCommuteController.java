package kr.maxted.tamtam.api.controller.company;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.maxted.tamtam.admin.commute.service.CommuteService;
import kr.maxted.tamtam.admin.employee.service.EmployeeService;
import kr.maxted.tamtam.admin.workplace.model.Workplace;
import kr.maxted.tamtam.admin.workplace.service.WorkplaceService;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

@RestController
@RequestMapping(value = "/mngComm")
public class MngCommuteController {

	@Resource
	private CommuteService commuteService;
	
	@Resource
	private WorkplaceService workplaceService;
	
	@Resource
	private EmployeeService employeeService;
	
	@GetMapping("/getMyWkp/{comIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyWkp(@PathVariable Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		List<Workplace> workplaceList = workplaceService.findAllByComIdxAndDelYnOrderByWkpNm(comIdx, EnumYn.N);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", workplaceList);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@GetMapping("/getMyEmp/{comIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyEmp(@PathVariable Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		rtn = employeeService.getEmpList(comIdx);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@GetMapping("/getCommListByDate/{wkpId}/{date}")
	public ResponseEntity<HashMap<String, Object>> getCommListByDate(@PathVariable Long wkpId, @PathVariable String date){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> commuteList = commuteService.findAllByWkpIdAndAtdDd(wkpId, date);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", commuteList);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@GetMapping({"/getCommListByEmp/{wkpId}/{empIdx}/{atdDd}"})
	public ResponseEntity<HashMap<String, Object>> getCommListByDate(@PathVariable Long wkpId, @PathVariable Long empIdx,  @PathVariable String atdDd){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> commuteList = commuteService.queryWkpIdAndEmpIdx(wkpId, empIdx, atdDd);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", commuteList);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
}
