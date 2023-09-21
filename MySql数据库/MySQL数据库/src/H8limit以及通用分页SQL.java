/**
 * @author 游家纨绔
 */
/*
LIMIT(重点之中的重点，以后分页查询全靠它了)
   1、limit是MySQL特有的，其他数据库中没有，不通用.(Oracle中有一个相同的机制，叫做rownum)

   2、limit取结果集中的部分数据，这是它的作用

   3、语法机制：
       LIMIT offset,count;
             offset 参数指定要返回的第一行的偏移量,第一行的偏移量为0，而不是1。就跟数组的下标一样从 0 开始
             count  指定要返回的最大行数。

       LIMIT N,M ----> 相当于 LIMIT M offset N , 从第 N 条记录开始, 返回 M 条记录

       案例：取出工资前5名的员工（思路：降序取前5个）
        SELECT ename,sal FROM emp ORDER BY sal DESC LIMIT 0,5;
        SELECT ename,sal FROM emp ORDER BY sal DESC LIMIT 5;            // LIMIT第一个参数默认为 0，可以省略不写
        SELECT ename,sal FROM emp ORDER BY sal DESC LIMIT 0 offset 5;   // 另一种写法

   4、LIMIT是SQL语句最后执行的一个环节：
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
           SELECT ename,sal FROM emp ODER BY sal DESC LIMIT 3,6;
           SELECT ename,sal FROM emp ODER BY sal DESC LIMIT 3 offset 6;

   6、通用的标准分页sql
          每页显示3条记录：
             第一页：0，3
             第二页：3，3
             第三页：6，3
             第四页：9，3
             第五页：12，3

          每页显示pageSize条记录：
             第pageNo页：(pageNo-1)*pageSize, pageSize

          java代码 {
             int pageNo = 11;    // 页码是11
             int pageSize = 10;  // 每页显示10条

             limit 100,10
          }
 */
public class H8limit以及通用分页SQL {
}
