package kr.maxted.tamtam.admin.outsideWork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.maxted.tamtam.admin.outsideWork.model.OutsideWork;

@Repository
public interface OutsideWorkRepository extends JpaRepository<OutsideWork, Long>{

	OutsideWork findFirsByReqIdx(Long reqIdx);
	
	OutsideWork findFirstByEmpIdxAndWkpIdAndReqDtOrderByReqIdxDesc(Long empIdx, Long wkpId, String reqDt);
	
	@Query(value = 
			"SELECT A.REQ_IDX,\r\n" + 
			"	   A.COM_IDX, \r\n" + 
			"	   A.EMP_IDX, \r\n" + 
			"	   A.WKP_ID, \r\n" + 
			"	   A.REQ_DT, \r\n" + 
			"	   A.GPS_LAT, \r\n" + 
			"	   A.GPS_LON, \r\n" + 
			"	   A.APPR_STAT, \r\n" + 
			"	   A.REQ_DIV, \r\n" + 
			"	   B.EMP_NM,\r\n" + 
			"	   if(A.REQ_DIV = 'ATD', C.ATD_TM, C.LEV_TM) AS REQ_TM\r\n" + 
			"FROM REQ_OUTSIDE_WORK AS A \r\n" + 
			"INNER JOIN EMPLOYEE AS B \r\n" + 
			"ON A.EMP_IDX = B.EMP_IDX\r\n" + 
			"INNER JOIN COMMUTE AS C ON (C.OUT_ATD_IDX = A.REQ_IDX OR C.OUT_LEV_IDX = A.REQ_IDX)\r\n" + 
			"WHERE A.COM_IDX = :comIdx \r\n" + 
			"AND A.APPR_STAT = :apprStat ", nativeQuery = true)
	List<Object[]> getOutApprList(@Param("comIdx") Long comIdx, @Param("apprStat") String apprStat);
	
	@Query(value = 
			"SELECT A.REQ_IDX,\r\n" + 
			"	   A.COM_IDX, \r\n" + 
			"	   A.EMP_IDX, \r\n" + 
			"	   A.WKP_ID, \r\n" + 
			"	   A.REQ_DT, \r\n" + 
			"	   A.GPS_LAT, \r\n" + 
			"	   A.GPS_LON, \r\n" + 
			"	   A.APPR_STAT, \r\n" + 
			"	   A.REQ_DIV, \r\n" + 
			"	   B.EMP_NM,\r\n" + 
			"	   if(A.REQ_DIV = 'ATD', C.ATD_TM, C.LEV_TM) AS REQ_TM\r\n" + 
			"FROM REQ_OUTSIDE_WORK AS A \r\n" + 
			"INNER JOIN EMPLOYEE AS B \r\n" + 
			"ON A.EMP_IDX = B.EMP_IDX\r\n" + 
			"INNER JOIN COMMUTE AS C ON (C.OUT_ATD_IDX = A.REQ_IDX OR C.OUT_LEV_IDX = A.REQ_IDX)\r\n" + 
			"WHERE A.REQ_IDX = :reqIdx ", nativeQuery = true)
	List<Object[]> getOutApprDeail(@Param("reqIdx") Long reqIdx);
}
