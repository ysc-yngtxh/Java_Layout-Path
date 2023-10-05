/**
 * @author 游家纨绔
 * @dateTime 2023-10-05 06:38
 * @apiNote TODO Mysql索引应用
 */
/*
1、索引的分类
   [1]、主键索引：主键上会自动添加索引
   [2]、唯一索引：有 unique约束 的字段上会自动添加索引
   [3]、普通索引：给单个字段添加索引
   [4]、联合索引：给多个字段联合起来添加一个索引

   MyISAM存储引擎下还有一个全文索引
---------------------------------------------------------------------------------------------------------------
2、建表时和建表之后添加、删除索引
   [1]、创建表的时候建立索引
        ①、普通索引：                       ②、联合索引：                       ③、唯一索引：
            CREATE TABLE t_student(           CREATE TABLE t_student(            CREATE TABLE t_student(
              id INT NOT NULL PRIMARY KEY,      id INT NOT NULL PRIMARY KEY,       id INT NOT NULL PRIMARY KEY,
              no BIGINT(50) NULL,               no BIGINT(50) null,                no BIGINT(50) NULL,
              name VARCHAR(20) NULL,            name VARCHAR(20) null,             name VARCHAR(20) NULL,
              INDEX index_no(no)                KEY index_no_name(no,name)         UNIQUE INDEX index_no(no)
            )                                 )                                  )
   [2]、建完表之后创建索引
        添加索引语法：CREATE INDEX 索引名称 ON 表名(字段名(length)); -- 字段名(length)中 length 表示将字段指定长度作为前缀索引
        ①、普通索引：CREATE INDEX index_name ON t_student(name);
        ②、联合索引：CREATE INDEX index_name_no ON t_student(name,no);
        ③、唯一索引：CREATE UNIQUE INDEX index_name ON t_student(name);
        ④、全文索引：CREATE FULLTEXT INDEX index_name ON t_student(name);
   [3]、以修改表的方式添加索引
        ①、普通索引：ALTER TABLE t_student ADD INDEX index_name(name);
        ②、联合索引：ALTER TABLE t_student ADD INDEX index_name_no(name,no);
        ③、唯一索引：ALTER TABLE t_student ADD UNIQUE INDEX index_name(name);
        ④、全文索引：ALTER TABLE t_student ADD FULLTEXT index_name(name);
   [4]、删除索引语法：drop index 索引名称 on 表名;
---------------------------------------------------------------------------------------------------------------
3、使用 explain 命令查看sql语句的执行计划
   [1]、explain 显示Mysql如何使用索引来处理 select语句 以及 连接表的，可以帮助我们 选择更好的索引和写出更优化的查询语句
        explain select sno,s_name from t_student where s_name='zs1';
        +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+
        | id | select_type | table     | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra       |
        +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+
        |  1 | SIMPLE      | t_student | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where |
        +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+

   [2]、给薪资sal字段添加索引：CREATE INDEX sno_name_index ON t_student(s_name);
        explain select sno,s_name from t_student where s_name='zs1';
        +----+-------------+-----------+------------+------+---------------+-----------------+---------+-------+------+----------+-------------+
        | id | select_type | table     | partitions | type | possible_keys | key             | key_len | ref   | rows | filtered | Extra       |
        +----+-------------+-----------+------------+------+---------------+-----------------+---------+-------+------+----------+-------------+
        |  1 | SIMPLE      | t_student | NULL       | ref  | sno_name_index| sno_name_index  | 1023    | const |    1 |   100.00 | Using index |
        +----+-------------+-----------+------------+------+---------------+-----------------+---------+-------+------+----------+-------------+
---------------------------------------------------------------------------------------------------------------
4、索引在什么情况下失效？
   [1]、使用or关键字：id和height字段都建了索引，最后加的address字段没有加索引，从而导致其他字段的索引都失效了
        SELECT * FROM t_student WHERE id=1 OR height='175' OR address='成都';
        注意：如果使用了or关键字，那么它前面和后面的字段都要加索引，不然所有的索引都会失效，这是一个大坑。

   [2]、not in和not exists关键字;
        SELECT * FROM t_student WHERE id NOT IN(173, 174, 175, 176);
        SELECT * FROM t_student WHERE height NOT IN(173, 174, 175, 176);
        主键字段id 使用not in关键字查询数据仍然可以走索引；而普通索引字段height 使用了not in关键字查询数据索引会失效。
        SELECT * FROM t_student t1 WHERE NOT EXISTS(select 1 from t_student t2 where t2.height=173 and t1.id=t2.id)
        使用not exists关键后，t1表走了全表扫描，并没有走索引

   [3]、where中索引列有运算;
        SELECT * FROM t_student WHERE id+1=5;

   [4]、where中索引列使用了函数;
        SELECT * FROM t_student WHERE SUBSTR(height,1,2)=17; // 截取了height字段的前面两位字符为 17的数据

   [5]、在sql语句中因为字段类型不同，而导致索引失效的问题;
        SELECT * FROM t_student WHERE address=101;
        因为address字段的类型是varchar，而传参的类型是int，两种类型不同，从而导致索引失效。

   [6]、联合索引中未使用最左列字段，不满足最左匹配原则;
        CREATE INDEX idx_code_age_name ON t_student(code,age,name);
        SELECT * FROM t_student WHERE code='101';
        SELECT * FROM t_student WHERE code='101' AND age=21;
        SELECT * FROM t_student WHERE code='101' AND name='周星驰';
        SELECT * FROM t_student WHERE code='101' AND age=21 AND name='周星驰';
        这4条sql中都有code字段，它是索引字段中的第一个字段，也就是最左边的字段。
        where 条件中只要有这个code字段在，该sql就能走索引。这就是我们所说的最左匹配原则。

        SELECT * FROM t_student WHERE age=21;
        SELECT * FROM t_student WHERE name='周星驰';
        SELECT * FROM t_student WHERE age=21 AND name='周星驰';
        以上3种情况不满足最左匹配原则，说白了就是因为查询条件中，没有包含给定字段最左边的索引字段，即字段code。

   [7]、模糊查询的时候，第一个通配符使用的是%，这个时候索引是失效的。
        SELECT ename FROM emp WHERE ename LIKE '%A%';
 */
public class M13_2索引应用 {
}
