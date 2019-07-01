package kr.maxted.tamtam.admin.empSche.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.maxted.tamtam.admin.empSche.model.EmpSche;
import kr.maxted.tamtam.admin.empSche.repository.EmpScheRepository;

@Service
public class EmpScheService {

	@Autowired
	private EmpScheRepository empScheRepository;
	
	public EmpScheService() {
		
	}
	
	@Autowired
	public EmpScheService(EmpScheRepository empScheRepository) {
		this.empScheRepository = empScheRepository;
	}
	
	
	public void save(EmpSche empSche) {
		empScheRepository.save(empSche);
	}
	
	public List<HashMap<String, Object>> findEmployeeWorkPlaceListByEmpIdx(Long empIdx) {
		List<Object[]> orgList = empScheRepository.findEmployeeWorkPlaceListByEmpIdx(empIdx);
		List<HashMap<String, Object>> rtnList = new ArrayList<HashMap<String,Object>>();
		
		for(Object[] obj : orgList) {
			HashMap<String, Object> rtn = new HashMap<String, Object>();
			rtn.put("empIdx", obj[0]);
			rtn.put("wkpId", obj[1]);
			rtn.put("wkpNm", obj[2]);
			rtn.put("gpsLon", obj[3]);
			rtn.put("gpsLat", obj[4]);
			rtnList.add(rtn);
		}
		
		return rtnList;
	}
}
