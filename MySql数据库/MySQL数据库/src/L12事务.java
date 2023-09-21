/*
事务
1、什么是事务？

       一个事务是一个完整的业务逻辑单元，不可再分。

       比如：银行账户转账，从账户向账户转账10000，需要执行两条update语句
          update t_act set balance=balance-10000 where actno='act-001';
          update t_act set balance=balance+10000 where actno='act-002';
          以上两条DML语句必须同时成功，或者同时失败，不允许出现一条成功，一条失败。
          要想保证以上的两条DML语句同时成功或者同时失败，那么就需要使用数据库的“事务机制”。

2、事务的特性
          事务包括四大特性：ACID
         A:原子性：事务是最小的工作单元，不可再分
         C:一致性：事务必须保证多条DML语句同时成功或者同时失败
         I:隔离性：事务A与事务B之间具有隔离
         D:持久性：持久性说的是最终数据必须持久化到硬盘中，事务才算成功的都结束。

3、关于事务之间的隔离性

   事务隔离性存在级别：理论上隔离级别包括4个：

       第一级别：读未提交(read uncommitted)
                 A事务读取B事务尚未提交的数据，此时如果B事务发生错误并执行回滚操作，那么A事务读取到的数据就是脏数据。
                 读未提交存在脏读(Dirty Read)现象：表示读到了脏的数据。

       第二级别：读已提交(read committed)
                 事务A在执行读取操作，由整个事务A比较大，前后读取同一条数据需要经历很长的时间 。
                 而在事务A第一次读取数据，比如此时读取了小明的年龄为20岁，事务B执行更改操作，将小明的年龄更改为30岁，
                 此时事务A第二次读取到小明的年龄时，发现其年龄是30岁，和之前的数据不一样了，就会造成事务A的异常。
                 所以读这种提交的数据是不可以重复的读取数据，这种隔离级别解决了：脏读现象没有了。
                 大多数数据库系用默认得隔离级别是这个，比如 Oracle数据库（mysql不是）
                 读已提交存在的问题是：不可重复读

       第三级别：可重复读(repeatable read)
                 事务A在执行读取操作，需要两次统计数据的总条数，前一次查询数据总量后，此时事务B执行了新增数据的操作并提交后，
                 这个时候事务A第二次读取的数据总条数和之前统计的不一样，就像产生了幻觉一样，平白无故的多了几条数据。
                 尽管B事务在A事务还未结束的时候，增加了表中的数据，但是为了维护可重复读，A事务中不管怎么查询，
                 是查询不了新增的数据的。但是对于真实的表而言，表中的数据是的的确确增加了，这种事务隔离级别也被称为成为幻读。
                 MySQL的 InnoDB 和 XtraDB 存储引擎通过 MVCC（多版本并发控制）解决了幻读的问题。
                 MySQL数据库默认的隔离级别是：可重复读
                 这种隔离级别解决了：不可重复读问题     存在的问题是：读取到的数据是幻象

       第四级别：序列化读/串行化读
                 通过事务得强制串行化执行，避免了幻读得问题，这个隔离级别会对读取得每一行数据加锁，可能导致大量的超时和锁征用得问题。
                 实际上很少用这个隔离级别，只有在非常需要确保数据一致性的情况且没有并发的情况下才考虑使用这种方式
                 效率低，需要事务排队

           Oracle数据库默认的隔离级别是：读已提交
           MySQL数据库默认的隔离级别是：可重复读

4、演示事务
    MySQL事务默认情况下是自动提交的（什么是自动提交？只要执行任意一条DML语句提交一次）怎么关闭自动提交？start transaction;

       像一些select into、update、delete等MySQL语句都是历史操作。
       并且每条语句执行完之后都会自动提交，提交到硬盘文件中。

       提交事务语句：commit; 提交历史操作，并同步到硬盘文件中。
       回滚事务语句：rollback; 清空历史操作。

        演示：
        drop table if exists t_user;
        create table t_user(
           id int primary key auto_increment,
           username varchar(255)
        );
        insert into t_user(username) values('zs1');
        select * from t_user;
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        +----+----------+
        rollback;       //回滚事务,清空历史操作。
        select * from t_user;    //这里为什么回滚之后还会显示历史操作呢？，因为MySQL的自动提交功能
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        +----+----------+

        start transaction;        //启动事务
        insert into t_user(username) values('lisi');
        insert into t_user(username) values('wangwu');
        select * from t_user;
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        |  2 | lisi     |
        |  3 | wangwu   |
        +----+----------+
        rollback;     //因为关闭了自动提交，所以以上的语句都属于历史操作，会被rollback回滚事务清空
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        +----+----------+
        insert into t_user(username) values('zhaoliu');
        insert into t_user(username) values('qianqi');
        commit;        //这里是提交事务，历史操作都被提交到硬盘文件中。
        select * from t_user;
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        |  4 | zhaoliu  |
        |  5 | qianqi   |
        +----+----------+
        //这里的id是自动增加的主键，因为'lisi','wangwu'历史操作被回滚，所以没有2和3.属于auto_increment机制


5、使用两个事务演示隔离级别
        第一：读未提交(read uncommitted)
                      设置事务的全局隔离级别：
                  set global transaction isolation level read uncommitted;
                      查看事务的全局隔离级别：
                  select @@global.transaction_isolation;

                      其实就是在历史操作期间，还未被提交到硬盘文件就能被读到的意思

         第二：读已提交(read committed)

                      其实就是在历史操作期间，只有被提交到硬盘文件才能被读到的意思

         第三：可重复读(repeatable read)
         第四：可串行化
 */

/**
 * @author 游家纨绔
 */
public class L12事务 {
}
