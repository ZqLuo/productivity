# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.15)
# Database: work
# Generation Time: 2019-01-05 06:35:58 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table sys_user
# ------------------------------------------------------------

CREATE TABLE `sys_user` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `login_name` varchar(32) DEFAULT NULL COMMENT '登录名',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(256) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户表';



# Dump of table work_account_statement
# ------------------------------------------------------------

CREATE TABLE `work_account_statement` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `account_statement_name` varchar(64) DEFAULT NULL COMMENT '对账单名称',
  `company_name` varchar(64) DEFAULT NULL COMMENT '公司名称',
  `from_date` datetime DEFAULT NULL COMMENT '开始时间',
  `to_date` datetime DEFAULT NULL COMMENT '结束时间',
  `input_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售对账单';

LOCK TABLES `work_account_statement` WRITE;
/*!40000 ALTER TABLE `work_account_statement` DISABLE KEYS */;

INSERT INTO `work_account_statement` (`id`, `account_statement_name`, `company_name`, `from_date`, `to_date`, `input_time`)
VALUES
	(1,NULL,'名称',NULL,NULL,'2019-01-05 00:34:35');

/*!40000 ALTER TABLE `work_account_statement` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table work_account_statement_detail
# ------------------------------------------------------------

CREATE TABLE `work_account_statement_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `sale_date` datetime DEFAULT NULL COMMENT '销售日期',
  `trade_name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `unit` varchar(32) DEFAULT NULL COMMENT '单位',
  `count` int(6) DEFAULT NULL COMMENT '数量',
  `unit_price` double DEFAULT NULL COMMENT '单价',
  `total_price` double DEFAULT NULL COMMENT '总价',
  `remark` varchar(512) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='销售对账单详细';




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
