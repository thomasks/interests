package cn.freeexchange.interests.service;

import cn.freeexchange.interests.domain.InterestsMeta;

public interface GenerationService {
	
	InterestsMeta genearation(Long interestsId,Long openId,String scenario,Long amount);
	
	
}
