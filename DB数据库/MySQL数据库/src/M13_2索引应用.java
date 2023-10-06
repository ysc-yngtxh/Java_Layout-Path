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
   'MyISAM' 存储引擎下还有一个全文索引
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
3、Mysql 聚集(聚簇)索引 和 非聚集(非聚簇)索引 的区别
   [1]、MySQL中的 '一级索引' 又称为 '聚集索引'，通常是主键索引。
        '一级索引' 将索引和数据存储在一起，存储在同一个 'B+树' 中的叶子节点。当使用 '一级索引' 进行查询时，可以直接定位到数据行。
        例如：在一个表中使用字段 'id' 作为主键创建了 一级索引，当执行SQL语句 SELECT * FROM table WHERE id=1;
             Mysql直接通过 '聚集索引' 就能查询到对应索引值指向叶子节点的数据行。

   [2]、MySQL中 '二级索引' 又称为 '非聚集索引' 或次要索引，是在 '一级索引' 之外创建的索引，用于提高非主键列的查询性能。
        '二级索引' 在InnoDB存储引擎中的叶子节点存储的是：①、索引    ②、主键值   ③、索引字段的列数据，而不是整行数据。
        当使用 '二级索引' 进行查询时，首先通过 '二级索引' 找到对应的主键值，然后再使用 '一级索引' 找到对应的数据记录。
        例如：SQL语句 SELECT * FROM p_ranking WHERE name='Java'; 其中查询条件字段 [name] 建立了二级索引
             Mysql首先会通过 '二级索引' 找到包含 'Java' 数据行对应的主键id，
             然后再通过 '一级索引' 找到对应的数据行。因此，使用 '二级索引' 需要进行"回表"操作，即需要额外的一次查找。

   [3]、'一级索引' 和二级索引之间的关系是：'一级索引' 可以单独存在，'二级索引' 必须依附于 '一级索引'。
        '二级索引' 存储的是主键值而不是实际的教据，这样可以减少数据冗余和维护工作。
            优点：减少了数据冗余，减少了数据行移动或数据页分裂时二级索引的维护工作。
            缺点：根据二级索引查找行的完整数据需要进行额外的回表操作。

   [4]、如果表设置了主键，则主键就是聚簇索引
        如果表没有主键，则会默认第一个NOT NULL，且唯一（UNIQUE）的列作为聚簇索引
        以上都没有，则会默认创建一个隐藏的row_id作为聚簇索引

   总结：一级索引(聚集索引) 存储在 B+树 中叶子节点的数据，相当于表中对应索引值的一行数据
        二级索引(非聚集索引) 存储在 B+树 中叶子节点的数据，相当于表中对应索引值的一个列数据
---------------------------------------------------------------------------------------------------------------
4、在Mysql中，什么是回表，什么是覆盖索引，索引下推？
   [1]、回表：
        回表是发生在 二级索引 上的一种数据查询操作，简单点讲就是我们要查询的列不在 二级索引 的列中，那么必须根据 二级索引 查到主键ID，
        然后再根据主键ID到 聚簇索引树 上去查询整行的数据，这一过程就叫作 ---- 回表。
        比如：SQL语句 SELECT age,name FROM t_user WHERE age=30;
             其中的查询条件字段 [age] 为 普通索引(非聚簇索引)，查询列 [name] 不是索引字段，因此这条SQL语句需要扫描两次 B+树。
             第一次扫描通过 普通索引 定位到 聚簇索引 的值，然后第二次扫描通过 聚簇索引 的值定位到要查找的行记录数据。
             因为需要扫描两次索引 B+树，它的性能较扫描一遍的索引树更低。
        小曹这个时候就会说：如果SQL语句 SELECT age,name FROM t_user WHERE age=30; 查询列 [age]、[name] 都是索引字段

   [2]、覆盖索引：
        当SQL语句中查询的列都在索引中时，我们就不需要回表去把整行数据都捞出来了，可以从 非聚簇索引树 中直接获取到我们需要的列的数据，
        这就叫 ---- 索引覆盖。简单点来讲就是：所有不需要回表的查询操作都叫 索引覆盖。

   [3]、索引下推：
---------------------------------------------------------------------------------------------------------------
5、Mysql 索引如何进行优化
   [1]、避免过度索引：过多的索引会增加数据库的存储和维护成本，同时也会影响数据库的性能。
                    因此，在建立索引时，应该根据实际查询场景进行决定，尽量避免过度索引。

   [2]、建立复合索引：对于经常需要同时查询多个列的语句，建立复合索引可以有效地提高查询效率。
                    需要注意的是，复合索引的建立顺序需要根据实际查询场景进行决定。

   [3]、使用覆盖索引：覆盖索引是指查询语句只需要使用索引就可以获取需要的数据，而不需要回表操作。
                    覆盖索引可以减少MySQL的I/O操作，从而提高查询效率。

   [4]、避免使用函数在索引列上进行计算：在查询语句中，应尽量避免在索引列上使用函数进行计算，
                                   因为这样会使MySQL无法使用索引，从而需要进行全表扫描。

   [5]、定期维护索引：定期维护索引可以清理无用的索引，以保证数据库的正常运行。
                    例如，可以使用OPTIMIZE TABLE语句来对表进行优化，以清理无用的索引和碎片。
---------------------------------------------------------------------------------------------------------------
6、使用 explain 命令查看sql语句的执行计划
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
7、索引在什么情况下失效？
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
