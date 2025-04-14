DROP TABLE IF EXISTS `db_student`;
CREATE TABLE `db_student` (
    `id`    int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `name`  varchar(255) DEFAULT NULL COMMENT '用户名',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `age`   int          DEFAULT NULL COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '学生表';
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (1, '陆子异', 'ziyilu9@gmail.com', 29);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (2, '袁子异', 'zyuan@icloud.com', 25);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (3, '傅安琪', 'fu923@gmail.com', 89);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (4, '秦岚', 'lqin@gmail.com', 18);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (5, '谢子异', 'xiezi@gmail.com', 39);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (6, '赵安琪', 'anqizhao@icloud.com', 53);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (7, '赵嘉伦', 'jialun89@yahoo.com', 22);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (8, '徐子韬', 'xuz10@icloud.com', 63);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (9, '严詩涵', 'yan9@yahoo.com', 77);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `email`, `age`) VALUES (10, '吴云熙', 'wuyunxi@hotmail.com', 30);


DROP TABLE IF EXISTS `db_user`;
CREATE TABLE `db_user` (
    `id`           int          NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `superior_id`  int          NOT NULL COMMENT '上级Id',
    `user_name`    varchar(255) DEFAULT NULL COMMENT '用户名',
    `pass_word`    varchar(255) DEFAULT NULL COMMENT '密码',
    `alias`        varchar(255) DEFAULT NULL COMMENT '别名',
    `age`          int          DEFAULT NULL COMMENT '年龄',
    `sex`          int          DEFAULT NULL COMMENT '性别',
    `phone`        varchar(255) DEFAULT NULL COMMENT '手机号码',
    `email`        varchar(255) DEFAULT NULL COMMENT '邮件',
    `address`      varchar(255) DEFAULT NULL COMMENT '地址',
    `delete_flag`  tinyint      DEFAULT NULL COMMENT '删除标志（0:存在；1:注销）',
    `created_date` datetime     DEFAULT NULL COMMENT '创建时间',
    `update_date`  datetime     DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户表';
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (1, 3, '朱璐', 'yN2k0wQ19G', '先生', 63, 1, '10-0355-6798', 'zhu530@icloud.com', '東城区東直門內大街776号', 0, '2011-02-26 02:14:03', '2024-12-11 12:53:10');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (2, 7, '田震南', 'M0EEhYRcg0', '教授', 92, 0, '140-6258-1117', 'tian518@outlook.com', '锦江区人民南路四段120号', 0, '2001-04-11 06:03:45', '2023-09-04 16:42:42');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (3, 8, '钟震南', 'NJ2Oiv4lUW', '先生', 91, 0, '10-303-7024', 'zzhen@gmail.com', '東城区東直門內大街963号', 1, '2008-11-02 00:31:35', '2010-10-26 10:56:29');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (4, 1, '莫致远', 'TQ1BZQPwnU', '教授', 3, 0, '171-8890-9201', 'mozhiyu2@gmail.com', '锦江区红星路三段318号', 1, '2016-07-26 14:14:07', '2001-07-31 18:09:43');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (5, 0, '郭安琪', 'BgNQ2nk5rZ', '先生', 47, 1, '186-1160-2134', 'anqiguo8@icloud.com', '环区南街二巷993号', 1, '2016-02-20 13:16:38', '2013-11-05 06:09:33');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (6, 9, '魏致远', 'Y0liczZrkN', '太太', 53, 0, '140-5921-4652', 'zhiyuanwei86@icloud.com', '東城区東直門內大街508号', 0, '2003-05-07 00:15:18', '2010-02-19 19:06:58');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (7, 7, '郭致远', 'v0xQpZpcx9', '先生', 72, 1, '159-8981-4088', 'zhguo@outlook.com', '海珠区江南西路283号', 0, '2015-04-12 01:26:29', '2014-06-08 15:39:27');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (8, 10, '顾秀英', 'v8oZvdFjBn', '太太', 46, 1, '149-6436-4686', 'xiuying7@mail.com', '海淀区清河中街68号57号', 1, '2023-06-30 22:57:59', '2010-01-13 20:30:27');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (9, 1, '王詩涵', 'gyskDoANeL', '先生', 89, 1, '194-8687-7143', 'was1030@gmail.com', '乐丰六路511号', 1, '2013-08-10 14:58:58', '2024-08-29 13:33:07');
INSERT INTO `springboot`.`db_user` (`id`, `superior_id`, `user_name`, `pass_word`, `alias`, `age`, `sex`, `phone`, `email`, `address`, `delete_flag`, `created_date`, `update_date`) VALUES (10, 1, '龚詩涵', 'DrkwL6zguj', '女士', 16, 1, '141-6420-1971', 'gongshihan@gmail.com', '徐汇区虹桥路133号', 0, '2011-05-24 19:50:31', '2000-06-13 18:25:14');
