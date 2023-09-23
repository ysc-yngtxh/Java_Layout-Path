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

  2、关于MySQL当中字段的数据类型，以下只说常见的
        int        整数型 (java中的int)
        bigint     长整型 (java中的long)
        float      浮点型 (java中的float double)
        char       定长字符串 (String)
        varchar    可变长字符串 (StringBuffer/StringBuilder)
        date       日期类型 (对应Java中的java.sql.Date类型)
        tinyint(1) 布尔类型(对应Java中的boolean,用1代表true,0代表false)
        BLOB       二进制大对象 (Binary Large Object 存储图片、视频等流媒体信息)
        CLOB       字符大对象 (Character Large Object 存储较大文本，比如，可以存储4G的字符串)

        char和varchar怎么选择？
            在实际的开发中，当某个字段中的数据长度不发生改变的时候，是定长的，例如：性别、生日等都是采用char
            当一个字段的数据长度不确定，例如：简介、姓名等都是采用varchar

            表名在数据库当中一般建议以：t_或者tbl_开始

  3、创建学生表：
         学生信息包括：
             序号、学号、姓名、性别、班级编号、生日
             序号：id
             学号：bigint
             姓名：varchar
             性别：char
             班级编号：int
             生日：char
             入学时间：date（格式2022-02-21 18:12:13）
             是否毕业：tinyint（1表示true，0表示false）

        create table t_students(
              id int NOT NULL AUTO_INCREMENT COMMENT '序号',
              no bigint NOT NULL COMMENT '学号',
              name varchar(255) NOT NULL COMMENT '姓名',
              sex char(11) NOT NULL COMMENT '性别',
              class_no varchar(255) DEFAULT '' COMMENT '班级编号',
              birth char(10) NOT NULL COMMENT '生日',
              time date NOT NULL COMMENT '入学时间',
              graduate tinyint(1) NOT NULL COMMENT '是否毕业',
              PRIMARY KEY (`id`)
        ) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='学生信息表';
        // AUTO_INCREMENT=24表示的是自增将会从24开始，第一条数据的序号将会是24。date格式2022-02-21 18:12:13
        +----------+--------------+------+-----+---------+----------------+
        | Field    | Type         | Null | Key | Default | Extra          |
        +----------+--------------+------+-----+---------+----------------+
        | id       | int(11)      | NO   | PRI | NULL    | auto_increment |
        | no       | bigint(20)   | NO   |     | NULL    |                |
        | name     | varchar(255) | NO   |     | NULL    |                |
        | sex      | char(11)     | NO   |     | NULL    |                |
        | class_no | varchar(255) | NO   |     |         |                |
        | birth    | char(10)     | NO   |     | NULL    |                |
        | time     | date         | NO   |     | NULL    |                |
        | graduate | tinyint(1)   | NO   |     | NULL    |                |
        +----------+--------------+------+-----+---------+----------------+

   4、向表中插入数据
      insert语句插入数据
          语法格式：INSERT INTO 表名(字段名1，字段名2，字段名3,...) VALUES(值1,值2,值3,...)
                  要求：字段的数量和值的数量相同，并且数据类型要对应相同

           INSERT INTO t_student(no,name,sex,class_no,birth) VALUES(1,'zhangsan','1','高三1班','1950-10-12');
           +------+----------+------+------------+------------+
           | no   | name     | sex  | class_no   | birth      |
           +------+----------+------+------------+------------+
           |    1 | zhangsan | 1    | 高三1班     | 1950-10-12 |
           +------+----------+------+------------+------------+

           INSERT INTO t_student(name) VALUES('lisi');    // 除name字段外，剩下的所有字段自动插入null
           +------+----------+------+------------+------------+
           | no   | name     | sex  | class_no   | birth      |
           +------+----------+------+------------+------------+
           |    1 | zhangsan | 1    | 高三1班     | 1950-10-12 |
           | NULL | lisi     | NULL | NULL       | NULL       |
           +------+----------+------+------------+------------+
           ** 需要注意的地方：
                当一条insert语句执行成功之后，表格当中必然会多一行记录。
                即使多的这一行记录当中某些字段是null，后期也没有办法再执行insert语句插入数据了，只能使用update更新

           drop table if exists t_student;   // 当这个表存在的话删除
           create table t_student(
              no bigint,
              name varchar(255),
              sex char(1) default 1,         // 默认值是1
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
             (4,'laotie','1','高三2班','1955-12-14');
          +------+----------+------+------------+------------+
          | no   | name     | sex  | class_no   | birth      |
          +------+----------+------+------------+------------+
          |    1 | zhangsan | 1    | 高三1班     | 1950-10-12 |
          |    1 | jack     | 0    | 高三2班     | 1986-10-23 |
          |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
          |    4 | laotie   | 1    | 高三2班     | 1955-12-14 |
          +------+----------+------+------------+------------+

  5、表的复制
        语法：create table 表名 as select语句;
             将查询结果当作表创建出来;
        例如：create table t_student1 AS SELECT * FROM t_student;

  6、批量插入
      INSERT INTO t_student1 SELECT * FROM t_student;
      +------+----------+------+------------+------------+
      | no   | name     | sex  | class_no   | birth      |
      +------+----------+------+------------+------------+
      |    1 | zhangsan | 1    | 高三1班     | 1950-10-12 |
      |    1 | jack     | 0    | 高三2班     | 1986-10-23 |
      |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
      |    4 | laotie   | 1    | 高三2班     | 1955-12-14 |

      |    1 | zhangsan | 1    | 高三1班     | 1950-10-12 |
      |    1 | jack     | 0    | 高三2班     | 1986-10-23 |
      |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
      |    4 | laotie   | 1    | 高三2班     | 1955-12-14 |
      +------+----------+------+------------+------------+

  7、修改数据：update
        语法格式：update 表名 set 字段名1=值1，字段名2=值2...where 条件;
        注意：如果没有where条件,那么将会把整张表数据全部更新

              案例：将学号为1的class_no修改为高三3班,将sex修改为0
              UPDATE t_student1 SET class_no='高三3班', sex='0' WHERE no=1;
              +------+----------+------+------------+------------+
              | no   | name     | sex  | class_no   | birth      |
              +------+----------+------+------------+------------+
              |    1 | zhangsan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
              |    4 | laotie   | 1    | 高三2班     | 1955-12-14 |
              |    1 | zhangsan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 1    | 高三2班     | 1952-12-14 |
              |    4 | laotie   | 1    | 高三2班     | 1955-12-14 |
              +------+----------+------+------------+------------+

              更新所有记录：
              UPDATE t_student1 SET class_no='高三3班', sex='0';
              +------+----------+------+------------+------------+
              | no   | name     | sex  | class_no   | birth      |
              +------+----------+------+------------+------------+
              |    1 | zhangsan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 0    | 高三3班     | 1952-12-14 |
              |    4 | laotie   | 0    | 高三3班     | 1955-12-14 |
              |    1 | zhangsan | 0    | 高三3班     | 1950-10-12 |
              |    1 | jack     | 0    | 高三3班     | 1986-10-23 |
              |    3 | rose     | 0    | 高三3班     | 1952-12-14 |
              |    4 | laotie   | 0    | 高三3班     | 1955-12-14 |
              +------+----------+------+------------+------------+

  8、删除数据
        语法格式：delete from 表名 where 条件;
        注意：如果没有where条件，默认是全部删除

           案例：删除学号1数据
           DELETE FROM t_student1 WHERE no = 1;
           +------+--------+------+------------+------------+
           | no   | name   | sex  | class_no   | birth      |
           +------+--------+------+------------+------------+
           |    3 | rose   | 0    | 高三3班     | 1952-12-14 |
           |    4 | laotie | 0    | 高三3班     | 1955-12-14 |
           |    3 | rose   | 0    | 高三3班     | 1952-12-14 |
           |    4 | laotie | 0    | 高三3班     | 1955-12-14 |
           +------+--------+------+------------+------------+

           删除表所有记录
           delete from t_student1;  // 删除大表比较慢，可回滚，能找回

           删除表所有数据
           truncate table 表名；     // 表数据被截断，不可回滚，永久丢失。

           删除表数据跟结构
           drop table 表名;         // 是把数据库中指定的表数据和表结构同时删掉，不可回滚，永久丢失。

      增删改查有一个术语：CRUD操作。。。（以后就别说增删改查了，太low了）
      Create(增)    Retrieve(检索)    Update(修改)     Delete(删除)
 */
public class I9表的创建 {
}
