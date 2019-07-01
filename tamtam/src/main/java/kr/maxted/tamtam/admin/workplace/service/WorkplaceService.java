package kr.maxted.tamtam.admin.workplace.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxted.tamtam.admin.workplace.model.Workplace;
import kr.maxted.tamtam.admin.workplace.repository.WorkplaceRepository;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;

@Service
public class WorkplaceService {

	@Resource
	private WorkplaceRepository workplaceRepository;
	
	public WorkplaceService() {
		
	}
	
	public Workplace save(Workplace workplace) {
		return workplaceRepository.save(workplace);
	}
	
	public Workplace saveAndFlush(Workplace workplace) {
		return workplaceRepository.saveAndFlush(workplace);
	}
	
	public List<Workplace> findAllByComIdxAndDelYnOrderByWkpNm(Long comIdx, EnumYn delYn){
		return workplaceRepository.findAllByComIdxAndDelYnOrderByWkpNm(comIdx, delYn);
	}
	
	public Workplace findFirstByWkpId(Long wkpId) {
		return workplaceRepository.findFirstByWkpId(wkpId);
	}
	
}
