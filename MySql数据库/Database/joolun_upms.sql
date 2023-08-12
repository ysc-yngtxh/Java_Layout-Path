/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : joolun_upms

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 12/08/2023 17:24:34
*/
DROP DATABASE IF EXISTS joolun_upms;
CREATE DATABASE joolun_upms;
USE joolun_upms;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PK',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `create_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建者ID',
  `table_name` varchar(200) DEFAULT '' COMMENT '表名',
  `table_comment` varchar(500) DEFAULT '' COMMENT '表描述',
  `package_name` varchar(100) DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) DEFAULT NULL COMMENT '生成模块名',
  `author` varchar(50) DEFAULT NULL COMMENT '作者',
  `table_prefix` varchar(50) DEFAULT NULL COMMENT '表前缀',
  `gen_key` varchar(50) NOT NULL COMMENT '服务的路由key',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_table_name` (`table_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='代码生成配置表';

-- ----------------------------
-- Records of gen_table
-- ----------------------------
BEGIN;
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1255750807946391554', '0', '2020-04-30 14:48:18', '2020-04-30 14:48:18', NULL, 'gen_table', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1255753730952654849', '0', '2020-04-30 14:59:54', '2020-04-30 14:59:54', NULL, 'sys_config_editor', '', '', '', '', '', '457');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1255820343672320001', '0', '2020-04-30 19:24:36', '2020-04-30 19:24:36', NULL, 'sys_role', '', '', '', '', '', 'weixin');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1255821032897130498', '0', '2020-04-30 19:27:20', '2020-04-30 19:27:20', NULL, 'sys_dict_value', '', '', '', '', '', 'upms');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1261168174558015489', '0', '2020-05-15 13:34:58', '2020-05-15 13:34:58', NULL, 'shop_info', '', 'com.joolun.cloud.mall', 'api', '', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1261537942976823298', '0', '2020-05-16 14:04:18', '2020-05-16 14:04:18', NULL, 'pay_config', '', 'com.joolun.cloud.pay', 'api', '', '', 'payapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1268419556851826690', '0', '2020-06-04 13:49:23', '2020-06-04 13:49:23', NULL, 'theme_mobile', '', 'com.joolun.cloud.mall', 'api', '', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1293104588490076161', '0', '2020-08-11 16:38:53', '2020-08-11 16:38:53', NULL, 'seckill_hall', '', 'com.joolun.cloud.mall', 'api', '', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1293382644647653377', '0', '2020-08-11 16:38:53', '2020-08-11 16:38:53', NULL, 'seckill_info', '', 'com.joolun.cloud.mall', 'api', '', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1293427362278469633', '0', '2020-08-11 16:38:53', '2020-08-11 16:38:53', NULL, 'seckill_hall_info', '', 'com.joolun.cloud.mall', 'api', '', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1298424453346136066', '0', '2020-08-26 08:58:07', '2020-08-26 08:58:07', NULL, 'goods_category_shop', '', 'com.joolun.cloud.mall', 'api', '', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1298781720218210305', '0', '2020-08-27 08:37:46', '2020-08-27 08:37:46', NULL, 'pay_apply_form', '', 'com.joolun.cloud.pay', 'api', '', '', 'payapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1308235580145954818', '0', '2020-09-22 10:44:02', '2020-09-22 10:44:02', NULL, 'page_devise', '', 'com.joolun.cloud.mall', 'api', '', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1321280508006588418', '0', '2020-10-28 10:39:55', '2020-10-28 10:39:55', NULL, 'shop_apply', '', 'com.joolun.cloud.mall', 'admin', 'JL', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1331121474162925570', '0', '2020-11-24 14:24:25', '2020-11-24 14:24:25', NULL, 'article_category', '文章分类', 'com.joolun.cloud.mall', 'api', 'JL', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1331126454496272385', '0', '2020-11-24 14:24:25', '2020-11-24 14:24:25', NULL, 'article_info', '文章', 'com.joolun.cloud.mall', 'api', 'JL', '', 'mallapi');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1342092292527308802', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'user_collect', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1342110032403185665', '0', '2020-12-24 22:09:01', '2020-12-24 22:09:01', NULL, 'user_footprint', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1349242439481696258', '0', '2021-01-13 14:30:39', '2021-01-13 14:30:39', NULL, 'sign_config', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1349257233064587265', '0', '2021-01-13 15:29:27', '2021-01-13 15:29:27', NULL, 'sign_record', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1371293336046952449', '0', '2021-03-15 10:53:03', '2021-03-15 10:53:03', NULL, 'wxma_code_audit', '', 'com.joolun.cloud.weixin', 'admin', '', '', 'weixin');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1376419795686154242', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'user_shop', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1381870005837156354', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'distribution_config', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1385070407791378434', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'user_record', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1386253784273948673', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'distribution_user', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1387956485047226370', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'distribution_order', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1390197555629953026', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'distribution_order_item', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1391638992917630978', '0', '2020-12-24 20:58:32', '2020-12-24 20:58:32', NULL, 'user_withdraw_record', '', 'com.joolun.cloud.mall', 'admin', '', '', 'mall');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1422744411652001793', '0', '2021-08-04 10:21:15', '2021-08-04 10:21:15', NULL, 'sys_user', '', '', '', '', '', 'upms');
INSERT INTO `gen_table` (`id`, `del_flag`, `create_time`, `update_time`, `create_id`, `table_name`, `table_comment`, `package_name`, `module_name`, `author`, `table_prefix`, `gen_key`) VALUES ('1458653446072295425', '0', '2021-11-11 12:30:56', '2021-11-11 12:30:56', NULL, 'sys_user_tenant', '', 'com.joolun.cloud.upms', 'admin', '', '', 'upms');
COMMIT;

-- ----------------------------
-- Table structure for sys_config_editor
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_editor`;
CREATE TABLE `sys_config_editor` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PK',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属租户',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `editor_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '编辑器类型1、quill-editor；2、froala',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='编辑器配置';

