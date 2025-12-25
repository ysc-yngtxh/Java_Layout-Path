DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 Id',
    `username` varchar(50) DEFAULT NULL COMMENT '用户名',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `full_name` varchar(100) DEFAULT NULL COMMENT '全名',
    `active` tinyint(1) DEFAULT '1' COMMENT '是否激活',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户表';
