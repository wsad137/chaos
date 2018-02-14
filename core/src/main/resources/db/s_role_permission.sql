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

 Date: 11/02/2018 09:02:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `s_role_permission`;
CREATE TABLE `s_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rid` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限关联表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
