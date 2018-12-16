package cn.freeexchange.interests.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.freeexchange.interests.domain.Coupon;
import cn.freeexchange.interests.domain.GenerationRule;
import cn.freeexchange.interests.domain.Interests;
import cn.freeexchange.interests.domain.rpt.CouponRpt;
import cn.freeexchange.interests.dto.CouponDto;
import cn.freeexchange.interests.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
	
	@Autowired
	private CouponRpt couponRpt ;

	@Override
	@Transactional
	public Coupon makeCoupon(Long openId, Interests interests, GenerationRule gr, Long orderAmount, String refType,
			Long refValue) {
		Coupon coupon = 
				Coupon.makeCoupon(openId, interests, gr, orderAmount, refType, refValue);
		couponRpt.save(coupon);
		return coupon;
	}

	@Override
	public Map<String,List<CouponDto>> queryCoupons(Long partner, Long openId) {
		Map<String,List<CouponDto>> ret = new HashMap<>();
		List<Coupon> coupons = couponRpt.queryCoupons(partner, openId);
		for (Coupon coupon : coupons) {
			CouponDto couponDto = coupon.makeDto();
			String status = coupon.getStatus();
			if(ret.get(status) == null) {
				List<CouponDto> values = new ArrayList<>();
				values.add(couponDto);
				ret.put(status, values);
			} else {
				ret.get(status).add(couponDto);
			} 
		}
		return ret;
	}
	
	
	
	

}
