/**
 * @author 游家纨绔
 */
/*
1、一门标准通用语言。标准的SQL、DB、DBMS分别代表的是什么，它们之间的关系？
     DB：
        DataBase (数据库，数据库实际上在硬盘上以文件的形式存在)
     DBMS：
        DataBase Management System (数据库管理系统，常见的有：MySQL Oracle DB2 Sybase SqlServer)
     SQL：
        结构化查询语言，是一QL适合于所有的数据库产品。
        SQL属于高级语言。只要能看懂英语单词，写出来的SQL语句，可以读懂什么意思。
        SQL语句在执行的时候，实际上内部也会先进行编译，然后再执行SQL。(SQL语句的编译由DBMS完成)

     DBMS负责执行SQL语句，通过执行SQL语句来操作DB当中的数据
     DBMS(执行) -> SQL(操作) -> DB
---------------------------------------------------------------------------------------------------------------
2、学习MySQL主要还是学习通用的SQL语句，那么SQL语句包括增删改查，SQL语句怎么分类呢？
     DQL(数据查询语言): 查询语句，凡是select语句都是DQL
     DML(数据操作语言): insert delete update,对表单中的数据进行增删改
     DDL(数据定义语言): create drop alter,对表的结构进行增删改
     TCL(事务控制语言): commit 提交事务,rollback回滚事务。(TCL中的T是Transaction)
     DCL(数据控制语言): grant授权, revoke撤销权限等
---------------------------------------------------------------------------------------------------------------
3、Mysql数据库常见命令
     第一步 -- 登录MySQL管理系统
              mysql -u root -p;                (root为账号)
     第二步 -- 查看有哪些数据库
              mysql> show databases;           (这个不是SQL语句，属于MySQL命令)
                     +--------------------+
                     | Database           |
                     +--------------------+
                     | information_schema |
                     | mysql              |
                     | performance_schema |
                     | sys                |
                     +--------------------+
      第三步 -- 创建属于我们自己的数据库
               mysql> create database 数据库名; (这个不是SQL语句，属于MySQL命令)
      第四步 -- 使用指定数据库名
               mysql> use 数据库名;             (这个不是SQL语句，属于MySQL命令)
      第五步 -- 查看当前使用的数据库中有哪些表
               mysql> show tables;             (这个不是SQL语句，属于MySQL命令)
                      +---------------------+
                      | Tables_in_employees |
                      +---------------------+
                      | departments         |
                      | dept_emp            |
                      | dept_manager        |
                      | employees           |
                      | salaries            |
                      | titles              |
                      +---------------------+
      第六步 -- 初始化数据(导入sql脚本)
               mysql> source D:\\......\\employees.sql
                      ①、什么是SQL脚本呢？
                            当一个文件的扩展名是.sql, 并且该文件中编写了大量的SQL语句，我们称这样的文件为SQL脚本
                            注意：直接只用source命令可以执行SQL脚本。
                      ②、SQL脚本中的数据量太大无法打开的时候，应使用source命令完成初始化
      第七步 -- 查看有哪些表:  mysql> show tables;
      第八步 -- 查看某一个(student)表的具体内容结构：desc student;
                      +-----------+-------------+------+-----+---------+-------+
                      | Field     | Type        | Null | Key | Default | Extra |
                      +-----------+-------------+------+-----+---------+-------+
                      | emp_no    | int(11)     | NO   | PRI | NULL    |       |
                      | title     | varchar(50) | NO   | PRI | NULL    |       |
                      | from_date | date        | NO   | PRI | NULL    |       |
                      | to_date   | date        | YES  |     | NULL    |       |
                      +-----------+-------------+------+-----+---------+-------+
      第九步 -- 删除数据库：drop database *****;
---------------------------------------------------------------------------------------------------------------
6、Mysql数据库信息函数
      select user(); -------- 查看当前用户
      select database(); ---- 查看当前使用的是哪个数据库
      select version(); ----- 查看MySQL版本号
      show create table emp;  查看创建表的语句
      \c -------------------- 命令，结束一条语句
      exit ------------------ 退出MySQL
---------------------------------------------------------------------------------------------------------------
7、简单的查询语句
        语法格式：
           SELECT 字段名1, 字段名2, 字段名3, ... FORM 表名;
        提示：
           ①、任何一条SQL语句以";"结尾。
           ②、SQL语句不区分大小写。
        示例：
           (1)、查询员工的年薪: SELECT ename,sal*12 FROM emp;（字段可以参与数学运算）
                        +-----------+-------------+
                        | ename     | sal*12      |
                        +-----------+-------------+
                        | SMITH     |  9600.00    |
                        | ALLEN     | 19200.00    |
                        | WARD      | 15000.00    |
                        | JONES     | 34200.00    |
                        +-----------+-------------+
           (2)、给查询结果的列重命名: SELECT ename,sal*12 AS yearSal FROM emp;
                        +-----------+-------------+
                        | ename     | yearSal     |
                        +-----------+-------------+
                        | SMITH     |  9600.00    |
                        | ALLEN     | 19200.00    |
                        | WARD      | 15000.00    |
                        | JONES     | 34200.00    |
                        +-----------+-------------+
           (3)、别名中命名中文: SELECT ename,sal*12 AS '年薪' FROM emp;
                        +-----------+-------------+
                        | ename     | 年薪         |
                        +-----------+-------------+
                        | SMITH     |  9600.00    |
                        | ALLEN     | 19200.00    | 注意:标准SQL语句中要求字符串使用单引号括起来,虽然MySQL支持双引号,但是不通用
                        | WARD      | 15000.00    |
                        | JONES     | 34200.00    |
                        +-----------+-------------+
           (4)、别名AS关键字可以省略，用空格来代替: SELECT ename,sal*12 '年薪' FROM emp;
                        +-----------+-------------+
                        | ename     | 年薪         |
                        +-----------+-------------+
                        | SMITH     |  9600.00    |
                        | ALLEN     | 19200.00    |
                        | WARD      | 15000.00    |
                        | JONES     | 34200.00    |
                        +-----------+-------------+
           (5)、查询所有字段: SELECT * FROM emp;
               注意：以后再Java开发，写语句在Java里，使用 * 号效率太低。所以只是在MySQL查询才使用 * 号

     delete   用于删除表中的特定行。              delete from 表名 where 条件;
     truncate 用于删除表中的所有行，但不删除表本身。truncate table 表名称;
     drop     用于删除整个表，包括其结构和数据。   drop table 表名称;
 */
public class A1_入门知识 {}
