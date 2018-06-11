/*
 Navicat MySQL Data Transfer

 Source Server         : 127.0.0.1_java
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : chaos-core

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 05/02/2018 14:57:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for region_
-- ----------------------------
DROP TABLE IF EXISTS `region_`;
CREATE TABLE `region_`  (
  `id` smallint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名字',
  `code` smallint(4) NULL DEFAULT NULL COMMENT '区号',
  `parent_id` smallint(4) NULL DEFAULT NULL COMMENT '上级id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '区域表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
