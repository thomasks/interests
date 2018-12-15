package cn.freeexchange.interests.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//卡配置信息
public class InterestsCoupon {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "interests_id")
	private Long interestsId;
	
	@Column(name = "instructions")
	private String instructions;
	
}
