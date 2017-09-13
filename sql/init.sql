create table `Industry` (
  `id` int primary key auto_increment,
  `name` varchar(200) not null default "",
  `platform` varchar(200) not null default "",
  `url` varchar(5000) not null default "",
  `urlMd5` varchar(200) not null default "",
  unique key `un_ix_url_md5` (`urlMd5`)
) default charset 'utf8' ENGINE='innodb';