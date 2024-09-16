

CREATE SEQUENCE smc_ece1.ece_user_id_seq;

CREATE TABLE smc_ece1.ece_user(
    id           int4 NOT NULL DEFAULT nextval('smc_ece1.ece_user_id_seq'::regclass),
    username varchar(255) COLLATE "pg_catalog"."default",
    birthday     timestamp(6),
    age          int4 NOT NULL,
    sex          varchar(255) COLLATE "pg_catalog"."default",
    address      varchar(255) COLLATE "pg_catalog"."default",
    CONSTRAINT "ece_user_pkey" PRIMARY KEY (id)
);

ALTER TABLE smc_ece1.ece_user
    OWNER TO "root";

COMMENT ON COLUMN smc_ece1.ece_user.id IS '主键ID';

COMMENT ON COLUMN smc_ece1.ece_user.username IS '用户名';

COMMENT ON COLUMN smc_ece1.ece_user.birthday IS '生日时间';

COMMENT ON COLUMN smc_ece1.ece_user.age IS '年龄';

COMMENT ON COLUMN smc_ece1.ece_user.sex IS '性别';

COMMENT ON COLUMN smc_ece1.ece_user.address IS '地址';