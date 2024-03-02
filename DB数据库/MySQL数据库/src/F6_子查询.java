/**
 * @author 游家纨绔
 */
/*
子查询
   1、什么是子查询？子查询都可以出现在哪里？
        select语句当中嵌套select语句，被嵌套的select语句是子查询。
             子查询可以出现的位置：
             SELECT
                ...(select)
             FROM
                ...(select)
             WHERE
                ...(select)
---------------------------------------------------------------------------------------------------------------
   2、WHERE 子句中使用子查询
        案例：找出高于平均薪资的员工信息
             SELECT * FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);
---------------------------------------------------------------------------------------------------------------
   3、FROM 后面嵌套子查询
        案例一：找出每个部门平均薪水的薪资等级。
               SELECT
                  t.*, s.grade
               FROM
                  (SELECT dept_no, AVG(sal) avgSal FROM emp GROUP BY dept_no) t
               JOIN
                  sal_grade s
               ON
                  t.avgSal BETWEEN s.loSal AND s.hiSal;
               // 使用子查询找出每个部门的平均薪水，然后将以上的查询结果(部门名称，平均薪水)当作临时表t，让t表和sal_grade表连接

        案例二：找出每个部门平均的薪资等级。
               方法一：
                   第①步：找出每个员工的薪资等级                第②步：基于以上结果，继续按照dept_no分组，求grade平均值
                          SELECT                                   SELECT
                             e.ename, e.sal, e.dept_no, s.grade        t.dept_no, AVG(t.grade)
                          FROM                                     FROM
                             emp e                                    (SELECT
                          JOIN                                            e.dept_no, AVG(s.grade)
                             sal_grade s                                FROM
                          ON                                              emp e
                             e.sal BETWEEN loSal AND hiSal;            JOIN
                                                                          sal_grade s
                                                                       ON
                                                                          e.sal BETWEEN s.loSal AND s.hiSal) t
                                                                   GROUP BY
                                                                      t.dept_no;
               方法二：
                   SELECT
                      e.dept_no, AVG(s.grade)
                   FROM
                      emp e
                   JOIN
                      sal_grade s   // 这里就没有使用from嵌套子查询，因为 emp e表 与 sal_grade s表 连接后需要查询的都在里面
                   ON                  就没有必要强行嵌套子查询，这种方法查询效率更高
                      e.sal BETWEEN s.loSal AND s.hiSal
                   GROUP BY
                      e.dept_no;
---------------------------------------------------------------------------------------------------------------
  4、在 SELECT 后面嵌套子查询
        案例：找出每个员工所在的部门名称，要求显示员工名和部门名
             通常写法：                     嵌套写法：
             SELECT                        SELECT
                e.ename, d.d_name             e.ename,
             FROM                             (SELECT d.d_name FROM dept d WHERE e.dept_no = d.dept_no) AS d_name
                emp e                      FROM
             JOIN                             emp e;
                dept d
             ON
                e.dept_no = d.dept_no;
 */
public class F6_子查询 {
}
