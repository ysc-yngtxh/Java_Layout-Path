/**
 * @author 游家纨绔
 */
/*
三张表怎么连接查询？
      案例：找出每一个员工的部门名称、工资等级以及上级领导
           emp e表
           +-------+-------+---------+--------+------+
           | emp_no| ename | sal     | dept_no| mgr  |  // 员工编号   员工姓名    工资    部门编号   员工的上级领导编号
           +-------+-------+---------+--------+------+
           | 7839  | SMITH | 800.00  | 20     | NULL |
           | 4563  | ALLEN | 3000.00 | 30     | 7263 |
           | 4135  | WARD  | 2005.00 | 20     | 4563 |
           | 7263  | JONES | 1080.00 | 10     | 7839 |
           +-------+-------+-----------+-------------+
           dept d表
           +-----------+-------------+-----------+
           | dept_no   | d_name      | loc       |       // 部门编号   部门名称   部门地址
           +-----------+-------------+-----------+
           | 10        | ACCOUNTING  | NEW YORK  |
           | 20        | RESEARCH    | DALLAS    |
           | 30        | SALES       | CHICAGO   |
           | 40        | OPERATIONS  | BOSTON    |
           +-----------+-------------+-----------+
           sal_grade s表
           +-----------+-------------+-----------+
           | grade     | loSal       | hiSal     |      // 工资等级   员工最低金额   员工最高金额
           +-----------+-------------+-----------+
           | 1         | 700         | 1200      |
           | 2         | 1201        | 1400      |
           | 3         | 1401        | 2000      |
           | 4         | 2001        | 3000      |
           +-----------+-------------+-----------+

           SELECT
              e.ename, d.d_name, s.grade, em.ename '领导'
           FROM emp e
              INNER JOIN
                 dept d
              ON
                 e.dept_no = d.dept_no
              INNER JOIN
                 sal_grade s
              ON
                 e.sal BETWEEN s.loSal AND s.hiSal
              LEFT OUTER JOIN                         // inner和outer可以省略
                 emp em
              ON
                 e.mgr = em.emp_no;    // 因为SMITH领导为null，所以一定要用到外连接。否则SMITH这条数据就会不显示。
      因为我们肯定会选用有null的那张表作为主导，而主导的那张表会显示所有数据。
 */
public class E5_2三张以上表连接查询 {}
