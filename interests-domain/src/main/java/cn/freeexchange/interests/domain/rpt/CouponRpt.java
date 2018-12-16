package cn.freeexchange.interests.domain.rpt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.freeexchange.interests.domain.Coupon;

public interface CouponRpt extends JpaRepository<Coupon, Long>, JpaSpecificationExecutor<Coupon>{
	
	
	@Query("from Coupon where partner=?1 and openId=?2")
	List<Coupon> queryCoupons(Long partner,Long openId);
}
