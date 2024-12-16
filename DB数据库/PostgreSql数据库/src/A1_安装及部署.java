/**
 * @author 游家纨绔
 * @dateTime 2024-07-31 22:38
 * @apiNote TODO
 */
public class A1_安装及部署 {}

// TODO Mac环境下安装PostgreSql流程：
//      1、使用 homebrew 下载安装 postgresql@17
//         brew install postgresql@17;
//      2、开启 postgresql@17
//         brew services start postgresql@17;
//      3、配置 postgresql@17 环境变量
//         export PATH=$PATH:/opt/homebrew/Cellar/postgresql@17/17.2/bin
//      4、进入postgresql，postgres是PostgreSql的默认数据库
//         psql postgres;
//      5、创建角色或者用户
//         # 在Postgresql中 USER(用户) 与 ROLE(角色) 没有太大的区别。
//         # 不同的是 CREATE USER 定义的用户默认就有 'LOGIN' 权限，而 CREATE ROLE 默认没有 'LOGIN' 权限（默认没有，但是可以自定义加上）
//         # 'LOGIN' 权限：表示的是登录数据库的权限。
//         创建角色：CREATE ROLE [角色] WITH LOGIN PASSWORD '密码'; (这里的角色加上了'LOGIN'权限)
//         创建用户：CREATE USER [用户] PASSWORD '密码';
//      6、角色或者用户 授予 CREATEDB(创建数据库) 权限
//         授予角色 CREATEDB(创建数据库) 权限：ALTER ROLE [角色] CREATEDB;
//         授予用户 CREATEDB(创建数据库) 权限：ALTER USER [用户] CREATEDB;
//      7、退出命令行
//         \q
//      8、查看所有用户
//         \du
//      9、创建testdb数据库
//         CREATE DATABASE testdb;