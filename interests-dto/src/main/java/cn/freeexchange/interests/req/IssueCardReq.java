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
public class IssueCardReq {
	
	private Long openId;
	
	private String scence;
	
	private String scenario;
	
	private BigDecimal amount;
	
	public void verify() {
		if(openId == null) {
			throw new BusinessException("card00", "openId");
		}
		if(StringUtils.isBlank(scence)) {
			throw new BusinessException("card00", "scence");
		}
		if(StringUtils.isBlank(scenario)) {
			throw new BusinessException("card00", "scence");
		}
		if(amount == null) {
			throw new BusinessException("card00", "amount");
		}
		
		
	}
}
