package kr.maxted.tamtam.admin.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.maxted.tamtam.admin.employee.model.Employee;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findFirstByEmpIdx(Long empIdx);
	
	Employee findFirstByEmpId(String empId);
	
	Employee findFirstByEmpIdxAndDelYn(Long empIdx, EnumYn delYn);
	
	Employee findFirstByEmpIdAndEmpPwd(String empId, String empPwd);
	
	Employee findFirstByEmpNmAndEmpHpAndDelYn(String empNm, String empHp, EnumYn delYn);
	
	@Query(value = 
			"SELECT E.EMP_IDX,\r\n"+
			"E.EMP_ID,\r\n" + 
			"E.EMP_NM,\r\n " + 
			"E.EMP_HP,\r\n" + 
			"E.EMP_EMAIL,\r\n" +
			"E.JOIN_DD\r\n" +
			"FROM COM_EMP_REL AS CER \r\n" + 
			"JOIN EMPLOYEE AS E ON CER.EMP_IDX = E.EMP_IDX\r\n" + 
			"WHERE CER.COM_IDX = :comIdx\r\n" + 
			"AND CER.DEL_YN = 'N'", nativeQuery = true)
	List<Object[]> findComEmpList(@Param("comIdx") Long comIdx);
	
	@Query(value =			
			"SELECT E.EMP_IDX,\r\n"+
			"E.EMP_ID,\r\n" + 
			"E.EMP_NM,\r\n " + 
			"E.EMP_HP,\r\n" + 
			"E.EMP_EMAIL,\r\n" +
			"E.JOIN_DD\r\n" +
			"FROM COM_EMP_REL AS CER \r\n" + 
			"JOIN EMPLOYEE AS E ON CER.EMP_IDX = E.EMP_IDX\r\n" + 
			"WHERE CER.COM_IDX = :comIdx\r\n" + 
			"AND CER.DEL_YN = 'Y'", nativeQuery = true)
	List<Object[]> findComLeaveEmpList(@Param("comIdx") Long comIdx);
	
}
 