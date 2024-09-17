

CREATE TABLE `ece_user`(
    `id`          int              NOT NULL AUTO_INCREMENT,
    `ece_id`      int              DEFAULT NULL COMMENT 'eceId',
    `user_code`   varchar(32)      NOT NULL COMMENT '用户编码',
    `user_name`   varchar(32)      NOT NULL COMMENT '用户名称',
    `birthday`    datetime         DEFAULT NULL COMMENT '生日',
    `age`         int              NOT NULL COMMENT '年龄',
    `sex`         enum('男', '女')  DEFAULT NULL COMMENT '性别',
    `address`     varchar(256)     DEFAULT NULL COMMENT '地址',
    `create_date` TIMESTAMP        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;