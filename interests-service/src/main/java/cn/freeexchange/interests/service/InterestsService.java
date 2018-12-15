package cn.freeexchange.interests.service;

import java.util.Map;

import cn.freeexchange.interests.dto.HoldInterestsResp;

public interface InterestsService {
	
	
	public HoldInterestsResp holdInterests(Long partner,Long openId,String scence);
	
	
	public HoldInterestsResp writeoffInterests(Long partner,Long openId,String mediaType,String couponId);
	
	
	public HoldInterestsResp holdAndWriteoffInterests(Long partner,Long openId,String scence,Map<String,Object> extParams);
	
}
