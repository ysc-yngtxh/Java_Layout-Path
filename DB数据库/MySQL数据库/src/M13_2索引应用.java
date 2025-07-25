/**
 * @author 游家纨绔
 * @dateTime 2023-10-05 06:40:00
 * @apiNote TODO Mysql索引应用
 */
/* 1、索引的分类
 *    [1]、主键索引：主键上会自动添加索引
 *    [2]、唯一索引：有 unique约束 的字段上会自动添加索引
 *    [3]、普通索引：给单个字段添加索引
 *    [4]、联合索引：给多个字段联合起来添加一个索引
 *    'MyISAM' 存储引擎下还有一个全文索引
 * ---------------------------------------------------------------------------------------------------------------
 * 2、建表时和建表之后添加、删除索引
 *    [1]、创建表的时候建立索引（MySQL中的INDEX和KEY是等价的，它们都是用来创建索引的关键字。因此，INDEX和KEY在语法上是相同的）
 *         ①、普通索引：                       ②、联合索引：                       ③、唯一索引：
 *             CREATE TABLE t_student(           CREATE TABLE t_student(            CREATE TABLE t_student(
 *               id INT NOT NULL PRIMARY KEY,      id INT NOT NULL PRIMARY KEY,       id INT NOT NULL PRIMARY KEY,
 *               no BIGINT(50) NULL,               no BIGINT(50) null,                no BIGINT(50) NULL,
 *               name VARCHAR(20) NULL,            name VARCHAR(20) null,             name VARCHAR(20) NULL,
 *               INDEX index_no(no),               KEY index_no_name(no,name)         UNIQUE INDEX index_no(no)
 *               KEY index_name(name)            )                                  )
 *             )
 *            -- INDEX 和 KEY 是 完全等价 的关键字，KEY 是 MySQL 的历史遗留语法（早期版本更常用），现在推荐使用 INDEX 以提高可读性。
 *
 *    [2]、建完表之后创建索引
 *         添加索引语法：CREATE INDEX 索引名称 ON 表名(字段名(length)); -- 字段名(length)中 length 表示将字段指定长度作为前缀索引
 *         ①、普通索引：CREATE INDEX index_name ON t_student(name);
 *         ②、联合索引：CREATE INDEX index_name_no ON t_student(name,no);
 *         ③、唯一索引：CREATE UNIQUE INDEX index_name ON t_student(name);
 *         ④、全文索引：CREATE FULLTEXT INDEX index_name ON t_student(name);
 *
 *    [3]、以修改表的方式添加索引
 *         ①、普通索引：ALTER TABLE t_student ADD INDEX index_name(name);
 *         ②、联合索引：ALTER TABLE t_student ADD INDEX index_name_no(name,no);
 *         ③、唯一索引：ALTER TABLE t_student ADD UNIQUE INDEX index_name(name);
 *         ④、全文索引：ALTER TABLE t_student ADD FULLTEXT index_name(name);
 *
 *    [4]、删除索引语法：DROP INDEX 索引名称 ON 表名;
 *
 *    [5]、函数索引（Functional Indexes）
 *         MySQL 8.0.13+ 支持函数索引，可以绕过部分最左匹配限制：
 *             -- 创建函数索引
 *             CREATE INDEX idx_name ON users((LEFT(name, 10)));
 *             -- 使用函数索引查询
 *             SELECT * FROM users WHERE LEFT(name, 5) = 'John';
 *
 *    [6]、降序索引优化
 *         MySQL 8.0+ 支持降序索引，优化器能更智能地利用混合排序的联合索引：
 *             -- 创建包含降序的联合索引
 *             CREATE INDEX idx_name_age_desc ON users(name, age DESC);
 *             -- 以下查询可以高效使用索引
 *             SELECT * FROM users WHERE name = 'John' ORDER BY age DESC;
 * ---------------------------------------------------------------------------------------------------------------
 * 3、MySql 聚集(聚簇)索引 和 非聚集(非聚簇)索引 的区别
 *    [1]、MySQL中的 '一级索引' 又称为 '聚集索引'，通常是主键索引。
 *         '一级索引' 将索引和数据存储在一起，存储在同一个 'B+树' 中的叶子节点。当使用 '一级索引' 进行查询时，可以直接定位到数据行。
 *         例如：在一个表中使用字段 'id' 作为主键创建了 一级索引，当执行SQL语句 SELECT * FROM table WHERE id=1;
 *              Mysql直接通过 '聚集索引' 就能查询到对应索引值指向叶子节点的数据行。
 *
 *    [2]、MySQL中 '二级索引' 又称为 '非聚集索引' 或次要索引，是在 '一级索引' 之外创建的索引，用于提高非主键列的查询性能。
 *         '二级索引' 在InnoDB存储引擎中的叶子节点存储的是：①、索引    ②、主键值   ③、索引字段的列数据，而不是整行数据。
 *         例如：SQL语句 SELECT * FROM p_ranking WHERE name='Java'; 其中查询条件字段 [name] 建立了二级索引。
 *              Mysql首先会通过 '[name]的二级索引' 找到包含 'Java' 数据对应索引树叶子节点上的主键id，
 *              然后再通过 '一级索引' 找到对应的数据行。因此，使用 '二级索引' 需要进行"回表"操作，即需要额外的一次查找。
 *
 *    [3]、'一级索引' 和二级索引之间的关系是：'一级索引' 可以单独存在，'二级索引' 需要依附于 '一级索引'。
 *         '二级索引' 存储的是主键值与索引列数据而不是整行的教据，这样可以减少数据冗余和维护工作。
 *             优点：减少了数据冗余，减少了数据行移动或数据页分裂时二级索引的维护工作。
 *             缺点：根据二级索引查找行的完整数据需要进行额外的回表操作。
 *
 *    [4]、如果表设置了主键，则主键就是聚簇索引
 *         如果表没有主键，则会默认第一个NOT NULL，且唯一（UNIQUE）的列作为聚簇索引
 *         以上都没有，则会默认创建一个隐藏的 'row_id' 作为聚簇索引
 *
 *    总结：一级索引(聚集索引) 存储在 'B+树' 中叶子节点的数据，相当于表中对应索引值的一行数据
 *         二级索引(非聚集索引) 存储在 'B+树' 中叶子节点的数据，相当于表中对应索引字段的一个列数据（还包含一个主键值）
 * ---------------------------------------------------------------------------------------------------------------
 * 4、在MySql中，什么是回表，什么是索引覆盖，索引下推？
 *    [1]、回表：是发生在 '二级索引' 上的一种数据查询操作，简单点讲就是我们要查询的列不在 '二级索引' 的列中，
 *              那么必须根据 '二级索引' 查到主键ID，然后再根据主键ID到 '聚簇索引树' 上去查询整行的数据，这一过程就叫作回表。
 *         比如：SQL语句 SELECT age,name FROM t_user WHERE age=30; 字段 [age] 为 '普通索引'，查询列 [name] 不是索引字段。
 *              执行SQL语句，第一次扫描通过 '普通索引' 定位到[age=30]索引的叶子节点，但是我们需要查询出的列数据还有[name]字段，
 *              因此需要从叶子节点中得到 '聚簇索引'(主键ID) 的值，通过第二次扫描 '聚簇索引' 的值定位到行记录数据，从而捞出[name]值。
 *
 *    [2]、索引覆盖：当SQL语句中查询的列都在索引中时，我们就不需要通过 回表 去把整行数据都捞出来了，可以从 '非聚簇索引树' 中直接获取到我们需要的列数据，
 *                 简单点来讲就是：所有不需要 '回表' 的查询操作都叫 '索引覆盖'。
 *         比如：SQL语句 SELECT id,age,name FROM t_user WHERE age=30; 字段 [age]、[name] 为 '联合索引'。
 *              执行SQL语句，第一次扫描通过 '联合索引' 定位到[age=30]索引的叶子节点，该叶子节点上存储有主键值id、索引列数据[age、name]。
 *              且 SELECT 列字段刚好只需要这些数据值，因此也就没有必要再 '回表' 去捞整行其他数据。
 *         应用场景：分页查询 -- 将单列索引(name)升级为联合索引(name, sex)，即可避免回表。
 *
 *    [3]、索引下推：(Index Condition PushDown，简称ICP)，是MySQL5.6版本的新特性，它能减少回表查询次数，提高查询效率。
 *                 MySQL会在取出索引的同时，判断是否可以进行where条件过滤再进行索引查询，也就是说提前执行where的部分过滤操作，
 *                 在某些场景下，可以大大减少回表次数，从而提升整体性能。
 *         比如：检索出表中名字第一个字是'张'，而且年龄是10岁的所有用户。SELECT * FROM t_user WHERE name LIKE '张%' AND age=10;
 *         ①、在MySQL 5.6之前，是没有使用索引下推(ICP)，存储引擎通过 '联合索引' 找到满足 [name like '张%'] 条件叶子节点索引列数据，
 *             假如叶子节点中的满足 [name like '张%'] 条件的数据是： [id=1,name='张三',age=10]、 [id=4,name='张四',age=20]。
 *             并通过主键id(1，4)逐一进行回表扫描（因为检索出来有两条数据，所以需要两次回表操作），然后通过 '聚集索引' 找到完整的行记录，
 *             最后根据 [age=10] 条件进行行记录筛选。但是这样就把 '联合索引' 的另一个字段 [age] 浪费了，毕竟都没发挥出这个索引性能。
 *         ②、在MySQL 5.6之后，索引下推(ICP)是默认开启的。存储引擎通过 '联合索引' 找到满足 [name like '张%'] 条件叶子节点索引列数据，
 *             假如叶子节点中的满足 [name like '张%'] 条件的数据是： [id=1,name='张三',age=10]、 [id=4,name='张四',age=20]。
 *             由于开启了索引下推(ICP)，并且 '联合索引' 中包含 [age] 列，所以存储引擎直接在叶子节点索引列里按照 [age=10] 过滤，
 *             此时叶子节点中的满足 [name like '张%' AND age=10] 条件的数据是： [id=1,name='张三',age=10]，
 *             按照过滤后的数据中主键id=10再进行回表扫描，通过 '聚集索引' 找到完整的行记录，这样我们只有一条数据，因此只需要进行一次回表。
 * ---------------------------------------------------------------------------------------------------------------
 * 5、Mysql 索引如何进行优化
 *    [1]、避免过度索引：过多的索引会增加数据库的存储和维护成本，同时也会影响数据库的性能。
 *                     因此，在建立索引时，应该根据实际查询场景进行决定，尽量避免过度索引。
 *
 *    [2]、建立复合索引：对于经常需要同时查询多个列的语句，建立复合索引可以有效地提高查询效率。
 *                     需要注意的是，复合索引的建立顺序需要根据实际查询场景进行决定。
 *
 *    [3]、使用覆盖索引：设计索引使其包含查询所需的所有列，这样数据库就可以直接从索引中获取所有需要的数据，而不需要访问表本身，这称为覆盖索引。
 *                     覆盖索引可以减少回表，即MySQL的I/O操作，从而提高查询效率。
 *
 *    [4]、如果需要索引很长的字符串，此时需要考虑前缀索引：我们就需要选择字符串的一部分前缀内容作为索引。
 *
 *    [5]、避免使用函数在索引列上进行计算：在查询语句中，应尽量避免在索引列上使用函数进行计算，
 *                                    因为这样会使MySQL无法使用索引，从而需要进行全表扫描。
 *
 *    [6]、定期维护索引：定期维护索引可以清理无用的索引，以保证数据库的正常运行。
 *                     例如，可以使用OPTIMIZE TABLE语句来对表进行优化，以清理无用的索引和碎片。
 * ---------------------------------------------------------------------------------------------------------------
 * 6、使用 explain 命令查看sql语句的执行计划
 *    [1]、explain 显示Mysql如何使用索引来处理 select语句 以及 连接表的，可以帮助我们 选择更好的索引和写出更优化的查询语句
 *         explain select sno,s_name from t_student where s_name='zs1';
 *         +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+
 *         | id | select_type | table     | partitions | type | possible_keys | key  | key_len | ref  | rows | filtered | Extra       |
 *         +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+
 *         |  1 | SIMPLE      | t_student | NULL       | ALL  | NULL          | NULL | NULL    | NULL |    3 |    33.33 | Using where |
 *         +----+-------------+-----------+------------+------+---------------+------+---------+------+------+----------+-------------+
 *
 *    [2]、给薪资sal字段添加索引：CREATE INDEX sno_name_index ON t_student(s_name);
 *         explain select sno,s_name from t_student where s_name='zs1';
 *         +----+-------------+-----------+------------+------+---------------+-----------------+---------+-------+------+----------+-------------+
 *         | id | select_type | table     | partitions | type | possible_keys | key             | key_len | ref   | rows | filtered | Extra       |
 *         +----+-------------+-----------+------------+------+---------------+-----------------+---------+-------+------+----------+-------------+
 *         |  1 | SIMPLE      | t_student | NULL       | ref  | sno_name_index| sno_name_index  | 1023    | const |    1 |   100.00 | Using index |
 *         +----+-------------+-----------+------------+------+---------------+-----------------+---------+-------+------+----------+-------------+
 *
 *    [3]、核心字段
 *           type（访问类型） - 最重要的字段之一
 *               性能从好到差：system > const > eq_ref > ref > range > index > ALL
 *                   system：查询只有一条记录的系统表【表（系统表）中只有一行数据】
 *                   const： 主键或唯一索引等值查询
 *                   eq_ref：在连接查询中，使用主键或唯一索引作为关联条件等值连接
 *                   ref：   普通索引等值查询
 *                   range： 使用索引检索给定范围的行
 *                   index： 全索引扫描（扫描整个索引树）
 *                   ALL：   没有使用任何索引，全表扫描
 *               目标是尽量避免出现"ALL"（全表扫描）
 *           key（实际使用的索引）
 *               显示MySQL实际决定使用的索引，如果为NULL，表示没有使用索引
 *           rows（预估需要检查的行数）
 *               估算MySQL认为它必须检查的行数，数值越小越好
 *           Extra（额外信息）
 * 			     额外信息，显示MySQL在执行查询时的额外信息
 * 			     可能的值：
 * 			     Using index：表示使用了覆盖索引
 * 			     Using filesort：需要额外排序，可能需优化
 * 			     Using temporary：使用了临时表，通常需要优化
 * 			     Using where：使用了WHERE过滤
 *
 *    [4]、其他重要字段
 *           possible_keys（可能使用的索引）
 *               显示查询可能使用的索引
 *           key_len（使用的索引长度）
 *               显示MySQL决定使用的索引的长度，可帮助判断是否使用了索引的全部部分
 *           ref（索引的哪一列被使用）
 *               显示索引的哪一列被使用了
 *           filtered（过滤百分比）
 *               表示存储引擎返回的数据在server层过滤后剩余的比例，理想情况下应接近100%
 * ---------------------------------------------------------------------------------------------------------------
 * 7、索引在什么情况下失效？
 *    [1]、使用or关键字：id和height字段都建了索引，最后加的address字段没有加索引，从而导致其他字段的索引都失效了
 *         SELECT * FROM t_student WHERE id=1 OR height='175' OR address='成都';
 *         注意：如果使用了or关键字，那么它前面和后面的字段都要加索引，不然所有的索引都会失效，这是一个大坑。
 *
 *         分析索引失效原因：
 *         假如不用索引：一次全表扫描完事
 *         使用索引：height走的索引，address不走索引所以成本就是全表扫描，最后还需要merge求并集。
 *                 结果： 全表扫描 + 索引扫描 + merge
 *         很明显直接进行全表扫描操作更少，因此MySQL在这种情况下不走索引直接全表扫描也就可以理解了。
 *
 *    [2]、not in 和 not exists 关键字;
 *         SELECT * FROM t_student WHERE id NOT IN(173, 174, 175, 176);
 *         SELECT * FROM t_student WHERE height NOT IN(173, 174, 175, 176);
 *         主键字段id 使用not in关键字查询数据仍然可以走索引；而普通索引字段height 使用了not in关键字查询数据索引会失效。
 *         SELECT * FROM t_student t1 WHERE NOT EXISTS(select 1 from t_student t2 where t2.height=173 and t1.id=t2.id)
 *         使用not exists关键后，t1表走了全表扫描，并没有走索引
 *
 *    [3]、where中索引列有运算;
 *         SELECT * FROM t_student WHERE id+1=5;
 *
 *    [4]、where中索引列使用了函数;
 *         SELECT * FROM t_student WHERE SUBSTR(height,1,2)=17; // 截取了height字段的前面两位字符为 17 的数据
 *
 *    [5]、在Sql语句中因为字段类型不同，而导致索引失效的问题;
 *         SELECT * FROM t_student WHERE address=101;
 *         因为address字段的类型是varchar，而传参的类型是int，两种类型不同，从而导致索引失效。
 *
 *    [6]、联合索引中未使用最左列字段，不满足最左匹配原则;
 *         CREATE INDEX idx_code_age_name ON t_student(code,age,name);
 *         SELECT * FROM t_student WHERE code='101';
 *         SELECT * FROM t_student WHERE code='101' AND age=21;
 *         SELECT * FROM t_student WHERE code='101' AND name='周星驰';
 *         SELECT * FROM t_student WHERE code='101' AND age=21 AND name='周星驰';
 *         这4条sql中都有code字段，它是索引字段中的第一个字段，也就是最左边的字段。
 *         where 条件中只要有这个code字段在，该sql就能走索引。这就是我们所说的最左匹配原则。
 *
 *         SELECT * FROM t_student WHERE age=21;
 *         SELECT * FROM t_student WHERE name='周星驰';
 *         SELECT * FROM t_student WHERE age=21 AND name='周星驰';
 *         以上3种情况不满足最左匹配原则，说白了就是因为查询条件中，没有包含给定字段最左边的索引字段，即字段code。
 *
 *         补充：在 MySql8 之后，不满足最左匹配原则的查询也是有可能走索引的。
 *              有一个‘索引跳跃扫描’，是针对我们联合索引最左边列唯一值较少情况下的一种优化策略。
 *              比如：explain select * from user where f2 = 1 [其中联合索引(f1,f2)] 实际情况是走了索引的·
 *              原理：在执行的时候，MySql会自动的将 f1 的所有唯一值进行拼接，将 f2=xxx 条件隐式的加到sql中，从而达到了走索引的效果。
 *                   其实就是隐式的构造了查询条件，使得看起来好像不需要最左匹配原则而已，因此最终还是没有逃出最左匹配原则
 *
 *    [7]、模糊查询的时候，第一个通配符使用的是%，这个时候索引是失效的。
 *         SELECT ename FROM emp WHERE ename LIKE '%A%';
 */
public class M13_2索引应用 {}
