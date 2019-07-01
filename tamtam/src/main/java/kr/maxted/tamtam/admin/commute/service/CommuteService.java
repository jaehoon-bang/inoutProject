package kr.maxted.tamtam.admin.commute.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.maxted.tamtam.admin.commute.model.Commute;
import kr.maxted.tamtam.admin.commute.repository.commuteRepository;

@Service
public class CommuteService {

	private commuteRepository commuteRepository;
	
	public CommuteService() {
		
	}
	
	@Autowired
	public CommuteService(commuteRepository commuteRepository) {
		this.commuteRepository = commuteRepository;
	}
	
	public void save(Commute commute) {
		commuteRepository.save(commute);
	}
	
	public Commute findByEmpIdxAndWkpIdAndAtdDd(Long empIdx, Long wkpId, String atdDd) {
		return commuteRepository.findByEmpIdxAndWkpIdAndAtdDd(empIdx, wkpId, atdDd);
	}
	
	public List<Commute> findAllByEmpIdxAndWkpIdAndAtdDdBetween(Long empIdx, Long wkpId, String startDd, String endDd){
		return commuteRepository.findAllByEmpIdxAndWkpIdAndAtdDdBetween(empIdx, wkpId, startDd, endDd);
	}
	
	public List<HashMap<String, Object>> findAllByWkpIdAndAtdDd(Long wkpId, String date){
		List<Object[]> rtnList = commuteRepository.findAllByWkpIdAndAtdDd(wkpId, date);
		List<HashMap<String, Object>> commList = new ArrayList<HashMap<String,Object>>();
		
		for(Object[] comm : rtnList) {
			HashMap<String, Object> commMap = new HashMap<String, Object>();
			commMap.put("commId", comm[0]);
			commMap.put("wkpId", comm[1]);
			commMap.put("empIdx", comm[2]);
			commMap.put("empNm", comm[3]);
			commMap.put("atdDd", comm[4]);
			commMap.put("levDd", comm[5]);
			commMap.put("atdTm", comm[6]);
			commMap.put("levTm", comm[7]);
			commMap.put("atdApprStat", comm[8]);
			commMap.put("levApprStat", comm[9]);
			commList.add(commMap);
		}
		
		return commList;
	}
	
	public List<HashMap<String, Object>> queryWkpIdAndEmpIdx(Long wkpId, Long empIdx, String atdDd){
		List<Object[]> rtnList = commuteRepository.queryWkpIdAndEmpIdxAndAtdDd(wkpId, empIdx, atdDd);
		List<HashMap<String, Object>> commList = new ArrayList<HashMap<String,Object>>();
		
		for(Object[] comm : rtnList) {
			HashMap<String, Object> commMap = new HashMap<String, Object>();
			commMap.put("commId", comm[0]);
			commMap.put("wkpId", comm[1]);
			commMap.put("empIdx", comm[2]);
			commMap.put("empNm", comm[3]);
			commMap.put("atdDd", comm[4]);
			commMap.put("levDd", comm[5]);
			commMap.put("atdTm", comm[6]);
			commMap.put("levTm", comm[7]);
			commMap.put("atdApprStat", comm[8]);
			commMap.put("levApprStat", comm[9]);
			commList.add(commMap);
		}
		
		return commList;
	}
	
	public List<Commute> findAllByWkpIdAndEmpIdx(Long wkpId, Long empIdx){
		return commuteRepository.findAllByWkpIdAndEmpIdx(wkpId, empIdx);
	}
	
}
