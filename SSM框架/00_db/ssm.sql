DROP TABLE IF EXISTS `ssm_student`;
CREATE TABLE `ssm_student`(
    `id`    int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `name`  varchar(255) DEFAULT NULL COMMENT '用户名',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `age`   int          DEFAULT NULL COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '学生表';
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (1, 'Yao Xiuying', 'xiuyiyao1030@yahoo.com', 600);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (2, 'Keith Davis', 'davikeith@gmail.com', 35);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (3, '敏敏', 'yotai@outlook.com', 22);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (4, 'Chiang Kwok Wing', 'chiangkw814@yahoo.com', 672);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (5, '游家纨绔', 'onyung@hotmail.com', 25);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (6, 'Noguchi Mio', 'noguchimi@gmail.com', 640);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (7, 'Wan Suk Yee', 'wansy1956@icloud.com', 199);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (8, '敏敏', '12387@11.com', 22);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (9, 'Maeda Daisuke', 'daisumaeda4@icloud.com', 601);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (10, '敏敏', '12345@11.com', 22);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (11, '曹操', 'caocao@163.com', 23);
INSERT INTO `ssm`.`ssm_student` (`id`, `name`, `email`, `age`) VALUES (1020, '我喜欢敏敏', 'lijingjing@163.com', 19);


DROP TABLE IF EXISTS `ssm_employee`;
CREATE TABLE `ssm_employee` (
    `employee_id`            int          NOT NULL AUTO_INCREMENT COMMENT '员工Id',
    `employee_name`          varchar(255) DEFAULT NULL COMMENT '员工名称',
    `employee_department_id` int          NOT NULL COMMENT '员工部门Id',
    `employee_grade_id`      int          NOT NULL COMMENT '员工等级Id',
    `employee_salary`        int          DEFAULT NULL COMMENT '员工工资',
    PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '员工表';
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (1, 'Zhao Zitao', 4, 4, 4286);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (2, 'Otsuka Minato', 4, 5, 9799);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (3, 'Ye Anqi', 9, 4, 5178);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (4, 'Lo Wing Kuen', 1, 1, 6378);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (5, 'Ma Xiuying', 10, 4, 4363);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (6, 'Hu Jiehong', 4, 2, 4620);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (7, 'Mo Zhiyuan', 9, 2, 4560);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (8, 'Ishikawa Ryota', 4, 3, 8193);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (9, 'He Yunxi', 6, 3, 8028);
INSERT INTO `ssm`.`ssm_employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (10, 'Hu Jiehong', 5, 3, 6867);


DROP TABLE IF EXISTS `ssm_user`;
CREATE TABLE `ssm_user` (
    `id`        int          NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
    `birth_day` datetime     DEFAULT NULL COMMENT '生日',
    `sex`       varchar(255) DEFAULT NULL COMMENT '性别',
    `address`   varchar(255) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户表';
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (1, '汤宇宁', '2006-10-05 23:42:38', '男', '东泰五街580号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (2, '杨秀英', '2019-04-26 10:27:03', '女', '徐汇区虹桥路881号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (3, '卢子异', '2020-02-22 01:59:18', '男', '浦东新区健祥路19号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (4, '曹云熙', '2008-04-27 06:16:04', '男', '白云区机场路棠苑街五巷440号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (5, '郝璐', '2017-06-08 16:16:18', '女', '龙岗区学园一巷608号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (6, '许致远', '2019-05-31 08:31:23', '男', '紫马岭商圈中山五路108号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (7, '袁宇宁', '2002-02-21 14:39:21', '男', '东泰五街287号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (8, '林嘉伦', '2007-05-05 20:08:33', '男', '成华区二仙桥东三路49号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (9, '贺岚', '2021-08-29 05:27:16', '女', '海珠区江南西路361号');
INSERT INTO `ssm`.`ssm_user` (`id`, `user_name`, `birth_day`, `sex`, `address`) VALUES (10, '叶致远', '2012-02-22 05:10:46', '男', '天河区大信商圈大信南路647号');


DROP TABLE IF EXISTS `ssm_role`;
CREATE TABLE `ssm_role` (
    `id`        int          NOT NULL AUTO_INCREMENT COMMENT '角色Id',
    `role_name` varchar(255) DEFAULT NULL COMMENT '角色名称',
    `role_desc` varchar(255) DEFAULT NULL COMMENT '角色描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '角色表';
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (1, '管理员', '管理人员账号');
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (2, '超级管理员', 'super管理人员');
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (3, '匿名用户', '低级用户');
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (4, '用户(注册)', '一般用户');
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (5, 'VIP用户', 'VIP用户');
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (6, 'SVIP用户', '高级用户');
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (7, '黑卡用户', '顶级用户权限');
INSERT INTO `ssm`.`ssm_role` (`id`, `role_name`, `role_desc`) VALUES (8, '全权限用户', '全知全能');


DROP TABLE IF EXISTS `ssm_user_role`;
CREATE TABLE `ssm_user_role` (
    `id` int NOT NULL COMMENT '主键Id',
    `uid` int NOT NULL COMMENT '用户Id',
    `rid` int NOT NULL COMMENT '角色Id',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户角色关联表';
INSERT INTO `ssm`.`ssm_user_role` (`id`, `uid`, `rid`) VALUES (1, 1, 4);
INSERT INTO `ssm`.`ssm_user_role` (`id`, `uid`, `rid`) VALUES (2, 1, 7);
INSERT INTO `ssm`.`ssm_user_role` (`id`, `uid`, `rid`) VALUES (3, 1, 8);
INSERT INTO `ssm`.`ssm_user_role` (`id`, `uid`, `rid`) VALUES (4, 4, 1);
INSERT INTO `ssm`.`ssm_user_role` (`id`, `uid`, `rid`) VALUES (5, 4, 2);
INSERT INTO `ssm`.`ssm_user_role` (`id`, `uid`, `rid`) VALUES (6, 5, 1);


DROP TABLE IF EXISTS `ssm_order`;
CREATE TABLE `ssm_order` (
    `id`         int      NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `order_time` datetime DEFAULT NULL COMMENT '订单创建时间',
    `money`      double   DEFAULT NULL COMMENT '订单商品价格',
    `uid`        int      DEFAULT NULL COMMENT '订单外键id',
    PRIMARY KEY (`id`),
    KEY `uid` (`uid`),
    CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `ssm_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '订单表';
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (1, '2014-10-09 09:06:15', 482.45, 8);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (2, '2016-01-06 08:31:45', 662.9, 8);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (3, '2015-12-18 13:31:05', 947.92, 7);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (4, '2004-03-11 22:26:27', 699.45, 6);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (5, '2003-03-12 05:25:27', 289.92, 8);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (6, '2015-01-28 16:18:36', 449.26, 7);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (7, '2006-04-25 06:10:02', 299.42, 6);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (8, '2010-06-10 17:59:02', 501.8, 1);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (9, '2022-05-10 22:15:21', 47.37, 4);
INSERT INTO `ssm`.`ssm_order` (`id`, `order_time`, `money`, `uid`) VALUES (10, '2015-09-14 10:46:00', 596.7, 10);
