DROP TABEL IF EXISTS `t_student`;
CREATE TABLE `t_student`(
    `id`   int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `no`   int NOT NULL COMMENT '编号',
    `name` varchar(255) DEFAULT NULL COMMENT '用户名',
    `age`  int          DEFAULT NULL COMMENT '年龄',
    `height` double DEFAULT NULL COMMENT '身高',
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
