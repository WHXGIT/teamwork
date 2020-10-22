/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : localhost:3306
 Source Schema         : db_test

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 22/10/2020 09:37:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_int_user
-- ----------------------------
DROP TABLE IF EXISTS `t_int_user`;
CREATE TABLE `t_int_user`  (
  `fid` bigint(36) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `fusername` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `fusername_cn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '中文名',
  `fpasswd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1234567' COMMENT '密码',
  `ftype` int(1) NOT NULL DEFAULT 2 COMMENT '用户类型， 0：超级管理员， 1：管理员，2：普通用户 ',
  `fcreate_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `fupdate_time` timestamp(0) NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10008 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_int_user
-- ----------------------------
INSERT INTO `t_int_user` VALUES (10001, 'wanghaoxin', '王浩鑫', '1234567', 2, '2020-08-14 09:37:24', '2020-08-14 10:19:28');
INSERT INTO `t_int_user` VALUES (10002, 'chenpeng', '陈朋', '1234567', 2, '2020-08-14 10:20:10', '2020-08-14 10:20:15');
INSERT INTO `t_int_user` VALUES (10003, 'mahaojie', '马浩杰', '1234567', 2, '2020-08-14 10:20:50', '2020-08-14 10:20:54');
INSERT INTO `t_int_user` VALUES (10004, 'caolifeng', '曹丽峰', '1234567', 2, '2020-08-14 10:21:43', '2020-08-14 10:21:48');
INSERT INTO `t_int_user` VALUES (10005, 'xieyongchen', '谢永晨', '1234567', 2, '2020-08-14 10:22:30', '2020-08-14 10:22:32');
INSERT INTO `t_int_user` VALUES (10006, 'wangning', '王宁', '1234567', 2, '2020-08-14 10:22:53', '2020-08-14 10:22:57');
INSERT INTO `t_int_user` VALUES (10007, 'zhouhuikun', '周会坤', '1234567', 2, '2020-08-14 10:23:45', '2020-08-14 10:23:48');

SET FOREIGN_KEY_CHECKS = 1;
