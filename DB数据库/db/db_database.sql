CREATE DATABASE IF NOT EXISTS `db_database` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `t_student`;
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


DROP TABLE IF EXISTS `t_act`;
CREATE TABLE `t_act`(
    `id`      int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `act_no`  int NOT NULL COMMENT '编号',
    `balance` varchar(255) DEFAULT NULL COMMENT '金额',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='活动表';
INSERT INTO `db_databash`.`t_act` (`id`, `act_no`, `balance`) VALUES (1, 111, '10000.0');
INSERT INTO `db_databash`.`t_act` (`id`, `act_no`, `balance`) VALUES (2, 222, '10000.0');


DROP TABLE IF EXISTS `t_emp`;
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
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (1, 1, 'SMITH', 4, 800.00, 13.32, 5, '信息技术支持部', '北京市');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (2, 5, 'ALLEN', 3, 1600.00, 122.44, 3, '行政管理部', '深圳');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (3, 9, 'WARD', 6, 1250.00, 846.75, 1, '产品质量部', '上海市');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (4, 3, 'JONES', 6, 2850.00, NULL, 1, '产品质量部', '上海市');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (5, 8, 'Yau Wai Yee', 2, 31047.97, 266.68, 8, 'Purchasing', '深圳');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (6, 5, 'Gong Jiehong', 0, 44757.87, 391.58, 0, 'Human resource', '深圳');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (7, 7, 'Inoue Momoka', 8, 54929.11, 338.78, 7, 'Research', '东莞');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (8, 6, 'Cheng Chi Ming', 8, 41528.65, 892.20, 6, 'Marketing', '东莞');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (9, 6, 'Tiffany Stone', 4, 28986.97, 579.85, 5, '产品质量部', '东莞');
INSERT INTO `db_database`.`t_emp` (`id`, `emp_no`, `ename`, `mgr`, `sal`, `comm`, `dept_no`, `job`, `province`) VALUES (10, 2, 'Saito Takuya', 10, 59816.50, 597.47, 4, 'Engineering', '东莞');


DROP TABLE IF EXISTS `t_sal_grade`;
CREATE TABLE `t_sal_grade`(
    `id`     BIGINT(20)    PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
    `grade`  VARCHAR(255)  NOT NULL COMMENT '工资等级',
    `lo_sal` DOUBLE(10, 2) DEFAULT NULL COMMENT '最低金额',
    `hi_al`  DOUBLE(10, 2) DEFAULT NULL COMMENT '最高金额'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='工资等级表';
INSERT INTO `db_database`.`t_sal_grade` (`id`, `grade`, `lo_sal`, `hi_al`) VALUES (1, 'A', 1000.00, 2000.00);
INSERT INTO `db_database`.`t_sal_grade` (`id`, `grade`, `lo_sal`, `hi_al`) VALUES (2, 'B', 2000.01, 3000.00);
INSERT INTO `db_database`.`t_sal_grade` (`id`, `grade`, `lo_sal`, `hi_al`) VALUES (3, 'C', 3000.01, 4000.00);
INSERT INTO `db_database`.`t_sal_grade` (`id`, `grade`, `lo_sal`, `hi_al`) VALUES (4, 'D', 4000.01, 5000.00);


DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`(
    `id`      BIGINT(20)   PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
    `dept_no` VARCHAR(255) NOT NULL COMMENT '部门编号',
    `d_name`  VARCHAR(255) DEFAULT NULL COMMENT '部门名称',
    `loc`     VARCHAR(255) DEFAULT NULL COMMENT '部门地址'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='部门信息表';
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (1, '1', '信息技术支持部', '北京市');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (2, '2', '行政管理部', '深圳');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (3, '3', '产品质量部', '上海市');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (4, '4', '工程部', '东莞');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (5, '5', '人力资源部', '广州市');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (6, '6', '财务部', '杭州市');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (7, '7', '市场营销部', '成都市');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (8, '8', '研发中心', '西安市');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (9, '9', '客户服务部', '武汉市');
INSERT INTO `db_database`.`t_dept` (`id`, `dept_no`, `d_name`, `loc`) VALUES (10, '10', '采购部', '南京市');


DROP TABLE IF EXISTS `t_dict`;
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


DROP TABLE IF EXISTS `t_dict_item`;
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


DROP TABLE IF EXISTS `tbl_users`;
CREATE TABLE `tbl_users` (
    `uuid`         int          NOT NULL,
    `customerId`   varchar(200) DEFAULT NULL,
    `pwd`          varchar(20)  DEFAULT NULL,
    `showName`     varchar(100) DEFAULT NULL,
    `trueName`     varchar(100) DEFAULT NULL,
    `registerTime` varchar(100) DEFAULT NULL,
    PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='字典类别表'
(PARTITION p0 VALUES LESS THAN (5) ENGINE = InnoDB,
 PARTITION p1 VALUES LESS THAN (10) ENGINE = InnoDB,
 PARTITION p2 VALUES LESS THAN (15) ENGINE = InnoDB,
 PARTITION p3 VALUES LESS THAN MAXVALUE ENGINE = InnoDB);
INSERT INTO `tbl_users` (`uuid`, `customerId`, `pwd`, `showName`, `trueName`, `registerTime`) VALUES (1, '只为你', '123456', 'qzp', 'quezhipeng', '20200808');
INSERT INTO `tbl_users` (`uuid`, `customerId`, `pwd`, `showName`, `trueName`, `registerTime`) VALUES (6, '人生', '123456', '人生', '无名', '20200808');
INSERT INTO `tbl_users` (`uuid`, `customerId`, `pwd`, `showName`, `trueName`, `registerTime`) VALUES (12, '无须终有', '123456', '无须终有', '无声', '20200808');
INSERT INTO `tbl_users` (`uuid`, `customerId`, `pwd`, `showName`, `trueName`, `registerTime`) VALUES (100, '坚持', '123456', '胜利', '坚持', '20200808');


CREATE TABLE `t_order` (
    `id`           bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `subject`      varchar(256)    DEFAULT NULL COMMENT '订单标题',
    `order_no`     varchar(50)     DEFAULT NULL COMMENT '商户订单编号',
    `total_amount` double          DEFAULT NULL COMMENT '订单金额',
    `status`       varchar(50)     DEFAULT NULL COMMENT '订单状态(WAIT_BUYER_PAY: 交易创建; TRADE_SUCCESS: 支付成功; TRADE_FINISHED: 交易完结; TRADE_CLOSED: 交易关闭)',
    `create_time`  datetime        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  timestamp       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `pay_type`     varchar(50)     DEFAULT NULL COMMENT '支付方式(支付宝; 微信; 银联...)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (1, 'iPhone 15 Pro', 'e8pI9EajHM', 637.42, '仅退款', '2000-10-31 17:18:08', '2025-06-20 09:45:18', '支付宝（Alipay）');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (2, 'Samsung Galaxy S24 Ultra', 'y995xBVgYU', 292.44, '未支付', '2006-09-06 07:10:37', '2025-06-20 09:45:18', '微信支付（WeChat Pay）');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (3, 'Sony WH-1000XM5 降噪耳机', 'eE0z7LMSg5', 905.65, '仅退款', '2000-12-05 21:22:50', '2025-06-20 09:45:18', '银联云闪付（UnionPay QuickPass）');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (4, 'Dyson V12 Detect Slim 吸尘器', 'FiJXVeUEjx', 671.81, '仅退款', '2007-11-03 13:03:54', '2025-06-20 09:45:18', 'Apple Pay');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (5, 'LG 4K OLED 智能电视', 'mQRCUuJeLn', 192.05, '退货退款', '2006-02-18 13:34:37', '2025-06-20 09:45:18', 'Google Pay');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (6, 'MacBook Pro 14\" (M3芯片)', 'utMeobXxtt', 58.18, '仅退款', '2017-06-06 01:53:35', '2025-06-20 09:45:18', '信用卡支付（Visa/MasterCard/银联）');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (7, 'Logitech MX Master 3S 鼠标', 'GWwqFjAc8C', 638.98, '退货退款', '2019-06-01 07:53:57', '2025-06-20 09:45:18', '借记卡支付（储蓄卡）');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (8, 'Nespresso Vertuo 咖啡机', 'xM3ebbkEIF', 665.16, '仅退款', '2000-09-07 15:16:18', '2025-06-20 09:45:18', 'PayPal（国际支付）');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (9, 'Oral-B iO9 电动牙刷', '2CHUtHnfd7', 911.71, '未支付', '2017-10-31 12:22:46', '2025-06-20 09:45:18', '数字货币支付（BTC/USDT等）');
INSERT INTO `db_database`.`t_order` (`id`, `subject`, `order_no`, `total_amount`, `status`, `create_time`, `update_time`, `pay_type`) VALUES (10, 'GoPro HERO12 Black 运动相机', 'KJTV6UpxGR', 457.33, '退货退款', '2021-03-12 11:50:35', '2025-06-20 09:45:18', '货到付款（COD, Cash on Delivery）');
