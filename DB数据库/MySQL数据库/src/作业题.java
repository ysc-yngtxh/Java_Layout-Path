/*
MySQL作业题(表参考E5)

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
           s(sno,sname) 代表 (学号，姓名)
           c(cno,cname,cteacher) 代表 (课号，课名，教师)
           sc(sno,cno,scgrade) 代表 (学号，课号，成绩)
           问题：
           1、找出没选过“黎明”老师的所有学生姓名
           2、列出2门以上（含2门）不及格学生姓名及平均成绩
           3、既学过1号课程又学过2号课所有学生的姓名





     1、取得每个部门最高薪水的人员名称。
        select
           e.deptno,e.name,e.sal
        from
           emp e,
           (select deptno,max(sal) as maxsal from emp group by deptno) t
        where
           e.deptno=t.deptno,
           e.sal=t.maxsal;

     2、哪些人的薪水在部门的平均薪水之上
        select e.name,e.sal from emp e,(select deptno,avg(sal) as avgsal from emp where group by deptno) t where e,sal>t.avgsal;

     3、取得每个部门平均的薪水等级
        select e.deptno,s.grade from emp e inner join salgrade s on e.sal between losal and hisal group by e.deptno;

     4、不准用（max），取得最高薪水（给出两种解决方案）
          第一种：
        select sal from emp order by sql desc limit 1;
          第二种：(表的自连接)
        select sal from emp where sal not in (select a.sal from emp a inner join emp b on a.sal<b.sal);

     5、取得平均薪水最高的部门的部门编号（给出两种解决方案）
          第一种：
        select deptno,avg(sal) as avgsal from emp group by deptno order by avgsal desc limit 1;
          第二种：
        select deptno,max(avgsal) from emp (select deptno,avg(sal) as avgsal from emp group by deptno);

     6、取得平均薪水最高的部门的部门名称
        select d.dname,e.avg(sal) as avgsal from emp e inner join dept d on e.deptno=d.depyno group by d.dname order by avgsal desc limit 1;

     7、求平均薪水的级别最低的部门的部门名称
        select
           d.dname,avg(e.sal) as avgsal
        from
           emp e
        inner join
           dept d
        on
           e.deptno=d.deptno
        inner join
           salgrade s
        on
           avgsal between losal and hisal
        group by
           d.dname
        order by
           grade asc
        limit
           1;

     8、取得比普通员工（员工代码没有在mgr字段上出现的）的最高薪水还要高的领导人姓名.
        select
           name,sal
        from
           emp
        where
           sal > (select
                     max(sal)
                  from
                     emp                  //嵌套里的语句是普通员工的最高薪水
                  where
                     emp not in(select distinct mgr from emp where mgr is not null)
                  )

     9、取得每个薪水级别有多少员工
        select
           s.grade,count(*)
        from
           emp e
        inner join
           salgrade s
        on
           sal between losal and hisal
         group by
            s.grade;

     10、列出所有员工及领导的姓名
        select
           A.ename as '员工名',B.ename as '领导名'
        from
           emp A
        left outer join
           emp B
        on
           A.mgr=B.empno;
        // 这里选用的是左外连接而没有选用自连接的原因是emp表中有一个mgr(领导)为null，选用了自连接就无法显示其领导为null的员工。

     11、求出部门名称中，带'S'字符的部门员工的工资合计、部门人数
        select
           d.deptno,d.dname,d.loc,count(e.ename),ifnull(sum(e.sal),0)
        from
           emp e
        right outer join
           dept d
        on
           e.deptno=d.deptno
        where
           d.dname like '%S%'
        group by
           d.deptno,d.dname,d.loc

     12、给员工加薪10%
        update emp set sal=sal*1.1;

     13、面试题
                有3个表 s(学生表),c(课程表),sc(学生选课表)
           s(sno,sname) 代表 (学号，姓名)
           c(cno,cname,cteacher) 代表 (课号，课名，教师)
           sc(sno,cno,scgrade) 代表 (学号，课号，成绩)
           问题：
           1、找出没选过“黎明”老师的所有学生姓名
           2、列出2门以上（含2门）不及格学生姓名及平均成绩
           3、既学过1号课程又学过2号课所有学生的姓名
 */
/**
 * @author 游家纨绔
 */
public class 作业题 {
}
