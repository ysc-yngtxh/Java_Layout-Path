DROP TABEL IF EXISTS `t_student`;
CREATE TABLE `t_student`(
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `no`          int          NOT NULL COMMENT '编号',
    `name`        varchar(255) DEFAULT NULL COMMENT '用户名',
    `age`         int          DEFAULT NULL COMMENT '年龄',
    `height`      double       DEFAULT NULL COMMENT '身高',
    `sex`         char(11)     NOT NULL COMMENT '性别',
    `class_no`    varchar(255) DEFAULT '' COMMENT '班级编号',
    `birth`       char(10)     NOT NULL COMMENT '生日',
    `time`        datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '入学时间',
    `update_time` timestamp    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `graduate`    tinyint(1)   DEFAULT 1 COMMENT '是否毕业',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';


DROP TABEL IF EXISTS `t_act`;
CREATE TABLE `t_act`(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `act_no`  int NOT NULL COMMENT '编号',
    `balance` varchar(255) DEFAULT NULL COMMENT '金额',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动表';
INSERT INTO `db_databash`.`t_act` (`id`, `act_no`, `balance`) VALUES (1, 111, '10000.0');
INSERT INTO `db_databash`.`t_act` (`id`, `act_no`, `balance`) VALUES (2, 222, '10000.0');


DROP TABEL IF EXISTS `t_emp`;
CREATE TABLE `t_emp`(
    `id`       int          NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `emp_no`   bigint       DEFAULT NULL COMMENT '员工编号',
    `ename`    varchar(255) DEFAULT NULL COMMENT '员工姓名',
    `mgr`      bigint       DEFAULT NULL COMMENT '领导编号',
    `sal`      double(10,2) DEFAULT NULL COMMENT '工资',
    `comm`     double(10,2) DEFAULT NULL COMMENT '津贴',
    `dept_no`  bigint       DEFAULT NULL COMMENT '部门编号',
    `job`      varchar(255) DEFAULT NULL COMMENT '工作岗位',
    `province` varchar(255) DEFAULT NULL COMMENT '省份',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工信息表';


CREATE TABLE `t_dict`(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `dict_name`   varchar(255) DEFAULT NULL COMMENT '字典名称',
    `dict_code`   varchar(255) DEFAULT NULL COMMENT '字典编码',
    `description` varchar(255) DEFAULT NULL COMMENT '描述',
    `del_flag`    varchar(255) DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';
INSERT INTO `db_database`.`t_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`) VALUES (1, '支付方式', 'PAY_METHOD', '支付方式', '0');
INSERT INTO `db_database`.`t_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`) VALUES (2, '订单状态', 'ORDER_STATUS', '订单状态', '0');


CREATE TABLE `t_dict_item`(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `dict_id`     int NOT NULL COMMENT '字典Id',
    `item_text`   varchar(255) DEFAULT NULL COMMENT '子项内容',
    `item_value`  varchar(255) DEFAULT NULL COMMENT '子项值',
    `description` varchar(255) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典子项表';
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (1, 1, '支付宝', 'ZFB', '支付宝');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (2, 1, '微信', 'WX', '微信');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (3, 1, '银联', 'YL', '银联');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (4, 2, '待付款', 'WAIT_BUYER_PAY', '待付款');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (5, 2, '交易成功', 'TRADE_SUCCESS', '交易成功');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (6, 2, '订单取消成功', 'CANCEL_SUCCESS', '订单取消成功');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (7, 2, '退款成功', 'REFUSE_SUCCESS', '退款成功');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (8, 2, '退款失败', 'REFUSE_FAIL', '退款失败');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (9, 2, '订单已关闭', 'TRADE_CLOSED', '订单已关闭');
