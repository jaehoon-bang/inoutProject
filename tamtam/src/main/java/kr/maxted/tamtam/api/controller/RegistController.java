package kr.maxted.tamtam.api.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import kr.maxted.tamtam.admin.apprCom.model.ApprCom;
import kr.maxted.tamtam.admin.apprCom.service.ApprComService;
import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.admin.company.service.CompanyService;
import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.admin.employee.service.EmployeeService;
import kr.maxted.tamtam.core.utils.StringUtils;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

/**
 * 
 * @author devkimsj
 *
 */
@RestController
@RequestMapping(value = "/regist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RegistController {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ApprComService apprComService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("")
	public ResponseEntity<HashMap<String, Object>> isRunning() {
		List<ApprCom> apprCom = apprComService.findAll();
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("sysemCode", HttpStatus.OK);
		rtn.put("resultData", apprCom);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@GetMapping("/{comCd}")
	public ResponseEntity<HashMap<String, Object>> checkDupComCd(@PathVariable Long comCd) {

		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		ApprCom apprCom = apprComService.findFirstByComCdOrderByRegDtDesc(comCd);
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", apprCom);
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("") 
	public ResponseEntity<HashMap<String, Object>> saveApprCom(@RequestBody ApprCom apprCom, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		apprCom.setApprStat('N');
		apprCom.setApprId("admin");
		apprCom.setApprDt("20190101222222");
		apprCom.setRegId("admin");
		apprCom.setRegDt("20190101222222"); 
		apprCom.setUpdId("admin");
		apprCom.setUpdDt("20190101222222");
		  
		rtn = apprComService.save(apprCom);
				 
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/findId")
	public ResponseEntity<HashMap<String, Object>> findId(@RequestBody HashMap<String, Object> param){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		String flag = StringUtils.defaultString(param.get("flag"));
		String name = StringUtils.defaultString(param.get("name"));
		String number = StringUtils.defaultString(param.get("number"));
		
		if(flag.equals("company")) {
			String comNm = name;
			String comCd = number;
			
			Company company = companyService.findFirstByComCdAndComNmAndComHp(comCd, comNm, EnumYn.N);
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", company);
		} else {
			String empNm = name;
			String empHp = number;
			
			Employee employee = employeeService.findFirstByEmpNmAndEmpHpAndDelYn(empNm, empHp, EnumYn.N);
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", employee);
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/findPwd")
	public ResponseEntity<HashMap<String, Object>> findPwd(@RequestBody HashMap<String, Object> param){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		String flag = StringUtils.defaultString(param.get("flag"));
		String name = StringUtils.defaultString(param.get("name"));
		String number = StringUtils.defaultString(param.get("number"));
		
		if(flag.equals("company")) {
			String comNm = name;
			String comCd = number;
			
			Company company = companyService.findFirstByComCdAndComNmAndComHp(comCd, comNm, EnumYn.N);
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", company);
		} else {
			String empNm = name;
			String empHp = number;
			
			Employee employee = employeeService.findFirstByEmpNmAndEmpHpAndDelYn(empNm, empHp, EnumYn.N);
			rtn.put("systemCode", HttpStatus.OK);
			rtn.put("resultData", employee);
		}
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
}