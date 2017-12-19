/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_java
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : core

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-03-15 16:50:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for common_exceptions
-- ----------------------------
DROP TABLE IF EXISTS `common_exceptions`;
CREATE TABLE `common_exceptions` (
  `ip` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `title` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '异常标题',
  `context` text COMMENT '异常内容',
  `device` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '设备信息',
  `c_date` bigint(13) DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常信息收集表';
