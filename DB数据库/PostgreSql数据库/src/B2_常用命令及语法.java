/**
 * @author 游家纨绔
 * @dateTime 2024-12-15 11:45
 * @apiNote TODO
 */
public class B2_常用命令及语法 {}
/* TODO PostgreSQL 常用管理命令

一、常用命令说明
    \?                  # 所有命令帮助
    \l                  # 列出所有数据库
    \d                  # 列出数据库中所有表
    \dt                 # 列出数据库中所有表
    \d [table_name]     # 显示指定表的结构
    \di                 # 列出数据库中所有 index
    \dv                 # 列出数据库中所有 view
    \h                  # sql命令帮助
    \q                  # 退出连接
    \c [database_name]  # 切换到指定的数据库
    \c                  # 显示当前数据库名称和用户
    \conninfo           # 显示客户端的连接信息
    \du                 # 显示所有用户
    \dn                 # 显示数据库中的schema
    \encoding           # 显示字符集
    select version();   # 显示版本信息
    \i testdb.sql       # 执行 testdb.sql 文件
    \x                  # 扩展展示结果信息，相当于MySQL的\G
    \o /tmp/test.txt    # 将下一条sql执行结果导入文件中

二、常用的语法说明
    1、登录命令
       # 连接指定服务器上的数据库
         psql [-h IP] [-p 端口] [-U 用户名] [-d 数据库名] -W;
         例如：psql -h localhost -p 5432 -U root -d smartmatch_ece -W;
              -h # 数据库所在的IP地址
              -p #（默认5432）数据库的监听端口
              -U # 用户名
              -d # 数据库名称

    2、创建账号(角色或者用户)：
        # 在Postgresql中 USER(用户) 与 ROLE(角色) 没有太大的区别。
        # 不同的是 CREATE USER 定义的用户默认就有 'LOGIN' 权限，而 CREATE ROLE 默认没有 'LOGIN' 权限（默认没有，但是可以自定义加上）
        # 'LOGIN' 权限：表示的是登录数据库的权限。
        创建角色：create role [角色名] with login password '密码'; (这里的角色加上了'LOGIN'权限)
        创建用户：create user [用户名] password '密码';

    3、权限
       Ⅰ、授予权限：
          ①、授予(角色或者用户)创建数据库权限：
              授予角色 CREATEDB(创建数据库) 权限：alter role [角色名] createdb;
              授予用户 CREATEDB(创建数据库) 权限：alter user [用户名] createdb;
          ②、设置用户只读权限：
              alter user [用户名] set default_transaction_read_only = on;
          ③、设置指定数据库所有权限（select,insert,update,delete,truncate,references[外键],trigger[触发器]）给指定的用户名：
              grant all on database [数据库名] to [用户或角色];
          ④、授予指定用户或角色对 模式 下当前存在的所有表的 select(读取) 权限：
              grant select on all tables in schema [模式名] to [用户或角色];
              例如：授予指定用户或角色对 public模式 下当前存在的所有表的 select(读取) 权限：
                   grant select on all tables in schema public to [用户或角色];
          ⑤、将所有权限（select,insert,update,delete,truncate,references,trigger）授予 [用户或角色] 对 指定表 的操作权限：
              grant all on table [schema名.table表名] to [用户或角色];
              例如：将所有权限授予 [用户或角色] 对 public.user 表的操作权限：
                   grant all on table public.user to [用户或角色];
          ⑥、将指定权限（SELECT,UPDATE,INSERT,DELETE）授予 [用户或角色] 对 指定表 的操作权限：
              grant select,update,insert,delete on table [schema名.table表名] to [用户或角色];
              例如：将指定权限（SELECT,UPDATE,INSERT,DELETE）授予 [用户或角色] 对 public.user 表的操作权限：
                   grant select,update,insert,delete on table public.user to [用户或角色];
       Ⅱ、撤销权限：
          ①、撤销特定用户对指定数据库的所有操作权限：
              revoke all on database [数据库名] from [用户或角色];
          ②、撤销特定用户对某个模式（schema）中所有表的 SELECT 权限：
              revoke select on all tables in schema public from [用户或角色];
          ③、撤销默认权限，以确保在这个模式中创建的新表也不会自动授予该用户或角色 select 权限
              alter default privileges in schema public
              revoke select on tables from [用户或角色];

    4、删除账号(用户)：
       ①、if exists选项：可写可不写
           drop user [if exists] [角色名];
       ②、cascade选项：删除一个角色及其拥有的数据库对象（例如表、视图等），并且还撤销它授予其他角色的权限。级联删除所有相关的依赖对象和权限
           drop role [if exists] [角色名] cascade;

    5、删除账号(角色)：
       drop role [IF EXISTS] [角色名];


三、模式 Schema
    PostgreSQL 模式SCHEMA 可以看着是一个表的集合。一个模式可以包含视图、索引、数据类型、函数和操作符等。
    相同的对象名称可以被用于不同的模式中而不会出现冲突，例如 schema1 和 myschema 都可以包含名为 mytable 的表。

    使用模式的优势：
       1、允许多个用户使用一个数据库并且不会互相干扰。
       2、将数据库对象组织成逻辑组以便更容易管理。
       3、第三方应用的对象可以放在独立的模式中，这样它们就不会与其他对象的名称发生冲突。

语法
我们可以使用 CREATE SCHEMA 语句来创建模式，语法格式如下：

CREATE SCHEMA myschema.mytable (
...
);
创建和当前用户同名模式（schema）
注意：用户名与 schema 同名，且用户具有访问改 schema 的权限，用户连入数据库时，默认即为当前 schema。

create schema AUTHORIZATION CURRENT_USER;
自定义创建模式（schema）

create schema 模式名称;
注意：如果不创建scheme，并且语句中不写scheme，则默认scheme使用内置的public。

查看数据库下的所有（schema）

select * from information_schema.schemata;
数据库管理
查询所有数据库
select datname from pg_database;
创建数据库
create database 数据库名 owner 所属用户 encoding UTF8;
注意：创建完数据库，需要切换到数据库下，创建和当前用户同名scheme，删除数据库后schema也会一并删除：

-- 重新登陆到新数据库下，执行如下语句
create schema AUTHORIZATION CURRENT_USER;
删除数据库
drop database 数据库名;
注意：删库前需要关闭所有会话，不然会提示：

ERROR:  database "mydb" is being accessed by other users
DETAIL:  There are 8 other sessions using the database.
关闭数据库所有会话
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE datname='mydb' AND pid<>pg_backend_pid();
更多关于大数据 PostgreSQL 系列的学习文章，请参阅：PostgreSQL 数据库，本系列持续更新中。

表管理
建表模板语句
create table "t_user" (
 "id" bigserial not null,
 "username" varchar (64) not null,
 "password" varchar (64) not null,
 "create_time" timestamp not null default current_timestamp,
 "update_time" timestamp not null default current_timestamp,
 constraint t_user_pk primary key (id)
);

comment on column "t_user"."id" is '主键';
comment on column "t_user"."username" is '用户名';
comment on column "t_user"."password" is '密码';
comment on column "t_user"."create_time" is '创建时间';
comment on column "t_user"."update_time" is '更新时间';
查询schema中所有表
select table_name from information_schema.tables where table_schema = 'myuser';
创建表
CREATE TABLE public.t_user (
  "id" BIGSERIAL NOT NULL,
  "username" VARCHAR(64) NOT NULL,
  "password" VARCHAR(64) NOT NULL,
  "create_time" TIMESTAMP(0) default CURRENT_TIMESTAMP not null,
  "update_time" TIMESTAMP(0) default CURRENT_TIMESTAMP not null
);
-- 注释
COMMENT ON TABLE public.t_user IS '用户表';
COMMENT ON COLUMN public.t_user.id IS '主键';
COMMENT ON COLUMN public.t_user.username IS '用户名';
COMMENT ON COLUMN public.t_user.password IS '密码';
COMMENT ON COLUMN public.t_user.create_time IS '创建时间';
COMMENT ON COLUMN public.t_user.update_time IS '更新时间';
-- 创建自增序列
alter sequence "t_user_ID_seq" restart with 1 increment by 1;
-- 创建主键序列
drop index if exists "t_user_pkey";
alter table "t_user" add constraint "t_user_pkey" primary key ("ID");
根据已有表结构创建表
create table if not exists 新表 (like 旧表 including indexes including comments including defaults);
删除表
drop table if exists "t_template" cascade;
查询注释
SELECT
a.attname as "字段名",
col_description(a.attrelid,a.attnum) as "注释",
concat_ws('',t.typname,SUBSTRING(format_type(a.atttypid,a.atttypmod) from '(.*)')) as "字段类型"
FROM
pg_class as c,
pg_attribute as a,
pg_type as t
WHERE
c.relname = 't_batch_task'
and a.atttypid = t.oid
and a.attrelid = c.oid
and a.attnum>0;
索引管理
创建索引
drop index if exists t_user_username;
create index t_user_username on t_user (username);
创建唯一索引
drop index if exists t_user_username;
create index t_user_username on t_user (username);
查看索引
\d t_user
查询SQL
注意：PostgreSQL中的字段大小写敏感，而且只认小写字母，查询时需注意。其他与基本sql大致相同。

to_timestamp() 字符串转时间
select * from t_user
where create_time >= to_timestamp('2023-01-01 00:00:00', 'yyyy-mm-dd hh24:MI:SS');
to_char 时间转字符串
select to_char(create_time, 'yyyy-mm-dd hh24:MI:SS') from t_user;
时间加减
-- 当前时间加一天
SELECT NOW()::TIMESTAMP + '1 day';
SELECT NOW() + INTERVAL '1 DAY';
SELECT now()::timestamp + ('1' || ' day')::interval
-- 当前时间减一天
SELECT NOW()::TIMESTAMP + '-1 day';
SELECT NOW() - INTERVAL '1 DAY';
SELECT now()::timestamp - ('1' || ' day')::interval
-- 加1年1月1天1时1分1秒
select NOW()::timestamp + '1 year 1 month 1 day 1 hour 1 min 1 sec';
like 模糊查询
SELECT * FROM 表名 WHERE 字段 LIKE ('%关键字%');
substring字符串截取
--从第一个位置开始截取，截取4个字符,返回结果:Post
SELECT SUBSTRING ('PostgreSQL', 1, 4);
-- 从第8个位置开始截取，截取到最后一个字符，返回结果:SQL
SELECT SUBSTRING ('PostgreSQL', 8);
--正则表达式截取，截取'gre'字符串
SELECT SUBSTRING ('PostgreSQL', 'gre');
更多关于大数据 PostgreSQL 系列的学习文章，请参阅：PostgreSQL 数据库，本系列持续更新中。

执行sql脚本
方式一：先登录再执行

\i testdb.sql
方式二：通过psql执行

psql -d testdb -U postgres -f /pathA/xxx.sql
导出数据到SQL文件
pg_dump -h localhost -p 5432 -U postgres --column-inserts -t table_name -f save_sql.sql database_name
--column-inserts #以带有列名的 `INSERT` 命令形式转储数据。
-t #只转储指定名称的表。
-f #指定输出文件或目录名。
JDBC 连接串常用参数
PostgreSQL JDBC 官方驱动下载地址：https://jdbc.postgresql.org/download/
PostgreSQL JDBC 官方参数说明文档：https://jdbc.postgresql.org/documentation/use/
驱动类：driver-class-name=org.postgresql.Driver
单机 PostgreSQL 连接串
url: jdbc:postgresql://10.20.1.231:5432/postgres?
binaryTransfer=false&forceBinary=false&reWriteBatchedInserts=true
binaryTransfer=false：控制是否使用二进制协议传输数据，false 表示不适用，默认为 true
forceBinary=false：控制是否将非 ASCII 字符串强制转换为二进制格式，false 表示不强制转换，默认为 true
reWriteBatchedInserts=true：控制是否将批量插入语句转换成更高效的形式，true 表示转换，默认为 false
例如：

insert into foo (col1, col2, col3) values(1,2,3);
insert into foo (col1, col2, col3) values(4,5,6);
会转换成：

insert into foo (col1, col2, col3) values(1,2,3), (4,5,6);
如果使用正确，reWriteBatchedInserts 会提升批量 insert 性能 2-3 倍。

集群PostgreSQL 连接串
集群PostgreSQL，连接串如下：

url: jdbc:postgresql://10.20.1.231:5432/postgres?
binaryTransfer=false&forceBinary=false&reWriteBatchedInserts=true&targetServerType=master&loadBalanceHosts=true
单机 PostgreSQL 连接串的所有参数。
targetServerType=master：只允许连接到具有所需状态的服务器，可选值有：
any：默认，表示连接到任何一个可用的数据库服务器，不区分主从数据库；
master：表示连接到主数据库，可读写；
slave：表示连接到从数据库，可读，不可写；
其他不常用值：primary, master, slave, secondary, preferSlave, preferSecondary and preferPrimary。
loadBalanceHosts=true：控制是否启用主从模式下的负载均衡，true 表示启用，开启后依序选择一个 ip1:port 进行连接，默认为 false。


四、PostgreSql的标识符区分大小写
    在PostgreSql中，执行SQL语句时，会把所有表示关键字，库名，表名，列名的标识符转换成小写。
    在PostgreSQL中，给表名、列名或其他标识符加上双引号（"）是为了支持大小写敏感的标识符。
    这是PostgreSQL中的一个特性，允许你使用保留关键字作为标识符，并且使标识符成为大小写敏感。
    如果有需要保持标识符的大写状态，只需要在Sql语句中加上双引号("")标记出来

    标识符不带双引号("")，PostgreSQL会将标识符转换为小写，并且默认为大小写不敏感。
    CREATE TABLE USER (                                       CREATE TABLE "user" (
        Id bigserial PRIMARY KEY, # bigserial属性表示使用序列       "id" int8 NOT NULL DEFAULT nextval('"smc_ece".user_id_seq'::regclass),
        userName varchar(255)         ==== 实际执行效果 ====》      "username" varchar(255)
    );                                                            CONSTRAINT "ece_user_pkey" PRIMARY KEY ("id")
                                                              );

    标识符带双引号("")，PostgreSQL会将其视为大小写敏感的标识符。例如，"User" 和 "user" 将被视为不同的标识符。
    CREATE TABLE "User" (                                     CREATE TABLE "User" (
        "Id" bigserial PRIMARY KEY, # bigserial属性表示使用序列     "Id" int8 NOT NULL DEFAULT nextval('"smc_ece".User_id_seq'::regclass),
        "userName" varchar(255)        ==== 实际执行效果 ====》     "userName" varchar(255),
    );                                                            CONSTRAINT "ece_user_pkey" PRIMARY KEY ("id")
                                                              );

    根据上述标识符带双引号("")，那么对应的 DML、DQL 语句也需要加上双引号("")，且区分大小写，否则会报错。
    -- 插入数据：INSERT INTO "User" ("Id","userName") VALUES(1, 'Alice');
    -- 查询数据：SELECT "Id","userName" FROM "User" WHERE "Id" = 1;

    总结：在PostgreSQL中，标识符区分大小写，如果不使用双引号，PostgreSQL会将标识符转换为小写，并且默认为大小写不敏感。
         如果需要保持标识符的大写状态，只需要在Sql语句中加上双引号("")标记出来。
         在实际应用中，建议保持标识符的一致性，避免不必要的复杂度。如果你不需要大小写敏感的标识符，通常不使用双引号会更加简单明了。



 */
