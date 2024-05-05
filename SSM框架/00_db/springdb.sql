CREATE TABLE `student`(
    `id`    int NOT NULL AUTO_INCREMENT,
    `name`  varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
    `email` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
    `age`   int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
    `uid` int DEFAULT NULL COMMENT '订单外键id',
    `ordertime` datetime DEFAULT NULL COMMENT '订单创建时间',
    `money` double DEFAULT NULL COMMENT '订单商品价格',
    PRIMARY KEY (`id`),
    KEY `uid` (`uid`),
    CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `role` (
    `id` int NOT NULL COMMENT '编号',
    `role_name` varchar(30) DEFAULT NULL COMMENT '角色名称',
    `role_desc` varchar(60) DEFAULT NULL COMMENT '角色描述',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(32) NOT NULL COMMENT '用户名称',
    `birthday` datetime DEFAULT NULL COMMENT '生日',
    `sex` varchar(10) DEFAULT NULL COMMENT '性别',
    `address` varchar(256) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;