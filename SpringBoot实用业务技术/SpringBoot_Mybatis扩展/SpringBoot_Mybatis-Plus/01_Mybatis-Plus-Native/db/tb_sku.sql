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

 Date: 01/09/2023 17:25:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_sku
-- ----------------------------
DROP TABLE IF EXISTS `tb_sku`;
CREATE TABLE `tb_sku`(
    `id`           int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `image`        varchar(255) CHARACTER SET utf8mb3                      DEFAULT NULL COMMENT '图片',
    `price`        decimal(10, 2)                                          DEFAULT NULL COMMENT '价格',
    `inventory`    int                                                     DEFAULT NULL COMMENT '库存',
    `title`        varchar(255) CHARACTER SET utf8mb3                      DEFAULT NULL COMMENT '标题',
    `shelves`      tinyint                                                 DEFAULT NULL COMMENT '是否上架',
    `menu`         varchar(5000) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '菜单',
    `create_date`  datetime                                                DEFAULT NULL COMMENT '创建时间',
    `updated_date` datetime                                                DEFAULT NULL COMMENT '更新时间',
    `create_by`    varchar(255) CHARACTER SET utf8mb3                      DEFAULT NULL COMMENT '创建人',
    `updated_by`   varchar(255) COLLATE utf8mb4_bin                        DEFAULT NULL COMMENT '更新人',
    `delete_flag`  tinyint                                                 DEFAULT NULL COMMENT '逻辑删除',
    `order_json`   varchar(5000) COLLATE utf8mb4_bin                       DEFAULT NULL COMMENT '订单数据',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of tb_sku
-- ----------------------------
BEGIN;
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (1, 'Food', 111.17, 264, 'Cheryy', 0, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2006-12-07 12:02:16', '2003-04-28 16:36:44', 'Hsuan Ka Ming', 'Gloria Grant', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (2, 'CDs & Vinyl', 306.79, 905, 'Strawberry elite', 0, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2011-12-28 00:03:25', '2003-05-24 00:29:37', 'Justin Collins', 'Han Wai Man', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (3, 'Baggage & Travel Equipment', 831.38, 751, 'tpple se', 0, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2000-08-22 13:30:10', '2009-12-03 18:33:08', 'Randall Baker', 'Donald Lewis', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (4, 'Sports & Outdoor', 19.14, 650, 'aherry air', 1, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2020-07-28 23:50:38', '2010-11-06 03:43:11', 'Eleanor Mendoza', 'Zou Xiuying', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (5, 'Clothing, Shoes and Jewelry', 157.14, 710, 'irape', 0, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2005-11-25 06:39:53', '2004-09-06 17:33:59', 'Ernest Gonzalez', 'Jack Campbell', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (6, 'Books', 867.36, 600, 'Cheray', 0, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2015-08-31 20:19:15', '2001-08-29 03:43:15', 'Shing Wai San', 'Lo Wai San', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (7, 'CDs & Vinyl', 841.94, 808, 'qrape', 1, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2010-12-06 19:51:16', '2009-04-23 14:20:17', 'Keith West', 'Koon Fu Shing', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (8, 'Cell Phones & Accessories', 455.24, 473, 'Chxrry elite', 1, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2001-12-08 02:56:38', '2012-11-16 02:06:28', 'Judith Nichols', 'Zou Ziyi', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (9, 'Food', 837.34, 363, 'Raspberry', 0, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2011-02-26 13:39:17', '2016-08-06 00:44:41', 'Paula Watson', 'Emma Hamilton', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
INSERT INTO `tb_sku` (`id`, `image`, `price`, `inventory`, `title`, `shelves`, `menu`, `create_date`, `updated_date`, `create_by`, `updated_by`, `delete_flag`, `order_json`) VALUES (10, 'Books', 558.64, 720, 'giwi', 1, '[\"admin\",\"test\",\"system\",\"list\",\"abc\"]', '2023-07-26 03:57:45', '2023-01-31 21:15:44', 'Tang Wai Man', 'Duan Shihan', 0, '{\"orderId\":1,\"skuId\":41,\"num\":754,\"buyPrice\":169.07,\"menuList\":[\"ROLE_vip1\",\"ROLE_vip2\",\"ROLE_vip3\"],\"createDate\":\"2009-06-29T09:22:50.000+00:00\",\"updatedDate\":\"2001-01-12T04:00:33.000+00:00\",\"createBy\":\"Kwan Sze Kwan\",\"updatedBy\":\"Luis Peterson\",\"deleteFlag\":0,\"skuJson\":null}');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
