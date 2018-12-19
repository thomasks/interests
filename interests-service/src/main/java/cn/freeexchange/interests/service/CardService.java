package cn.freeexchange.interests.service;

import java.util.List;

import cn.freeexchange.interests.dto.CardDto;
import cn.freeexchange.interests.dto.CardOverviewDto;

public interface CardService {

	
	public List<CardDto> queryCards(Long partner,Long openId);
	
	public CardOverviewDto cardOverview(Long partner,Long openId);
}
