package kr.maxted.tamtam.admin.commute.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CommutePk implements Serializable {

	@Column(name="COMM_ID")
	private Long commId;
	
}
