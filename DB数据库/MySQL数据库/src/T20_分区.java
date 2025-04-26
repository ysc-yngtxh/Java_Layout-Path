/**
 * @author 游家纨绔
 * @dateTime 2024-09-21 21:09
 * @apiNote TODO NySql分区不常用，顶多也只是 RANGE 分区
 */
/* 分区
 *    [1]、所谓的分区就是将一个表分解成多个区块进行操作和保存，从而降低每次操作的数据，提高性能，而对应用来说是透明的，
 *        从逻辑上看就只是一个表（这里跟分库分表的访问不一样），但是物理上的这个表可能是由多个物理分区组成，
 *        每个分区都是一个独立的对象，可以进行独立处理。
 *
 *    [2]、分区目的
 *         1、进行逻辑数据分割，分割数据能够有多个不同的物理文件路径。
 *         2、可以保存更多的数据，突破系统单个文件的最大限制。
 *         3、提升性能，提高每个分区的读写速度，提高分区范围查询的速度。
 *         4、可以通过删除相关分区来快速删除数据。
 *         5、通过跨多个磁盘来分散数据查询，从而提高磁盘I/O的性能。
 *         6、涉及到例如 SUM() 和 COUNT() 这样聚合函数的查询，可以很容易地进行并行处理。
 *         7、分区能支持引擎。如MyISAM、InnoDB等；不支持MERGE和CSV等创建分区。
 *         8、分区表的数据存储在各个分区中，每个分区可以单独设置索引，从而提高查询性能。
 *
 *    [3]、示例
 *         1、RANGE分区：基于属于一个给定连续区间的列值，把多行分配给分区。
 *            例 1：创建分区表的分区键为 int 类型
 *                -- 创建数据库
 *                CREATE DATABASE partitioning DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
 *                CREATE TABLE tbl_users (
 *                	 `uuid`         INT PRIMARY KEY,
 *                	 `customerId`   VARCHAR(200),
 *                	 `pwd`          VARCHAR(20),
 *                	 `showName`     VARCHAR(100),
 *                	 `trueName`     VARCHAR(100),
 *                	 `registerTime` VARCHAR(100)
 *                ) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
 *                  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT '字典类别表'
 *                  -- 注意：建表时创建分区，应在建表语句后连着书写，不能断开单独书写
 *                  PARTITION BY RANGE (uuid) (
 *                		PARTITION p0 VALUES LESS THAN (5),
 *                		PARTITION p1 VALUES LESS THAN (10),
 *                		PARTITION p2 VALUES LESS THAN (15),
 *                		PARTITION p3 VALUES LESS THAN MAXVALUE
 *                  );
 *                -- 插入数据
 *                INSERT INTO tbl_users VALUES(1,   "只为你",   "123456", "qzp",     "quezhipeng", "20200808"),
 *                                            (6,   "人生",    "123456",  "人生",     "无名",       "20200808"),
 *                                            (12,  "无须终有", "123456",  "无须终有", "无声",       "20200808"),
 *                                            (100, "坚持",    "123456",  "胜利",     "坚持",       "20200808");
 *            注意：(1).如果表中存在primary key 或者 unique key 时，分区的列必须是primary key 或者 unique key的一个组成部分，
 *                     也就是说，分区函数的列只能从primary key或者 unique key这些key中取子集。
 *                 (2).如果表中不存在任何的primary key 或者 unique key 时，则可以指定任何一个列作为分区列。
 *
 *            查看分区信息
 *              SELECT * FROM information_schema.`PARTITIONS` WHERE table_schema='partitioning' AND table_name='tbl_users';
 *            查看分区上的数据
 *              SELECT * FROM tbl_users PARTITION(p0);
 *            查看MySQL操作的分区
 *              EXPLAIN SELECT * FROM tbl_users PARTITIONS WHERE uuid = 2;
 *
 *            例 2：创建分区表的分区键为 非整型 类型：
 *                CREATE TABLE tbl_users2 (
 *                	 `uuid`       INT NOT NULL,
 *                	 `customerId` VARCHAR(200),
 *                	 `pwd`        VARCHAR(20)
 *                )
 *                -- 字符串比较是进行字符比较
 *                PARTITION BY RANGE columns(customerId) (
 *                		PARTITION p0 VALUES LESS THAN ('id05'),
 *                		PARTITION p1 VALUES LESS THAN ('id10'),
 *                		PARTITION p2 VALUES LESS THAN ('id15')
 *                );
 *                -- 插入数据
 *                INSERT INTO tbl_users2 VALUES(1, "id01", "123456"),
 *                                             (2, "id06", "123456"),
 *                                             (3, "id12", "123456");
 *
 *         2、LIST分区：类似于按RANGE分区，LIST是列值匹配一个离散值集合中的某个值来进行选择。
 *                CREATE TABLE tbl_users2 (
 *                  `uuid`         INT NOT NULL,
 *                  `customerId`   VARCHAR(200),
 *                  `pwd`          VARCHAR(20),
 *                  `showName`     VARCHAR(100),
 *                  `trueName`     VARCHAR(100),
 *                  `registerTime` VARCHAR(100)
 *                )
 *                PARTITION BY List(uuid) (
 *                	PARTITION p0 VALUES in(1,2,3,5),
 *                	PARTITION p1 VALUES in(7,9,10),
 *                	PARTITION p2 VALUES in(11,15)
 *                );
 *                -- 插入数据，注意：加入数据需要根据主键来，不可以时主键分区中没有的，否则报错
 *                INSERT INTO tbl_users2 VALUES(1,  "只为你",  "123456", "qzp",     "quezhipeng", "20200808"),
 *                                             (7,  "人生",    "123456", "人生",     "无名",       "20200808"),
 *                                             (10, "无须终有", "123456", "无须终有", "无声",        "20200808"),
 *                                             (15, "坚持",    "123456", "胜利",     "坚持",        "20200808");
 *           注意：如果试图操作的列值不在分区值列表中时，那么会失败并报错。
 *                要注意的是，LIST分区没有类似如：“VALUES LESS THAN” 这样的包含其他值在内的定义，
 *                将要匹配的任何值都必须在值列表中找到。
 *
 *         3.HASH分区：基于用户定义的表达式的返回值来进行选择的分区，该表达式使用将要插入到表中的这些行的列值进行计算，这个函数必须产生非负整数值。
 *               -- 创建一张hash分区表
 *               CREATE TABLE tbl_users4 (
 *               	`uuid`         INT NOT NULL,
 *               	`customerId`   VARCHAR(200),
 *               	`pwd`          VARCHAR(20),
 *               	`showName`     VARCHAR(100),
 *               	`trueName`     VARCHAR(100),
 *               	`registerTime` VARCHAR(100)
 *               )
 *               PARTITION BY hash(uuid)
 *               	PARTITIONS 3;
 *               -- 插入数据，注意：分区是有uuid/3求余数决定：余数为0，在p0; 余数为1，在p1; 余数为2，在p2,以此类推....
 *               INSERT INTO tbl_users2 VALUES(1,  "只为你",  "123456", "qzp",     "quezhipeng", "20200808"),
 *                                            (7,  "人生",    "123456", "人生",     "无名",       "20200808"),
 *                                            (10, "无须终有", "123456", "无须终有", "无声",        "20200808"),
 *                                            (15, "坚持",    "123456", "胜利",     "坚持",        "20200808");
 *
 *         4.KEY分区：类似于按HASH分区，由MySQL服务器提供其自身的哈希函数。
 *               -- 创建一张key分区表
 *               CREATE TABLE tbl_users5 (
 *               	`uuid` INT NOT NULL,
 *               	`customerId` VARCHAR(200),
 *               	`pwd` VARCHAR(20),
 *               	`showName` VARCHAR(100),
 *               	`trueName` VARCHAR(100),
 *               	`registerTime` VARCHAR(100)
 *               )
 *               PARTITION BY LINEAR key(uuid)
 *               	PARTITIONS 3;
 *               -- 插入数据，注意：分区是有uuid/3求余数决定：余数为0，在p0;余数为1，在p1;余数为2，在p2,以此类推....
 *               INSERT INTO tbl_users5 VALUES(1,  "只为你",  "123456", "qzp",     "quezhipeng", "20200808"),
 *                                            (7,  "人生",    "123456", "人生",     "无名",       "20200808"),
 *                                            (10, "无须终有", "123456", "无须终有", "无声",        "20200808"),
 *                                            (15, "坚持",    "123456", "胜利",     "坚持",        "20200808");
 *
 *          5.子分区
 *               CREATE TABLE tbl_users6 (
 *                 `uuid` INT NOT NULL,
 *                 `customerId` VARCHAR(200),
 *                 `pwd` VARCHAR(20),
 *                 `showName` VARCHAR(100),
 *                 `trueName` VARCHAR(100),
 *                 `registerTime` DATE
 *               )
 *               PARTITION BY RANGE(YEAR(registerTime))        -- 主分区
 *               	SUBPARTITION BY HASH(TO_DAYS(registerTime)) -- 子分区hash分区，求余
 *               	SUBPARTITIONS 2
 *               	(
 *               		PARTITION P0 VALUES LESS THAN (2008),
 *               		PARTITION P1 VALUES LESS THAN (2015),
 *               		PARTITION P2 VALUES LESS THAN MAXVALUE
 *               	);
 *               -- 插入数据
 *               INSERT INTO tbl_users6 VALUES(1,  "只为你",  "123456", "qzp",     "quezhipeng", "20200808"),
 *                                            (7,  "人生",    "123456", "人生",     "无名",       "20200808"),
 *                                            (10, "无须终有", "123456", "无须终有", "无声",        "20200808"),
 *                                            (15, "坚持",    "123456", "胜利",     "坚持",        "20200808");
 */
public class T20_分区 {}
