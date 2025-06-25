/*
 Navicat Premium Dump SQL

 Source Server         : PostgreSql
 Source Server Type    : PostgreSQL
 Source Server Version : 170002 (170002)
 Source Host           : localhost:5432
 Source Catalog        : smartmatch_ece
 Source Schema         : smc_ece

 Target Server Type    : PostgreSQL
 Target Server Version : 170002 (170002)
 File Encoding         : 65001

 Date: 15/12/2024 22:01:13
*/


-- ----------------------------
-- Sequence structure for ece_user_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "smc_ece"."ece_user_id_seq";
CREATE SEQUENCE "smc_ece"."ece_user_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;
ALTER SEQUENCE "smc_ece"."ece_user_id_seq" OWNER TO "root";

-- ----------------------------
-- Table structure for User
-- ----------------------------
DROP TABLE IF EXISTS "smc_ece"."User";
CREATE TABLE "smc_ece"."User" (
  "Id" int4 NOT NULL,
  "userName" varchar(256) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "smc_ece"."User" OWNER TO "root";

-- ----------------------------
-- Records of User
-- ----------------------------
BEGIN;
INSERT INTO "smc_ece"."User" ("Id", "userName") VALUES (1, 'Alice');
COMMIT;

-- ----------------------------
-- Table structure for ece_user
-- ----------------------------
DROP TABLE IF EXISTS "smc_ece"."ece_user";
CREATE TABLE "smc_ece"."ece_user" (
  "id" int8 NOT NULL DEFAULT nextval('"smc_ece".ece_user_id_seq'::regclass),
  "ece_id" int4 NOT NULL,
  "user_code" varchar(32) COLLATE "pg_catalog"."default",
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "pass_word" varchar(255) COLLATE "pg_catalog"."default",
  "email" varchar(100) COLLATE "pg_catalog"."default",
  "phone" varchar(20) COLLATE "pg_catalog"."default",
  "birthday" timestamp(6) DEFAULT NULL::timestamp without time zone,
  "age" int4 NOT NULL,
  "sex" varchar(20) COLLATE "pg_catalog"."default" DEFAULT NULL::character varying,
  "address" varchar(255) COLLATE "pg_catalog"."default",
  "create_date" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "update_date" timestamp(6) DEFAULT CURRENT_TIMESTAMP,
  "status" varchar(32) COLLATE "pg_catalog"."default"
)
;
ALTER TABLE "smc_ece"."ece_user" OWNER TO "root";
COMMENT ON COLUMN "smc_ece"."ece_user"."id" IS '主键Id';
COMMENT ON COLUMN "smc_ece"."ece_user"."ece_id" IS 'eceId';
COMMENT ON COLUMN "smc_ece"."ece_user"."user_code" IS '用户编码';
COMMENT ON COLUMN "smc_ece"."ece_user"."user_name" IS '用户名';
COMMENT ON COLUMN "smc_ece"."ece_user"."pass_word" IS '密码';
COMMENT ON COLUMN "smc_ece"."ece_user"."email" IS '邮箱';
COMMENT ON COLUMN "smc_ece"."ece_user"."phone" IS '手机号';
COMMENT ON COLUMN "smc_ece"."ece_user"."birthday" IS '生日时间';
COMMENT ON COLUMN "smc_ece"."ece_user"."age" IS '年龄';
COMMENT ON COLUMN "smc_ece"."ece_user"."sex" IS '性别';
COMMENT ON COLUMN "smc_ece"."ece_user"."address" IS '地址';
COMMENT ON COLUMN "smc_ece"."ece_user"."create_date" IS '创建时间';
COMMENT ON COLUMN "smc_ece"."ece_user"."update_date" IS '更新时间';
COMMENT ON COLUMN "smc_ece"."ece_user"."status" IS '用户状态(ACTIVE, INACTIVE, DELETED)';
COMMENT ON TABLE "smc_ece"."ece_user" IS '用户表';

-- ----------------------------
-- Records of ece_user
-- ----------------------------
BEGIN;
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (1, 191, 'LhGr5S5Vn0', '丁杰宏', 'ZFr0KOr40P', 'wrighttheresa@icloud.com', '163-9680-9148', '2011-08-22 20:59:39', 52, '男', '越秀区中山二路771号', '2005-01-22 22:45:08', '2006-05-14 04:17:57', 'DELETED');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (2, 556, 'NV8BO0KLcv', '尹璐', 'qcX7h97Jjk', 'tszhin8@yahoo.com', '7903 790326', '2015-03-28 22:35:16', 44, '男', '天河区大信商圈大信南路502号', '2017-11-16 11:20:28', '2024-06-01 23:48:58', 'ACTIVE');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (3, 618, 'JgjiYkQbus', '蒋杰宏', 'ImwDxpoclS', 'anqcai@yahoo.com', '52-381-5475', '2009-05-20 08:41:32', 6, '男', '成华区玉双路6号911号', '2016-09-22 09:48:09', '2007-01-23 20:49:15', 'INACTIVE');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (4, 153, 'fxL78F7C3W', '魏睿', 'zRADdIqGL7', 'guoziyi@gmail.com', '11-681-2485', '2021-09-22 04:23:10', 33, '男', '龙岗区布吉镇西环路405号', '2024-03-13 08:40:37', '2005-11-11 22:48:01', 'INACTIVE');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (5, 901, 'gG77w1EeVe', '罗杰宏', '5rQmGI15lW', 'shaozita@yahoo.com', '718-523-1872', '2007-07-02 06:26:46', 95, '女', '白云区小坪东路612号', '2009-04-06 13:34:07', '2002-02-23 19:49:58', 'ACTIVE');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (6, 748, '2eIYpIbKgu', '武詩涵', 'u1YMUwG2wJ', 'zhennanlu@hotmail.com', '11-298-6160', '2021-03-14 18:39:41', 52, '男', '京华商圈华夏街964号', '2007-05-05 11:48:21', '2014-04-02 19:35:41', 'ACTIVE');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (7, 346, 'IViyJBTXur', '于岚', 'GsxHCOQ8Yj', 'shaws@hotmail.com', '330-477-3594', '2010-12-12 13:19:03', 98, '女', '福田区景田东一街67号', '2006-10-21 19:29:08', '2019-02-26 22:22:44', 'DELETED');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (8, 76, 'wCcfjdVGLk', '秦杰宏', 'WHVsnOgJKI', 'tanjialun@outlook.com', '(161) 604 6639', '2006-07-06 11:59:21', 4, '女', '朝阳区三里屯路66号', '2017-04-29 18:37:37', '2024-09-17 19:17:37', 'INACTIVE');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (9, 740, 'KjlLCCHkSd', '林晓明', 'nQ7AZp6mls', 'whkwok@gmail.com', '134-5217-0482', '2023-10-23 12:12:19', 53, '男', '坑美十五巷733号', '2022-12-29 05:29:42', '2005-06-11 09:24:41', 'INACTIVE');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (10, 785, 'BNj5hOGTma', '沈晓明', 'Dy4VyOmdHK', 'ramomarie@icloud.com', '614-578-9402', '2008-01-21 23:52:35', 86, '女', '罗湖区清水河一路275号', '2008-01-12 21:33:13', '2022-11-27 17:12:45', 'DELETED');
INSERT INTO "smc_ece"."ece_user" ("id", "ece_id", "user_code", "user_name", "pass_word", "email", "phone", "birthday", "age", "sex", "address", "create_date", "update_date", "status") VALUES (1044018246606389248, 123456, 'ECE-RPT', '李四', '123456', '12345678@qq.com', '13888888867', '2024-09-16 10:00:00', 22, '女', '湖北武汉洪山区', '2024-09-20 22:35:47.007524', '2024-09-20 22:35:47.007524', 'ACTIVE');
COMMIT;

-- ----------------------------
-- Function structure for update_update_date_column
-- ----------------------------
DROP FUNCTION IF EXISTS "smc_ece"."update_update_date_column"();
CREATE OR REPLACE FUNCTION "smc_ece"."update_update_date_column"()
  RETURNS "pg_catalog"."trigger" AS $BODY$
BEGIN
   NEW.updated_at = NOW();
   RETURN NEW;
END;
$BODY$
  LANGUAGE plpgsql VOLATILE
  COST 100;
ALTER FUNCTION "smc_ece"."update_update_date_column"() OWNER TO "root";

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "smc_ece"."ece_user_id_seq"
OWNED BY "smc_ece"."ece_user"."id";
SELECT setval('"smc_ece"."ece_user_id_seq"', 1, false);

-- ----------------------------
-- Primary Key structure for table User
-- ----------------------------
ALTER TABLE "smc_ece"."User" ADD CONSTRAINT "User_pkey" PRIMARY KEY ("Id");

-- ----------------------------
-- Triggers structure for table ece_user
-- ----------------------------
CREATE TRIGGER "update_date_trigger" BEFORE UPDATE ON "smc_ece"."ece_user"
FOR EACH ROW
EXECUTE PROCEDURE "smc_ece"."update_update_date_column"();

-- ----------------------------
-- Primary Key structure for table ece_user
-- ----------------------------
ALTER TABLE "smc_ece"."ece_user" ADD CONSTRAINT "ece_user_pkey" PRIMARY KEY ("id");
