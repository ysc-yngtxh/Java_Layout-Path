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

   2、where子句中使用子查询
        案例：找出高于平均薪资的员工信息
        SELECT * FROM emp WHERE sal > (SELECT AVG(sal) FROM emp);

   3、from后面嵌套子查询
        案例：找出每个部门平均薪水的薪资等级。
        SELECT
           t.*, s.grade
        FROM
           (SELECT deptno, AVG(sal) FROM emp GROUP BY deptno) t
           JOIN
              salgrade s
           ON
              t.avgsal BETWEEN s.losal AND s.hisal;
        // 使用子查询找出每个部门的平均薪水，然后将以上的查询结果(部门名称，平均薪水)当作临时表t，让t表和salgrade表连接

        案例：找出每个部门平均的薪资等级。
           方法一：
             第①步：找出每个员工的薪资等级                第②步：基于以上结果，继续按照deptno分组，求grade平均值
             SELECT                                   SELECT
                e.ename, e.sal, e.deptno, s.grade        t.deptno, AVG(t.grade)
             FROM                                     FROM
                emp e                                    (SELECT
             JOIN                                            e.deptno, AVG(s.grade)
                salgrade s                                FROM
             ON                                              emp e
                e.sal BETWEEN losal AND hisal;            JOIN
                                                             salgrade s
                                                          ON
                                                             e.sal BETWEEN s.losal AND s.hisal) t
                                                      GROUP BY
                                                         t.deptno;
          方法二：
            SELECT
               e.deptno, AVG(s.grade)
            FROM
               emp e
            JOIN
               salgrade s       // 这里就没有使用from嵌套子查询，因为emp e表与salgrade s表连接后需要查询的都在里面
            ON                     就没有必要强行嵌套子查询，这种方法查询效率更高
               e.sal BETWEEN s.losal AND s.hisal
            GROUP BY
               e.deptno;


  4、在select后面嵌套子查询
        案例：找出每个员工所在的部门名称，要求显示员工名和部门名

         通常写法：                        嵌套写法：
         SELECT                           SELECT
            e.ename,d.dname                  e.enamem,
         FROM                                (SELECT d.dname FROM dept d WHERE e.deptno = d.deptno) AS dname
            emp e                         FROM
         JOIN                                emp e;
            dept d
         ON
            e.deptno = d.deptno;
 */

/**
 * @author 游家纨绔
 */
public class F6子查询 {
}
