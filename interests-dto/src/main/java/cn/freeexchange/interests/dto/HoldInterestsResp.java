package cn.freeexchange.interests.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HoldInterestsResp {
	
	//介质类型
	private String mediaType;
	
	//实体标识
	private String generationId;
	
	//权益金额
	private Long interestsAmount;
	
	//权益类型
	private String interestsType;
	
}
