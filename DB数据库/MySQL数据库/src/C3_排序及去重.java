/**
 * @author 游家纨绔
 */
/* 一、排序（order by 升序，降序）
 *
 *       CREATE TABLE t_emp(
 *          `id` INT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
 *          `emp_no` BIGINT(20) COMMENT '员工编号',
 *          `ename` VARCHAR(255) COMMENT '员工姓名',
 *          `mgr` BIGINT(20) COMMENT '领导编号',
 *          `sal` DOUBLE(10, 2) COMMENT '工资',
 *          `comm` DOUBLE(10, 2) COMMENT '津贴',
 *          `dept_no` BIGINT(20) COMMENT '部门编号',
 *          `job` VARCHAR(255) COMMENT '工作岗位',
 *          `province` VARCHAR(255) COMMENT '省份'
 *       ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='员工信息表';
 *
 *      （1）按照工资升序，找出员工名和薪资
 *               SELECT
 *                   ename,sal
 *               FROM
 *                   t_emp
 *               ORDER BY
 *                   sal;
 *               注意：默认是升序
 *
 *      （2）指定升序或者降序。asc 表是升序。desc表示降序
 *               SELECT ename,sal FROM t_emp ORDER BY sal;       // 升序
 *               SELECT ename,sal FROM t_emp ORDER BY sal ASC;   // 升序
 *               SELECT ename,sal FROM t_emp ORDER BY sal DESC;  // 降序
 *
 *      （3）例如：按照工资的降序排列，当工资相同的时候再按照名字的升序排列
 *               SELECT ename,sal FROM t_emp ORDER BY sal DESC, ename ASC;
 *               注意：越靠前的字段越能起到主导作用。只有当前面的字段无法完成排序的时候，才会启用后面的字段
 *
 *               SELECT ename,sal FROM t_emp ORDER BY 1;   // 代表的是表格的第一列，即ename列
 *               SELECT ename,sal FROM t_emp ORDER BY 2;   // 代表的是表格的第二列，即sal列
 *               注意: 表格的列顺序会因为select语法中字段顺序的改变而改变，所以这样的语法不健壮，
 *                    尽量不在Java语言中使用这种语法，知道有这种写法即可！
 * ---------------------------------------------------------------------------------------------------------------
 * 二、去除重复记录(distinct)
 *       distinct只能出现在所有字段的最前面，去除的是所有的字段联合的重复数据
 *       应用场景
 *          查询工作岗位: SELECT job FROM t_emp;
 *                      +--------------+
 *                      | job          |
 *                      +--------------+
 *                      | CLERK        |
 *                      | PRESIDENT    | 我们可以发现工作岗位中出现了重复的PRESIDENT，我们如何去除重复的呢？
 *                      | MANAGER      |
 *                      | PRESIDENT    |
 *                      +--------------+
 *          示例一：查询不同部门的工作岗位
 *                 SELECT DISTINCT dept_no,job FROM t_emp; // 这个SQL语句表达的意思是去除 (部门与工作岗位联合的) 重复数据
 *
 *          示例二：统计岗位的数量
 *                 SELECT COUNT(DISTINCT job) FROM t_emp;  // 这个SQL语句表达的意思是统计不重复岗位的数量
 */
public class C3_排序及去重 {}
