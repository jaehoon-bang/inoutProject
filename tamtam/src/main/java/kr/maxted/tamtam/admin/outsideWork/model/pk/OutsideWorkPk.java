package kr.maxted.tamtam.admin.outsideWork.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@SuppressWarnings("serial")
@Embeddable
@Data
public class OutsideWorkPk implements Serializable{
	
	@Column(name = "REQ_IDX")
	private Long reqIdx;
	
	@Column(name = "COM_IDX")
	private Long comIdx;
	
	@Column(name = "EMP_IDX")
	private Long empIdx;
	
	@Column(name = "REQ_DT")
	private String reqDt;

}
 