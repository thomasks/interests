package cn.freeexchange.interests.api;

import lombok.Getter;

@Getter
public enum InterestsMetaType {
	
	CARD(1,"CARD","折扣卡"),
	COUPON(2,"COUPON","折扣卡");
	
	private Integer id;
	private String code;
    private String name;
    
    private InterestsMetaType(Integer id,String code,String name) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
}
