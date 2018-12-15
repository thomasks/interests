package cn.freeexchange.interests.api;

import lombok.Getter;

@Getter
public enum InterestsCardType {
	
	DEPOSITE_DISCOUNT_CARD(1,"DEPOSITE_DISCOUNT_CARD","折扣卡"),
	STORED_VALUE_CARD(2,"STORED_VALUE_CARD","折扣卡");
	
	private Integer id;
	private String code;
    private String name;
    
    private InterestsCardType(Integer id,String code,String name) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
}
