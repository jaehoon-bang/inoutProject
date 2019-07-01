package kr.maxted.tamtam.admin.apprCom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.maxted.tamtam.admin.apprCom.model.ApprCom;

@Repository
public interface ApprComRepository extends JpaRepository<ApprCom, Long> {

	ApprCom findFirstByComCdOrderByRegDtDesc(Long comCd);
	
	List<ApprCom> findByComCdAndApprStat(Long comCd, char apprStat);
}
