package cn.freeexchange.interests.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.freeexchange.interests.domain.Interests;
import cn.freeexchange.interests.domain.InterestsMeta;
import cn.freeexchange.interests.domain.rpt.InterestsRpt;
import cn.freeexchange.interests.dto.HoldInterestsResp;
import cn.freeexchange.interests.service.GenerationService;
import cn.freeexchange.interests.service.InterestsService;

@Service
public class InterestsServiceImpl implements InterestsService {
	
	@Autowired
	private InterestsRpt interestsRpt;

	@Autowired
	private GenerationService generationService;
	
	@Override
	@Transactional
	public HoldInterestsResp holdInterests(Long partner, Long openId, String scence,Map<String, Object> extParams) {
		Interests interests = interestsRpt.findInterestsByPartnerAndScene(partner, scence);
		Long interestsId = interests.getId();
		String scenario = extParams.get("scenario").toString();
		long yAmt = new BigDecimal(extParams.get("amount").toString()).multiply(new BigDecimal(1000)).longValue();
		InterestsMeta genearation = generationService.genearation(interestsId, openId, scenario, yAmt);
		HoldInterestsResp holdInterestsResp = 
				HoldInterestsResp.makeHoldInterestsResp(genearation.metaType(), genearation.metaId().toString(), 
				genearation.intestesValue(), genearation.interestsType());
		return holdInterestsResp;
	}

	@Override
	public HoldInterestsResp writeoffInterests(Long partner, Long openId, String couponId) {
		return null;
	}

	@Override
	public HoldInterestsResp holdAndWriteoffInterests(Long partner, Long openId, String scence,
			Map<String, Object> extParams) {
		
		return null;
	}

}