-- ----------------------------
-- Records of sys_config_editor
-- ----------------------------
BEGIN;
INSERT INTO `sys_config_editor` (`id`, `tenant_id`, `del_flag`, `create_time`, `update_time`, `editor_type`) VALUES ('1226761439705792514', '1', '0', '2020-02-10 14:54:54', '2020-02-10 14:54:54', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_config_storage
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_storage`;
CREATE TABLE `sys_config_storage` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PK',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '所属租户',
  `del_flag` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `storage_type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '存储类型1、阿里OSS；2、七牛云；3、minio',
  `endpoint` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '地域节点',
  `access_key_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'access_key',
  `access_key_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'access_secret',
  `bucket` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '域名',
  `water_mark_content` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图片水印内容',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='存储配置';

-- ----------------------------
-- Records of sys_config_storage
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_datasource
-- ----------------------------
DROP TABLE IF EXISTS `sys_datasource`;
CREATE TABLE `sys_datasource` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '名称',
  `url` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新',
  `del_flag` char(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='数据源表';

-- ----------------------------
-- Records of sys_datasource
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编号',
  `type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '描述',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1', 'log_type', '日志类型', '2019-01-22 11:06:44', '2019-07-25 16:30:52', '异常、正常', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('11', 'wx_rep_type', '微信回复消息类型', '2019-04-12 16:46:24', '2019-07-25 16:30:55', 'text：文本；image：图片；voice：语音；video：视频；music：音乐；news：图文', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('12', 'wx_req_type', '微信请求消息类型', '2019-04-04 10:23:41', '2019-07-25 16:30:58', 'text：文本；image：图片；voice：语音；video：视频；shortvideo：小视频；location：地理位置；event：事件推送', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1224310636680245250', 'storage_type', '存储类型', '2020-02-03 20:36:17', '2020-02-03 20:36:17', NULL, '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1226758602905022466', 'editor_type', '文本编辑器', '2020-02-10 14:43:37', '2020-02-10 14:43:37', NULL, '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('13', 'wx_rep_mate', '微信回复类型文本匹配类型', '2019-04-13 11:11:57', '2019-07-25 16:31:00', '微信回复类型文本匹配类型', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('14', 'weixin_type', '公众号类型', '2019-04-12 09:35:56', '2019-07-25 16:31:05', '公众号类型', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('15', 'wx_qr_scene_str', '微信二维码扫码场景', '2019-04-29 11:50:57', '2019-06-11 21:34:24', '微信二维码扫码场景', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('17', 'wx_subscribe', '微信用户关注状态', '2019-06-02 10:21:59', '2019-06-11 21:34:24', '微信用户关注状态', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('18', 'wx_subscribe_scene', '微信用户关注渠道', '2019-06-02 10:27:25', '2019-06-11 21:34:24', '微信用户关注渠道', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('19', 'wx_sex', '微信性别', '2019-06-02 10:31:16', '2019-06-11 21:34:24', '微信性别', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('2', 'third_party_type', '第三方登录类型', '2019-03-19 11:09:44', '2019-06-11 21:34:24', '微信、QQ', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('20', 'yes_no', '是/否', '2019-06-09 12:37:37', '2019-06-11 21:34:24', '是/否', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('21', 'wx_event', '微信事件类型', '2019-06-09 12:42:02', '2019-06-11 21:34:24', '微信事件类型', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('24', 'wx_verify_type', '认证类型', '2019-06-25 10:27:39', '2019-06-25 10:27:39', '认证类型', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('26', 'wx_mass_msg_status', '微信群发消息状态', '2019-07-03 09:04:19', '2019-07-03 09:04:19', '微信群发消息状态', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('3947ade00eea6517e2b66a1f7985fe1a', 'organ_type', '机构类型', '2019-07-25 18:29:07', '2019-07-25 18:29:07', '机构类型', '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('51aa2730eb17bca730cf7a50d9ce4b35', 'true_false', '是/否', '2019-07-31 09:20:45', '2019-07-31 09:20:45', NULL, '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('53372a6873b2d03e5fb8c1918de82d9f', 'authorized_grant_types', '授权类型', '2019-07-30 17:08:13', '2019-07-30 17:08:13', NULL, '0');
INSERT INTO `sys_dict` (`id`, `type`, `description`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('6c5ab5650155a1b4cc3c1a2c881fa137', 'request_method', '请求类型', '2019-07-26 12:11:37', '2019-07-26 12:11:37', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_dict_value
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_value`;
CREATE TABLE `sys_dict_value` (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编号',
  `dict_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '字典ID',
  `value` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '数据值',
  `label` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '标签名',
  `type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '类型',
  `description` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
  `sort` int NOT NULL COMMENT '排序（升序）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='字典项';

-- ----------------------------
-- Records of sys_dict_value
-- ----------------------------
BEGIN;
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1', '1', '9', '异常', 'log_type', '日志异常', 1, '2019-03-19 11:08:59', '2019-03-25 12:49:13', '', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1224310769123782658', '1224310636680245250', '1', '阿里OSS', 'storage_type', '阿里OSS', 1, '2020-02-03 20:36:48', '2020-02-03 20:36:48', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1224310831061069825', '1224310636680245250', '2', '七牛云', 'storage_type', '七牛云', 2, '2020-02-03 20:37:03', '2020-02-03 20:37:03', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1224310888170713090', '1224310636680245250', '3', 'minio', 'storage_type', 'minio', 4, '2020-02-03 20:37:17', '2020-02-03 20:37:17', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1226758708496625665', '1226758602905022466', '1', 'quill-editor', 'editor_type', 'quill-editor', 1, '2020-02-10 14:44:02', '2020-02-10 14:44:02', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1226758779615244290', '1226758602905022466', '2', 'froala', 'editor_type', 'froala', 2, '2020-02-10 14:44:19', '2020-02-10 14:44:19', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('1227915814432108545', '1224310636680245250', '4', '腾讯cos', 'storage_type', '腾讯cos', 3, '2020-02-13 19:21:58', '2020-02-13 19:21:58', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('14758623adf70d753af951a5f4df0643', '53372a6873b2d03e5fb8c1918de82d9f', 'authorization_code', '授权码模式', 'authorized_grant_types', 'Web服务端应用与第三方移动App应用', 0, '2019-07-30 17:11:08', '2019-07-30 17:11:08', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('2', '1', '0', '正常', 'log_type', '日志正常', 0, '2019-03-19 11:09:17', '2019-03-25 12:49:18', '', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('29', '11', 'text', '文本', 'wx_rep_type', '文本', 1, '2019-04-23 16:47:14', '2019-04-23 16:47:14', '文本', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('2f2a733749ead9eb7ad9d32b4930ca0c', '3947ade00eea6517e2b66a1f7985fe1a', '3', '小组', 'organ_type', '小组', 2, '2019-07-25 21:45:06', '2019-07-25 21:45:06', '小组', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('3', '2', 'WX', '微信', 'third_party_type', '微信登录', 0, '2019-03-19 11:10:02', '2019-07-25 16:26:54', '', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('30', '11', 'image', '图片', 'wx_rep_type', '图片', 2, '2019-04-23 16:47:36', '2019-04-23 16:47:36', '图片', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('30cb1469d0f571a7febb45c6e8386cdd', '6c5ab5650155a1b4cc3c1a2c881fa137', 'POST', '新增', 'request_method', '新增', 1, '2019-07-26 12:12:48', '2019-07-26 12:12:48', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('31', '11', 'voice', '语音', 'wx_rep_type', '语音', 3, '2019-04-23 16:47:57', '2019-04-23 16:47:57', '语音', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('32', '11', 'video', '视频', 'wx_rep_type', '视频', 4, '2019-04-23 16:48:12', '2019-04-23 16:48:12', '视频', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('33', '11', 'music', '音乐', 'wx_rep_type', '音乐', 5, '2019-04-23 16:48:27', '2019-04-23 16:48:27', '音乐', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('34', '11', 'news', '图文', 'wx_rep_type', '图文', 6, '2019-04-23 16:48:42', '2019-04-23 16:48:42', '图文', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('35', '12', 'text', '文本', 'wx_req_type', '文本', 1, '2019-04-24 10:24:03', '2019-04-24 10:24:03', '文本', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('36', '12', 'image', '图片', 'wx_req_type', '图片', 2, '2019-04-24 10:24:18', '2019-04-24 10:24:18', '图片', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('37', '12', 'voice', '语音', 'wx_req_type', '语音', 3, '2019-04-24 10:24:32', '2019-04-24 10:24:32', '语音', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('38', '12', 'video', '视频', 'wx_req_type', '视频', 4, '2019-04-24 10:24:46', '2019-04-24 10:24:46', '视频', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('39', '12', 'shortvideo', '小视频', 'wx_req_type', '小视频', 5, '2019-04-24 10:25:01', '2019-04-24 10:25:01', '小视频', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('4', '2', 'QQ', 'QQ', 'third_party_type', 'QQ登录', 1, '2019-03-19 11:10:14', '2019-07-25 16:26:54', '', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('40', '12', 'location', '地理位置', 'wx_req_type', '地理位置', 6, '2019-04-24 10:25:15', '2019-04-24 10:25:15', '地理位置', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('41', '13', '1', '全匹配', 'wx_rep_mate', '全匹配', 1, '2019-04-24 11:12:12', '2019-04-24 11:12:12', '全匹配', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('42', '13', '2', '半匹配', 'wx_rep_mate', '半匹配', 2, '2019-04-24 11:12:26', '2019-04-24 11:12:26', '半匹配', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('43', '14', '0', '订阅号', 'weixin_type', '订阅号', 0, '2019-04-29 09:36:12', '2019-06-21 09:49:12', '订阅号', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('44', '14', '2', '服务号', 'weixin_type', '服务号', 1, '2019-04-29 09:36:30', '2019-06-21 09:51:09', '服务号', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('45', '14', '1', '老帐号升级后的订阅号', 'weixin_type', '老帐号升级后的订阅号', 2, '2019-04-29 09:36:41', '2019-06-21 09:51:10', '老帐号升级后的订阅号', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('46', '15', '1', '后台默认二维码', 'wx_qr_scene_str', '后台默认二维码', 1, '2019-04-29 11:51:51', '2019-04-29 11:51:51', '后台默认二维码', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('471fccf38af3aca7d4a6ea9242de64f9', '53372a6873b2d03e5fb8c1918de82d9f', 'refresh_token', '刷新令牌', 'authorized_grant_types', '刷新并延迟访问令牌时长', 0, '2019-07-30 17:10:47', '2019-07-30 17:10:47', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('55', '12', 'link', '链接消息', 'wx_req_type', '链接消息', 7, '2019-05-30 11:05:00', '2019-05-30 11:05:00', '链接消息', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('56', '17', '1', '已关注', 'wx_subscribe', '已关注', 0, '2019-06-02 10:23:46', '2019-06-02 10:23:46', '已关注', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('57', '17', '0', '取消关注', 'wx_subscribe', '取消关注', 1, '2019-06-02 10:24:11', '2019-06-02 10:24:11', '取消关注', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('58', '17', '2', '网页授权用户', 'wx_subscribe', '网页授权用户', 2, '2019-06-02 10:24:34', '2019-06-02 10:24:34', '网页授权用户', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('59', '18', 'ADD_SCENE_SEARCH', '公众号搜索', 'wx_subscribe_scene', '公众号搜索', 0, '2019-06-02 10:28:04', '2019-06-02 10:28:04', '公众号搜索', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('5b86d249fa9db2c9cde7b3793b516d44', '53372a6873b2d03e5fb8c1918de82d9f', 'client_credentials', '客户端模式', 'authorized_grant_types', '没有用户参与的,内部服务端与第三方服务端', 0, '2019-07-30 17:11:26', '2019-07-30 17:11:26', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('5cd74c7e25a7358e18cb1778587c155d', '51aa2730eb17bca730cf7a50d9ce4b35', 'false', '否', 'true_false', '假', 0, '2019-07-31 09:21:28', '2019-07-31 09:21:28', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('60', '18', 'ADD_SCENE_ACCOUNT_MIGRATION', '公众号迁移', 'wx_subscribe_scene', '公众号迁移', 1, '2019-06-02 10:28:21', '2019-06-02 10:28:21', '公众号迁移', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('61', '18', 'ADD_SCENE_PROFILE_CARD', '名片分享', 'wx_subscribe_scene', '名片分享', 2, '2019-06-02 10:28:40', '2019-06-02 10:28:40', '名片分享', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('62', '18', 'ADD_SCENE_QR_CODE', '扫描二维码', 'wx_subscribe_scene', '扫描二维码', 3, '2019-06-02 10:28:58', '2019-06-02 10:28:58', '扫描二维码', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('63', '18', 'ADD_SCENE_PROFILE_LINK', '图文页内名称点击', 'wx_subscribe_scene', '图文页内名称点击', 4, '2019-06-02 10:29:18', '2019-06-02 10:29:18', '图文页内名称点击', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('64', '18', 'ADD_SCENE_PROFILE_ITEM', '图文页右上角菜单', 'wx_subscribe_scene', '图文页右上角菜单', 5, '2019-06-02 10:29:36', '2019-06-02 10:29:36', '图文页右上角菜单', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('65', '18', 'ADD_SCENE_PAID', '支付后关注', 'wx_subscribe_scene', '支付后关注', 7, '2019-06-02 10:29:50', '2019-06-02 10:29:50', '支付后关注', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('66', '18', 'ADD_SCENE_OTHERS', '其他', 'wx_subscribe_scene', '其他', 7, '2019-06-02 10:30:06', '2019-06-02 10:30:06', '其他', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('67', '19', '0', '未知', 'wx_sex', '未知', 0, '2019-06-02 10:31:31', '2019-06-02 10:31:31', '未知', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('68', '19', '1', '男', 'wx_sex', '男', 1, '2019-06-02 10:31:43', '2019-06-02 10:31:43', '男', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('69', '19', '2', '女', 'wx_sex', '女', 2, '2019-06-02 10:31:55', '2019-06-02 10:31:55', '女', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('70', '12', 'event', '事件推送', 'wx_req_type', '事件推送', 7, '2019-06-09 12:31:18', '2019-06-09 12:31:18', '事件推送', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('705f57cfc5d58f6a9f71b880a2efdf76', '6c5ab5650155a1b4cc3c1a2c881fa137', 'GET', '查询', 'request_method', '查询', 4, '2019-07-26 12:13:49', '2019-07-26 12:13:49', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('71', '20', '1', '是', 'yes_no', '是', 0, '2019-06-09 12:37:54', '2019-06-09 12:37:54', '是', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('72', '20', '0', '否', 'yes_no', '否', 1, '2019-06-09 12:38:06', '2019-06-09 12:38:06', '否', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('73', '21', 'subscribe', '关注', 'wx_event', '关注', 0, '2019-06-09 12:42:37', '2019-06-09 12:42:37', '关注', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('74', '21', 'unsubscribe', '取关', 'wx_event', '取关', 1, '2019-06-09 12:42:55', '2019-06-09 12:42:55', '取关', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('75', '21', 'CLICK', '点击菜单', 'wx_event', '点击菜单', 2, '2019-06-09 12:43:32', '2019-06-09 12:43:32', '点击菜单', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('76', '21', 'VIEW', '点击菜单链接', 'wx_event', '点击菜单链接', 3, '2019-06-09 12:43:59', '2019-06-09 12:43:59', '点击菜单链接', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('76064a463d0f06300dccfffb654d1fe9', '53372a6873b2d03e5fb8c1918de82d9f', 'implicit', '隐式授权', 'authorized_grant_types', 'Web网页应用或第三方移动App应用', 0, '2019-07-30 17:13:41', '2019-07-30 17:13:41', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('77', '24', '-1', '未认证', 'wx_verify_type', '未认证', 0, '2019-06-25 10:28:39', '2019-06-25 10:28:39', '未认证', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('78', '24', '0', '微信认证', 'wx_verify_type', '微信认证', 1, '2019-06-25 10:28:56', '2019-06-25 10:28:56', '微信认证', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('79', '24', '1', '新浪微博认证', 'wx_verify_type', '新浪微博认证', 2, '2019-06-25 10:29:10', '2019-06-25 10:29:10', '新浪微博认证', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('7a2d5f98b2f60fa309aa4dd69c111ea2', '6c5ab5650155a1b4cc3c1a2c881fa137', 'PUT', '修改', 'request_method', '修改', 2, '2019-07-26 12:12:18', '2019-07-26 12:12:18', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('80', '24', '2', '腾讯微博认证', 'wx_verify_type', '腾讯微博认证', 3, '2019-06-25 10:29:22', '2019-06-25 10:29:22', '腾讯微博认证', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('81', '24', '3', '已资质认证通过但还未通过名称认证', 'wx_verify_type', '已资质认证通过但还未通过名称认证', 4, '2019-06-25 10:29:38', '2019-06-25 10:29:38', '已资质认证通过但还未通过名称认证', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('82', '24', '4', '已资质认证通过、还未通过名称认证，但通过了新浪微博认证', 'wx_verify_type', '已资质认证通过、还未通过名称认证，但通过了新浪微博认证', 5, '2019-06-25 10:29:52', '2019-06-25 10:29:52', '已资质认证通过、还未通过名称认证，但通过了新浪微博认证', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('83', '24', '5', '已资质认证通过、还未通过名称认证，但通过了腾讯微博认证', 'wx_verify_type', '已资质认证通过、还未通过名称认证，但通过了腾讯微博认证', 6, '2019-06-25 10:30:05', '2019-06-25 10:30:05', '已资质认证通过、还未通过名称认证，但通过了腾讯微博认证', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('86', '26', 'SUB_SUCCESS', '提交成功', 'wx_mass_msg_status', '提交成功', 0, '2019-07-03 09:04:47', '2019-07-03 09:04:47', '提交成功', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('87', '26', 'SUB_FAIL', '提交失败', 'wx_mass_msg_status', '提交失败', 1, '2019-07-03 09:05:15', '2019-07-03 09:05:15', '提交失败', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('88', '26', 'SEND_SUCCESS', '发送成功', 'wx_mass_msg_status', '发送成功', 2, '2019-07-03 09:05:31', '2019-07-03 09:05:31', '发送成功', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('89', '26', 'SENDING', '发送中', 'wx_mass_msg_status', '发送中', 3, '2019-07-03 09:06:11', '2019-07-03 09:06:11', '发送中', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('90', '26', 'SEND_FAIL', '发送失败', 'wx_mass_msg_status', '发送失败', 4, '2019-07-03 09:06:30', '2019-07-03 09:06:30', '发送失败', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('91', '26', 'DELETE', '已删除', 'wx_mass_msg_status', '已删除', 5, '2019-07-03 09:06:48', '2019-07-03 09:06:48', '已删除', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('91be97d29d6849492c0b279478957f4e', '3947ade00eea6517e2b66a1f7985fe1a', '1', '公司', 'organ_type', '公司', 0, '2019-07-25 18:30:20', '2019-07-25 18:30:20', '公司', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('97ac1672e010af552517a46e5d150678', '6c5ab5650155a1b4cc3c1a2c881fa137', 'DELETE', '删除', 'request_method', '删除', 3, '2019-07-26 12:13:08', '2019-07-26 12:13:08', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('9b75d4968ea73c2cd494f3688a043878', '3947ade00eea6517e2b66a1f7985fe1a', '4', '其它', 'organ_type', '其它', 3, '2019-07-25 21:45:32', '2019-07-25 21:45:32', '其它', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('9c84ab74bda56b68dc381a0cad08b321', '51aa2730eb17bca730cf7a50d9ce4b35', 'true', '是', 'true_false', '真', 0, '2019-07-31 09:21:08', '2019-07-31 09:21:08', NULL, '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('a5f575a44038c13900feaab29704e4f3', '3947ade00eea6517e2b66a1f7985fe1a', '2', '部门', 'organ_type', '部门', 1, '2019-07-25 21:44:47', '2019-07-25 21:44:47', '部门', '0');
INSERT INTO `sys_dict_value` (`id`, `dict_id`, `value`, `label`, `type`, `description`, `sort`, `create_time`, `update_time`, `remarks`, `del_flag`) VALUES ('b369cb683678b6b2e913187277799b83', '53372a6873b2d03e5fb8c1918de82d9f', 'password', '密码模式', 'authorized_grant_types', '内部Web网页应用与内部移动App应用', 0, '2019-07-30 17:10:29', '2019-07-30 17:10:29', NULL, '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '编号',
  `type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '1' COMMENT '日志类型（0：正常；9：异常）',
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '服务ID',
  `create_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '操作提交的数据',
  `time` mediumtext CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '执行时间',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `exception` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '异常信息',
  `tenant_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '所属租户',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_log_create_by` (`create_by`) USING BTREE,
  KEY `sys_log_request_uri` (`request_uri`) USING BTREE,
  KEY `sys_log_type` (`type`) USING BTREE,
  KEY `sys_log_create_date` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_log_login
-- ----------------------------
DROP TABLE IF EXISTS `sys_log_login`;
CREATE TABLE `sys_log_login` (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT 'PK',
  `create_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建者ID',
  `create_by` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'IP地址',
  `user_agent` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '请求URI',
  `params` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '操作提交的数据',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `address` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '地址信息',
  `tenant_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT '0' COMMENT '所属租户',
  `type` char(1) DEFAULT NULL COMMENT '日志类型（0：正常；9：异常）',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='登录日志表';

-- ----------------------------
-- Records of sys_log_login
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单名称',
  `permission` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '前端URL',
  `parent_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '图标',
  `component` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '页面',
  `sort` int NOT NULL DEFAULT '0' COMMENT '排序值',
  `keep_alive` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `type` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('01e9e720e3eedce613a297dcdddb3d63', '积分管理', NULL, 'points', '8000000', 'el-icon-s-flag', 'Layout', 50, '0', '0', '2019-12-06 16:33:52', '2021-03-29 14:30:59', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('04c920605926e47982b7ede7fa5c3ec6', '积分明细', NULL, 'pointsrecord', '01e9e720e3eedce613a297dcdddb3d63', 'el-icon-s-order', 'views/mall/pointsrecord/index', 2, '0', '0', '2019-12-11 11:28:46', '2020-02-15 22:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('0776fdb3afe857025aefeb6500f67fb6', '商城订单列表', 'mall:orderinfo:index', NULL, '70dbfd7911a393a3c50d1eef09a8708f', NULL, NULL, 1, '0', '1', '2019-10-18 21:41:30', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('088b9371d30c932f9ba555a457a165cb', '素材删除', 'mall:material:del', NULL, 'e2ab8932bc86ad98b917c22731205caa', NULL, NULL, 1, '0', '1', '2019-10-26 19:36:09', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('097f4947ac78faf5074c357f5b1ee8f1', '商品评价列表', 'mall:goodsappraises:index', NULL, 'c247293edbfc0ee004f413cb6ee654cd', NULL, NULL, 1, '0', '1', '2019-10-18 21:41:07', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('0d1cfcbd88e13d75ea9796d08b4964a0', '退款单查询', 'mall:orderrefunds:get', NULL, '6e46351de075ab9febf8ee6ec17bdced', NULL, NULL, 1, '0', '1', '2019-12-13 19:23:25', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1000000', '系统管理', '', '/sys', '0', 'icon-quanxianguanli', 'Layout', 40, '0', '0', '2018-09-28 08:29:53', '2021-10-30 12:47:47', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('10e7a8ca4da4f8cd9e36a375d7266f6a', '商品评价修改', 'mall:goodsappraises:edit', NULL, 'c247293edbfc0ee004f413cb6ee654cd', NULL, NULL, 1, '0', '1', '2019-09-25 20:30:13', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1100000', '用户管理', NULL, 'user', '1000000', 'icon-yonghu', 'views/upms/user/index', 1, '0', '0', '2017-11-02 22:24:37', '2020-04-26 18:42:27', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1101000', '用户新增', 'sys:user:add', NULL, '1100000', NULL, NULL, 1, '0', '1', '2017-11-08 09:52:09', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1102000', '用户修改', 'sys:user:edit', NULL, '1100000', NULL, NULL, 1, '0', '1', '2017-11-08 09:52:48', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1103000', '用户删除', 'sys:user:del', NULL, '1100000', NULL, NULL, 1, '0', '1', '2017-11-08 09:54:01', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1104000', '用户详情', 'sys:user:get', NULL, '1100000', NULL, NULL, 1, '0', '1', '2019-07-27 11:34:23', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1105000', '用户密码修改', 'sys:user:password', NULL, '1100000', NULL, NULL, 1, '0', '1', '2019-07-27 18:07:08', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1200000', '菜单管理', NULL, 'menu', '1454042553678036993', 'icon-caidanguanli', 'views/upms/menu/index', 4, '0', '0', '2017-11-08 09:57:27', '2021-10-29 19:14:02', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1201000', '菜单新增', 'sys:menu:add', NULL, '1200000', NULL, NULL, 1, '0', '1', '2017-11-08 10:15:53', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1202000', '菜单修改', 'sys:menu:edit', NULL, '1200000', NULL, NULL, 1, '0', '1', '2017-11-08 10:16:23', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1203000', '菜单删除', 'sys:menu:del', NULL, '1200000', NULL, NULL, 1, '0', '1', '2017-11-08 10:16:43', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1224306298712969218', '文件存储配置', NULL, 'configstorage', '1000000', 'el-icon-s-management', 'views/upms/configstorage/form', 15, '0', '0', '2020-02-03 20:19:02', '2021-11-01 19:08:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1224306628599173121', '文件存储配置查询', 'sys:configstorage:get', NULL, '1224306298712969218', NULL, NULL, 0, '0', '1', '2020-02-03 20:20:21', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1224307007944609794', '文件存储配置修改', 'sys:configstorage:edit', NULL, '1224306298712969218', NULL, NULL, 0, '0', '1', '2020-02-03 20:21:51', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1225254548244733953', '菜单查询', 'sys:menu:get', '', '1200000', '', '', 0, '0', '1', '2020-02-06 11:07:03', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226525445274419201', '保障服务管理', '', 'ensure', '1000000', 'el-icon-s-management', 'views/mall/ensure/index', 40, '0', '0', '2020-02-09 23:17:08', '2021-10-30 19:34:11', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226525576396750849', '保障服务列表', 'mall:ensure:index', '', '1226525445274419201', '', '', 0, '0', '1', '2020-02-09 23:17:39', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226525700103553025', '保障服务查询', 'mall:ensure:get', '', '1226525445274419201', '', '', 0, '0', '1', '2020-02-09 23:18:09', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226525832920383490', '保障服务新增', 'mall:ensure:add', '', '1226525445274419201', '', '', 0, '0', '1', '2020-02-09 23:18:40', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226525967616262146', '保障服务修改', 'mall:ensure:edit', '', '1226525445274419201', '', '', 0, '0', '1', '2020-02-09 23:19:13', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226526082179481602', '保障服务删除', 'mall:ensure:del', '', '1226525445274419201', '', '', 0, '0', '1', '2020-02-09 23:19:40', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226759608866893825', '文本编辑器配置', '', 'configeditor', '1000000', 'el-icon-s-unfold', 'views/upms/configeditor/form', 20, '0', '0', '2020-02-10 14:47:37', '2021-11-01 19:08:44', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226759834914713601', '文本编辑器查询', 'sys:configeditor:get', '', '1226759608866893825', '', '', 0, '0', '1', '2020-02-10 14:48:31', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1226760046232137729', '文本编辑器修改', 'sys:configeditor:edit', '', '1226759608866893825', '', '', 0, '0', '1', '2020-02-10 14:49:21', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1227202152985784322', '常用工具', '', '/tool', '0', 'el-icon-s-cooperation', 'Layout', 50, '0', '0', '2020-02-11 20:06:08', '2021-07-31 21:27:08', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1227202599968567298', '邮件管理', '', 'email', '1227202152985784322', 'el-icon-s-promotion', 'views/upms/email/form', 10, '0', '0', '2020-02-11 20:07:54', '2020-04-26 18:43:07', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1227203300044042241', '发送邮件', 'sys:email:add', '', '1227202599968567298', '', '', 0, '0', '1', '2020-02-11 20:10:41', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1227524298689736706', '短信工具', '', 'sms', '1227202152985784322', 'el-icon-s-comment', 'views/upms/sms/form', 20, '0', '0', '2020-02-12 17:26:13', '2020-04-26 18:42:49', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1227524435889614850', '发短信', 'sys:sms:add', '', '1227524298689736706', '', '', 0, '0', '1', '2020-02-12 17:26:46', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1227568833306177537', '规格查询', 'mall:goodsspec:get', '', '8400000', '', '', 0, '0', '1', '2020-02-12 20:23:11', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1228676296659161089', '小程序用户', '', 'wxuser', 'ae0619ea3e5b86a3e382081680785f7e', 'el-icon-user-solid', 'views/wxma/wxuser/index', 2, '0', '0', '2020-02-15 21:43:51', '2020-02-15 22:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1228676665992794113', '小程序用户列表', 'wxmp:wxuser:index', '', '1228676296659161089', '', '', 0, '0', '1', '2020-02-15 21:45:19', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1228676836369616897', '小程序用户详情', 'wxmp:wxuser:get', '', '1228676296659161089', '', '', 0, '0', '1', '2020-02-15 21:46:00', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1229322873778180098', '数据统计', '', 'wxsummary', '8100000', 'el-icon-s-marketing', 'views/wxmp/wxsummary/index', 20, '0', '0', '2020-02-17 16:33:07', '2020-02-17 16:33:07', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1229323003373785090', '数据统计列表', 'wxmp:wxsummary:index', '', '1229322873778180098', '', '', 0, '0', '1', '2020-02-17 16:33:38', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1229675965086429186', '微信模块', '', '/weixin', '0', 'icon-weixin', 'Layout', 20, '0', '0', '2020-02-18 15:56:11', '2021-07-31 21:26:31', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1231205330852978690', '菜单分组列表', 'wxmp:wxmenurule:index', '', '8140000', '', '', 0, '0', '1', '2020-02-22 21:13:20', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1233957113861967874', '服务监控', '', 'server', '1227202152985784322', 'el-icon-s-marketing', 'views/upms/server/index', 30, '0', '0', '2020-03-01 11:27:56', '2020-04-26 18:42:51', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1233957499251326978', '查询服务监控', 'sys:server:index', '', '1233957113861967874', '', '', 0, '0', '1', '2020-03-01 11:29:28', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1234086429899862018', '数据监控', '', 'druid', '1227202152985784322', 'el-icon-s-operation', 'views/upms/druid/index', 40, '0', '0', '2020-03-01 20:01:47', '2020-04-26 18:43:10', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239766266052329473', '拼团管理', '', 'groupon', '8000000', 'el-icon-s-management', 'Layout', 80, '0', '0', '2020-03-17 12:11:26', '2021-03-29 14:31:21', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239766777090523137', '拼团配置', '', 'grouponinfo', '1239766266052329473', 'el-icon-s-tools', 'views/mall/grouponinfo/index', 1, '0', '0', '2020-03-17 12:13:28', '2020-03-17 12:13:28', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239766958087323650', '拼团列表', 'mall:grouponinfo:index', '', '1239766777090523137', '', '', 0, '0', '1', '2020-03-17 12:14:11', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239767051196678145', '拼团查询', 'mall:grouponinfo:get', '', '1239766777090523137', '', '', 0, '0', '1', '2020-03-17 12:14:33', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239767117328269313', '拼团新增', 'mall:grouponinfo:add', '', '1239766777090523137', '', '', 0, '0', '1', '2020-03-17 12:14:49', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239767195036139522', '拼团修改', 'mall:grouponinfo:edit', '', '1239766777090523137', '', '', 0, '0', '1', '2020-03-17 12:15:07', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239767264883884034', '拼团删除', 'mall:grouponinfo:del', '', '1239766777090523137', '', '', 0, '0', '1', '2020-03-17 12:15:24', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239767506106695682', '拼团记录', '', 'grouponuser', '1239766266052329473', 'el-icon-s-order', 'views/mall/grouponuser/index', 20, '0', '0', '2020-03-17 12:16:21', '2020-03-17 12:16:21', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1239767627204640770', '拼团记录列表', 'mall:grouponuser:index', '', '1239767506106695682', '', '', 0, '0', '1', '2020-03-17 12:16:50', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1243832970918268930', '登录日志', '', 'loglogin', '1000000', 'el-icon-info', 'views/upms/loglogin/index', 4, '0', '0', '2020-03-28 17:31:04', '2021-10-29 19:28:46', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1243833202171219970', '登录日志列表', 'sys:loglogin:index', '', '1243832970918268930', '', '', 0, '0', '1', '2020-03-28 17:31:59', '2020-03-28 17:31:59', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1243869063717883905', '登录日志查询', 'sys:loglogin:get', '', '1243832970918268930', '', '', 0, '0', '1', '2020-03-28 19:54:29', '2020-03-28 19:54:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1250724694023069698', '订阅消息模板', '', 'template', 'ae0619ea3e5b86a3e382081680785f7e', 'el-icon-s-comment', 'views/wxma/wxtemplatemsg/index', 30, '0', '0', '2020-04-16 17:56:19', '2020-04-16 18:53:19', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1250724859953930242', '订阅消息列表', 'wxma:wxtemplatemsg:index', '', '1250724694023069698', '', '', 0, '0', '1', '2020-04-16 17:56:58', '2020-04-16 17:57:58', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1250725031559684098', '订阅消息新增', 'wxma:wxtemplatemsg:add', '', '1250724694023069698', '', '', 0, '0', '1', '2020-04-16 17:57:39', '2020-04-16 17:57:39', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1250725307804934146', '订阅消息查询', 'wxma:wxtemplatemsg:get', '', '1250724694023069698', '', '', 0, '0', '1', '2020-04-16 17:58:45', '2020-04-16 17:58:45', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1250725417255297025', '订阅消息修改', 'wxma:wxtemplatemsg:edit', '', '1250724694023069698', '', '', 0, '0', '1', '2020-04-16 17:59:11', '2020-04-16 17:59:11', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1250725530589585409', '订阅消息删除', 'wxma:wxtemplatemsg:del', '', '1250724694023069698', '', '', 0, '0', '1', '2020-04-16 17:59:38', '2020-04-16 17:59:38', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1261169795019001858', '店铺管理', '', 'shop', '8000000', 'el-icon-s-shop', 'Layout', 10, '0', '0', '2020-05-15 13:41:25', '2021-03-29 14:29:04', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1261170252735008769', '店铺设置', '', 'info', '1261169795019001858', 'el-icon-s-tools', 'views/mall/shopinfo/index', 10, '0', '0', '2020-05-15 13:43:14', '2020-05-15 13:43:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1261170652447985665', '店铺列表', 'mall:shopinfo:index', '', '1261170252735008769', '', '', 0, '0', '1', '2020-05-15 13:44:49', '2020-05-15 13:44:49', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1261170764750475265', '店铺查询', 'mall:shopinfo:get', '', '1261170252735008769', '', '', 0, '0', '1', '2020-05-15 13:45:16', '2020-05-15 13:45:16', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1261170872766386178', '店铺新增', 'mall:shopinfo:add', '', '1261170252735008769', '', '', 0, '0', '1', '2020-05-15 13:45:42', '2020-05-15 13:45:42', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1261174275395506178', '店铺修改', 'mall:shopinfo:edit', '', '1261170252735008769', '', '', 0, '0', '1', '2020-05-15 13:59:13', '2020-05-15 13:59:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1261174360032366593', '店铺删除', 'mall:shopinfo:del', '', '1261170252735008769', '', '', 0, '0', '1', '2020-05-15 13:59:33', '2020-05-15 13:59:33', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1265900454069366785', '服务商收款配置', '', 'payconfig', '1000000', 'el-icon-s-management', 'views/payapi/payconfig/index', 50, '0', '0', '2020-05-28 14:59:22', '2021-10-30 19:34:26', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1265900671623720961', '添加收款账号', 'payapi:payconfig:add', '', '1265900454069366785', '', '', 0, '0', '1', '2020-05-28 15:00:14', '2020-05-29 17:21:25', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1265900771255218178', '收款账号列表', 'payapi:payconfig:index', '', '1265900454069366785', '', '', 0, '0', '1', '2020-05-28 15:00:37', '2020-05-29 17:21:33', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1265900866067460098', '收款账号查询', 'payapi:payconfig:get', '', '1265900454069366785', '', '', 0, '0', '1', '2020-05-28 15:01:00', '2020-05-29 17:21:41', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1265900951765479426', '收款账号修改', 'payapi:payconfig:edit', '', '1265900454069366785', '', '', 0, '0', '1', '2020-05-28 15:01:20', '2020-05-29 17:21:47', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1265901033235640321', '收款账号删除', 'payapi:payconfig:del', '', '1265900454069366785', '', '', 0, '0', '1', '2020-05-28 15:01:40', '2020-05-29 17:21:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293360350588104706', '秒杀管理', '', 'seckill', '8000000', 'el-icon-goods', 'Layout', 90, '0', '0', '2020-08-12 09:35:11', '2021-03-29 14:32:01', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293360792097320962', '秒杀会场', '', 'seckillhall', '1293360350588104706', 'el-icon-s-management', 'views/mall/seckillhall/index', 20, '0', '0', '2020-08-12 09:36:56', '2020-08-12 09:36:56', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293360954580463618', '秒杀会场新增', 'mall:seckillhall:add', '', '1293360792097320962', '', '', 0, '0', '1', '2020-08-12 09:37:35', '2020-08-12 09:37:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293361105973866497', '秒杀会场修改', 'mall:seckillhall:edit', '', '1293360792097320962', '', '', 0, '0', '1', '2020-08-12 09:38:11', '2020-08-12 09:38:11', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293361232570544129', '秒杀会场删除', 'mall:seckillhall:del', '', '1293360792097320962', '', '', 0, '0', '1', '2020-08-12 09:38:41', '2020-08-12 09:38:41', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293361400409812994', '秒杀会场列表', 'mall:seckillhall:index', '', '1293360792097320962', '', '', 0, '0', '1', '2020-08-12 09:39:22', '2020-08-12 09:39:22', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293361554047168513', '秒杀会场查询', 'mall:seckillhall:get', '', '1293360792097320962', '', '', 0, '0', '1', '2020-08-12 09:39:58', '2020-08-12 09:39:58', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293383822223667201', '秒杀商品', '', 'seckillinfo', '1293360350588104706', 'el-icon-s-cooperation', 'views/mall/seckillinfo/index', 10, '0', '0', '2020-08-12 11:08:27', '2020-08-12 11:08:27', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293384016864538626', '秒杀商品列表', 'mall:seckillinfo:index', '', '1293383822223667201', '', '', 0, '0', '1', '2020-08-12 11:09:14', '2020-08-12 11:09:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293384142387474433', '秒杀商品查询', 'mall:seckillinfo:get', '', '1293383822223667201', '', '', 0, '0', '1', '2020-08-12 11:09:44', '2020-08-12 11:09:44', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293384241469517825', '秒杀商品新增', 'mall:seckillinfo:add', '', '1293383822223667201', '', '', 0, '0', '1', '2020-08-12 11:10:07', '2020-08-12 11:10:07', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293384332062289922', '秒杀商品修改', 'mall:seckillinfo:edit', '', '1293383822223667201', '', '', 0, '0', '1', '2020-08-12 11:10:29', '2020-08-12 11:10:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1293384409782743041', '秒杀商品删除', 'mall:seckillinfo:del', '', '1293383822223667201', '', '', 0, '0', '1', '2020-08-12 11:10:47', '2020-08-12 11:10:47', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298426964249427969', '店铺商品分类', '', 'goodscategoryshop', '8500000', 'el-icon-s-cooperation', 'views/mall/goodscategoryshop/index', 40, '0', '0', '2020-08-26 09:08:06', '2020-08-26 09:08:06', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298427283679232001', '店铺商品分类列表', 'mall:goodscategoryshop:index', '', '1298426964249427969', '', '', 0, '0', '1', '2020-08-26 09:09:22', '2020-08-26 09:09:22', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298427420728115202', '店铺商品分类表查询', 'mall:goodscategoryshop:get', '', '1298426964249427969', '', '', 0, '0', '1', '2020-08-26 09:09:55', '2020-08-26 09:09:55', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298427594900783106', '店铺商品分类表新增', 'mall:goodscategoryshop:add', '', '1298426964249427969', '', '', 0, '0', '1', '2020-08-26 09:10:36', '2020-08-26 09:10:36', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298427668183662594', '店铺商品分类表修改', 'mall:goodscategoryshop:edit', '', '1298426964249427969', '', '', 0, '0', '1', '2020-08-26 09:10:54', '2020-08-26 09:10:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298427749360222210', '店铺商品分类表删除', 'mall:goodscategoryshop:del', '', '1298426964249427969', '', '', 0, '0', '1', '2020-08-26 09:11:13', '2020-08-26 09:11:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298809898521346049', '支付进件申请单列表', 'payapi:payapplyform:index', '', '1261170252735008769', '', '', 0, '0', '1', '2020-08-27 10:29:45', '2020-08-27 10:29:45', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298809978259259393', '支付进件申请单查询', 'payapi:payapplyform:get', '', '1261170252735008769', '', '', 1, '0', '1', '2020-08-27 10:30:04', '2020-08-27 10:30:04', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298810074178797570', '支付进件申请单新增', 'payapi:payapplyform:add', '', '1261170252735008769', '', '', 1, '0', '1', '2020-08-27 10:30:27', '2020-08-27 10:30:27', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298810151446265857', '支付进件申请单修改', 'payapi:payapplyform:edit', '', '1261170252735008769', '', '', 1, '0', '1', '2020-08-27 10:30:45', '2020-08-27 10:30:45', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298810233528795138', '支付进件申请单删除', 'payapi:payapplyform:del', '', '1261170252735008769', '', '', 1, '0', '1', '2020-08-27 10:31:05', '2020-08-27 10:31:05', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1298980277709717506', '支付进件申请单提交', 'payapi:payapplyform:submit', '', '1261170252735008769', '', '', 1, '0', '1', '2020-08-27 21:46:46', '2020-08-27 21:46:46', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('129a20e268846e9f03e7c7317d34763a', '明细修改', 'mall:pointsrecord:edit', NULL, '04c920605926e47982b7ede7fa5c3ec6', NULL, NULL, 1, '0', '1', '2019-12-11 11:30:23', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1300000', '角色管理', NULL, 'role', '1000000', 'icon-jiaose', 'views/upms/role/index', 2, '0', '0', '2017-11-08 10:13:37', '2020-04-26 18:42:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1301000', '角色新增', 'sys:role:add', NULL, '1300000', NULL, NULL, 1, '0', '1', '2017-11-08 10:14:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1302000', '角色修改', 'sys:role:edit', NULL, '1300000', NULL, NULL, 1, '0', '1', '2017-11-08 10:14:41', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1303000', '角色删除', 'sys:role:del', NULL, '1300000', NULL, NULL, 1, '0', '1', '2017-11-08 10:14:59', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1304000', '角色详情', 'sys:role:get', NULL, '1300000', NULL, NULL, 1, '0', '1', '2019-07-27 11:43:06', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1305000', '分配权限', 'sys:role:perm', NULL, '1300000', NULL, NULL, 1, '0', '1', '2018-04-20 07:22:55', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308349941648834562', '商城装修', '', 'decorate', '8000000', 'el-icon-s-shop', 'Layout', 105, '0', '0', '2020-09-22 18:18:28', '2021-05-31 14:52:26', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308351154087903234', '主题配置', '', 'thememobile', '1308349941648834562', 'el-icon-s-flag', 'views/mall/thememobile/form', 10, '0', '0', '2020-09-22 18:23:17', '2020-09-22 18:23:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308353287508049922', '主题配置查询', 'mall:thememobile:get', '', '1308351154087903234', '', '', 0, '0', '1', '2020-09-22 18:31:46', '2020-09-22 18:31:46', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308353386212605954', '主题配置', 'mall:thememobile:edit', '', '1308351154087903234', '', '', 0, '0', '1', '2020-09-22 18:32:09', '2020-09-22 18:32:09', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308364923052605441', '首页装修', '', 'home', '1308349941648834562', 'el-icon-s-home', 'views/mall/pagedevise/home/index', 20, '0', '0', '2020-09-22 19:18:00', '2020-09-22 19:18:00', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308365472401571842', '首页装修列表', 'mall:pagedevise:index', '', '1308364923052605441', '', '', 0, '0', '1', '2020-09-22 19:20:11', '2020-09-22 19:20:11', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308365991039844354', '首页装修查询', 'mall:pagedevise:get', '', '1308364923052605441', '', '', 0, '0', '1', '2020-09-22 19:22:15', '2020-09-22 19:22:15', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308366119851114498', '首页装修新增', 'mall:pagedevise:add', '', '1308364923052605441', '', '', 0, '0', '1', '2020-09-22 19:22:45', '2020-09-22 19:22:45', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308366209860878337', '首页装修修改', 'mall:pagedevise:edit', '', '1308364923052605441', '', '', 0, '0', '1', '2020-09-22 19:23:07', '2020-09-22 19:23:07', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1308366313216917505', '首页装修删除', 'mall:pagedevise:del', '', '1308364923052605441', '', '', 0, '0', '1', '2020-09-22 19:23:32', '2020-09-22 19:23:32', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1309098073269506049', '店员管理', '', 'shopuser', '1261169795019001858', 'el-icon-user-solid', 'views/mall/shopuser/index', 20, '0', '0', '2020-09-24 19:51:17', '2020-09-24 19:51:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1309113406327906305', '店员列表', 'mall:shopuser:index', '', '1309098073269506049', '', '', 0, '0', '1', '2020-09-24 20:52:12', '2020-09-24 20:52:12', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1309113499143659521', '店员查询', 'mall:shopuser:get', '', '1309098073269506049', '', '', 0, '0', '1', '2020-09-24 20:52:35', '2020-09-24 20:52:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1309113662792818689', '店员新增', 'mall:shopuser:add', '', '1309098073269506049', '', '', 0, '0', '1', '2020-09-24 20:53:14', '2020-09-24 20:53:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1309113748939628546', '店员修改', 'mall:shopuser:edit', '', '1309098073269506049', '', '', 0, '0', '1', '2020-09-24 20:53:34', '2020-09-24 20:53:34', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1309113826735579138', '店员删除', 'mall:shopuser:del', '', '1309098073269506049', '', '', 0, '0', '1', '2020-09-24 20:53:53', '2020-09-24 20:53:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1309124414236835842', '店员密码修改', 'sys:user:password', '', '1309098073269506049', '', '', 0, '0', '1', '2020-09-24 21:35:57', '2020-09-24 21:36:55', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1320352206834208769', 'job定时任务', '', 'job', '1227202152985784322', 'el-icon-video-camera-solid', 'views/upms/job/index', 50, '0', '0', '2020-10-25 21:11:11', '2020-10-25 21:11:11', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1321281973857124354', '店铺入驻', '', 'shopapply', '1261169795019001858', 'el-icon-s-flag', 'views/mall/shopapply/index', 30, '0', '0', '2020-10-28 10:45:45', '2020-10-28 10:45:45', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1321283875755884546', '店铺入驻申请列表', 'mall:shopapply:index', '', '1321281973857124354', '', '', 0, '0', '1', '2020-10-28 10:53:18', '2020-10-30 15:19:57', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1321284014893531137', '店铺入驻申请查询', 'mall:shopapply:get', '', '1321281973857124354', '', '', 0, '0', '1', '2020-10-28 10:53:52', '2020-10-28 10:53:52', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1321284106564239361', '店铺入驻申请新增', 'mall:shopapply:add', '', '1321281973857124354', '', '', 0, '0', '1', '2020-10-28 10:54:13', '2020-10-28 10:54:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1321284197328977921', '店铺入驻申请修改', 'mall:shopapply:edit', '', '1321281973857124354', '', '', 0, '0', '1', '2020-10-28 10:54:35', '2020-10-28 10:54:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1321284312139661314', '店铺入驻申请删除', 'mall:shopapply:del', '', '1321281973857124354', '', '', 0, '0', '1', '2020-10-28 10:55:02', '2020-10-28 10:55:02', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1322075855138095105', '店铺入驻申请审核', 'mall:shopapply:verify', '', '1321281973857124354', '', '', 0, '0', '1', '2020-10-30 15:20:21', '2020-10-30 15:20:21', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331123534564425730', '文章管理', '', 'article', '8000000', 'el-icon-s-management', 'Layout', 95, '0', '0', '2020-11-24 14:32:36', '2021-03-29 14:32:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331123875771056130', '文章分类', '', 'articlecategory', '1331123534564425730', 'el-icon-s-operation', 'views/mall/articlecategory/index', 10, '0', '0', '2020-11-24 14:33:57', '2020-11-24 14:33:57', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331124222807769090', '文章分类列表', 'mall:articlecategory:index', '', '1331123875771056130', '', '', 0, '0', '1', '2020-11-24 14:35:20', '2020-11-24 14:35:20', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331124349102456833', '文章分类查询', 'mall:articlecategory:get', '', '1331123875771056130', '', '', 0, '0', '1', '2020-11-24 14:35:50', '2020-11-24 14:35:50', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331124445831495681', '文章分类新增', 'mall:articlecategory:add', '', '1331123875771056130', '', '', 0, '0', '1', '2020-11-24 14:36:13', '2020-11-24 14:36:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331124539226062849', '文章分类修改', 'mall:articlecategory:edit', '', '1331123875771056130', '', '', 0, '0', '1', '2020-11-24 14:36:35', '2020-11-24 14:36:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331124631018405890', '文章分类删除', 'mall:articlecategory:del', '', '1331123875771056130', '', '', 0, '0', '1', '2020-11-24 14:36:57', '2020-11-24 14:36:57', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331127314508939265', '文章内容', '', 'articleinfo', '1331123534564425730', 'el-icon-s-order', 'views/mall/articleinfo/index', 20, '0', '0', '2020-11-24 14:47:37', '2020-11-24 14:47:37', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331127770438172674', '文章列表', 'mall:articleinfo:index', '', '1331127314508939265', '', '', 0, '0', '1', '2020-11-24 14:49:26', '2020-11-24 14:49:26', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331127886733639681', '文章查询', 'mall:articleinfo:get', '', '1331127314508939265', '', '', 0, '0', '1', '2020-11-24 14:49:54', '2020-11-24 14:49:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331127967310413825', '文章新增', 'mall:articleinfo:add', '', '1331127314508939265', '', '', 0, '0', '1', '2020-11-24 14:50:13', '2020-11-24 14:50:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331128039997702145', '文章修改', 'mall:articleinfo:edit', '', '1331127314508939265', '', '', 0, '0', '1', '2020-11-24 14:50:30', '2020-11-24 14:50:30', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1331128115079938050', '文章删除', 'mall:articleinfo:del', '', '1331127314508939265', '', '', 0, '0', '1', '2020-11-24 14:50:48', '2020-11-24 14:50:48', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342074455276478465', '缓存监控', '', 'cache', '1227202152985784322', 'el-icon-upload', 'views/upms/cache/index', 35, '0', '0', '2020-12-24 19:47:39', '2021-08-21 10:20:34', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342074603792588801', '缓存查询', 'sys:cache:index', '', '1342074455276478465', '', '', 0, '0', '1', '2020-12-24 19:48:14', '2020-12-24 20:10:12', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342089211811028994', '商城用户', '', 'user', '8000000', 'el-icon-user-solid', 'Layout', 30, '0', '0', '2020-12-24 20:46:17', '2021-03-29 14:30:40', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342093572213661698', '商品收藏', '', 'goods', '1342089211811028994', 'el-icon-s-goods', 'views/mall/usercollect/goods', 20, '0', '0', '2020-12-24 21:03:37', '2020-12-24 21:03:37', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342093729722359809', '店铺收藏', '', 'shop', '1342089211811028994', 'el-icon-s-shop', 'views/mall/usercollect/shop', 30, '0', '0', '2020-12-24 21:04:14', '2020-12-24 21:04:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342093905342062593', '商品收藏列表', 'mall:usercollect:index', '', '1342093572213661698', '', '', 0, '0', '1', '2020-12-24 21:04:56', '2020-12-24 21:04:56', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342093992382259201', '店铺收藏列表', 'mall:usercollect:index', '', '1342093729722359809', '', '', 0, '0', '1', '2020-12-24 21:05:17', '2020-12-24 21:05:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342112579171999746', '会员足迹', '', 'userfootprint', '1342089211811028994', 'el-icon-s-marketing', 'views/mall/userfootprint/index', 40, '0', '0', '2020-12-24 22:19:08', '2020-12-24 22:19:08', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342112864309174273', '足迹列表', 'mall:userfootprint:index', '', '1342112579171999746', '', '', 0, '0', '1', '2020-12-24 22:20:16', '2020-12-24 22:20:16', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342368489077252098', '直播管理', '', 'wxlive', 'ae0619ea3e5b86a3e382081680785f7e', 'el-icon-video-camera-solid', 'views/wxma/wxlive/index', 40, '0', '0', '2020-12-25 15:16:02', '2020-12-25 15:16:02', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342368718203691010', '直播列表', 'wxma:wxmalive:index', '', '1342368489077252098', '', '', 0, '0', '1', '2020-12-25 15:16:57', '2020-12-25 15:16:57', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342369468602425345', '小程序直播新增', 'wxma:wxmalive:add', '', '1342368489077252098', '', '', 0, '0', '1', '2020-12-25 15:19:56', '2020-12-25 15:19:56', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342369546528399362', '小程序直播修改', 'wxma:wxmalive:edit', '', '1342368489077252098', '', '', 0, '0', '1', '2020-12-25 15:20:14', '2020-12-25 15:20:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1342369625242902530', '小程序直播删除', 'wxma:wxmalive:del', '', '1342368489077252098', '', '', 0, '0', '1', '2020-12-25 15:20:33', '2020-12-25 15:20:33', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1346469866533576705', '店铺装修列表', 'mall:pagedevise2:index', '', '1261170252735008769', '', '', 0, '0', '1', '2021-01-05 22:53:27', '2021-03-29 17:46:38', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1346470006900154370', '店铺装修查询', 'mall:pagedevise2:get', '', '1261170252735008769', '', '', 0, '0', '1', '2021-01-05 22:54:00', '2021-01-05 22:54:00', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1346470119282335746', '店铺装修新增', 'mall:pagedevise2:add', '', '1261170252735008769', '', '', 0, '0', '1', '2021-01-05 22:54:27', '2021-01-05 22:54:27', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1346470207647932417', '店铺装修修改', 'mall:pagedevise2:edit', '', '1261170252735008769', '', '', 0, '0', '1', '2021-01-05 22:54:48', '2021-01-05 22:54:48', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1346470309233975298', '店铺装修删除', 'mall:pagedevise2:del', '', '1261170252735008769', '', '', 0, '0', '1', '2021-01-05 22:55:12', '2021-01-05 22:55:12', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349244303388487681', '签到设置', '', 'signconfig', '01e9e720e3eedce613a297dcdddb3d63', 'el-icon-picture-outline-round', 'views/mall/signconfig/index', 30, '0', '0', '2021-01-13 14:38:04', '2021-01-13 14:38:04', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349244680460611585', '签到设置分页列表', 'mall:signconfig:index', '', '1349244303388487681', '', '', 0, '0', '1', '2021-01-13 14:39:34', '2021-01-13 14:39:34', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349244822907564034', '签到设置查询', 'mall:signconfig:get', '', '1349244303388487681', '', '', 0, '0', '1', '2021-01-13 14:40:08', '2021-01-13 14:40:08', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349245153523576834', '签到设置新增', 'mall:signconfig:add', '', '1349244303388487681', '', '', 0, '0', '1', '2021-01-13 14:41:27', '2021-01-13 14:41:27', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349245241826258946', '签到设置修改', 'mall:signconfig:edit', '', '1349244303388487681', '', '', 0, '0', '1', '2021-01-13 14:41:48', '2021-01-13 14:41:48', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349245331873771521', '签到设置删除', 'mall:signconfig:del', '', '1349244303388487681', '', '', 0, '0', '1', '2021-01-13 14:42:09', '2021-01-13 14:42:09', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349258652614176770', '签到统计', '', 'signrecord', '01e9e720e3eedce613a297dcdddb3d63', 'el-icon-s-data', 'views/mall/signrecord/index', 40, '0', '0', '2021-01-13 15:35:05', '2021-01-13 15:35:05', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349258902200430594', '签到记录列表', 'mall:signrecord:index', '', '1349258652614176770', '', '', 0, '0', '1', '2021-01-13 15:36:04', '2021-01-13 15:36:04', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1349259096077938689', '签到记录查询', 'mall:signrecord:get', '', '1349258652614176770', '', '', 0, '0', '1', '2021-01-13 15:36:51', '2021-01-13 15:36:51', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1350792832623841281', '应用监控', '', 'bootadmin', '1227202152985784322', 'el-icon-s-marketing', 'views/upms/bootadmin/index', 70, '0', '0', '2021-01-17 21:11:22', '2021-01-20 13:44:18', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1351564537773465602', 'Sentinel流量监控', '', 'sentinel', '1227202152985784322', 'el-icon-camera-solid', 'views/upms/sentinel/index', 80, '0', '0', '2021-01-20 00:17:51', '2021-03-19 20:19:26', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1355900128047747074', '小程度代码管理', 'wxma:code:index', '', 'a96328161a39450238e8cafa243b7c35', '', '', 0, '0', '1', '2021-01-31 23:25:56', '2021-01-31 23:31:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1358436360011132929', '小程序服务器域名管理', 'wxma:serveraddress:index', '', 'a96328161a39450238e8cafa243b7c35', '', '', 0, '0', '1', '2021-02-07 23:24:01', '2021-02-07 23:24:01', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1372090280218505217', '直播商品管理', '', 'wxlivegoods', 'ae0619ea3e5b86a3e382081680785f7e', 'el-icon-s-goods', 'views/wxma/wxlivegoods/index', 50, '0', '0', '2021-03-17 15:39:49', '2021-03-17 15:40:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1372090492391567361', '直播商品列表', 'wxma:wxmalivegoods:index', '', '1372090280218505217', '', '', 0, '0', '1', '2021-03-17 15:40:40', '2021-03-17 16:27:26', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1372101740151746561', '直播商品新增', 'wxma:wxmalivegoods:add', '', '1372090280218505217', '', '', 0, '0', '1', '2021-03-17 16:25:21', '2021-03-17 16:25:21', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1372101887707361282', '直播商品修改', 'wxma:wxmalivegoods:edit', '', '1372090280218505217', '', '', 0, '0', '1', '2021-03-17 16:25:57', '2021-03-17 16:25:57', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1372102024953376769', '直播商品删除', 'wxma:wxmalivegoods:del', '', '1372090280218505217', '', '', 0, '0', '1', '2021-03-17 16:26:29', '2021-03-17 16:26:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1372102231694815234', '直播商品查询', 'wxma:wxmalivegoods:get', '', '1372090280218505217', '', '', 0, '0', '1', '2021-03-17 16:27:19', '2021-03-17 16:27:19', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1372888102607085569', 'nacos配置中心', '', 'nacos', '1227202152985784322', 'el-icon-s-order', 'views/upms/nacos/index', 90, '0', '0', '2021-03-19 20:30:05', '2021-03-19 20:30:05', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1376421493427228674', '店铺用户', '', 'usershop', '8000000', 'el-icon-user', 'Layout', 20, '0', '0', '2021-03-29 14:30:31', '2021-03-29 14:30:31', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1376422486076379138', '店铺用户', '', 'usershop', '1376421493427228674', 'el-icon-user-solid', 'views/mall/usershop/index', 10, '0', '0', '2021-03-29 14:34:28', '2021-03-29 14:34:28', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1376422678422966274', '店铺用户列表', 'mall:usershop:index', '', '1376422486076379138', '', '', 0, '0', '1', '2021-03-29 14:35:13', '2021-03-29 14:35:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1376422796756865025', '店铺用户查询', 'mall:usershop:get', '', '1376422486076379138', '', '', 0, '0', '1', '2021-03-29 14:35:42', '2021-03-29 14:35:42', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1376422875219709954', '店铺用户新增', 'mall:usershop:add', '', '1376422486076379138', '', '', 0, '0', '1', '2021-03-29 14:36:00', '2021-03-29 14:36:00', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1376422957583257601', '店铺用户修改', 'mall:usershop:edit', '', '1376422486076379138', '', '', 0, '0', '1', '2021-03-29 14:36:20', '2021-03-29 14:36:20', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1376423034070585346', '店铺用户删除', 'mall:usershop:del', '', '1376422486076379138', '', '', 0, '0', '1', '2021-03-29 14:36:38', '2021-03-29 14:36:38', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1377094310065950721', '商品审核', 'mall:goodsspu:verify', '', '8300000', '', '', 0, '0', '1', '2021-03-31 11:04:03', '2021-03-31 11:04:03', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1381872508842598401', '分销管理', '', 'distribution', '8000000', 'el-icon-share', 'Layout', 93, '0', '0', '2021-04-13 15:30:54', '2021-04-13 15:39:02', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1381872757216698369', '分销设置', '', 'distributionconfig', '1381872508842598401', 'el-icon-s-tools', 'views/mall/distributionconfig/form', 10, '0', '0', '2021-04-13 15:31:53', '2021-04-13 15:31:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1381872968177606657', '分销查询', 'mall:distributionconfig:get', '', '1381872757216698369', '', '', 0, '0', '1', '2021-04-13 15:32:44', '2021-04-13 15:32:44', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1381873672174755842', '分销修改', 'mall:distributionconfig:edit', '', '1381872757216698369', '', '', 0, '0', '1', '2021-04-13 15:35:32', '2021-04-13 15:35:32', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1386255289156673537', '分销员管理', '', 'distributionuser', '1381872508842598401', 'el-icon-user-solid', 'views/mall/distributionuser/index', 20, '0', '0', '2021-04-25 17:46:30', '2021-04-25 17:46:30', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1386255440021594113', '分销员列表', 'mall:distributionuser:index', '', '1386255289156673537', '', '', 0, '0', '1', '2021-04-25 17:47:06', '2021-04-25 17:47:06', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1386255544229076993', '分销员查询', 'mall:distributionuser:get', '', '1386255289156673537', '', '', 0, '0', '1', '2021-04-25 17:47:31', '2021-04-25 17:47:31', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1386255636726063106', '分销员新增', 'mall:distributionuser:add', '', '1386255289156673537', '', '', 0, '0', '1', '2021-04-25 17:47:53', '2021-04-25 17:47:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1386255721815908354', '分销员修改', 'mall:distributionuser:edit', '', '1386255289156673537', '', '', 0, '0', '1', '2021-04-25 17:48:14', '2021-04-25 17:48:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1386255797153996801', '分销员删除', 'mall:distributionuser:del', '', '1386255289156673537', '', '', 0, '0', '1', '2021-04-25 17:48:32', '2021-04-25 17:48:32', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1391640356498513922', '财务管理', '', 'finance', '8000000', 'el-icon-s-platform', 'Layout', 110, '0', '0', '2021-05-10 14:24:51', '2021-05-10 14:24:51', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1391640959698149378', '用户提现记录', '', 'userwithdrawrecord', '1391640356498513922', 'el-icon-s-cooperation', 'views/mall/userwithdrawrecord/index', 10, '0', '0', '2021-05-10 14:27:14', '2021-05-10 14:27:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1391641292511977473', '用户提现记录列表', 'mall:userwithdrawrecord:index', '', '1391640959698149378', '', '', 0, '0', '1', '2021-05-10 14:28:34', '2021-05-10 14:29:04', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1391641504399826945', '用户提现记录查询', 'mall:userwithdrawrecord:get', '', '1391640959698149378', '', '', 0, '0', '1', '2021-05-10 14:29:24', '2021-05-10 14:31:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1391641564550340609', '用户提现记录新增', 'mall:userwithdrawrecord:add', '', '1391640959698149378', '', '', 0, '0', '1', '2021-05-10 14:29:39', '2021-05-10 14:31:30', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1391641697975345154', '用户提现记录修改', 'mall:userwithdrawrecord:edit', '', '1391640959698149378', '', '', 0, '0', '1', '2021-05-10 14:30:10', '2021-05-10 14:30:10', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1391641880649867266', '用户提现记录删除', 'mall:userwithdrawrecord:del', '', '1391640959698149378', '', '', 0, '0', '1', '2021-05-10 14:30:54', '2021-05-10 14:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1400000', '机构管理', NULL, 'organ', '1000000', 'icon-bumen', 'views/upms/organ/index', 3, '0', '0', '2018-01-20 13:17:19', '2020-04-26 18:42:56', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1401000', '机构新增', 'sys:organ:add', NULL, '1400000', NULL, NULL, 1, '0', '1', '2018-01-20 14:56:16', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1402000', '机构修改', 'sys:organ:edit', NULL, '1400000', NULL, NULL, 1, '0', '1', '2018-01-20 14:56:59', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1403000', '机构删除', 'sys:organ:del', NULL, '1400000', NULL, NULL, 1, '0', '1', '2018-01-20 14:57:28', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1404000', '机构详情', 'sys:organ:get', NULL, '1400000', NULL, NULL, 1, '0', '1', '2019-07-27 11:31:26', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1420641504689475586', '极光配置', '', 'jiguangconfig', '1454042553678036993', 'el-icon-s-promotion', 'views/mall/jiguangconfig/form', 70, '0', '0', '2021-07-29 15:05:03', '2021-10-29 19:10:04', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1420641654505820161', '极光配置查询', 'mall:jiguangconfig:get', '', '1420641504689475586', '', '', 0, '0', '1', '2021-07-29 15:05:39', '2021-07-29 15:05:39', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1420641756330938370', '极光配置修改', 'mall:jiguangconfig:edit', '', '1420641504689475586', '', '', 0, '0', '1', '2021-07-29 15:06:03', '2021-07-29 15:06:03', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421313332319657985', '后台第三方登录配置', '', 'thirdpartyconfig', '1454042553678036993', 'el-icon-s-data', 'views/upms/thirdpartyconfig/form', 40, '0', '0', '2021-07-31 11:34:39', '2021-10-31 14:57:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421313416033771522', '第三方登录配置查询', 'sys:thirdpartyconfig:get', '', '1421313332319657985', '', '', 0, '0', '1', '2021-07-31 11:34:59', '2021-07-31 11:34:59', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421313667629166593', '第三方登录配置修改', 'sys:thirdpartyconfig:edit', '', '1421313332319657985', '', '', 0, '0', '1', '2021-07-31 11:35:59', '2021-07-31 11:35:59', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421320896126234626', '商城配置', '', 'mallconfig', '1454042553678036993', 'el-icon-setting', 'views/mall/mallconfig/form', 80, '0', '0', '2021-07-31 12:04:43', '2021-10-29 19:11:09', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421320981740367874', '商城配置查询', 'mall:mallconfig:get', '', '1421320896126234626', '', '', 0, '0', '1', '2021-07-31 12:05:03', '2021-07-31 12:05:03', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421321068310802433', '商城配置修改', 'mall:mallconfig:edit', '', '1421320896126234626', '', '', 0, '0', '1', '2021-07-31 12:05:24', '2021-07-31 12:05:24', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421471312252047361', '阿里短信配置', '', 'smsconfig', '1454042553678036993', 'el-icon-s-comment', 'views/upms/smsconfig/form', 50, '0', '0', '2021-07-31 22:02:25', '2021-10-29 19:13:25', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421471400907051009', '阿里短信配置查询', 'sys:smsconfig:get', '', '1421471312252047361', '', '', 0, '0', '1', '2021-07-31 22:02:46', '2021-07-31 22:02:46', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421471501620678657', '阿里短信配置修改', 'sys:smsconfig:edit', '', '1421471312252047361', '', '', 0, '0', '1', '2021-07-31 22:03:10', '2021-07-31 22:03:10', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421474975628472321', '邮箱配置', '', 'emailconfig', '1454042553678036993', 'el-icon-s-ticket', 'views/upms/emailconfig/form', 60, '0', '0', '2021-07-31 22:16:58', '2021-10-29 19:13:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421475074509189121', '邮箱配置查询', 'sys:emailconfig:get', '', '1421474975628472321', '', '', 0, '0', '1', '2021-07-31 22:17:22', '2021-07-31 22:17:22', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421475157585768449', '邮箱配置修改', 'sys:emailconfig:edit', '', '1421474975628472321', '', '', 0, '0', '1', '2021-07-31 22:17:41', '2021-07-31 22:17:41', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421480464693137410', '微信第三方平台配置', '', 'wxcomponentconfig', '1454042553678036993', 'el-icon-setting', 'views/wxcomponent/wxcomponentconfig/form', 30, '0', '0', '2021-07-31 22:38:47', '2021-10-31 14:58:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421480612496216066', '微信第三方平台配置查询', 'wx:wxcomponentconfig:get', '', '1421480464693137410', '', '', 0, '0', '1', '2021-07-31 22:39:22', '2021-07-31 22:39:22', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1421480719023149058', '微信第三方平台配置修改', 'wx:wxcomponentconfig:edit', '', '1421480464693137410', '', '', 0, '0', '1', '2021-07-31 22:39:47', '2021-07-31 22:39:47', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1452888291086512130', '模板消息', '', 'wxtemplatemsg', '8100000', 'el-icon-bell', 'views/wxmp/wxtemplatemsg/index', 30, '0', '0', '2021-10-26 14:42:16', '2021-10-26 14:42:16', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1452888781744582657', '模板消息列表', 'wxmp:wxtemplatemsg:index', '', '1452888291086512130', '', '', 0, '0', '1', '2021-10-26 14:44:13', '2021-10-26 14:44:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1452888918969626626', '模板消息查询', 'wxmp:wxtemplatemsg:get', '', '1452888291086512130', '', '', 0, '0', '1', '2021-10-26 14:44:46', '2021-10-26 14:44:46', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1452889019288989698', '模板消息新增', 'wxmp:wxtemplatemsg:add', '', '1452888291086512130', '', '', 0, '0', '1', '2021-10-26 14:45:10', '2021-10-26 14:45:10', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1452889113044267010', '模板消息修改', 'wxmp:wxtemplatemsg:edit', '', '1452888291086512130', '', '', 0, '0', '1', '2021-10-26 14:45:32', '2021-10-26 14:45:32', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1452889220854657025', '模板消息删除', 'wxmp:wxtemplatemsg:del', '', '1452888291086512130', '', '', 0, '0', '1', '2021-10-26 14:45:58', '2021-10-26 14:45:58', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1453315663338254338', '发送公众号模板消息', 'wxmp:wxtemplatemsg:send', '', '1452888291086512130', '', '', 0, '0', '1', '2021-10-27 19:00:30', '2021-10-27 19:00:30', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1454042553678036993', '平台管理', '', '/platform', '0', 'el-icon-s-platform', 'Layout', 5, '0', '0', '2021-10-29 19:08:54', '2021-10-30 15:03:05', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1454652643779022850', '租户状态修改', 'sys:tenant:status', '', 'e9c150621e6bcfd7f0cea479d2ab9236', '', '', 0, '0', '1', '2021-10-31 11:33:11', '2021-10-31 11:33:11', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1454656678447812609', '基础信息管理', '', 'tenantform', '1000000', 'el-icon-s-tools', 'views/upms/tenant/form', 0, '0', '0', '2021-10-31 11:49:13', '2021-10-31 11:49:13', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1454663152586723330', '租户基础信息查询', 'sys:organ:tenant', '', '1454656678447812609', '', '', 0, '0', '1', '2021-10-31 12:14:56', '2021-10-31 13:11:39', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1454682603688378369', '租户基础信息修改', 'sys:organ:edit', '', '1454656678447812609', '', '', 0, '0', '1', '2021-10-31 13:32:14', '2021-10-31 13:32:14', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1455096953657966593', '物流公司配置', '', 'logistics', '1454042553678036993', 'el-icon-s-marketing', 'views/mall/logistics/index', 90, '0', '0', '2021-11-01 16:58:42', '2021-11-01 16:58:42', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1455097203571376129', '物流公司修改', 'mall:logistics:edit', '', '1455096953657966593', '', '', 0, '0', '1', '2021-11-01 16:59:42', '2021-11-01 16:59:42', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1455097289248423938', '物流公司新增', 'mall:logistics:add', '', '1455096953657966593', '', '', 0, '0', '1', '2021-11-01 17:00:02', '2021-11-01 17:00:02', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1455097421603880961', '物流公司删除', 'mall:logistics:del', '', '1455096953657966593', '', '', 0, '0', '1', '2021-11-01 17:00:34', '2021-11-01 17:00:34', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1455100118050619393', '物流公司列表', 'mall:logistics:index', '', '1455096953657966593', '', '', 0, '0', '1', '2021-11-01 17:11:17', '2021-11-01 17:11:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458315340261376002', '平台用户管理', '', 'user', '1458335569028857858', 'el-icon-user-solid', 'views/upms/user/index2', 10, '0', '0', '2021-11-10 14:07:26', '2021-11-11 19:07:39', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458328892766437377', '平台角色管理', '', 'role', '1458335569028857858', 'el-icon-s-cooperation', 'views/upms/role/index2', 15, '0', '0', '2021-11-10 15:01:17', '2021-11-11 19:11:59', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330074838745089', '用户新增', 'sys:user:add', '', '1458315340261376002', '', '', 0, '0', '1', '2021-11-10 15:05:58', '2021-11-10 15:05:58', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330157793689602', '用户修改', 'sys:user:edit', '', '1458315340261376002', '', '', 0, '0', '1', '2021-11-10 15:06:18', '2021-11-10 15:06:18', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330223161917441', '用户删除', 'sys:user:del', '', '1458315340261376002', '', '', 0, '0', '1', '2021-11-10 15:06:34', '2021-11-10 15:06:34', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330288127492097', '用户详情', 'sys:user:get', '', '1458315340261376002', '', '', 0, '0', '1', '2021-11-10 15:06:49', '2021-11-10 15:06:49', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330354909200386', '用户密码修改', 'sys:user:password', '', '1458315340261376002', '', '', 0, '0', '1', '2021-11-10 15:07:05', '2021-11-10 15:07:05', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330420562640897', '用户列表', 'sys:user:index', '', '1458315340261376002', '', '', 0, '0', '1', '2021-11-10 15:07:21', '2021-11-10 15:07:21', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330506537484289', '角色新增', 'sys:role:add', '', '1458328892766437377', '', '', 0, '0', '1', '2021-11-10 15:07:41', '2021-11-10 15:07:41', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330564880252930', '角色修改', 'sys:role:edit', '', '1458328892766437377', '', '', 0, '0', '1', '2021-11-10 15:07:55', '2021-11-10 15:07:55', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330657897332737', '角色删除', 'sys:role:del', '', '1458328892766437377', '', '', 0, '0', '1', '2021-11-10 15:08:17', '2021-11-10 15:08:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330746220986370', '角色详情', 'sys:role:get', '', '1458328892766437377', '', '', 0, '0', '1', '2021-11-10 15:08:39', '2021-11-10 15:08:39', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330814382620674', '分配权限', 'sys:role:perm', '', '1458328892766437377', '', '', 0, '0', '1', '2021-11-10 15:08:55', '2021-11-10 15:08:55', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458330874130481153', '角色列表', 'sys:role:index', '', '1458328892766437377', '', '', 0, '0', '1', '2021-11-10 15:09:09', '2021-11-10 15:09:09', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458332164357111810', '平台登录日志', '', 'loglogin', '1458335569028857858', 'el-icon-s-order', 'views/upms/loglogin/index', 17, '0', '0', '2021-11-10 15:14:17', '2021-11-11 19:12:07', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458332380720283650', '平台操作日志', '', 'log', '1458335569028857858', 'el-icon-s-order', 'views/upms/log/index', 18, '0', '0', '2021-11-10 15:15:08', '2021-11-11 19:12:15', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458334001260937218', '登录日志列表', 'sys:loglogin:index', '', '1458332164357111810', '', '', 0, '0', '1', '2021-11-10 15:21:35', '2021-11-10 15:21:35', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458334066188763138', '登录日志查询', 'sys:loglogin:get', '', '1458332164357111810', '', '', 0, '0', '1', '2021-11-10 15:21:50', '2021-11-10 15:21:50', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458334147696672770', '日志删除', 'sys:log:del', '', '1458332380720283650', '', '', 0, '0', '1', '2021-11-10 15:22:10', '2021-11-10 15:22:10', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458334207809437697', '日志列表', 'sys:log:index', '', '1458332380720283650', '', '', 0, '0', '1', '2021-11-10 15:22:24', '2021-11-10 15:22:24', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458334820429479938', '平台令牌管理', '', 'token', '1458335569028857858', 'el-icon-bell', 'views/upms/token/index', 19, '0', '0', '2021-11-10 15:24:50', '2021-11-11 19:12:21', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458334888670806018', '令牌删除', 'sys:token:del', '', '1458334820429479938', '', '', 0, '0', '1', '2021-11-10 15:25:06', '2021-11-10 15:25:06', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458334955809030146', '令牌列表', 'sys:token:index', '', '1458334820429479938', '', '', 0, '0', '1', '2021-11-10 15:25:22', '2021-11-10 15:25:22', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458335569028857858', '平台系统配置', '', 'auth', '1454042553678036993', 'el-icon-s-home', 'Layout', 10, '0', '0', '2021-11-10 15:27:48', '2021-11-10 15:39:56', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458338863461711873', '平台文件存储配置', '', 'configstorage', '1458335569028857858', 'el-icon-upload', 'views/upms/configstorage/form', 30, '0', '0', '2021-11-10 15:40:54', '2021-11-11 19:12:26', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458338974786928641', '文件存储配置查询', 'sys:configstorage:get', '', '1458338863461711873', '', '', 0, '0', '1', '2021-11-10 15:41:20', '2021-11-10 15:41:20', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1458339030734749698', '文件存储配置修改', 'sys:configstorage:edit', '', '1458338863461711873', '', '', 0, '0', '1', '2021-11-10 15:41:34', '2021-11-10 15:41:34', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1459139258920476673', '平台信息管理', '', 'tenantform', '1458335569028857858', 'el-icon-s-tools', 'views/upms/tenant/form', 5, '0', '0', '2021-11-12 20:41:23', '2021-11-12 20:41:23', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1459143497277341698', '平台信息查询', 'sys:organ:tenant', '', '1459139258920476673', '', '', 0, '0', '1', '2021-11-12 20:58:14', '2021-11-12 20:58:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1459143655096418305', '平台信息修改', 'sys:organ:edit', '', '1459139258920476673', '', '', 0, '0', '1', '2021-11-12 20:58:51', '2021-11-12 20:58:51', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1460504920276418561', '配置小程序用户隐私保护指引', 'wxma:privacysetting:index', '', 'a96328161a39450238e8cafa243b7c35', '', '', 0, '0', '1', '2021-11-16 15:08:02', '2021-11-16 15:08:02', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501764795530477570', '草稿箱', '', 'wxdraft', '8100000', 'el-icon-s-management', 'views/wxmp/wxdraft/index', 4, '0', '0', '2022-03-10 11:40:03', '2022-03-10 12:04:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501764915156221954', '新增草稿箱', 'wxmp:wxdraft:add', '', '1501764795530477570', '', '', 0, '0', '1', '2022-03-10 11:40:31', '2022-03-10 11:40:31', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501765024115851265', '修改草稿箱', 'wxmp:wxdraft:edit', '', '1501764795530477570', '', '', 0, '0', '1', '2022-03-10 11:40:57', '2022-03-10 11:40:57', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501765156685217793', '删除草稿箱', 'wxmp:wxdraft:del', '', '1501764795530477570', '', '', 0, '0', '1', '2022-03-10 11:41:29', '2022-03-10 11:41:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501765381776736257', '草稿箱列表', 'wxmp:wxdraft:index', '', '1501764795530477570', '', '', 0, '0', '1', '2022-03-10 11:42:23', '2022-03-10 11:42:23', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501851134011637761', '已发布', '', 'wxfreepublish', '8100000', 'el-icon-camera-solid', 'views/wxmp/wxfreepublish/index', 4, '0', '0', '2022-03-10 17:23:08', '2022-03-10 17:23:08', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501851272989900802', '成功发布列表', 'wxmp:wxfreepublish:index', '', '1501851134011637761', '', '', 0, '0', '1', '2022-03-10 17:23:41', '2022-03-10 17:23:41', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501851374773075970', '删除已发布', 'wxmp:wxfreepublish:del', '', '1501851134011637761', '', '', 0, '0', '1', '2022-03-10 17:24:05', '2022-03-10 17:24:05', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1501851453114286081', '发布草稿', 'wxmp:wxdraft:publish', '', '1501764795530477570', '', '', 0, '0', '1', '2022-03-10 17:24:24', '2022-03-10 17:24:24', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('158da8253a07848d3087e27742b90ecf', '积分设置查询', 'mall:pointsconfig:get', NULL, 'f9ef6fbe98f64ab0f0076d9d51a34f2e', NULL, NULL, 1, '0', '1', '2019-12-11 22:21:02', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('17e1ead2dacd1d312105b396b3517032', '砍价删除', 'mall:bargaininfo:del', NULL, '33f86fb25ecde4e2bc5ec0bdf9229e28', NULL, NULL, 1, '0', '1', '2019-12-28 19:04:39', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1803c5717dce433515683a91bb6d704b', '领券记录查询', 'mall:couponuser:get', NULL, '6dcb14bf76e04a4f5345a1ec6bd7eacc', NULL, NULL, 1, '0', '1', '2019-12-18 18:30:09', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1923c874226184c056658a742f295534', '菜单列表', 'sys:menu:index', NULL, '1200000', NULL, NULL, 1, '0', '1', '2019-10-18 21:20:04', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1a722cc14550813e4caeb5dee7bd5597', '电子券删除', 'mall:couponinfo:del', NULL, 'ba1273686386588c78b20b93fcb1779f', NULL, NULL, 1, '0', '1', '2019-12-14 12:01:13', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('1ee60d42f423c8e0b1b0438ab69aaba4', '查询用户', 'mall:userinfo:get', NULL, 'db95df20df51a46afa7935f38efb7360', NULL, NULL, 1, '0', '1', '2019-12-10 20:58:21', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2100000', '操作日志', NULL, 'log', '1000000', 'el-icon-s-order', 'views/upms/log/index', 5, '0', '0', '2017-11-20 14:06:22', '2021-10-29 19:28:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2101000', '日志删除', 'sys:log:del', NULL, '2100000', NULL, NULL, 1, '0', '1', '2017-11-20 20:37:37', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2185077a9a1a8c4ed3ae2d892e17db1a', '租户修改', 'sys:tenant:edit', NULL, 'e9c150621e6bcfd7f0cea479d2ab9236', NULL, NULL, 1, '0', '1', '2019-10-12 20:34:20', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2200000', '字典管理', NULL, 'dict', '1454042553678036993', 'icon-zidian', 'views/upms/dict/index', 20, '0', '0', '2017-11-29 11:30:52', '2021-11-10 13:35:39', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2201000', '字典删除', 'sys:dict:del', NULL, '2200000', NULL, NULL, 1, '0', '1', '2017-11-29 11:30:11', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2202000', '字典新增', 'sys:dict:add', NULL, '2200000', NULL, NULL, 1, '0', '1', '2018-05-11 22:34:55', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2203000', '字典修改', 'sys:dict:edit', NULL, '2200000', NULL, NULL, 1, '0', '1', '2018-05-11 22:36:03', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('22dad641b94b70574166204f014e462d', '字典详情', 'sys:dict:get', NULL, '2200000', NULL, NULL, 1, '0', '1', '2019-10-18 21:16:48', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2300000', '代码生成器', '', 'gen', 'f513f9e7cb53c16e5de982e12268b070', 'icon-daimashengcheng', 'views/gen/index', 4, '0', '0', '2018-01-20 13:17:19', '2020-02-18 21:07:24', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2304aae6101ef1b56160e5cfe63b997a', '租户列表', 'sys:tenant:index', NULL, 'e9c150621e6bcfd7f0cea479d2ab9236', NULL, NULL, 1, '0', '1', '2019-10-18 21:26:34', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2400000', '终端管理', '', 'client', '1454042553678036993', 'icon-shouji', 'views/upms/client/index', 25, '0', '0', '2018-01-20 13:17:19', '2021-11-10 13:35:47', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2401000', '客户端新增', 'sys:client:add', NULL, '2400000', '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2402000', '客户端修改', 'sys:client:edit', NULL, '2400000', NULL, NULL, 1, '0', '1', '2018-05-15 21:37:06', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2403000', '客户端删除', 'sys:client:del', NULL, '2400000', NULL, NULL, 1, '0', '1', '2018-05-15 21:39:16', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('241b84a3f3ae7876271634c094dee5cc', '客户端详情', 'sys:client:get', NULL, '2400000', NULL, NULL, 1, '0', '1', '2019-10-18 21:22:14', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2600000', '令牌管理', NULL, 'token', '1000000', 'icon-lingpai', 'views/upms/token/index', 11, '0', '0', '2018-09-04 05:58:41', '2021-10-29 19:38:37', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2601000', '令牌删除', 'sys:token:del', NULL, '2600000', NULL, NULL, 1, '0', '1', '2018-09-04 05:59:50', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('266d5676deeafd74df0febe6a45cc209', '租户新增', 'sys:tenant:add', NULL, 'e9c150621e6bcfd7f0cea479d2ab9236', NULL, NULL, 1, '0', '1', '2019-10-12 20:35:00', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2700000', '接口文档', NULL, 'doc', '1227202152985784322', 'el-icon-s-claim', 'views/upms/doc/index', 8, '0', '0', '2019-07-31 11:41:39', '2020-04-26 18:43:12', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2a437bae8175be76cb075ea2888f412d', '商城商品类目列表', 'mall:goodscategory:index', NULL, '8200000', NULL, NULL, 1, '0', '1', '2019-10-18 21:40:11', '2020-08-26 09:13:24', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2b8fa95ebcb3111266ba38f833faf4db', '租户删除', 'sys:tenant:del', NULL, 'e9c150621e6bcfd7f0cea479d2ab9236', NULL, NULL, 1, '0', '1', '2019-10-12 20:33:59', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('2f5d0e7eda34754af13a3d3f519dce19', '小程序配置详情', 'wxmp:wxapp:get', NULL, 'a96328161a39450238e8cafa243b7c35', NULL, NULL, 1, '0', '1', '2019-09-25 17:09:49', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('33f86fb25ecde4e2bc5ec0bdf9229e28', '砍价配置', NULL, 'bargaininfo', '5b1483fcac1c14bcabc28b7e50f21921', 'el-icon-s-tools', 'views/mall/bargaininfo/index', 1, '0', '0', '2019-12-28 19:06:53', '2019-12-28 19:08:58', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('3412a37e6cdc2de99fd534b9327c6288', '素材修改', 'mall:material:edit', NULL, 'e2ab8932bc86ad98b917c22731205caa', NULL, NULL, 1, '0', '1', '2019-10-26 19:35:49', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('35c3726d4956967e108dd142f71f6627', 'CRUD表格设计器', NULL, 'https://crud.avuejs.com/', 'f513f9e7cb53c16e5de982e12268b070', 'el-icon-s-grid', 'https://crud.avuejs.com/', 1, '0', '0', '2019-11-05 18:19:00', '2020-02-15 22:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('39855c97d751071177ab9e88680b78aa', '小程序配置修改', 'wxmp:wxapp:edit', NULL, 'a96328161a39450238e8cafa243b7c35', NULL, NULL, 1, '0', '1', '2019-09-25 17:12:12', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('3d73a5a1971f8485850e387e00da4381', '明细查询', 'mall:pointsrecord:get', NULL, '04c920605926e47982b7ede7fa5c3ec6', NULL, NULL, 1, '0', '1', '2019-12-11 11:29:44', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('3fe13d13918b4386750fbc470cb74689', '素材列表', 'mall:material:index', NULL, 'e2ab8932bc86ad98b917c22731205caa', NULL, NULL, 1, '0', '1', '2019-10-26 19:35:02', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('43886e8bc03aa4dbbd56b88bc7616b3f', '小程序配置新增', 'wxmp:wxapp:add', NULL, 'a96328161a39450238e8cafa243b7c35', NULL, NULL, 1, '0', '1', '2019-09-25 17:10:24', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('4b382a3090885d2783f1f8a1c8e72da9', '电子券查询', 'mall:couponinfo:get', NULL, 'ba1273686386588c78b20b93fcb1779f', NULL, NULL, 1, '0', '1', '2019-12-14 12:00:05', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('4d4ee31e793ab205bd1b0c0796ab464a', '明细列表', 'mall:pointsrecord:index', NULL, '04c920605926e47982b7ede7fa5c3ec6', NULL, NULL, 1, '0', '1', '2019-12-11 11:29:23', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('4e2f07380b3a3a3c83491b44becc87d1', '砍价记录列表', 'mall:bargainuser:index', NULL, 'cb3e50350c8d982ee23474146281f7ed', NULL, NULL, 0, '0', '1', '2020-01-03 19:50:15', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('5a46087c7799d15b3f7dec6373459c18', '砍价列表', 'mall:bargaininfo:index', NULL, '33f86fb25ecde4e2bc5ec0bdf9229e28', NULL, NULL, 1, '0', '1', '2019-12-28 19:03:15', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('5b1483fcac1c14bcabc28b7e50f21921', '砍价管理', NULL, 'bargain', '8000000', 'el-icon-watermelon', 'Layout', 70, '0', '0', '2019-12-28 18:59:37', '2021-03-29 14:31:09', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('5b234a22036af9fcacaad011a3a5a207', '电子券新增', 'mall:couponinfo:add', NULL, 'ba1273686386588c78b20b93fcb1779f', NULL, NULL, 1, '0', '1', '2019-12-14 12:00:29', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('5c4d7e01c344912ab0f55f4c951995f7', '用户修改', 'mall:userinfo:edit', NULL, 'db95df20df51a46afa7935f38efb7360', NULL, NULL, 1, '0', '1', '2019-12-10 20:59:13', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('5d7eca401883fb565e5dfc7b855ed566', '客户端列表', 'sys:client:index', NULL, '2400000', NULL, NULL, 1, '0', '1', '2019-10-18 21:21:26', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('5f3b1faf8a0b5179bf32acd9759f2bfc', '角色列表', 'sys:role:index', NULL, '1300000', NULL, NULL, 1, '0', '1', '2019-10-18 21:25:28', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('62165a91efcb202e65c55cde8a0ef62a', '用户消息列表', 'wxmp:wxmsg:index', NULL, '8170000', NULL, NULL, 1, '0', '1', '2019-10-18 21:36:15', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('6c717f6f1ddc913366e4ba902f84aecf', '商城订单详情', 'mall:orderinfo:get', NULL, '70dbfd7911a393a3c50d1eef09a8708f', NULL, NULL, 1, '0', '1', '2019-09-10 15:13:44', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('6ce1a6e397603915346dc39d2cf2cd8e', '用户列表', 'mall:userinfo:index', NULL, 'db95df20df51a46afa7935f38efb7360', NULL, NULL, 1, '0', '1', '2019-12-10 20:57:48', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('6dcb14bf76e04a4f5345a1ec6bd7eacc', '领券记录', NULL, 'couponuser', 'd2d722dc5a535b918e8edc711e77d5c6', 'el-icon-s-order', 'views/mall/couponuser/index', 2, '0', '0', '2019-12-18 18:28:31', '2020-02-15 22:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('6e46351de075ab9febf8ee6ec17bdced', '退款单', NULL, 'orderrefunds', '7f2e1c50777c7b3009fb44af37766a54', 'el-icon-s-release', 'views/mall/orderrefunds/index', 2, '0', '0', '2019-12-13 19:22:17', '2020-02-15 22:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('70429f02ff42a948929190d7363cb8ae', '积分设置修改', 'mall:pointsconfig:edit', NULL, 'f9ef6fbe98f64ab0f0076d9d51a34f2e', NULL, NULL, 1, '0', '1', '2019-12-06 16:37:13', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('70a667199c0b5af89a9aec25395de035', '电子券列表', 'mall:couponinfo:index', NULL, 'ba1273686386588c78b20b93fcb1779f', NULL, NULL, 1, '0', '1', '2019-12-14 11:59:33', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('70dbfd7911a393a3c50d1eef09a8708f', '全部订单', '', 'orderinfo', '7f2e1c50777c7b3009fb44af37766a54', 'el-icon-s-order', 'views/mall/orderinfo/index', 2, '0', '0', '2019-09-10 15:07:14', '2019-09-10 15:07:29', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('71efc9c14c1c83dd30c826e82ea33eda', '明细新增', 'mall:pointsrecord:add', NULL, '04c920605926e47982b7ede7fa5c3ec6', NULL, NULL, 1, '0', '1', '2019-12-11 11:30:03', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('74008da0be270ec7c90d9b44d5336e24', '小程序配置列表', 'wxmp:wxapp:index', NULL, 'a96328161a39450238e8cafa243b7c35', NULL, NULL, 1, '0', '1', '2019-10-18 21:32:44', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('7b507e991dc0d6364e9ede4a3e3c0f75', '规格列表', 'mall:goodsspec:index', NULL, '8400000', NULL, NULL, 1, '0', '1', '2019-10-18 21:40:42', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('7b7dce33eb721f48c81c4c245345bf2d', '商品列表', 'mall:goodsspu:index', NULL, '8300000', NULL, NULL, 1, '0', '1', '2019-10-18 21:39:43', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('7dcb5d797e46db0bfbc8a1bd2b2079b8', '消息自动回复列表', 'wxmp:wxautoreply:index', NULL, '8160000', NULL, NULL, 1, '0', '1', '2019-10-18 21:33:43', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('7f2e1c50777c7b3009fb44af37766a54', '订单管理', NULL, 'order', '8000000', 'el-icon-s-order', 'Layout', 40, '0', '0', '2019-09-25 13:01:14', '2021-03-29 14:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('7f9dde9bc5f370716d2295302b78a5c1', '商品评价详情', 'mall:goodsappraises:get', NULL, 'c247293edbfc0ee004f413cb6ee654cd', NULL, NULL, 1, '0', '1', '2019-09-25 20:30:28', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8000000', '商城模块', '', '/mall', '0', 'el-icon-s-shop', 'Layout', 10, '0', '0', '2018-01-20 13:17:19', '2021-07-31 21:26:41', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8100000', '公众号管理', '', 'wxmp', '1229675965086429186', 'icon-gongzhonghao', 'Layout', 0, '0', '0', '2018-01-20 13:17:19', '2020-02-18 16:07:41', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8110000', '公众号列表', '', 'wxapp', '8100000', 'icon-peizhi', 'views/wxmp/wxapp/index', 0, '0', '0', '2018-01-20 13:17:19', '2020-02-18 18:15:10', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8111000', '公众号配置新增', 'wxmp:wxapp:add', NULL, '8110000', '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8112000', '公众号配置修改', 'wxmp:wxapp:edit', NULL, '8110000', '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8113000', '公众号配置删除', 'wxmp:wxapp:del', NULL, '8110000', '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8114000', '公众号配置详情', 'wxmp:wxapp:get', NULL, '8110000', '1', NULL, 3, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8120000', '公众号用户', '', 'wxuser', '8100000', 'icon-yonghu', 'views/wxmp/wxuser/index', 2, '0', '0', '2018-01-20 13:17:19', '2019-04-02 09:50:46', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8121000', '公众号用户新增', 'wxmp:wxuser:add', NULL, '8120000', '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8122000', '公众号用户修改', 'wxmp:wxuser:edit', NULL, '8120000', '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8123000', '公众号用户删除', 'wxmp:wxuser:del', NULL, '8120000', '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8124000', '公众号用户详情', 'wxmp:wxuser:get', NULL, '8120000', '1', NULL, 3, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8125000', '公众号用户同步', 'wxmp:wxuser:synchro', NULL, '8120000', NULL, NULL, 1, '0', '1', '2019-04-14 17:35:01', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8126000', '公众号用户备注修改', 'wxmp:wxuser:edit:remark', NULL, '8120000', NULL, NULL, 1, '0', '1', '2019-04-17 14:35:14', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8127000', '公众号用户打标签', 'wxmp:wxuser:tagging', NULL, '8120000', NULL, NULL, 1, '0', '1', '2019-04-17 15:26:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8130000', '素材管理', '', 'wxmaterial', '8100000', 'el-icon-s-cooperation', 'views/wxmp/wxmaterial/index', 4, '0', '0', '2018-01-20 13:17:19', '2019-04-02 09:50:46', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8131000', '素材新增', 'wxmp:wxmaterial:add', NULL, '8130000', '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8132000', '素材修改', 'wxmp:wxmaterial:edit', NULL, '8130000', '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8133000', '素材删除', 'wxmp:wxmaterial:del', NULL, '8130000', '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8134000', '素材详情', 'wxmp:wxmaterial:get', NULL, '8130000', '1', NULL, 3, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8140000', '自定义菜单', '', 'wxmenu', '8100000', 'el-icon-s-unfold', 'views/wxmp/wxmenu/detail', 5, '0', '0', '2018-01-20 13:17:19', '2020-02-18 18:15:40', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8141000', '自定义菜单发布', 'wxmp:wxmenu:add', NULL, '8140000', '1', NULL, 4, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8142000', '自定义菜单详情', 'wxmp:wxmenu:get', NULL, '8140000', '1', NULL, 5, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8150000', '用户标签', NULL, 'wxusertags', '8100000', 'el-icon-s-flag', 'views/wxmp/wxusertags/index', 1, '0', '0', '2019-04-16 17:28:44', '2020-02-18 18:15:24', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8151000', '用户标签列表', 'wxmp:wxusertags:list', NULL, '8150000', NULL, NULL, 1, '0', '1', '2019-04-16 17:30:24', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8152000', '用户标签新增', 'wxmp:wxusertags:add', NULL, '8150000', NULL, NULL, 1, '0', '1', '2019-04-17 09:03:56', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8153000', '用户标签修改', 'wxmp:wxusertags:edit', NULL, '8150000', NULL, NULL, 1, '0', '1', '2019-04-17 09:04:33', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8154000', '用户标签删除', 'wxmp:wxusertags:del', NULL, '8150000', NULL, NULL, 1, '0', '1', '2019-04-17 09:05:08', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8160000', '消息自动回复', '', 'wxautoreply', '8100000', 'el-icon-s-comment', 'views/wxmp/wxautoreply/index', 6, '0', '0', '2018-01-20 13:17:19', '2018-07-29 13:38:19', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8161000', '消息自动回复新增', 'wxmp:wxautoreply:add', NULL, '8160000', '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8162000', '消息自动回复修改', 'wxmp:wxautoreply:edit', NULL, '8160000', '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8163000', '消息自动回复删除', 'wxmp:wxautoreply:del', NULL, '8160000', '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8164000', '消息自动回复详情', 'wxmp:wxautoreply:get', NULL, '8160000', '1', NULL, 3, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8170000', '用户消息', '', 'wxmsg', '8100000', 'el-icon-s-comment', 'views/wxmp/wxmsg/index', 3, '0', '0', '2019-05-28 16:04:18', '2020-02-18 18:15:32', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8171000', '用户消息新增', 'wxmp:wxmsg:add', NULL, '8170000', NULL, NULL, 1, '0', '1', '2019-05-28 16:05:48', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8172000', '用户消息修改', 'wxmp:wxmsg:edit', NULL, '8170000', NULL, NULL, 1, '0', '1', '2019-05-28 16:06:15', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8173000', '用户消息删除', 'wxmp:wxmsg:del', NULL, '8170000', NULL, NULL, 1, '0', '1', '2019-05-28 16:11:06', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8174000', '用户消息详情', 'wxmp:wxmsg:get', NULL, '8170000', NULL, NULL, 1, '0', '1', '2019-05-28 16:11:35', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8180000', '消息群发', '', 'wxmassmsg', '8100000', 'el-icon-s-comment', 'views/wxmp/wxmassmsg/index', 8, '0', '0', '2018-01-20 13:17:19', '2020-02-18 18:15:48', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8181000', '微信群发消息新增', 'wxmp:wxmassmsg:add', NULL, '8180000', '1', NULL, 0, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8182000', '微信群发消息修改', 'wxmp:wxmassmsg:edit', NULL, '8180000', '1', NULL, 1, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8183000', '微信群发消息删除', 'wxmp:wxmassmsg:del', NULL, '8180000', '1', NULL, 2, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8184000', '微信群发消息详情', 'wxmp:wxmassmsg:get', NULL, '8180000', '1', NULL, 3, '0', '1', '2018-05-15 21:35:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8200000', '商城商品类目', '', 'goodscategory', '8500000', 'el-icon-s-finance', 'views/mall/goodscategory/index', 2, '0', '0', '2019-08-12 14:20:15', '2020-08-26 09:12:57', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8210000', '商城商品类目新增', 'mall:goodscategory:add', NULL, '8200000', NULL, NULL, 1, '0', '1', '2019-08-12 14:21:10', '2020-08-26 09:14:05', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8220000', '商城商品类目修改', 'mall:goodscategory:edit', NULL, '8200000', NULL, NULL, 1, '0', '1', '2019-08-12 14:21:46', '2020-08-26 09:14:01', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8230000', '商城商品类目删除', 'mall:goodscategory:del', NULL, '8200000', NULL, NULL, 1, '0', '1', '2019-08-12 14:22:12', '2020-08-26 09:14:03', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8240000', '商城商品类目详情', 'mall:goodscategory:get', NULL, '8200000', NULL, NULL, 1, '0', '1', '2019-08-12 14:22:43', '2020-08-26 09:13:56', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('82dcc2e0e8ca1c0f8bd4c424a5dda5c5', '退款单列表', 'mall:orderrefunds:index', NULL, '6e46351de075ab9febf8ee6ec17bdced', NULL, NULL, 1, '0', '1', '2019-12-13 19:23:01', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8300000', '全部商品', '', 'goodsspu', '8500000', 'el-icon-s-goods', 'views/mall/goodsspu/index', 0, '0', '0', '2019-08-12 16:32:05', '2019-08-12 16:32:38', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8310000', '商品新增', 'mall:goodsspu:add', NULL, '8300000', NULL, NULL, 1, '0', '1', '2019-08-12 16:34:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8320000', '商品修改', 'mall:goodsspu:edit', NULL, '8300000', NULL, NULL, 1, '0', '1', '2019-08-12 16:34:53', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8330000', '商品删除', 'mall:goodsspu:del', NULL, '8300000', NULL, NULL, 1, '0', '1', '2019-08-12 16:35:22', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8340000', '商品详情', 'mall:goodsspu:get', NULL, '8300000', NULL, NULL, 1, '0', '1', '2019-08-12 16:35:42', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8398b906bfd903c55d3bab220d95d050', '运费模板查询', 'mall:freighttemplat:get', NULL, 'e6a73489db9ef754101570b0e6d0d2e5', NULL, NULL, 1, '0', '1', '2019-12-24 16:27:16', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8400000', '规格管理', '', 'goodsspec', '8500000', 'el-icon-s-operation', 'views/mall/goodsspec/index', 1, '0', '0', '2019-08-13 16:22:40', '2019-08-13 16:23:51', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8410000', '规格新增', 'mall:goodsspec:add', NULL, '8400000', NULL, NULL, 1, '0', '1', '2019-08-13 16:21:01', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8420000', '规格删除', 'mall:goodsspec:del', NULL, '8400000', NULL, NULL, 1, '0', '1', '2019-08-13 16:21:28', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8430000', '规格修改', 'mall:goodsspec:edit', NULL, '8400000', NULL, NULL, 1, '0', '1', '2019-08-13 16:28:26', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8500000', '商品管理', NULL, 'goods', '8000000', 'el-icon-goods', 'Layout', 35, '0', '0', '2019-08-21 09:09:37', '2021-03-29 14:32:53', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('8aae802c5e76c10fb00c342870aec822', '砍价新增', 'mall:bargaininfo:add', NULL, '33f86fb25ecde4e2bc5ec0bdf9229e28', NULL, NULL, 1, '0', '1', '2019-12-28 19:03:56', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('92e0817e3f1f38b2f307a12dfb57f973', '退款单修改', 'mall:orderrefunds:edit', NULL, '6e46351de075ab9febf8ee6ec17bdced', NULL, NULL, 1, '0', '1', '2019-12-13 19:24:01', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('9758015e84ca9001f54651db3e215f22', '微信群发消息列表', 'wxmp:wxmassmsg:index', NULL, '8180000', NULL, NULL, 1, '0', '1', '2019-10-18 21:34:25', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('997766d975fecca2bbc6d87a1ffa33bc', '领券记录列表', 'mall:couponuser:index', NULL, '6dcb14bf76e04a4f5345a1ec6bd7eacc', NULL, NULL, 1, '0', '1', '2019-12-18 18:29:32', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('99de545b2709c749074253793b0b3579', '砍价修改', 'mall:bargaininfo:edit', NULL, '33f86fb25ecde4e2bc5ec0bdf9229e28', NULL, NULL, 1, '0', '1', '2019-12-28 19:04:18', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('9de3c01d50199f569d58979adba94190', '租户详情', 'sys:tenant:get', NULL, 'e9c150621e6bcfd7f0cea479d2ab9236', NULL, NULL, 1, '0', '1', '2019-10-12 20:34:40', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('a020c0eda490bc5acbbdab8aed5de286', '商城订单修改', 'mall:orderinfo:edit', NULL, '70dbfd7911a393a3c50d1eef09a8708f', NULL, NULL, 1, '0', '1', '2019-09-10 15:12:47', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('a18a3759214257e8ad00b4e8e250b097', '运费模板删除', 'mall:freighttemplat:del', NULL, 'e6a73489db9ef754101570b0e6d0d2e5', NULL, NULL, 1, '0', '1', '2019-12-24 16:28:12', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('a1b97d911a253bbb7984d914423edab1', '运费模板修改', 'mall:freighttemplat:edit', NULL, 'e6a73489db9ef754101570b0e6d0d2e5', NULL, NULL, 1, '0', '1', '2019-12-24 16:27:43', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('a1d5a717939198826962f7621f86f53d', '商品评价新增', 'mall:goodsappraises:add', NULL, 'c247293edbfc0ee004f413cb6ee654cd', NULL, NULL, 1, '0', '1', '2019-09-25 20:30:58', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('a24da84b7de0787d578d5f60c3803d2f', '公众号用户列表', 'wxmp:wxuser:index', NULL, '8120000', NULL, NULL, 1, '0', '1', '2019-10-18 21:37:10', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('a263bd8cbe22bceab8ecd4f6e2b827b4', '明细删除', 'mall:pointsrecord:del', NULL, '04c920605926e47982b7ede7fa5c3ec6', NULL, NULL, 1, '0', '1', '2019-12-11 11:30:40', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('a96328161a39450238e8cafa243b7c35', '小程序列表', '', 'wxapp', 'ae0619ea3e5b86a3e382081680785f7e', 'el-icon-s-operation', 'views/wxma/wxapp/index', 0, '0', '0', '2019-09-25 17:09:00', '2020-02-18 18:16:24', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('ae0619ea3e5b86a3e382081680785f7e', '小程序管理', NULL, 'wxma', '1229675965086429186', 'el-icon-orange', 'Layout', 10, '0', '0', '2019-09-25 14:25:17', '2020-02-18 16:07:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('b7fb7e7511057c292f4e7a567803e49c', '用户列表', 'sys:user:index', NULL, '1100000', NULL, NULL, 1, '0', '1', '2019-10-18 21:29:16', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('ba1273686386588c78b20b93fcb1779f', '电子券制作', NULL, 'couponinfo', 'd2d722dc5a535b918e8edc711e77d5c6', 'el-icon-s-open', 'views/mall/couponinfo/index', 1, '0', '0', '2019-12-14 11:57:55', '2020-02-15 22:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('bec7e2f7cf4ad760bbe550fa05f7eec4', '砍价查询', 'mall:bargaininfo:get', NULL, '33f86fb25ecde4e2bc5ec0bdf9229e28', NULL, NULL, 1, '0', '1', '2019-12-28 19:03:36', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('bf99fd6d49b5db3a107848984daff0ba', '商城订单新增', 'mall:orderinfo:add', NULL, '70dbfd7911a393a3c50d1eef09a8708f', NULL, NULL, 1, '0', '1', '2019-09-10 15:12:13', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('c0ed022164dbf21954ef79dfb3d70ed3', '素材新增', 'mall:material:add', NULL, 'e2ab8932bc86ad98b917c22731205caa', NULL, NULL, 1, '0', '1', '2019-10-26 19:35:23', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('c247293edbfc0ee004f413cb6ee654cd', '商品评价', '', 'goodsappraises', '8500000', 'el-icon-s-open', 'views/mall/goodsappraises/index', 3, '0', '0', '2019-09-25 20:27:30', '2019-09-25 21:39:59', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('c35e06fc6b2b474a14aff1d7a69aaef9', '商城订单删除', 'mall:orderinfo:del', NULL, '70dbfd7911a393a3c50d1eef09a8708f', NULL, NULL, 1, '0', '1', '2019-09-10 15:13:12', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('cb3e50350c8d982ee23474146281f7ed', '砍价记录', NULL, 'bargainuser', '5b1483fcac1c14bcabc28b7e50f21921', 'el-icon-s-order', 'views/mall/bargainuser/index', 2, '0', '0', '2020-01-03 19:49:23', '2020-01-03 19:49:37', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('d2d722dc5a535b918e8edc711e77d5c6', '电子券管理', NULL, 'coupon', '8000000', 'el-icon-s-ticket', 'Layout', 60, '0', '0', '2019-12-14 11:52:39', '2021-03-29 14:31:03', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('d56d79f193c43e90ef3bef5b1d19bee8', '令牌列表', 'sys:token:index', NULL, '2600000', NULL, NULL, 1, '0', '1', '2019-10-18 21:30:11', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('d9c8041c62c50f859b049e86357d1c41', '日志列表', 'sys:log:index', NULL, '2100000', NULL, NULL, 1, '0', '1', '2019-10-18 21:18:38', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('db95df20df51a46afa7935f38efb7360', '商城用户', NULL, 'userInfo', '1342089211811028994', 'el-icon-user', 'views/mall/userinfo/index', 10, '0', '0', '2019-12-10 20:57:03', '2021-03-29 11:39:32', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('e2ab8932bc86ad98b917c22731205caa', '素材库', NULL, 'material', '8000000', 'el-icon-s-platform', 'views/mall/material/index', 100, '0', '0', '2019-10-26 19:33:45', '2021-10-30 19:40:22', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('e3a14107a9e90f90922f7053a379a719', 'FORM表单设计器', NULL, 'https://form.avuejs.com/', 'f513f9e7cb53c16e5de982e12268b070', 'el-icon-s-management', 'https://form.avuejs.com/', 1, '0', '0', '2019-11-05 18:18:12', '2020-02-15 22:30:54', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('e6a73489db9ef754101570b0e6d0d2e5', '运费模板', NULL, 'freighttemplat', '1000000', 'el-icon-s-promotion', 'views/mall/freighttemplat/index', 30, '0', '0', '2019-12-24 16:22:03', '2021-10-30 19:33:58', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('e6ae905517592d8b3f30a0a5a1bfb65b', '商品评价删除', 'mall:goodsappraises:del', NULL, 'c247293edbfc0ee004f413cb6ee654cd', NULL, NULL, 1, '0', '1', '2019-09-25 20:29:32', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('e9c150621e6bcfd7f0cea479d2ab9236', '租户管理', '', 'tenant', '1454042553678036993', 'el-icon-s-grid', 'views/upms/tenant/index', 1, '0', '0', '2019-10-12 20:33:27', '2021-10-29 19:13:51', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('eace6e697073de815152466b9c493c4d', '素材详情', 'mall:material:get', NULL, 'e2ab8932bc86ad98b917c22731205caa', NULL, NULL, 1, '0', '1', '2019-10-26 19:36:25', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('ec18bd25c6ed99faaa7cc8190fa23db1', '素材列表', 'wxmp:wxmaterial:index', NULL, '8130000', NULL, NULL, 1, '0', '1', '2019-10-18 21:35:34', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('ec1b689468ac0eab23713ce41273ae4b', '机构列表', 'sys:organ:index', NULL, '1400000', NULL, NULL, 1, '0', '1', '2019-10-18 21:24:07', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('ec21dec4d5791af4e30c34f7d077f06d', '运费模板新增', 'mall:freighttemplat:add', NULL, 'e6a73489db9ef754101570b0e6d0d2e5', NULL, NULL, 1, '0', '1', '2019-12-24 16:23:17', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('edcaca9d4cc487aefe2c0c043bb95c30', '字典列表', 'sys:dict:index', NULL, '2200000', NULL, NULL, 1, '0', '1', '2019-10-18 21:17:36', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('f4eae5341815bec413ef64e3e6524385', '小程序配置删除', 'wxmp:wxapp:del', NULL, 'a96328161a39450238e8cafa243b7c35', NULL, NULL, 1, '0', '1', '2019-09-25 17:11:16', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('f513f9e7cb53c16e5de982e12268b070', '代码生成', NULL, 'develop', '1227202152985784322', 'el-icon-s-marketing', 'Layout', 4, '0', '0', '2019-11-05 18:12:32', '2020-02-18 21:07:16', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('f9ef6fbe98f64ab0f0076d9d51a34f2e', '积分设置', '', 'pointsconfig', '01e9e720e3eedce613a297dcdddb3d63', 'el-icon-s-operation', 'views/mall/pointsconfig/form', 1, '0', '0', '2019-12-06 16:36:33', '2019-12-06 16:38:55', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('fda6b16ece50ede8a7a5bb16213753d0', '电子券修改', 'mall:couponinfo:edit', NULL, 'ba1273686386588c78b20b93fcb1779f', NULL, NULL, 1, '0', '1', '2019-12-14 12:00:49', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('fe8074f23fa08934775bc7d06401fd3f', '运费模板列表', 'mall:freighttemplat:index', NULL, 'e6a73489db9ef754101570b0e6d0d2e5', NULL, NULL, 1, '0', '1', '2019-12-24 16:26:39', '2020-03-24 20:19:17', '0');
INSERT INTO `sys_menu` (`id`, `name`, `permission`, `path`, `parent_id`, `icon`, `component`, `sort`, `keep_alive`, `type`, `create_time`, `update_time`, `del_flag`) VALUES ('fef4dfeeb78c4f299e0e6e1a43be3585', '公众号列表', 'wxmp:wxapp:index', NULL, '8110000', NULL, NULL, 1, '0', '1', '2019-10-18 21:31:53', '2020-03-24 20:19:17', '0');
COMMIT;

-- ----------------------------
-- Table structure for sys_oauth_client
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client`;
CREATE TABLE `sys_oauth_client` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `client_secret` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '密钥',
  `scope` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '域',
  `authorized_grant_types` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '授权模式',
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `autoapprove` char(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='终端信息表';

-- ----------------------------
-- Records of sys_oauth_client
-- ----------------------------
BEGIN;
INSERT INTO `sys_oauth_client` (`id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('admin', NULL, 'admin', 'server', 'password,refresh_token,authorization_code,client_credentials', '', NULL, NULL, NULL, NULL, 'true');
INSERT INTO `sys_oauth_client` (`id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('app', '', 'app', 'server', 'authorization_code,refresh_token,password,client_credentials', '', '', NULL, NULL, '', 'true');
INSERT INTO `sys_oauth_client` (`id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('gen', NULL, 'gen', 'server', 'password,refresh_token', NULL, NULL, NULL, NULL, NULL, 'true');
INSERT INTO `sys_oauth_client` (`id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('swagger', '', 'swagger', 'server', 'password,refresh_token', '', '', NULL, NULL, '', 'true');
INSERT INTO `sys_oauth_client` (`id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('test', '', 'test', 'server', 'refresh_token,password', '', '', 0, 0, '', 'true');
INSERT INTO `sys_oauth_client` (`id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `autoapprove`) VALUES ('weixin', '', 'weixin', 'server', 'password,refresh_token', '', '', NULL, NULL, '', 'true');
COMMIT;

-- ----------------------------
-- Table structure for sys_organ
-- ----------------------------
DROP TABLE IF EXISTS `sys_organ`;
CREATE TABLE `sys_organ` (
  `id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `parent_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '父类ID，为0时是租户，-1时是平台系统管理',
  `sort` int DEFAULT '1' COMMENT '排序',
  `type` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构类型',
  `code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '机构编码',
  `name` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '机构名称',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '电话',
  `fax` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '传真',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮箱',
  `address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '地址',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '备注',
  `del_flag` char(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `tenant_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '租户ID',
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '0:正常，9:冻结',
  `logo` varchar(500) DEFAULT NULL COMMENT 'logo',
  `home_url` varchar(100) DEFAULT NULL COMMENT '官方网址',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_tenant_id_code` (`code`,`tenant_id`) USING BTREE,
  KEY `ids_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='机构管理';

-- ----------------------------
-- Records of sys_organ
-- ----------------------------
BEGIN;
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('-1', '2021-11-10 12:07:33', '2021-11-11 13:24:04', '-1', 1, '1', '-1', 'JooLun系统平台管理', '', NULL, NULL, NULL, NULL, '0', '-1', '0', 'http://minio.joolun.com/joolun/1/material/a7ef5da8-832d-4439-8668-f52a06ff60fd.png', 'https://www.joolun.com/');
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('1', '2018-01-22 19:00:23', '2020-07-22 21:44:15', '0', 1, '1', '10000', 'JooLun多店版演示商城', NULL, NULL, NULL, NULL, '4145', '0', '1', '0', 'http://minio.joolun.com/joolun/1/material/a7ef5da8-832d-4439-8668-f52a06ff60fd.png', 'http://www.joolun.com');
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('1232901074731294721', '2020-02-27 13:31:37', '2020-05-22 17:30:44', '4', 4, '2', '6656546', '服务部', '', '', '', '', 'rtu', '0', '1', NULL, NULL, NULL);
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('2', '2018-01-22 19:00:38', '2021-10-31 12:20:01', '0', 2, '1', '20000', '阿里商城', NULL, NULL, NULL, NULL, NULL, '0', '2', '0', 'https://minio.joolun.com/joolun/1/material/e0a1a0d1-3cf1-4229-a871-5f3fa87550c9.png', 'http://www.joolun.com');
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('29a4609f543eb7a1b0f8b38ce01cc125', '2019-12-15 17:28:18', '2020-02-12 22:39:09', '3', 1, '2', 'ge235235', '技术部', '', '', '', '', NULL, '0', '1', NULL, NULL, NULL);
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('3', '2018-01-22 19:00:44', '2020-02-12 22:39:09', '1', 0, '2', '10001', '腾讯部门一', '188888888', '556558444', '156545588@qq.com', '中国广东省深圳高南山区机械厂32号', NULL, '0', '1', NULL, NULL, NULL);
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('4', '2018-01-22 19:00:52', '2020-02-12 22:39:09', '1', 2, '2', '10002', '腾讯部门二', NULL, NULL, NULL, NULL, NULL, '0', '1', NULL, NULL, NULL);
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('7', '2018-01-22 19:01:57', '2020-02-27 21:38:48', '2', 1, '2', '20001', '阿里一公司', NULL, NULL, NULL, NULL, NULL, '0', '2', NULL, NULL, NULL);
INSERT INTO `sys_organ` (`id`, `create_time`, `update_time`, `parent_id`, `sort`, `type`, `code`, `name`, `phone`, `fax`, `email`, `address`, `remarks`, `del_flag`, `tenant_id`, `status`, `logo`, `home_url`) VALUES ('8', '2018-01-22 19:02:03', '2020-02-27 21:38:51', '2', 2, '2', '20002', '阿里二公司', NULL, NULL, NULL, NULL, NULL, '0', '2', NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_organ_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_organ_relation`;
CREATE TABLE `sys_organ_relation` (
  `ancestor` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '祖先节点',
  `descendant` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '后代节点',
  `tenant_id` varchar(32) NOT NULL COMMENT '所属租户',
  PRIMARY KEY (`ancestor`,`descendant`) USING BTREE,
  KEY `idx1` (`ancestor`) USING BTREE,
  KEY `idx2` (`descendant`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='机构关系表';

-- ----------------------------
-- Records of sys_organ_relation
-- ----------------------------
BEGIN;
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('1', '1', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('1', '1232901074731294721', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('1', '29a4609f543eb7a1b0f8b38ce01cc125', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('1', '3', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('1', '4', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('1232901074731294721', '1232901074731294721', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('2', '2', '2');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('2', '7', '2');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('2', '8', '2');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('29a4609f543eb7a1b0f8b38ce01cc125', '29a4609f543eb7a1b0f8b38ce01cc125', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('3', '29a4609f543eb7a1b0f8b38ce01cc125', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('3', '3', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('4', '1232901074731294721', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('4', '4', '1');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('7', '7', '2');
INSERT INTO `sys_organ_relation` (`ancestor`, `descendant`, `tenant_id`) VALUES ('8', '8', '2');
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'PK',
  `role_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色名',
  `role_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '角色编码',
  `role_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '角色描述',
  `ds_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '2' COMMENT '数据权限类型',
  `ds_scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '数据权限范围',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '租户id',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `role_idx1_role_code` (`role_code`,`tenant_id`) USING BTREE,
  KEY `ids_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`, `tenant_id`) VALUES ('1', '系统管理员', 'ROLE_ADMIN', '系统管理员', '0', '', '2017-10-29 15:45:51', '2021-11-10 12:01:10', '0', '-1');
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`, `tenant_id`) VALUES ('1309132281396396034', '店铺管理员', 'ROLE_SHOP_USER', '店铺管理员', '0', '', '2020-09-24 22:07:13', NULL, '0', '2');
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`, `tenant_id`) VALUES ('1454349176107761666', '租户管理员', 'ROLE_ADMIN', '管理所属租户（商城）下的所有数据', '0', '', '2021-10-30 15:27:18', '2021-11-10 13:28:59', '0', '1');
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`, `tenant_id`) VALUES ('1458655453256470530', '系统订单管理员', 'role_order', '管理绑定的租户下所有订单', '0', NULL, '2021-11-11 12:38:55', NULL, '0', '-1');
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`, `tenant_id`) VALUES ('2', '租户管理员', 'ROLE_ADMIN', '租户管理员', '0', NULL, '2018-11-11 19:42:26', '2021-10-30 14:53:40', '0', '2');
INSERT INTO `sys_role` (`id`, `role_name`, `role_code`, `role_desc`, `ds_type`, `ds_scope`, `create_time`, `update_time`, `del_flag`, `tenant_id`) VALUES ('3', '店员角色', 'ROLE_SHOP_USER', '店员角色，新增店员用户时，会自动授予此角色，不能删除', '2', '4', '2019-03-30 17:44:20', '2020-09-24 21:39:14', '0', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色ID',
  `menu_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '菜单ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `tenant_id` varchar(32) NOT NULL COMMENT '所属租户',
  PRIMARY KEY (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '01e9e720e3eedce613a297dcdddb3d63', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '04c920605926e47982b7ede7fa5c3ec6', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '0776fdb3afe857025aefeb6500f67fb6', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '088b9371d30c932f9ba555a457a165cb', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '097f4947ac78faf5074c357f5b1ee8f1', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '0d1cfcbd88e13d75ea9796d08b4964a0', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1000000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '10e7a8ca4da4f8cd9e36a375d7266f6a', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1100000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1101000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1102000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1103000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1104000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1105000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1200000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1201000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1202000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1203000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1224306298712969218', '2020-02-03 20:19:02', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1224306628599173121', '2020-02-03 20:20:21', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1224307007944609794', '2020-02-03 20:21:51', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1225254548244733953', '2020-02-06 11:07:03', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226525445274419201', '2020-02-09 23:17:08', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226525576396750849', '2020-02-09 23:17:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226525700103553025', '2020-02-09 23:18:09', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226525832920383490', '2020-02-09 23:18:40', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226525967616262146', '2020-02-09 23:19:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226526082179481602', '2020-02-09 23:19:40', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226759608866893825', '2020-02-10 14:47:37', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226759834914713601', '2020-02-10 14:48:31', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1226760046232137729', '2020-02-10 14:49:21', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1227202152985784322', '2020-02-11 20:06:08', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1227202599968567298', '2020-02-11 20:07:54', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1227203300044042241', '2020-02-11 20:10:41', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1227524298689736706', '2020-02-12 17:26:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1227524435889614850', '2020-02-12 17:26:46', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1227568833306177537', '2020-02-12 20:23:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1228676296659161089', '2020-02-15 21:43:51', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1228676665992794113', '2020-02-15 21:45:19', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1228676836369616897', '2020-02-15 21:46:00', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1229322873778180098', '2020-02-17 16:33:07', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1229323003373785090', '2020-02-17 16:33:38', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1229675965086429186', '2020-02-18 15:56:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1231205330852978690', '2020-02-22 21:13:20', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1233957113861967874', '2020-03-01 11:27:56', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1233957499251326978', '2020-03-01 11:29:28', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1234086429899862018', '2020-03-01 20:01:47', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239766266052329473', '2020-03-17 12:11:26', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239766777090523137', '2020-03-17 12:13:28', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239766958087323650', '2020-03-17 12:14:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239767051196678145', '2020-03-17 12:14:33', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239767117328269313', '2020-03-17 12:14:49', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239767195036139522', '2020-03-17 12:15:07', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239767264883884034', '2020-03-17 12:15:24', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239767506106695682', '2020-03-17 12:16:21', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1239767627204640770', '2020-03-17 12:16:50', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1243832970918268930', '2020-03-28 17:31:04', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1243833202171219970', '2020-03-28 17:31:59', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1243869063717883905', '2020-03-28 19:54:29', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1250724694023069698', '2020-04-16 17:56:19', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1250724859953930242', '2020-04-16 17:56:58', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1250725031559684098', '2020-04-16 17:57:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1250725307804934146', '2020-04-16 17:58:45', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1250725417255297025', '2020-04-16 17:59:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1250725530589585409', '2020-04-16 17:59:38', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1261169795019001858', '2020-05-15 13:41:25', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1261170252735008769', '2020-05-15 13:43:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1261170652447985665', '2020-05-15 13:44:49', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1261170764750475265', '2020-05-15 13:45:16', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1261170872766386178', '2020-05-15 13:45:42', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1261174275395506178', '2020-05-15 13:59:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1261174360032366593', '2020-05-15 13:59:33', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1265900454069366785', '2020-05-28 14:59:22', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1265900671623720961', '2020-05-28 15:00:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1265900771255218178', '2020-05-28 15:00:37', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1265900866067460098', '2020-05-28 15:01:00', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1265900951765479426', '2020-05-28 15:01:20', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1265901033235640321', '2020-05-28 15:01:40', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293360350588104706', '2020-08-12 09:35:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293360792097320962', '2020-08-12 09:36:57', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293360954580463618', '2020-08-12 09:37:35', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293361105973866497', '2020-08-12 09:38:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293361232570544129', '2020-08-12 09:38:42', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293361400409812994', '2020-08-12 09:39:22', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293361554047168513', '2020-08-12 09:39:58', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293383822223667201', '2020-08-12 11:08:27', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293384016864538626', '2020-08-12 11:09:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293384142387474433', '2020-08-12 11:09:44', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293384241469517825', '2020-08-12 11:10:07', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293384332062289922', '2020-08-12 11:10:29', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1293384409782743041', '2020-08-12 11:10:47', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298426964249427969', '2020-08-26 09:08:06', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298427283679232001', '2020-08-26 09:09:22', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298427420728115202', '2020-08-26 09:09:55', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298427594900783106', '2020-08-26 09:10:36', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298427668183662594', '2020-08-26 09:10:54', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298427749360222210', '2020-08-26 09:11:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298809898521346049', '2020-08-27 10:29:45', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298809978259259393', '2020-08-27 10:30:04', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298810074178797570', '2020-08-27 10:30:27', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298810151446265857', '2020-08-27 10:30:45', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298810233528795138', '2020-08-27 10:31:05', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1298980277709717506', '2020-08-27 21:46:46', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '129a20e268846e9f03e7c7317d34763a', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1300000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1301000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1302000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1303000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1304000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1305000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308349941648834562', '2020-09-22 18:18:28', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308351154087903234', '2020-09-22 18:23:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308353287508049922', '2020-09-22 18:31:46', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308353386212605954', '2020-09-22 18:32:09', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308364923052605441', '2020-09-22 19:18:00', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308365472401571842', '2020-09-22 19:20:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308365991039844354', '2020-09-22 19:22:15', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308366119851114498', '2020-09-22 19:22:45', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308366209860878337', '2020-09-22 19:23:07', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1308366313216917505', '2020-09-22 19:23:32', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1309098073269506049', '2020-09-24 19:51:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1309113406327906305', '2020-09-24 20:52:12', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1309113499143659521', '2020-09-24 20:52:35', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1309113662792818689', '2020-09-24 20:53:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1309113748939628546', '2020-09-24 20:53:34', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1309113826735579138', '2020-09-24 20:53:53', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1309124414236835842', '2020-09-24 21:35:57', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1320352206834208769', '2020-10-25 21:11:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1321281973857124354', '2020-10-28 10:45:45', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1321283875755884546', '2020-10-28 10:53:18', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1321284014893531137', '2020-10-28 10:53:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1321284106564239361', '2020-10-28 10:54:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1321284197328977921', '2020-10-28 10:54:35', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1321284312139661314', '2020-10-28 10:55:02', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1322075855138095105', '2020-10-30 15:20:21', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331123534564425730', '2020-11-24 14:32:36', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331123875771056130', '2020-11-24 14:33:57', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331124222807769090', '2020-11-24 14:35:20', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331124349102456833', '2020-11-24 14:35:50', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331124445831495681', '2020-11-24 14:36:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331124539226062849', '2020-11-24 14:36:35', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331124631018405890', '2020-11-24 14:36:57', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331127314508939265', '2020-11-24 14:47:37', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331127770438172674', '2020-11-24 14:49:26', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331127886733639681', '2020-11-24 14:49:54', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331127967310413825', '2020-11-24 14:50:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331128039997702145', '2020-11-24 14:50:30', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1331128115079938050', '2020-11-24 14:50:48', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342074455276478465', '2020-12-24 19:47:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342074603792588801', '2020-12-24 19:48:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342089211811028994', '2020-12-24 20:46:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342093572213661698', '2020-12-24 21:03:37', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342093729722359809', '2020-12-24 21:04:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342093905342062593', '2020-12-24 21:04:56', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342093992382259201', '2020-12-24 21:05:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342112579171999746', '2020-12-24 22:19:08', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342112864309174273', '2020-12-24 22:20:16', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342368489077252098', '2020-12-25 15:16:02', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342368718203691010', '2020-12-25 15:16:57', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342369468602425345', '2020-12-25 15:19:56', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342369546528399362', '2020-12-25 15:20:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1342369625242902530', '2020-12-25 15:20:33', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1346469866533576705', '2021-01-05 22:55:12', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1346470006900154370', '2021-01-05 22:55:12', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1346470119282335746', '2021-01-05 22:55:12', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1346470207647932417', '2021-01-05 22:55:12', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1346470309233975298', '2021-01-05 22:55:12', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349244303388487681', '2021-01-13 14:38:04', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349244680460611585', '2021-01-13 14:39:34', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349244822907564034', '2021-01-13 14:40:08', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349245153523576834', '2021-01-13 14:41:27', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349245241826258946', '2021-01-13 14:41:48', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349245331873771521', '2021-01-13 14:42:09', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349258652614176770', '2021-01-13 15:35:05', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349258902200430594', '2021-01-13 15:36:04', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1349259096077938689', '2021-01-13 15:36:51', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1350792832623841281', '2021-01-17 21:11:22', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1351564537773465602', '2021-01-20 00:17:51', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1355900128047747074', '2021-01-31 23:25:56', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1358436360011132929', '2021-02-07 23:24:01', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1372090280218505217', '2021-03-17 15:39:49', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1372090492391567361', '2021-03-17 15:40:40', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1372101740151746561', '2021-03-17 16:25:21', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1372101887707361282', '2021-03-17 16:25:57', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1372102024953376769', '2021-03-17 16:26:29', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1372102231694815234', '2021-03-17 16:27:19', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1372888102607085569', '2021-03-19 20:30:05', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1376421493427228674', '2021-03-29 14:30:31', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1376422486076379138', '2021-03-29 14:34:28', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1376422678422966274', '2021-03-29 14:35:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1376422796756865025', '2021-03-29 14:35:42', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1376422875219709954', '2021-03-29 14:36:00', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1376422957583257601', '2021-03-29 14:36:20', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1376423034070585346', '2021-03-29 14:36:38', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1377094310065950721', '2021-03-31 11:04:03', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1381872508842598401', '2021-04-13 15:30:54', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1381872757216698369', '2021-04-13 15:31:53', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1381872968177606657', '2021-04-13 15:32:44', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1381873672174755842', '2021-04-13 15:35:32', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1386255289156673537', '2021-04-25 17:46:30', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1386255440021594113', '2021-04-25 17:47:06', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1386255544229076993', '2021-04-25 17:47:31', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1386255636726063106', '2021-04-25 17:47:53', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1386255721815908354', '2021-04-25 17:48:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1386255797153996801', '2021-04-25 17:48:32', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1391640356498513922', '2021-05-10 14:24:51', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1391640959698149378', '2021-05-10 14:27:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1391641292511977473', '2021-05-10 14:28:34', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1391641504399826945', '2021-05-10 14:29:24', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1391641564550340609', '2021-05-10 14:29:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1391641697975345154', '2021-05-10 14:30:10', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1391641880649867266', '2021-05-10 14:30:54', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1400000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1401000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1402000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1403000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1404000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1420641504689475586', '2021-07-29 15:05:03', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1420641654505820161', '2021-07-29 15:05:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1420641756330938370', '2021-07-29 15:06:03', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421313332319657985', '2021-07-31 11:34:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421313416033771522', '2021-07-31 11:34:59', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421313667629166593', '2021-07-31 11:35:59', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421320896126234626', '2021-07-31 12:04:43', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421320981740367874', '2021-07-31 12:05:03', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421321068310802433', '2021-07-31 12:05:24', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421471312252047361', '2021-07-31 22:02:25', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421471400907051009', '2021-07-31 22:02:46', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421471501620678657', '2021-07-31 22:03:10', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421474975628472321', '2021-07-31 22:16:58', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421475074509189121', '2021-07-31 22:17:22', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421475157585768449', '2021-07-31 22:17:41', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421478402899120130', '2021-07-31 22:30:35', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421480464693137410', '2021-07-31 22:38:47', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421480612496216066', '2021-07-31 22:39:22', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1421480719023149058', '2021-07-31 22:39:47', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1452888291086512130', '2021-10-26 14:42:16', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1452888781744582657', '2021-10-26 14:44:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1452888918969626626', '2021-10-26 14:44:46', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1452889019288989698', '2021-10-26 14:45:10', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1452889113044267010', '2021-10-26 14:45:32', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1452889220854657025', '2021-10-26 14:45:58', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1453315663338254338', '2021-10-27 19:00:30', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1454042553678036993', '2021-10-29 19:08:54', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1454652643779022850', '2021-10-31 11:33:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1454656678447812609', '2021-10-31 11:49:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1454663152586723330', '2021-10-31 12:14:56', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1454682603688378369', '2021-10-31 11:33:11', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1455096953657966593', '2021-11-01 16:58:42', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1455097203571376129', '2021-11-01 16:59:42', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1455097289248423938', '2021-11-01 17:00:02', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1455097421603880961', '2021-11-01 17:00:34', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1455100118050619393', '2021-11-01 17:11:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458315340261376002', '2021-11-10 14:07:26', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458328892766437377', '2021-11-10 15:01:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330074838745089', '2021-11-10 15:05:58', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330157793689602', '2021-11-10 15:06:18', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330223161917441', '2021-11-10 15:06:34', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330288127492097', '2021-11-10 15:06:49', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330354909200386', '2021-11-10 15:07:05', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330420562640897', '2021-11-10 15:07:21', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330506537484289', '2021-11-10 15:07:41', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330564880252930', '2021-11-10 15:07:55', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330657897332737', '2021-11-10 15:08:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330746220986370', '2021-11-10 15:08:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330814382620674', '2021-11-10 15:08:55', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458330874130481153', '2021-11-10 15:09:09', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458332164357111810', '2021-11-10 15:14:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458332380720283650', '2021-11-10 15:15:08', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458334001260937218', '2021-11-10 15:21:35', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458334066188763138', '2021-11-10 15:21:50', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458334147696672770', '2021-11-10 15:22:10', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458334207809437697', '2021-11-10 15:22:24', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458334820429479938', '2021-11-10 15:24:50', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458334888670806018', '2021-11-10 15:25:06', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458334955809030146', '2021-11-10 15:25:22', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458335569028857858', '2021-11-10 15:27:48', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458338863461711873', '2021-11-10 15:40:54', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458338974786928641', '2021-11-10 15:41:20', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1458339030734749698', '2021-11-10 15:41:34', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1459139258920476673', '2021-11-12 20:41:23', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1459143497277341698', '2021-11-12 20:58:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1459143655096418305', '2021-11-12 20:58:51', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1460504920276418561', '2021-11-16 15:08:02', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501764795530477570', '2022-03-10 11:40:03', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501764915156221954', '2022-03-10 11:40:31', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501765024115851265', '2022-03-10 11:40:57', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501765156685217793', '2022-03-10 11:41:29', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501765381776736257', '2022-03-10 11:42:23', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501851134011637761', '2022-03-10 17:23:08', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501851272989900802', '2022-03-10 17:23:41', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501851374773075970', '2022-03-10 17:24:05', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1501851453114286081', '2022-03-10 17:24:24', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '158da8253a07848d3087e27742b90ecf', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '17e1ead2dacd1d312105b396b3517032', '2019-12-28 19:04:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1803c5717dce433515683a91bb6d704b', '2019-12-18 18:30:09', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1923c874226184c056658a742f295534', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1a722cc14550813e4caeb5dee7bd5597', '2019-12-14 12:01:13', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '1ee60d42f423c8e0b1b0438ab69aaba4', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2100000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2101000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2185077a9a1a8c4ed3ae2d892e17db1a', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2200000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2201000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2202000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2203000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '22dad641b94b70574166204f014e462d', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2300000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2304aae6101ef1b56160e5cfe63b997a', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2400000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2401000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2402000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2403000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '241b84a3f3ae7876271634c094dee5cc', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2600000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2601000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '266d5676deeafd74df0febe6a45cc209', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2700000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2a437bae8175be76cb075ea2888f412d', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2b8fa95ebcb3111266ba38f833faf4db', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '2f5d0e7eda34754af13a3d3f519dce19', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '33f86fb25ecde4e2bc5ec0bdf9229e28', '2019-12-28 19:06:53', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '3412a37e6cdc2de99fd534b9327c6288', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '35c3726d4956967e108dd142f71f6627', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '39855c97d751071177ab9e88680b78aa', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '3d73a5a1971f8485850e387e00da4381', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '3fe13d13918b4386750fbc470cb74689', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '43886e8bc03aa4dbbd56b88bc7616b3f', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '4b382a3090885d2783f1f8a1c8e72da9', '2019-12-14 12:00:05', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '4d4ee31e793ab205bd1b0c0796ab464a', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '4e2f07380b3a3a3c83491b44becc87d1', '2020-01-03 19:50:15', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '5a46087c7799d15b3f7dec6373459c18', '2019-12-28 19:03:15', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '5b1483fcac1c14bcabc28b7e50f21921', '2019-12-28 18:59:37', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '5b234a22036af9fcacaad011a3a5a207', '2019-12-14 12:00:29', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '5c4d7e01c344912ab0f55f4c951995f7', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '5d7eca401883fb565e5dfc7b855ed566', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '5f3b1faf8a0b5179bf32acd9759f2bfc', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '62165a91efcb202e65c55cde8a0ef62a', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '6c717f6f1ddc913366e4ba902f84aecf', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '6ce1a6e397603915346dc39d2cf2cd8e', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '6dcb14bf76e04a4f5345a1ec6bd7eacc', '2019-12-18 18:28:31', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '6e46351de075ab9febf8ee6ec17bdced', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '70429f02ff42a948929190d7363cb8ae', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '70a667199c0b5af89a9aec25395de035', '2019-12-14 11:59:33', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '70dbfd7911a393a3c50d1eef09a8708f', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '71efc9c14c1c83dd30c826e82ea33eda', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '74008da0be270ec7c90d9b44d5336e24', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '7b507e991dc0d6364e9ede4a3e3c0f75', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '7b7dce33eb721f48c81c4c245345bf2d', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '7dcb5d797e46db0bfbc8a1bd2b2079b8', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '7f2e1c50777c7b3009fb44af37766a54', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '7f9dde9bc5f370716d2295302b78a5c1', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8000000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8100000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8110000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8111000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8112000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8113000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8114000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8120000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8121000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8122000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8123000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8124000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8125000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8126000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8127000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8130000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8131000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8132000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8133000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8134000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8140000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8141000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8142000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8150000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8151000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8152000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8153000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8154000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8160000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8161000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8162000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8163000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8164000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8170000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8171000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8172000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8173000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8174000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8180000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8181000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8182000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8183000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8184000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8200000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8210000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8220000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8230000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8240000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '82dcc2e0e8ca1c0f8bd4c424a5dda5c5', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8300000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8310000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8320000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8330000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8340000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8398b906bfd903c55d3bab220d95d050', '2019-12-24 16:27:16', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8400000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8410000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8420000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8430000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8500000', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '8aae802c5e76c10fb00c342870aec822', '2019-12-28 19:03:56', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '92e0817e3f1f38b2f307a12dfb57f973', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '9758015e84ca9001f54651db3e215f22', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '997766d975fecca2bbc6d87a1ffa33bc', '2019-12-18 18:29:32', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '99de545b2709c749074253793b0b3579', '2019-12-28 19:04:18', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', '9de3c01d50199f569d58979adba94190', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'a020c0eda490bc5acbbdab8aed5de286', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'a18a3759214257e8ad00b4e8e250b097', '2019-12-24 16:28:12', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'a1b97d911a253bbb7984d914423edab1', '2019-12-24 16:27:43', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'a1d5a717939198826962f7621f86f53d', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'a24da84b7de0787d578d5f60c3803d2f', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'a263bd8cbe22bceab8ecd4f6e2b827b4', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'a96328161a39450238e8cafa243b7c35', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'ae0619ea3e5b86a3e382081680785f7e', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'b7fb7e7511057c292f4e7a567803e49c', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'ba1273686386588c78b20b93fcb1779f', '2019-12-14 11:57:55', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'bec7e2f7cf4ad760bbe550fa05f7eec4', '2019-12-28 19:03:36', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'bf99fd6d49b5db3a107848984daff0ba', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'c0ed022164dbf21954ef79dfb3d70ed3', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'c247293edbfc0ee004f413cb6ee654cd', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'c35e06fc6b2b474a14aff1d7a69aaef9', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'cb3e50350c8d982ee23474146281f7ed', '2020-01-03 19:49:23', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'd2d722dc5a535b918e8edc711e77d5c6', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'd56d79f193c43e90ef3bef5b1d19bee8', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'd9c8041c62c50f859b049e86357d1c41', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'db95df20df51a46afa7935f38efb7360', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'e2ab8932bc86ad98b917c22731205caa', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'e3a14107a9e90f90922f7053a379a719', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'e6a73489db9ef754101570b0e6d0d2e5', '2019-12-24 16:22:03', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'e6ae905517592d8b3f30a0a5a1bfb65b', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'e9c150621e6bcfd7f0cea479d2ab9236', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'eace6e697073de815152466b9c493c4d', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'ec18bd25c6ed99faaa7cc8190fa23db1', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'ec1b689468ac0eab23713ce41273ae4b', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'ec21dec4d5791af4e30c34f7d077f06d', '2019-12-24 16:23:17', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'edcaca9d4cc487aefe2c0c043bb95c30', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'f4eae5341815bec413ef64e3e6524385', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'f513f9e7cb53c16e5de982e12268b070', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'f9ef6fbe98f64ab0f0076d9d51a34f2e', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'fda6b16ece50ede8a7a5bb16213753d0', '2019-12-14 12:00:49', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'fe8074f23fa08934775bc7d06401fd3f', '2019-12-24 16:26:39', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1', 'fef4dfeeb78c4f299e0e6e1a43be3585', '2019-12-14 11:55:14', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '01e9e720e3eedce613a297dcdddb3d63', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '04c920605926e47982b7ede7fa5c3ec6', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '0776fdb3afe857025aefeb6500f67fb6', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '097f4947ac78faf5074c357f5b1ee8f1', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '0d1cfcbd88e13d75ea9796d08b4964a0', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '10e7a8ca4da4f8cd9e36a375d7266f6a', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239766266052329473', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239766777090523137', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239766958087323650', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239767051196678145', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239767117328269313', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239767195036139522', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239767264883884034', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239767506106695682', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1239767627204640770', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1261169795019001858', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1261170252735008769', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1261170652447985665', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1261170764750475265', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1261170872766386178', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1261174275395506178', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1261174360032366593', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1293360350588104706', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1293383822223667201', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1293384016864538626', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1293384142387474433', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1293384241469517825', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1293384332062289922', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1293384409782743041', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298426964249427969', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298427283679232001', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298427420728115202', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298427594900783106', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298427668183662594', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298427749360222210', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298809898521346049', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298809978259259393', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298810074178797570', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298810151446265857', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298810233528795138', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1298980277709717506', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '129a20e268846e9f03e7c7317d34763a', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1309098073269506049', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1309113406327906305', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1309113499143659521', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1309113662792818689', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1309113748939628546', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1309113826735579138', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1309124414236835842', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '17e1ead2dacd1d312105b396b3517032', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1803c5717dce433515683a91bb6d704b', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '1a722cc14550813e4caeb5dee7bd5597', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '33f86fb25ecde4e2bc5ec0bdf9229e28', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '3d73a5a1971f8485850e387e00da4381', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '4b382a3090885d2783f1f8a1c8e72da9', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '4d4ee31e793ab205bd1b0c0796ab464a', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '4e2f07380b3a3a3c83491b44becc87d1', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '5a46087c7799d15b3f7dec6373459c18', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '5b1483fcac1c14bcabc28b7e50f21921', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '5b234a22036af9fcacaad011a3a5a207', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '6c717f6f1ddc913366e4ba902f84aecf', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '6dcb14bf76e04a4f5345a1ec6bd7eacc', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '6e46351de075ab9febf8ee6ec17bdced', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '70a667199c0b5af89a9aec25395de035', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '70dbfd7911a393a3c50d1eef09a8708f', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '71efc9c14c1c83dd30c826e82ea33eda', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '7b7dce33eb721f48c81c4c245345bf2d', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '7f2e1c50777c7b3009fb44af37766a54', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '7f9dde9bc5f370716d2295302b78a5c1', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8000000', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '82dcc2e0e8ca1c0f8bd4c424a5dda5c5', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8300000', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8310000', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8320000', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8330000', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8340000', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8500000', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '8aae802c5e76c10fb00c342870aec822', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '92e0817e3f1f38b2f307a12dfb57f973', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '997766d975fecca2bbc6d87a1ffa33bc', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', '99de545b2709c749074253793b0b3579', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'a020c0eda490bc5acbbdab8aed5de286', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'a1d5a717939198826962f7621f86f53d', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'a263bd8cbe22bceab8ecd4f6e2b827b4', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'ba1273686386588c78b20b93fcb1779f', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'bec7e2f7cf4ad760bbe550fa05f7eec4', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'bf99fd6d49b5db3a107848984daff0ba', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'c247293edbfc0ee004f413cb6ee654cd', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'c35e06fc6b2b474a14aff1d7a69aaef9', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'cb3e50350c8d982ee23474146281f7ed', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'd2d722dc5a535b918e8edc711e77d5c6', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'e6ae905517592d8b3f30a0a5a1bfb65b', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1309132281396396034', 'fda6b16ece50ede8a7a5bb16213753d0', '2020-09-24 22:08:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '01e9e720e3eedce613a297dcdddb3d63', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '04c920605926e47982b7ede7fa5c3ec6', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '0776fdb3afe857025aefeb6500f67fb6', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '088b9371d30c932f9ba555a457a165cb', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '097f4947ac78faf5074c357f5b1ee8f1', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '0d1cfcbd88e13d75ea9796d08b4964a0', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1000000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '10e7a8ca4da4f8cd9e36a375d7266f6a', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1100000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1101000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1102000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1103000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1104000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1105000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1224306298712969218', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1224306628599173121', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1224307007944609794', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226525445274419201', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226525576396750849', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226525700103553025', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226525832920383490', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226525967616262146', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226526082179481602', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226759608866893825', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226759834914713601', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1226760046232137729', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1227568833306177537', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1228676296659161089', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1228676665992794113', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1228676836369616897', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1229322873778180098', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1229323003373785090', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1229675965086429186', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1231205330852978690', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239766266052329473', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239766777090523137', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239766958087323650', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239767051196678145', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239767117328269313', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239767195036139522', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239767264883884034', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239767506106695682', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1239767627204640770', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1243832970918268930', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1243833202171219970', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1243869063717883905', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1250724694023069698', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1250724859953930242', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1250725031559684098', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1250725307804934146', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1250725417255297025', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1250725530589585409', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1261169795019001858', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1261170252735008769', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1261170652447985665', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1261170764750475265', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1261170872766386178', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1261174275395506178', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1261174360032366593', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1265900454069366785', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1265900671623720961', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1265900771255218178', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1265900866067460098', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1265900951765479426', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1265901033235640321', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293360350588104706', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293360792097320962', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293360954580463618', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293361105973866497', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293361232570544129', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293361400409812994', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293361554047168513', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293383822223667201', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293384016864538626', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293384142387474433', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293384241469517825', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293384332062289922', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1293384409782743041', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298426964249427969', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298427283679232001', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298427420728115202', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298427594900783106', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298427668183662594', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298427749360222210', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298809898521346049', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298809978259259393', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298810074178797570', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298810151446265857', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298810233528795138', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1298980277709717506', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '129a20e268846e9f03e7c7317d34763a', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1300000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1301000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1302000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1303000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1304000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1305000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308349941648834562', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308351154087903234', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308353287508049922', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308353386212605954', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308364923052605441', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308365472401571842', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308365991039844354', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308366119851114498', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308366209860878337', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1308366313216917505', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1309098073269506049', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1309113406327906305', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1309113499143659521', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1309113662792818689', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1309113748939628546', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1309113826735579138', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1309124414236835842', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1321281973857124354', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1321283875755884546', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1321284014893531137', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1321284106564239361', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1321284197328977921', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1321284312139661314', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1322075855138095105', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331123534564425730', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331123875771056130', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331124222807769090', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331124349102456833', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331124445831495681', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331124539226062849', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331124631018405890', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331127314508939265', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331127770438172674', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331127886733639681', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331127967310413825', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331128039997702145', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1331128115079938050', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342089211811028994', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342093572213661698', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342093729722359809', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342093905342062593', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342093992382259201', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342112579171999746', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342112864309174273', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342368489077252098', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342368718203691010', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342369468602425345', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342369546528399362', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1342369625242902530', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1346469866533576705', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1346470006900154370', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1346470119282335746', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1346470207647932417', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1346470309233975298', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349244303388487681', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349244680460611585', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349244822907564034', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349245153523576834', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349245241826258946', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349245331873771521', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349258652614176770', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349258902200430594', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1349259096077938689', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1355900128047747074', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1358436360011132929', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1372090280218505217', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1372090492391567361', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1372101740151746561', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1372101887707361282', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1372102024953376769', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1372102231694815234', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1376421493427228674', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1376422486076379138', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1376422678422966274', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1376422796756865025', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1376422875219709954', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1376422957583257601', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1376423034070585346', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1377094310065950721', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1381872508842598401', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1381872757216698369', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1381872968177606657', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1381873672174755842', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1386255289156673537', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1386255440021594113', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1386255544229076993', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1386255636726063106', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1386255721815908354', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1386255797153996801', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1391640356498513922', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1391640959698149378', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1391641292511977473', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1391641504399826945', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1391641564550340609', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1391641697975345154', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1391641880649867266', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1400000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1401000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1402000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1403000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1404000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1452888291086512130', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1452888781744582657', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1452888918969626626', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1452889019288989698', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1452889113044267010', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1452889220854657025', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1453315663338254338', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1454656678447812609', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1454663152586723330', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1454682603688378369', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '158da8253a07848d3087e27742b90ecf', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '17e1ead2dacd1d312105b396b3517032', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1803c5717dce433515683a91bb6d704b', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1a722cc14550813e4caeb5dee7bd5597', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '1ee60d42f423c8e0b1b0438ab69aaba4', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '2100000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '2101000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '2600000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '2601000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '2a437bae8175be76cb075ea2888f412d', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '2f5d0e7eda34754af13a3d3f519dce19', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '33f86fb25ecde4e2bc5ec0bdf9229e28', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '3412a37e6cdc2de99fd534b9327c6288', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '39855c97d751071177ab9e88680b78aa', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '3d73a5a1971f8485850e387e00da4381', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '3fe13d13918b4386750fbc470cb74689', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '43886e8bc03aa4dbbd56b88bc7616b3f', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '4b382a3090885d2783f1f8a1c8e72da9', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '4d4ee31e793ab205bd1b0c0796ab464a', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '4e2f07380b3a3a3c83491b44becc87d1', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '5a46087c7799d15b3f7dec6373459c18', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '5b1483fcac1c14bcabc28b7e50f21921', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '5b234a22036af9fcacaad011a3a5a207', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '5c4d7e01c344912ab0f55f4c951995f7', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '5f3b1faf8a0b5179bf32acd9759f2bfc', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '62165a91efcb202e65c55cde8a0ef62a', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '6c717f6f1ddc913366e4ba902f84aecf', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '6ce1a6e397603915346dc39d2cf2cd8e', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '6dcb14bf76e04a4f5345a1ec6bd7eacc', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '6e46351de075ab9febf8ee6ec17bdced', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '70429f02ff42a948929190d7363cb8ae', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '70a667199c0b5af89a9aec25395de035', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '70dbfd7911a393a3c50d1eef09a8708f', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '71efc9c14c1c83dd30c826e82ea33eda', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '74008da0be270ec7c90d9b44d5336e24', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '7b507e991dc0d6364e9ede4a3e3c0f75', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '7b7dce33eb721f48c81c4c245345bf2d', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '7dcb5d797e46db0bfbc8a1bd2b2079b8', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '7f2e1c50777c7b3009fb44af37766a54', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '7f9dde9bc5f370716d2295302b78a5c1', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8000000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8100000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8110000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8111000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8112000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8113000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8114000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8120000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8121000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8122000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8123000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8124000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8125000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8126000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8127000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8130000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8131000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8132000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8133000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8134000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8140000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8141000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8142000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8150000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8151000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8152000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8153000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8154000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8160000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8161000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8162000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8163000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8164000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8170000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8171000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8172000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8173000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8174000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8180000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8181000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8182000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8183000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8184000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8200000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8210000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8220000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8230000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8240000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '82dcc2e0e8ca1c0f8bd4c424a5dda5c5', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8300000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8310000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8320000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8330000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8340000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8398b906bfd903c55d3bab220d95d050', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8400000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8410000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8420000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8430000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8500000', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '8aae802c5e76c10fb00c342870aec822', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '92e0817e3f1f38b2f307a12dfb57f973', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '9758015e84ca9001f54651db3e215f22', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '997766d975fecca2bbc6d87a1ffa33bc', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', '99de545b2709c749074253793b0b3579', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'a020c0eda490bc5acbbdab8aed5de286', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'a18a3759214257e8ad00b4e8e250b097', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'a1b97d911a253bbb7984d914423edab1', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'a1d5a717939198826962f7621f86f53d', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'a24da84b7de0787d578d5f60c3803d2f', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'a263bd8cbe22bceab8ecd4f6e2b827b4', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'a96328161a39450238e8cafa243b7c35', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'ae0619ea3e5b86a3e382081680785f7e', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'b7fb7e7511057c292f4e7a567803e49c', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'ba1273686386588c78b20b93fcb1779f', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'bec7e2f7cf4ad760bbe550fa05f7eec4', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'bf99fd6d49b5db3a107848984daff0ba', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'c0ed022164dbf21954ef79dfb3d70ed3', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'c247293edbfc0ee004f413cb6ee654cd', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'c35e06fc6b2b474a14aff1d7a69aaef9', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'cb3e50350c8d982ee23474146281f7ed', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'd2d722dc5a535b918e8edc711e77d5c6', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'd56d79f193c43e90ef3bef5b1d19bee8', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'd9c8041c62c50f859b049e86357d1c41', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'db95df20df51a46afa7935f38efb7360', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'e2ab8932bc86ad98b917c22731205caa', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'e6a73489db9ef754101570b0e6d0d2e5', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'e6ae905517592d8b3f30a0a5a1bfb65b', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'eace6e697073de815152466b9c493c4d', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'ec18bd25c6ed99faaa7cc8190fa23db1', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'ec1b689468ac0eab23713ce41273ae4b', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'ec21dec4d5791af4e30c34f7d077f06d', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'f4eae5341815bec413ef64e3e6524385', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'f9ef6fbe98f64ab0f0076d9d51a34f2e', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'fda6b16ece50ede8a7a5bb16213753d0', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'fe8074f23fa08934775bc7d06401fd3f', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1454349176107761666', 'fef4dfeeb78c4f299e0e6e1a43be3585', '2021-11-10 11:50:52', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '0776fdb3afe857025aefeb6500f67fb6', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '0d1cfcbd88e13d75ea9796d08b4964a0', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '1454042553678036993', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '1454652643779022850', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '2185077a9a1a8c4ed3ae2d892e17db1a', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '2304aae6101ef1b56160e5cfe63b997a', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '266d5676deeafd74df0febe6a45cc209', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '2b8fa95ebcb3111266ba38f833faf4db', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '6c717f6f1ddc913366e4ba902f84aecf', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '6e46351de075ab9febf8ee6ec17bdced', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '70dbfd7911a393a3c50d1eef09a8708f', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '7f2e1c50777c7b3009fb44af37766a54', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '8000000', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '82dcc2e0e8ca1c0f8bd4c424a5dda5c5', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '92e0817e3f1f38b2f307a12dfb57f973', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', '9de3c01d50199f569d58979adba94190', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', 'a020c0eda490bc5acbbdab8aed5de286', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', 'bf99fd6d49b5db3a107848984daff0ba', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', 'c35e06fc6b2b474a14aff1d7a69aaef9', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('1458655453256470530', 'e9c150621e6bcfd7f0cea479d2ab9236', '2021-11-11 13:44:52', '-1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '01e9e720e3eedce613a297dcdddb3d63', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '04c920605926e47982b7ede7fa5c3ec6', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '0776fdb3afe857025aefeb6500f67fb6', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '088b9371d30c932f9ba555a457a165cb', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '097f4947ac78faf5074c357f5b1ee8f1', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '0d1cfcbd88e13d75ea9796d08b4964a0', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1000000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '10e7a8ca4da4f8cd9e36a375d7266f6a', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1100000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1101000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1102000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1103000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1104000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1105000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1224306298712969218', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1224306628599173121', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1224307007944609794', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226525445274419201', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226525576396750849', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226525700103553025', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226525832920383490', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226525967616262146', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226526082179481602', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226759608866893825', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226759834914713601', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226760046232137729', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1226762096135340033', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1227568833306177537', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1228676296659161089', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1228676665992794113', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1228676836369616897', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1229322873778180098', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1229323003373785090', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1229675965086429186', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1231205330852978690', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239766266052329473', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239766777090523137', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239766958087323650', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239767051196678145', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239767117328269313', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239767195036139522', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239767264883884034', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239767506106695682', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1239767627204640770', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1243832970918268930', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1243833202171219970', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1243869063717883905', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1250724694023069698', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1250724859953930242', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1250725031559684098', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1250725307804934146', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1250725417255297025', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1250725530589585409', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1261169795019001858', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1261170252735008769', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1261170652447985665', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1261170764750475265', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1261170872766386178', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1261174275395506178', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1261174360032366593', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1265900454069366785', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1265900671623720961', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1265900771255218178', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1265900866067460098', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1265900951765479426', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1265901033235640321', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293360350588104706', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293360792097320962', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293360954580463618', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293361105973866497', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293361232570544129', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293361400409812994', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293361554047168513', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293383822223667201', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293384016864538626', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293384142387474433', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293384241469517825', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293384332062289922', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1293384409782743041', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298426964249427969', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298427283679232001', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298427420728115202', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298427594900783106', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298427668183662594', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298427749360222210', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298809898521346049', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298809978259259393', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298810074178797570', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298810151446265857', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298810233528795138', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1298980277709717506', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '129a20e268846e9f03e7c7317d34763a', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1300000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1301000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1302000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1303000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1304000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1305000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308349941648834562', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308351154087903234', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308353287508049922', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308353386212605954', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308364923052605441', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308365472401571842', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308365991039844354', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308366119851114498', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308366209860878337', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1308366313216917505', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1309098073269506049', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1309113406327906305', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1309113499143659521', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1309113662792818689', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1309113748939628546', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1309113826735579138', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1309124414236835842', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1321281973857124354', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1321283875755884546', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1321284014893531137', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1321284106564239361', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1321284197328977921', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1321284312139661314', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1322075855138095105', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331123534564425730', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331123875771056130', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331124222807769090', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331124349102456833', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331124445831495681', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331124539226062849', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331124631018405890', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331127314508939265', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331127770438172674', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331127886733639681', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331127967310413825', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331128039997702145', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1331128115079938050', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342089211811028994', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342093572213661698', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342093729722359809', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342093905342062593', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342093992382259201', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342112579171999746', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342112864309174273', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342368489077252098', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342368718203691010', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342369468602425345', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342369546528399362', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1342369625242902530', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1346469866533576705', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1346470006900154370', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1346470119282335746', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1346470207647932417', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1346470309233975298', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349244303388487681', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349244680460611585', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349244822907564034', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349245153523576834', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349245241826258946', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349245331873771521', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349258652614176770', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349258902200430594', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1349259096077938689', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1355900128047747074', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1358436360011132929', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1372090280218505217', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1372090492391567361', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1372101740151746561', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1372101887707361282', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1372102024953376769', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1372102231694815234', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1376421493427228674', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1376422486076379138', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1376422678422966274', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1376422796756865025', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1376422875219709954', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1376422957583257601', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1376423034070585346', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1377094310065950721', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1381872508842598401', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1381872757216698369', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1381872968177606657', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1381873672174755842', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1386255289156673537', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1386255440021594113', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1386255544229076993', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1386255636726063106', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1386255721815908354', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1386255797153996801', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1391640356498513922', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1391640959698149378', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1391641292511977473', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1391641504399826945', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1391641564550340609', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1391641697975345154', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1391641880649867266', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1400000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1401000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1402000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1403000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1404000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1452888291086512130', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1452888781744582657', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1452888918969626626', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1452889019288989698', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1452889113044267010', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1452889220854657025', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1453315663338254338', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '158da8253a07848d3087e27742b90ecf', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '17e1ead2dacd1d312105b396b3517032', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1803c5717dce433515683a91bb6d704b', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1a722cc14550813e4caeb5dee7bd5597', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '1ee60d42f423c8e0b1b0438ab69aaba4', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '2100000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '2101000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '2600000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '2601000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '2a437bae8175be76cb075ea2888f412d', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '2f5d0e7eda34754af13a3d3f519dce19', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '33f86fb25ecde4e2bc5ec0bdf9229e28', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '3412a37e6cdc2de99fd534b9327c6288', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '39855c97d751071177ab9e88680b78aa', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '3d73a5a1971f8485850e387e00da4381', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '3fe13d13918b4386750fbc470cb74689', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '43886e8bc03aa4dbbd56b88bc7616b3f', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '4b382a3090885d2783f1f8a1c8e72da9', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '4d4ee31e793ab205bd1b0c0796ab464a', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '4e2f07380b3a3a3c83491b44becc87d1', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '5a46087c7799d15b3f7dec6373459c18', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '5b1483fcac1c14bcabc28b7e50f21921', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '5b234a22036af9fcacaad011a3a5a207', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '5c4d7e01c344912ab0f55f4c951995f7', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '5f3b1faf8a0b5179bf32acd9759f2bfc', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '62165a91efcb202e65c55cde8a0ef62a', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '6c717f6f1ddc913366e4ba902f84aecf', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '6ce1a6e397603915346dc39d2cf2cd8e', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '6dcb14bf76e04a4f5345a1ec6bd7eacc', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '6e46351de075ab9febf8ee6ec17bdced', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '70429f02ff42a948929190d7363cb8ae', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '70a667199c0b5af89a9aec25395de035', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '70dbfd7911a393a3c50d1eef09a8708f', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '71efc9c14c1c83dd30c826e82ea33eda', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '74008da0be270ec7c90d9b44d5336e24', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '7b507e991dc0d6364e9ede4a3e3c0f75', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '7b7dce33eb721f48c81c4c245345bf2d', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '7dcb5d797e46db0bfbc8a1bd2b2079b8', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '7f2e1c50777c7b3009fb44af37766a54', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '7f9dde9bc5f370716d2295302b78a5c1', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8000000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8100000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8110000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8111000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8112000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8113000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8114000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8120000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8121000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8122000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8123000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8124000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8125000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8126000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8127000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8130000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8131000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8132000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8133000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8134000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8140000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8141000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8142000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8150000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8151000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8152000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8153000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8154000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8160000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8161000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8162000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8163000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8164000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8170000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8171000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8172000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8173000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8174000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8180000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8181000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8182000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8183000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8184000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8200000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8210000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8220000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8230000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8240000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '82dcc2e0e8ca1c0f8bd4c424a5dda5c5', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8300000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8310000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8320000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8330000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8340000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8398b906bfd903c55d3bab220d95d050', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8400000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8410000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8420000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8430000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8500000', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '8aae802c5e76c10fb00c342870aec822', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '92e0817e3f1f38b2f307a12dfb57f973', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '9758015e84ca9001f54651db3e215f22', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '997766d975fecca2bbc6d87a1ffa33bc', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', '99de545b2709c749074253793b0b3579', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'a020c0eda490bc5acbbdab8aed5de286', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'a18a3759214257e8ad00b4e8e250b097', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'a1b97d911a253bbb7984d914423edab1', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'a1d5a717939198826962f7621f86f53d', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'a24da84b7de0787d578d5f60c3803d2f', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'a263bd8cbe22bceab8ecd4f6e2b827b4', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'a96328161a39450238e8cafa243b7c35', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'ae0619ea3e5b86a3e382081680785f7e', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'b7fb7e7511057c292f4e7a567803e49c', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'ba1273686386588c78b20b93fcb1779f', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'bec7e2f7cf4ad760bbe550fa05f7eec4', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'bf99fd6d49b5db3a107848984daff0ba', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'c0ed022164dbf21954ef79dfb3d70ed3', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'c247293edbfc0ee004f413cb6ee654cd', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'c35e06fc6b2b474a14aff1d7a69aaef9', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'cb3e50350c8d982ee23474146281f7ed', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'd2d722dc5a535b918e8edc711e77d5c6', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'd56d79f193c43e90ef3bef5b1d19bee8', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'd9c8041c62c50f859b049e86357d1c41', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'db95df20df51a46afa7935f38efb7360', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'e2ab8932bc86ad98b917c22731205caa', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'e6a73489db9ef754101570b0e6d0d2e5', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'e6ae905517592d8b3f30a0a5a1bfb65b', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'eace6e697073de815152466b9c493c4d', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'ec18bd25c6ed99faaa7cc8190fa23db1', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'ec1b689468ac0eab23713ce41273ae4b', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'ec21dec4d5791af4e30c34f7d077f06d', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'f4eae5341815bec413ef64e3e6524385', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'f6cb055276971023060a341236237bb2', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'f9ef6fbe98f64ab0f0076d9d51a34f2e', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'fda6b16ece50ede8a7a5bb16213753d0', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'fe8074f23fa08934775bc7d06401fd3f', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('2', 'fef4dfeeb78c4f299e0e6e1a43be3585', '2021-10-30 15:54:57', '2');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '01e9e720e3eedce613a297dcdddb3d63', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '04c920605926e47982b7ede7fa5c3ec6', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '0776fdb3afe857025aefeb6500f67fb6', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '088b9371d30c932f9ba555a457a165cb', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '097f4947ac78faf5074c357f5b1ee8f1', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '0d1cfcbd88e13d75ea9796d08b4964a0', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '10e7a8ca4da4f8cd9e36a375d7266f6a', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1226525445274419201', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1226525576396750849', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1226525700103553025', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1227568833306177537', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239766266052329473', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239766777090523137', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239766958087323650', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239767051196678145', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239767117328269313', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239767195036139522', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239767264883884034', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239767506106695682', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1239767627204640770', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1261169795019001858', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1261170252735008769', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1261170652447985665', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1261170764750475265', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1261170872766386178', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1261174275395506178', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1261174360032366593', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293360350588104706', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293360792097320962', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293361400409812994', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293361554047168513', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293383822223667201', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293384016864538626', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293384142387474433', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293384241469517825', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293384332062289922', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1293384409782743041', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298426964249427969', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298427283679232001', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298427420728115202', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298427594900783106', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298427668183662594', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298427749360222210', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298809898521346049', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298809978259259393', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298810074178797570', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298810151446265857', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298810233528795138', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1298980277709717506', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '129a20e268846e9f03e7c7317d34763a', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1309098073269506049', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1309113406327906305', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1309113499143659521', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1309113662792818689', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1309113748939628546', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1309113826735579138', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1309124414236835842', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1346469866533576705', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1346470006900154370', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1346470119282335746', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1346470207647932417', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1346470309233975298', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1376421493427228674', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1376422486076379138', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1376422678422966274', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1376422796756865025', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1376422875219709954', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1376422957583257601', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1376423034070585346', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '158da8253a07848d3087e27742b90ecf', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '17e1ead2dacd1d312105b396b3517032', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1803c5717dce433515683a91bb6d704b', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '1a722cc14550813e4caeb5dee7bd5597', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '2a437bae8175be76cb075ea2888f412d', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '33f86fb25ecde4e2bc5ec0bdf9229e28', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '3412a37e6cdc2de99fd534b9327c6288', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '3d73a5a1971f8485850e387e00da4381', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '3fe13d13918b4386750fbc470cb74689', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '4b382a3090885d2783f1f8a1c8e72da9', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '4d4ee31e793ab205bd1b0c0796ab464a', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '4e2f07380b3a3a3c83491b44becc87d1', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '5a46087c7799d15b3f7dec6373459c18', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '5b1483fcac1c14bcabc28b7e50f21921', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '5b234a22036af9fcacaad011a3a5a207', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '6c717f6f1ddc913366e4ba902f84aecf', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '6dcb14bf76e04a4f5345a1ec6bd7eacc', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '6e46351de075ab9febf8ee6ec17bdced', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '70a667199c0b5af89a9aec25395de035', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '70dbfd7911a393a3c50d1eef09a8708f', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '71efc9c14c1c83dd30c826e82ea33eda', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '7b507e991dc0d6364e9ede4a3e3c0f75', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '7b7dce33eb721f48c81c4c245345bf2d', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '7f2e1c50777c7b3009fb44af37766a54', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '7f9dde9bc5f370716d2295302b78a5c1', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8000000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8200000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8240000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '82dcc2e0e8ca1c0f8bd4c424a5dda5c5', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8300000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8310000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8320000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8330000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8340000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8398b906bfd903c55d3bab220d95d050', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8400000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8500000', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '8aae802c5e76c10fb00c342870aec822', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '92e0817e3f1f38b2f307a12dfb57f973', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '997766d975fecca2bbc6d87a1ffa33bc', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', '99de545b2709c749074253793b0b3579', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'a020c0eda490bc5acbbdab8aed5de286', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'a18a3759214257e8ad00b4e8e250b097', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'a1b97d911a253bbb7984d914423edab1', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'a1d5a717939198826962f7621f86f53d', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'a263bd8cbe22bceab8ecd4f6e2b827b4', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'ba1273686386588c78b20b93fcb1779f', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'bec7e2f7cf4ad760bbe550fa05f7eec4', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'bf99fd6d49b5db3a107848984daff0ba', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'c0ed022164dbf21954ef79dfb3d70ed3', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'c247293edbfc0ee004f413cb6ee654cd', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'c35e06fc6b2b474a14aff1d7a69aaef9', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'cb3e50350c8d982ee23474146281f7ed', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'd2d722dc5a535b918e8edc711e77d5c6', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'e2ab8932bc86ad98b917c22731205caa', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'e6a73489db9ef754101570b0e6d0d2e5', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'e6ae905517592d8b3f30a0a5a1bfb65b', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'eace6e697073de815152466b9c493c4d', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'ec21dec4d5791af4e30c34f7d077f06d', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'f9ef6fbe98f64ab0f0076d9d51a34f2e', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'fda6b16ece50ede8a7a5bb16213753d0', '2021-10-30 15:33:39', '1');
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`, `create_time`, `tenant_id`) VALUES ('3', 'fe8074f23fa08934775bc7d06401fd3f', '2021-10-30 15:33:39', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '随机盐',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `organ_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '机构ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `lock_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '0-正常，9-锁定',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT '0' COMMENT '逻辑删除标记（0：显示；1：隐藏）',
  `wx_openid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '微信openid',
  `qq_openid` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'QQ openid',
  `tenant_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '所属租户',
  `type` char(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '1' COMMENT '账号类型：-1、系统管理员；1、租户管理员；2、店铺管理员',
  `nick_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称（客服系统中展示的名称）',
  `shop_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '店铺ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_username` (`username`) USING BTREE,
  UNIQUE KEY `uk_phone` (`phone`),
  UNIQUE KEY `uk_email` (`email`) USING BTREE,
  UNIQUE KEY `uk_wx_openid` (`wx_openid`),
  UNIQUE KEY `uk_qq_openid` (`qq_openid`),
  KEY `user_wx_openid` (`wx_openid`) USING BTREE,
  KEY `user_qq_openid` (`qq_openid`) USING BTREE,
  KEY `ids_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` (`id`, `username`, `email`, `password`, `salt`, `phone`, `avatar`, `organ_id`, `create_time`, `update_time`, `lock_flag`, `del_flag`, `wx_openid`, `qq_openid`, `tenant_id`, `type`, `nick_name`, `shop_id`) VALUES ('1279041761051721730', 'admin', '1023530620@qq.com', '$2a$10$8pmVKv/1aDvj9Z58XbgCgOiAkTsZtJlnR0uZLVgGu0/0hsRh0V1t.', NULL, '15802621888', 'http://minio.joolun.com/joolun/1/user/ff6c9604-ead7-4dfa-9940-da780b34f75a.png', '-1', '2020-07-03 21:18:13', '2021-11-11 19:58:20', '0', '0', NULL, NULL, '-1', '-1', '三直人', NULL);
INSERT INTO `sys_user` (`id`, `username`, `email`, `password`, `salt`, `phone`, `avatar`, `organ_id`, `create_time`, `update_time`, `lock_flag`, `del_flag`, `wx_openid`, `qq_openid`, `tenant_id`, `type`, `nick_name`, `shop_id`) VALUES ('1279042290712625154', 'admin2', '89555587117@qq.com', '$2a$10$8MX2i.kRCWLLUaGjhJsO7.IRZolRjukpS3t88G4fKuMiRp9Pv4n8i', NULL, '18608451675', NULL, '2', '2020-07-03 21:20:20', NULL, '0', '0', NULL, NULL, '2', '1', NULL, NULL);
INSERT INTO `sys_user` (`id`, `username`, `email`, `password`, `salt`, `phone`, `avatar`, `organ_id`, `create_time`, `update_time`, `lock_flag`, `del_flag`, `wx_openid`, `qq_openid`, `tenant_id`, `type`, `nick_name`, `shop_id`) VALUES ('1309132398698496002', 'lishi', 'lishi@qq.com', '$2a$10$sPNob/n7cjM88etDgHXeuugMVXSli1sGr0fqaZKuKC4jUhhhqy0pe', NULL, '18656985321', 'http://minio.joolun.com/joolun/2/material/d852729c-90e8-4ac9-af3c-908df4223c2e.png', '2', '2020-09-24 22:07:41', '2021-10-30 11:40:36', '0', '0', NULL, NULL, '2', '2', 'lishi', '1309131973823905793');
INSERT INTO `sys_user` (`id`, `username`, `email`, `password`, `salt`, `phone`, `avatar`, `organ_id`, `create_time`, `update_time`, `lock_flag`, `del_flag`, `wx_openid`, `qq_openid`, `tenant_id`, `type`, `nick_name`, `shop_id`) VALUES ('1454348737375174658', 'system', '888888@qq.com', '$2a$10$sUlRFAJcFL9ZEipfV8OlbuNhxs3c1e05iCb4Lh1IMrMt.Y06FATQm', NULL, '18888888888', 'https://minio.joolun.com/joolun/1/material/b4546fae-4cd0-48bc-b1af-6b0503845273.png', '-1', '2021-10-30 15:25:34', '2021-11-11 19:58:26', '0', '0', NULL, NULL, '-1', '-1', 'system', '');
INSERT INTO `sys_user` (`id`, `username`, `email`, `password`, `salt`, `phone`, `avatar`, `organ_id`, `create_time`, `update_time`, `lock_flag`, `del_flag`, `wx_openid`, `qq_openid`, `tenant_id`, `type`, `nick_name`, `shop_id`) VALUES ('1454351729310629890', 'mall', '66666666@qq.com', '$2a$10$KEO9QtRVonQucqj.F6R2C.FEUkC.f2TT06lGdVu73JysvzNg/904i', NULL, '16666666666', 'https://minio.joolun.com/joolun/1/material/b4546fae-4cd0-48bc-b1af-6b0503845273.png', '4', '2021-10-30 15:37:27', '2021-11-16 11:23:42', '0', '0', NULL, NULL, '1', '1', 'mall1', '');
INSERT INTO `sys_user` (`id`, `username`, `email`, `password`, `salt`, `phone`, `avatar`, `organ_id`, `create_time`, `update_time`, `lock_flag`, `del_flag`, `wx_openid`, `qq_openid`, `tenant_id`, `type`, `nick_name`, `shop_id`) VALUES ('1454352049017257986', 'huawei', '555555@qq.com', '$2a$10$PklNEJfzxu2d89Lv8YEYy.2xypQ0XwVq3ZCsBkA3sDDTIdw4DHUfS', NULL, '15555555555', 'https://minio.joolun.com/joolun/1/material/b4546fae-4cd0-48bc-b1af-6b0503845273.png', '1', '2021-10-30 15:38:43', NULL, '0', '0', NULL, NULL, '1', '2', 'huawei', '1261176336950480898');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户ID',
  `role_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '角色ID',
  `tenant_id` varchar(32) NOT NULL COMMENT '所属租户',
  PRIMARY KEY (`user_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` (`user_id`, `role_id`, `tenant_id`) VALUES ('1279041761051721730', '1', '-1');
INSERT INTO `sys_user_role` (`user_id`, `role_id`, `tenant_id`) VALUES ('1279042290712625154', '2', '2');
INSERT INTO `sys_user_role` (`user_id`, `role_id`, `tenant_id`) VALUES ('1309132398698496002', '1309132281396396034', '2');
INSERT INTO `sys_user_role` (`user_id`, `role_id`, `tenant_id`) VALUES ('1421293687965552642', '1308708870308347906', '1');
INSERT INTO `sys_user_role` (`user_id`, `role_id`, `tenant_id`) VALUES ('1454348737375174658', '1', '-1');
INSERT INTO `sys_user_role` (`user_id`, `role_id`, `tenant_id`) VALUES ('1454351729310629890', '1454349176107761666', '1');
INSERT INTO `sys_user_role` (`user_id`, `role_id`, `tenant_id`) VALUES ('1454352049017257986', '3', '1');
COMMIT;

-- ----------------------------
-- Table structure for sys_user_tenant
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_tenant`;
CREATE TABLE `sys_user_tenant` (
  `id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'PK',
  `user_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户ID',
  `tenant_id` varchar(32) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '租户ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=DYNAMIC COMMENT='平台系统管理员用户绑定租户关联表';

-- ----------------------------
-- Records of sys_user_tenant
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_tenant` (`id`, `user_id`, `tenant_id`) VALUES ('1458766036639268866', '1279041761051721730', '2');
INSERT INTO `sys_user_tenant` (`id`, `user_id`, `tenant_id`) VALUES ('1458766036639268867', '1279041761051721730', '1');
INSERT INTO `sys_user_tenant` (`id`, `user_id`, `tenant_id`) VALUES ('1458766062149025794', '1454348737375174658', '1');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
