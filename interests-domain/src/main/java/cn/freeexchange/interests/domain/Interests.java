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

@Getter
@Setter
@ToString
@Entity
@Table(name = "tb_interests", schema = "interests")
public class Interests {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "partner")
	private Long partner;
	
	@Column(name = "scene")
	private String scene;
	
	// 权益名称
	@Column(name = "interests_name")
	private String interestsName;
	
    //权益领取开始日期
	@Column(name = "hold_enable_date")
    private Date holdEnableDate;
    
    // 权益领取结束日期
	@Column(name = "hold_expire_date")
    private Date holdExpireDate;
    
    //权益核销开始日期
	@Column(name = "writeoff_enable_date")
    private Date writeoffEnableDate;

    // 权益核销结束日期
	@Column(name = "writeoff_expire_date")
    private Date writeoffExpireDate;
    
    // 权益状态：INIT - 初始化， ENABLE - 可使用， DISABLED - 已失效
	@Column(name = "status")
    private String status;
    
	// 权益发布方ID
	@Column(name = "issuer_id")
    private Long issuerId;

    // 权益发布方名称
	@Column(name = "issuer_name")
    private String issuerName;
    
	@Column(name = "create_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @Column(name = "update_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime = new Date();

    @Column(name = "logic_delete")
    private Boolean logicDelete = false;
    
}
