/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : db_findit

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 22/10/2020 08:03:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for f_int_user_project
-- ----------------------------
DROP TABLE IF EXISTS `f_int_user_project`;
CREATE TABLE `f_int_user_project`  (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fuser_id` bigint(20) NOT NULL COMMENT '用户id',
  `fproject_id` bigint(20) NOT NULL COMMENT '项目id',
  `flocal_path` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本机地址',
  `fupdate_time` timestamp(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
