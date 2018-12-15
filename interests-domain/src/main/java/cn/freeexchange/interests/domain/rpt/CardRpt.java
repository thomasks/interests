package cn.freeexchange.interests.domain.rpt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import cn.freeexchange.interests.domain.Card;

public interface CardRpt extends JpaRepository<Card, Long>, JpaSpecificationExecutor<Card>{
	
	
	@Query("from Card where partner=?1 and openId=?2")
	List<Card> queryCards(Long partner,Long openId);
}
