/**
 * @author 游家纨绔
 */
/*
创建表
  1、关于MySQL当中字段的数据类型，以下只说常见的
        [1]、整数类型：
             int         整数型 (对应Java中的int)
             bigint      长整型 (对应Java中的long)
             tinyint(1)  布尔类型(对应Java中的boolean，用1代表true，0代表false)
             smallint    整数型 (对应Java中的short)
                   字段如果设置为UNSIGNED类型: 只能存储正整数,不能用来储存负数；例如：tinyint 类型只能存储0到255的整数
                   字段如果不设置UNSIGNED类型: 存储该类型范围的整数；例如：tinyint 类型只能存储-128到127的整数
                   例如：无符号 `id` bigint un signed NOT NULL AUTO_INCREMENT COMMENT '订单id',
        [2]、浮点类型：
             float       单精度浮点数 (对应Java中的float)
             double      双精度浮点数 (对应Java中的double)
        [3]、字符串类型：
             char        固定长度字符串，最多 255 个字符 (对应Java中的String)
             varchar     可变长度字符串，最多 65535 个字符 (对应Java中的StringBuffer/StringBuilder)
             text        可变长度字符串，最多 65535 个字符，适合存储大段文本
             BLOB        二进制大对象 (Binary Large Object 存储图片、视频等流媒体信息)
             CLOB        字符大对象 (Character Large Object 存储较大文本，比如，可以存储4G的字符串)
        [4]、日期和时间类型：
             date        日期，格式为 'YYYY-MM-DD' (对应Java中的java.sql.Date类型)
             time        时间，格式为 'HH:MM:SS'
             datetime    客户端插入的时间不做任何改变，基本上是原样输入和输出。
             timestamp   把客户端插入的时间从当前时区转化为UTC（世界标准时间）进行存储。查询时，将其又转化为客户端当前时区进行返回。

             timestamp所能存储的时间范围为：‘1970-01-01 00:00:01.000000’ 到 ‘2038-01-19 03:14:07.999999’。
             datetime所能存储的时间范围为：‘1000-01-01 00:00:00.000000’ 到 ‘9999-12-31 23:59:59.999999’。
             datetime和timestamp都表示日期和时间，格式都为 'YYYY-MM-DD HH:MM:SS'。
             总结：datetime和timestamp除了存储范围和存储方式不一样，没有太大区别。当然，对于跨时区的业务，TIMESTAMP更为合适。

        对于创建表时间字段：`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                         DEFAULT CURRENT_TIMESTAMP 表示创建时获取当前时间戳
                         ON UPDATE CURRENT_TIMESTAMP 表示根据当前时间戳更新

        char和varchar怎么选择？
            在实际的开发中，当某个字段中的数据长度不发生改变的时候，是定长的，例如：性别、生日等都是采用char
            当一个字段的数据长度不确定，例如：简介、姓名等都是采用varchar
---------------------------------------------------------------------------------------------------------------
  2、创建学生表
        建表语句的语法格式：                   学生信息包括：
          create table 表名(                           序号：id
              字段名1  数据类型,                         学号：bigint
              字段名2  数据类型,                         姓名：varchar
              字段名3  数据类型,                         性别：char
              ...                                     班级编号：int
          );                                          生日：char
                                                      入学时间：datetime（格式2023-09-25 14:30:00）
                                                      是否毕业：tinyint（1表示true，0表示false）
        drop table if exists t_students;   // 当这个t_student表存在的话就删除掉
        create table t_students(
              id int NOT NULL AUTO_INCREMENT COMMENT '序号',
              no bigint unsigned NOT NULL COMMENT '学号',
              name varchar(255) NOT NULL COMMENT '姓名',
              sex char(11) NOT NULL COMMENT '性别',
              class_no varchar(255) DEFAULT '' COMMENT '班级编号',
              birth char(10) NOT NULL COMMENT '生日',
              time datetime DEFAULT CURRENT_TIMESTAMP COMMENT '入学时间',
              update_time timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
              graduate tinyint(1) DEFAULT 1 COMMENT '是否毕业',
              PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4
          COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='学生信息表';
        // unsigned关键字：表示的是为“无符号”的意思，即为非负数，用此类型可以增加数据长度：
                          例如 tinyint的范围[-128,127] 最大值是127，tinyint unsigned的范围[0,127*2] 最大值就可以到127*2，
                          且unsigned只针对整型的数据类型。
        // AUTO_INCREMENT=1：表示的是自增将会从1开始，第一条数据的id序号将会是1。datetime格式2023-09-25 14:30:00
        // ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4：表示使用innodb存储引擎，下一个主键自增序号为1，默认字符集为 utf8mb4
        // COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC：字符集校对规则为utf8mb4_0900_ai_ci。DYNAMIC为默认行格式，它允许记录的长度可变
        +-------------+-----------------+------+-----+-------------------+-----------------------------------------------+
        | Field       | Type            | Null | Key | Default           | Extra                                         |
        +-------------+-----------------+------+-----+-------------------+-----------------------------------------------+
        | id          | int             | NO   | PRI | NULL              | auto_increment                                |
        | no          | bigint unsigned | NO   |     | NULL              |                                               |
        | name        | varchar(255)    | NO   |     | NULL              |                                               |
        | sex         | char(11)        | NO   |     | NULL              |                                               |
        | class_no    | varchar(255)    | YES  |     |                   |                                               |
        | birth       | char(10)        | NO   |     | NULL              |                                               |
        | time        | datetime        | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED                             |
        | update_time | timestamp       | YES  |     | CURRENT_TIMESTAMP | DEFAULT_GENERATED on update CURRENT_TIMESTAMP |
        | graduate    | tinyint(1)      | YES  |     | 1                 |                                               |
        +-------------+-----------------+------+-----+-------------------+-----------------------------------------------+
---------------------------------------------------------------------------------------------------------------
  3、插入数据：insert
        语法格式：INSERT INTO 表名(字段名1, 字段名2, 字段名3, ...) VALUES(值1, 值2, 值3, ...)
        注意：字段的数量和值的数量相同，并且数据类型要对应相同

             INSERT INTO t_students(id, no, name, sex, class_no, birth, time, update_time, graduate)
             VALUES(1, 1, 'ZhangSan', '1', '高三1班', '1950-10-12', now(), now(), 1);
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             | id | no | name     | sex | class_no   | birth      | time                | update_time        | graduate|
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             |  1 |  1 | ZhangSan | 1   | 高三1班     | 1950-10-12 | 2023-10-05 04:20:00 | 2023-10-05 04:20:00| 1       |
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+

             // 除no、name、sex、birth字段外，剩下的其他字段有默认值自动添加默认值，没有默认值自动插入null
                INSERT INTO t_students(no, name, sex, birth) VALUES(2, 'lisi', 1, '1997-04-29');

             // 插入数据时字段可以省略不写，但是后面的values对数量和顺序都有要求(要一一对应，不能少也不能多)
                INSERT INTO t_students VALUES(3, 3, 'ZhangSan2', '1', '高三1班', '1990-10-12', now(), now(), 1);

             // 一次插入多行数据
                INSERT INTO t_students(id, no, name, sex, class_no, birth, time, update_time, graduate)
                VALUES(4, 4, 'ZhangSan3', '1', '高三2班', '1991-10-12', now(), now(), 2),
                      (5, 5, 'ZhangSan4', '0', '高三3班', '1992-10-12', now(), now(), 2);
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             | id | no | name     | sex | class_no   | birth      | time                | update_time        | graduate|
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             |  1 |  1 | ZhangSan | 1   | 高三1班     | 1950-10-12 | 2023-10-05 04:20:00 | 2023-10-05 04:20:00| 1       |
             |  2 |  2 | lisi     | 1   |            | 1997-04-29 | 2023-10-05 04:30:00 | 2023-10-05 04:30:00| 1       |
             |  3 |  3 | ZhangSan2| 1   | 高三1班     | 1990-10-12 | 2023-10-05 04:40:00 | 2023-10-05 04:40:00| 1       |
             |  4 |  4 | ZhangSan3| 1   | 高三2班     | 1991-10-12 | 2023-10-05 04:50:00 | 2023-10-05 04:50:00| 1       |
             |  5 |  5 | ZhangSan4| 0   | 高三3班     | 1992-10-12 | 2023-10-05 05:00:00 | 2023-10-05 05:00:00| 1       |
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
---------------------------------------------------------------------------------------------------------------
  4、表的复制
        语法：create table 表名 as select语句;
             将查询结果当作表创建出来;
        例如：create table t_student20230925 AS SELECT * FROM t_students;
---------------------------------------------------------------------------------------------------------------
  5、批量插入
        INSERT INTO t_study SELECT * FROM t_students;
        +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
        | id | no | name     | sex | class_no   | birth      | time                | update_time        | graduate|
        +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
        |  6 |  6 | WangWu1  | 1   | 大三1班     | 1850-10-12 | 2023-10-05 04:20:00 | 2023-10-05 04:20:00| 1       |
        |  7 |  7 | WangWu2  | 1   | 大三1班     | 1897-04-29 | 2023-10-05 04:30:00 | 2023-10-05 04:30:00| 1       |
        |  8 |  8 | WangWu3  | 1   | 大三1班     | 1890-10-12 | 2023-10-05 04:40:00 | 2023-10-05 04:40:00| 1       |
        |  9 |  9 | WangWu4  | 1   | 大三2班     | 1891-10-12 | 2023-10-05 04:50:00 | 2023-10-05 04:50:00| 1       |
        |  10|  10| WangWu5  | 0   | 大三3班     | 1892-10-12 | 2023-10-05 05:00:00 | 2023-10-05 05:00:00| 1       |

        |  1 |  1 | ZhangSan | 1   | 高三1班     | 1950-10-12 | 2023-10-05 04:20:00 | 2023-10-05 04:20:00| 1       |
        |  2 |  2 | lisi     | 1   |            | 1997-04-29 | 2023-10-05 04:30:00 | 2023-10-05 04:30:00| 1       |
        |  3 |  3 | ZhangSan2| 1   | 高三1班     | 1990-10-12 | 2023-10-05 04:40:00 | 2023-10-05 04:40:00| 1       |
        |  4 |  4 | ZhangSan3| 1   | 高三2班     | 1991-10-12 | 2023-10-05 04:50:00 | 2023-10-05 04:50:00| 1       |
        |  5 |  5 | ZhangSan4| 0   | 高三3班     | 1992-10-12 | 2023-10-05 05:00:00 | 2023-10-05 05:00:00| 1       |
        +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
---------------------------------------------------------------------------------------------------------------
  6、修改数据：update
        语法格式：update 表名 set 字段名1=值1,字段名2=值2 ... where 条件;
        注意：如果没有where条件,那么将会把整张表数据全部更新

             案例：将学号为1的class_no修改为高三3班，将sex修改为0
             UPDATE t_students SET class_no='高三3班',sex='0' WHERE no=1;
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             | id | no | name     | sex | class_no   | birth      | time                | update_time        | graduate|
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             |  1 |  1 | ZhangSan | 0   | 高三3班     | 1950-10-12 | 2023-10-05 04:20:00 | 2023-10-05 04:20:00| 1       |
             |  2 |  2 | lisi     | 1   |            | 1997-04-29 | 2023-10-05 04:30:00 | 2023-10-05 04:30:00| 1       |
             |  3 |  3 | ZhangSan2| 1   | 高三1班     | 1990-10-12 | 2023-10-05 04:40:00 | 2023-10-05 04:40:00| 1       |
             |  4 |  4 | ZhangSan3| 1   | 高三2班     | 1991-10-12 | 2023-10-05 04:50:00 | 2023-10-05 04:50:00| 1       |
             |  5 |  5 | ZhangSan4| 0   | 高三3班     | 1992-10-12 | 2023-10-05 05:00:00 | 2023-10-05 05:00:00| 1       |
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+

             更新所有记录：
             UPDATE t_students SET class_no='高三3班', sex='0';
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             | id | no | name     | sex | class_no   | birth      | time                | update_time        | graduate|
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             |  1 |  1 | ZhangSan | 0   | 高三3班     | 1950-10-12 | 2023-10-05 04:20:00 | 2023-10-05 04:20:00| 1       |
             |  2 |  2 | lisi     | 0   | 高三3班     | 1997-04-29 | 2023-10-05 04:30:00 | 2023-10-05 04:30:00| 1       |
             |  3 |  3 | ZhangSan2| 0   | 高三3班     | 1990-10-12 | 2023-10-05 04:40:00 | 2023-10-05 04:40:00| 1       |
             |  4 |  4 | ZhangSan3| 0   | 高三3班     | 1991-10-12 | 2023-10-05 04:50:00 | 2023-10-05 04:50:00| 1       |
             |  5 |  5 | ZhangSan4| 0   | 高三3班     | 1992-10-12 | 2023-10-05 05:00:00 | 2023-10-05 05:00:00| 1       |
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
---------------------------------------------------------------------------------------------------------------
  7、删除数据：delete
        语法格式：delete from 表名 where 条件;
        注意：如果没有where条件，默认是全部删除

             案例：删除学号1数据
             DELETE FROM t_students WHERE no = 1;
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             | id | no | name     | sex | class_no   | birth      | time                | update_time        | graduate|
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+
             |  2 |  2 | lisi     | 0   | 高三3班     | 1997-04-29 | 2023-10-05 04:30:00 | 2023-10-05 04:30:00| 1       |
             |  3 |  3 | ZhangSan2| 0   | 高三3班     | 1990-10-12 | 2023-10-05 04:40:00 | 2023-10-05 04:40:00| 1       |
             |  4 |  4 | ZhangSan3| 0   | 高三3班     | 1991-10-12 | 2023-10-05 04:50:00 | 2023-10-05 04:50:00| 1       |
             |  5 |  5 | ZhangSan4| 0   | 高三3班     | 1992-10-12 | 2023-10-05 05:00:00 | 2023-10-05 05:00:00| 1       |
             +----+----+----------+-----+------------+------------+---------------------+--------------------+---------+

             删除表所有记录
             delete from 表名;      // 删除大表比较慢，可回滚，能找回

             删除表所有数据
             truncate table 表名；  // 表数据被截断，不可回滚，永久丢失。

             删除表数据跟结构
             drop table 表名;       // 是把数据库中指定的表数据和表结构同时删掉，不可回滚，永久丢失。

        增删改查有一个术语：CRUD操作。。。（以后就别说增删改查了，太low了）
        Create(增)    Retrieve(检索)    Update(修改)     Delete(删除)
---------------------------------------------------------------------------------------------------------------
  8、创建表之后新增、修改、删除字段
        [1]、创建表之后新增字段
             基本语法: ALTER TABLE 表名 ADD COLUMN 字段名 字段类型 [约束条件];
             # 约束条件可以不写  ①、默认规则: 新增到最后一列   ②、FIRST: 新增在第一列
                              ③、AFTER: 在某字段后        ④、BEFORE: 在某字段之前
             ALTER TABLE t_student ADD COLUMN age int(11) UNIQUE COMMENT '年龄';
             ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄';
             ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' FIRST;
             ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' AFTER name;
             ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' BEFORE name;
             ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' AFTER name BEFORE sex;
        [2]、创建表之后修改字段名
             # ALTER TABLE 表名 CHANGE 旧字段名 新字段名 新数据类型;
             ALTER TABLE t_student CHANGE name user_name varchar(32) UNIQUE COMMENT '姓名';
             ALTER TABLE t_student CHANGE name user_name varchar(32) DEFAULT NULL COMMENT '姓名';
        [3]、创建表之后修改字段类型
             # ALTER TABLE 表名 MODIFY 字段名 数据类型;
             ALTER TABLE t_student MODIFY name varchar(32) UNIQUE COMMENT '姓名';
             ALTER TABLE t_student MODIFY name varchar(32) DEFAULT NULL COMMENT '姓名';
        [4]、创建表之后删除字段
             # ALTER TABLE 表名 DROP 字段名;
             ALTER TABLE t_student DROP name;
        [5]、创建表之后设置自增
             # ALTER TABLE 表名 AUTO_INCREMENT = 预想的下一次插入数据的自增值;
             ALTER TABLE t_student AUTO_INCREMENT = 10000;
---------------------------------------------------------------------------------------------------------------
  9、备份表
        语法：mysqldump -u root -p [database] [table...] > [custom-path]

        示例：mysqldump -u root -p springdb t_student > /Users/ysc/Desktop/t_student.sql 
 */
public class I9_表的创建 {}
