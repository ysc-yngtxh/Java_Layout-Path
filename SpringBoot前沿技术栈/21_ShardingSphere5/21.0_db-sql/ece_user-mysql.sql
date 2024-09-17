
-- ----------------------------
-- 用户表
-- ----------------------------
CREATE TABLE `ece_user`(
    `id`          bigint           NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `ece_id`      int              DEFAULT NULL COMMENT 'eceId',
    `user_code`   varchar(32)      NOT NULL COMMENT '用户编码',
    `user_name`   varchar(32)      NOT NULL COMMENT '用户名称',
    `birthday`    datetime         DEFAULT NULL COMMENT '生日',
    `age`         int              NOT NULL COMMENT '年龄',
    `sex`         enum('男', '女')  DEFAULT NULL COMMENT '性别',
    `address`     varchar(256)     DEFAULT NULL COMMENT '地址',
    `create_date` TIMESTAMP        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT = '用户表';

INSERT INTO ece_user VALUES (1, 191, 'LhGr5S5Vn0', '丁杰宏', '2011-08-22 20:59:39', 52, '男', '越秀区中山二路771号', '2005-01-22 22:45:08', '2006-05-14 04:17:57');
INSERT INTO ece_user VALUES (2, 556, 'NV8BO0KLcv', '尹璐', '2015-03-28 22:35:16', 44, '男', '天河区大信商圈大信南路502号', '2017-11-16 11:20:28', '2024-06-01 23:48:58');
INSERT INTO ece_user VALUES (3, 618, 'JgjiYkQbus', '蒋杰宏', '2009-05-20 08:41:32', 6, '男', '成华区玉双路6号911号', '2016-09-22 09:48:09', '2007-01-23 20:49:15');
INSERT INTO ece_user VALUES (4, 153, 'fxL78F7C3W', '魏睿', '2021-09-22 04:23:10', 33, '男', '龙岗区布吉镇西环路405号', '2024-03-13 08:40:37', '2005-11-11 22:48:01');
INSERT INTO ece_user VALUES (5, 901, 'gG77w1EeVe', '罗杰宏', '2007-07-02 06:26:46', 95, '女', '白云区小坪东路612号', '2009-04-06 13:34:07', '2002-02-23 19:49:58');
INSERT INTO ece_user VALUES (6, 748, '2eIYpIbKgu', '武詩涵', '2021-03-14 18:39:41', 52, '男', '京华商圈华夏街964号', '2007-05-05 11:48:21', '2014-04-02 19:35:41');
INSERT INTO ece_user VALUES (7, 346, 'IViyJBTXur', '于岚', '2010-12-12 13:19:03', 98, '女', '福田区景田东一街67号', '2006-10-21 19:29:08', '2019-02-26 22:22:44');
INSERT INTO ece_user VALUES (8, 76, 'wCcfjdVGLk', '秦杰宏', '2006-07-06 11:59:21', 4, '女', '朝阳区三里屯路66号', '2017-04-29 18:37:37', '2024-09-17 19:17:37');
INSERT INTO ece_user VALUES (9, 740, 'KjlLCCHkSd', '林晓明', '2023-10-23 12:12:19', 53, '男', '坑美十五巷733号', '2022-12-29 05:29:42', '2005-06-11 09:24:41');
INSERT INTO ece_user VALUES (10, 785, 'BNj5hOGTma', '沈晓明', '2008-01-21 23:52:35', 86, '女', '罗湖区清水河一路275号', '2008-01-12 21:33:13', '2022-11-27 17:12:45');