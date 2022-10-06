/*
Navicat MySQL Data Transfer

Source Server         : Windows
Source Server Version : 80029
Source Host           : localhost:3306
Source Database       : zclass

Target Server Type    : MYSQL
Target Server Version : 80029
File Encoding         : 65001

Date: 2022-10-05 18:57:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `cou_on_id` int NOT NULL AUTO_INCREMENT,
  `cou_on_name` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `tea_userid` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `cou_grade` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `cou_class` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`cou_on_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for cou_stu
-- ----------------------------
DROP TABLE IF EXISTS `cou_stu`;
CREATE TABLE `cou_stu` (
  `cou_stu_id` int NOT NULL AUTO_INCREMENT,
  `cou_on_id` int DEFAULT NULL,
  `stu_userid` varchar(45) COLLATE utf8mb4_bin DEFAULT '',
  PRIMARY KEY (`cou_stu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` varchar(45) COLLATE utf8mb4_bin NOT NULL,
  `username` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `sex` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL,
  `password` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `school` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `profess` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  `phonenumber` varchar(45) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
