package cn.freeexchange.interests.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "tb_generation_property", schema = "interests")
public class GenerationProperty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "generate_rule_id")
    private Long generateRuleId;
    
    @Column(name = "rule_key")
    private String ruleKey;
    
    @Column(name = "rule_value")
    private String ruleValue;

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
	
	
}
