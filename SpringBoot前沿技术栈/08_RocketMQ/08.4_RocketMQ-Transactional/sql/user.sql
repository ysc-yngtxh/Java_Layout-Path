-- ----------------------------
-- 用户表
-- ----------------------------
CREATE TABLE `t_user`(
    `id`              int(11)        NOT NULL AUTO_INCREMENT COMMENT '用户表',
    `name`            varchar(16)    NOT NULL COMMENT '姓名',
    `id_card`         varchar(32)    NOT NULL COMMENT '身份证号',
    `balance`         decimal(10, 2) NOT NULL DEFAULT '0' COMMENT '余额',
    `state`           tinyint(1)     DEFAULT NULL COMMENT '状态（1在线，0离线）',
    `vip_flag`        tinyint(1)     NOT NULL DEFAULT '0' COMMENT 'VIP用户标识（1是，0否）',
    `create_time`     datetime       NOT NULL COMMENT '创建时间',
    `last_login_time` datetime       DEFAULT NULL COMMENT '最后一次登录时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (1, '上野光', '790069793077506155', 42.16, 0, 0, '2015-06-06 08:16:32', '2021-01-23 10:18:00');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (2, '雷云熙', '267639973198149676', 23.38, 1, 0, '2001-07-26 12:29:57', '2001-01-13 20:39:38');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (3, '平野大輔', '807723859427828676', 88.86, 0, 0, '2009-03-04 02:21:28', '2009-02-17 13:22:35');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (4, '中山和真', '959278739638332553', 53.27, 1, 0, '2015-04-27 17:31:52', '2024-07-11 09:34:03');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (5, '蔣仲賢', '044024186543270208', 6.81, 1, 1, '2015-11-11 22:10:53', '2006-05-18 02:22:38');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (6, '加藤花', '008910416019533834', 54.14, 0, 1, '2006-06-04 16:36:44', '2016-04-01 03:29:13');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (7, '内田愛梨', '841145887325983593', 5.93, 0, 0, '2008-08-26 22:41:20', '2002-01-18 09:04:50');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (8, '吳霆鋒', '066168905597798931', 13.11, 1, 1, '2000-04-26 14:05:58', '2023-08-04 04:58:10');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (9, '韩詩涵', '362391538582144383', 58.18, 0, 0, '2022-03-09 03:47:35', '2000-07-16 20:45:20');
INSERT INTO `technician`.`t_user` (`id`, `name`, `id_card`, `balance`, `state`, `vip_flag`, `create_time`, `last_login_time`) VALUES (10, '史云熙', '170544782252699462', 70.00, 0, 1, '2014-09-08 15:44:54', '2020-06-26 14:54:16');


-- ----------------------------
-- 积分表
-- ----------------------------
CREATE TABLE `t_credit`(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT '积分表',
    `user_id`     int(11)     NOT NULL COMMENT '用户id',
    `user_name`   varchar(16) NOT NULL COMMENT '用户姓名',
    `integration` int(11)     NOT NULL DEFAULT '0' COMMENT '积分',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='积分表';
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (1, 1, '上野光', 792);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (2, 7, '内田愛梨', 335);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (3, 6, '加藤花', 469);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (4, 7, '内田愛梨', 366);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (5, 5, '蔣仲賢', 467);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (6, 4, '中山和真', 153);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (7, 7, '内田愛梨', 2);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (8, 8, '吳霆鋒', 584);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (9, 1, '上野光', 691);
INSERT INTO `technician`.`t_credit` (`id`, `user_id`, `user_name`, `integration`) VALUES (10, 7, '内田愛梨', 4);


-- ----------------------------
-- 事务日志表
-- ----------------------------
CREATE TABLE `t_mq_transaction_log`(
    `transaction_id` varchar(64) NOT NULL COMMENT '事务id',
    `log`            varchar(64) NOT NULL COMMENT '日志',
    PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事务日志表';
