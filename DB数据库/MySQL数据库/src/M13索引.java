/**
 * @author 游家纨绔
 */
/*
索引
1、什么是索引？有什么用?
   [1]、索引就相当于一本书的目录，通过目录可以快速的找到对应的资源。在数据库方面，查询一张表的时候有两种检索方式：
        第一种方式：全表扫描
        第二种方式：根据索引检索

   [2]、索引为什么可以提高检索效率？
        其实最根本的原理是缩小了扫描的范围。
        索引虽然可以提高检索效率，但是不能随意的添加索引，因为索引也是数据库当中的对象，也需要不断的维护，是有维护成本的。
        比如：表中的数据经常被修改，这样就不适合添加索引，因为数据一旦被修改，索引需要重新排序，进行维护。

   [3]、添加索引是给某一个字段，或者说给 某些字段组合 添加索引。
        SELECT ename,sal FROM emp WHERE ename = 'SMITH';
        当ename字段上没有添加索引的时候，以上SQL语句会进行全表扫描，扫描ename字段中所有的值
        当ename字段上添加索引的时候，以上SQL语句会根据索引扫描，快速定位。

   [4]、什么情况下考虑给字段添加索引？（满足什么条件）
        数据量庞大
        该字段涉及很少的DML操作
        该字段经常出现在 WHERE 条件语句中

   [5]、注意：主键和具有unique约束的字段自动会添加索引。根据主键查询，效率较高。尽量根据主键检索。
---------------------------------------------------------------------------------------------------------------
2、索引的分类
   [1]、主键索引：主键上会自动添加索引
   [2]、唯一索引：有unique约束的字段上会自动添加索引
   [3]、普通索引：给单个字段添加索引
   [4]、联合索引：给多个字段联合起来添加一个索引

   MyISAM存储引擎下还有一个全文索引
---------------------------------------------------------------------------------------------------------------
3、建表时和建表之后添加、删除索引
   [1]、添加索引语法：create index 索引名称 on 表名(字段名(length));  // 字段名(length)中的 length 表示将字段的指定长度作为前缀索引
   [2]、创建表的时候建立索引
        ①、普通索引：                       ②、联合索引：                       ③、唯一索引：
            CREATE TABLE t_student(           CREATE TABLE t_student(            CREATE TABLE t_student(
              id INT NOT NULL PRIMARY KEY,      id INT NOT NULL PRIMARY KEY,       id INT NOT NULL PRIMARY KEY,
              no BIGINT(50) NULL,               no BIGINT(50) null,                no BIGINT(50) NULL,
              name VARCHAR(20) NULL,            name VARCHAR(20) null,             name VARCHAR(20) NULL,
              INDEX index_no(no)                KEY index_no_name(no,name)         UNIQUE INDEX index_no(no)
            )                                 )                                  )
   [3]、建完表之后创建索引
        ①、普通索引：CREATE INDEX index_name ON t_student(name);
        ②、联合索引：CREATE INDEX index_name_no ON t_student(name,no);
        ③、唯一索引：CREATE UNIQUE INDEX index_name ON t_student(name);
        ④、全文索引：CREATE FULLTEXT INDEX index_name ON t_student(name);
   [4]、以修改表的方式添加索引
        ①、普通索引：ALTER TABLE t_student ADD INDEX index_name(name);
        ②、联合索引：ALTER TABLE t_student ADD INDEX index_name_no(name,no);
        ③、唯一索引：ALTER TABLE t_student ADD UNIQUE INDEX index_name(name);
        ④、全文索引：ALTER TABLE t_student ADD FULLTEXT index_name(name);
   [5]、删除索引语法：drop index 索引名称 on 表名;
---------------------------------------------------------------------------------------------------------------
5、使用 explain 命令查看sql语句的执行计划
   [1]、explain显示Mysql如何使用索引来处理 select语句 以及 连接表的，可以帮助我们 选择更好的索引和写出更优化的查询语句
        explain select sno,s_name from t_student where s_name='zs1';
        +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+
        | id | select_type | table     | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra       |
        +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+
        |  1 | SIMPLE      | t_student | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where |
        +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+

   [2]、给薪资sal字段添加索引：create index sno_name_index on t_student(s_name);
        explain select sno,s_name from t_student where s_name='zs1';
        +----+-------------+-----------+------------+------+-----------------+-----------------+---------+-------+------+----------+-------------+
        | id | select_type | table     | partitions | type | possible_keys   | key             | key_len | ref   | rows | filtered | Extra       |
        +----+-------------+-----------+------------+------+-----------------+-----------------+---------+-------+------+----------+-------------+
        |  1 | SIMPLE      | t_student | NULL       | ref  | sno_name_index  | sno_name_index  | 1023    | const |    1 |   100.00 | Using index |
        +----+-------------+-----------+------------+------+-----------------+-----------------+---------+-------+------+----------+-------------+
---------------------------------------------------------------------------------------------------------------
6、索引的实现原理
   Mysql的索引原理和数据结构是基于B树（B-tree）的数据结构。B树是一种自平衡的搜索树，它能够高效地支持数据的插入、删除和查找操作。
   在Mysql中，索引底层使用的数据结构就是B树。具体而言，Mysql中的索引使用的是B+树数据结构，它是B树的一种变体。
   B+树相比于B树有以下特点：
   B+树的非叶子节点不存储实际的数据，只存储索引信息，这使得B+树的高度更低，减少了磁盘I/O的次数。
   B+树的叶子节点使用链表连接，可以支持范围查询和排序操作。
   B+树的叶子节点按照键值大小顺序存储，提升了范围查询的效率。
   Mysql的索引是存储在磁盘上的，具体存储在数据库目录下的数据库文件中。索引文件使用MYI扩展名，其中存储了B+树数据结构的索引信息和表中数据的关联关系。通过使用索引，Mysql可以快速定位到符合特定条件的数据，提高了查询效率。

   通过索引底层采用的数据结构：B+Tree 缩小扫描范围，底层索引进行了排序、分区，索引会携带数据在表中的“物理地址”，
   最终通过索引检索到数据之后，获取到关联的物理地址，通过物理地址定位表中的数据，效率是最高的
   SELECT ename FROM emp WHERE ename = 'SMITH';
   通过索引转换为：
   SELECT ename FROM emp WHERE 物理地址 = 0x0123;
---------------------------------------------------------------------------------------------------------------
7、索引在什么情况下失效？
   [1]、使用or关键字; id和height字段都建了索引，最后加的address字段没有加索引，从而导致其他字段的索引都失效了
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
public class M13索引 {
}
