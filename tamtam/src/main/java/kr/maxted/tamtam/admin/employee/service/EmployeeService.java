package kr.maxted.tamtam.admin.employee.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.maxted.tamtam.admin.comEmpRel.model.ComEmpRel;
import kr.maxted.tamtam.admin.comEmpRel.repository.ComEmpRelRepository;
import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.admin.employee.repository.EmployeeRepository;
import kr.maxted.tamtam.core.utils.StringUtils;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

/**
 * @author bjh89
 *
 */
@Service
@Transactional(value = "transactionManagerAdmin", rollbackFor = Exception.class)
public class EmployeeService {
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);
	
	@Resource
	private EmployeeRepository employeeRepository;
	
	@Resource
	private ComEmpRelRepository comEmpRelRepository;
	
	@Resource
	private PasswordEncoder passwordEncoder;
	
	public EmployeeService() {
		
	}
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	/**
	 * Employee 검색
	 * @since 2019-06-28
	 * @param Long empIdx
	 * @return Employee
	 */
	public Employee findFirstByEmpIdx(Long empIdx) {
		return employeeRepository.findFirstByEmpIdx(empIdx);
	}
	
	/**
	 * Employee 검색
	 * @since 2019-06-28
	 * @param Long empIdx,  EnumYn delYn
	 * @return Employee
	 */
	public Employee findFirstByEmpIdxAndDelYn(Long empIdx,  EnumYn delYn) {
		return employeeRepository.findFirstByEmpIdxAndDelYn(empIdx, delYn);
	}
	
	/**
	 * Employee 검색
	 * @since 2019-06-28
	 * @param String empId
	 * @return Employee
	 */
	public Employee findFirstByEmpId(String empId) {
		return employeeRepository.findFirstByEmpId(empId);
	}
	
	
	/**
	 * Employee 검색
	 * @since 2019-06-28
	 * @param String empId, String empPwd
	 * @return Employee
	 */
	public Employee findFirstByEmpIdAndEmpPwd(String empId, String empPwd) {
		return employeeRepository.findFirstByEmpIdAndEmpPwd(empId, empPwd);
	}
	
	/**
	 * Employee 검색
	 * @since 2019-06-28
	 * @param String empNm, String empHp, EnumYn delYn
	 * @return Employee
	 */
	public Employee findFirstByEmpNmAndEmpHpAndDelYn(String empNm, String empHp, EnumYn delYn) {
		return employeeRepository.findFirstByEmpNmAndEmpHpAndDelYn(empNm, empHp, delYn);
	}
	
	/**
	 * Employee 저장
	 * @since 2019-06-28
	 * @param Employee employee
	 * @return Employee
	 */
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	/**
	 * 직원 조회
	 * @since 2019-06-28
	 * @param Long comIdx
	 * @return List<HashMap<String, Object>>
	 */
	public HashMap<String, Object> getEmpList(Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		List<HashMap<String, Object>> empList = new ArrayList<HashMap<String, Object>>();
		
		List<Object[]> objList = employeeRepository.findComEmpList(comIdx);
		
		for(Object[] obj : objList) {
			HashMap<String, Object> empMap = new HashMap<String, Object>();
			
			//Object to Map	
			empMap.put("empIdx", obj[0]);
			empMap.put("empId", obj[1]);
			empMap.put("empNm", obj[2]);
			empMap.put("empHp", obj[3]);
			empMap.put("empEmail", obj[4]);
			empMap.put("joinDd", obj[5]);
			
			empList.add(empMap);
		}
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", empList);
		return rtn;
	}
	
	/**
	 * 퇴사자 조회
	 * @since 2019-06-28
	 * @param Long comIdx
	 * @return List<HashMap<String, Object>>
	 */
	public HashMap<String, Object> getLevEmpList(Long comIdx){
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		List<HashMap<String, Object>> empList = new ArrayList<HashMap<String, Object>>();
		
		List<Object[]> objList = employeeRepository.findComLeaveEmpList(comIdx);
		
		//Object to Map	
		for(Object[] obj : objList) {
			HashMap<String, Object> empMap = new HashMap<String, Object>();
			
			empMap.put("empIdx", obj[0]);
			empMap.put("empId", obj[1]);
			empMap.put("empNm", obj[2]);
			empMap.put("empHp", obj[3]);
			empMap.put("empEmail", obj[4]);
			empMap.put("joinDd", obj[5]);
			
			empList.add(empMap);
		}
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", empList);
		return rtn;
	}
	
	
	/**
	 * 직원 추가
	 * @since 2019-06-28
	 * @param HashMap<String, Object>
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> addEmp(HashMap<String, Object> param) throws Exception{
		HashMap<String, Object > rtn = new HashMap<String, Object>();
		
		try {
		//parameters
		Long comIdx = Long.parseLong(StringUtils.defaultString(param.get("comIdx")));
		String empId = StringUtils.defaultString(param.get("empId"));
		String empHp = StringUtils.defaultString(param.get("empHp"));
		String empNm = StringUtils.defaultString(param.get("empNm"));
		String empEmail = StringUtils.defaultString(param.get("empEmail"));
		String empPwd = passwordEncoder.encode(StringUtils.defaultString(empHp.substring(7, 11)));
		String devInfo = StringUtils.defaultString(param.get("devInfo"));
		String joinDd = StringUtils.defaultString(param.get("joinDd"));
		
		//entity
		Employee employee = new Employee();
		ComEmpRel comEmpRel = new ComEmpRel();
		
		//setting employee entity parameters
		employee.setEmpId(empId);
		employee.setEmpHp(empHp);
		employee.setEmpNm(empNm);
		employee.setEmpEmail(empEmail);
		employee.setEmpPwd(empPwd);
		employee.setEmpStat(EnumYn.Y);
		employee.setDevInfo(devInfo);
		employee.setDelYn(EnumYn.N);
		employee.setJoinDd(joinDd);
		employee.setRegId("admin");
		employee.setRegDt("20190624100500");
		
		//employee save
		employeeRepository.save(employee);
		
		//setting comEmpRel entity parameters 
		comEmpRel.setComIdx(comIdx);
		comEmpRel.setEmpIdx(employee.getEmpIdx());
		comEmpRel.setDelYn(EnumYn.N);
		comEmpRel.setRegId("admin");
		comEmpRel.setRegDt("20190627100000");
		
		//comEmpRel save
		comEmpRelRepository.save(comEmpRel);
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", "S");
		
		throw new RuntimeException();
		
		} catch(Exception e) {
			log.error("EmployeeService addEmp error!!");
			rtn.put("systemCode", HttpStatus.BAD_REQUEST);
			rtn.put("resultData", "F");
		}
		return rtn;
	}
	
	/**
	 * 직원 수정
	 * @since 2019-06-28
	 * @param Employee
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> updateEmp(Employee employee) throws Exception{
		
		HashMap<String, Object> rtn = new HashMap<String, Object>();
		try {
		Employee emp = this.findFirstByEmpIdx(employee.getEmpIdx());
		
		emp.setEmpNm(employee.getEmpNm());
		emp.setEmpHp(employee.getEmpHp());
		emp.setEmpEmail(employee.getEmpEmail());
		
		employeeRepository.save(emp);
		
		rtn.put("systemCode", HttpStatus.OK);
		rtn.put("resultData", "S");
		
		} catch(Exception e) {
			log.error("EmployeeService updateEmp error!!");
			rtn.put("systemCode", HttpStatus.BAD_REQUEST);
			rtn.put("resultData", "F");
		}
		
		return rtn;
	}
	
}
