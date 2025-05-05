DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`(
    `Id`          bigint NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `brand_name`  varchar(255) DEFAULT NULL COMMENT '品牌名称',
    `racking`     int          DEFAULT NULL COMMENT '是否上架:0为true即上架,1为false下架',
    `delete_flag` int          DEFAULT NULL COMMENT '逻辑删除：0存在,1删除',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌表';
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (1, '韦秀英', 1, 1, 'It provides strong authentication and secure encrypted communications between two hosts, known as SSH Port Forwarding (Tunneling), over an insecure network.');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (2, '范晓明', 1, 1, 'Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. Creativity is intelligence having fun. To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source. Champions keep playing until they get it right.');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (3, '雷子异', 1, 1, 'Typically, it is employed as an encrypted version of Telnet. Anyone who has never made a mistake has never tried anything new. Optimism is the one quality more associated with success and happiness than any other. Remember that failure is an event, not a person.');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (4, '金子异', 1, 0, 'Success consists of going from failure to failure without loss of enthusiasm. Genius is an infinite capacity for taking pains. How we spend our days is, of course, how we spend our lives. To successfully establish a new connection to local/remote server - no matter via SSL or SSH, set the database login information in the General tab.');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (5, '姜杰宏', 0, 0, 'It collects process metrics such as CPU load, RAM usage, and a variety of other resources over SSH/SNMP. The On Startup feature allows you to control what tabs appear when you launch Navicat. The repository database can be an existing MySQL, MariaDB, PostgreSQL, SQL Server, or Amazon RDS instance. All the Navicat Cloud objects are located under different projects. You can share the project to other Navicat Cloud accounts for collaboration.');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (6, '冯致远', 1, 1, 'The Information Pane shows the detailed object information, project activities, the DDL of database objects, object dependencies, membership of users/roles and preview. SSH serves to prevent such vulnerabilities and allows you to access a remote server\s shell without compromising security. Remember that failure is an event, not a person. The Synchronize to Database function will give you a full picture of all database differences. The Synchronize to Database function will give');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (7, '范岚', 1, 0, 'Such sessions are also susceptible to session hijacking, where a malicious user takes over your session once you have authenticated. I will greet this day with love in my heart. The Main Window consists of several toolbars and panes for you to work on connections, database objects and advanced tools.');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (8, '于璐', 1, 1, 'To get a secure connection, the first thing you need to do is to install OpenSSL Library and download Database Source. If the plan doesn’t work, change the plan, but never the goal. Navicat Cloud provides a cloud service for synchronizing connections, queries, model files and virtual group information from Navicat, other Navicat family members, different machines and different platforms. Navicat allows you to transfer data from one database and/or schema to another with detailed');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (9, '陆子异', 0, 1, 'After comparing data, the window shows the number of records that will be inserted, updated or deleted in the target. You can select any connections, objects or projects, and then select the corresponding buttons on the Information Pane.');
INSERT INTO `business`.`brand` (`Id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (10, '梁云熙', 0, 1, 'Secure Sockets Layer(SSL) is a protocol for transmitting private documents via the Internet. Monitored servers include MySQL, MariaDB and SQL Server, and compatible with cloud databases like Amazon RDS, Amazon Aurora, Oracle Cloud, Google Cloud and Microsoft Azure. Navicat Data Modeler is a powerful and cost-effective database design tool which helps you build high-quality conceptual, logical and physical data models.');


DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`(
    `id`    int NOT NULL AUTO_INCREMENT COMMENT '主键 Id',
    `name`  varchar(255) DEFAULT NULL COMMENT '用户名',
    `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
    `age`   int          DEFAULT NULL COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生表';
INSERT INTO `business`.`student` (`id`, `name`, `email`, `age`) VALUES (1, '向詩涵', 'xianshihan@icloud.com', 14);
INSERT INTO `business`.`student` (`id`, `name`, `email`, `age`) VALUES (2, '钟嘉伦', 'jialunzh@gmail.com', 97);
INSERT INTO `business`.`student` (`id`, `name`, `email`, `age`) VALUES (3, '冯詩涵', 'fesh829@mail.com', 19);
INSERT INTO `business`.`student` (`id`, `name`, `email`, `age`) VALUES (4, '丁致远', 'dingzhiy@icloud.com', 16);
INSERT INTO `business`.`student` (`id`, `name`, `email`, `age`) VALUES (5, '李震南', 'lizhennan@icloud.com', 69);


DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
    `cid`         int          NOT NULL AUTO_INCREMENT COMMENT '主键 Id',
    `code`        int          DEFAULT NULL COMMENT '课程编码',
    `cname`       varchar(255) DEFAULT NULL COMMENT '课程名称',
    `zyname`      varchar(255) DEFAULT NULL COMMENT '专业名称',
    `teacher`     varchar(255) DEFAULT NULL COMMENT '任课老师',
    `tclass`      varchar(255) DEFAULT NULL COMMENT '授课班级',
    `delete_flag` tinyint      DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
INSERT INTO courses (code, cname, zyname, teacher, tclass, delete_flag) VALUES (1001, '高等数学', '计算机科学与技术', '张伟', '2022级1班', 0);
INSERT INTO courses (code, cname, zyname, teacher, tclass, delete_flag) VALUES (1002, '数据结构', '软件工程', '王芳', '2023级2班', 0);
INSERT INTO courses (code, cname, zyname, teacher, tclass, delete_flag) VALUES (1003, '大学英语', '英语', '李强', '2022级3班', 0);
INSERT INTO courses (code, cname, zyname, teacher, tclass, delete_flag) VALUES (1004, '数据库原理', '计算机科学与技术', '赵敏', '2023级1班', 0);
INSERT INTO courses (code, cname, zyname, teacher, tclass, delete_flag) VALUES (1005, '计算机网络', '软件工程', '周杰', '2022级2班', 1);


DROP TABLE IF EXISTS `time_table`;
CREATE TABLE `time_table` (
    `id`          int         NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     int         DEFAULT NULL COMMENT '用户id',
    `course_id`   int         DEFAULT NULL COMMENT '课程id',
    `tenant`      varchar(32) DEFAULT NULL COMMENT '租户标识(区别老师和学生)',
    `week`        int         DEFAULT NULL COMMENT '星期',
    `start`       datetime    DEFAULT NULL COMMENT '课程上课时间',
    `finish`      datetime    DEFAULT NULL COMMENT '课程下课时间',
    `delete_flag` tinyint     DEFAULT '0' COMMENT '删除标记(0-未删除,1-已删除)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户课程表';
