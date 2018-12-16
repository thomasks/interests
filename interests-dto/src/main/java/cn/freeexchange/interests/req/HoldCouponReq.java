package cn.freeexchange.interests.req;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import cn.freeexchange.common.base.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HoldCouponReq {
	
	private Long openId;
	
	private String scence;
	
	private String scenario;
	
	private BigDecimal amount;
	
	public void verify() {
		if(openId == null) {
			throw new BusinessException("coupon02", "openId");
		}
		if(StringUtils.isBlank(scence)) {
			throw new BusinessException("coupon02", "scence");
		}
		if(StringUtils.isBlank(scenario)) {
			throw new BusinessException("coupon02", "scence");
		}
		if(amount == null) {
			throw new BusinessException("coupon02", "amount");
		}
		
		
	}
}
