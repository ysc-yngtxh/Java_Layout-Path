/**
 * @author 游家纨绔
 */
/*
limit(重点之中的重点，以后分页查询全靠它了)
   1、limit是MySQL特有的，其他数据库中没有，不通用.(Oracle中有一个相同的机制，叫做rownum)

   2、limit取结果集中的部分数据，这是它的作用

   3、语法机制：
       limit startIndex,length
           startIndex  表示起始位置，从0开始，0表示第一条数据
           length      表示取几个数据

             案例：取出工资前5名的员工（思路：降序取前5个）
            select ename,sal from emp order by sal desc limit 0,5;
            select ename,sal from emp order by sal desc limit 5;    // 0也可不写，默认为0

   4、limit是SQL语句最后执行的一个环节：
        SELECT
           ...    5
        FROM
           ...    1
        WHERE
           ...    2
        GROUP BY
           ...    3
        HAVING
           ...    4
        ODER BY
           ...    6
        LIMIT
           ...;   7

   5、案例：找出工资排名在第4到第9名的员工
           select ename sal from emp oder by sal desc limit 3,6;

   6、通用的标准分页sql

              每页显示3条记录：
              第一页：0，3
              第二页：3，3
              第三页：6，3
              第四页：9，3
              第五页：12，3

              每页显示pageSize条记录：
              第pageNo页：(pageNo-1)*pageSize, pageSize

              java代码{
                 int pageNo = 11;    // 页码是11
                 int pageSize = 10;  // 每页显示10条

                 limit 10,10
              }
 */
public class H8limit以及通用分页SQL {
}
