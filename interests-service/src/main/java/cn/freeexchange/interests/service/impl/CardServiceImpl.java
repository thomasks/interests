package cn.freeexchange.interests.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.freeexchange.interests.domain.Card;
import cn.freeexchange.interests.domain.rpt.CardRpt;
import cn.freeexchange.interests.domain.rpt.CouponRpt;
import cn.freeexchange.interests.dto.CardDto;
import cn.freeexchange.interests.dto.CardOverviewDto;
import cn.freeexchange.interests.service.CardService;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRpt cardRpt;
	
	@Autowired
	private CouponRpt couponRpt;
	
	@Override
	public List<CardDto> queryCards(Long partner, Long openId) {
		List<CardDto> ret = new ArrayList<>();
		List<Card> cards = cardRpt.queryCards(partner,openId);
		for (Card card : cards) {
			ret.add(card.makeDto());
		}
		return ret;
	}

	@Override
	public CardOverviewDto cardOverview(Long partner, Long openId) {
		Long cardNum = cardRpt.countByPartnerAndOpenId(partner, openId);
		Long couponNum = couponRpt.countByPartnerAndOpenId(partner, openId);
		return CardOverviewDto.makeCardOverviewDto(cardNum, couponNum);
	}

}
