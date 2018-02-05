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

 Date: 05/02/2018 14:57:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for s_account
-- ----------------------------
DROP TABLE IF EXISTS `s_account`;
CREATE TABLE `s_account`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `nickname` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录帐号',
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `status` smallint(1) NULL DEFAULT 1 COMMENT '1:有效，0:禁止登录',
  `u_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户id 一用户id 可以对应多个账户',
  `token` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '每次登录后刷新',
  `l_t` bigint(15) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `ut` bigint(15) NULL DEFAULT NULL COMMENT '更新时间',
  `ct` bigint(15) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of s_account
-- ----------------------------
INSERT INTO `s_account` VALUES ('1', 'admin', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL, '49292b75cfa6117539b4b784bfbfef60', 1517226661095, 1517226657507, NULL);

SET FOREIGN_KEY_CHECKS = 1;
