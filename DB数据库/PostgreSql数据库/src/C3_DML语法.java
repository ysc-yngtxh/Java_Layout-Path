/**
 * @author 游家纨绔
 * @dateTime 2024-12-16 20:43
 * @apiNote TODO
 */
public class C3_DML语法 {}
/*
一、创建表格式
    CREATE TABLE public.t_user (
      "id" BIGSERIAL NOT NULL,  # 这里的 serial 是自增序列，bigserial 是大整数类型
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

二、修改表格式
    -- 创建自增序列
       alter sequence "t_user_id_seq" restart with 1 increment by 1;
    -- 创建主键序列
       drop index if exists "t_user_pkey";
       alter table "t_user" add constraint "t_user_pkey" primary key ("id");
    -- 根据已有表结构创建表
       create table if not exists [新表名] (
           like [旧表名] including indexes including comments including defaults
       );
    -- 删除表
       drop table if exists [表名] cascade;


    -- 查询所有数据库
       select datname from pg_database;
    -- 查看数据库下的所有（schema）
       select * from information_schema.schemata;
    -- 查询schema中所有表
       select table_name from information_schema.tables where table_schema = 'myuser';


    -- 查询注释
       SELECT
         a.attname as "字段名",
         col_description(a.attrelid, a.attnum) as "注释",
         concat_ws('', t.typname, SUBSTRING(format_type(a.atttypid,a.atttypmod) from '(.*)')) as "字段类型"
       FROM
         pg_class as c,
         pg_attribute as a,
         pg_type as t
       WHERE
         c.relname = 't_batch_task'
         and a.atttypid = t.oid
         and a.attrelid = c.oid
         and a.attnum > 0;


    -- 创建索引
       drop index if exists t_user_username;
       create index t_user_username on t_user (username);
    -- 创建唯一索引
       drop index if exists t_user_username;
       create index t_user_username on t_user (username);
    -- 查看索引
       \d t_user


三、函数方法
    -- to_timestamp() 字符串转时间
       select * from t_user
       where create_time >= to_timestamp('2023-01-01 00:00:00', 'yyyy-mm-dd hh24:MI:SS');
    -- to_char 时间转字符串
       select to_char(create_time, 'yyyy-mm-dd hh24:MI:SS') from t_user;

    -- 当前时间加一天
       SELECT NOW()::TIMESTAMP + '1 day';
       SELECT NOW() + INTERVAL '1 DAY';
       SELECT now()::timestamp + ('1' || ' day')::interval
    -- 当前时间减一天
       SELECT NOW()::TIMESTAMP + '-1 day';
       SELECT NOW() - INTERVAL '1 DAY';
       SELECT now()::timestamp - ('1' || ' day')::interval
    -- 加1年1月1天1时1分1秒
       SELECT NOW()::timestamp + '1 year 1 month 1 day 1 hour 1 min 1 sec';
    -- 从第一个位置开始截取，截取4个字符。返回结果：Post
       SELECT SUBSTRING ('PostgreSQL', 1, 4);
    -- 从第8个位置开始截取，截取到最后一个字符。返回结果：SQL
       SELECT SUBSTRING ('PostgreSQL', 8);
    -- 正则表达式截取，截取 'gre' 字符串
       SELECT SUBSTRING ('PostgreSQL', 'gre');

四、JDBC 连接串常用参数
    1、单机 PostgreSQL 连接串
       url: jdbc:postgresql://10.20.1.231:5432/postgres?
            binaryTransfer=false&forceBinary=false&reWriteBatchedInserts=true

            binaryTransfer=false：控制是否使用二进制协议传输数据，false 表示不适用，默认为 true
            forceBinary=false：控制是否将非 ASCII 字符串强制转换为二进制格式，false 表示不强制转换，默认为 true
            reWriteBatchedInserts=true：控制是否将批量插入语句转换成更高效的形式，true 表示转换，默认为 false
                                        例如：insert into foo (col1, col2, col3) values(1,2,3);
                                             insert into foo (col1, col2, col3) values(4,5,6);
                                        会转换成：insert into foo (col1, col2, col3) values(1,2,3), (4,5,6);
                                        如果使用正确，reWriteBatchedInserts 会提升批量 insert 性能 2-3 倍。

   2、集群PostgreSQL 连接串
      url: jdbc:postgresql://10.20.1.231:5432/postgres?
           binaryTransfer=false&forceBinary=false&reWriteBatchedInserts=true&
           targetServerType=master&loadBalanceHosts=true

           targetServerType=master：只允许连接到具有所需状态的服务器，可选值有：
                                    any：默认，表示连接到任何一个可用的数据库服务器，不区分主从数据库；
                                    master：表示连接到主数据库，可读写；
                                    slave：表示连接到从数据库，可读，不可写；
                                    其他不常用值：primary, master, slave, secondary, preferSlave, preferSecondary and preferPrimary。
           loadBalanceHosts=true：控制是否启用主从模式下的负载均衡，true表示启用，开启后依序选择一个 ip1:port 进行连接，默认为 false。

 */
