create table oauth2_account(
    `id`                     BIGINT(10) PRIMARY KEY auto_increment COMMENT '主键Id',
    `unique_id`              INT(64)      NOT NULL COMMENT '账号唯一Id',
    `login`                  VARCHAR(255) NOT NULL COMMENT '账号登录名',
    `name`                   VARCHAR(255) NOT NULL COMMENT '账号名',
    `avatar_url`             VARCHAR(255) NOT NULL COMMENT '账户头像地址',
    `registration_id`        VARCHAR(255) NOT NULL COMMENT '注册Id',
    `credentials`            VARCHAR(255) NOT NULL COMMENT '凭证',
    `credentials_expires_at` timestamp    NOT NULL COMMENT '凭证过期时间',
    `create_time`            timestamp    NOT NULL COMMENT '账号创建时间'
) ENGINE = Innodb auto_increment = 1 CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT ='OAuth2通过第三方认证的账号';