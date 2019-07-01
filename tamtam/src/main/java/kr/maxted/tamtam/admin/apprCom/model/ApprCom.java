package kr.maxted.tamtam.admin.apprCom.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import kr.maxted.tamtam.admin.apprCom.model.pk.ApprComPk;

@Entity
@Table(name = "APPR_COM")
@IdClass(ApprComPk.class)
public class ApprCom {

	@Id
	@Column(name = "SEQ")
	private int seq;
	
	@Id
	@NotNull
	@Column(name = "COM_CD")
	private Long comCd;
	
	@NotNull
	@Column(name = "COM_EMAIL")
	private String comEmail;
	
	@Column(name = "APPR_STAT")
	private char apprStat;
	
	@Column(name = "APPR_ID")
	private String apprId;
	
	@Column(name = "APPR_DT")
	private String apprDt;
	
	@Column(name = "FILE_ID")
	private String fileId;
	
	@Column(name = "REG_ID")
	private String regId;
	
	@Column(name = "REG_DT")
	private String regDt;
	
	@Column(name = "UPD_ID")
	private String updId;
	
	@Column(name = "UPD_DT")
	private String updDt;
	
	public ApprCom() {
		
	}
	

	public int getSeq() {
		return seq;
	}


	public void setSeq(int seq) {
		this.seq = seq;
	}


	public Long getComCd() {
		return comCd;
	}


	public void setComCd(Long comCd) {
		this.comCd = comCd;
	}


	public String getComEmail() {
		return comEmail;
	}


	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}

	
	public char getApprStat() {
		return apprStat;
	}

	public void setApprStat(char apprStat) {
		this.apprStat = apprStat;
	}

	public String getApprId() {
		return apprId;
	}

	public void setApprId(String apprId) {
		this.apprId = apprId;
	}

	public String getApprDt() {
		return apprDt;
	}

	public void setApprDt(String apprDt) {
		this.apprDt = apprDt;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getUpdId() {
		return updId;
	}

	public void setUpdId(String updId) {
		this.updId = updId;
	}

	public String getUpdDt() {
		return updDt;
	}

	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	
}
