/*
 Navicat Premium Data Transfer

 Source Server         : example
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 10.10.2.104:3306
 Source Schema         : example

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 11/08/2018 14:18:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `age` int(11) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
