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

 Date: 01/09/2023 23:55:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order`(
    `order_id`     bigint         NOT NULL AUTO_INCREMENT COMMENT '订单id ',
    `sku_id`       bigint         NOT NULL COMMENT 'sku商品id',
    `num`          int            NOT NULL COMMENT '购买数量',
    `buy_price`    decimal(10, 2) NOT NULL COMMENT '购买价格',
    `menu`         varchar(5000)                                                  DEFAULT NULL COMMENT '菜单',
    `create_date`  datetime                                                       DEFAULT NULL COMMENT '创建时间',
    `updated_date` datetime                                                       DEFAULT NULL COMMENT '更新时间',
    `create_by`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  DEFAULT NULL COMMENT '创建人',
    `updated_by`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin         DEFAULT NULL COMMENT '更新人',
    `delete_flag`  tinyint                                                        DEFAULT '0' COMMENT '逻辑删除',
    `sku_json`     varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT 'sku数据',
    `version`      int                                                            DEFAULT '0' COMMENT '版本号',
    PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单详情表';

-- ----------------------------
-- Records of tb_order
-- ----------------------------
BEGIN;
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (1, 456, 12, 56.23, '[\"vip1\",\"vip2\"]', '2009-06-29 09:22:50', '2023-09-01 15:44:06', 'Kwan Sze Kwan', 'Luis Peterson', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (2, 13, 423, 295.46, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2007-09-25 19:42:42', '2014-02-03 15:55:09', 'Tao Ziyi', 'Bonnie Hall', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (3, 514, 8, 374.46, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2018-01-25 23:01:56', '2010-11-16 17:06:22', 'Stephanie Gonzales', 'Randy Kennedy', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (4, 427, 723, 563.73, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2004-03-09 07:54:32', '2021-11-11 01:13:53', 'Yuan Lan', '范杰宏', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (5, 71, 942, 998.20, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2022-02-24 19:21:35', '2006-11-04 05:10:19', 'Ng Suk Yee', 'Bonnie Long', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (6, 838, 919, 425.17, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2019-03-16 08:16:53', '2000-05-19 04:27:26', 'Craig Mendez', 'Julia Robertson', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (7, 705, 851, 810.82, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2022-09-20 02:13:39', '2017-07-17 09:25:24', 'Hashimoto Mai', 'Ruth Palmer', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (8, 978, 758, 41.00, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2012-09-01 06:04:03', '2018-12-19 10:36:32', 'Saito Misaki', 'Gregory Chen', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (9, 801, 606, 454.87, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2021-01-27 01:08:04', '2022-11-18 05:21:59', 'Xia Rui', 'Anita Burns', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
INSERT INTO `tb_order` (`order_id`, `sku_id`, `num`, `buy_price`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `sku_json`, `version`) VALUES (10, 996, 685, 703.34, '[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"]', '2020-01-22 18:53:50', '2019-06-06 20:36:04', 'Kimura Kaito', '萧子异', 0, '{\"id\":10,\"image\":\"Books\",\"price\":558.64,\"inventory\":720,\"title\":\"giwi\",\"shelves\":1,\"menu\":[\"system:admin:list\",\"system:test:abc\"],\"createDate\":\"2023-07-26T03:57:45.000+00:00\",\"updatedDate\":\"2023-01-31T21:15:44.000+00:00\",\"createBy\":\"Tang Wai Man\",\"updatedBy\":\"Duan Shihan\",\"deleteFlag\":0,\"order_json\":null}', 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
