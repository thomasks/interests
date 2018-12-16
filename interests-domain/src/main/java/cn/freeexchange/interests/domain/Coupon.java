package cn.freeexchange.interests.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.RandomStringUtils;

import cn.freeexchange.common.base.exception.BusinessException;
import cn.freeexchange.interests.api.CouponStatus;
import cn.freeexchange.interests.api.CouponType;
import cn.freeexchange.interests.api.InterestsMetaType;
import cn.freeexchange.interests.dto.CardDto;
import cn.freeexchange.interests.dto.CouponDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_coupon", schema = "interests")
public class Coupon implements InterestsMeta {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(name = "partner")
	private Long partner;
	
	@Column(name = "open_id")
	private Long openId;
	
	
	//CARD INTERESTS
	@Column(name="ref_type")
	private String refType;
	
	//CARDID INTERESTS_ID
	@Column(name="ref_value")
	private Long refValue;
	
	@Column(name="coupon_no")
	private String couponNo;
	
	@Column(name="issuer_id")
	private Long issuerId;
	
	@Column(name="issuer_name")
	private String issuerName;
    
	@Column(name="coupon_name")
	private String couponName;
	
	
	/**
     * 优惠券类型: 1 -- 折扣券 2 -- 满减券 3 -- 凭证券 4 -- 特价券 5 -- 代金券
     * 
     */
	@Column(name="coupon_type")
    private String couponType;
	
	/**
     * 优惠券描述
     */
	@Column(name="description")
    private String description;
    

    /**
     * 优惠券面值
     * 
     */
	@Column(name="original_amt")
    private Long originalAmt;

    /**
     * 优惠金额
     * 
     */
	@Column(name="amount")
	private Long amount;
    
    /**
     * 最高金额（折扣）单位：分。使用折扣最高金额，超过的部分不享受折扣
     */
	@Column(name="max_amt")
    private Long maxAmt;
    
    /**
     * 最低金额（满减）单位：分。使用满减最低金额，低于此金额则该订单不享受满减
     */
	@Column(name="min_amt")
	private Long minAmt;
    
    /**
     * 打折比例
     */
	@Column(name="discount")
	private String discount;
    
    /**
     * 扣减金额 单位：分。满减金额
     */
	@Column(name="deduct_amt")
    private Long deductAmt;
    
    /**
     * 是否可用：1可用，0不可用
     */
	@Column(name="status")
    private String status;
	
	@Column(name = "create_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    @Column(name = "logic_delete")
    private Boolean logicDelete = false;
    
    //生效日期
  	@Column(name = "enable_date")
    private Date enableDate = new Date();
      
    // 失效日期
  	@Column(name = "expire_date")
    private Date expireDate;

	@Override
	public Long intestesValue() {
		return originalAmt;
	}

	@Override
	public Long usageValue() {
		return amount;
	}

	@Override
	public String metaType() {
		return InterestsMetaType.COUPON.getCode();
	}

	@Override
	public Long metaId() {
		return id;
	}

	@Override
	public String interestsType() {
		return couponType;
	}
	
	
	protected Coupon() {
		
	}
	
	
	public void writeoff() {
		Date curDate = new Date();
		if(curDate.before(enableDate) || curDate.after(expireDate)) {
			throw new BusinessException("coupon04");
		}
		this.status = CouponStatus.WRITEOFF.getCode();
	}
	
	public CouponDto makeDto() {
		CouponDto couponDto = CouponDto.makeCouponDto(issuerName, amount, status, enableDate,expireDate,couponNo);
		return couponDto;
	}
	
	public static Coupon makeCoupon(Long openId,Interests interests,GenerationRule gr,Long orderAmount,String refType,Long refValue) {
		return new Coupon(openId, interests, gr, orderAmount, refType, refValue);
	}
	
	protected Coupon(Long openId,Interests interests,GenerationRule gr,Long orderAmount,String refType,Long refValue) {
		this.partner = interests.getPartner();
		this.openId = openId;
		Long calcInterestsAmount = calcInterestsAmount(orderAmount, gr);
		String couponName = couponName(gr);
		this.originalAmt = calcInterestsAmount;
		this.amount = calcInterestsAmount;
		this.couponName = couponName;
		Map<String, String> ruleProps = gr.ruleProps();
		String couponType = ruleProps.get("couponType");
		this.couponType = couponType;
		if(couponType.equalsIgnoreCase(CouponType.DISCOUNT.getCode())) {
			this.discount =  ruleProps.get("discount");
		}
		this.issuerId = interests.getIssuerId();
		this.issuerName = interests.getIssuerName();
		this.refType = refType;
		this.refValue = refValue;
		this.couponNo = RandomStringUtils.random(12, false, true);
		this.status = CouponStatus.HOLD.getCode();
		this.expireDate = interests.getWriteoffExpireDate();
	}
	
	protected String couponName(GenerationRule gr) {
		Map<String, String> ruleProps = gr.ruleProps();
		String couponType = ruleProps.get("couponType");
		//折扣券
		if(couponType.equalsIgnoreCase(CouponType.DISCOUNT.getCode())) {
			return CouponType.DISCOUNT.getName();
		}
		throw new BusinessException("coupon00");
	}
	
	
	protected Long calcInterestsAmount(Long orderAmount,GenerationRule gr) {
		Map<String, String> ruleProps = gr.ruleProps();
		String couponType = ruleProps.get("couponType");
		//折扣券
		if(couponType.equalsIgnoreCase(CouponType.DISCOUNT.getCode())) {
			String discountStr = ruleProps.get("discount");
			long interestsValue = 
					new BigDecimal(orderAmount).multiply(new BigDecimal(discountStr)).longValue();
			return interestsValue;
		}
		throw new BusinessException("coupon00");
	}
}
