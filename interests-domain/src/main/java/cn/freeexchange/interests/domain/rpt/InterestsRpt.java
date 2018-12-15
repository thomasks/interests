package cn.freeexchange.interests.domain.rpt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.freeexchange.interests.domain.Interests;

public interface InterestsRpt extends JpaRepository<Interests, Long>, JpaSpecificationExecutor<Interests>{
	
	@Query("from Interests where partner=?1 and scene=?2")
	//DEPOSITE_DISCOUNT_CAR
	Interests findInterestsByPartnerAndScene(Long partner,String scene);
}
