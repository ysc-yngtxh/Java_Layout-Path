/**
 * @author 游家纨绔
 * @dateTime 2024-07-31 22:38
 * @apiNote TODO
 */

// TODO Mac环境下安装PostgreSql流程：
//      1、使用 homebrew 下载安装 postgresql@17
//          brew install postgresql@17;
//      2、开启 postgresql@17
//          brew services start postgresql@17;
//      3、配置 postgresql@17 环境变量
//          export PATH=/opt/homebrew/Cellar/postgresql@17/17.2/bin:$PATH
//      4、进入postgresql，postgres是PostgreSql的默认数据库
//          ①、查看安装版本
//              psql -version;
//          ②、进入postgresql
//              psql postgres;
//      5、创建角色或者用户
//          ①、在Postgresql中 USER(用户) 与 ROLE(角色) 没有太大的区别。
//          ②、不同的是 CREATE USER 定义的用户默认就有 'LOGIN' 权限，
//              而 CREATE ROLE 默认没有 'LOGIN' 权限（默认没有，但是可以执行另外语句添加）
//          ③、'LOGIN' 权限：表示的是登录数据库的权限。
//              创建角色：CREATE ROLE [角色] WITH LOGIN PASSWORD '密码'; (这里的角色加上了'LOGIN'权限)
//                      如果一开始没有加上'LOGIN'权限，可另外执行语句添加'LOGIN'权限：ALTER ROLE [角色] LOGIN;
//              创建用户：CREATE USER [用户] PASSWORD '密码';
//          ④、使用 Postgres 命令行创建 root 用户：create user root password '123456';
//      6、退出命令行
//          \q
//      7、明确指定以用户名 root 进行连接的数据库主机
//              psql -h localhost -p 5432 -U root -d postgres -W;
//      6、角色或者用户 授予 CREATEDB(创建数据库) 权限
//          授予角色 CREATEDB(创建数据库) 权限：ALTER ROLE [角色] CREATEDB;
//          授予用户 CREATEDB(创建数据库) 权限：ALTER USER [用户] CREATEDB;
//      7、退出命令行
//          \q
//      8、查看所有用户
//          \du
//      9、创建数据库
//          # 创建数据库（默认所有者）
//          CREATE DATABASE testdb;
//          # 这里 OWNER 指定新数据库的所有者，ENCODING 设置字符编码，LC_COLLATE 和 LC_CTYPE 设置排序规则，而 TEMPLATE 指定了用于初始化新数据库的模板。
//          CREATE DATABASE testdb
//                 WITH OWNER = your_username ENCODING = 'UTF8'
//                      LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8'
//                      TEMPLATE template0;
public class A1_安装及部署 {}
