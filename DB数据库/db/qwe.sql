CREATE TABLE t_emp(
    `id`       INT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
    `emp_no`   BIGINT(20) COMMENT '员工编号',
    `ename`    VARCHAR(255) COMMENT '员工姓名',
    `mgr`      BIGINT(20) COMMENT '领导编号',
    `sal`      DOUBLE(10, 2) COMMENT '工资',
    `comm`     DOUBLE(10, 2) COMMENT '津贴',
    `dept_no`  BIGINT(20) COMMENT '部门编号',
    `job`      VARCHAR(255) COMMENT '工作岗位',
    `province` VARCHAR(255) COMMENT '省份'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='员工信息表';
