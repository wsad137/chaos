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

 Date: 05/02/2018 14:57:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for logs_
-- ----------------------------
DROP TABLE IF EXISTS `logs_`;
CREATE TABLE `logs_`  (
  `user_id` bigint(20) UNSIGNED NULL DEFAULT 0 COMMENT '用户id',
  `targetTable` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '目标表',
  `type` int(11) UNSIGNED NULL DEFAULT 0 COMMENT '类型1添加，2删除，3修改',
  `description` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志描述',
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'ip地址',
  `ct` bigint(15) NULL DEFAULT NULL COMMENT '操作时间'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '日志记录表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
