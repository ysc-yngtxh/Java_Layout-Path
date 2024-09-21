/**
 * @author 游家纨绔
 */
/*
约束
  1、什么是约束？
         在创建表的时候，可以给表的字段添加相应的约束，添加约束的目的是为了保证表中数据的合法性、有效性、完整性。
---------------------------------------------------------------------------------------------------------------
  2、常见的约束有哪些？
         非空约束(not null)：   约束的字段不能为null
         唯一约束(unique)：     约束的字段不能重复
         主键约束(primary key)：约束的字段既不能为null，也不能重复（简称PK）
         外键约束(foreign key)：不能重复（简称FK）
         检查约束(check)：      注意：Oracle数据库有check约束，MySQL8.0.16 版本后才引入 check约束
---------------------------------------------------------------------------------------------------------------
  3、非空约束 (not null)
         drop table if exists t_user;
         create table t_user(
            `id` int,
            `username` varchar(255) not null,
            `password` varchar(255)
         );
         insert into t_user(id, password) values(1, '123');
         // 报错：这样写默认情况下username是null，但是因为加了not null语句，所以报错
         // ERROR 1364 (HY000): Field 'username' doesn't have a default value
         insert into t_user(is, username, password) values(1, 'lisi', '123');
---------------------------------------------------------------------------------------------------------------
  4、唯一约束 (unique)
         唯一约束修饰的字段具有唯一性，不能重复，但可以为null
         案例一：
               drop table if exists t_user;
               create table t_user(
                  id int,
                  username varchar(255) unique
               );
               insert into t_user(id, username) values(1, 'lisi');
               insert into t_user(id, username) values(2, 'lisi');  // 报错，username具有唯一性
         案例二：
               drop table if exists t_user;
               create table t_user(
                  `id` int,
                  `user_code` varchar(255),
                  `user_name` varchar(255),
                  unique(user_code, user_name)   // 这个语句表示的是(user_code, user_name)联合语句不重复
               );
               insert into t_user(id, user_code, user_name) values(1, '111', 'lisi');
               insert into t_user(id, user_code, user_name) values(2, '111', 'wangWu');
               insert into t_user(id, user_code, user_name) values(3, '222', 'wangWu');
---------------------------------------------------------------------------------------------------------------
  5、主键约束
         [1]、使用字段约束方式定义主键：
              drop table if exists t_user;
              create table t_user(
                 id int primary key,
                 username varchar(255),
                 email varchar(255)
              );
              insert into t_user(id,username,email) values(1,'zs','zs.@123.com');
              insert into t_user(id,username,email) values(2,'ls','ls.@123.com');
              insert into t_user(id,username,email) values(3,'www','www.@123.com');
         [2]、id是主键，因为添加了主键约束，主键字段中的数据不能为null，也不能重复
              主键相关术语：
                        -- 主键约束  primary key
                        -- 主键字段  id字段添加primary key之后，id叫做主键字段
                        -- 主键值    id字段中的每一个值都是主键值
              主键有什么作用？
                        -- 表的设计三范式中有要求，第一范式就要求任何一张表都应该有主键。
                        -- 主键的作用：主键值是这行记录在这张表当中的唯一标识。(就像一个人的身份证号码一样)
                        -- 一张表的主键约束只能有1个
              主键的的分类：
                        -- 单一主键：(推荐的，常用的)
                        -- 复合主键：多个字段联合起来添加一个主键约束（复合主键不建议使用，因为复合主键违背三范式）
                        -- 自然主键：主键值最好就是一个和业务没有任何关系的自然数（这种方式是推荐的）
                        -- 业务主键：主键值和系统的业务挂钩，例如：拿着银行卡的卡号做主键，拿着身份证号码作为主键（不推荐用）
                                    最好不要拿着和业务挂钩的字段作为主键。因为以后的业务一旦发生改变的时候，主键值可能也需要随着发生变化，
                                    但有的时候没有办法变化，因为变化可能会导致主键值重复
         [3]、使用表级约束方式定义主键：
              drop table if exists t_user;
              create table t_user(
                 id int,
                 username varchar(255),
                 primary key(id)
              );
              insert into t_user(id, username) values(1, 'zs');
              insert into t_user(id, username) values(2, 'ls');
              insert into t_user(id, username) values(3, 'ww');
         [4]、mysql提供主键值自增
              drop table if exists t_user;
              create table t_user(
                 id int primary key auto_increment, // id字段自动维护一个自增的数字，从1开始，以1递增
                 username varchar(255)
              );
              insert into t_user(username) values('a');
              insert into t_user(username) values('b');
              insert into t_user(username) values('c');
              insert into t_user(username) values('d');
              insert into t_user(username) values('e');
              +----+----------+
              | id | username |
              +----+----------+
              |  1 | a        |
              |  2 | b        |  扩展：Oracle但中也提供了一个自增机制，叫做：序列(sequece)对象。
              |  3 | c        |
              |  4 | d        |
              |  5 | e        |
              +----+----------+
---------------------------------------------------------------------------------------------------------------
  6、外键约束
          [1]、关于外键约束的相关术语：
                 外键约束：foreign key
                 外键字段：添加有外键约束的字段
                 外键值：  外键字段中的每一个值
          [2]、业务背景：请设计数据库表，用来维护学生的班级的信息。
               第①种方案：一张表存储所有数据
                         +------+------+---------+------------------------+
                         | no   | name | class_no| classname              |
                         +------+------+---------+------------------------+   显得很冗余
                         |    1 | zs1  |     101 | 湖北省武汉市江夏区高中1班  |
                         |    2 | zs2  |     102 | 湖北省武汉市江夏区高中2班  |
                         +------+------+---------+------------------------+

               第②种方案：两张表（班级表和学生表）
                                        +----------+------------------------+
                                        | cno(PK)  | cname                  |
                         t_class 班级表  +----------+------------------------+
                                        |  101     | 湖北省武汉市江夏区高中1班  |
                                        |  102     | 湖北省武汉市江夏区高中2班  |
                                        +----------+------------------------+
                                        +---------+-------+---------------------+
                                        | sno(PK) | s_name| cmo(添加约束的外键值)  |
                         t_student学生表 +---------+-------+---------------------+
                                        |   1     | zs1   |  101                |
                                        |   2     | zs2   |  102                |
                                        +---------+-------+---------------------+
                         t_student中的cmo字段引用t_class表中的cno字段。此时t_class表叫做父表，t_student表叫做子表
                         设计操作的顺序要求：
                            添加数据或者表的时候，先添加父表再添加子表，
                            删除数据或者表的时候，先删除子表再删除父表。

                         drop table if exists t_student;
                         drop table if exists t_class;
                         create table t_class(
                            `cno` int primary key,
                            `cname` varchar(255)
                         );
                         create table t_student(
                            `sno` int  primary key auto_increment,
                            `s_name` varchar(255),
                            `cmo` int,
                            constraint `fk_cmo` foreign key(cmo) references t_class(cno) on delete cascade
                            // constraint `fk_cmo`：表示外键约束名
                            // foreign key(cmo) references t_class(cno)：表示外键，`cmo`是外键，引用t_class表中的cno字段
                            // on delete cascade：表示级联删除，当et_class表中的cno字段被删除时，t_student表中的cmo字段也会被删除
                            // cmo 引用 cno，cmo中的数据只能是cno中的数据。且 cno 中的数据只能是唯一的【因此只能是主键或者加了唯一约束】。
                         );
                         insert into t_class values(101, 'x');
                         insert into t_class values(102, 'y');
                         insert into t_student(s_name, cmo) values('zs1', 101);
                         insert into t_student(s_name, cmo) values('zs2', 101);
                         insert into t_student(s_name, cmo) values('zs3', 102);
                         select * from t_class;
                         select * from t_student;

                         外键值可以为null。
                         外键字段引用其他表的某个字段的时候，被引用的字段必须是主键吗？
                         注意：被引用的字段不一定是主键，但至少具有unique约束(唯一约束)。
---------------------------------------------------------------------------------------------------------------
       TODO 阿里巴巴的Java开发手册中有这样一句话：不得使用外键与级联，一切外键概念必须在应用层解决！
        1、什么是级联：当在 t_class 表中要删除或者更新 cmo=101 的相关信息，
                     马上在 t_student 表中中查找是否有 cmo=101 这个记录，然后进行删除或更新，这就叫级联。
        2、在使用外键的情况下,每次修改数据都需要去另外一个表检查数据,需要获取额外的锁。若是在高并发大流量事务场景,使用外键更容易造成死锁
           外键和级联比较适合单机和低并发的情况下 ，大量事务也会影响crud速度，不适合分布式和高并发集群。
---------------------------------------------------------------------------------------------------------------
  7、check约束
     MySQL 对 CHECK 约束的支持是在 MySQL 8.0.16 版本中引入的。在此之前，MySQL 并不完全支持标准 SQL 中定义的 CHECK 约束。
     自 MySQL 8.0.16 版本起，用户可以在创建表或修改表时使用 CHECK 约束来限定列值的有效范围。

     这意味着，如果你使用的是 MySQL 8.0.16 或更高版本，你可以直接利用 CHECK 约束来增强数据完整性检查。
     对于老版本的 MySQL 数据库，你需要升级到至少 8.0.16 版本才能使用这一功能。
     在此之前，通常的做法是通过触发器、存储过程或应用程序逻辑来强制执行这样的约束。

           create table t_product(
               id      bigint    auto_increment primary key comment '评论ID',
               -- CHECK 约束用于确保列中的值满足特定条件。InnoDB 存储引擎中支持 CHECK 约束，但在 MyISAM 存储引擎中则不支持。
               rating  int       check (rating >= 1 AND rating <= 5) comment '评分',
           )


 */
public class J10_约束 {}
