package kr.maxted.tamtam.admin.comEmpRel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.maxted.tamtam.admin.comEmpRel.model.ComEmpRel;

public interface ComEmpRelRepository extends JpaRepository<ComEmpRel, Long>{

	ComEmpRel findFirstByComIdxAndEmpIdx(Long comIdx, Long empIdx);
	
}
