package kr.maxted.tamtam.admin.comEmpRel.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ComEmpRelPk implements Serializable {

	@Column(name = "COM_IDX")
	private Long comIdx;
	
	@Column(name = "EMP_IDX")
	private Long empIdx;

}
