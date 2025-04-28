/**
 * @author 游家纨绔
 */
/* 一、条件查询
 *     DROP TABLE IF EXISTS `t_emp`;
 *     CREATE TABLE `t_emp`(                                          语法格式：
 *         `id`       int NOT NULL AUTO_INCREMENT COMMENT '主键Id',        SELECT
 *         `emp_no`   bigint       DEFAULT NULL COMMENT '员工编号',            字段1, 字段2...     3
 *         `ename`    varchar(255) DEFAULT NULL COMMENT '员工姓名',         FROM
 *         `mgr`      bigint       DEFAULT NULL COMMENT '领导编号',            表名                1
 *         `sal`      double(10,2) DEFAULT NULL COMMENT '工资',            WHERE
 *         `comm`     double(10,2) DEFAULT NULL COMMENT '津贴',               条件                2
 *         `dept_no`  bigint       DEFAULT NULL COMMENT '部门编号',
 *         `job`      varchar(255) DEFAULT NULL COMMENT '工作岗位',
 *         `province` varchar(255) DEFAULT NULL COMMENT '省份',
 *         PRIMARY KEY (`id`)
 *     ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工信息表';
 * ---------------------------------------------------------------------------------------------------------------
 * 二、条件查询中会用到的运算符：
 *     1、between ... and ... (是一个闭区间，取其范围内)
 *          查询员工工资在1100和3000之间的员工，包括1100和3000。
 *             SELECT ename,sal FROM t_emp WHERE sal >= 1100 AND sal <= 3000;
 *             SELECT ename,sal FROM t_emp WHERE sal BETWEEN 1100 AND 3000;  // between...and...是闭区间[1100 ~ 3000]
 *     2、is null (null代表的是不确定值，不能用等号衡量，必须使用 is null 或者 is not null)
 *          查询哪些员工津贴为空。
 *              SELECT ename,sal,comm FROM t_emp WHERE comm IS NULL;
 *     3、is not null
 *     4、or (条件'或者'语句)
 *          查询工作岗位是MANAGER和SALESMAN的员工。
 *               SELECT ename,job FROM t_emp WHERE job='MANAGER' OR job='SALESMAN';
 *               注意：不能是and,如果是and，那么意思就变成了工作岗位即是MANAGER又是SALESMAN的员工
 *     5、and (条件'并且'语句)
 *           查询薪资大于1000的并且部门编号是20或30部门的员工
 *               SELECT ename,sal,depth_no FROM t_emp WHERE sal > 1000 AND (dept_no=20 OR dept_no=30);
 *               注意：and的优先级比or的要高，不加小括号出来的表格式会是使部门编号30的所有员工显示出来
 *     6、in(语句会优先执行in()中的。确定条件查询中给定的值是否与列表中的值相匹配)
 *           查询通过b表查到的id的数据，去匹配a表中的id然后得到结果
 *               SELECT * FROM a WHERE a.id IN(SELECT b.id AS bid FROM b)
 *               如果b表数据中某个id在a表中不存在，那这个结果会忽略(不会出现异常也不会显示为null)
 *               +----+--------+                +----+---------+                +----+--------+
 *           a表 |  id | user  ｜           b表  | id | phone   |           结果 ｜ id | user   |
 *               +----+--------+                +----+---------+                +----+--------+
 *               |  0 | 杜子韬  |                |  0 | 4532534 |                |  0 | 杜子韬  |
 *               |  1 | 陈嘉琪  |                |  1 | 4356345 |                |  1 | 陈嘉琪  |
 *               | 10 | 邵荣珍  |                |  2 | 45634   |                +----+--------+
 *               +----+--------+                +----+---------+
 *           分析：①、首先执行子查询 SELECT b.id AS bid FROM b
 *                ②、将查询到的结果和原有的a表做一个笛卡尔积(3 * 3)
 *                ③、此时，再根据我们的 a.id IN(bid) 的条件，将结果进行筛选（即比较 a表id列 和 b表id列 的值是否相等，将不相等的删除）
 *           总结：可以看出,当B表数据较大时不适合使用in(),因为它会B表数据全部遍历一次.
 *                如:  A表有 1W 条记录,B表有 100W 条记录,那么最多有可能遍历 1W*100W 次,效率很差.
 *                再如: A表有 1W 条记录,B表有 100 条记录,那么最多有可能遍历 1W*100 次,
 *                     遍历次数大大减少,效率大大提升适合于in()中的数据小的情况，数据大的话效率极其低下。
 *     7、not in(就是不在这个范围内的数据)
 *     8、exists(优先执行exists()语句外的语句)
 *           SELECT * FROM a WHERE EXISTS (SELECT b.id FROM b WHERE a.id = b.id)
 *           分析：①、使用exists关键字进行查询的时候，首先，我们先查询的不是子查询的内容，而是查我们的主查询的表SELECT * FROM a
 *                ②、然后，根据表的每一条记录，执行以下语句，依次去判断where后面的条件是否成立
 *                ③、如果成立则返回true不成立则返回false。如果返回的是true的话，则该行结果保留，如果返回的是false的话，则删除该行。
 *           总结：当B表比A表数据大时适合使用exists(),因为它没有那么遍历操作,只需要再执行一次查询就行.
 *                如: A表有 1W 条记录,B表有 100W 条记录,那么exists()会执行 1W 次去判断A表中的id是否与B表中的id相等.
 *                如: A表有 1W 条记录,B表有 10000W 条记录,那么exists()还是执行 1W 次,因为它只执行A.length次,
 *                    可见B表数据越多,越适合exists()发挥效果.
 *                再如: A表有 1W 条记录,B表有100条记录,那么exists()还是执行 1W 次,还不如使用in()遍历 1W*100 次,
 *                     因为 in() 是在内存里遍历比较,而exists()需要查询数据库,我们都知道查询数据库所消耗的性能更高,而内存比较很快.
 *     9、not exists(就是不在这个范围内的数据)
 *     10、like(模糊查询)
 * ---------------------------------------------------------------------------------------------------------------
 * 三、模糊查询like
 *       在模糊查询当中，必须掌握两个特殊的符号，一个是'%',一个是'_'
 *       '%' 代表任意多个字符，'_' 代表任意1个字符。
 *
 *       例：(1)、找出名字当中含有字母 'O' 的
 *               SELECT ename FROM t_emp WHERE ename LIKE '%O%';
 *           (2)、找出名字当中第二个字母是 A 的
 *               SELECT ename FROM t_emp WHERE enamel LIKE '_A%';
 *           (3)、找出名字中有下划线(_)的
 *               SELECT ename FROM t_emp WHERE enamel LIKE '%\_%';    // 通过\来转义_，就可以实现查找下划线
 *           (4)、找出名字中最后一个字母是T的
 *               SELECT ename FROM t_emp WHERE enamel LIKE '%T';
 *           注意：模糊查询不需要和字段名个数一致
 */
public class B2_条件查询 {}
