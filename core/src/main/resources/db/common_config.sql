/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_java
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : core

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-03-15 16:49:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for common_config
-- ----------------------------
DROP TABLE IF EXISTS `common_config`;
CREATE TABLE `common_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_mark` varchar(255) CHARACTER SET utf8mb4 NOT NULL COMMENT 'key唯一性',
  `value` text COMMENT '内容建议使用json格式',
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '详细说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8;
