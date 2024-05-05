/**
 * @author 游家纨绔
 */
/*
MySQL作业题

       CREATE TABLE t_emp(
          `id` INT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
          `emp_no` BIGINT(20) COMMENT '员工编号',
          `ename` VARCHAR(255) COMMENT '员工姓名',
          `mgr` BIGINT(20) COMMENT '领导编号',
          `sal` DOUBLE(10, 2) COMMENT '工资',
          `comm` DOUBLE(10, 2) COMMENT '津贴',
          `dept_no` BIGINT(20) COMMENT '部门编号',
          `job` VARCHAR(255) COMMENT '工作岗位',
          `province` VARCHAR(255) COMMENT '省份'
       ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='员工信息表';

       CREATE TABLE sal_grade(
          `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
          `grade` VARCHAR(255) COMMENT '工资等级',
          `loSal` DOUBLE(10, 2) COMMENT '最低金额',
          `hiSal` DOUBLE(10, 2) COMMENT '最高金额'
       ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='工资等级表';

       CREATE TABLE dept(
          `id` BIGINT(20) PRIMARY KEY AUTO_INCREMENT COMMENT '主键Id',
          `dept_no` VARCHAR(255) COMMENT '部门编号',
          `d_name` VARCHAR(255) COMMENT '部门名称',
          `loc` VARCHAR(255) COMMENT '部门地址'
       ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='部门信息表';

       1、取得每个部门最高薪水的人员名称。
       2、哪些人的薪水在部门的平均薪水之上
       3、取得每个部门平均的薪水等级
       4、不准用（max），取得最高薪水（给出两种解决方案）
       5、取得平均薪水最高的部门的部门编号（给出两种解决方案）
       6、取得平均薪水最高的部门的部门名称
       7、求平均薪水的级别最低的部门的部门名称
       8、取得比普通员工（员工代码没有在mgr字段上出现的）的最高薪水还要高的领导人姓名.
       9、取得每个薪水级别有多少员工
       10、列出所有员工及领导的姓名
       11、求出部门名称中，带'S'字符的部门员工的工资合计、部门人数
       12、给员工加薪10%
       13、面试题
             有3个表 s(学生表),c(课程表),sc(学生选课表)
                s(sno, s_name) 代表 (学号，姓名)
                c(cno, cname, c_teacher) 代表 (课号，课名，教师)
                sc(sno, cno, sc_grade) 代表 (学号，课号，成绩)
             问题：
                 1)、找出没选过“黎明”老师的所有学生姓名
                 2)、列出2门以上（含2门）不及格学生姓名及平均成绩
                 3)、既学过1号课程又学过2号课所有学生的姓名










       1、取得每个部门最高薪水的人员名称。
          SELECT
             e.dept_no, e.name, e.sal
          FROM
             t_emp e
          INNER JOIN
             (SELECT dept_no, MAX(sal) AS maxSal FROM t_emp GROUP BY dept_no) t
          ON
             e.dept_no=t.dept_no AND e.sal=t.maxSal;

       2、哪些人的薪水在部门的平均薪水之上
          SELECT
             e.name, e.sal
          FROM
             t_emp e
          INNER JOIN
             (SELECT dept_no, AVG(sal) AS avgSal FROM t_emp GROUP BY dept_no) t
          ON
             e.dept_no=t.dept_no AND e.sal>t.avgSal;

       3、取得每个部门平均的薪水等级
          SELECT
             e.dept_no, e.avgSal, s.grade
          FROM
             (SELECT dept_no, AVG(sal) AS avgSal FROM t_emp GROUP BY dept_no) e
          INNER JOIN
             sal_grade s
          ON
             e.avgSal between s.loSal and s.hiSal

       4、不准用（max），取得最高薪水（给出两种解决方案）
          第一种：SELECT sal FROM t_emp ORDER BY sql DESC LIMIT 1;
          第二种：SELECT sal FROM t_emp WHERE sal not in(SELECT a.sal FROM t_emp a INNER JOIN t_emp b ON a.sal<b.sal);

       5、取得平均薪水最高的部门的部门编号（给出两种解决方案）
          第一种：SELECT dept_no, AVG(sal) as avgSal FROM t_emp GROUP BY dept_no ORDER BY avgSal DESC LIMIT 1;
          第二种：SELECT dept_no, MAX(avgSal) FROM t_emp (SELECT dept_no,AVG(sal) AS avgSal FROM t_emp GROUP BY dept_no);

       6、取得平均薪水最高的部门的部门名称
          SELECT
             d.d_name, e.AVG(sal) AS avgSal
          FROM
             t_emp e
          INNER JOIN
             dept d
          ON
             e.dept_no=d.dept_no
          GROUP BY
             d.d_name
          ORDER BY
             avgSal DESC
          LIMIT 1;

       7、求平均薪水的级别最低的部门的部门名称
          SELECT
             d.d_name, AVG(e.sal) AS avgSal
          FROM
             t_emp e
          INNER JOIN
             dept d
          ON
             e.dept_no=d.dept_no
          INNER JOIN
             sal_grade s
          ON
             avgSal between loSal and hiSal
          GROUP BY
             d.d_name
          ORDER BY
             grade asc
          LIMIT 1;

       8、取得比普通员工（员工代码没有在mgr字段上出现的）的最高薪水还要高的领导人姓名.
          SELECT
             name, sal
          FROM
             t_emp
          WHERE
             sal > (SELECT MAX(sal)
                    FROM t_emp                  // 嵌套里的语句是普通员工的最高薪水
                    WHERE t_emp not in(select distinct mgr from t_emp where mgr is not null)
                    )

       9、取得每个薪水级别有多少员工
          SELECT
             s.grade, COUNT(*)
          FROM
             t_emp e
          INNER JOIN
             sal_grade s
          ON
             sal between loSal and hiSal
           GROUP BY
              s.grade;

       10、列出所有员工及领导的姓名
           SELECT
              a.ename AS '员工名', b.ename AS '领导名'
           FROM
              t_emp a
           LEFT OUTER JOIN
              t_emp b
           ON
              a.mgr=b.emp_no;
           // 这里选用的是左外连接而没有选用自连接的原因是t_emp表中有一个mgr(领导)为null，选用了自连接就无法显示其领导为null的员工。

       11、求出部门名称中，带'S'字符的部门员工的工资合计、部门人数
           SELECT
              d.dept_no, IFNULL(SUM(e.sal), 0), COUNT(e.ename)
           FROM
              t_emp e
           RIGHT OUTER JOIN
              dept d
           ON
              e.dept_no=d.dept_no
           WHERE
              d.d_name LIKE '%S%'
           GROUP BY
              d.dept_no

       12、给员工加薪10%
           UPDATE t_emp SET sal=sal*1.1;

       13、面试题
             有3个表 s(学生表),c(课程表),sc(学生选课表)
                s(sno, s_name) 代表 (学号，姓名)
                c(cno, cname, c_teacher) 代表 (课号，课名，教师)
                sc(sno, cno, sc_grade) 代表 (学号，课号，成绩)
             问题：
                 1)、找出没选过"黎明"老师的所有学生姓名
                 2)、列出2门以上（含2门）不及格学生姓名及平均成绩
                 3)、既学过1号课程又学过2号课所有学生的姓名
 */
public class 作业题 {
}
