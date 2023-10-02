/**
 * @author 游家纨绔
 * @dateTime 2023-10-03 02:44
 * @apiNote TODO
 */
/*
1、MVCC实现原理
              事务A                                         事务B
              begin
                                                           begin
   select * from t_table where id=1;
                                            update t_table set name='曹操' where id=1;
                                                           commit
   select * from t_table where id=1;
              commit

   [1]、读已提交：
         (1)、A开启事务，首先得到一个事务ID为100
         (2)、B开启事务，得到事务ID为101
         (3)、事务A生成一个Read View，read view对应的值如下
              +----------------+---------+
              | m_ids          | 100,101 |
              +----------------+---------+
              | max_limit_id   | 102     |
              +----------------+---------+
              | min_limit_id   | 100     |
              +----------------+---------+
              | creator_trx_id | 100     |
              +----------------+---------+
             然后回到版本链：开始从版本链中挑选可见的记录
               min_limit_id(100) =< trx_id（100）< max_limit_id(102);
               creator_trx_id = trx_id = 100;
             由此可得，trx_id=100的这个记录，当前事务是可见的。所以查到是name为孙权的记录
                +--------+--------+--------------+-----+------+
                | ROW_ID | TRX_ID | ROLL_POINTER | id  | name |
                +--------+--------+--------------+-----+------+
                | NULL   | 100    | NULL         | 1   | 孙权  |
                +--------+--------+--------------+-----+------+
         (4)、事务B进行修改操作，把name改为曹操。把原数据拷贝到undo log,然后对数据进行修改，标记事务ID和上一个数据版本在undo log的地址。
                +--------+--------+--------------+-----+------+
                | ROW_ID | TRX_ID | ROLL_POINTER | id  | name |
                +--------+--------+--------------+-----+------+
                | NULL   | 101    | 0x0123       | 1   | 曹操  |
                +--------+--------+--------------+-----+------+
         (5)、提交事务B
         (6)、事务A再次执行查询操作，新生成一个Read View，Read View对应的值如下
              +----------------+---------+
              | m_ids          | 100     |
              +----------------+---------+
              | max_limit_id   | 102     |
              +----------------+---------+
              | min_limit_id   | 100     |
              +----------------+---------+
              | creator_trx_id | 100     |
              +----------------+---------+
             然后再次回到版本链：从版本链中挑选可见的记录：
               min_limit_id(100) =< trx_id（101）< max_limit_id（102); 但是,trx_id=101，不属于m_ids集合
             因此，trx_id=101这个记录，对于当前事务是可见的。所以SQL查询到的是name为曹操的记录。

        综上所述，在读已提交（RC）隔离级别下，同一个事务里，两个相同的查询，读取同一条记录（id=1），
        却返回了不同的数据（第一次查出来是孙权，第二次查出来是曹操那条记录），因此RC隔离级别，存在 不可重复读并发问题。

   [2]、可重复读：
         (1)、A开启事务，首先得到一个事务ID为100
         (2)、B开启事务，得到事务ID为101
         (3)、事务A生成一个Read View，read view对应的值如下
              +----------------+---------+
              | m_ids          | 100,101 |
              +----------------+---------+
              | max_limit_id   | 102     |
              +----------------+---------+
              | min_limit_id   | 100     |
              +----------------+---------+
              | creator_trx_id | 100     |
              +----------------+---------+
             然后回到版本链：开始从版本链中挑选可见的记录：当前事务ID trx_id=100
               min_limit_id(100) =< trx_id（100）< max_limit_id(102);
               creator_trx_id = trx_id = 100;
             由此可得，trx_id=100的这个记录，当前事务是可见的。所以查到是name为孙权的记录
                +--------+--------+--------------+-----+------+
                | ROW_ID | TRX_ID | ROLL_POINTER | id  | name |
                +--------+--------+--------------+-----+------+
                | NULL   | 101    | NULL         | 1   | 孙权  |
                +--------+--------+--------------+-----+------+
         (4)、事务B进行修改操作，把name改为曹操。把原数据拷贝到undo log,然后对数据进行修改，标记事务ID和上一个数据版本在undo log的地址。
                +--------+--------+--------------+-----+------+
                | ROW_ID | TRX_ID | ROLL_POINTER | id  | name |
                +--------+--------+--------------+-----+------+
                | NULL   | 101    | 0x0123       | 1   | 曹操  |
                +--------+--------+--------------+-----+------+
         (5)、提交事务B
         (6)、事务A再次执行查询操作，生成一个Read View副本（就是第一次的Read View）
              +----------------+---------+
              | m_ids          | 100,101 |
              +----------------+---------+
              | max_limit_id   | 102     |
              +----------------+---------+
              | min_limit_id   | 100     |
              +----------------+---------+
              | creator_trx_id | 100     |
              +----------------+---------+
             然后再次回到版本链：从版本链中挑选可见的记录：
               min_limit_id(100) =< trx_id（101）< max_limit_id（102);
             因为m_ids{100,101} 包含 trx_id（101），并且creator_trx_id (100) 不等于trx_id（101）
             所以，trx_id=101这个记录，对于当前事务是不可见的。这时候呢，版本链roll_pointer跳到下一个版本，trx_id=100这个记录，
             再次校验是否可见：
               min_limit_id(100) =< trx_id（100）< max_limit_id（102);
             因为 m_ids{100,101} 包含 trx_id（100），并且creator_trx_id (100) 等于trx_id（100）所以，trx_id=100这个记录，对于当前事务是可见的。
             即在可重复读（RR）隔离级别下，复用老的Read View副本，解决了不可重复读的问题。
---------------------------------------------------------------------------------------------------------------
2、演示事务
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
        rollback;                // 回滚事务,清空历史操作。
        select * from t_user;    // 这里为什么回滚之后还会显示历史操作呢？，因为MySQL的自动提交功能
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        +----+----------+

        start transaction;        // 启动事务
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
        rollback;     // 因为关闭了自动提交，所以以上的语句都属于历史操作，会被rollback回滚事务清空
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        +----+----------+
        insert into t_user(username) values('zhaoliu');
        insert into t_user(username) values('qianqi');
        commit;        // 这里是提交事务，历史操作都被提交到硬盘文件中。
        select * from t_user;
        +----+----------+
        | id | username |
        +----+----------+
        |  1 | zs       |
        |  4 | zhaoliu  |
        |  5 | qianqi   |
        +----+----------+
        // 这里的id是自动增加的主键，因为'lisi','wangwu'历史操作被回滚，所以没有2和3.属于auto_increment机制
---------------------------------------------------------------------------------------------------------------
3、使用两个事务演示隔离级别
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
public class L12_2事务原理 {
}
