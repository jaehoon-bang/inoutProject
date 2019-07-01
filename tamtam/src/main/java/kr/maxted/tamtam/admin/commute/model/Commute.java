package kr.maxted.tamtam.admin.commute.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import kr.maxted.tamtam.admin.commute.model.pk.CommutePk;
import lombok.Data;

@Entity
@Table(name = "COMMUTE")
@Data
@IdClass(CommutePk.class)
public class Commute {
	
	@Id
	@Column(name="COMM_ID")
	private Long commId;
	
	@Column(name="WKP_ID")
	private Long wkpId;
	
	@Column(name="EMP_IDX")
	private Long empIdx;
	
	@Column(name="ATD_DD")
	private String atdDd;
	
	@Column(name="LEV_DD")
	private String levDd;
	
	@Column(name="ATD_TM")
	private String atdTm;
	
	@Column(name="LEV_TM")
	private String levTm;
	
	@Column(name="OUT_ATD_IDX")
	private Long outAtdIdx;
	
	@Column(name="OUT_LEV_IDX")
	private Long outLevIdx;
	
	@Column(name="REG_ID")
	private String regId;
	
	@Column(name="REG_DT")
	private String regDt;
	
	@Column(name="UPD_ID")
	private String updId;
	
	@Column(name="UPD_DT")
	private String updDt;

}
