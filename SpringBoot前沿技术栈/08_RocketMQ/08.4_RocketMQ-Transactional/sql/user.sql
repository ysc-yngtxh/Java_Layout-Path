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


-- ----------------------------
-- 积分表
-- ----------------------------
CREATE TABLE `t_credit`(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT '积分表',
    `user_id`     int(11)     NOT NULL COMMENT '用户id',
    `user_name`    varchar(16) NOT NULL COMMENT '用户姓名',
    `integration` int(11)     NOT NULL DEFAULT '0' COMMENT '积分',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='积分表';



-- ----------------------------
-- 事务日志表
-- ----------------------------
CREATE TABLE `t_mq_transaction_log`(
    `transaction_id` varchar(64) NOT NULL COMMENT '事务id',
    `log`            varchar(64) NOT NULL COMMENT '日志',
    PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='事务日志表';
