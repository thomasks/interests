package cn.freeexchange.interests.service;

import java.util.Map;

import cn.freeexchange.interests.dto.HoldInterestsResp;

public interface InterestsService {
	
	//领取权益
	public HoldInterestsResp holdInterests(Long partner,Long openId,String scence,Map<String, Object> extParams);
	
	//核销券
	public void writeoffInterests(Long couponId);
	
	//领取并核销
	public HoldInterestsResp holdAndWriteoffInterests(Long partner,Long openId,String scence,Map<String,Object> extParams);
	
}
