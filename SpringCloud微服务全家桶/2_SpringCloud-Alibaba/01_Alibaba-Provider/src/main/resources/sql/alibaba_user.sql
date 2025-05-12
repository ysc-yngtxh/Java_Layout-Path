

CREATE TABLE `alibaba_user`(
    `id`           INT           AUTO_INCREMENT COMMENT '主键Id',
    `superior_id`  INT           DEFAULT NULL COMMENT '上级Id',
    `user_name`    VARCHAR(255)  DEFAULT NULL COMMENT '用户名',
    `pass_word`    VARCHAR(255)  DEFAULT NULL COMMENT '密码',
    `alias`        VARCHAR(255)  DEFAULT NULL COMMENT '别名',
    `age`          INT           DEFAULT NULL COMMENT '年龄',
    `sex`          VARCHAR(255)  DEFAULT NULL COMMENT '性别',
    `phone`        VARCHAR(255)  DEFAULT NULL COMMENT '手机号',
    `address`      VARCHAR(255)  DEFAULT NULL COMMENT '地址',
    `email`        VARCHAR(255)  DEFAULT NULL COMMENT '邮件',
    `delete_flag`  TINYINT       DEFAULT 0 COMMENT '逻辑删除(0存在 1删除)',
    `created_date` TIMESTAMP     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_date` TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COLLATE utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT '用户信息表';
