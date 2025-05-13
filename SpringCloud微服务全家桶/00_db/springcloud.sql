DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`(
    `id`           int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `superior_id`  int(11)      DEFAULT NULL COMMENT '上级Id',
    `user_name`    varchar(50)  NOT NULL COMMENT '用户名',
    `pass_word`    varchar(100) NOT NULL COMMENT '密码',
    `alias`        varchar(50)  DEFAULT NULL COMMENT '别名',
    `age`          int(3)       DEFAULT NULL COMMENT '年龄',
    `sex`          int(1)       DEFAULT NULL COMMENT '性别(0-女,1-男)',
    `phone`        varchar(20)  DEFAULT NULL COMMENT '手机号',
    `address`      varchar(255) DEFAULT NULL COMMENT '地址',
    `email`        varchar(100) DEFAULT NULL COMMENT '邮件',
    `delete_flag`  int(1)       DEFAULT '0' COMMENT '逻辑删除(0-未删除,1-已删除)',
    `created_date` datetime     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_date` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (1, NULL, '朱璐', 'e10adc3949ba59abbe56e057f20f883e', '系统管理员', 35, 1, '13800138000', '北京市海淀区', 'admin@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (2, 1, '田震南', 'e10adc3949ba59abbe56e057f20f883e', '用户一号', 28, 1, '13900139001', '上海市浦东新区', 'user1@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (3, 1, '钟震南', 'e10adc3949ba59abbe56e057f20f883e', '用户二号', 25, 0, '13900139002', '广州市天河区', 'user2@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (4, 2, '莫致远', 'e10adc3949ba59abbe56e057f20f883e', '用户三号', 32, 1, '13900139003', '深圳市南山区', 'user3@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (5, 2, '郭安琪', 'e10adc3949ba59abbe56e057f20f883e', '用户四号', 22, 0, '13900139004', '杭州市西湖区', 'user4@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (6, 3, '魏致远', 'e10adc3949ba59abbe56e057f20f883e', '用户五号', 30, 1, '13900139005', '成都市武侯区', 'user5@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (7, 3, '郭致远', 'e10adc3949ba59abbe56e057f20f883e', '用户六号', 27, 0, '13900139006', '武汉市洪山区', 'user6@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (8, 4, '顾秀英', 'e10adc3949ba59abbe56e057f20f883e', '用户七号', 29, 1, '13900139007', '南京市鼓楼区', 'user7@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (9, 4, '王詩涵', 'e10adc3949ba59abbe56e057f20f883e', '用户八号', 24, 0, '13900139008', '西安市雁塔区', 'user8@example.com', 0, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
INSERT INTO `springcloud`.`user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `address`, `email`, `delete_flag`, `created_date`, `updated_date`) VALUES (10, 5, '龚詩涵', 'e10adc3949ba59abbe56e057f20f883e', '已删除用户', 40, 1, '13900139009', '重庆市渝中区', 'deleted@example.com', 1, '2025-05-13 11:29:56', '2025-05-13 11:30:52');
