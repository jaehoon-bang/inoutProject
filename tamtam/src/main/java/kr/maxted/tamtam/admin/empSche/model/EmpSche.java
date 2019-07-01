package kr.maxted.tamtam.admin.empSche.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import kr.maxted.tamtam.admin.empSche.model.pk.empSchePk;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;
import kr.maxted.tamtam.core.utils.enumUtils.enumDay;
import lombok.Data;

@Entity
@Table(name = "EMP_SCHE")
@IdClass(empSchePk.class)
@Data
public class EmpSche {

	@Id
	@Column(name = "EMP_IDX")
	private Long empIdx;
	
	@Id
	@Column(name = "WKP_ID")
	private Long wkpId;
	
	@Id
	@Column(name = "ATD_DAY")
	@Enumerated(EnumType.STRING)
	private enumDay atdDay;
	
	@Column(name = "ATD_TM")
	private String atdTm;
	
	@Column(name = "RET_TM")
	private String retTm;
	
	@Column(name = "DEL_YN")
	@Enumerated(EnumType.STRING)
	private EnumYn delYN;

	@Column(name = "REG_ID")
	private String regId;
	
	@Column(name = "REG_DT")
	private String regDt;
	
	@Column(name = "UPD_ID")
	private String updId;
	
	@Column(name = "UPD_DT")
	private String updDt;
	
}
