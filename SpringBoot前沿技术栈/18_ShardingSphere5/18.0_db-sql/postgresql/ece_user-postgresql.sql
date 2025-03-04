
-- ----------------------------
-- 用户表
-- ----------------------------

-- TODO 创建序列
CREATE SEQUENCE public.ece_user_id_seq;
-- TODO 创建表
CREATE TABLE public.ece_user(
    id           int8          NOT NULL DEFAULT nextval('public.ece_user_id_seq'::regclass),
--  id           serial        NOT NULL, 类型serial表示该字段数值是自增的，就不需要提前执行创建序列的脚本，
--  以上提供了 PostgreSql 的两种自增字段的配置，自行选择即可。
    ece_id       int4          NOT NULL,
    user_code    varchar(32)   COLLATE "pg_catalog"."default",
    user_name    varchar(255)  COLLATE "pg_catalog"."default",
    pass_word    varchar(255)  COLLATE "pg_catalog"."default",
    email        varchar(100)  COLLATE "pg_catalog"."default",
    phone        varchar(20)   COLLATE "pg_catalog"."default",
    birthday     timestamp(6)  DEFAULT NULL,
    age          int4          NOT NULL,
    sex          varchar(20)   DEFAULT NULL,
    address      varchar(255)  COLLATE "pg_catalog"."default",
    create_date  timestamp(6)  DEFAULT CURRENT_TIMESTAMP,
    update_date  timestamp(6)  DEFAULT CURRENT_TIMESTAMP,
    status       varchar(32)   COLLATE "pg_catalog"."default",
    CONSTRAINT "ece_user_pkey" PRIMARY KEY (id)
);

COMMENT ON COLUMN public.ece_user.id IS '主键Id';

COMMENT ON COLUMN public.ece_user.ece_id IS 'eceId';

COMMENT ON COLUMN public.ece_user.user_code IS '用户编码';

COMMENT ON COLUMN public.ece_user.user_name IS '用户名';

COMMENT ON COLUMN public.ece_user.pass_word IS '密码';

COMMENT ON COLUMN public.ece_user.email IS '邮箱';

COMMENT ON COLUMN public.ece_user.phone IS '手机号';

COMMENT ON COLUMN public.ece_user.birthday IS '生日时间';

COMMENT ON COLUMN public.ece_user.age IS '年龄';

COMMENT ON COLUMN public.ece_user.sex IS '性别';

COMMENT ON COLUMN public.ece_user.address IS '地址';

COMMENT ON COLUMN public.ece_user.create_date IS '创建时间';

COMMENT ON COLUMN public.ece_user.update_date IS '更新时间';

COMMENT ON COLUMN public.ece_user.status IS '用户状态(ACTIVE, INACTIVE, DELETED)';

COMMENT ON TABLE public.ece_user IS '用户表';


-- TODO 创建触发器去执行自定义的函数。从而达到在执行更新操作时，可以自动更新字段数据的目的。
-- 创建update_update_date_column()函数，如果该函数已存在，则替换已有的函数。
CREATE OR REPLACE FUNCTION "public".update_update_date_column()
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
    ON public.ece_user -- 触发器作用于 public.ece_user 表
    FOR EACH ROW -- 触发器针对每一行更新操作生效。这意味着每更新一行数据，触发器就会被调用一次。
-- 指定触发器执行的函数为 update_update_date_column。当触发器被触发时，会调用这个函数来执行相应的逻辑。
EXECUTE PROCEDURE "public".update_update_date_column();


INSERT INTO ece_user VALUES (1,  191, 'LhGr5S5Vn0', '丁杰宏', 'ZFr0KOr40P', 'wrighttheresa@icloud.com', '163-9680-9148',  '2011-08-22 20:59:39', 52, '男', '越秀区中山二路771号',        '2005-01-22 22:45:08', '2006-05-14 04:17:57', 'DELETED');
INSERT INTO ece_user VALUES (2,  556, 'NV8BO0KLcv', '尹璐',   'qcX7h97Jjk', 'tszhin8@yahoo.com',        '7903 790326',    '2015-03-28 22:35:16', 44, '男', '天河区大信商圈大信南路502号', '2017-11-16 11:20:28', '2024-06-01 23:48:58', 'ACTIVE');
INSERT INTO ece_user VALUES (3,  618, 'JgjiYkQbus', '蒋杰宏', 'ImwDxpoclS', 'anqcai@yahoo.com',         '52-381-5475',    '2009-05-20 08:41:32', 6,  '男', '成华区玉双路6号911号',       '2016-09-22 09:48:09', '2007-01-23 20:49:15', 'INACTIVE');
INSERT INTO ece_user VALUES (4,  153, 'fxL78F7C3W', '魏睿',   'zRADdIqGL7', 'guoziyi@gmail.com',        '11-681-2485',    '2021-09-22 04:23:10', 33, '男', '龙岗区布吉镇西环路405号',    '2024-03-13 08:40:37', '2005-11-11 22:48:01', 'INACTIVE');
INSERT INTO ece_user VALUES (5,  901, 'gG77w1EeVe', '罗杰宏', '5rQmGI15lW', 'shaozita@yahoo.com',       '718-523-1872',   '2007-07-02 06:26:46', 95, '女', '白云区小坪东路612号',        '2009-04-06 13:34:07', '2002-02-23 19:49:58', 'ACTIVE');
INSERT INTO ece_user VALUES (6,  748, '2eIYpIbKgu', '武詩涵', 'u1YMUwG2wJ', 'zhennanlu@hotmail.com',    '11-298-6160',    '2021-03-14 18:39:41', 52, '男', '京华商圈华夏街964号',        '2007-05-05 11:48:21', '2014-04-02 19:35:41', 'ACTIVE');
INSERT INTO ece_user VALUES (7,  346, 'IViyJBTXur', '于岚',   'GsxHCOQ8Yj', 'shaws@hotmail.com',        '330-477-3594',   '2010-12-12 13:19:03', 98, '女', '福田区景田东一街67号',       '2006-10-21 19:29:08', '2019-02-26 22:22:44', 'DELETED');
INSERT INTO ece_user VALUES (8,  76,  'wCcfjdVGLk', '秦杰宏', 'WHVsnOgJKI', 'tanjialun@outlook.com',    '(161) 604 6639', '2006-07-06 11:59:21', 4,  '女', '朝阳区三里屯路66号',         '2017-04-29 18:37:37', '2024-09-17 19:17:37', 'INACTIVE');
INSERT INTO ece_user VALUES (9,  740, 'KjlLCCHkSd', '林晓明', 'nQ7AZp6mls', 'whkwok@gmail.com',         '134-5217-0482',  '2023-10-23 12:12:19', 53, '男', '坑美十五巷733号',           '2022-12-29 05:29:42', '2005-06-11 09:24:41', 'INACTIVE');
INSERT INTO ece_user VALUES (10, 785, 'BNj5hOGTma', '沈晓明', 'Dy4VyOmdHK', 'ramomarie@icloud.com',     '614-578-9402',   '2008-01-21 23:52:35', 86, '女', '罗湖区清水河一路275号',      '2008-01-12 21:33:13', '2022-11-27 17:12:45', 'DELETED');

