package kr.maxted.tamtam.admin.comEmpRel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import kr.maxted.tamtam.admin.comEmpRel.model.pk.ComEmpRelPk;
import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;
import lombok.Data;

@Entity
@Table(name = "COM_EMP_REL")
@Data
@IdClass(ComEmpRelPk.class)
public class ComEmpRel {

	@Id
	@Column(name = "COM_IDX")
	private Long comIdx;
	
	@Id
	@Column(name = "EMP_IDX")
	private Long empIdx;
	
	@Column(name = "DEL_YN")
	@Enumerated(EnumType.STRING)
	private EnumYn delYn;
	
	@Column(name = "REG_ID")
	private String regId;

	@Column(name = "REG_DT")
	private String regDt;
	
	@Column(name = "UPD_ID")
	private String updId;
	
	@Column(name = "UPD_DT")
	private String updDt;
	
	
}
