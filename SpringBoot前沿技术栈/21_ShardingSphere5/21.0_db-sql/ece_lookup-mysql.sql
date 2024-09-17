
-- ----------------------------
-- 字典表
-- ----------------------------
CREATE TABLE `ece_lookup`(
    `lookup_id`        BIGINT(20)   NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '字典主键',
    `lookup_name`      VARCHAR(255) NOT NULL UNIQUE COMMENT '字典名称',
    `lookup_type`      VARCHAR(255) NOT NULL UNIQUE COMMENT '字典类型',
    `status`           TINYINT      DEFAULT 0 COMMENT '状态：(0正常 1停用)',
    `create_by`        VARCHAR(64)  DEFAULT NULL COMMENT '创建者',
    `created_date`     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`        VARCHAR(64)  DEFAULT NULL COMMENT '更新者',
    `updated_date`     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`           VARCHAR(500) DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT '字典表';

insert into ece_lookup values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', sysdate(), '', null, '用户性别列表');
insert into ece_lookup values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', sysdate(), '', null, '菜单状态列表');
insert into ece_lookup values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', sysdate(), '', null, '系统开关列表');
insert into ece_lookup values(4,  '任务状态', 'sys_job_status',      '0', 'admin', sysdate(), '', null, '任务状态列表');
insert into ece_lookup values(5,  '任务分组', 'sys_job_group',       '0', 'admin', sysdate(), '', null, '任务分组列表');
insert into ece_lookup values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', sysdate(), '', null, '系统是否列表');
insert into ece_lookup values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', sysdate(), '', null, '通知类型列表');
insert into ece_lookup values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', sysdate(), '', null, '通知状态列表');
insert into ece_lookup values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', sysdate(), '', null, '操作类型列表');
insert into ece_lookup values(10, '系统状态', 'sys_common_status',   '0', 'admin', sysdate(), '', null, '登录状态列表');



-- ----------------------------
-- 字典数据表
-- ----------------------------
CREATE TABLE ece_lookup_items (
    `lookup_item_id`    BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '字典项主键ID',
    `lookup_id`         BIGINT(20)   NOT NULL COMMENT '字典主键ID',
    `lookup_item_code`  VARCHAR(255) NOT NULL COMMENT '字典项编码',
    `lookup_item_name`  VARCHAR(255) NOT NULL COMMENT '字典项名称',
    `lookup_item_value` VARCHAR(255) NOT NULL COMMENT '字典项值',
    `lookup_item_sort`  INT(4)       DEFAULT 0 COMMENT '字典项排序',
    `status`            TINYINT      DEFAULT 0 COMMENT '状态：(0正常 1停用)',
    `create_by`         VARCHAR(64)  DEFAULT NULL COMMENT '创建者',
    `created_date`      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`         VARCHAR(64)  DEFAULT NULL COMMENT '更新者',
    `updated_date`      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`            VARCHAR(500) DEFAULT NULL COMMENT '备注',
    -- CONSTRAINT `fk_lookup_id`：表示约束名
    -- FOREIGN KEY (`lookup_id`) REFERENCES ece_lookup(`lookup_id`)：表示外键，`lookup_id`是外键，引用ece_lookup表中的lookup_id字段
    -- ON DELETE CASCADE：表示级联删除，当ece_lookup表中的lookup_id字段被删除时，ece_lookup_items表中的lookup_id字段也会被删除
    CONSTRAINT `fk_lookup_id` FOREIGN KEY (`lookup_id`) REFERENCES ece_lookup(`lookup_id`) ON DELETE CASCADE,
    PRIMARY KEY (`lookup_item_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT '字典数据表';

insert into ece_lookup_items values (1,  1,  'MAN',            '男',      '0',      1, 0, 'admin', sysdate(), '', NULL, '性别男');
insert into ece_lookup_items values (2,  1,  'WOMEN',          '女',      '1',      2, 0, 'admin', sysdate(), '', NULL, '性别女');
insert into ece_lookup_items values (3,  1,  'Unknown',        '未知',    '2',      3, 0, 'admin', sysdate(), '', NULL, '性别未知');
insert into ece_lookup_items values (4,  2,  'Display',        '显示',    '0',      1, 0, 'admin', sysdate(), '', NULL, '显示菜单');
insert into ece_lookup_items values (5,  2,  'Hide',           '隐藏',    '1',      2, 0, 'admin', sysdate(), '', NULL, '隐藏菜单');
insert into ece_lookup_items values (6,  3,  'Normal',         '正常',    '0',      1, 0, 'admin', sysdate(), '', NULL, '正常状态');
insert into ece_lookup_items values (7,  3,  'Deactivate',     '停用',    '1',      2, 0, 'admin', sysdate(), '', NULL, '停用状态');
insert into ece_lookup_items values (8,  4,  'Normal',         '正常',    '0',      1, 0, 'admin', sysdate(), '', NULL, '正常状态');
insert into ece_lookup_items values (9,  4,  'Pause',          '暂停',    '1',      2, 0, 'admin', sysdate(), '', NULL, '停用状态');
insert into ece_lookup_items values (10, 5,  'Default',        '默认',    'DEFAULT',1, 0, 'admin', sysdate(), '', NULL, '默认分组');
insert into ece_lookup_items values (11, 5,  'System',         '系统',    'SYSTEM', 2, 0, 'admin', sysdate(), '', NULL, '系统分组');
insert into ece_lookup_items values (12, 6,  'Yes',            '是',      'Y',      1, 0, 'admin', sysdate(), '', NULL, '系统默认是');
insert into ece_lookup_items values (13, 6,  'No',             '否',      'N',      2, 0, 'admin', sysdate(), '', NULL, '系统默认否');
insert into ece_lookup_items values (14, 7,  'Notifications',  '通知',    '1',      1, 0, 'admin', sysdate(), '', NULL, '通知');
insert into ece_lookup_items values (15, 7,  'Announcement',   '公告',    '2',      2, 0, 'admin', sysdate(), '', NULL, '公告');
insert into ece_lookup_items values (16, 8,  'Normal',         '正常',    '0',      1, 0, 'admin', sysdate(), '', NULL, '正常状态');
insert into ece_lookup_items values (17, 8,  'Close',          '关闭',    '1',      2, 0, 'admin', sysdate(), '', NULL, '关闭状态');
insert into ece_lookup_items values (18, 9,  'Other',          '其他',    '0',      99,0, 'admin', sysdate(), '', NULL, '其他操作');
insert into ece_lookup_items values (19, 9,  'New',            '新增',    '1',      1, 0, 'admin', sysdate(), '', NULL, '新增操作');
insert into ece_lookup_items values (20, 9,  'Modify',         '修改',    '2',      2, 0, 'admin', sysdate(), '', NULL, '修改操作');
insert into ece_lookup_items values (21, 9,  'Delete',         '删除',    '3',      3, 0, 'admin', sysdate(), '', NULL, '删除操作');
insert into ece_lookup_items values (22, 9,  'Authorization',  '授权',    '4',      4, 0, 'admin', sysdate(), '', NULL, '授权操作');
insert into ece_lookup_items values (23, 9,  'Export',         '导出',    '5',      5, 0, 'admin', sysdate(), '', NULL, '导出操作');
insert into ece_lookup_items values (24, 9,  'Import',         '导入',    '6',      6, 0, 'admin', sysdate(), '', NULL, '导入操作');
insert into ece_lookup_items values (25, 9,  'Strong retreat', '强退',    '7',      7, 0, 'admin', sysdate(), '', NULL, '强退操作');
insert into ece_lookup_items values (26, 9,  'Generate code',  '生成代码', '8',      8, 0, 'admin', sysdate(), '', NULL, '生成操作');
insert into ece_lookup_items values (27, 9,  'Clear data',     '清空数据', '9',      9, 0, 'admin', sysdate(), '', NULL, '清空操作');
insert into ece_lookup_items values (28, 10, 'Success',        '成功',    '0',      1, 0, 'admin', sysdate(), '', NULL, '正常状态');
insert into ece_lookup_items values (29, 10, 'Fail',           '失败',    '1',      2, 0, 'admin', sysdate(), '', NULL, '停用状态');


