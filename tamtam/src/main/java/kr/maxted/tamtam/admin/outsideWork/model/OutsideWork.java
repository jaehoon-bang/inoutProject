package kr.maxted.tamtam.admin.outsideWork.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.maxted.tamtam.core.utils.enumUtils.ApprStat;
import kr.maxted.tamtam.core.utils.enumUtils.outsideWorkReqDiv;
import lombok.Data;

@Entity
@Data
@Table(name = "REQ_OUTSIDE_WORK")
public class OutsideWork {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REQ_IDX")
	private Long reqIdx;
	
	@Column(name = "COM_IDX")
	private Long comIdx;
	
	@Column(name = "EMP_IDX")
	private Long empIdx;
	
	@Column(name = "WKP_ID")
	private Long wkpId;
	
	@Column(name = "REQ_DT")
	private String reqDt;
	
	@Column(name = "REQ_CMT")
	private String reqCmt;
	
	@Column(name = "GPS_LAT")
	private String gpsLat;
	
	@Column(name = "GPS_LON")
	private String gpsLon;
	
	@Column(name = "APPR_DT")
	private String apprDt;
	
	@Column(name = "APPR_ID")
	private String apprId;
	
	@Column(name = "APPR_STAT")
	@Enumerated(EnumType.STRING)
	private ApprStat apprStat;
	
	@Column(name = "REQ_DIV")
	@Enumerated(EnumType.STRING)
	private outsideWorkReqDiv reqDiv;
	
	@Column(name = "REG_ID")
	private String regId;
	
	@Column(name = "REG_DT")
	private String regDt;
	
	@Column(name = "UPD_ID")
	private String updId;
	
	@Column(name = "UPD_DT")
	private String updDt;
}
