/**
 * @author 游家纨绔
 */
/*
一、排序（order by 升序，降序）
    按照工资升序，找出员工名和薪资
       select
          ename,sal
       from
          emp
       order by
          sal;
          注意：默认是升序

          怎么指定升序或者降序呢？
          asc 表是升序。desc表示降序
          select ename,sal from emp order by sal;       //升序
          select ename,sal from emp order by sal asc;   //升序
          select ename,sal from emp order by sal desc;  //降序

System.out.println("=================================================================================================");

          例如：按照工资的降序排列，当工资相同的时候再按照名字的升序排列
          select ename,sal from emp order by sal desc , ename asc;
                  注意：越靠前的字段越能起到主导作用。只有当前面的字段无法完成排序的时候，才会启用后面的字段

          select ename,sal from emp order by 1;   //代表的是表格的第一列，即ename列
          select ename,sal from emp order by 2;   //代表的是表格的第二列，即sal列
                  注意:表格的列顺序会因为select语法中字段顺序的改变而改变，所以这样的语法不健壮，不可以在Java语言中使用这种语法。
                       自己调试着玩的时候可以方便使用


二、去除重复记录(distinct)
         distinct只能出现在所有字段的最前面，去除的是所有的字段联合的重复数据

      例如：查询工作岗位
         select job from emp;
           +---------------------+
           | job                 |
           +---------------------+
           | CLERK               |
           | PRESIDENT           |
           | MANAGER             |
           | PRESIDENT           |
           +---------------------+
                我们可以发现工作岗位中出现了重复的PRESIDENT，我们如何去除重复的呢？

        例如：查询不同部门的工作岗位
        select distinct dephno,job from emp;   //这个SQL语句表达的意思是去除 (部门与工作岗位联合的) 重复数据

        案例：统计岗位的数量
        select count(distinct job) from emp;    //这个SQL语句表达的意思是统计不重复岗位的数量
 */
public class C3排序及去重 {
}
