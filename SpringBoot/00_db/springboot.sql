DROP TABLE IF EXISTS `db_student`;
CREATE TABLE `db_student` (
    `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `name` varchar(255) DEFAULT NULL COMMENT '用户名',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `age` int DEFAULT NULL COMMENT '年龄',
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
