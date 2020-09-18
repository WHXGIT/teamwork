/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : kingdee

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 29/06/2020 00:17:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_int_google_conf
-- ----------------------------
DROP TABLE IF EXISTS `t_int_google_conf`;
CREATE TABLE `t_int_google_conf`  (
  `fid` bigint(20) NOT NULL COMMENT '主键',
  `fjson_content` varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'json文件',
  `fscope_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'scope',
  `fvpn_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'vpn host',
  `fvpn_port` int(5) NOT NULL COMMENT 'vpn port',
  `fprotocol` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '协议',
  `fglsy_bucket` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '术语表所属分区',
  `fglsy_fn` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '术语表文件名（唯一）',
  `fproj_num` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'project number',
  PRIMARY KEY (`fid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_int_google_conf
-- ----------------------------
INSERT INTO `t_int_google_conf` VALUES (10000, '{\r\n  \"type\": \"service_account\",\r\n  \"project_id\": \"translationadvanced01\",\r\n  \"private_key_id\": \"2ac27d9bc2a95ef5a1efa1803ee9a8b32bb06a0e\",\r\n  \"private_key\": \"-----BEGIN PRIVATE KEY-----\\nMIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC7Wmwv9Wz/BHxZ\\nY56XwNZYcH/v9jl/Lu7v4lcWXspiClLn7SJ8stAsZX4DuuVlUxZA3WCJwBh9Vf69\\nVkJQ8CG7Dxechf7gnoUwkZ0Mgft1AF/U3T+JBlFfIigow1lBK5uqfRU3yjdFW25L\\n9HOILWRoQ0EjycBRs1GPBBmQ2JRKZ+UMLIT1dTo3ABV8c3fDSqgiv3lJhFHvNk9b\\n0h76eJPIJXv43kMaVtoreGWfFxRV6vpBrg/oM7ZTZ4Crdc3pbP2lp1WjfO7woLSq\\ng3cFHxB+OCIhkQf+5ULf06gNGXljbnbt6ybQ6lPZgKqxHvg13SwQ48x2MlEm2KqK\\nM24pT7q1AgMBAAECggEAEZTMrjtzvQKowKKukUMRWj9EYjpS7vJyjaRJ09mVWFnr\\nqcQBBn6pNmFXz/XL7RQ89F1R39tc8PYO76mWk43VPH6QEqooXt/BU+t2N2RDOpmM\\nZKdWfP9Qx4KnDhw21fzHoNoYEaIySZno4cvQw6iu0XHuPPODzqbrMmaKF6ObVtf7\\nzWgbR0B8js3HwvPsq6ZhVinmZ6mSa8qboXD7h6+AyHNlLQvJcwrsIb0bJshxfaWm\\nPf532NkYnFe9Hh8z0EOpP0Z3JaPWtIJVa4NYRnyjbBl1v/y6eOCUev5QnA9bqyi7\\noefI54hbTtebtr1NF5Nc8DYYcjmXuQbW6dMNr+YEwQKBgQDvIWjf+SMydmyyaS/T\\nmZQHDGSPUbtUsXrKwAeUqJA+bkq26pPuio9gzDipOYVgukdHv+fBAUIUmrzgPcLM\\nGHYmBvgcb7/M6bpb44Y37UaRJCPsJwXyCcv9vJU+vkncrnG1zOUCrW2FAIWKe5VH\\nkv7/yKeWRNlm2oL2s3len5Kf3QKBgQDIkfAn4nBXiYg1LEC+PRE/6NkZsIW31d/X\\nBqlIRfWiXG2PAcMxD84rK79oURsjqVQc6vnDu7Jh+UFrJyvkSLPYPzTTTBcyfxKO\\nezKz/GrxCx6KVgRyt3LK8QJ8hgIoTJKTOwXbkj5Vf9z3JsKO4fQ2n4YHZEvDehmY\\n1mUp+PnEuQKBgQChQwKaqESYL0y2NEFQjW1KI41dKV0PjHCmckGqetJrh61TFXaV\\nk1OFj7NWS7gqtvSC6mO7wMFza7ab8op/YK+sjV6crq/F3IkUlqdY5+aC0sB27QoR\\nY5Hxl09+dI0TCYEdfQOjfpkw7F0iOp09G2Bj1klOCTgjKJDRFc9vhGU48QKBgQDA\\nUbd+9xCTz2uM2O8Hu1Sj/GjXyjyd5vVooJmHlJWQ5qGjA6SfkbE2Eg/KPiMQ0de2\\nltWBiqPkb/X7gicDEXdpfknv5cFEiTZI6vbWQM5mbSwOp6ZCPYdT8z9YGcNN0wal\\nKG5YeWVN5HoDfuYFzcG28sW4psJDIPwUYlfEpFkS+QKBgFPN4pg64fH+nAo5I50M\\ni8yLiPBpYV7X1DShnM9gGkpIFdspdbauPbFOF1NKUip3iNN+E/F7kds+O5GXyqGb\\nz5cpBFCd+5YiMt3vFsgW1mLQpg2fewYDz2vxELHOR5ExycFd1pOCm2b1Yf5gLBaq\\nlvFrJn1ldz61qyXXpRVVIc9K\\n-----END PRIVATE KEY-----\\n\",\r\n  \"client_email\": \"google-trans@translationadvanced01.iam.gserviceaccount.com\",\r\n  \"client_id\": \"116101300796684973266\",\r\n  \"auth_uri\": \"https://accounts.google.com/o/oauth2/auth\",\r\n  \"token_uri\": \"https://oauth2.googleapis.com/token\",\r\n  \"auth_provider_x509_cert_url\": \"https://www.googleapis.com/oauth2/v1/certs\",\r\n  \"client_x509_cert_url\":\"https://www.googleapis.com/robot/v1/metadata/x509/google-trans%40translationadvanced01.iam.gserviceaccount.com\"\r\n}', 'https://www.googleapis.com/auth/cloud-platform', '127.0.0.1', 1080, 'HTTP', 'glossary_bucket01', 'whx_test.csv', '264423809822');

SET FOREIGN_KEY_CHECKS = 1;
