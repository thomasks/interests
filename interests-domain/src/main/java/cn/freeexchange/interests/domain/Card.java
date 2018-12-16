package cn.freeexchange.interests.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;

import cn.freeexchange.common.base.exception.BusinessException;
import cn.freeexchange.interests.api.InterestsMetaType;
import cn.freeexchange.interests.dto.CardDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_card", schema = "interests")
public class Card implements InterestsMeta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "partner")
	private Long partner;
	
	@Column(name = "open_id")
	private Long openId;
	
	@Column(name="card_no")
	private String cardNo;
	
	@ManyToOne
	@JoinColumn(name="partner_card_level_id")
	private PartnerCardLevel partnerCardLevel;
	
	@Column(name = "interests_value")
	private Long interestsValue;
	
	@Column(name = "usage_value")
	private Long usageValue = 0L;
	
	@Column(name = "activation")
	private boolean activation = true;
	
	@Column(name = "ref_type")
	private String refType;
	
	@Column(name = "ref_value")
	private String refValue;
	
	@Column(name = "create_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    @Column(name = "logic_delete")
    private Boolean logicDelete = false;
    
    @Column(name = "issuer_name")
    private String issuerName;
    
    
    protected Card() {
    	
    }
    
    
    public void syncUsageValue(Long amount) {
    	Long usageValue  = (this.usageValue == null) ? 0L : this.usageValue;
    	Long latestsValue = usageValue + amount;
    	if(latestsValue > this.interestsValue) {
    		throw new BusinessException("coupon05");
    	}
    	this.usageValue = latestsValue;
    }
    
    protected Card(Long openId,PartnerCardLevel partnerCardLevel,Long interestsValue,String issuerName) {
    	this.cardNo = RandomStringUtils.random(12, false, true);
    	this.partner= partnerCardLevel.getPartner();
    	this.openId =openId;
    	this.interestsValue = interestsValue;
    	this.partnerCardLevel = partnerCardLevel;
    	this.issuerName = issuerName;
    }
    
    public static Card makeCard(Long openId,PartnerCardLevel partnerCardLevel,Long interestsValue,String issuerName) {
    	return new Card(openId, partnerCardLevel, interestsValue,issuerName);
    }
    
    
    
    public CardDto makeDto() {
    	return CardDto.makeCardDto(cardNo, partnerCardLevel.getCardName(), issuerName, 
    			partnerCardLevel.getCardUrl(), partnerCardLevel.getLogoUrl());
    			
    }
    
	@Override
	public Long intestesValue() {
		return interestsValue;
	}

	@Override
	public Long usageValue() {
		return usageValue;
	}

	@Override
	public String metaType() {
		return InterestsMetaType.CARD.getCode();
	}

	@Override
	public Long metaId() {
		return id;
	}

	@Override
	public String interestsType() {
		return getPartnerCardLevel().getCardType();
	}

}
