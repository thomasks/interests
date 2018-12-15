package cn.freeexchange.interests.domain.rpt;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.freeexchange.interests.domain.GenerationRule;
import cn.freeexchange.interests.domain.Interests;

public interface GenerationRuleRpt extends JpaRepository<GenerationRule, Long>, JpaSpecificationExecutor<GenerationRule>{
	
	@Query("from GenerationRule where interestsId=?1 and scenario=?2")
	//DEPOSITE_DISCOUNT_CAR
	GenerationRule findGenerationRuleByInterestsAndScenario(Long interestsId,String scenario);
}
