DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`(
    `Id`          bigint       NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `brand_name`  varchar(255) DEFAULT NULL COMMENT '品牌名称',
    `racking`     int          DEFAULT NULL COMMENT '是否上架：0为true即上架,1为false下架',
    `delete_flag` int          DEFAULT NULL COMMENT '逻辑删除：0存在,1删除',
    `remark`      varchar(500) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`Id`),
    UNIQUE KEY `品牌名称唯一索引` (`brand_name`) USING BTREE COMMENT '品牌名称索引'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='品牌表';
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (1, 'Apple', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (2, 'SANNAING', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (3, '华为', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (4, 'vivo', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (5, 'OPPO', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (6, '小米', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (7, 'SAMSUNG', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (8, '努比亚（Nubia）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (9, '荣耀（HONOR）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (10, '朵唯（DOOV）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (11, '联想（Lenovo）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (12, '海语（HAIYU）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (13, '中兴（ZTE）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (14, '乐视TV', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (15, '黑鲨', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (16, '华硕（ASUS）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (17, '神舟（Hasee）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (18, '戴睿（DERE）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (19, '微软（Microsoft）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (20, '玩家国度（ROG）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (21, '雷蛇（Razer）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (22, '机械革命（MECHREVO）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (23, '摆渡者（）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (24, '海尔（Haier）', 0, 0, NULL);
INSERT INTO `brand` (`id`, `brand_name`, `racking`, `delete_flag`, `remark`) VALUES (25, '豪吉利（HaoGeely）', 0, 0, NULL);


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
    PRIMARY KEY (`cid`) USING BTREE,
    KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';
INSERT INTO `courses` (`cid`, `code`, `cname`, `zyname`, `teacher`, `tclass`, `delete_flag`) VALUES (1, 1001, 'Java', '计科', '钱二', '101', 0);
INSERT INTO `courses` (`cid`, `code`, `cname`, `zyname`, `teacher`, `tclass`, `delete_flag`) VALUES (2, 1002, '数据库', '计科', '王五', '102', 0);
INSERT INTO `courses` (`cid`, `code`, `cname`, `zyname`, `teacher`, `tclass`, `delete_flag`) VALUES (3, 1003, '高数', '软工', '王五', '103', 0);
INSERT INTO `courses` (`cid`, `code`, `cname`, `zyname`, `teacher`, `tclass`, `delete_flag`) VALUES (4, 1004, '线代', '软工', '赵六', '104', 0);
INSERT INTO `courses` (`cid`, `code`, `cname`, `zyname`, `teacher`, `tclass`, `delete_flag`) VALUES (5, 1005, 'python', '软工', '钱二', '105', 0);
INSERT INTO `courses` (`cid`, `code`, `cname`, `zyname`, `teacher`, `tclass`, `delete_flag`) VALUES (6, 1006, '英语', '计科', '赵六', '106', 0);
INSERT INTO `courses` (`cid`, `code`, `cname`, `zyname`, `teacher`, `tclass`, `delete_flag`) VALUES (7, 1007, '离散数学', '计科', '王五', '107', 0);


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
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (1, 1, 1001, '学生', 1, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (2, 1, 1001, '学生', 3, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (3, 3, 1003, '学生', 2, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (4, 3, 1002, '学生', 5, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (5, 2, 1001, '老师', 1, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (6, 2, 1005, '老师', 4, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (7, 3, 1005, '学生', 4, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (8, 1, 1006, '学生', 2, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (9, 6, 1006, '老师', 2, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (10, 5, 1002, '老师', 5, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (11, 5, 1003, '老师', 2, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (12, 5, 1007, '老师', 2, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (13, 2, 1001, '老师', 3, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (14, 1, 1002, '学生', 5, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (15, 5, 1002, '老师', 5, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (16, 1, 1006, '学生', 4, now(), now(), 0);
INSERT INTO `time_table` (`id`, `user_id`, `course_id`, `tenant`, `week`, `start`, `finish`, `delete_flag`) VALUES (17, 6, 1006, '老师', 4, now(), now(), 0);


DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
    `id`               bigint NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `category_name`    varchar(100) NOT NULL COMMENT '类别名称',
    `category_name_en` varchar(100) DEFAULT NULL COMMENT '类别英文名称',
    `delete_flag`      tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除：0存在,1删除',
    `remark`           varchar(255) DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品类别表';
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (1, '手机', 'Phone', 0, NULL);
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (2, '笔记本', 'Work-Book', 0, NULL);
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (3, '数码', 'Digital', 0, NULL);
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (4, '平板', 'Flat Panel', 0, NULL);
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (5, '家电', 'Home Appliances', 0, NULL);
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (6, '奢侈品', 'Luxury', 0, NULL);
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (7, '图书', 'Books', 0, NULL);
INSERT INTO `category` (`id`, `category_name`, `category_name_en`, `delete_flag`, `remark`) VALUES (8, '其他', 'Other', 0, NULL);


DROP TABLE IF EXISTS `sku`;
CREATE TABLE `sku` (
    `id`       int NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `spu_id`   int NOT NULL COMMENT 'spu的id',
    `img`      varchar(255) DEFAULT NULL COMMENT '图片地址',
    `title`    varchar(100) NOT NULL COMMENT '标题',
    `price`    decimal(10,2) NOT NULL COMMENT '销售价格，单位为分',
    `own_spec` json DEFAULT NULL COMMENT 'sku的特有规格参数键值对，json格式',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='商品SKU表';
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (1, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01T5KdlE2J4b8P5iEax_!!2206550199368-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 1070.00, '{\"颜色\": \"正品5G手机【远峰蓝色】\", \"机身内存\": \"【i14pro】灵动屏16+512G大内存送充动屏电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (2, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01T5KdlE2J4b8P5iEax_!!2206550199368-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 870.00, '{\"颜色\": \"正品5G手机【远峰蓝色】\", \"机身内存\": \"【i14pro】灵动屏16+256G大内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (3, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01T5KdlE2J4b8P5iEax_!!2206550199368-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 670.00, '{\"颜色\": \"正品5G手机【远峰蓝色】\", \"机身内存\": \"【i14pro】灵动屏10+128G大内存\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (4, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01CsHk282J4b8LNIHyH_!!2206550199368-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 1070.00, '{\"颜色\": \"正品5G手机【香槟金】\", \"机身内存\": \"【i14pro】灵动屏16+512G大内存送充动屏电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (5, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01CsHk282J4b8LNIHyH_!!2206550199368-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 870.00, '{\"颜色\": \"正品5G手机【香槟金】\", \"机身内存\": \"【i14pro】灵动屏16+256G大内存送充动屏电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (6, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01CsHk282J4b8LNIHyH_!!2206550199368-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 670.00, '{\"颜色\": \"正品5G手机【香槟金】\", \"机身内存\": \"【i14pro】灵动屏10+128G大内存\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (7, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01EjdPeM1Bs2nOHt4zv_!!0-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 1070.00, '{\"颜色\": \"正品5G手机【暗夜紫】\", \"机身内存\": \"【i14pro】灵动屏16+512G大内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (8, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01EjdPeM1Bs2nOHt4zv_!!0-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 870.00, '{\"颜色\": \"正品5G手机【暗夜紫】\", \"机身内存\": \"【i14pro】灵动屏16+256G大内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (9, 1, 'https://cbu01.alicdn.com/img/ibank/O1CN01EjdPeM1Bs2nOHt4zv_!!0-0-cib.jpg', '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 670.00, '{\"颜色\": \"正品5G手机【暗夜紫】\", \"机身内存\": \"【i14pro】灵动屏10+128G大内存\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (10, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN016gZH541FLlKMLaVwq_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 1348.00, '{\"颜色\": \"天空之境【12G运行】\", \"机身内存\": \"512G内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (11, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN016gZH541FLlKMLaVwq_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 1048.00, '{\"颜色\": \"天空之境【12G运行】\", \"机身内存\": \"256G内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (12, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN016gZH541FLlKMLaVwq_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 748.00, '{\"颜色\": \"天空之境【12G运行】\", \"机身内存\": \"128G内存\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (13, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN01QYjCxN1FLlKXmZWtu_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 1348.00, '{\"颜色\": \"幻境黑【12G运行】\", \"机身内存\": \"512G内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (14, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN01QYjCxN1FLlKXmZWtu_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 1048.00, '{\"颜色\": \"幻境黑【12G运行】\", \"机身内存\": \"256G内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (15, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN01QYjCxN1FLlKXmZWtu_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 748.00, '{\"颜色\": \"幻境黑【12G运行】\", \"机身内存\": \"128G内存\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (16, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN01cE1DjJ1FLlKXmXWAy_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 1348.00, '{\"颜色\": \"落日黄【12G运行】\", \"机身内存\": \"512G内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (17, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN01cE1DjJ1FLlKXmXWAy_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 1048.00, '{\"颜色\": \"落日黄【12G运行】\", \"机身内存\": \"256G内存送充电宝\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (18, 2, 'https://cbu01.alicdn.com/img/ibank/O1CN01cE1DjJ1FLlKXmXWAy_!!2214652160471-0-cib.jpg', '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 748.00, '{\"颜色\": \"落日黄【12G运行】\", \"机身内存\": \"128G内存\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (19, 3, 'https://img10.360buyimg.com/n7/jfs/t1/108811/15/23805/66166/636896f9E97c2995d/0b2b1a3e1d16a740.jpg', '【现货速发】华为畅享20Plus 5G全网通手机256GB', 1988.00, '{\"颜色\": \"【优畅享50Plus】雅致黑\", \"机身内存\": \"8+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (20, 3, 'https://img10.360buyimg.com/n7/jfs/t1/108811/15/23805/66166/636896f9E97c2995d/0b2b1a3e1d16a740.jpg', '【现货速发】华为畅享20Plus 5G全网通手机256GB', 2377.00, '{\"颜色\": \"【优畅享50Plus】雅致黑\", \"机身内存\": \"8+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (21, 3, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/142271/39/34263/38426/63f08d60F6c6632a1/131b3314c71bc523.jpg', '【现货速发】华为畅享20Plus 5G全网通手机256GB', 1988.00, '{\"颜色\": \"【优畅享50Plus】海雾蓝\", \"机身内存\": \"8+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (22, 3, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/142271/39/34263/38426/63f08d60F6c6632a1/131b3314c71bc523.jpg', '【现货速发】华为畅享20Plus 5G全网通手机256GB', 2377.00, '{\"颜色\": \"【优畅享50Plus】海雾蓝\", \"机身内存\": \"8+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (23, 3, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/196640/17/32264/18606/63f08d4dF2f06207a/690c9107c89c5e89.jpg', '【现货速发】华为畅享20Plus 5G全网通手机256GB', 1988.00, '{\"颜色\": \"【优畅享50Plus】珠贝白\", \"机身内存\": \"8+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (24, 3, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/196640/17/32264/18606/63f08d4dF2f06207a/690c9107c89c5e89.jpg', '【现货速发】华为畅享20Plus 5G全网通手机256GB', 2377.00, '{\"颜色\": \"【优畅享50Plus】珠贝白\", \"机身内存\": \"8+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (25, 4, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/220035/4/23494/31691/637af278E84b67cd7/007e2a7174a05cf2.jpg', 'vivo X90 8GB+256GB 至黑 4nm天玑9200旗舰芯片 自研芯片V2 120W双芯闪充 蔡司影像 5G 拍照 手机', 3999.00, '{\"颜色\": \"至黑\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (26, 4, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/220035/4/23494/31691/637af278E84b67cd7/007e2a7174a05cf2.jpg', 'vivo X90 8GB+256GB 至黑 4nm天玑9200旗舰芯片 自研芯片V2 120W双芯闪充 蔡司影像 5G 拍照 手机', 4499.00, '{\"颜色\": \"至黑\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (27, 4, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/179865/15/30701/44504/637c541bEdd9c5408/77a07a8bbccd3f1b.jpg', 'vivo X90 8GB+256GB 至黑 4nm天玑9200旗舰芯片 自研芯片V2 120W双芯闪充 蔡司影像 5G 拍照 手机', 3999.00, '{\"颜色\": \"冰蓝\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (28, 4, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/179865/15/30701/44504/637c541bEdd9c5408/77a07a8bbccd3f1b.jpg', 'vivo X90 8GB+256GB 至黑 4nm天玑9200旗舰芯片 自研芯片V2 120W双芯闪充 蔡司影像 5G 拍照 手机', 4499.00, '{\"颜色\": \"冰蓝\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (29, 4, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/50585/12/18312/94447/637af31fE1713f66b/e7d69b35ef02b0d1.jpg', 'vivo X90 8GB+256GB 至黑 4nm天玑9200旗舰芯片 自研芯片V2 120W双芯闪充 蔡司影像 5G 拍照 手机', 3999.00, '{\"颜色\": \"华夏红\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (30, 4, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/50585/12/18312/94447/637af31fE1713f66b/e7d69b35ef02b0d1.jpg', 'vivo X90 8GB+256GB 至黑 4nm天玑9200旗舰芯片 自研芯片V2 120W双芯闪充 蔡司影像 5G 拍照 手机', 4499.00, '{\"颜色\": \"华夏红\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (31, 5, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/161308/14/32753/38707/63738c87E201553af/3cf66104aa7b1b0c.jpg', 'OPPO Reno9 水光人像镜头 120Hz 超清曲面屏 4500mAh大电池 轻薄机身 5G手机', 2499.00, '{\"颜色\": \"明日金\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (32, 5, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/161308/14/32753/38707/63738c87E201553af/3cf66104aa7b1b0c.jpg', 'OPPO Reno9 水光人像镜头 120Hz 超清曲面屏 4500mAh大电池 轻薄机身 5G手机', 2699.00, '{\"颜色\": \"明日金\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (33, 5, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/186375/20/28337/15376/63738cc9E5a68bd70/5fedc8c498a474c5.jpg', 'OPPO Reno9 水光人像镜头 120Hz 超清曲面屏 4500mAh大电池 轻薄机身 5G手机', 2499.00, '{\"颜色\": \"微醺\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (34, 5, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/186375/20/28337/15376/63738cc9E5a68bd70/5fedc8c498a474c5.jpg', 'OPPO Reno9 水光人像镜头 120Hz 超清曲面屏 4500mAh大电池 轻薄机身 5G手机', 2699.00, '{\"颜色\": \"微醺\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (35, 5, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/187945/29/30615/28642/637f1513E4c76c932/661c9d2813aab376.jpg', 'OPPO Reno9 水光人像镜头 120Hz 超清曲面屏 4500mAh大电池 轻薄机身 5G手机', 2499.00, '{\"颜色\": \"万事红\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (36, 5, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/187945/29/30615/28642/637f1513E4c76c932/661c9d2813aab376.jpg', 'OPPO Reno9 水光人像镜头 120Hz 超清曲面屏 4500mAh大电池 轻薄机身 5G手机', 2699.00, '{\"颜色\": \"万事红\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (37, 6, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/3808/10/19982/37488/6336c1f7E30e2058b/8e5f04fd32dae511.jpg', '小米12S Pro 5G手机 8GB+256GB 黑色 全网通5G', 4799.00, '{\"颜色\": \"紫色\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (38, 6, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/3808/10/19982/37488/6336c1f7E30e2058b/8e5f04fd32dae511.jpg', '小米12S Pro 5G手机 8GB+256GB 黑色 全网通5G', 5189.00, '{\"颜色\": \"紫色\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (39, 6, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/39345/19/19365/48435/6336c210Eb5b4a516/bc6cbf282752ac37.jpg', '小米12S Pro 5G手机 8GB+256GB 黑色 全网通5G', 4799.00, '{\"颜色\": \"原野绿\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (40, 6, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/39345/19/19365/48435/6336c210Eb5b4a516/bc6cbf282752ac37.jpg', '小米12S Pro 5G手机 8GB+256GB 黑色 全网通5G', 5189.00, '{\"颜色\": \"原野绿\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (41, 6, 'https://img13.360buyimg.com/n1/s450x450_jfs/t1/142353/29/29727/22336/6336c1dbE08f77a3f/73885395df40196f.jpg', '小米12S Pro 5G手机 8GB+256GB 黑色 全网通5G', 4799.00, '{\"颜色\": \"黑色\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (42, 6, 'https://img13.360buyimg.com/n1/s450x450_jfs/t1/142353/29/29727/22336/6336c1dbE08f77a3f/73885395df40196f.jpg', '小米12S Pro 5G手机 8GB+256GB 黑色 全网通5G', 5189.00, '{\"颜色\": \"黑色\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (43, 7, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/82735/22/23295/68083/6347deb5E661f1a2c/a8dffc5a830574d6.jpg', 'SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计', 3599.00, '{\"颜色\": \"雾松绿\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (44, 7, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/82735/22/23295/68083/6347deb5E661f1a2c/a8dffc5a830574d6.jpg', 'SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计', 4999.00, '{\"颜色\": \"雾松绿\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (45, 7, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/110631/13/31749/66208/6347df40E96d95a79/01eff52ecce80900.jpg', 'SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计', 3599.00, '{\"颜色\": \"浮光粉\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (46, 7, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/110631/13/31749/66208/6347df40E96d95a79/01eff52ecce80900.jpg', 'SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计', 4999.00, '{\"颜色\": \"浮光粉\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (47, 7, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/120303/32/32061/51244/6347df7cE86aa1423/cbd57d95918b1c3d.jpg', 'SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计', 3599.00, '{\"颜色\": \"幽紫秘境\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (48, 7, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/120303/32/32061/51244/6347df7cE86aa1423/cbd57d95918b1c3d.jpg', 'SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计', 4999.00, '{\"颜色\": \"幽紫秘境\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (49, 8, 'https://img14.360buyimg.com/n1/jfs/t1/91036/27/30612/69834/640594f9F625a4a3b/aa1ee50c05d1f330.jpg', 'nubia 努比亚 Z50 Ultra 屏下摄像8GB+256GB 夜海 第二代骁龙8 35mm+85mm黄金双焦段定制光学 5G手机游戏拍照', 3999.00, '{\"颜色\": \"夜海\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (50, 8, 'https://img14.360buyimg.com/n1/jfs/t1/91036/27/30612/69834/640594f9F625a4a3b/aa1ee50c05d1f330.jpg', 'nubia 努比亚 Z50 Ultra 屏下摄像8GB+256GB 夜海 第二代骁龙8 35mm+85mm黄金双焦段定制光学 5G手机游戏拍照', 4299.00, '{\"颜色\": \"夜海\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (51, 8, 'https://img10.360buyimg.com/n1/jfs/t1/128793/18/30856/66393/64098c44F0e289ab9/eb2120e7ca0400b3.jpg', 'nubia 努比亚 Z50 Ultra 屏下摄像8GB+256GB 夜海 第二代骁龙8 35mm+85mm黄金双焦段定制光学 5G手机游戏拍照', 3999.00, '{\"颜色\": \"敦煌\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (52, 8, 'https://img10.360buyimg.com/n1/jfs/t1/128793/18/30856/66393/64098c44F0e289ab9/eb2120e7ca0400b3.jpg', 'nubia 努比亚 Z50 Ultra 屏下摄像8GB+256GB 夜海 第二代骁龙8 35mm+85mm黄金双焦段定制光学 5G手机游戏拍照', 4299.00, '{\"颜色\": \"敦煌\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (53, 8, 'https://img12.360buyimg.com/n1/jfs/t1/65089/28/18400/265289/64098c46Fa6145ba3/46f7f9cc3f898c1a.jpg', 'nubia 努比亚 Z50 Ultra 屏下摄像8GB+256GB 夜海 第二代骁龙8 35mm+85mm黄金双焦段定制光学 5G手机游戏拍照', 3999.00, '{\"颜色\": \"星空典藏版\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (54, 8, 'https://img12.360buyimg.com/n1/jfs/t1/65089/28/18400/265289/64098c46Fa6145ba3/46f7f9cc3f898c1a.jpg', 'nubia 努比亚 Z50 Ultra 屏下摄像8GB+256GB 夜海 第二代骁龙8 35mm+85mm黄金双焦段定制光学 5G手机游戏拍照', 4299.00, '{\"颜色\": \"星空典藏版\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (55, 9, 'https://img13.360buyimg.com/n1/s450x450_jfs/t1/151180/17/26534/25077/634663deEddadb847/32d1030259655c49.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 1599.00, '{\"颜色\": \"墨玉青\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (56, 9, 'https://img13.360buyimg.com/n1/s450x450_jfs/t1/151180/17/26534/25077/634663deEddadb847/32d1030259655c49.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 1789.00, '{\"颜色\": \"墨玉青\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (57, 9, 'https://img13.360buyimg.com/n1/s450x450_jfs/t1/151180/17/26534/25077/634663deEddadb847/32d1030259655c49.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 2089.00, '{\"颜色\": \"墨玉青\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (58, 9, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/167705/34/30879/20456/63466358Ea0d205f5/11ef85aa635310b5.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 1599.00, '{\"颜色\": \"彩云追月\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (59, 9, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/167705/34/30879/20456/63466358Ea0d205f5/11ef85aa635310b5.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 1789.00, '{\"颜色\": \"彩云追月\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (60, 9, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/167705/34/30879/20456/63466358Ea0d205f5/11ef85aa635310b5.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 2089.00, '{\"颜色\": \"彩云追月\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (61, 9, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/204491/26/28505/55849/63917446Eab4cdaa9/b1f79c65dc9ac41f.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 1599.00, '{\"颜色\": \"琥珀星光\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (62, 9, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/204491/26/28505/55849/63917446Eab4cdaa9/b1f79c65dc9ac41f.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 1789.00, '{\"颜色\": \"琥珀星光\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (63, 9, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/204491/26/28505/55849/63917446Eab4cdaa9/b1f79c65dc9ac41f.jpg', '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 2089.00, '{\"颜色\": \"琥珀星光\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (64, 10, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/185239/28/30894/31197/6395fc51E50d2f203/8e6fb30d5bd19c89.jpg', '朵唯（DOOV）X60 Pro智能手机游戏超薄可用5G移动联通电信卡老人老年机长续航百元便宜学生备用', 499.00, '{\"颜色\": \"远峰蓝\", \"机身内存\": \"旗舰八核64G\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (65, 10, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/185239/28/30894/31197/6395fc51E50d2f203/8e6fb30d5bd19c89.jpg', '朵唯（DOOV）X60 Pro智能手机游戏超薄可用5G移动联通电信卡老人老年机长续航百元便宜学生备用', 579.00, '{\"颜色\": \"远峰蓝\", \"机身内存\": \"旗舰八核128G\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (66, 10, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/41521/11/22651/29844/63abe2bdF4e31cd69/efa46b6144b82287.jpg', '朵唯（DOOV）X60 Pro智能手机游戏超薄可用5G移动联通电信卡老人老年机长续航百元便宜学生备用', 499.00, '{\"颜色\": \"冰峰白\", \"机身内存\": \"旗舰八核64G\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (67, 10, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/41521/11/22651/29844/63abe2bdF4e31cd69/efa46b6144b82287.jpg', '朵唯（DOOV）X60 Pro智能手机游戏超薄可用5G移动联通电信卡老人老年机长续航百元便宜学生备用', 579.00, '{\"颜色\": \"冰峰白\", \"机身内存\": \"旗舰八核128G\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (68, 11, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/116288/11/28051/11015/62fdeaabEfd2ec1a6/36809afba306b54c.jpg', '联想拯救者Y70 高性能游戏电竞商务智能学生手机 Y70', 2099.00, '{\"颜色\": \"Y70 白\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (69, 11, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/116288/11/28051/11015/62fdeaabEfd2ec1a6/36809afba306b54c.jpg', '联想拯救者Y70 高性能游戏电竞商务智能学生手机 Y70', 2499.00, '{\"颜色\": \"Y70 白\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (70, 11, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/116288/11/28051/11015/62fdeaabEfd2ec1a6/36809afba306b54c.jpg', '联想拯救者Y70 高性能游戏电竞商务智能学生手机 Y70', 2999.00, '{\"颜色\": \"Y70 白\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (71, 11, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/76134/6/21563/12545/62fdebbfEa1150be2/ad3c8887a238f9f0.jpg', '联想拯救者Y70 高性能游戏电竞商务智能学生手机 Y70', 2099.00, '{\"颜色\": \"Y70 灰\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (72, 11, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/76134/6/21563/12545/62fdebbfEa1150be2/ad3c8887a238f9f0.jpg', '联想拯救者Y70 高性能游戏电竞商务智能学生手机 Y70', 2499.00, '{\"颜色\": \"Y70 灰\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (73, 11, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/76134/6/21563/12545/62fdebbfEa1150be2/ad3c8887a238f9f0.jpg', '联想拯救者Y70 高性能游戏电竞商务智能学生手机 Y70', 2999.00, '{\"颜色\": \"Y70 灰\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (74, 11, 'https://img13.360buyimg.com/n1/s450x450_jfs/t1/190688/27/27439/23470/62fded0aE8dd6bd6e/4b321d2bf576fdc0.jpg', '联想拯救者Y70 高性能游戏电竞商务智能学生手机 Y70', 2999.00, '{\"颜色\": \"Y70 红\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (75, 12, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/180976/22/21215/97362/6127fa28E9de238fc/5b7d9357b78ece83.jpg', '海语(HAIYU) x12升级版大电量大屏幕4G全网通指纹人脸双卡微信视频学生老人智能手机', 368.00, '{\"颜色\": \"天空之境\", \"机身内存\": \"4GB+32GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (76, 12, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/180976/22/21215/97362/6127fa28E9de238fc/5b7d9357b78ece83.jpg', '海语(HAIYU) x12升级版大电量大屏幕4G全网通指纹人脸双卡微信视频学生老人智能手机', 428.00, '{\"颜色\": \"天空之境\", \"机身内存\": \"4GB+64GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (77, 12, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/200171/7/4917/103719/6127fa28E1257e112/43c99fa2b8bdf896.jpg', '海语(HAIYU) x12升级版大电量大屏幕4G全网通指纹人脸双卡微信视频学生老人智能手机', 368.00, '{\"颜色\": \"湖光山色\", \"机身内存\": \"4GB+32GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (78, 12, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/200171/7/4917/103719/6127fa28E1257e112/43c99fa2b8bdf896.jpg', '海语(HAIYU) x12升级版大电量大屏幕4G全网通指纹人脸双卡微信视频学生老人智能手机', 428.00, '{\"颜色\": \"湖光山色\", \"机身内存\": \"4GB+64GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (79, 12, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/195441/13/20131/360212/6127f480E79753330/27adf4a2eb662882.png', '海语(HAIYU) x12升级版大电量大屏幕4G全网通指纹人脸双卡微信视频学生老人智能手机', 368.00, '{\"颜色\": \"墨色雅士\", \"机身内存\": \"4GB+32GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (80, 12, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/195441/13/20131/360212/6127f480E79753330/27adf4a2eb662882.png', '海语(HAIYU) x12升级版大电量大屏幕4G全网通指纹人脸双卡微信视频学生老人智能手机', 428.00, '{\"颜色\": \"墨色雅士\", \"机身内存\": \"4GB+64GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (81, 13, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/189070/9/33154/93491/63fee9dcF75da2bec/cde766a437129f95.jpg', '中兴Axon 40 Pro 吴京代言 高通骁龙870 一亿像素高清影像 144HZ屏66W快充 幻夜黑 8+128GB 官方标配', 1698.00, '{\"颜色\": \"幻夜黑\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (82, 13, 'https://img10.360buyimg.com/n1/s450x450_jfs/t1/126476/23/34708/105736/63fee9caFbcf8f9b1/e353e83f73700126.jpg', '中兴Axon 40 Pro 吴京代言 高通骁龙870 一亿像素高清影像 144HZ屏66W快充 幻夜黑 8+128GB 官方标配', 1698.00, '{\"颜色\": \"晶雾蓝\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (83, 13, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/144795/8/29001/106513/63fee9caFf70fdfdb/018a490d77d32cca.jpg', '中兴Axon 40 Pro 吴京代言 高通骁龙870 一亿像素高清影像 144HZ屏66W快充 幻夜黑 8+128GB 官方标配', 1698.00, '{\"颜色\": \"星光橙\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (84, 14, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/142557/19/33933/91029/63ea0103Fa0f4b8a5/dfeb586378c94b90.jpg', '乐视Letv Y2Pro八核智能手机自营 128GB超薄大屏游戏学生百元老人机全网通4G 移动联通电信 长续航', 699.00, '{\"颜色\": \"电光蓝\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (85, 14, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/142557/19/33933/91029/63ea0103Fa0f4b8a5/dfeb586378c94b90.jpg', '乐视Letv Y2Pro八核智能手机自营 128GB超薄大屏游戏学生百元老人机全网通4G 移动联通电信 长续航', 899.00, '{\"颜色\": \"电光蓝\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (86, 14, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/195291/13/32991/77606/63ea0103F66474f15/758ccfd004478483.jpg', '乐视Letv Y2Pro八核智能手机自营 128GB超薄大屏游戏学生百元老人机全网通4G 移动联通电信 长续航', 699.00, '{\"颜色\": \"夏日橙\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (87, 14, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/195291/13/32991/77606/63ea0103F66474f15/758ccfd004478483.jpg', '乐视Letv Y2Pro八核智能手机自营 128GB超薄大屏游戏学生百元老人机全网通4G 移动联通电信 长续航', 899.00, '{\"颜色\": \"夏日橙\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (88, 14, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/33351/12/19294/71143/63ea0103Fdfc46094/cef71a267bd0b751.jpg', '乐视Letv Y2Pro八核智能手机自营 128GB超薄大屏游戏学生百元老人机全网通4G 移动联通电信 长续航', 699.00, '{\"颜色\": \"幻夜黑\", \"机身内存\": \"8GB+128GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (89, 14, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/33351/12/19294/71143/63ea0103Fdfc46094/cef71a267bd0b751.jpg', '乐视Letv Y2Pro八核智能手机自营 128GB超薄大屏游戏学生百元老人机全网通4G 移动联通电信 长续航', 899.00, '{\"颜色\": \"幻夜黑\", \"机身内存\": \"8GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (90, 15, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/112593/18/27433/111832/62d8dd80E1dd35d3a/3517ef488dac828b.jpg', '黑鲨5pro 游戏5G手机 中国航天版 16+512G全网通', 4459.00, '{\"颜色\": \"陨石黑\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (91, 15, 'https://img14.360buyimg.com/n1/s450x450_jfs/t1/112593/18/27433/111832/62d8dd80E1dd35d3a/3517ef488dac828b.jpg', '黑鲨5pro 游戏5G手机 中国航天版 16+512G全网通', 5889.00, '{\"颜色\": \"陨石黑\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (92, 15, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/24715/19/17517/128003/62d8dda2E14948379/ca79823aeabdae36.jpg', '黑鲨5pro 游戏5G手机 中国航天版 16+512G全网通', 4459.00, '{\"颜色\": \"天宫白\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (93, 15, 'https://img12.360buyimg.com/n1/s450x450_jfs/t1/24715/19/17517/128003/62d8dda2E14948379/ca79823aeabdae36.jpg', '黑鲨5pro 游戏5G手机 中国航天版 16+512G全网通', 5889.00, '{\"颜色\": \"天宫白\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (94, 15, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/28634/8/17844/126283/62d8ddc8Ece6065f6/d71d890d0ee4bf8f.jpg', '黑鲨5pro 游戏5G手机 中国航天版 16+512G全网通', 4459.00, '{\"颜色\": \"中国航天版\", \"机身内存\": \"12GB+256GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (95, 15, 'https://img11.360buyimg.com/n1/s450x450_jfs/t1/28634/8/17844/126283/62d8ddc8Ece6065f6/d71d890d0ee4bf8f.jpg', '黑鲨5pro 游戏5G手机 中国航天版 16+512G全网通', 5889.00, '{\"颜色\": \"中国航天版\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (96, 16, 'https://gd2.alicdn.com/imgextra/i2/2215206508116/O1CN01OTpni429pBHO7TtRk_!!2215206508116.jpg_400x400.jpg', 'Lenovo/联想笔记本电脑i7轻薄便携大学生上网课超薄办公游戏本', 1510.00, '{\"颜色\": \"联想套餐0  i5处理器  8g内存  固态256G硬盘    12.5寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (97, 16, 'https://gd3.alicdn.com/imgextra/i3/2215206508116/O1CN01XQmrFo29pBHKUZna1_!!2215206508116.jpg_400x400.jpg', 'Lenovo/联想笔记本电脑i7轻薄便携大学生上网课超薄办公游戏本', 2681.00, '{\"颜色\": \"联想游戏套餐3  i7 8g 固态512G 独立512MB 14寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (98, 16, 'https://gd4.alicdn.com/imgextra/i4/2215206508116/O1CN016b9FHc29pBHMx86Xy_!!2215206508116.jpg_400x400.jpg', 'Lenovo/联想笔记本电脑i7轻薄便携大学生上网课超薄办公游戏本', 3381.00, '{\"颜色\": \"联想游戏套餐6  i7 16g 固态512G 独立2G 15.6寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (99, 17, 'https://gd3.alicdn.com/imgextra/i3/4141530666/O1CN01NErwCR1Gn4bCVWoGG_!!4141530666.jpg_400x400.jpg', 'i7苹果笔记本电脑MacBook Pro超薄Air办公游戏本轻薄手提2023新款', 2480.00, '{\"颜色\": \"苹果套餐1：8GB内存 固态512GB硬盘 处理器p755高配 13.3寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (100, 17, 'https://gd1.alicdn.com/imgextra/i1/4141530666/O1CN01oxEAGR1Gn4bFx8gxE_!!4141530666.jpg_400x400.jpg', 'i7苹果笔记本电脑MacBook Pro超薄Air办公游戏本轻薄手提2023新款', 3380.00, '{\"颜色\": \"苹果15寸Pro套餐3：i7顶配  8GB内存  固态512GB  独立显卡 15寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (101, 17, 'https://gd3.alicdn.com/imgextra/i3/4141530666/O1CN01dNERx61Gn4bC7oMXr_!!4141530666.jpg_400x400.jpg', 'i7苹果笔记本电脑MacBook Pro超薄Air办公游戏本轻薄手提2023新款', 4680.00, '{\"颜色\": \"苹果15寸Pro套餐6：i7高级处理器  16GB内存  固态1TB硬盘 独立1GB显卡 15寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (102, 17, 'https://gd1.alicdn.com/imgextra/i1/4141530666/O1CN01JCqgDR1Gn4bGbt6C3_!!4141530666.jpg_400x400.jpg', 'i7苹果笔记本电脑MacBook Pro超薄Air办公游戏本轻薄手提2023新款', 6980.00, '{\"颜色\": \"外星人吃鸡游戏套餐16：i7顶配 +32G内存+固态1TB硬盘+独立8GB 吃鸡游戏显卡+18寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (103, 18, 'https://gw.alicdn.com/bao/uploaded/i2/2208565043688/O1CN01AQryNC1d79TGFyYYw_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 4479.00, '{\"颜色\": \"【颜值推荐】a豆14白//i7-1165G7/100%高色域 /背光键盘\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (104, 18, 'https://gw.alicdn.com/bao/uploaded/i2/2208565043688/O1CN01AQryNC1d79TGFyYYw_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 4979.00, '{\"颜色\": \"【颜值推荐】a豆14白//i7-1165G7/100%高色域 /背光键盘\", \"机身内存\": \"16GB+1T\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (105, 18, 'https://gw.alicdn.com/bao/uploaded/i4/2208565043688/O1CN01dRqa681d79TwZ5WF5_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 4479.00, '{\"颜色\": \"【推荐】a豆14薄荷/i5-12500H标压/背光键盘\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (106, 18, 'https://gw.alicdn.com/bao/uploaded/i4/2208565043688/O1CN01dRqa681d79TwZ5WF5_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 4979.00, '{\"颜色\": \"【推荐】a豆14薄荷/i5-12500H标压/背光键盘\", \"机身内存\": \"16GB+1T\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (107, 18, 'https://gw.alicdn.com/bao/uploaded/i4/2208565043688/O1CN01aMYgzr1d79UM4su70_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 3779.00, '{\"颜色\": \"【大屏幕】无畏16蓝 R5-5600H标压/180°开合\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (108, 18, 'https://gw.alicdn.com/bao/uploaded/i4/2208565043688/O1CN01aMYgzr1d79UM4su70_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 4279.00, '{\"颜色\": \"【大屏幕】无畏16蓝 R5-5600H标压/180°开合\", \"机身内存\": \"16GB+1T\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (109, 18, 'https://gw.alicdn.com/bao/uploaded/i4/2208565043688/O1CN01GUhjrQ1d79URx5B1w_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 4179.00, '{\"颜色\": \"【大屏幕】无畏15银 i5-1240P/180°开合\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (110, 18, 'https://gw.alicdn.com/bao/uploaded/i4/2208565043688/O1CN01GUhjrQ1d79URx5B1w_!!2208565043688.jpg', 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 4679.00, '{\"颜色\": \"【大屏幕】无畏15银 i5-1240P/180°开合\", \"机身内存\": \"16GB+1T\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (111, 19, 'https://gw.alicdn.com/imgextra/i1/3252495915/O1CN01bvoe1s1tZ7Wzwmnqw_!!3252495915.jpg', '【咨询立减】华为笔记本电脑MateBookD14/15 2022新款12代酷睿i5/i7高清14英寸轻薄电脑旗舰店官网笔记本正品', 3379.00, '{\"颜色\": \"【D14】深空灰:R5-5500U+8G+512G+14英寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (112, 19, 'https://gw.alicdn.com/imgextra/i2/3252495915/O1CN01qqRmFc1tZ7Y8CeXnz_!!3252495915.jpg', '【咨询立减】华为笔记本电脑MateBookD14/15 2022新款12代酷睿i5/i7高清14英寸轻薄电脑旗舰店官网笔记本正品', 3899.00, '{\"颜色\": \"【D14】皓月银:R5-5500U+16G+512G 14英寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (113, 19, 'https://gw.alicdn.com/imgextra/i1/3252495915/O1CN01Xqr1c11tZ7XCNIiyU_!!3252495915.jpg', '【咨询立减】华为笔记本电脑MateBookD14/15 2022新款12代酷睿i5/i7高清14英寸轻薄电脑旗舰店官网笔记本正品', 3599.00, '{\"颜色\": \"【D15 SE版】银:i5-1155G7+8G+512G固态+15.6英寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (114, 19, 'https://gw.alicdn.com/imgextra/i2/3252495915/O1CN01Gj3Z4T1tZ7Xf2yJTc_!!3252495915.jpg', '【咨询立减】华为笔记本电脑MateBookD14/15 2022新款12代酷睿i5/i7高清14英寸轻薄电脑旗舰店官网笔记本正品', 5449.00, '{\"颜色\": \"【12代】D15 灰:i7-1260P+16G+512G 15.6英寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (115, 20, 'https://gd2.alicdn.com/imgextra/i2/2945643708/O1CN01jZUhNJ1dGJPBHhsFj_!!2945643708.png_400x400.jpg', 'Hasee/神舟战神Z8D6游戏本TX8R5 RT4060系列13代I7独显笔记本电脑', 4748.99, '{\"颜色\": \"Z7-RA5(I5-13500/16G/512/3050/144HZ\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (116, 20, 'https://gd4.alicdn.com/imgextra/i4/2945643708/O1CN01nlEi6A1dGJPHOhCzT_!!2945643708.png_400x400.jpg', 'Hasee/神舟战神Z8D6游戏本TX8R5 RT4060系列13代I7独显笔记本电脑', 5458.99, '{\"颜色\": \"Z7-RA7 13代(i7-13700/16/512/3050\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (117, 20, 'https://gd1.alicdn.com/imgextra/i4/2945643708/O1CN01Jk5XJC1dGJQRiBSfo_!!2945643708.jpg_400x400.jpg', 'Hasee/神舟战神Z8D6游戏本TX8R5 RT4060系列13代I7独显笔记本电脑', 5949.00, '{\"颜色\": \"Z7-RA9 (I9-13900/16/512/3050/165HZ\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (118, 21, 'https://gw.alicdn.com/bao/uploaded/i1/4118523471/O1CN017P5yUA1bVlYSwwBsJ_!!4118523471.jpg', '【新品首销】Xiaomi/RedmiBook Pro 14 15 2022 12代英特尔酷睿i5-12500H 2.5K120Hz高性能轻薄本笔记本电脑', 4699.00, '{\"颜色\": \"【14英寸新品现货】i5-12500H/UMA/16G/512G\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (119, 21, 'https://gw.alicdn.com/imgextra/i1/4118523471/O1CN01s2IOOm1bVlYzw8oMR_!!4118523471.jpg', '【新品首销】Xiaomi/RedmiBook Pro 14 15 2022 12代英特尔酷睿i5-12500H 2.5K120Hz高性能轻薄本笔记本电脑', 3899.00, '{\"颜色\": \"【14英寸星光灰】i5-12450H/UMA/16G/512G\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (120, 21, 'https://gw.alicdn.com/bao/uploaded/i4/4118523471/O1CN01egMLLo1bVlYtgT1wD_!!4118523471.jpg', '【新品首销】Xiaomi/RedmiBook Pro 14 15 2022 12代英特尔酷睿i5-12500H 2.5K120Hz高性能轻薄本笔记本电脑', 4899.00, '{\"颜色\": \"【15.6英寸新品现货】i5-12500H/UMA/16G/512G\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (121, 21, 'https://gw.alicdn.com/imgextra/i2/4118523471/O1CN013Q5k721bVlWU0agpv_!!4118523471.jpg', '【新品首销】Xiaomi/RedmiBook Pro 14 15 2022 12代英特尔酷睿i5-12500H 2.5K120Hz高性能轻薄本笔记本电脑', 4899.00, '{\"颜色\": \"【15.6英寸星光灰】i5-12450H/UMA/16G/512G\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (122, 22, 'https://gw.alicdn.com/bao/uploaded/i3/2891951486/O1CN01gfUicT1MqdFC1KQQK_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2458.00, '{\"颜色\": \"尊贵银\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (123, 22, 'https://gw.alicdn.com/bao/uploaded/i3/2891951486/O1CN01gfUicT1MqdFC1KQQK_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2658.00, '{\"颜色\": \"尊贵银\", \"机身内存\": \"16GB+1T机械硬盘+256G固态\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (124, 22, 'https://gw.alicdn.com/bao/uploaded/i3/2891951486/O1CN01gfUicT1MqdFC1KQQK_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2758.00, '{\"颜色\": \"尊贵银\", \"机身内存\": \"16GB+1TB 固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (125, 22, 'https://gw.alicdn.com/bao/uploaded/i4/2891951486/O1CN01m7Tu4K1MqdFErDPp7_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2458.00, '{\"颜色\": \"中国红\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (126, 22, 'https://gw.alicdn.com/bao/uploaded/i4/2891951486/O1CN01m7Tu4K1MqdFErDPp7_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2658.00, '{\"颜色\": \"中国红\", \"机身内存\": \"16GB+1T机械硬盘+256G固态\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (127, 22, 'https://gw.alicdn.com/bao/uploaded/i4/2891951486/O1CN01m7Tu4K1MqdFErDPp7_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2758.00, '{\"颜色\": \"中国红\", \"机身内存\": \"16GB+1TB 固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (128, 22, 'https://gw.alicdn.com/bao/uploaded/i3/2891951486/O1CN01UKxQsf1MqdF6uOQsS_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2458.00, '{\"颜色\": \"星空黑\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (129, 22, 'https://gw.alicdn.com/bao/uploaded/i3/2891951486/O1CN01UKxQsf1MqdF6uOQsS_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2658.00, '{\"颜色\": \"星空黑\", \"机身内存\": \"16GB+1T机械硬盘+256G固态\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (130, 22, 'https://gw.alicdn.com/bao/uploaded/i3/2891951486/O1CN01UKxQsf1MqdF6uOQsS_!!2891951486.jpg', '【2023新品】笔记本电脑戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 2758.00, '{\"颜色\": \"星空黑\", \"机身内存\": \"16GB+1TB 固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (131, 23, 'https://gw.alicdn.com/bao/uploaded/i4/1621790841/O1CN01ON5npA1I5DsOO045O_!!1621790841.png', '【12期免息】Microsoft/微软 Surface Laptop 5 13.5英寸12代酷睿i5 触控屏微软新款笔记本电脑', 11088.00, '{\"颜色\": \"亮铂金\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (132, 23, 'https://gw.alicdn.com/bao/uploaded/i4/1621790841/O1CN01isGYzV1I5DsKdiJam_!!1621790841.png', '【12期免息】Microsoft/微软 Surface Laptop 5 13.5英寸12代酷睿i5 触控屏微软新款笔记本电脑', 11088.00, '{\"颜色\": \"砂岩金\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (133, 23, 'https://gw.alicdn.com/bao/uploaded/i2/1621790841/O1CN01JxidQh1I5DsUeyN2r_!!1621790841.png', '【12期免息】Microsoft/微软 Surface Laptop 5 13.5英寸12代酷睿i5 触控屏微软新款笔记本电脑', 11088.00, '{\"颜色\": \"雅典黑\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (134, 23, 'https://gw.alicdn.com/bao/uploaded/i4/1621790841/O1CN01Bxib6J1I5DsSm4nqe_!!1621790841.png', '【12期免息】Microsoft/微软 Surface Laptop 5 13.5英寸12代酷睿i5 触控屏微软新款笔记本电脑', 11088.00, '{\"颜色\": \"仙茶绿\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (135, 24, 'https://gw.alicdn.com/imgextra/i4/4003223843/O1CN01JO3WUV1eG8tLHwTJq_!!4003223843.jpg', 'ROG枪神7 Plus 英特尔13代酷睿i9 RTX4060 RTX4070显卡18英寸星云屏电竞吃鸡游戏本笔记本电脑玩家国度旗舰店', 13999.00, '{\"颜色\": \"i9-13980HX 液金导热 RTX4060 2.5K 240Hz\", \"机身内存\": \"16GB+1TB 固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (136, 24, 'https://gw.alicdn.com/imgextra/i2/4003223843/O1CN01WmszfA1eG8tOAi1mV_!!4003223843.jpg', 'ROG枪神7 Plus 英特尔13代酷睿i9 RTX4060 RTX4070显卡18英寸星云屏电竞吃鸡游戏本笔记本电脑玩家国度旗舰店', 14999.00, '{\"颜色\": \"i9-13980HX 液金导热 RTX4070 2.5K 240Hz\", \"机身内存\": \"16GB+1TB 固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (137, 25, 'https://gd2.alicdn.com/imgextra/i2/21052504/O1CN017cSmqJ1UMsWejdnKs_!!21052504.jpg', 'Razer/雷蛇 灵刃 RZ09-0195雷蛇笔记本razer游戏1060外星人退订机', 6550.00, '{\"颜色\": \"套1：雷蛇 灵刃标准版15-0328\", \"机身内存\": \"16GB+500GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (138, 25, 'https://gd1.alicdn.com/imgextra/i1/21052504/O1CN01qUWd4J1UMsWpZVmDi_!!21052504.jpg', 'Razer/雷蛇 灵刃 RZ09-0195雷蛇笔记本razer游戏1060外星人退订机', 4550.00, '{\"颜色\": \"套2：雷蛇 灵刃标准版15-0270\", \"机身内存\": \"16GB+500GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (139, 25, 'https://gd1.alicdn.com/imgextra/i1/21052504/O1CN01jDGfmP1UMsW8CWQ7W_!!21052504.jpg', 'Razer/雷蛇 灵刃 RZ09-0195雷蛇笔记本razer游戏1060外星人退订机', 5350.00, '{\"颜色\": \"套3：雷蛇 灵刃标准版15-0300\", \"机身内存\": \"16GB+500GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (140, 26, 'https://gd1.alicdn.com/imgextra/i1/2214797504518/O1CN01R0XQW01jFIEa5FY46_!!2214797504518.jpg', 'Samsung/三星笔记本电脑商务办公学生吃鸡游戏本剪辑设计便携i7', 2680.00, '{\"颜色\": \"惠普游戏套餐3  i7  16g  固态512GB  独显1G  14寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (141, 26, 'https://gd4.alicdn.com/imgextra/i4/2214797504518/O1CN01PgLzqF1jFIEclZ42m_!!2214797504518.jpg', 'Samsung/三星笔记本电脑商务办公学生吃鸡游戏本剪辑设计便携i7', 3280.00, '{\"颜色\": \"惠普游戏套餐4  i7  16g  固态512GB  独显2G 15.6寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (142, 26, 'https://gd3.alicdn.com/imgextra/i3/2214797504518/O1CN01twZWVG1jFIEYgJWQZ_!!2214797504518.jpg', 'Samsung/三星笔记本电脑商务办公学生吃鸡游戏本剪辑设计便携i7', 5880.00, '{\"颜色\": \"外星人吃鸡游戏套餐8 i7 32G 固态512G 独显4GB 17.3寸\", \"机身内存\": \"16GB+512GB\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (143, 27, 'https://gd2.alicdn.com/imgextra/i2/2214011850474/O1CN01KwLSRC1FN8V3IGXzF_!!2214011850474.jpg', 'MECHREVO机械革命z3air钛钽i7//i5泰坦笔记本电脑游戏蛟龙5本3060', 3099.00, '{\"颜色\": \"套餐3/i7-6700H GTX960-4G\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (144, 27, 'https://gd1.alicdn.com/imgextra/i1/2214011850474/O1CN01U5NTxk1FN8UmeGg86_!!2214011850474.jpg', 'MECHREVO机械革命z3air钛钽i7//i5泰坦笔记本电脑游戏蛟龙5本3060', 3299.00, '{\"颜色\": \"套餐7/i7-7700H GTX1050-2G\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (145, 27, 'https://gd4.alicdn.com/imgextra/i4/2214011850474/O1CN01xHziCO1FN8Xo1gTL6_!!2214011850474.jpg', 'MECHREVO机械革命z3air钛钽i7//i5泰坦笔记本电脑游戏蛟龙5本3060', 5399.00, '{\"颜色\": \"套餐21/蛟龙5 R5-4600 RTX3060-6G\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (146, 28, 'https://gw.alicdn.com/imgextra/i1/2206687946483/O1CN01rAoysz1xlGUzLq8em_!!2206687946483.jpg', '摆渡者 F15国行2022新款酷睿i5笔记本电脑游戏本RTX3060独立显卡超级游戏本商务办公手提', 7999.00, '{\"颜色\": \"深夜黑\", \"机身内存\": \"32GB+1TB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (147, 29, 'https://gw.alicdn.com/bao/uploaded/i1/2214211571512/O1CN01TGgW9K1N2XfFkAThH_!!2214211571512.jpg_Q75.jpg', '【晒单赠鼠标垫】海尔15.6英寸笔记本电脑商务办公学生网课轻度娱乐轻薄本15M-38SH(11代i3 8G 512G Win11)', 2999.00, '{\"颜色\": \"灰色\", \"机身内存\": \"8GB+256GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (148, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01zVukTq26keUWSscDs_!!2208305407700.png', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1658.00, '{\"颜色\": \"2023旗舰版A款J系粉色\", \"机身内存\": \"12GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (149, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01zVukTq26keUWSscDs_!!2208305407700.png', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1898.00, '{\"颜色\": \"2023旗舰版A款J系粉色\", \"机身内存\": \"12GB+1TB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (150, 30, 'https://gw.alicdn.com/imgextra/i2/2208305407700/O1CN01HauDE926keRSetBy3_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1758.00, '{\"颜色\": \"2023旗舰版A款N系粉色\", \"机身内存\": \"12GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (151, 30, 'https://gw.alicdn.com/imgextra/i2/2208305407700/O1CN01HauDE926keRSetBy3_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1998.00, '{\"颜色\": \"2023旗舰版A款N系粉色\", \"机身内存\": \"12GB+1TB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (152, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01buNYGb26keRag2lRX_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1758.00, '{\"颜色\": \"2023旗舰版B款N系粉色\", \"机身内存\": \"12GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (153, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01buNYGb26keRag2lRX_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1998.00, '{\"颜色\": \"2023旗舰版B款N系粉色\", \"机身内存\": \"12GB+1TB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (154, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01zVukTq26keUWSscDs_!!2208305407700.png', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1758.00, '{\"颜色\": \"2023旗舰版A款J系粉色\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (155, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01zVukTq26keUWSscDs_!!2208305407700.png', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1998.00, '{\"颜色\": \"2023旗舰版A款J系粉色\", \"机身内存\": \"16GB+1TB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (156, 30, 'https://gw.alicdn.com/imgextra/i2/2208305407700/O1CN01HauDE926keRSetBy3_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1858.00, '{\"颜色\": \"2023旗舰版A款N系粉色\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (157, 30, 'https://gw.alicdn.com/imgextra/i2/2208305407700/O1CN01HauDE926keRSetBy3_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 2098.00, '{\"颜色\": \"2023旗舰版A款N系粉色\", \"机身内存\": \"16GB+1TB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (158, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01buNYGb26keRag2lRX_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 1858.00, '{\"颜色\": \"2023旗舰版B款N系粉色\", \"机身内存\": \"16GB+512GB固态硬盘\"}');
INSERT INTO `business`.`sku` (`id`, `spu_id`, `img`, `title`, `price`, `own_spec`) VALUES (159, 30, 'https://gw.alicdn.com/bao/uploaded/i3/2208305407700/O1CN01buNYGb26keRag2lRX_!!2208305407700.jpg', '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 2098.00, '{\"颜色\": \"2023旗舰版B款N系粉色\", \"机身内存\": \"16GB+1TB固态硬盘\"}');


DROP TABLE IF EXISTS `spu`;
CREATE TABLE `spu` (
    `id`          int          NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `category_id` int          DEFAULT NULL COMMENT '类别id',
    `brand_id`    int          DEFAULT NULL COMMENT '商品所属品牌id',
    `title`       varchar(255) NOT NULL COMMENT '标题',
    `img`         varchar(255) NOT NULL COMMENT '图片地址',
    `details`     varchar(255) DEFAULT NULL COMMENT '详情',
    `saleable`    varchar(255) NOT NULL DEFAULT '1' COMMENT '是否上架，0下架，1上架',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='spu商品信息表';
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (1, 1, 1, '新款正品灵动岛i14 pro全网通5G智能手机16+512G直播抖音快手批发', 'https://cbu01.alicdn.com/img/ibank/O1CN01db2Sx62J4b8FDfuqw_!!2206550199368-0-cib.220x220.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (2, 1, 2, '可拍1台水滴爆款M40 6.8寸大屏12+512G全网通低价智能手机批直播', 'https://cbu01.alicdn.com/img/ibank/O1CN01Sou6Nv1FLlKPMGxaE_!!2214652160471-0-cib.220x220.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (3, 1, 3, '华为Mate40E 5G 全网通 支持鸿蒙系HarmonyOS 亮黑色 8G+128G（浙江电信）', 'https://img14.360buyimg.com/n7/jfs/t1/104169/25/29946/227655/62a69b0eE68cfefef/81c0584c25907b30.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (4, 1, 4, 'vivo X90 8GB+128GB 冰蓝 4nm天玑9200旗舰芯片 自研芯片V2 120W双芯闪充 蔡司影像 5G 拍照 ', 'https://img10.360buyimg.com/n7/jfs/t1/156047/19/35947/73209/640df0a5Fe3537de8/8be56ee30483d039.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (5, 1, 5, 'OPPO Reno9 水光人像镜头 120Hz 超清曲面屏 4500mAh大电池 轻薄机身 5G手机', 'https://img10.360buyimg.com/n7/jfs/t1/188585/38/33046/59025/6406ed5aF85d1f8f8/81d64c35b7ce80c7.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (6, 1, 6, '小米12S Pro 5G手机  12GB+256GB 黑色 全网通5G', 'https://img13.360buyimg.com/n7/jfs/t1/91090/12/34515/66475/640a9f71F83219db7/31153c6b1ff89d99.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (7, 1, 7, 'SAMSUNG Galaxy S22 超视觉夜拍系统超清夜景 超电影影像系统 超耐用精工设计 8GB+256GB 幽紫秘境', 'https://img10.360buyimg.com/n7/jfs/t1/164914/28/34062/76689/63e0aacfF9227a8f0/14ff794b783c7b44.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (8, 1, 8, 'nubia 努比亚 Z50 Ultra 屏下摄像8GB+256GB 夜海 第二代骁龙8 35mm+85mm黄金双焦段定制光学 5G手机', 'https://img14.360buyimg.com/n7/jfs/t1/180021/9/33708/81859/6410391dF443a3d20/8e2c92f83a2644bc.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (9, 1, 9, '荣耀X40 120Hz OLED硬核曲屏 5100mAh 快充大电池 7.9mm轻薄设计 5G手机', 'https://img11.360buyimg.com/n7/jfs/t1/206344/27/28925/81124/63b6815dFa9921f73/b2d6125396fc3e6c.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (10, 1, 10, '朵唯（DOOV）X60 Pro智能手机游戏超薄可用5G移动联通电信卡老人老年机长续航百元便宜学生备用', 'https://img12.360buyimg.com/n7/jfs/t1/197496/27/30627/59620/640313bbF0d766b51/47a22e286fe18a14.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (11, 1, 11, '联想拯救者Y70 高性能游戏电竞商务智能学生手机', 'https://img14.360buyimg.com/n7/jfs/t1/6914/9/28149/76584/640ebed5F0e6bf737/2ce15ab30a7c4f12.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (12, 1, 12, '海语(HAIYU) x12升级版大电量大屏幕4G全网通指纹人脸双卡微信视频学生老人智能', 'https://img12.360buyimg.com/n7/jfs/t1/109496/10/21223/113421/6205c9b6E752313d8/dd7b517dcf4151c0.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (13, 1, 13, '中兴Axon 40 Pro 吴京代言 高通骁龙870 一亿像素高清影像 144HZ屏66W快充', 'https://img14.360buyimg.com/n7/jfs/t1/190252/26/33079/90055/63fee9c9Ffd00bfa1/54d4dd05d82c9f44.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (14, 1, 14, '乐视Letv Y2Pro八核智能 自营128GB 超薄大屏游戏学生百元老人机全网通4G 移动联通电信 长续航 幻夜黑', 'https://img12.360buyimg.com/n7/jfs/t1/169005/9/34519/60247/63ea00f9F6fd1b1cf/0fb9fcf9411e1466.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (15, 1, 15, '黑鲨5RS 120W闪充 144Hz电竞屏 5G新品游戏电竞', 'https://img10.360buyimg.com/n7/jfs/t1/102759/17/37739/436724/64097956F3bf6795a/c95200334cd660a5.png', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (16, 2, 11, 'Lenovo/联想 笔记本电脑 i7轻薄便携大学生上网课超薄办公游戏本', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i1/2215206508116/O1CN01aGbzoc29pBHMx7ZD7_!!2215206508116.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (17, 2, 1, 'i7苹果 笔记本电脑 MacBook Pro超薄Air办公游戏本轻薄手提2023新款', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i4/4141530666/O1CN01YF15Dj1Gn4bI1gGPk_!!4141530666.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (18, 2, 16, 'ASUS华硕无畏15/16 a豆1412代酷睿i5 锐龙AMD15.6轻薄本180°办公学生网课笔记本电脑官方旗舰店', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i3/2208565043688/O1CN01yL3D2z1d79VqPjLBy_!!0-item_pic.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (19, 2, 3, '【咨询立减】华为笔记本电脑 MateBookD14/15 2022新款12代酷睿i5/i7高清14英寸轻薄电脑旗舰店官网笔记本正品', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextrahttps://img.alicdn.com/imgextra/i4/3252495915/O1CN01aAurwG1tZ7V5ForRh_!!3252495915-0-alimamacc.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (20, 2, 17, 'Hasee/神舟战神Z8D6游戏本TX8R5 RT4060系列13代I7独显笔记本电脑', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/2945643708/O1CN01Jk5XJC1dGJQRiBSfo_!!2945643708.jpg_580x580Q90.jpg_.webp', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (21, 2, 6, '【新品首销】Xiaomi/RedmiBook Pro 14 15 2022 12代英特尔酷睿i5-12500H 2.5K120Hz高性能轻薄本', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/190290121/O1CN01kKdx6h1ClSiFb2tNx_!!0-saturn_solar.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (22, 2, 18, '【2023新品】笔记本电脑 戴睿R12Pro全新15.6英寸高配轻薄便携大学生游戏本超薄超极上网手提办公用商务超极本', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i1/116671484/O1CN01hIjVMG1MpiWkLFUq2_!!0-saturn_solar.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (23, 2, 19, '【12期免息】Microsoft/微软 Surface Laptop 5 13.5英寸12代酷睿i5 触控屏微软新款笔记本电脑', 'https://gw.alicdn.com/imgextra/O1CN01vJAOSc1I5DubD9XM6_!!1621790841-0-picasso.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (24, 2, 20, 'ROG枪神7 Plus 英特尔13代酷睿i9 RTX4060 RTX4070显卡18英寸星云屏电竞吃鸡游戏本笔记本电脑玩家国度旗舰店', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextrahttps://img.alicdn.com/imgextra/i3/4003223843/O1CN01fLh9cN1eG8thrLmjy_!!4003223843-0-alimamacc.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (25, 2, 21, 'Razer/雷蛇 灵刃 RZ09-0195雷蛇笔记本razer游戏1060外星人退订机', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/imgextra/i2/10959900/O1CN01kuJRZP2N0FcT3uW2T_!!0-saturn_solar.jpg_580x580Q90.jpg_.webp', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (26, 2, 7, 'Samsung/三星笔记本电脑商务办公学生吃鸡游戏本剪辑设计便携i7', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i2/2214797504518/O1CN01vVsu5B1jFIEfg98mJ_!!2214797504518.jpg_580x580Q90.jpg_.webp', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (27, 2, 22, 'MECHREVO机械革命z3air钛钽i7//i5泰坦笔记本电脑游戏蛟龙5本3060', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/2214011850474/O1CN018RZMT51FN8Y9OTbhe_!!2214011850474.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (28, 2, 23, '摆渡者 F15国行2022新款酷睿i5笔记本电脑游戏本RTX3060独立显卡超级游戏本商务办公手提', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i4/2206687946483/O1CN01VbU0cK1xlGUvviA81_!!0-item_pic.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (29, 2, 24, '【晒单赠鼠标垫】海尔15.6英寸笔记本电脑商务办公学生网课轻度娱乐轻薄本15M-38SH(11代i3 8G 512G Win11)', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i1/2214211571512/O1CN01SFsc0N1N2XfM762Ci_!!0-item_pic.jpg_580x580Q90.jpg', NULL, '1');
INSERT INTO `spu` (`id`, `category_id`, `brand_id`, `title`, `img`, `details`, `saleable`) VALUES (30, 2, 25, '2023全新英特尔四核手提笔记本电脑15英寸女大学生款超薄商务办公设计专用吃鸡游戏本', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i1/2208305407700/O1CN01o36yK526keRVIFMbF_!!0-item_pic.jpg_580x580Q90.jpg', NULL, '1');


DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
    `id`   int          NOT NULL AUTO_INCREMENT COMMENT '主键 Id',
    `name` varchar(255) NOT NULL COMMENT '姓名',
    `age`  int          NOT NULL COMMENT '年龄',
    `sex`  tinyint      NOT NULL COMMENT '性别',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `FK_Department_ID_idx` (`age`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='员工表';
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (1, 'Anthony Stevens', 65, '0');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (2, 'Song Ziyi', 50, '1');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (3, 'Lui Yu Ling', 40, '1');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (4, 'Sugiyama Misaki', 41, '1');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (5, 'Ishida Ayano', 98, '0');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (6, 'Otsuka Itsuki', 2, '1');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (7, 'Carmen Jones', 1, '1');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (8, 'Tao Wing Kuen', 52, '0');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (9, 'Otsuka Seiko', 79, '1');
INSERT INTO `business`.`employee` (`id`, `name`, `age`, `sex`) VALUES (10, 'Wei Jiehong', 17, '0');


DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
    `id`           bigint        NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `tenant_id`    int           NOT NULL COMMENT '租户ID',
    `user_name`    varchar(50)   NOT NULL COMMENT '用户名',
    `pass_word`    varchar(100)  NOT NULL COMMENT '密码',
    `commodity`    varchar(100)  DEFAULT NULL COMMENT '商品',
    `color`        varchar(20)   DEFAULT NULL COMMENT '颜色',
    `number`       int           DEFAULT '0' COMMENT '数量',
    `price`        decimal(10,2) DEFAULT NULL COMMENT '价格',
    `create_date`  datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_date` datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `delete_flag`  tinyint       DEFAULT '0' COMMENT '逻辑删除(0:未删除,1:已删除)',
    `version`      int           DEFAULT '0' COMMENT '版本号(乐观锁)',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'MacBook Pro', '深空灰', 1, 12999.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (1, 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', 'iPhone 13', '白色', 2, 5999.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (2, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'iPad Air', '蓝色', 1, 4799.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (2, 'lisi', 'e10adc3949ba59abbe56e057f20f883e', 'AirPods Pro', '白色', 1, 1999.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (3, 'wangwu', 'e10adc3949ba59abbe56e057f20f883e', 'Apple Watch', '黑色', 1, 3199.00, 1, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (1, 'test_user', 'e10adc3949ba59abbe56e057f20f883e', 'iMac', '绿色', 1, 9999.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (4, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'Mac mini', '银色', 1, 5299.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (1, 'vip_user', 'e10adc3949ba59abbe56e057f20f883e', 'iPhone 14 Pro', '金色', 1, 8999.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (5, 'zhaoliu', 'e10adc3949ba59abbe56e057f20f883e', 'HomePod mini', '黄色', 2, 749.00, 0, 1);
INSERT INTO `user` (`tenant_id`, `user_name`, `pass_word`, `commodity`, `color`, `number`, `price`, `delete_flag`, `version`) VALUES (1, 'multi_buyer', 'e10adc3949ba59abbe56e057f20f883e', 'Apple Pencil', '白色', 3, 999.00, 0, 1);


DROP TABLE IF EXISTS `tree_children`;
CREATE TABLE `tree_children` (
    `id`       int         NOT NULL AUTO_INCREMENT COMMENT '主键',
    `address`  varchar(10) DEFAULT NULL COMMENT '地址',
    `parentId` int         NOT NULL COMMENT '父节点Id',
    `remake`   varchar(20) DEFAULT NULL COMMENT '备注',
    `sort`     int         DEFAULT NULL COMMENT '排序',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='节点表';
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (1, '湖北', 0, '省份', 2);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (2, '黄冈', 1, '城市', 2);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (3, '武汉', 1, '城市', 1);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (4, '天门', 1, '城市', 3);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (5, '仙桃', 1, '城市', 4);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (6, '武穴', 2, '县', 1);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (7, '梅川', 6, '镇（街道）', 1);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (8, '蕲春', 2, '县', 2);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (9, '山西', 0, '省份', 1);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (10, '太原', 9, '城市', 1);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (11, '大同', 9, '城市', 2);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (12, '朔州', 9, '城市', 4);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (13, '吕梁', 9, '城市', 3);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (14, '长治', 9, '城市', 5);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (15, '小店区', 10, '县', 4);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (16, '清徐', 10, '县', 2);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (17, '古交', 10, '县', 1);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (18, '阳曲', 10, '县', 3);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (19, '学府街道', 15, '镇（街道）', 2);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (20, '坞城街道', 15, '镇（街道）', 1);
INSERT INTO `tree_children` (`id`, `address`, `parentId`, `remake`, `sort`) VALUES (21, '青龙古镇', 18, '镇（街道）', 1);
