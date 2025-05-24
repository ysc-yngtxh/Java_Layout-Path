DROP TABLE IF EXISTS student;
CREATE TABLE student(
    `id`    INT PRIMARY KEY AUTO_INCREMENT,
    `name`  VARCHAR(100) DEFAULT NULL COMMENT '用户名称',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `age`   int DEFAULT NULL COMMENT '年龄'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='学生表';
INSERT INTO student (name, email, age) VALUES ('张三', 'zhangsan@example.com', '20');
INSERT INTO student (name, email, age) VALUES ('李四', 'lisi@example.com', '21');
INSERT INTO student (name, email, age) VALUES ('王五', 'wangwu@example.com', '22');
INSERT INTO student (name, email, age) VALUES ('赵六', 'zhaoliu@example.com', '19');
INSERT INTO student (name, email, age) VALUES ('钱七', 'qianqi@example.com', '20');
INSERT INTO student (name, email, age) VALUES ('孙八', 'sunba@example.com', '23');
INSERT INTO student (name, email, age) VALUES ('周九', 'zhoujiu@example.com', '21');
INSERT INTO student (name, email, age) VALUES ('吴十', 'wushi@example.com', '22');
INSERT INTO student (name, email, age) VALUES ('郑十一', 'zhengshiyi@example.com', '20');
INSERT INTO student (name, email, age) VALUES ('王十二', 'wangshier@example.com', '19');


DROP TABLE IF EXISTS user;
CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `birthday` date DEFAULT NULL COMMENT '生日',
    `sex` varchar(10) DEFAULT NULL COMMENT '性别',
    `address` varchar(255) DEFAULT NULL COMMENT '地址',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
INSERT INTO user (username, birthday, sex, address) VALUES ('张三', '1990-05-15', '男', '北京市朝阳区');
INSERT INTO user (username, birthday, sex, address) VALUES ('李四', '1985-08-22', '男', '上海市浦东新区');
INSERT INTO user (username, birthday, sex, address) VALUES ('王芳', '1992-03-10', '女', '广州市天河区');
INSERT INTO user (username, birthday, sex, address) VALUES ('赵敏', '1995-11-28', '女', '深圳市南山区');
INSERT INTO user (username, birthday, sex, address) VALUES ('刘强', '1988-07-03', '男', '成都市武侯区');
INSERT INTO user (username, birthday, sex, address) VALUES ('陈静', '1993-09-17', '女', '杭州市西湖区');
INSERT INTO user (username, birthday, sex, address) VALUES ('孙伟', '1991-12-05', '男', '武汉市洪山区');
INSERT INTO user (username, birthday, sex, address) VALUES ('周婷', '1994-04-30', '女', '南京市鼓楼区');
INSERT INTO user (username, birthday, sex, address) VALUES ('吴刚', '1987-06-12', '男', '西安市雁塔区');
INSERT INTO user (username, birthday, sex, address) VALUES ('郑丽', '1996-02-25', '女', '重庆市渝中区');
