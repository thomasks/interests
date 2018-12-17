package cn.freeexchange.interests.dto;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import cn.freeexchange.common.base.utils.AmountUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponDto {
	
	
	private  transient static final String parttern = "yyyy-MM-dd";
	
	private String couponNo;
	private String validateBeginDate;
	private String validateEndDate;
	private String issuerName;
	private String amount;
	private String state;
	
	protected CouponDto() {
		
	}
	
	
	
	
	public static CouponDto makeCouponDto(String issuerName,Long amount,String status,Date enableDate,Date expiryDate,String couponNo) {
		String validateBeginDate = DateFormatUtils.format(enableDate, parttern);
		String validateEndDate = DateFormatUtils.format(expiryDate, parttern);
		String amt = AmountUtils.toAmount(amount);
		return new CouponDto(validateBeginDate, validateEndDate, issuerName, amt, status,couponNo);
	}


	public CouponDto(String validateBeginDate, String validateEndDate, String issuerName, String amount, String state,String couponNo) {
		this.validateBeginDate = validateBeginDate;
		this.validateEndDate = validateEndDate;
		this.issuerName = issuerName;
		this.amount = amount;
		this.state = state;
		this.couponNo = couponNo;
	}
	
	

}
;