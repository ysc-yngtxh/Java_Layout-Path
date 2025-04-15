DROP TABLE IF EXISTS `shiro_user`;
CREATE TABLE `shiro_user` (
    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键 Id',
    `name` varchar(255) DEFAULT NULL COMMENT '用户名',
    `pwd` varchar(255) DEFAULT NULL COMMENT '密码',
    `perms` varchar(255) DEFAULT NULL COMMENT '权限',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户表';
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (1, '毛睿', 'LYhr3WiTXg', 'user:add');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (2, '彭云熙', '4Fq9h0MQvz', 'admin:update');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (3, '孟震南', 'treoFjdgqI', 'vip:delete');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (4, '梁詩涵', 'R2mxHOetMG', 'user:insert');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (5, '陶子韬', 'JKHghnXMDN', 'admin:select');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (6, '黄嘉伦', 'IvHK2KfySg', 'root:normal');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (7, '冯云熙', 'Eon5k112RD', 'super:all');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (8, '谭晓明', '4nD92lwIpO', 'vip:delete');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (9, '陆宇宁', 'GAnZDSoHG7', 'user:add');
INSERT INTO `technician`.`user` (`id`, `name`, `pwd`, `perms`) VALUES (10, '张云熙', 'dV5IGlqKpS', 'svip:normal');


DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
    `id`           int          NOT NULL AUTO_INCREMENT COMMENT '主键 Id',
    `user_name`    varchar(255) DEFAULT NULL COMMENT '用户名',
    `pass_word`    varchar(255) DEFAULT NULL COMMENT '密码',
    `nick_name`    varchar(255) DEFAULT NULL COMMENT '昵称',
    `status`       varchar(255) DEFAULT NULL COMMENT '帐号状态(0正常，1停用)',
    `email`        varchar(255) DEFAULT NULL COMMENT '邮箱',
    `phone_number` varchar(255) DEFAULT NULL COMMENT '手机号',
    `sex`          varchar(255) DEFAULT NULL COMMENT '用户类型(0管理员，1普通用户)',
    `avatar`       BLOB         DEFAULT NULL COMMENT '头像',
    `user_type`    varchar(255) DEFAULT NULL COMMENT '用户类型(0管理员，1普通用户)',
    `permission`   varchar(255) DEFAULT NULL COMMENT '用户权限',
    `create_by`    bigint       DEFAULT NULL COMMENT '创建人',
    `create_time`  datetime     DEFAULT NULL COMMENT '创建时间',
    `update_by`    bigint       DEFAULT NULL COMMENT '更新人',
    `update_time`  datetime     DEFAULT NULL COMMENT '更新时间',
    `del_flag`     varchar(255) DEFAULT NULL COMMENT '删除标志(0未删除， 1已删除)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
