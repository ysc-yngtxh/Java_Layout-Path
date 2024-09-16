

CREATE TABLE `ece_user`(
    `id`       int          NOT NULL AUTO_INCREMENT,
    `username` varchar(32)  NOT NULL COMMENT '用户名称',
    `birthday` datetime     DEFAULT NULL COMMENT '生日',
    `age`      int          NOT NULL,
    `sex`      varchar(10)  DEFAULT NULL COMMENT '性别',
    `address`  varchar(256) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;