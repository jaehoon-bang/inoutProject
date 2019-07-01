
package kr.maxted.tamtam.admin.empSche.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import kr.maxted.tamtam.admin.empSche.model.EmpSche;

@Repository
public interface EmpScheRepository extends JpaRepository<EmpSche, Long>{
	
	@Query(value = "SELECT A.EMP_IDX, "
			+ "A.WKP_ID, "
			+ "B.WKP_NM, "
			+ "B.GPS_LON,   "
			+ "B.GPS_LAT   "
			+ "FROM EMP_SCHE AS A  "
			+ "INNER JOIN WORKPLACE AS B "
			+ "ON A.WKP_ID = B.WKP_ID "
			+ "WHERE A.EMP_IDX = :empIdx "
			+ "GROUP BY A.EMP_IDX, A.WKP_ID", nativeQuery = true)
	List<Object[]> findEmployeeWorkPlaceListByEmpIdx(@Param("empIdx") Long empIdx);
	
}
