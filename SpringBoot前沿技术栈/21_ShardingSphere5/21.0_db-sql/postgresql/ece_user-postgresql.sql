
-- ----------------------------
-- 用户表
-- ----------------------------

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

COMMENT ON TABLE smc_ece1.ece_user IS '用户表';


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


INSERT INTO ece_user VALUES (1,  688, 'oyB4OUjNhy', '程秀英', '2011-03-23 05:39:07', 39, '女', '房山区岳琉路812号',     '2023-11-09 02:03:16', '2005-12-01 07:58:24');
INSERT INTO ece_user VALUES (2,  576, 'xWTcOdBM7r', '卢璐',   '2003-10-14 03:41:44', 71, '女', '西城区西長安街744号',   '2010-03-01 23:23:10', '2003-09-08 20:56:06');
INSERT INTO ece_user VALUES (3,  116, 'XlVPNzgTal', '廖璐',   '2009-05-18 08:36:49', 72, '女', '东泰五街283号',        '2003-10-05 21:55:52', '2010-02-02 07:42:45');
INSERT INTO ece_user VALUES (4,  964, 'QbInsADSSa', '史致远', '2017-05-23 05:14:59', 14, '男', '海珠区江南西路89号',     '2016-01-08 20:56:36', '2019-12-20 08:35:04');
INSERT INTO ece_user VALUES (5,  364, '5nRHOD42pb', '余宇宁', '2001-08-11 10:25:11', 73, '男', '锦江区人民南路四段381号', '2017-09-13 08:38:46', '2000-03-08 11:58:25');
INSERT INTO ece_user VALUES (6,  600, 'SBbw23MfIc', '汪致远', '2005-01-17 00:35:30', 97, '男', '成华区玉双路6号739号',   '2011-03-16 01:30:35', '2010-05-21 12:20:03');
INSERT INTO ece_user VALUES (7,  818, '5TS7ROSUAx', '侯子异', '2014-01-24 21:32:19', 79, '男', '越秀区中山二路623号',    '2018-02-02 10:40:48', '2010-12-06 16:44:26');
INSERT INTO ece_user VALUES (8,  946, 'OXkSbHnweH', '张岚',   '2003-09-27 17:47:16', 2,  '女', '福田区深南大道460号',    '2013-02-26 06:11:53', '2017-03-06 03:26:49');
INSERT INTO ece_user VALUES (9,  307, 'z5QrG38mAr', '许睿',   '2016-07-06 22:07:22', 9,  '男', '龙岗区学园一巷669号',    '2006-10-06 06:56:33', '2015-01-16 23:23:23');
INSERT INTO ece_user VALUES (10, 662, 'DtXB9yZkGp', '潘杰宏', '2022-04-06 23:45:46', 67, '男', '锦江区人民南路四段36号',  '2003-02-07 00:21:13', '2018-03-27 04:24:02');