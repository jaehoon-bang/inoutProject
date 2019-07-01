package kr.maxted.tamtam.admin.company.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;
import lombok.Data;

@Data
@Entity
@Table(name = "COMPANY")
public class Company {
	
	@Id
	@Column(name="COM_IDX")
	private Long comIdx;
	
	@Column(name="COM_ID")
	private String comId;
	
	@Column(name="COM_CD")
	private String comCd;
	
	@Column(name="COM_NM")
	private String comNm;
	
	@Column(name="COM_HP")
	private String comHp;
	
	@Column(name="COM_EMAIL")
	private String comEmail;
	
	@Column(name="COM_PWD")
	private String comPwd;
	
	@Column(name="DEV_INFO")
	private String devInfo;
	
	@Column(name="PUSH_TKN")
	private String pushTkn;
	
	@Column(name="DEL_YN")
	@Enumerated(EnumType.STRING)
	private EnumYn delYn;
	
	@Column(name="REG_ID")
	private String regId;
	
	@Column(name="REG_DT")
	private String regDt;
	
	@Column(name="UPD_ID")
	private String updId;
	
	@Column(name="UPD_DT")
	private String updDt;

}
