package cn.freeexchange.interests.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardDto {
	
	private String cardNo;
	private String cardName;
	private String issuerName;
	private String cardUrl;
	private String logoUrl;
	
	protected CardDto() {
		
	}

	protected CardDto(String cardNo, String cardName, String issuerName, String cardUrl, String logoUrl) {
		this.cardNo = cardNo;
		this.cardName = cardName;
		this.issuerName = issuerName;
		this.cardUrl = cardUrl;
		this.logoUrl = logoUrl;
	}
	
	
	public static CardDto makeCardDto(String cardNo, String cardName, String issuerName, String cardUrl, String logoUrl) {
		return new CardDto(cardNo, cardName, issuerName, cardUrl, logoUrl);
	}
	
	

}
;