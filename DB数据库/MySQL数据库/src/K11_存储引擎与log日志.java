/**
 * @author 游家纨绔
 */
/* 存储引擎
 *   1、什么是存储引擎？
 *         存储引擎这个名字只有在MySQL中存在。(Oracle中有对应的机制，但是不叫作存储引擎。Oracle中没有特殊的名字，就是“表的存储方式”)
 *      MySQL支持很多存储引擎，每一个存储引擎都对应一种不同的存储方式。每一个存储引擎都有自己的优缺点，需要在合适的时机选择合适的存储引擎
 *      (MySQL默认使用的存储引擎是InnoDB方式。默认采用的字符集是UTF8)
 *
 *      存储引擎说白了就是
 *         ①、如何存储数据
 *         ②、如何为存储的数据建立索引
 *         ③、如何更新、查询数据 等技术的实现方法。
 *         例如: 如果你在研究大量的临时数据，你也许需要使用内存存储引擎，因为内存存储引擎能够在内存中存储所有的表格数据。
 *              又或者，你也许需要一个支持事务处理的数据库，以确保事务处理不成功时数据的回退能力，你就要选择支持事务的存储引擎。
 * ---------------------------------------------------------------------------------------------------------------
 *   2、查看当前MySQL支持的存储引擎
 *        show engines \G;
 *
 *        目前 MySql 8.x.x 版本支持的存储引擎有9个
 * ---------------------------------------------------------------------------------------------------------------
 *   3、常见的存储引擎
 *              第一个: InnoDB
 *             Engine: InnoDB
 *            Support: DEFAULT
 *            Comment: Supports transactions, row-level locking, and foreign keys
 *       Transactions: YES
 *                 XA: YES
 *         Savepoints: YES
 *
 *         InnoDB还引入了行级锁定和外键约束，在以下场合下，使用InnoDB是最理想的选择：
 *             ①、更新密集的表： InnoDB存储引擎特别适合处理多重并发的更新请求。
 *             ②、事务：        InnoDB存储引擎是支持事务的标准MySQL存储引擎。
 *             ③、自动灾难恢复： 与其它存储引擎不同，InnoDB表能够自动从灾难中恢复。
 *             ④、外键约束：     MySQL支持外键的存储引擎只有InnoDB。
 *             ⑤、支持自动增加列auto_increment属性。
 *
 *         表的结构存储在xxx.frm文件中
 *         数据存储在tablespace这样的表空间中(逻辑概念)，无法被压缩，无法转换成只读
 *         这种InnoDB存储引擎在MySQL数据库崩溃之后提供自动恢复机制。
 *         InnoDB支持级联删除和级联更新：(外键约束中删除（更新）父表中的一组数据，相对应的子表中约束的数据也会被删除（更新）)
 *
 *         优点：支持外键、行级锁、事务等。这种引擎数据的安全存储得到保障(MYSQL默认的存储引擎)
 *   ---------------------------------------------------------------------------------------------------
 *               第二个: MyISAM
 *              Engine: MyISAM
 *             Support: YES
 *             Comment: MyISAM storage engine
 *        Transactions: NO
 *                  XA: NO
 *          Savepoints: NO
 *
 *         每当我们建立一个MyISAM引擎的表时，就会在本地磁盘上建立三个文件，文件名就是表名。
 *         例如: 我建立了一个MyISAM引擎的tb_demo表，那么就会生成以下三个文件：
 *              tb_demo.frm (存储表定义的文件)
 *              tb_demo.MYD (存储表中数据的文件)
 *              tb_demo.MYI (存储表中索引的文件)
 *
 *         MyISAM表无法处理事务，这就意味着有事务处理需求的表，不能使用MyISAM存储引擎。MyISAM存储引擎特别适合在以下几种情况下使用：
 *            ①、选择密集型的表，MyISAM存储引擎在筛选大量数据时非常迅速，这是它最突出的优点。
 *            ②、插入密集型的表，MyISAM的并发插入特性允许同时选择和插入数据。
 *
 *         由此看来，MyISAM存储引擎很适合管理服务器日志数据。
 *
 *         由于 MyISAM 只支持表级锁定，所以在高并发的情况下可能会出现性能问题。
 *         相对而言，InnoDB 存储引擎支持行级锁定，因此更适合于高并发的场景，能够提供更好的性能和并发控制。
 *         因此，一般建议在需要支持事务和并发控制的场景下使用 InnoDB 存储引擎，而不是 MyISAM。
 *
 *         MyISAM这种存储引擎不支持事务。
 *            优点：可被压缩，节省存储空间，并且可以转换为只读表，提高检索效率。只支持表锁不支持行级锁
 *            缺点：不支持事务
 *   ---------------------------------------------------------------------------------------------------
 *              第三个: MEMORY
 *             Engine: MEMORY
 *            Support: YES
 *            Comment: Hash based, stored in memory, useful for temporary tables
 *       Transactions: NO
 *                 XA: NO
 *         Savepoints: NO
 *          优点：因为所有数据和索引都是存储在内存当中的，查询速度快
 *          缺点：不支持事务。因为所有数据和索引都是存储在内存当中的，所以数据容易丢失（比如说断电数据全没了）。
 *   ---------------------------------------------------------------------------------------------------
 *   4、日志
 *      MySQL 从整体来看，其实就有两块：一块是Server层，它主要做的是MySQL功能层面的事情；
 *                                  还有一块是引擎层，负责存储相关的具体事宜。
 *      MySQL 中还存在的三种日志：
 *      Undo log（回滚日志）：是 Innodb 存储引擎层生成的日志，实现了事务中的原子性，主要用于事务回滚和 MVCC。
 *      Redo log（重做日志）：是 Innodb 存储引擎层生成的日志，实现了事务中的持久性，主要用于掉电等故障恢复；
 *      Bin log （归档日志）：是 Server 层生成的日志，主要用于数据备份和主从复制；
 *      其中 Redo Log 是 InnoDB 引擎特有的日志，而 Bin Log 是 Server 层的日志。
 *      而 Undo Log 也并不是存在于所有存储引擎中的日志，只有支持事务的引擎才需要类似的机制。【例如，MyISAM 就不需要 Undo Log】
 *
 *      ①、binlog（二进制文件）
 *         binlog 用于记录了对MySQL数据库执行更改的所有操作（不包含SELECT、SHOW等，因为对数据没有修改）信息。
 *
 *         binlog 是MySQL的逻辑日志，以二进制的形式保存在磁盘中。使用任何存储引擎的MySQL数据库都会记录binlog日志。
 *         逻辑日志：可以简单理解为记录的就是Sql语句。
 *         物理日志：因为MySQL数据最终是保存在数据页中的，物理日志记录的就是数据页变更。
 *
 *         Binlog 有三种格式记录信息，其中 STATEMENT 格式最接近"记录相反操作"的概念：
 *         (1) STATEMENT 格式（MySQL 5.7.7 之前的默认格式）
 *             记录原始SQL语句，回放时会通过执行原始SQL实现恢复
 *             INSERT INTO accounts VALUES (1, 'Alice', 1000);
 *             UPDATE accounts SET balance=1500 WHERE id=1;
 *             DELETE FROM accounts WHERE id=1;
 *         (2) ROW 格式（MySQL 5.7.7 及以后版本的默认格式）
 *             INSERT: id=1,name='Alice',balance=1000
 *             UPDATE: before(id=1,name='Alice',balance=1000) after(id=1,name='Alice',balance=1500)
 *             DELETE: id=1,name='Alice',balance=1500
 *         (3) MIXED 格式
 *             混合使用STATEMENT和ROW格式
 *
 *         在一些特定的场景下可能需要通过 binlog 进行回滚操作：
 *            <1> 错误地运行了全表更新语句
 *            <2> 执行了错误的 DELETE/UPDATE 语句影响了大量数据
 *            <3> 应用程序bug导致数据被错误修改
 *         比如在以上场景下，可以通过 binlog 的回滚来撤销错误操作：
 *            # 使用 mysqlbinlog 工具生成反向SQL
 *            mysqlbinlog --start-position=位置 --stop-position=位置 binlog.000123 | mysql -u root -p
 *            # 或先导出为SQL文件审查后再执行
 *            mysqlbinlog --start-datetime="2025-06-20 14:00:00" --stop-datetime="2025-06-21 20:42:00" --base64-output=decode-rows -v binlog.000022 > analyze.txt
 *
 *         Ⅰ、binlog使用场景
 *            主从复制：在Master端开启binlog，然后将binlog发送到各个Slave端，Slave端回放binlog从而达到主从数据一致。
 *            数据恢复：通过使用mysqlbinlog工具来恢复数据。
 *
 *         Ⅱ、binlog刷盘时机
 *            对于InnoDB存储引擎而言，只有在事务提交时才会记录binlog，此时记录还在内存中，
 *            那么binlog是什么时候刷到磁盘中的呢？mysql通过sync_binlog参数控制binlog的刷盘时机，取值范围是0-N：
 *               0：不去强制要求，由系统自行判断何时写入磁盘；
 *               1：每次commit的时候都要将binlog写入磁盘；
 *               N：每N个事务，才会将binlog写入磁盘。
 *            从上面可以看出，sync_binlog最安全的是设置是1，这也是MySQL 5.7.7之后版本的默认值。
 *            但是设置一个大一些的值可以提升数据库性能，因此实际情况下也可以将值适当调大，牺牲一定的一致性来获取更好的性能。
 *
 *     ②、Undo log
 *        Undo Log是InnoDB存储引擎内部用于实现事务原子性和一致性的重要机制，它保存的是数据被修改前的状态，采用高效的数据快照方式来实现回滚操作。
 *        例如：假设有一个数据行，初始值为A。事务T修改该行值为B，那么就会在undo log中记录数据行值A。
 *             主要就是为了记录“修改前的值”，用于回滚和一致性读（如MVCC）。
 *
 *        应用场景：
 *             回滚事务：当事务需要被回滚时，通过Undo Log可以恢复到事务开始前的数据状态。
 *             MVCC（多版本并发控制）：InnoDB利用Undo Log来提供不同事务之间的一致性读视图，
 *                                  使得事务可以看到其他事务未提交之前的旧版本数据，从而避免锁竞争，提高并发性能。
 *
 *        redo log保证事务的持久性，undo log用来帮助事务回滚及MVCC的功能。
 *
 *     ③、redo log
 *        redo log记录的是对数据库物理结构的修改，确保在系统崩溃或故障后，已提交的事务修改能够被恢复，用于保障事务的持久性。
 *        主要记录“修改后的值”，用于故障恢复。并且是以循环方式覆盖旧日志。
 *        例如：假设原数据为 id=1, name='Alice'。执行更新语句 UPDATE users SET name = 'Bob' WHERE id = 1;
 *             Redo Log会记录：
 *             数据页位置：表空间 users_tablespace，文件号 3，块号 1024，偏移量 0x100。
 *             修改内容：从偏移量 0x100 开始的字节变化（如将 'Alice' 的二进制值替换为 'Bob'）。
 *             事务ID：TX123，LSN：00000015。
 *
 *        当有一条记录需要更新的时候，InnoDB引擎就会先把记录写到redo log里面，并更新内存（Buffer Pool），这个时候更新就算完成了。
 *        同时，InnoDB引擎会在适当的时候，将这个操作记录更新到磁盘里面，而这个更新往往是在系统比较空闲的时候做。
 *
 *        InnoDB 的 redo log 是固定大小的，比如可以配置为一组 4 个文件，每个文件的大小是1GB，
 *        那么redo log总共就可以记录 4GB 的操作。从头开始写，写到末尾就又回到开头循环写。
 *     ④、总结
 *        Undo Log：用于事务回滚，记录修改前的数据状态，是InnoDB引擎层实现的
 *        Binlog：用于复制和恢复，记录数据变化操作，是MySQL Server层实现的
 *        Redo Log：用于崩溃恢复，记录物理页的修改（这里没有提到但也很重要）
 */
public class K11_存储引擎与log日志 {}
