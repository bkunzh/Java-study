/*
Navicat MySQL Data Transfer

Source Server         : localhost-mysql
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2020-01-14 10:32:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `employees`
-- ----------------------------
DROP TABLE IF EXISTS `employees`;
CREATE TABLE `employees` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `first` varchar(30) DEFAULT NULL,
  `last` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employees
-- ----------------------------
INSERT INTO `employees` VALUES ('100', '28', 'aa', 'a');
INSERT INTO `employees` VALUES ('101', '29', 'bb', 'b');
INSERT INTO `employees` VALUES ('106', '22', 'cc', 'c');
INSERT INTO `employees` VALUES ('107', '21', 'dd', 'd');
INSERT INTO `employees` VALUES ('108', '25', 'ee', 'e');
INSERT INTO `employees` VALUES ('109', '22', 'ff', 'f');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `sex` char(4) DEFAULT NULL,
  `grade` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('4', 'fengbei', 'm', '2008811237');
INSERT INTO `student` VALUES ('5', 'lisong', 'm', '2008811221');
INSERT INTO `student` VALUES ('7', 'fengbei', 'm', '2008811237');
INSERT INTO `student` VALUES ('8', 'lisong', 'm', '2008811221');
