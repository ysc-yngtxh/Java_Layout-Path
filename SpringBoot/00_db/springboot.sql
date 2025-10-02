DROP TABLE IF EXISTS `db_student`;
CREATE TABLE `db_student` (
    `id`         int          NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `name`       varchar(255) DEFAULT NULL COMMENT '用户名',
    `teacher_id` int          DEFAULT NULL COMMENT '教师ID',
    `email`      varchar(255) DEFAULT NULL COMMENT '邮箱',
    `age`        int          DEFAULT NULL COMMENT '年龄',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT '学生表';
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (1, '陆子异', 1, 'ziyilu9@gmail.com', 29);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (2, '袁子异', 1, 'zyuan@icloud.com', 25);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (3, '傅安琪', 1, 'fu923@gmail.com', 89);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (4, '秦岚', 4, 'lqin@gmail.com', 18);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (5, '谢子异', 4, 'xiezi@gmail.com', 39);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (6, '赵安琪', 6, 'anqizhao@icloud.com', 53);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (7, '赵嘉伦', 6, 'jialun89@yahoo.com', 22);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (8, '徐子韬', 6, 'xuz10@icloud.com', 63);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (9, '严詩涵', 8, 'yan9@yahoo.com', 77);
INSERT INTO `springboot`.`db_student` (`id`, `name`, `teacher_id`, `email`, `age`) VALUES (10, '吴云熙', 8,'wuyunxi@hotmail.com', 30);


DROP TABLE IF EXISTS `db_department`;
CREATE TABLE `db_department` (
    `id`          INT          PRIMARY KEY AUTO_INCREMENT COMMENT '部门唯一标识ID',
    `name`        VARCHAR(100) NOT NULL COMMENT '部门名称',
    `description` VARCHAR(255) COMMENT '部门描述',
    `del_flag`    TINYINT(1)   DEFAULT 0 COMMENT '部门是否存在：0-存在，1-删除',
    `create_time` DATETIME     COMMENT '部门创建时间',
    `update_time` DATETIME     COMMENT '部门最后更新时间'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='院系表';
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (101, '计算机科学与技术系', '负责计算机科学与技术相关专业的教学与研究。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (102, '电子信息工程学院', '涵盖电子信息工程及相关领域的教学与科研。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (103, '外语学院', '提供英语、法语、德语等多种外语专业的教育。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (104, '艺术设计学院', '专注于视觉传达设计、环境设计等艺术设计领域。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (105, '经济管理学院', '涵盖经济学、工商管理等专业的教学与研究。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (106, '法学院', '提供法学专业的教育，培养法律人才。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (107, '软件工程系', '专注于软件开发、测试及维护等方面的教学与研究。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');
INSERT INTO `db_department` (`id`, `name`, `description`, `del_flag`, `create_time`, `update_time`) VALUES (108, '信息安全系', '涵盖信息安全技术与管理的专业教育。', 0, '2025-08-24 20:35:44', '2025-08-24 20:35:44');


DROP TABLE IF EXISTS `db_teacher`;
CREATE TABLE `db_teacher` (
    `id`            int           NOT NULL AUTO_INCREMENT COMMENT '教师唯一标识ID',
    `teacher_code`  varchar(20)   NOT NULL COMMENT '教师工号，具有业务意义',
    `name`          varchar(50)   NOT NULL COMMENT '教师姓名',
    `gender`        int           NOT NULL DEFAULT '0' COMMENT '性别：0-未知，1-男，2-女',
    `age`           int unsigned  DEFAULT NULL COMMENT '年龄',
    `email`         varchar(100)  DEFAULT NULL COMMENT '电子邮箱',
    `phone`         varchar(20)   DEFAULT NULL COMMENT '手机号码',
    `department_id` int           DEFAULT NULL COMMENT '所属院系ID，关联院系表',
    `title`         varchar(50)   DEFAULT NULL COMMENT '职称（如：教授、副教授、讲师等）',
    `salary`        decimal(10,2) DEFAULT NULL COMMENT '薪资',
    `hire_date`     date          DEFAULT NULL COMMENT '入职日期',
    `is_full_time`  tinyint(1)    NOT NULL DEFAULT '1' COMMENT '是否全职：TRUE-全职，FALSE-兼职',
    `description`   text          COMMENT '教师描述或备注',
    `create_time`   datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间',
    `update_time`   datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录最后更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `idx_teacher_code` (`teacher_code`),
    KEY `idx_department_id` (`department_id`),
    KEY `idx_name` (`name`),
    KEY `idx_hire_date` (`hire_date`),
    KEY `idx_name_phone` (`name`,`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='教师信息表';
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (1, 'T2024001', '张伟', 1, 45, 'zhangwei@university.edu.cn', '13800138001', 101, '教授', 35000.00, '2010-08-15', 1, '计算机科学系主任，博士生导师，研究方向为人工智能。', '2025-08-24 20:35:44', '2025-10-02 13:07:24');
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (2, 'T2024002', '王芳', 2, 38, 'wangfang@university.edu.cn', '13900139002', 106, '副教授', 25000.00, '2015-03-01', 1, '主讲《数据结构》和《算法分析》，教学经验丰富。', '2025-08-24 20:35:44', '2025-10-02 13:07:25');
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (3, 'T2024003', '李建国', 1, 52, 'lijg@university.edu.cn', '13700137003', 102, '教授', 38000.00, '2005-07-20', 1, '电子信息工程学院资深教授，享受国务院特殊津贴。', '2025-08-24 20:35:44', '2025-10-02 13:07:29');
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (4, 'T2024004', '赵敏', 2, 29, 'zhaomin@university.edu.cn', '18600186004', 103, '讲师', 18000.00, '2021-09-01', 1, '新晋教师，外语学院英语系，口语流利。', '2025-08-24 20:35:44', '2025-10-02 13:07:32');
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (5, 'T2024005', '陈浩', 1, 40, 'chenhao@university.edu.cn', '15900159005', 107, '副教授', 26000.00, '2013-11-10', 1, '软件工程专业负责人，多次带领学生获得竞赛大奖。', '2025-08-24 20:35:44', '2025-10-02 13:07:34');
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (6, 'T2025006', '刘婷婷', 2, 33, 'liutt@university.edu.cn', '18800188006', 104, '讲师', 16000.00, '2022-02-15', 0, '艺术学院外聘讲师，著名设计师。', '2025-08-24 20:35:44', '2025-10-02 13:07:36');
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (7, 'T2025007', '孙耀', 1, 47, 'suny@university.edu.cn', '13300133007', 108, '教授', 34000.00, '2008-05-30', 1, '发表多篇顶级期刊论文，科研能力突出。', '2025-08-24 20:35:44', '2025-10-02 13:07:39');
INSERT INTO `db_teacher` (`id`, `teacher_code`, `name`, `gender`, `age`, `email`, `phone`, `department_id`, `title`, `salary`, `hire_date`, `is_full_time`, `description`, `create_time`, `update_time`) VALUES (8, 'T2025008', '周琪', 2, 31, 'zhouqi@university.edu.cn', '15600156008', 105, '助教', 12000.00, '2023-07-01', 1, '', '2025-08-24 20:35:44', '2025-10-02 13:07:40');


DROP TABLE IF EXISTS `db_course`;
CREATE TABLE db_course (
    `id`           INT          PRIMARY KEY AUTO_INCREMENT COMMENT '课程ID',
    `course_code`  VARCHAR(20)  NOT NULL UNIQUE COMMENT '课程代码',
    `course_name`  VARCHAR(100) NOT NULL COMMENT '课程名称',
    `credit`       DECIMAL(3,1) NOT NULL DEFAULT 1.0 COMMENT '学分',
    `hours`        INT          NOT NULL DEFAULT 36 COMMENT '课时数',
    `teacher_id`   INT          COMMENT '授课教师ID',
    `max_students` INT          DEFAULT 50 COMMENT '最大学生数',
    `description`  TEXT         COMMENT '课程描述',
    `status`       ENUM('ACTIVE', 'INACTIVE', 'ARCHIVED') DEFAULT 'ACTIVE' COMMENT '课程状态',
    `created_at`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX idx_course_code (course_code),
    INDEX idx_teacher_id (teacher_id),
    INDEX idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程表';
-- 插入课程数据
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('CS101', '计算机科学导论', 3.0, 54, 1001, 100, '计算机科学基础入门课程，涵盖编程基础、算法和数据结构', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('MATH201', '高等数学A', 4.0, 72, 1002, 80, '大学数学基础课程，包括微积分、线性代数和概率论', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('ENG101', '大学英语I', 2.0, 36, 1003, 60, '英语基础课程，注重听说读写综合能力培养', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('PHY301', '大学物理', 3.5, 54, 1004, 70, '物理学基础课程，涵盖力学、电磁学和热力学', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('CHEM202', '基础化学', 3.0, 54, 1005, 65, '化学原理和实验基础课程', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('HIST101', '中国近代史', 2.0, 36, 1006, 55, '中国近代历史发展脉络和重要事件', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('ART105', '艺术鉴赏', 1.5, 27, 1007, 40, '艺术理论和作品欣赏入门课程', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('PE001', '体育与健康', 1.0, 36, 1008, 120, '体育锻炼和健康知识课程', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('ECON301', '经济学原理', 3.0, 54, 1009, 75, '微观经济学和宏观经济学基础', 'ACTIVE');
INSERT INTO db_course (course_code, course_name, credit, hours, teacher_id, max_students, description, status) VALUES('PHIL201', '哲学导论', 2.5, 45, 1010, 50, '西方哲学思想和中国哲学传统介绍', 'ACTIVE');


DROP TABLE IF EXISTS `db_student_course`;
CREATE TABLE db_student_course (
    `id`            INT        PRIMARY KEY AUTO_INCREMENT COMMENT '记录ID',
    `student_id`    INT        NOT NULL COMMENT '学生ID',
    `course_id`     INT        NOT NULL COMMENT '课程ID',
    `academic_year` VARCHAR(9) NOT NULL COMMENT '学年，如: 2023-2024',
    `semester`      ENUM('SPRING', 'SUMMER', 'FALL', 'WINTER') NOT NULL COMMENT '学期',
    `score`         DECIMAL(5,2) COMMENT '成绩',
    `grade_point`   DECIMAL(3,2) COMMENT '绩点',
    `status`        ENUM('REGISTERED', 'IN_PROGRESS', 'COMPLETED', 'DROPPED', 'FAILED') DEFAULT 'REGISTERED' COMMENT '选课状态',
    `registered_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '选课时间',
    `completed_at`  TIMESTAMP NULL COMMENT '完成时间',
    `created_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',

    -- 复合主键（可选，如果不用自增id的话）
    -- PRIMARY KEY (student_id, course_id, academic_year, semester),

    UNIQUE KEY uk_student_course_semester (student_id, course_id, academic_year, semester),
    INDEX idx_student_id (student_id),
    INDEX idx_course_id (course_id),
    INDEX idx_academic_year (academic_year),
    INDEX idx_semester (semester),
    INDEX idx_status (status),
    FOREIGN KEY (student_id) REFERENCES db_student(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES db_course(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生选课表';
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(1, 1, '2023-2024', 'FALL', 85.5, 3.5, 'COMPLETED', '2023-09-01 10:00:00', '2024-01-15 00:00:00', NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(1, 2, '2023-2024', 'FALL', 92.0, 4.0, 'COMPLETED', '2023-09-01 10:30:00', '2024-01-15 00:00:00', NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(2, 1, '2023-2024', 'FALL', 78.0, 3.0, 'COMPLETED', '2023-09-02 09:15:00', '2024-01-15 00:00:00', NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(2, 3, '2023-2024', 'FALL', NULL, NULL, 'IN_PROGRESS', '2023-09-02 14:20:00', NULL, NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(3, 1, '2023-2024', 'FALL', 88.5, 3.7, 'COMPLETED', '2023-09-03 11:45:00', '2024-01-15 00:00:00', NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(3, 4, '2023-2024', 'FALL', 76.5, 2.8, 'COMPLETED', '2023-09-03 13:30:00', '2024-01-15 00:00:00', NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(4, 5, '2023-2024', 'FALL', 94.0, 4.0, 'COMPLETED', '2023-09-04 08:45:00', '2024-01-15 00:00:00', NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(4, 6, '2023-2024', 'FALL', 81.0, 3.2, 'COMPLETED', '2023-09-04 10:20:00', '2024-01-15 00:00:00', NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(5, 7, '2023-2024', 'FALL', NULL, NULL, 'REGISTERED', '2023-09-05 15:30:00', NULL, NOW(), NOW());
INSERT INTO db_student_course (student_id, course_id, academic_year, semester, score, grade_point, status, registered_at, completed_at, created_at, updated_at) VALUES(5, 8, '2023-2024', 'FALL', 79.5, 3.0, 'COMPLETED', '2023-09-05 16:45:00', '2024-01-15 00:00:00', NOW(), NOW());


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
