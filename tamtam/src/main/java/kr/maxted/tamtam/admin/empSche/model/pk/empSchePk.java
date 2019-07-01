package kr.maxted.tamtam.admin.empSche.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import kr.maxted.tamtam.core.utils.enumUtils.enumDay;
import lombok.Data;

@Embeddable
@Data
public class empSchePk implements Serializable{

	@Column(name = "EMP_IDX")
	private Long empIdx;
	
	@Column(name = "WKP_ID")
	private Long wkpId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "ATD_DAY")
	private enumDay atdDay;
}
