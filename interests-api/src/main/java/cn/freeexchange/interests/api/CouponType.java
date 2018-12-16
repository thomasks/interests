package cn.freeexchange.interests.api;

import lombok.Getter;

@Getter
public enum CouponType {
	
	DISCOUNT(1,"DISCOUNT","折扣券"),
	DEDUCT(2,"DEDUCT","满减券");
	
	private Integer id;
	private String code;
    private String name;
    
    private CouponType(Integer id,String code,String name) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
}
