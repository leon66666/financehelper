create table `Industry` (
  `id` int primary key auto_increment,
  `name` varchar(200) not null default "",
  `platform` varchar(200) not null default "",
  `url` varchar(5000) not null default "",
  `urlMd5` varchar(200) not null default "",
  unique key `un_ix_url_md5` (`urlMd5`)
) default charset 'utf8' ENGINE='innodb';


CREATE TABLE `industry_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `industry_name` varchar(200) NOT NULL DEFAULT '',
  `industry_id` int(11) DEFAULT NULL,
  `rise` decimal(6,2) DEFAULT NULL COMMENT '涨跌幅',
  `main` int(11) DEFAULT NULL COMMENT '主力流入',
  `super` int(11) DEFAULT NULL COMMENT '超大单流入',
  `big` int(11) DEFAULT NULL COMMENT '大单流入',
  `medium` int(11) DEFAULT NULL COMMENT '中单流入',
  `small` int(11) DEFAULT NULL COMMENT '小单流入',
  `total` int(11) DEFAULT NULL COMMENT '总流入',
  `date` int(11) DEFAULT NULL COMMENT '日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_industry_id_date` (`industry_id`,`date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=421 DEFAULT CHARSET=utf8;