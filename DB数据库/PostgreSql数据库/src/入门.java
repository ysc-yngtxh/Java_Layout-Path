/**
 * @author 游家纨绔
 * @dateTime 2024-07-31 22:38
 * @apiNote TODO
 */
public class 入门 {
    // brew install postgresql;
    // brew services start postgresql@16;
    // export PATH=$PATH:/opt/homebrew/Cellar/postgresql@16/16.3/bin
    // psql postgres;
    // CREATE ROLE newUser WITH LOGIN PASSWORD 'password';
    // ALTER ROLE newUser CREATEDB;  角色 newUser 授予 CREATEDB 权限，使其能够创建数据库
    // \q  退出命令行
    // \du 查看所有用户
    // CREATE DATABASE testdb;  创建数据库
}
/* PostgreSQL 常用管理命令

#登录命令
# 连接指定服务器上的数据库
psql [-h IP] [-p 端口] [-U 用户名] [-d 数据库名] -W;
例如：psql -h localhost -p 5432 -U root -d smartmatch_ece -W;
-h # 数据库所在的IP地址
-p #（默认5432）数据库的监听端口
-U # 用户名
-d # 数据库名称

常用命令说明
\? #所有命令帮助
\l #列出所有数据库
\d #列出数据库中所有表
\dt #列出数据库中所有表
\d [table_name] #显示指定表的结构
\di #列出数据库中所有 index
\dv #列出数据库中所有 view
\h #sql命令帮助
\q #退出连接
\c [database_name] #切换到指定的数据库
\c #显示当前数据库名称和用户
\conninfo #显示客户端的连接信息
\du #显示所有用户
\dn #显示数据库中的schema
\encoding #显示字符集
select version(); #显示版本信息
\i testdb.sql #执行sql文件
\x #扩展展示结果信息，相当于MySQL的\G
\o /tmp/test.txt #将下一条sql执行结果导入文件中

# 创建账号
create user 用户名 password '密码';

#设置只读权限
alter user 用户名 set default_transaction_read_only = on;

#设置可操作的数据库
grant all on database 数据库名 to 用户名;

#授权可操作的模式和权限
-- 授权
grant select on all tables in schema public to 用户名;

-- 授权
GRANT ALL ON TABLE public.user TO mydata;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE public.user TO mydata_dml;
GRANT SELECT ON TABLE public.user TO mydata_qry;
删除账号
#撤回在public模式下的权限
revoke select on all tables in schema public from 用户名;

#撤回在information_schema模式下的权限
revoke select on all tables in schema information_schema from 用户名;

#撤回在pg_catalog模式下的权限
revoke select on all tables in schema pg_catalog from 用户名;

#撤回对数据库的操作权限
revoke all on database 数据库名 from 用户名;

#删除用户
drop user 用户名;


权限管理
授权
#设置只读权限
alter user 用户名 set default_transaction_read_only = on;
#设置可操作的数据库
grant all on database 数据库名 to 用户名;
#设置可操作的模式和权限
grant select,insert,update,delete on all tables in schema public to 用户名;

撤回权限
#撤回在public模式下的权限
revoke select on all tables in schema public from 用户名;
#撤回在information_schema模式下的权限
revoke select on all tables in schema information_schema from 用户名;
#撤回在pg_catalog模式下的权限
revoke select on all tables in schema pg_catalog from 用户名;
#撤回对数据库的操作权限
revoke all on database 数据库名 from 用户名;


模式 Schema
PostgreSQL 模式SCHEMA 可以看着是一个表的集合。一个模式可以包含视图、索引、数据类型、函数和操作符等。
相同的对象名称可以被用于不同的模式中而不会出现冲突，例如 schema1 和 myschema 都可以包含名为 mytable 的表。

使用模式的优势：
允许多个用户使用一个数据库并且不会互相干扰。
将数据库对象组织成逻辑组以便更容易管理。
第三方应用的对象可以放在独立的模式中，这样它们就不会与其他对象的名称发生冲突。


·



在PostgreSQL中，给表名、列名或其他标识符加上双引号（"）是为了支持大小写敏感的标识符。这是PostgreSQL中的一个特性，允许你使用保留关键字作为标识符，并且使标识符成为大小写敏感。

双引号的作用
大小写敏感：
当你使用双引号包围标识符时，PostgreSQL会将其视为大小写敏感的标识符。例如，"User" 和 "user" 将被视为不同的标识符。
保留关键字：
如果你想使用PostgreSQL中的保留关键字作为表名、列名或其他标识符，必须使用双引号包围它们。例如，SELECT 是一个保留关键字，如果你想将其用作列名，可以写成 "SELECT"。
示例
假设你有一个表名为 User，并且希望它是大小写敏感的，可以这样创建表：

sql

CREATE TABLE "User" (
    "id" int4 NOT NULL,
    "name" varchar(255),
    PRIMARY KEY ("id")
);
在这个例子中，"User" 和 "id" 都是大小写敏感的标识符。如果你在查询时写成 "USER" 或 "Id"，PostgreSQL将无法找到对应的表或列。

不使用双引号的情况
如果不使用双引号，PostgreSQL会将标识符转换为小写，并且默认为大小写不敏感。例如：

sql

CREATE TABLE user (
    id int4 NOT NULL,
    name varchar(255),
    PRIMARY KEY (id)
);
在这个例子中，user 和 id 都是大小写不敏感的标识符。无论你在查询时如何书写它们（例如 USER 或 ID），PostgreSQL都会将其转换为小写，并查找对应的表或列。

使用双引号的注意事项
大小写一致性：
使用双引号时，务必保持大小写一致性。例如，如果创建表时使用了 "User"，那么在后续的查询中也必须使用 "User"，而不是 "user" 或 "USER"。
保留关键字：
使用保留关键字作为标识符时，务必使用双引号包围它们。否则，PostgreSQL将抛出语法错误。
示例查询
假设你有一个名为 User 的表，并且希望它是大小写敏感的：

sql

-- 插入数据
INSERT INTO "User" ("id", "name") VALUES (1, 'Alice');

-- 查询数据
SELECT "id", "name" FROM "User" WHERE "id" = 1;
总结
使用双引号包围表名、列名或其他标识符的主要目的是为了支持大小写敏感的标识符，并且允许使用保留关键字作为标识符。如果你不需要这些功能，可以不使用双引号，这样标识符将默认为大小写不敏感。

在实际应用中，建议保持标识符的一致性，避免不必要的复杂度。如果你不需要大小写敏感的标识符，通常不使用双引号会更加简单明了。


 */
