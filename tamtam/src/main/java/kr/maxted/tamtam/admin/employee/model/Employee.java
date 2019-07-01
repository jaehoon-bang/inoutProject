package kr.maxted.tamtam.admin.employee.model;

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


@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMP_IDX")
	private Long empIdx;
	
	@Column(name = "EMP_ID")
	private String empId;
	
	@Column(name = "EMP_HP")
	private String empHp;
	
	@Column(name = "EMP_NM")
	private String empNm;
	
	@Column(name = "EMP_EMAIL")
	private String empEmail;
	
	@Column(name = "EMP_PWD")
	private String empPwd;
	
	@Column(name = "EMP_STAT")
	@Enumerated(EnumType.STRING)
	private EnumYn empStat;
	
	@Column(name = "DEV_INFO")
	private String devInfo;
	
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
	
	@Column(name = "JOIN_DD")
	private String joinDd;
	
	
	
}
