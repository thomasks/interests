package cn.freeexchange.interests.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



public class Card {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "partner")
	private Long partner;
	
	@Column(name = "open_id")
	private Long openId;
	
	@Column(name = "card_name")
	private String cardName;
	
	@Column(name = "img_url")
	private String imgUrl;
	
	@Column(name = "logo_url")
	private String logoUrl;
	
	@Column(name = "instructions")
	private String instructions;
	
	@Column(name = "interests_value")
	private Long interestsValue;
	
	@Column(name = "usage_value")
	private Long usageValue;
	
	@Column(name = "activation")
	private boolean activation;
	
	private String refType;
	
	private String refValue;
}
