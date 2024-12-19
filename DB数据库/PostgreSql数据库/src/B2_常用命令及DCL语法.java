/**
 * @author 游家纨绔
 * @dateTime 2024-12-15 11:45
 * @apiNote TODO
 */
public class B2_常用命令及DCL语法 {}
/*
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
       ①、连接指定服务器上的数据库
           psql [-h IP] [-p 端口] [-U 用户名] [-d 数据库名] -W;
           例如：psql -h localhost -p 5432 -U root -d smartmatch_ece -W;
                -h # 数据库所在的IP地址
                -p #（默认5432）数据库的监听端口
                -U # 用户名
                -d # 数据库名称
       ②、使用操作系统的默认配置
           psql [数据库名];
           例如：psql smartmatch_ece;

    2、创建账号(角色或者用户)：
        # 在Postgresql中 USER(用户) 与 ROLE(角色) 没有太大的区别。
        # 不同的是 CREATE USER 定义的用户默认就有 'LOGIN' 权限，而 CREATE ROLE 默认没有 'LOGIN' 权限（默认没有，但是可以自定义加上）
        # 'LOGIN' 权限：表示的是登录数据库的权限。
        ①、创建角色：create role [角色名] with login password '密码'; (这里的角色加上了'LOGIN'权限)
        ②、创建用户：create user [用户名] password '密码';

    3、创建数据库
        ①、创建数据库（默认所有者）
            CREATE DATABASE [数据库名];
        ②、这里 OWNER 指定新数据库的所有者，ENCODING 设置字符编码，LC_COLLATE 和 LC_CTYPE 设置排序规则，而 TEMPLATE 指定了用于初始化新数据库的模板。
            CREATE DATABASE [数据库名]
                   WITH OWNER = your_username ENCODING = 'UTF8'
                        LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8'
                        TEMPLATE template0;
            注意：PostgreSQL 的默认模板数据库为 template1。
                 当使用 CREATE DATABASE 命令但没有指定模板时，默认情况下会从 template1 复制所有内容来创建新数据库。
                 template1：包含了一些预定义的对象（如系统表、视图、函数等）和可能已经被安装到系统的扩展模块或自定义设置。
                            这意味着任何对 template1 的修改都会影响到从此模板创建的所有新数据库。
                            用户可以连接并修改 template1，但这通常不被推荐，因为这会影响到所有将来从中创建的新数据库。
                 template0：是一个几乎完全空的模板数据库，它只包含了启动一个新的数据库实例所必需的最小集合的对象。
                            这意味着它没有任何额外的用户数据或配置，提供了一个非常干净的基础。

    4、权限
       Ⅰ、授予权限：
          ①、授予(角色或者用户)创建数据库权限：
              授予角色 CREATEDB(创建数据库) 权限：alter role [角色名] createdb;   撤销权限：alter role [角色名] nocreatedb;
              授予用户 CREATEDB(创建数据库) 权限：alter user [用户名] createdb;   撤销权限：alter user [用户名] nocreatedb;
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

    5、删除账号(用户)：
       ①、if exists选项：可写可不写
           drop user [if exists] [角色名];
       ②、cascade选项：删除一个角色及其拥有的数据库对象（例如表、视图等），并且还撤销它授予其他角色的权限。级联删除所有相关的依赖对象和权限
           drop role [if exists] [角色名] cascade;

    6、删除账号(角色)：
       drop role [IF EXISTS] [角色名];


三、模式 Schema
    1、PostgreSQL 模式SCHEMA 可以看着是一个表的集合。一个模式可以包含视图、索引、数据类型、函数和操作符等。
       相同的对象名称可以被用于不同的模式中而不会出现冲突，例如 schema1 和 myschema 都可以包含名为 mytable 的表。

       使用模式的优势：
          ①、允许多个用户使用一个数据库并且不会互相干扰。
          ②、将数据库对象组织成逻辑组以便更容易管理。
          ③、第三方应用的对象可以放在独立的模式中，这样它们就不会与其他对象的名称发生冲突。

    2、创建 schema语法格式如下：以下【[if not exists]】语法非必写
       ①、创建 schema：
           create schema [if not exists] [模式名];
       ②、创建一个schema模式，并将其所有权赋予用户：
           create schema [if not exists] [模式名] authorization [用户或角色];
       ③、创建一个schema模式，并自动将其所有权赋予当前执行该命令的用户：
           create schema [if not exists] [模式名] authorization current_user;
       ④、创建一个 schema名 为当前用户名的模式，并将其所有权赋予当前所在角色：
           create schema [if not exists] authorization current_user;
       ⑤、创建一个 schema名 为当前用户名的模式，并将其所有权赋予当前所在角色：
           create schema [if not exists] authorization current_user;

    3、删除数据库
        drop database 数据库名;
       关闭数据库所有会话
        SELECT pg_terminate_backend(pid)
        FROM pg_stat_activity
        WHERE datname='mydb' AND pid<>pg_backend_pid();

四、PostgreSql的标识符区分大小写
    1、在PostgreSql中，执行SQL语句时，会自动把所有表示关键字，库名，表名，列名的标识符转换成小写。
       而给PostgreSQL中表名、列名或其他标识符加上双引号（"）是为了支持大小写敏感的标识符。
       这是PostgreSQL中的一个特性，允许你使用保留关键字作为标识符，并且使标识符成为大小写敏感。
       如果有需要保持标识符的大写状态，只需要在Sql语句中加上双引号("")标记出来

    2、标识符不带双引号("")，PostgreSQL会将标识符转换为小写，并且默认为大小写不敏感。
       CREATE TABLE USER (                                     CREATE TABLE "user" (
           Id bigserial PRIMARY KEY,                               "id" int8 NOT NULL DEFAULT nextval('"smc_ece".user_id_seq'::regclass),
           userName varchar(255)       ==== 实际执行效果 ====》      "username" varchar(255)
       );                                                          CONSTRAINT "ece_user_pkey" PRIMARY KEY ("id")
                                                               );

    3、标识符带双引号("")，PostgreSQL会将其视为大小写敏感的标识符。例如，"User" 和 "user" 将被视为不同的标识符。
       CREATE TABLE "User" (                                   CREATE TABLE "User" (
           "Id" bigserial PRIMARY KEY,                            "Id" int8 NOT NULL DEFAULT nextval('"smc_ece".User_id_seq'::regclass),
           "userName" varchar(255)     ==== 实际执行效果 ====》     "userName" varchar(255),
       );                                                         CONSTRAINT "ece_user_pkey" PRIMARY KEY ("id")
                                                               );

    4、根据上述标识符带双引号("")，那么对应的 DML、DQL 语句也需要加上双引号("")，且区分大小写，否则会报错。
       -- 插入数据：INSERT INTO "User" ("Id","userName") VALUES(1, 'Alice');
       -- 查询数据：SELECT "Id","userName" FROM "User" WHERE "Id" = 1;

    总结：在PostgreSQL中，标识符区分大小写，如果不使用双引号，PostgreSQL会将标识符转换为小写，并且默认为大小写不敏感。
         如果需要保持标识符的大写状态，只需要在Sql语句中加上双引号("")标记出来。
         在实际应用中，建议保持标识符的一致性，避免不必要的复杂度。如果你不需要大小写敏感的标识符，通常不使用双引号会更加简单明了。
 */
