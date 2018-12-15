package cn.freeexchange.interests.ctrl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.freeexchange.common.base.ApiResponse;
import cn.freeexchange.common.base.exception.BusinessException;
import cn.freeexchange.common.base.identity.IdentityDto;
import cn.freeexchange.common.base.req.RequestDTO;
import cn.freeexchange.common.base.service.TokenService;
import cn.freeexchange.interests.dto.CardDto;
import cn.freeexchange.interests.dto.HoldInterestsResp;
import cn.freeexchange.interests.req.IssueCardReq;
import cn.freeexchange.interests.service.CardService;
import cn.freeexchange.interests.service.InterestsService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/interests/api")
@Slf4j
public class InterestsCtrl {
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private CardService cardService;
	
	@Autowired
	private InterestsService interestsService;
	
	@RequestMapping(value = "/issueCard", method = {RequestMethod.POST,RequestMethod.GET})
	public ApiResponse<HoldInterestsResp> issueCard(HttpServletRequest request,@RequestBody IssueCardReq issueCardReq) {
		log.info("@@account overview arrived...");
		String partner = request.getHeader(RequestDTO.HEADER_PARTNER_ID);
		if(StringUtils.isBlank(partner)) {
			return ApiResponse.failure("合作商户号不能为空");
		}
		try {
			issueCardReq.verify();
			Long openId = issueCardReq.getOpenId();
			String scence = issueCardReq.getScence();
			String scenario = issueCardReq.getScenario();
			BigDecimal amount = issueCardReq.getAmount();
			Map<String,Object> extParams = new HashMap<>();
			extParams.put("scenario", scenario);
			extParams.put("amount", amount);
			HoldInterestsResp holdInterests = interestsService.holdInterests(Long.parseLong(partner), openId, scence, extParams);
			return ApiResponse.success(holdInterests);
		} catch (BusinessException e) {
			log.error("@@issueCard meet error.",e);
			return ApiResponse.failure(e.getBusinessCode(), e.getMessage());
		} catch (Throwable t) {
			log.error("@@issueCard meet error.",t);
			return ApiResponse.failure(ApiResponse.CODE_ERROR,ApiResponse.MSG_ERROR);
		}
		
	}
	
	
	@RequestMapping(value = "/cardList", method = {RequestMethod.POST,RequestMethod.GET})
	public ApiResponse<CardDto> cardList(@RequestHeader(value="token",defaultValue="") String token) {
		log.info("@@account overview arrived...");
		if(StringUtils.isBlank(token)) {
			return ApiResponse.failure("用户未登入");
		}
		try {
			IdentityDto identityDto = tokenService.getTokenIdentity(token);
			List<CardDto> cards = cardService.queryCards(identityDto.getPartner(), identityDto.getOpenId());
			return ApiResponse.success(cards);
		} catch (BusinessException e) {
			log.error("@@issueCard meet error.",e);
			return ApiResponse.failure(e.getBusinessCode(), e.getMessage());
		} catch (Throwable t) {
			log.error("@@issueCard meet error.",t);
			return ApiResponse.failure(ApiResponse.CODE_ERROR,ApiResponse.MSG_ERROR);
		}
		
	}
	
	
	

}
