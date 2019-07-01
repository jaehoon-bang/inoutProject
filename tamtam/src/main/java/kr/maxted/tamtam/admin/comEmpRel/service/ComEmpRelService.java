package kr.maxted.tamtam.admin.comEmpRel.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.maxted.tamtam.admin.comEmpRel.model.ComEmpRel;
import kr.maxted.tamtam.admin.comEmpRel.repository.ComEmpRelRepository;

@Service
public class ComEmpRelService {

	@Resource
	private ComEmpRelRepository comEmpRelRepository;
	
	public ComEmpRel save(ComEmpRel comEmpRel) {
		return comEmpRelRepository.save(comEmpRel);
	}
	
	public ComEmpRel findFirstByComIdxAndEmpIdx(Long comIdx, Long empIdx) {
		return comEmpRelRepository.findFirstByComIdxAndEmpIdx(comIdx, empIdx);
	}
}
