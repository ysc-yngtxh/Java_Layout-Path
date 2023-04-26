/*
子查询
   1、什么是子查询？子查询都可以出现在哪里？
        select语句当中嵌套select语句，被嵌套的select语句是子查询。
              子查询可以出现的位置：
             select
                ..(select).
             from
                ..(select).
             where
                ..(select).

   2、where子句中使用子查询
           案例：找出高于平均薪资的员工信息
           select * from emp where sal > (select avg(sal) from emp);

   3、from后面嵌套子查询
             案例：找出每个部门平均薪水的薪资等级。
           select
              t.*,s.grade
           from
              (select deptno,avg(sal) from emp group by deptno) t
           join
              salgrade s
           on
              t.avgsal between s.losal and s.hisal;
           //这语句的意思是：先运行from，找出每个部门的平均薪水
                          然后将以上的查询结果(部门名称，平均薪水)当作临时表t，让t表和salgrade表连接

             案例：找出每个部门平均的薪资等级。
                方法一：
                      第一步：找出每个员工的薪资等级              第二步：基于以上结果，继续按照deptno分组，求grade平均值
             select                                           select
                e.ename,e.sal,e.deptno,s.grade                   t.deptno,avg(t.grade)
             from                                             from
                emp e                                            (select
             join                                                    e.deptno,avg(s.grade)
                salgrade s                                        from
             on                                                      emp e
                e.sal between losal and hisal;                    join
                                                                     salgrade s
                                                                  on
                                                                     e.sal between s.losal and s.hisal) t
                                                               group by
                                                                  t.deptno;


                方法二：
             select
                e.deptno,avg(s.grade)
             from
                emp e
             join
                salgrade s                   //这里就没有使用from嵌套子查询，因为emp e表与salgrade s表连接后需要查询的都在里面
             on                                就没有必要强行嵌套子查询，这种方法查询效率更高
                e.sal between s.losal and s.hisal
             group by
                e.deptno;


  4、在select后面嵌套子查询
        案例：找出每个员工所在的部门名称，要求显示员工名和部门名

         通常写法：                                      嵌套写法：
         select                                         select
            e.ename,d.dname                                e.enamem,
         from                                              (select d.dname from dept d where e.deptno = d.deptno) as dname
            emp e                                       from
         join                                              emp e;
            dept d
         on
            e.deptno = d.deptno;
 */

/**
 * @author 游家纨绔
 */
public class F6子查询 {
}
