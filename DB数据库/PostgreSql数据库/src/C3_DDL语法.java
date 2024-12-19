/**
 * @author 游家纨绔
 * @dateTime 2024-12-16 20:43
 * @apiNote TODO
 */
public class C3_DDL语法 {}
/*
一、创建表格式
    CREATE TABLE public.t_user (
      "id" BIGSERIAL NOT NULL,  # 这里的 serial 是自增序列，bigserial 是大整数类型的自增序列
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

二、常用的数据类型
    ①、数值类型
        名字	               存储长度	          描述	                                范围
        ----------------------------------------------------------------------------------------------------
        smallint	       2 字节	           小范围整数	                      -32768 到 +32767
        integer	           4 字节            常用的整数	                -2147483648 到 +2147483647
        bigint	           8 字节            大范围整数	        -9223372036854775808 到 +9223372036854775807
        decimal	           可变长            用户指定的精度，精确	    小数点前 131072 位；小数点后 16383 位
        numeric	           可变长            用户指定的精度，精确	    小数点前 131072 位；小数点后 16383 位
        real	           4 字节            可变精度，不精确	                 6 位十进制数字精度
        double precision   8 字节            可变精度，不精确	                 15 位十进制数字精度
        smallserial	       2 字节            自增的小范围整数	                    1 到 32767
        serial	           4 字节            自增整数	                         1 到 2147483647
        bigserial	       8 字节            自增的大范围整数	             1 到 9223372036854775807

    ②、货币类型
        money 类型存储带有固定小数精度的货币金额。
        numeric、int 和 bigint 类型的值可以转换为 money，不建议使用浮点数来处理处理货币类型，因为存在舍入错误的可能性。
        名字	    存储容量	      描述	                         范围
        ----------------------------------------------------------------------------------------------------
        money	  8 字节	        货币金额	      -92233720368547758.08 到 +92233720368547758.07

    ③、字符类型
        变长，有长度限制：character varying(n), varchar(n)
        定长，不足补空白：character(n), char(n)
        变长，无长度限制：text

    ④、日期/时间类型
        名字	                            存储空间	             描述
        ----------------------------------------------------------------------------------------------------
        timestamp [ (p) ] [ without time zone ]	8 字节	日期和时间(无时区)
        timestamp [ (p) ] with time zone	    8 字节	日期和时间，有时区
        date	                                4 字节	只用于日期
        time [ (p) ] [ without time zone ]	    8 字节	只用于一日内时间
        time [ (p) ] with time zone	            12 字节	只用于一日内时间，带时区
        interval [ fields ] [ (p) ]	            12 字节	时间间隔

    ⑤、布尔类型
        boolean 类型只有1字节，有"true"(真)或"false"(假)两个状态， 第三种"unknown"(未知)状态，用 NULL 表示。

    ⑥、枚举类型
        枚举类型是一个包含静态合值的有序集合的数据类型。
        与其他类型不同的是枚举类型需要使用 CREATE TYPE 命令创建。一旦创建，枚举类型可以用于表和函数定义。
            例如：创建一礼拜的选项值：
                   CREATE TYPE week AS ENUM ('Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun');
                 使用创建的枚举类型：
                   CREATE TABLE public.t_user (
                       "worship" week not null
                   );

    ⑦、JSON 类型
        # json 数据类型可以用来存储 JSON（JavaScript Object Notation）数据，
        1︎⃣、创建一个包含 JSON 字段的产品表：            创建一个包含 JSONB 字段的产品表：
               CREATE TABLE products_json (            CREATE TABLE products_jsonb (
                   id serial PRIMARY KEY,                 id serial PRIMARY KEY,
                   details json NOT NULL                  details jsonb NOT NULL
               );                                      );
           json：以文本形式存储 JSON 数据，保留了原始的空格和键的顺序。这意味着输入的 JSON 文本将被严格地保持原样。
           jsonb：以二进制格式存储 JSON 数据，去除了多余的空白字符，并且不允许重复的键。
                  如果一个Json对象中有多个相同名称的键，则只有最后一个键值对会被保留。jsonb 还支持索引，而 json 不支持。

       2︎⃣、接着，我们可以向这两个表中插入一些包含 JSON 数据的记录。
           INSERT INTO products_json (details)
             VALUES ('{"name": "Laptop",     "price": 999, "tags": ["electronics", "laptops"]}'::json),
                    ('{"name": "Smartphone", "price": 699, "tags": ["electronics", "phones"]}'::json);

           INSERT INTO products_jsonb (details)
             VALUES ('{"name": "Laptop",     "price": 999, "tags": ["electronics", "laptops"]}'::jsonb),
                    ('{"name": "Smartphone", "price": 699, "tags": ["electronics", "phones"]}'::jsonb);

          -- 更新 products_json 表中第一个产品的名称：
              UPDATE products_json
              SET details = jsonb_set(details::jsonb, '{name}', '"Notebook"')::json
              WHERE id = 1;
             注意这里我们使用了 jsonb_set 函数，它要求输入是 jsonb 类型，所以我们需要先将 json 转换为 jsonb。

          -- 直接更新 products_jsonb 表中的数据，因为 jsonb 支持更直接的操作：
              UPDATE products_jsonb
              SET details = jsonb_set(details, '{name}', '"Notebook"')
              WHERE id = 1;

          -- 向 products_jsonb 表中的所有产品添加一个新的字段 stock：
              UPDATE products_jsonb
              SET details = details || '{"stock": 10}'::jsonb;

       3︎⃣、操作函数和操作符
           -> 和 ->>：用于从 JSON 对象中提取键值。-> 返回一个 json 或 jsonb 类型的结果，而 ->> 返回文本。
           @> 和 <@： 用于包含检查，即一个 JSON 是否完全包含另一个。
           ||：       用于合并两个 JSON 对象。
           json_array_elements：将 JSON 数组转换为一组行。

           -- 使用键提取操作符，提取名为 "name" 的键对应的值（文本形式）：
               SELECT details->>'name' AS product_name FROM products_json;
               SELECT details->>'name' AS product_name FROM products_jsonb;
           -- 提取名为 "tags" 的键对应的数组（JSON 形式）：
               SELECT details->'tags' AS tags FROM products_json;
               SELECT details->'tags' AS tags FROM products_jsonb;
           -- 查找所有价格大于 700 的产品：
               SELECT * FROM products_json WHERE (details->>'price')::int > 700;
               SELECT * FROM products_jsonb WHERE (details->>'price')::int > 700;

       4︎⃣、此外还有相关的函数来处理 json 数据：
           array_to_json('{{1,5},{99,100}}'::int[])	    返回结果：[[1,5],[99,100]]
           row_to_json(row(1,'foo'))	                返回结果：{"f1":1,"f2":"foo"}

    ⑧、数组类型
        # PostgreSQL 允许将字段定义成变长的多维数组。数组类型可以是任何基本类型或用户定义类型，枚举类型或复合类型。
        1︎⃣、创建表的时候，我们可以声明数组，方式如下：              也可以使用 "ARRAY" 关键字，如下所示：
            CREATE TABLE sal_emp (                            CREATE TABLE sal_emp (
                name            text,                             name text,
                pay_by_quarter  integer[],                        pay_by_quarter integer ARRAY[4],
                schedule        text[][]                          schedule text[][]
            );                                                );
            pay_by_quarter 为一维整型数组、schedule 为二维文本类型数组。
        2︎⃣、插入值使用花括号 {}，元素在 {} 使用逗号隔开：
            INSERT INTO sal_emp
                VALUES ('Bill', '{10000, 10000, 10000, 10000}', '{{"meeting", "lunch"}, {"training", "presentation"}}'),
                       ('Carol', '{20000, 25000, 25000, 25000}', '{{"breakfast", "consulting"}, {"meeting", "lunch"}}');
        3︎⃣、访问数组
            SELECT name FROM sal_emp WHERE pay_by_quarter[1] <> pay_by_quarter[2];    返回结果为：Carol
           修改数组
            UPDATE sal_emp SET pay_by_quarter = '{25000,25000,27000,27000}' WHERE name = 'Carol';
           或者使用 ARRAY 构造器语法：
            UPDATE sal_emp SET pay_by_quarter = ARRAY[25000,25000,27000,27000] WHERE name = 'Carol';

        4︎⃣、数组中检索，比如：
            SELECT * FROM sal_emp WHERE pay_by_quarter[1] = 10000 OR
                                        pay_by_quarter[2] = 10000 OR
                                        pay_by_quarter[3] = 10000 OR
                                        pay_by_quarter[4] = 10000;

            还可以用下面的语句找出数组中所有元素值都等于 10000 的行：
                SELECT * FROM sal_emp WHERE 10000 = ALL (pay_by_quarter);
            也能使用 generate_subscripts 函数。例如：
                SELECT * FROM (
                    SELECT pay_by_quarter,
                           generate_subscripts(pay_by_quarter, 1) AS s
                    FROM sal_emp
                    ) AS foo
                WHERE pay_by_quarter[s] = 10000;

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

三、针对
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

三、索引
    -- 创建索引
       drop index if exists t_user_username;
       create index t_user_username on t_user (username);
    -- 创建唯一索引
       drop index if exists t_user_username;
       create index t_user_username on t_user (username);
    -- 查看索引
       \d t_user

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
