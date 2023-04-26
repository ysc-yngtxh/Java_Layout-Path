/**
 * @author 游家纨绔
 */ /*
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

二、聚合函数(也叫做：多行处理函数:就是综合多行数据，然后再输出)
     count  计数
     sum    求和
     avg    平均值
     max    最大值
     min    最小值

     记住：所有的分组函数都是对“某一组”数据进行操作的

    例：找出工资总和
     select sum(sal) from emp;
     找出最高工资
     select max(sal) from emp;
     找出最低工资
     select min(sal) from emp;
     找出平均工资
     select avg(sal) from emp;
     找出总人数
     select count(*) from emp;       //不是统计某个字段中数据的个数，而是统计总记录条数（和某个字段无关）
     select count(ename) from emp;   //表示统计comm字段中部为null的数据总数量

    分组函数自动忽略null
    select sum(comm) from emp where is not null;
    //这么写意思是：不是bull的津贴(comm)之和。
          但是不需要后面的where语法，因为分组函数自动忽略null
          直接写select sum(comm) from emp;就好了.

    例：计算每个员工的年薪
    select ename,(sal+comm)*12 as yearsal from emp;
       注意：因为不是每个员工都有津贴(comm)的，有的员工津贴(comm)为null。但是所有的数据库这样规定，只要有null参与的运算结果一定是null
                 但是如果计算出来的员工年薪为null的话，人家早跑了，所以，怎么办呢？--------——————链接到单行处理函数

    例：找出工资高于平均工资的员工
    select ename,sal from emp where sal > avg(sal);
          思考以上的错误信息：无效的使用了分组函数
              原因：SQL语句当中有一个语法规则，分组函数不可直接使用在where字句当中，why？--------——————链接到group by和 having

三、单行处理函数
      单行处理函数就是输入一行，输出一行。

    ifnull()空处理函数
       ifnull(可能为null的数据 ， 被当作什么处理)  :属于单行处理函数
       select ename,ifnull(comm,0) as comm from emp;  //这里就表示了如果员工津贴(comm)为null的话，就当作是值0处理

      计算每个员工的年薪
       select enamel,(sal+ifnull(comm,0))*12 as yearsal fron emp;

四、group by 和 having

      group by : 按照某个字段或者某些字段进行分组。比如一张表里的工作类型，有重复的工作类型，也有不重复的工作类型。先进行分组，重复的为一组。
      having   : having是对分组之后的数据进行再次过滤

            注意：分组函数一般都会和group by联合使用，这也是为什么他被称为分组函数的原因
                并且任何一个分组函数都是在group by语句执行结束之后才会执行的。
                当一条SQL语句没有group by的话，整张表的数据会自成一组。

      执行顺序：
       select      5
         ...
       from        1
         ...
       where       2
         ...
       group by    3
         ...
       having      4
         ...
       order by    6
         ...


         找出工资高于平均工资的员工
         select ename,sal from emp where sal > avg(sal);
                      注意：这样写是错误的。因为avg(sal)是属于分组函数，但是在执行where的时候，并没有执行分组语句group by.
                            所以，这么写SQL语句是错误的。

                第一步：select avg(sal) from emp;  //from后面其实有group by，只是缺省了。
                第二步：select ename,sal from emp where sal > 表给的实际数据;
                合成一步：select ename,sal from emp where sal > (select avg(sal) from emp);//小括号优先级高，所以先执行小括号里的


      案例：找出每个工作岗位的最高薪资
      select ename,job,max(sal) from emp group by job;  //语法错误
               注意：以上在MySQL当中，查询结果是有的，但是结果没有意义，因为ename是随机的。在Oracle数据库当中会报错。语法错误。
              Oracle的语法规则比MySQL语法规则严谨
              记住一条规则：当一条语句中有group by的话，select后面只能跟分组函数和参与分组的字段


      案例：找出每个部门的不同工作岗位的最高薪资
      select deptno,job,max(sal) from emp group by deptno,job;
              +-----------+-------------+-----------+
              | deptno    | job         | sal       |
              +-----------+-------------+-----------|
              | 10        | CLERK       | 800.00    |
              +-----------+-------------+-----------+
              | 10        | PRESIDENT   | 1300.00   |
              +-----------+-------------+-----------|
              | 10        | MANAGER     | 5000.00   |

              +-----------+-------------+-----------+
              | 20        | CLERK       | 5612.00   |
              +-----------+-------------+-----------|
              | 20        | PRESIDENT   | 5624.00   |
              +-----------+-------------+-----------+
              | 20        | MANAGER     | 3513.00   |

              +-----------+-------------+-----------|
              | 30        | CLERK       | 4612.00   |
              +-----------+-------------+-----------|
              | 30        | PRESIDENT   | 6852.00   |
              +-----------+-------------+-----------+
              | 30        | MANAGER     | 4565.00   |
              +-----------+-------------+-----------+

           案例：找出每个部门的最高薪资，要求显示薪资大于2900的数据
                    第一步：找出每个部门的最高薪资    select dephno,max(sal) from emp group by dephno;
                    第二步：找出薪资大于2900的数据    select dephno,max(sal) from emp group by dephno having max(sal) > 2900;  //这种方式效率低
                 select dephno,max(sal) from emp where sal > 2900 group by dephno;   //效率较高，建议能够使用where过滤的尽量使用where

           案例：找出每个部门的平均薪资 ，并显示薪资大于2000的数据
                    第一步：找出每个部门的平均薪资    select dephno,avg(sal) from emp group by dephno;
                    第二步：薪资大于2000的数据           select dephno,avg(sal) from emp group by dephno having avg(sal) > 2000;
                                            //avg(sal)是分组函数不能写进where语句，所以只能使用having

五、去除重复记录(distinct)
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
public class C3排序 {
}
