package cn.freeexchange.interests.service;

import java.util.List;
import java.util.Map;

import cn.freeexchange.interests.domain.Coupon;
import cn.freeexchange.interests.domain.GenerationRule;
import cn.freeexchange.interests.domain.Interests;
import cn.freeexchange.interests.dto.CouponDto;

public interface CouponService {
	
	public Coupon makeCoupon(Long openId,Interests interests,GenerationRule gr,Long orderAmount, String refType, Long refValue);
	
	public Map<String,List<CouponDto>> queryCoupons(Long partner,Long openId);
	
	

}
