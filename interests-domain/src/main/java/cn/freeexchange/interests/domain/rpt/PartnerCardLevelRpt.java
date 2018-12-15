package cn.freeexchange.interests.domain.rpt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.freeexchange.interests.domain.PartnerCardLevel;

public interface PartnerCardLevelRpt extends JpaRepository<PartnerCardLevel, Long>, JpaSpecificationExecutor<PartnerCardLevel>{
	
	@Query("from PartnerCardLevel where partner=?1 and cardType=?2 and minValue <= ?3 and maxValue>= ?3")
	PartnerCardLevel findPartnerCardLevelByAmount(Long partner,String cardType,Long interestsAmount);
}
