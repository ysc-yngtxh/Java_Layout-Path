/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80033
 Source Host           : localhost:3306
 Source Schema         : xxl_job

 Target Server Type    : MySQL
 Target Server Version : 80033
 File Encoding         : 65001

 Date: 12/08/2023 17:49:53
*/
DROP DATABASE IF EXISTS xxl_job;
CREATE DATABASE xxl_job;
USE xxl_job;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group` (
  `id` int NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `address_type` tinyint NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_group` (`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL, '2023-04-27 10:17:49');
INSERT INTO `xxl_job_group` (`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (4, 'demo-xxl-job', 'jobs', 1, 'http://192.168.1.14:9999', '2023-04-26 20:30:06');
INSERT INTO `xxl_job_group` (`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (5, 'demo-xxl-job1', '自带demo', 1, 'http://192.168.1.14:8088', '2023-04-26 20:36:23');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (1, 1, '测试任务1', '2018-11-03 22:21:31', '2023-04-26 20:43:51', 'XXL', '', 'CRON', '0/5 0 0 * * ? *', 'DO_NOTHING', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 0, 0, 0);
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (6, 4, '定时任务', '2023-02-25 20:57:34', '2023-04-27 10:15:19', '游诗成', 'youshicheng97@163.com', 'CRON', '0/5 * * * * ?', 'DO_NOTHING', 'FIRST', 'myJobHandler', '123', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2023-02-25 20:57:34', '', 0, 0, 0);
INSERT INTO `xxl_job_info` (`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`, `trigger_status`, `trigger_last_time`, `trigger_next_time`) VALUES (8, 5, 'xxl-job自带demo', '2023-04-26 20:38:17', '2023-04-26 20:38:17', '游诗成', 'youshicheng97@163.com', 'CRON', '0/3 * * * * ?', 'DO_NOTHING', 'FIRST', 'demoJobHandler', '456', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2023-04-26 20:38:17', '', 0, 0, 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_lock` (`lock_name`) VALUES ('schedule_lock');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `job_group` int NOT NULL COMMENT '执行器主键ID',
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB AUTO_INCREMENT=781 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (752, 4, 6, '192.168.1.8', 'myJobHandler', '123', NULL, 0, '2023-04-04 10:52:36', 500, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[192.168.1.8]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.1.8<br>code：500<br>msg：xxl-job remoting error(no protocol: 192.168.1.8/run), for url : 192.168.1.8/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (753, 4, 6, 'http://192.168.1.7:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:03:40', 500, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.7:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.7:8088<br>code：500<br>msg：xxl-job remoting error(connect timed out), for url : http://192.168.1.7:8088/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (754, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:04:10', 500, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：500<br>msg：xxl-job remoting error(Connection refused: connect), for url : http://192.168.1.8:8088/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (755, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:39:45', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 11:39:46', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (756, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:40:29', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 11:40:29', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (757, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:41:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 11:41:00', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (758, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:41:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 11:41:05', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (759, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:41:10', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 11:41:10', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (760, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 11:41:15', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 11:41:15', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (761, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 13:11:58', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 13:11:59', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (762, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 13:12:17', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 13:12:17', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (763, 4, 6, 'http://192.168.1.8:8088', 'myJobHandler', '123', NULL, 0, '2023-04-04 13:23:32', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.8<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.8:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.8:8088<br>code：200<br>msg：null', '2023-04-04 13:23:32', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (764, 4, 6, '192.168.1.14:8082', 'myJobHandler', '123', NULL, 0, '2023-04-24 23:40:41', 500, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[192.168.1.14:8082]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：192.168.1.14:8082<br>code：500<br>msg：xxl-job remoting error(no protocol: 192.168.1.14:8082/run), for url : 192.168.1.14:8082/run', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (767, 4, 6, 'http://192.168.1.14:8088', 'myJobHandler', '123', NULL, 0, '2023-04-25 00:25:07', 500, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:8088<br>code：500<br>msg：job handler [myJobHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (768, 4, 6, 'http://192.168.1.14:8088', 'myJobHandler', '123', NULL, 0, '2023-04-26 17:02:22', 500, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:8088<br>code：500<br>msg：job handler [myJobHandler] not found.', NULL, 0, NULL, 2);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (771, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-26 20:33:57', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-26 20:33:58', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (772, 5, 8, 'http://192.168.1.14:8088', 'demoJobHandler', '456', NULL, 0, '2023-04-26 20:44:37', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:8088]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:8088<br>code：200<br>msg：null', '2023-04-26 20:44:48', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (773, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-26 20:45:41', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-26 20:45:41', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (774, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-26 20:52:34', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-26 20:52:35', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (775, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-26 21:02:52', 200, '任务触发类型：手动触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-26 21:02:52', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (776, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-27 10:15:00', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-27 10:15:00', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (777, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-27 10:15:05', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-27 10:15:05', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (778, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-27 10:15:10', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-27 10:15:10', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (779, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-27 10:15:15', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-27 10:15:15', 200, '', 0);
INSERT INTO `xxl_job_log` (`id`, `job_group`, `job_id`, `executor_address`, `executor_handler`, `executor_param`, `executor_sharding_param`, `executor_fail_retry_count`, `trigger_time`, `trigger_code`, `trigger_msg`, `handle_time`, `handle_code`, `handle_msg`, `alarm_status`) VALUES (780, 4, 6, 'http://192.168.1.14:9999', 'myJobHandler', '123', NULL, 0, '2023-04-27 10:15:20', 200, '任务触发类型：Cron触发<br>调度机器：192.168.1.14<br>执行器-注册方式：手动录入<br>执行器-地址列表：[http://192.168.1.14:9999]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>任务超时时间：0<br>失败重试次数：0<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：http://192.168.1.14:9999<br>code：200<br>msg：null', '2023-04-27 10:15:20', 200, '', 0);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (1, '2023-02-25 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (2, '2023-02-24 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (3, '2023-02-23 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (4, '2023-02-26 00:00:00', 0, 0, 3, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (5, '2023-02-27 00:00:00', 0, 0, 5, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (6, '2023-02-28 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (7, '2023-03-01 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (8, '2023-03-09 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (9, '2023-03-08 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (10, '2023-03-07 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (11, '2023-03-12 00:00:00', 0, 0, 5, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (12, '2023-03-11 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (13, '2023-03-10 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (14, '2023-04-04 00:00:00', 0, 9, 3, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (15, '2023-04-03 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (16, '2023-04-02 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (17, '2023-04-24 00:00:00', 0, 0, 1, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (18, '2023-04-23 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (19, '2023-04-22 00:00:00', 0, 0, 0, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (20, '2023-04-25 00:00:00', 0, 0, 1, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (21, '2023-04-26 00:00:00', 0, 5, 1, NULL);
INSERT INTO `xxl_job_log_report` (`id`, `trigger_day`, `running_count`, `suc_count`, `fail_count`, `update_time`) VALUES (22, '2023-04-27 00:00:00', 0, 5, 0, NULL);
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue` (
  `id` int NOT NULL AUTO_INCREMENT,
  `job_id` int NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_registry
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_registry` (`id`, `registry_group`, `registry_key`, `registry_value`, `update_time`) VALUES (91, 'EXECUTOR', 'demo-xxl-job1', 'http://192.168.1.14:8088/', '2023-04-27 10:17:55');
INSERT INTO `xxl_job_registry` (`id`, `registry_group`, `registry_key`, `registry_value`, `update_time`) VALUES (92, 'EXECUTOR', 'demo-xxl-job', 'http://192.168.1.14:9999/', '2023-04-27 10:18:05');
COMMIT;

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
BEGIN;
INSERT INTO `xxl_job_user` (`id`, `username`, `password`, `role`, `permission`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
