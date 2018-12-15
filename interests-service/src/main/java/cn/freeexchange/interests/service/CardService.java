package cn.freeexchange.interests.service;

import java.util.List;

import cn.freeexchange.interests.dto.CardDto;

public interface CardService {

	
	public List<CardDto> queryCards(Long partner,Long openId);
}
