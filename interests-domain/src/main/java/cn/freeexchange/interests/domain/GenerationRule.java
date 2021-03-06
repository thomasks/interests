package cn.freeexchange.interests.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//权益领取策略
@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_generation_rule", schema = "interests")
public class GenerationRule {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	//权益ID
	@Column(name = "interests_id")
    private Long interestsId;
    
    //适用场景
	@Column(name = "scenario")
    private String scenario;
    
    //生成介质类型
    @Column(name = "media_type")
    private String mediaType;
    
    //
    @Column(name = "rule_type")
    private String ruleType;
    
    @Column(name = "create_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    @Column(name = "logic_delete")
    private Boolean logicDelete = false;

    @Column(name = "memo")
    private String memo; 
    
    @OneToMany
    @JoinColumn(name="generate_rule_id")
    private List<GenerationProperty> geneationProperties;
    
    
    public Map<String,String> ruleProps() {
    	Map<String, String> ruleProps = geneationProperties.stream().collect(Collectors.toMap(GenerationProperty::getRuleKey,
    			GenerationProperty::getRuleValue));
    	return ruleProps;
    }
}
