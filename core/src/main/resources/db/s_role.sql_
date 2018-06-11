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

 Date: 05/02/2018 14:58:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_role
-- ----------------------------
DROP TABLE IF EXISTS `s_role`;
CREATE TABLE `s_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `status` smallint(1) NULL DEFAULT 0 COMMENT '角色状态 0：正常 -1：冻结',
  `mark` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_role
-- ----------------------------
INSERT INTO `s_role` VALUES (1, '超级管理员', 1, 'admin_super');

SET FOREIGN_KEY_CHECKS = 1;
