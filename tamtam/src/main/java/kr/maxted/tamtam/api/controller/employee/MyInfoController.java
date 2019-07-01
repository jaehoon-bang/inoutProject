package kr.maxted.tamtam.api.controller.employee;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

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

import kr.maxted.tamtam.admin.empSche.service.EmpScheService;
import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.admin.employee.service.EmployeeService;
import kr.maxted.tamtam.core.utils.StringUtils;

/**
 * @author bjh89
 *
 */
@RestController
@RequestMapping(value = "/myInfo")
public class MyInfoController {

	@Resource
	private EmployeeService employeeService;
	
	@Resource
	private EmpScheService EmpScheService;
	
	
	@GetMapping("/getMyInfo/{empIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyInfo(@PathVariable Long empIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Employee employee = employeeService.findFirstByEmpIdx(empIdx);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", employee);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@GetMapping("/getMyEmpSche/{empIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyEmpSche(@PathVariable Long empIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		List<HashMap<String, Object>> empScheDtoList = EmpScheService.findEmployeeWorkPlaceListByEmpIdx(empIdx);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", empScheDtoList);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/changePwd")
	public ResponseEntity<HashMap<String, Object>> changePwd(@RequestBody HashMap<String, Object> param, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Long empIdx = Long.parseLong(StringUtils.defaultString(param.get("empIdx")));
		String empPwd = StringUtils.defaultString(param.get("empPwd"));
		
		Employee employee = employeeService.findFirstByEmpIdx(empIdx);
		
		String nowPwd = StringUtils.defaultString(employee.getEmpPwd());
		
		if(nowPwd.equals(empPwd)) {
			employee.setEmpPwd(empPwd);
			
			employeeService.save(employee);
			
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", "S");
		} else {
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", "F");
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/changeEmpId")
	public ResponseEntity<HashMap<String, Object>> changeEmpId(@RequestBody HashMap<String, Object> param, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Long empIdx = Long.parseLong(StringUtils.defaultString(param.get("empIdx")));
		String empId = StringUtils.defaultString(param.get("empId"));
		
		Employee employee = employeeService.findFirstByEmpIdx(empIdx);
		
		employee.setEmpId(empId);
		
		employeeService.save(employee);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", "S");
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/checkMyPwd/{empIdx}")
	public ResponseEntity<HashMap<String, Object>> checkMyPwd(@PathVariable Long empIdx, @RequestBody HashMap<String, Object> param, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Employee employee = employeeService.findFirstByEmpIdx(empIdx);
		
		String nowPwd = employee.getEmpPwd();
		String checkPwd = StringUtils.defaultString(param.get("checkPwd"));
		
		rtn.put("systemCode", HttpStatus.OK);
		if(nowPwd.equals(checkPwd)) {
			rtn.put("resultData", "S");
		} else {
			rtn.put("resultData", "F");
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
}
