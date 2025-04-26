
-- ----------------------------
-- 用户表
-- ----------------------------
DROP TABLE IF EXISTS `ece_user`;
CREATE TABLE `ece_user`(
    `id`          BIGINT           NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '用户ID',
    `ece_id`      INT              DEFAULT NULL COMMENT 'eceId',
    `user_code`   VARCHAR(32)      NOT NULL COMMENT '用户编码',
    `user_name`   VARCHAR(32)      NOT NULL COMMENT '用户名称',
    `pass_word`   VARCHAR(255)     NOT NULL COMMENT '密码',
    `email`       VARCHAR(100)     NOT NULL UNIQUE COMMENT '邮箱',
    `phone`       VARCHAR(20)      NOT NULL UNIQUE COMMENT '手机号',
    `birthday`    DATETIME         DEFAULT NULL COMMENT '生日',
    `age`         INT              NOT NULL COMMENT '年龄',
    `sex`         ENUM('男', '女')  DEFAULT NULL COMMENT '性别',
    `address`     VARCHAR(256)     DEFAULT NULL COMMENT '地址',
    `create_date` TIMESTAMP        DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_date` TIMESTAMP        DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`      ENUM('ACTIVE', 'INACTIVE', 'DELETED')
                                   NOT NULL DEFAULT 'ACTIVE' COMMENT '用户状态'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT = '用户表';

INSERT INTO ece_user VALUES (1,  191, 'LhGr5S5Vn0', '丁杰宏', 'ZFr0KOr40P', 'wrighttheresa@icloud.com', '163-9680-9148',  '2011-08-22 20:59:39', 52, '男', '越秀区中山二路771号',        '2005-01-22 22:45:08', '2006-05-14 04:17:57', 'DELETED');
INSERT INTO ece_user VALUES (2,  556, 'NV8BO0KLcv', '尹璐',   'qcX7h97Jjk', 'tszhin8@yahoo.com',        '7903 790326',    '2015-03-28 22:35:16', 44, '男', '天河区大信商圈大信南路502号', '2017-11-16 11:20:28', '2024-06-01 23:48:58', 'ACTIVE');
INSERT INTO ece_user VALUES (3,  618, 'JgjiYkQbus', '蒋杰宏', 'ImwDxpoclS', 'anqcai@yahoo.com',         '52-381-5475',    '2009-05-20 08:41:32', 6,  '男', '成华区玉双路6号911号',       '2016-09-22 09:48:09', '2007-01-23 20:49:15', 'INACTIVE');
INSERT INTO ece_user VALUES (4,  153, 'fxL78F7C3W', '魏睿',   'zRADdIqGL7', 'guoziyi@gmail.com',        '11-681-2485',    '2021-09-22 04:23:10', 33, '男', '龙岗区布吉镇西环路405号',    '2024-03-13 08:40:37', '2005-11-11 22:48:01', 'INACTIVE');
INSERT INTO ece_user VALUES (5,  901, 'gG77w1EeVe', '罗杰宏', '5rQmGI15lW', 'shaozita@yahoo.com',       '718-523-1872',   '2007-07-02 06:26:46', 95, '女', '白云区小坪东路612号',        '2009-04-06 13:34:07', '2002-02-23 19:49:58', 'ACTIVE');
INSERT INTO ece_user VALUES (6,  748, '2eIYpIbKgu', '武詩涵', 'u1YMUwG2wJ', 'zhennanlu@hotmail.com',    '11-298-6160',    '2021-03-14 18:39:41', 52, '男', '京华商圈华夏街964号',        '2007-05-05 11:48:21', '2014-04-02 19:35:41', 'ACTIVE');
INSERT INTO ece_user VALUES (7,  346, 'IViyJBTXur', '于岚',   'GsxHCOQ8Yj', 'shaws@hotmail.com',        '330-477-3594',   '2010-12-12 13:19:03', 98, '女', '福田区景田东一街67号',       '2006-10-21 19:29:08', '2019-02-26 22:22:44', 'DELETED');
INSERT INTO ece_user VALUES (8,  76,  'wCcfjdVGLk', '秦杰宏', 'WHVsnOgJKI', 'tanjialun@outlook.com',    '(161) 604 6639', '2006-07-06 11:59:21', 4,  '女', '朝阳区三里屯路66号',         '2017-04-29 18:37:37', '2024-09-17 19:17:37', 'INACTIVE');
INSERT INTO ece_user VALUES (9,  740, 'KjlLCCHkSd', '林晓明', 'nQ7AZp6mls', 'whkwok@gmail.com',         '134-5217-0482',  '2023-10-23 12:12:19', 53, '男', '坑美十五巷733号',           '2022-12-29 05:29:42', '2005-06-11 09:24:41', 'INACTIVE');
INSERT INTO ece_user VALUES (10, 785, 'BNj5hOGTma', '沈晓明', 'Dy4VyOmdHK', 'ramomarie@icloud.com',     '614-578-9402',   '2008-01-21 23:52:35', 86, '女', '罗湖区清水河一路275号',      '2008-01-12 21:33:13', '2022-11-27 17:12:45', 'DELETED');
