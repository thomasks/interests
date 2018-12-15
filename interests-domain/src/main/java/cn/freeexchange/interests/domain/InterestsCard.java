package cn.freeexchange.interests.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//卡配置信息
public class InterestsCard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "interests_id")
	private Long interestsId;
	
	
	@Column(name = "card_type")
	private String cardType;
	
	@Column(name = "card_url")
	private String cardUrl;
	
	@Column(name = "logo_url")
	private String logoUrl;
	
	@Column(name = "card_name")
	private String cardName;
	
	@Column(name = "instructions")
	private String instructions;
	
	
}
