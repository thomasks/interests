DROP TABLE IF EXISTS `tb_interests`;
CREATE TABLE `tb_interests` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `partner` bigint(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `logic_delete` bit(1) DEFAULT 0,
  `update_time` datetime NOT NULL,
  `scene` varchar(128) DEFAULT NULL,
  `interests_name` varchar(255) DEFAULT NULL,
  `status` varchar(128) DEFAULT NULL,
  `hold_enable_date` datetime NOT NULL,
  `hold_expire_date` datetime NOT NULL,
  `writeoff_enable_date` datetime NOT NULL,
  `writeoff_expire_date` datetime NOT NULL,
  `issuer_id` bigint(64) DEFAULT NULL,
  `issuer_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;





DROP TABLE IF EXISTS `tb_generation_rule`;
CREATE TABLE `tb_generation_rule` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `interests_id` bigint(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `logic_delete` bit(1) DEFAULT 0,
  `update_time` datetime NOT NULL,
  `scenario` varchar(128) DEFAULT NULL,
  `media_type` varchar(255) DEFAULT NULL,
  `rule_type` varchar(128) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
ALTER TABLE `tb_generation_rule` ADD INDEX `IDX_INTERESTS_SCENARIO` (`interests_id`, `scenario`) ;

DROP TABLE IF EXISTS `tb_generation_property`;
CREATE TABLE `tb_generation_property` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT,
  `generate_rule_id` bigint(64) NOT NULL,
  `create_time` datetime NOT NULL,
  `logic_delete` bit(1) DEFAULT 0,
  `update_time` datetime NOT NULL,
  `rule_key` varchar(255) DEFAULT NULL,
  `rule_value` varchar(128) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;
ALTER TABLE `tb_generation_property` ADD INDEX `IDX_GENERATE_RULE` (`generate_rule_id`) ;
