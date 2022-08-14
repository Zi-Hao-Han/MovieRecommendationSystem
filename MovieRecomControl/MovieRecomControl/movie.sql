/*
 Navicat Premium Data Transfer

 Source Server         : 47.105.137.32_3306
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : 47.105.137.32:3306
 Source Schema         : movie

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 04/07/2022 06:10:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ima_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `movie_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `descrip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `timelong` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `shoot` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `language` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `actors` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `director` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `issue` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1526711273653046.jpg', '哥谭 第四季 Gotham', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (2, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1526726844323744.jpg', '游戏之夜', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (3, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1526727108393284.jpg', '环太平洋：雷霆再起', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (4, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1526727155977248.jpg', '头号玩家', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (5, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1527405023383089.jpg', '良种动物', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (7, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1527405918883501.jpg', '悲惨世界', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (8, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1527410387608938.jpg', '猛龙怪客', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (9, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1527410913000043.jpg', '绿野仙踪', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (10, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1527414992530668.jpg', '苍穹浩瀚', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (11, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1527415066008906.jpg', '犯罪现场调查', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (12, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1527415725066605.jpg', '西部世界', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (13, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1656533065428749.jpg', '人生大事', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (14, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1656539492059956.jpg', '绿皮书', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `movie` VALUES (15, 'https://images-1306663673.cos.ap-nanjing.myqcloud.com/1656748217792429.jpg', '变形金刚', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
