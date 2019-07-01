package kr.maxted.tamtam.api.controller.company;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.maxted.tamtam.admin.company.model.Company;
import kr.maxted.tamtam.admin.company.service.CompanyService;

@RestController
@RequestMapping(value = "/mngComInfo")
public class MngComInfoController {

	@Resource
	private CompanyService companyService;
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/getComInfo/{comIdx}")
	public ResponseEntity<HashMap<String, Object>> getMyEmployee(@PathVariable Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		
		Company company = companyService.findFirstByComIdx(comIdx);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", company);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
	@PostMapping("/chgComPwd")
	public ResponseEntity<HashMap<String, Object>> chgComPwd(@RequestBody Company company){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		Long comIdx = company.getComIdx();
		
		Company targetCom = companyService.findFirstByComIdx(comIdx);
		
		targetCom.setComPwd(passwordEncoder.encode(company.getComPwd()));
		
		companyService.save(targetCom);
		
		return new ResponseEntity<>(rtn, HttpStatus.OK);
	}
	
}
