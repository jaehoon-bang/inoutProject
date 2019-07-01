package kr.maxted.tamtam.admin.apprCom.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;

@Embeddable
public class ApprComPk implements Serializable{

	@Column(name = "COM_CD")
	private Long comCd;
	
	@Column(name = "SEQ")
	@GeneratedValue
	private int seq;

	public Long getComCd() {
		return comCd;
	}

	public void setComCd(Long comCd) {
		this.comCd = comCd;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}
	
}
