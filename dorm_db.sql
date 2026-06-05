/*
 Navicat Premium Dump SQL

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80034 (8.0.34)
 Source Host           : localhost:3306
 Source Schema         : dorm_db

 Target Server Type    : MySQL
 Target Server Version : 80034 (8.0.34)
 File Encoding         : 65001

 Date: 04/06/2026 08:59:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` tinyint NOT NULL DEFAULT 0 COMMENT '0普通 1超级管理员',
  `deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`admin_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', '$2a$10$adDfqa8C8R6nrxP49b4zU.XHd6eV7ZNF2TzGwQT9BnnDR7kEqxsG', 1, 0);
INSERT INTO `admin` VALUES (2, 'aa', '$2a$10$adDfqa8C8R6nrxP49b4zU.XHd6eV7ZNF2TzGwQT9BnnDR7kEqxsG', 1, 0);
INSERT INTO `admin` VALUES (4, 'bb', '$2a$10$jld2frl6UDsqvC5GyySozOge.4sBLl5fKvqmERVBMxVvgAVxvEIK2', 0, 0);

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `building_id` int NOT NULL AUTO_INCREMENT,
  `building_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `floor_count` int NOT NULL DEFAULT 1,
  `room_count` int NOT NULL DEFAULT 0,
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`building_id`) USING BTREE,
  UNIQUE INDEX `building_name`(`building_name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES (1, '1号楼', 7, 140, '2026-06-03 09:20:57', 0);
INSERT INTO `building` VALUES (3, '3号楼', 1, 100, '2026-06-03 09:44:44', 0);
INSERT INTO `building` VALUES (4, '2号楼', 2, 80, '2026-06-03 23:07:17', 1);

-- ----------------------------
-- Table structure for check_in
-- ----------------------------
DROP TABLE IF EXISTS `check_in`;
CREATE TABLE `check_in`  (
  `record_id` int NOT NULL AUTO_INCREMENT,
  `student_id` int NOT NULL,
  `dorm_id` int NOT NULL,
  `check_in_date` date NULL DEFAULT NULL,
  `check_out_date` date NULL DEFAULT NULL,
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0在住 1已退宿',
  `deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`record_id`) USING BTREE,
  INDEX `idx_student_id`(`student_id` ASC) USING BTREE,
  INDEX `idx_dorm_id`(`dorm_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_in
-- ----------------------------
INSERT INTO `check_in` VALUES (1, 1, 2, '2026-06-03', NULL, 0, 0);
INSERT INTO `check_in` VALUES (2, 1, 2, '2026-06-03', NULL, 0, 0);
INSERT INTO `check_in` VALUES (3, 1, 4, '2026-06-03', NULL, 0, 0);
INSERT INTO `check_in` VALUES (4, 4, 4, '2026-06-03', '2026-06-04', 1, 0);
INSERT INTO `check_in` VALUES (5, 3, 5, '2026-06-03', '2026-06-03', 1, 0);
INSERT INTO `check_in` VALUES (6, 4, 4, '2026-06-04', '2026-06-04', 1, 0);
INSERT INTO `check_in` VALUES (7, 4, 4, '2026-06-04', NULL, 0, 0);

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `dorm_id` int NOT NULL AUTO_INCREMENT,
  `building_id` int NOT NULL,
  `dorm_number` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `capacity` int NOT NULL DEFAULT 4,
  `current_count` int NOT NULL DEFAULT 0,
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0空闲 1满员 2维修中',
  `deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`dorm_id`) USING BTREE,
  UNIQUE INDEX `uk_building_dorm`(`building_id` ASC, `dorm_number` ASC) USING BTREE,
  INDEX `idx_building_id`(`building_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES (2, 1, '102', 4, 4, 1, 0);
INSERT INTO `dormitory` VALUES (4, 3, '309', 4, 2, 0, 0);
INSERT INTO `dormitory` VALUES (5, 1, '101', 1, 0, 0, 0);
INSERT INTO `dormitory` VALUES (7, 1, '109', 4, 0, 0, 0);

-- ----------------------------
-- Table structure for repair
-- ----------------------------
DROP TABLE IF EXISTS `repair`;
CREATE TABLE `repair`  (
  `repair_id` int NOT NULL AUTO_INCREMENT,
  `dorm_id` int NOT NULL,
  `student_id` int NOT NULL,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0待处理 1处理中 2已完成',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `finish_time` datetime NULL DEFAULT NULL,
  `deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`repair_id`) USING BTREE,
  INDEX `idx_dorm_id`(`dorm_id` ASC) USING BTREE,
  INDEX `idx_status`(`status` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair
-- ----------------------------
INSERT INTO `repair` VALUES (1, 1, 1, '太坏了', 2, '2026-06-03 21:23:05', '2026-06-03 21:23:10', 1);
INSERT INTO `repair` VALUES (2, 1, 1, '台海', 0, '2026-06-03 21:23:19', NULL, 1);
INSERT INTO `repair` VALUES (3, 2, 1, 'a\'a\'a', 2, '2026-06-03 23:12:24', '2026-06-03 23:12:42', 1);
INSERT INTO `repair` VALUES (4, 4, 12, '1111', 0, '2026-06-03 23:12:51', NULL, 0);
INSERT INTO `repair` VALUES (5, 4, 4, '0000000000000', 0, '2026-06-04 08:38:04', NULL, 0);
INSERT INTO `repair` VALUES (6, 4, 1, '咩咩咩咩', 0, '2026-06-04 08:46:27', NULL, 0);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` int NOT NULL AUTO_INCREMENT,
  `student_no` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` tinyint NOT NULL DEFAULT 0 COMMENT '0女 1男',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint NOT NULL DEFAULT 0 COMMENT '0未入住 1已入住 2退宿',
  `deleted` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`student_id`) USING BTREE,
  UNIQUE INDEX `student_no`(`student_no` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES (1, '2400320203', 'aa', 0, '12345678910', '123456', 1, 0);
INSERT INTO `student` VALUES (2, '001', '001', 1, '12345678910', '123456', 0, 0);
INSERT INTO `student` VALUES (3, '2400320219', 'ab', 1, '12345678910', '123456', 2, 0);
INSERT INTO `student` VALUES (4, '002', 'ttt', 0, '18781956086', '$2a$10$QBNbnkvvAYkfEOyzUjeQfuGn4bSK1YXZQqVwtkbYbDyC4lMAoQXPC', 1, 0);

SET FOREIGN_KEY_CHECKS = 1;
