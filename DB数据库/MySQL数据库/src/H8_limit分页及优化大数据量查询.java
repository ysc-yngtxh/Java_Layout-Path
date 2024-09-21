/**
 * @author 游家纨绔
 */
/*
LIMIT(重点之中的重点，以后分页查询全靠它了)
   1、limit是MySQL特有的，其他数据库中没有，不通用。(Oracle中有一个相同的机制，叫做rownum)

   2、limit取结果集中的部分数据，这是它的作用

   3、语法机制：
         LIMIT offset,count;
               offset 参数指定要返回的第一行的偏移量,第一行的偏移量为0，而不是1。就跟数组的下标一样从 0 开始
               count  指定要返回的最大行数。

         LIMIT N,M ----> 相当于 LIMIT M offset N , 从第 N+1 条记录开始, 返回 M 条记录

         案例：取出工资前5名的员工（思路：降序取前5个）
              SELECT ename,sal FROM emp ORDER BY sal DESC LIMIT 0,5;          // 表示从数据下标0开始，取5条数据
              SELECT ename,sal FROM emp ORDER BY sal DESC LIMIT 5;            // LIMIT第一个参数默认为 0，可以省略不写
              SELECT ename,sal FROM emp ORDER BY sal DESC LIMIT 5 offset 0;   // 另一种写法

---------------------------------------------------------------------------------------------------------------
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
---------------------------------------------------------------------------------------------------------------
   5、案例：找出工资排名在第4到第9名的员工
           SELECT ename,sal FROM emp ODER BY sal DESC LIMIT 3,6;
           SELECT ename,sal FROM emp ODER BY sal DESC LIMIT 6 OFFSET 3;

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

             limit (11-1)*10, 10
             limit 10 offset (11-1)*10
          }
---------------------------------------------------------------------------------------------------------------
   7、千万数据，怎么快速查询？
      ①、优化偏移量大(offset)问题
          Ⅰ、采用子查询方式（我们可以先定位偏移位置的 id，然后再查询数据）
             Ⓐ、SELECT * FROM `user_operation_log` LIMIT 1000000, 10;
             Ⓑ、SELECT * FROM `user_operation_log`
                         WHERE id >= (SELECT id FROM `user_operation_log` LIMIT 1000000, 1) LIMIT 10;

             分析：第二条比第一条查询速度快很多。
                  Ⓐ语句相当于 全表查询进行的分页，耗时和内存占用都是比较高的
                  Ⓑ中子查询 则走的是主键索引，递增id第1000000的数据。拿到偏移量数据行的id值，作为嵌套的外查询Where过滤条件，往后取出对应的数据量。
                  所以，Ⓑ语句走的是索引查询的分页；Ⓐ语句走的是全表查询的分页。因此，第二条比第一条查询速度快很多
             缺点：只适用于id递增的情况。
             提供id非递增的写法：
                  注意：某些 mysql 版本不支持在 in 子句中使用 limit，所以采用了多个嵌套select
                  SELECT * FROM `user_operation_log`
                      WHERE id IN(SELECT t.id FROM (SELECT id FROM `user_operation_log` LIMIT 1000000, 10) AS t);

          Ⅱ、采用 id 限定方式
             这种方法要求更高些，id必须是连续递增，而且还得计算id的范围，然后使用 between
             Ⓐ、SELECT * FROM `user_operation_log` WHERE id between 1000000 AND 1000100 LIMIT 100;
             Ⓑ、SELECT * FROM `user_operation_log` WHERE id >= 1000000 LIMIT 100;

      ②、优化数据量大(count)问题
          返回结果的数据量也会直接影响速度
          Ⓐ、SELECT * FROM `user_operation_log` LIMIT 1, 1000000;
          Ⓑ、SELECT id LIMIT 1, 1000000;

          分析：Ⓑ语句比Ⓐ语句查询速度快很多，这是问什么？SELECT * 它不香了吗？
               用 "SELECT * " 数据库需要解析更多的对象、字段、权限、属性等相关内容，
               在 SQL 语句复杂，硬解析较多的情况下，会对数据库造成沉重的负担。
               增大网络开销，* 有时会误带上如log、IconMD5之类的无用且大文本字段，数据传输size会几何增涨。
               特别是MySQL和应用程序不在同一台机器，这种开销非常明显。
               因此，开发当中是禁止 SELECT *
 */
public class H8_limit分页及优化大数据量查询 {}
