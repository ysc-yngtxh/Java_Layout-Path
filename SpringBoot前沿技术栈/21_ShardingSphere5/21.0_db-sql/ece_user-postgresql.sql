

-- TODO 创建序列
CREATE SEQUENCE smc_ece1.ece_user_id_seq;
-- TODO 创建表
CREATE TABLE smc_ece1.ece_user(
    id           int8          NOT NULL DEFAULT nextval('smc_ece1.ece_user_id_seq'::regclass),
--  id           serial        NOT NULL, 类型serial表示该字段数值是自增的，就不需要提前执行创建序列的脚本，
--  以上提供了 PostgreSql 的两种自增字段的配置，自行选择即可。
    ece_id       int4          NOT NULL,
    user_code    varchar(32)   COLLATE "pg_catalog"."default",
    user_name    varchar(255)  COLLATE "pg_catalog"."default",
    birthday     timestamp(6)  DEFAULT NULL,
    age          int4          NOT NULL,
    sex          sex           DEFAULT NULL,
    address      varchar(255)  COLLATE "pg_catalog"."default",
    create_date  timestamp(6)  DEFAULT CURRENT_TIMESTAMP,
    update_date  timestamp(6)  DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT "ece_user_pkey" PRIMARY KEY (id)
);

COMMENT ON COLUMN smc_ece1.ece_user.id IS '主键Id';

COMMENT ON COLUMN smc_ece1.ece_user.ece_id IS 'eceId';

COMMENT ON COLUMN smc_ece1.ece_user.user_code IS '用户编码';

COMMENT ON COLUMN smc_ece1.ece_user.user_name IS '用户名';

COMMENT ON COLUMN smc_ece1.ece_user.birthday IS '生日时间';

COMMENT ON COLUMN smc_ece1.ece_user.age IS '年龄';

COMMENT ON COLUMN smc_ece1.ece_user.sex IS '性别';

COMMENT ON COLUMN smc_ece1.ece_user.address IS '地址';

COMMENT ON COLUMN smc_ece1.ece_user.create_date IS '创建时间';

COMMENT ON COLUMN smc_ece1.ece_user.update_date IS '更新时间';


-- TODO 创建触发器去执行自定义的函数。从而达到在执行更新操作时，可以自动更新字段数据的目的。
-- 创建update_update_date_column()函数，如果该函数已存在，则替换已有的函数。
CREATE OR REPLACE FUNCTION "smc_ece1".update_update_date_column()
    RETURNS TRIGGER AS -- 定义一个函数，该函数返回一个触发器函数
$BODY$ -- 这个标记开始定义函数体内的SQL代码块。
BEGIN  -- BEGIN ... END; 在这里定义了函数体，它包含了实际的逻辑。
    -- NEW 是一个特殊变量，代表即将被插入或更新的行记录。NOW() 是一个 PostgreSQL 内置函数，用于获取当前日期和时间。
    NEW.update_date = NOW();
    RETURN NEW; -- 函数返回修改后的记录。
END;
$BODY$ -- 结束SQL代码块。
    LANGUAGE plpgsql; -- 指定函数使用的编程语言为 plpgsql。

-- 创建一个名称为 update_date_trigger 触发器
CREATE TRIGGER update_date_trigger
    BEFORE UPDATE -- 指定触发器在 UPDATE 操作之前被触发。
    ON smc_ece1.ece_user -- 触发器作用于 smc_ece1.ece_user 表
    FOR EACH ROW -- 触发器针对每一行更新操作生效。这意味着每更新一行数据，触发器就会被调用一次。
-- 指定触发器执行的函数为 update_update_date_column。当触发器被触发时，会调用这个函数来执行相应的逻辑。
EXECUTE PROCEDURE "smc_ece1".update_update_date_column();

