package kr.maxted.tamtam.admin.workplace.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.maxted.tamtam.core.utils.enumUtils.EnumYn;
import lombok.Data;

@Entity
@Data
@Table(name = "WORKPLACE")
public class Workplace {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "WKP_ID")
	private Long wkpId;
	
	@Column(name = "COM_IDX")
	private Long comIdx;
	
	@Column(name = "WKP_NM")
	private String wkpNm;
	
	@Column(name = "WKP_ADDR")
	private String wkpAddr;
	
	@Column(name = "GPS_LAT")
	private String gpsLat;
	
	@Column(name = "GPS_LON")
	private String gpsLon;
	
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
