package cn.freeexchange.interests.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_partner_card_level", schema = "interests")
public class PartnerCardLevel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "partner")
	private Long partner;
	
	@Column(name = "card_type")
	private String cardType;
	
	@Column(name = "card_level")
	private String cardLevel;
	
	@Column(name = "min_value")
	private Long minValue;
	
	@Column(name = "max_value")
	private Long maxValue;
	
	@Column(name = "card_url")
	private String cardUrl;
	
	@Column(name = "logo_url")
	private String logoUrl;
	
	@Column(name = "card_name")
	private String cardName;
	
	@Column(name = "instructions")
	private String instructions;
	
	public Card makeCard(Long openId,Long interestsValue,String issuerName) {
		return Card.makeCard(openId, this, interestsValue,issuerName);
	}
}
