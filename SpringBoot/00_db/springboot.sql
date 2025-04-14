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


DROP TABLE IF EXISTS `db_user`;
CREATE TABLE `db_user` (
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `username`    varchar(255) DEFAULT NULL COMMENT '用户名',
    `password`    varchar(255) DEFAULT NULL COMMENT '密码',
    `alias`       varchar(255) DEFAULT NULL COMMENT '别名',
    `age`         int          DEFAULT NULL COMMENT '年龄',
    `sex`         int          DEFAULT NULL COMMENT '性别',
    `phone`       varchar(255) DEFAULT NULL COMMENT '手机号码',
    `address`     varchar(255) DEFAULT NULL COMMENT '地址',
    `delete_flag` tinyint      DEFAULT NULL COMMENT '删除标志（0:存在；1:注销）',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '用户表';
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (1, '莫岚', 'rhFz7VkNLB', '女士', 92, 1, '137-8600-6296', '龙岗区布吉镇西环路937号', 1);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (2, '毛云熙', 'naFxac76TB', '小姐', 27, 0, '20-196-9906', '越秀区中山二路960号', 1);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (3, '林云熙', 'G6FGut38DF', '小姐', 1, 0, '20-1633-3524', '白云区机场路棠苑街五巷17号', 0);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (4, '段秀英', 'ZURMuEvq9B', '小姐', 8, 0, '165-3324-8120', '天河区天河路859号', 0);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (5, '陈睿', 'iMScxySPxT', '先生', 17, 1, '171-8395-6092', '徐汇区虹桥路853号', 0);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (6, '张岚', '5CuWNuANwR', '女士', 27, 0, '186-4324-0441', '罗湖区蔡屋围深南东路957号', 0);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (7, '顾岚', '88dHsNKUGw', '教授', 36, 0, '148-1068-9295', '福田区景田东一街38号', 0);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (8, '金睿', 'KyQ3Lz5uMF', '女士', 24, 0, '163-1443-2105', '东城区东单王府井东街151号', 1);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (9, '袁晓明', 'aSSVoz6nsK', '太太', 80, 1, '198-2095-6943', '东泰五街920号', 1);
INSERT INTO `springboot`.`db_user` (`id`, `username`, `password`, `alias`, `age`, `sex`, `phone`, `address`, `delete_flag`) VALUES (10, '毛睿', 'MtsznbJxQW', '教授', 90, 0, '186-9140-3066', '京华商圈华夏街140号', 1);
