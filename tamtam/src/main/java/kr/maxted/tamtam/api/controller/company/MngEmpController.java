package kr.maxted.tamtam.api.controller.company;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.maxted.tamtam.admin.comEmpRel.model.ComEmpRel;
import kr.maxted.tamtam.admin.comEmpRel.service.ComEmpRelService;
import kr.maxted.tamtam.admin.commute.service.CommuteService;
import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.admin.employee.service.EmployeeService;
import kr.maxted.tamtam.admin.workplace.service.WorkplaceService;
import kr.maxted.tamtam.core.utils.StringUtils;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;


@RestController
@RequestMapping(value = "/mngEmp")
@Transactional
public class MngEmpController {
	//not used
	//private static final Logger log = LoggerFactory.getLogger(MngEmpController.class);
	
	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private CommuteService commuteService;
	
	@Resource
	private WorkplaceService workplaceService;
	
	@Resource
	private ComEmpRelService comEmpRelService;
	
	/**
	 * 직원조회
	 */
	@GetMapping("/getMyEmp/{comIdx}")
	public ResponseEntity<HashMap<String, Object>> getEmpList(@PathVariable Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		rtn = employeeService.getEmpList(comIdx);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	
	/**
	 * 퇴사직원 조회
	 */
	@GetMapping("/getMyLeaveEmpList/{comIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyLeaveEmpList(@PathVariable Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		rtn = employeeService.getLevEmpList(comIdx);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	/**
	 * 직원추가
	 */
	@PostMapping("/seveEmp")
	public ResponseEntity<HashMap<String, Object>> saveEmp(@RequestBody HashMap<String, Object> param) throws Exception{
		HashMap<String, Object > rtn = new HashMap<String, Object>();
		
		rtn = employeeService.addEmp(param);

		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	/**
	 * 직원수정
	 */
	@PostMapping("/updateEmp")
	public ResponseEntity<HashMap<String, Object>> updateEmp(@RequestBody Employee employee) throws Exception{
		HashMap<String, Object > rtn = new HashMap<String, Object>();
		
		rtn = employeeService.updateEmp(employee);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	/**
	 * 직원퇴사처리
	 */
	@PostMapping("/setEmpLeave")
	public ResponseEntity<HashMap<String, Object>> setEmpLeave(@RequestBody HashMap<String, Object> param){
		HashMap<String, Object > rtn = new HashMap<String, Object>();

		Long comIdx = Long.parseLong(StringUtils.defaultString(param.get("comIdx")));
		Long empIdx = Long.parseLong(StringUtils.defaultString(param.get("empIdx")));
		
		ComEmpRel cer = comEmpRelService.findFirstByComIdxAndEmpIdx(comIdx, empIdx);
		
		cer.setDelYn(EnumYn.Y);
		
		comEmpRelService.save(cer);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", "S");
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
}
