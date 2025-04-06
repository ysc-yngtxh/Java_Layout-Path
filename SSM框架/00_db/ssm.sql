DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`(
    `id`    int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `name`  varchar(255) DEFAULT NULL COMMENT '用户名',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `age`   int          DEFAULT NULL COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1021 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (1, 'Yao Xiuying', 'xiuyiyao1030@yahoo.com', 600);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (2, 'Keith Davis', 'davikeith@gmail.com', 35);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (3, '敏敏', 'yotai@outlook.com', 22);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (4, 'Chiang Kwok Wing', 'chiangkw814@yahoo.com', 672);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (5, '游诗成', 'onyung@hotmail.com', 25);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (6, 'Noguchi Mio', 'noguchimi@gmail.com', 640);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (7, 'Wan Suk Yee', 'wansy1956@icloud.com', 199);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (8, '敏敏', '12387@11.com', 22);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (9, 'Maeda Daisuke', 'daisumaeda4@icloud.com', 601);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (10, '敏敏', '12345@11.com', 22);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (11, '曹操', 'caocao@163.com', 23);
INSERT INTO `ssm`.`student` (`id`, `name`, `email`, `age`) VALUES (1020, '我喜欢敏敏', 'lijingjing@163.com', 19);


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
    `employee_id` int NOT NULL AUTO_INCREMENT COMMENT '员工Id',
    `employee_name` varchar(255) DEFAULT NULL COMMENT '员工名称',
    `employee_department_id` int NOT NULL COMMENT '员工部门Id',
    `employee_grade_id` int NOT NULL COMMENT '员工等级Id',
    `employee_salary` int DEFAULT NULL COMMENT '员工工资',
    PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (1, 'Zhao Zitao', 4, 4, 4286);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (2, 'Otsuka Minato', 4, 5, 9799);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (3, 'Ye Anqi', 9, 4, 5178);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (4, 'Lo Wing Kuen', 1, 1, 6378);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (5, 'Ma Xiuying', 10, 4, 4363);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (6, 'Hu Jiehong', 4, 2, 4620);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (7, 'Mo Zhiyuan', 9, 2, 4560);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (8, 'Ishikawa Ryota', 4, 3, 8193);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (9, 'He Yunxi', 6, 3, 8028);
INSERT INTO `ssm`.`employee` (`employee_id`, `employee_name`, `employee_department_id`, `employee_grade_id`, `employee_salary`) VALUES (10, 'Hu Jiehong', 5, 3, 6867);


DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
    `id` int NOT NULL AUTO_INCREMENT,
    `order_time` datetime DEFAULT NULL,
    `money` double DEFAULT NULL,
    `uid` int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
