package kr.maxted.tamtam.admin.workplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.maxted.tamtam.admin.workplace.model.Workplace;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long>{
	
	
	Workplace findFirstByWkpId(Long wkpId);
	
	List<Workplace> findAllByComIdxAndDelYnOrderByWkpNm(Long comIdx, EnumYn delYn);
	
}
