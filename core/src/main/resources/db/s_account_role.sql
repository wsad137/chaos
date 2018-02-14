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

 Date: 11/02/2018 09:03:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_account_role
-- ----------------------------
DROP TABLE IF EXISTS `s_account_role`;
CREATE TABLE `s_account_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `a_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账户ID',
  `r_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表\r\n\r\n\r\n\r\n' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
