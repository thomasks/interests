package cn.freeexchange.interests.api;

import lombok.Getter;

@Getter
public enum CouponStatus {
	
	HOLD(1,"HOLD","领取"),
	WRITEOFF(2,"WRITEOFF","核销"),
	EXPIRY(2,"EXPIRY","过期");
	
	private Integer id;
	private String code;
    private String name;
    
    private CouponStatus(Integer id,String code,String name) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
}
