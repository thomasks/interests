package cn.freeexchange.interests.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardOverviewDto {
	
	private Long cardNum;
	
	private Long couponNum;
	
	protected CardOverviewDto() {
		
	}
	
	
	protected CardOverviewDto(Long cardNum,Long couponNum) {
		this.cardNum = cardNum;
		this.couponNum = couponNum;
	}
	
	public static CardOverviewDto makeCardOverviewDto(Long cardNum,Long couponNum) {
		return new CardOverviewDto(cardNum, couponNum);
	}
	

}