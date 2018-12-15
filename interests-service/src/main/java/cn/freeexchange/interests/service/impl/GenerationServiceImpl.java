package cn.freeexchange.interests.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.freeexchange.interests.api.InterestsCardType;
import cn.freeexchange.interests.api.InterestsMetaType;
import cn.freeexchange.interests.domain.Card;
import cn.freeexchange.interests.domain.GenerationRule;
import cn.freeexchange.interests.domain.Interests;
import cn.freeexchange.interests.domain.InterestsMeta;
import cn.freeexchange.interests.domain.PartnerCardLevel;
import cn.freeexchange.interests.domain.rpt.CardRpt;
import cn.freeexchange.interests.domain.rpt.GenerationRuleRpt;
import cn.freeexchange.interests.domain.rpt.InterestsRpt;
import cn.freeexchange.interests.domain.rpt.PartnerCardLevelRpt;
import cn.freeexchange.interests.service.GenerationService;

@Service
public class GenerationServiceImpl implements GenerationService {
	
	
	@Autowired
	private GenerationRuleRpt generationRuleRpt;
	
	@Autowired
	private InterestsRpt interestsRpt;
	
	@Autowired
	private PartnerCardLevelRpt partnerCardLevelRpt;
	
	@Autowired
	private CardRpt cardRpt;
	
	@Override
	@Transactional
	public InterestsMeta genearation(Long interestsId,Long openId,String scenario, Long amount) {
		Interests interests = interestsRpt.getOne(interestsId);
		GenerationRule gr = 
				generationRuleRpt.findGenerationRuleByInterestsAndScenario(interestsId, scenario);
		if(gr.getMediaType().equalsIgnoreCase(InterestsMetaType.CARD.getCode())) {
			String cardType = interests.getScene();
			Long partner = interests.getPartner();
			if(cardType.equalsIgnoreCase(InterestsCardType.DEPOSITE_DISCOUNT_CARD.getCode())) {
				Map<String, String> ruleProps = gr.ruleProps();
				BigDecimal costRatio = new BigDecimal(ruleProps.get("costRatio"));
				BigDecimal interestsRatio = new BigDecimal(ruleProps.get("interestsRatio"));
				Long interestsAmount = new BigDecimal(amount).divide(costRatio).multiply(interestsRatio).longValue();
				PartnerCardLevel partnerCardLevel = partnerCardLevelRpt.findPartnerCardLevelByAmount(partner, cardType, interestsAmount);
				Card card = partnerCardLevel.makeCard(openId, interestsAmount,interests.getIssuerName());
				cardRpt.save(card);
				return card;
			}
		}
		return null;
	}

}
