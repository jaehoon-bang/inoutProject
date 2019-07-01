package kr.maxted.tamtam.admin.commute.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kr.maxted.tamtam.admin.commute.model.Commute;

public interface commuteRepository extends JpaRepository<Commute, Long>{

	Commute findByEmpIdxAndWkpIdAndAtdDd(Long empIdx, Long wkpId, String atdDd);
	
	List<Commute> findAllByEmpIdxAndWkpIdAndAtdDdBetween(Long empIdx, Long wkpId, String startDd, String endDd);

	
	@Query(value = "SELECT COMM_ID AS CommId, C.WKP_ID AS WkpId,\r\n" +
			" C.EMP_IDX AS EmpId, E.EMP_NM AS EmpNm, ATD_DD AS AtdDd, LEV_DD AS LevDd,\r\n" + 
			" ATD_TM AS AtdTm, LEV_TM AS LevTm, ROW1.APPR_STAT AS AtdApprStat, ROW2.APPR_STAT AS LevApprStat FROM COMMUTE AS C\r\n" + 
			"INNER JOIN EMPLOYEE AS E ON C.EMP_IDX = E.EMP_IDX\r\n" + 
			"LEFT OUTER JOIN REQ_OUTSIDE_WORK AS ROW1 ON C.OUT_ATD_IDX = ROW1.REQ_IDX\r\n" + 
			"LEFT OUTER JOIN REQ_OUTSIDE_WORK AS ROW2 ON C.OUT_LEV_IDX = ROW2.REQ_IDX\r\n" +
			"WHERE C.WKP_ID = :wkpId AND C.ATD_DD = :atdDd ", nativeQuery = true)
	List<Object[]> findAllByWkpIdAndAtdDd(@Param("wkpId")Long wkpId, @Param("atdDd") String atdDd);
	
	@Query(value = "SELECT COMM_ID AS CommId, C.WKP_ID AS WkpId,\r\n" +
			" C.EMP_IDX AS EmpId, E.EMP_NM AS EmpNm, ATD_DD AS AtdDd, LEV_DD AS LevDd,\r\n" + 
			" ATD_TM AS AtdTm, LEV_TM AS LevTm, ROW1.APPR_STAT AS AtdApprStat, ROW2.APPR_STAT AS LevApprStat FROM COMMUTE AS C\r\n" + 
			"INNER JOIN EMPLOYEE AS E ON C.EMP_IDX = E.EMP_IDX\r\n" + 
			"LEFT OUTER JOIN REQ_OUTSIDE_WORK AS ROW1 ON C.OUT_ATD_IDX = ROW1.REQ_IDX\r\n" + 
			"LEFT OUTER JOIN REQ_OUTSIDE_WORK AS ROW2 ON C.OUT_LEV_IDX = ROW2.REQ_IDX\r\n" +
			"WHERE C.WKP_ID = :wkpId AND C.EMP_IDX = :empIdx AND C.ATD_DD LIKE CONCAT('',:atdDd,'%')", nativeQuery = true)
	List<Object[]> queryWkpIdAndEmpIdxAndAtdDd(@Param("wkpId")Long wkpId, @Param("empIdx") Long empIdx, @Param("atdDd") String atdDd);
	
	List<Commute> findAllByWkpIdAndEmpIdx(Long wkpId, Long empIdx);
}	
