/**
 * @author 游家纨绔
 */
/*
创建表：

  1、建表语句的语法格式：
        create table 表名(
                字段名1  数据类型,
                字段名2  数据类型,
                字段名3  数据类型,
           ...
        );
---------------------------------------------------------------------------------------------------------------
  2、关于MySQL当中字段的数据类型，以下只说常见的
        [1]、整数类型：
             int         整数型 (对应Java中的int)
             bigint      长整型 (对应Java中的long)
             tinyint(1)  布尔类型(对应Java中的boolean,用1代表true,0代表false)
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
             datetime    日期和时间，格式为 'YYYY-MM-DD HH:MM:SS'
             timestamp   自动记录插入或修改数据的日期和时间.需要设置默认值为当前时间并且会根据当前时间戳更新
               例如：`update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',


        char和varchar怎么选择？
            在实际的开发中，当某个字段中的数据长度不发生改变的时候，是定长的，例如：性别、生日等都是采用char
            当一个字段的数据长度不确定，例如：简介、姓名等都是采用varchar

        表名在数据库当中一般建议以：t_或者tbl_开始
---------------------------------------------------------------------------------------------------------------
  3、创建学生表：
         学生信息包括：序号、学号、姓名、性别、班级编号、生日
             序号：id
             学号：bigint
             姓名：varchar
             性别：char
             班级编号：int
             生日：char
             入学时间：datetime（格式2023-09-25 14:30:00）
             是否毕业：tinyint（1表示true，0表示false）

        create table t_students(
              id int NOT NULL AUTO_INCREMENT COMMENT '序号',
              no bigint unsigned NOT NULL COMMENT '学号',
              name varchar(255) NOT NULL COMMENT '姓名',
              sex char(11) NOT NULL COMMENT '性别',
              class_no varchar(255) DEFAULT '' COMMENT '班级编号',
              birth char(10) NOT NULL COMMENT '生日',
              time datetime NOT NULL COMMENT '入学时间',
              graduate tinyint(1) NOT NULL COMMENT '是否毕业',
              PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';
        // AUTO_INCREMENT=1表示的是自增将会从1开始，第一条数据的id序号将会是1。datetime格式2023-09-25 14:30:00
        +----------+--------------+------+-----+---------+----------------+
        | Field    | Type         | Null | Key | Default | Extra          |
        +----------+--------------+------+-----+---------+----------------+
        | id       | int(11)      | NO   | PRI | NULL    | auto_increment |
        | no       | bigint(20)   | NO   |     | NULL    |                |
        | name     | varchar(255) | NO   |     | NULL    |                |
        | sex      | char(11)     | NO   |     | NULL    |                |
        | class_no | varchar(255) | NO   |     |         |                |
        | birth    | char(10)     | NO   |     | NULL    |                |
        | time     | datetime     | NO   |     | NULL    |                |
        | graduate | tinyint(1)   | NO   |     | NULL    |                |
        +----------+--------------+------+-----+---------+----------------+
---------------------------------------------------------------------------------------------------------------
  4、向表中插入数据
      insert语句插入数据
          语法格式：INSERT INTO 表名(字段名1, 字段名2, 字段名3, ...) VALUES(值1, 值2, 值3, ...)
                  要求：字段的数量和值的数量相同，并且数据类型要对应相同

           INSERT INTO t_student(no,name,sex,class_no,birth) VALUES(1,'ZhangSan','1','高三1班','1950-10-12');
           +------+----------+------+------------+------------+
           | no   | name     | sex  | class_no   | birth      |
           +------+----------+------+------------+------------+
           |    1 | ZhangSan | 1    | 高三1班     | 1950-10-12 |
           +------+----------+------+------------+------------+

           INSERT INTO t_student(name) VALUES('lisi');    // 除name字段外，剩下的所有字段自动插入null
           +------+----------+------+------------+------------+
           | no   | name     | sex  | class_no   | birth      |
           +------+----------+------+------------+------------+
           |    1 | ZhangSan | 1    | 高三1班     | 1950-10-12 |
           | NULL | lisi     | NULL | NULL       | NULL       |
           +------+----------+------+------------+------------+

           drop table if exists t_student;   // 当这个表存在的话删除
           create table t_student(
               no bigint,
               name varchar(255),
               sex char(1) default 1,        // 默认值是1
               class_no varchar(255),
               birth char(10)
           );
           +---------+--------------+------+-----+---------+-------+
           | Field   | Type         | Null | Key | Default | Extra |
           +---------+--------------+------+-----+---------+-------+
           | no      | bigint(20)   | YES  |     | NULL    |       |
           | name    | varchar(255) | YES  |     | NULL    |       |
           | sex     | char(1)      | YES  |     | 1       |       | // 这里的默认值是1
           | class_no| varchar(255) | YES  |     | NULL    |       |
           | birth   | char(10)     | YES  |     | NULL    |       |
           +---------+--------------+------+-----+---------+-------+

          // 字段可以省略不写，但是后面的values对数量和顺序都有要求(要一一对应，不能少也不能多)
          INSERT INTO t_student VALUES(1, 'jack', '0', '高三2班', '1986-10-23');

          // 一次插入多行数据
          INSERT INTO t_student
             (no,name,sex,class_no,birth)
          VALUES
             (3,'rose','1','高三2班','1952-12-14'),
             (4,'LaoTie','1','高三2班','1955-12-14');
          +------+----------+------+------------+------------+
          | no   | name     | sex  | class_no   | birth      |
          +------+----------+------+------------+------------+
          |    1 | ZhangSan | 1    | 高三1班     | 1950-10-12 |
          |    1 | jack     | 0    | 高三2班     | 1986-10-23 |
          |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
          |    4 | LaoTie   | 1    | 高三2班     | 1955-12-14 |
          +------+----------+------+------------+------------+
---------------------------------------------------------------------------------------------------------------
  5、表的复制
        语法：create table 表名 as select语句;
             将查询结果当作表创建出来;
        例如：create table t_student20230925 AS SELECT * FROM t_student;
---------------------------------------------------------------------------------------------------------------
  6、批量插入
      INSERT INTO t_student1 SELECT * FROM t_student;
      +------+----------+------+------------+------------+
      | no   | name     | sex  | class_no   | birth      |
      +------+----------+------+------------+------------+
      |    1 | ZhangSan | 1    | 高三1班     | 1950-10-12 |
      |    1 | jack     | 0    | 高三2班     | 1986-10-23 |
      |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
      |    4 | LaoTie   | 1    | 高三2班     | 1955-12-14 |

      |    1 | ZhangSan | 1    | 高三1班     | 1950-10-12 |
      |    1 | jack     | 0    | 高三2班     | 1986-10-23 |
      |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
      |    4 | LaoTie   | 1    | 高三2班     | 1955-12-14 |
      +------+----------+------+------------+------------+
---------------------------------------------------------------------------------------------------------------
  7、修改数据：update
        语法格式：update 表名 set 字段名1=值1，字段名2=值2...where 条件;
        注意：如果没有where条件,那么将会把整张表数据全部更新

              案例：将学号为1的class_no修改为高三3班,将sex修改为0
              UPDATE t_student1 SET class_no='高三3班', sex='0' WHERE no=1;
              +------+----------+------+------------+------------+
              | no   | name     | sex  | class_no   | birth      |
              +------+----------+------+------------+------------+
              |    1 | ZhangSan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
              |    4 | LaoTie   | 1    | 高三2班     | 1955-12-14 |
              |    1 | ZhangSan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
              |    4 | LaoTie   | 1    | 高三2班     | 1955-12-14 |
              +------+----------+------+------------+------------+

              更新所有记录：
              UPDATE t_student1 SET class_no='高三3班', sex='0';
              +------+----------+------+------------+------------+
              | no   | name     | sex  | class_no   | birth      |
              +------+----------+------+------------+------------+
              |    1 | ZhangSan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 0    | 高三3班     | 1952-12-14 |
              |    4 | LaoTie   | 0    | 高三3班     | 1955-12-14 |
              |    1 | ZhangSan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 0    | 高三3班     | 1952-12-14 |
              |    4 | LaoTie   | 0    | 高三3班     | 1955-12-14 |
              +------+----------+------+------------+------------+
---------------------------------------------------------------------------------------------------------------
  8、删除数据
        语法格式：delete from 表名 where 条件;
        注意：如果没有where条件，默认是全部删除

           案例：删除学号1数据
           DELETE FROM t_student1 WHERE no = 1;
           +------+--------+------+------------+------------+
           | no   | name   | sex  | class_no   | birth      |
           +------+--------+------+------------+------------+
           |    3 | rose   | 0    | 高三3班     | 1952-12-14 |
           |    4 | LaoTie | 0    | 高三3班     | 1955-12-14 |
           |    3 | rose   | 0    | 高三3班     | 1952-12-14 |
           |    4 | LaoTie | 0    | 高三3班     | 1955-12-14 |
           +------+--------+------+------------+------------+

           删除表所有记录
           delete from t_student1;  // 删除大表比较慢，可回滚，能找回

           删除表所有数据
           truncate table 表名；     // 表数据被截断，不可回滚，永久丢失。

           删除表数据跟结构
           drop table 表名;         // 是把数据库中指定的表数据和表结构同时删掉，不可回滚，永久丢失。

      增删改查有一个术语：CRUD操作。。。（以后就别说增删改查了，太low了）
      Create(增)    Retrieve(检索)    Update(修改)     Delete(删除)
---------------------------------------------------------------------------------------------------------------
   9、创建表之后新增字段
      基本语法: ALTER TABLE 表名 ADD COLUMN 字段名 字段类型 [约束条件];
      # 约束条件可以不写,使用默认规则: 新增到最后一列    FIRST: 新增在第一列     AFTER: 在某字段后     BEFORE: 在某字段之前

        ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄';
        ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' FIRST;
        ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' AFTER name;
        ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' BEFORE name;
        ALTER TABLE t_student ADD COLUMN age int(11) DEFAULT NULL COMMENT '年龄' AFTER name BEFORE sex;
---------------------------------------------------------------------------------------------------------------
   10、创建表之后修改字段名
       # ALTER TABLE 表名  CHANGE 旧字段名 新字段名 新数据类型;
         ALTER TABLE t_student CHANGE name user_name varchar(32) DEFAULT NULL COMMENT '姓名';
---------------------------------------------------------------------------------------------------------------
   11、创建表之后修改字段类型
       # ALTER TABLE 表名 MODIFY  字段名  数据类型;
         ALTER TABLE t_student MODIFY name varchar(32) DEFAULT NULL COMMENT '姓名';
---------------------------------------------------------------------------------------------------------------
   12、创建表之后删除字段
       # ALTER TABLE 表名  DROP 字段名;
         ALTER TABLE t_student  DROP name;
---------------------------------------------------------------------------------------------------------------
   13、创建表之后设置自增
       # ALTER TABLE 表名  AUTO_INCREMENT = 预想的下一次插入数据的自增值;
         ALTER TABLE t_student AUTO_INCREMENT = 10000;
 */
public class I9表的创建 {
}
