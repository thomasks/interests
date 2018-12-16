package cn.freeexchange.interests.req;

import cn.freeexchange.common.base.exception.BusinessException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WriteoffCouponReq {
	
	private Long couponId;
	
	public void verify() {
		if(couponId == null) {
			throw new BusinessException("coupon03");
		}
	}
}
