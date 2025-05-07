/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : localhost:3306
 Source Schema         : business

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 01/09/2023 17:25:35
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_consumer
-- ----------------------------
DROP TABLE IF EXISTS `tb_consumer`;
CREATE TABLE `tb_consumer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
  `superior_id` int NOT NULL COMMENT '上级Id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '用户名',
  `pass_word` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '密码',
  `alias` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '别名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `sex` tinyint DEFAULT NULL COMMENT '性别',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地址',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮件',
  `delete_flag` tinyint DEFAULT NULL COMMENT '逻辑删除',
  `created_date` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_consumer
-- ----------------------------
BEGIN;
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (1, 0, '小透明', '123456', '劫', 26, 1, '15623041568', '湖北省武汉市洪山区', 'yamwaihan@outlook.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (2, 0, '瞬狱影杀不及她', '456789', '亚索', 4545, 1, '15623041568', '安徽省合肥市长丰县', 'xuzh@gmail.com', 1, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (3, 2, '笋子爱吃肉', '753159', '男刀', 26, 1, '15623041568', '北京市辖区崇文区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (4, 3, '水方言彦子', '159753', '妖姬', 45, 0, '15623041568', '福建省莆田市仙游县', 'xuzh@gmail.com', 1, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (5, 4, '细牛贿赂二牛杀了大牛', '456123', '皎月', 26, 0, '12345678952', '甘肃省酒泉市敦煌市', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (6, 7, '冈本零点零一', '789456', '球女', 26, 0, '15623041568', '广东省深圳市龙岗区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (7, 0, '邱社会', '123456', '鲲', 26, 1, '15623041568', '广西壮族自治区北海市海城区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (8, 11, '长安九万里', '456789', '鹏', 26, 1, '15623041568', '贵州省安顺市平坝县', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (9, 0, '消失的十一层', '753159', '魑魅', 26, 1, '15623041568', '湖北省武汉市洪山区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (10, 9, '画江湖之不良人', '159753', '魍', 26, 0, '15623041568', '安徽省合肥市长丰县', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (11, 0, '魁拔', '456123', '魉', 26, 0, '15623041568', '北京市辖区崇文区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (12, 0, '名侦探柯南', '789456', '盖伦', 26, 0, '15623041568', '福建省莆田市仙游县', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (13, 6, '中国奇谭', '456789', '卡特', 26, 1, '15623041568', '甘肃省酒泉市敦煌市', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (14, 0, '凹凸世界', '753159', '潮汐海灵', 26, 1, '15623041568', '广东省深圳市龙岗区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (15, 0, '中国古诗词动漫', '159753', '高启强', 26, 0, '15623041568', '广西壮族自治区北海市海城区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (16, 2, '三体', '456123', '安欣', 26, 0, '15623041568', '贵州省安顺市平坝县', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (17, 3, '镇魂街', '789456', '李星云', 26, 0, '15623041568', '湖北省武汉市洪山区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (18, 0, '双月之城', '456789', '上官云阙', 5, 1, '15623041568', '安徽省合肥市长丰县', 'xuzh@gmail.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (19, 1, '灵笼', '753159', '袁天罡', 4, 1, '15623041568', '北京市辖区崇文区', 'xuzh@gmail.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (20, 10, '伍六七', '159753', '李淳风', 26, 0, '15623041568', '福建省莆田市仙游县', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (21, 16, '境界的彼方', '456123', '蚩梦', 26, 0, '15623041568', '甘肃省酒泉市敦煌市', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (22, 0, '黑白无双', '789456', '张子凡', 26, 0, '15623041568', '广东省深圳市龙岗区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (23, 20, '漆黑的子弹', '456789', '诺手', 26, 1, '15623041568', '广西壮族自治区北海市海城区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (24, 15, '龙与虎', '753159', '石头人', 26, 1, '15623041568', '贵州省安顺市平坝县', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (25, 19, '一人之下', '159753', '李茂贞', 26, 0, '15623041568', '湖北省武汉市洪山区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (26, 7, '刀剑神域', '456123', '李嗣源', 26, 0, '15623041568', '安徽省合肥市长丰县', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (27, 14, '武庚纪', '789456', '旺仔', 26, 0, '15623041568', '北京市辖区崇文区', 'bjdfhd@qq.com', 0, '2023-08-29 06:30:00', '2023-08-29 06:30:00');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (28, 1, '测试', '616165', '法师', 26, 1, '5623041568', '安徽省合肥市长丰县', 'dfgsd@163.com', 0, '2023-08-30 16:54:22', '2023-08-30 16:54:25');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (30, 1, '测试', '616165', '法师', 24, 1, '15623041568', '安徽省合肥市长丰县', 'yamwaihan@outlook.com', 0, '2023-08-30 09:55:32', '2023-08-30 09:55:32');
INSERT INTO `tb_consumer` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (31, 1, '测试', '616165', '法师', 24, 1, '15623041568', '安徽省合肥市长丰县', 'yamwaihan@outlook.com', 0, '2023-08-30 10:17:22', '2023-08-30 10:17:22');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
