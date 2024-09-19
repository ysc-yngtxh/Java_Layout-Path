
-- ----------------------------
-- 字典表
-- ----------------------------
CREATE TABLE `ece_lookup_classify`(
    `classify_id`      BIGINT(20)   NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '类别ID',
    `classify_code`    VARCHAR(20)  NOT NULL COMMENT '类别编码',
    `classify_name`    VARCHAR(255) NOT NULL UNIQUE COMMENT '类别名称',
    `classify_type`    VARCHAR(255) NOT NULL UNIQUE COMMENT '类别类型',
    `status`           TINYINT      DEFAULT 0 COMMENT '状态：(0正常 1停用)',
    `classify_desc`    VARCHAR(255) DEFAULT NULL COMMENT '类别描述',
    `app_name`         VARCHAR(255) DEFAULT NULL COMMENT '应用名称',
    `create_by`        VARCHAR(64)  DEFAULT NULL COMMENT '创建者',
    `created_date`     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`        VARCHAR(64)  DEFAULT NULL COMMENT '更新者',
    `updated_date`     TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `remark`           VARCHAR(500) DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT '字典类别表';

insert into ece_lookup_classify values(1,  'USER_GENDER',         '用户性别', 'sys_user_sex',        '0', '用户的性别信息，如男、女或其他',           'ece', 'admin', sysdate(), '', null, '用户性别列表');
insert into ece_lookup_classify values(2,  'MENU_STATUS',         '菜单状态', 'sys_show_hide',       '0', '菜单当前的显示状态，如展开或收起',         'hsm', 'admin', sysdate(), '', null, '菜单状态列表');
insert into ece_lookup_classify values(3,  'SYSTEM_SWITCH',       '系统开关', 'sys_normal_disable',  '0', '系统功能的开启或关闭状态',                'ece', 'admin', sysdate(), '', null, '系统开关列表');
insert into ece_lookup_classify values(4,  'TASK_STATUS',         '任务状态', 'sys_job_status',      '0', '任务的当前进展状态，如待办、进行中、已完成', 'erp', 'admin', sysdate(), '', null, '任务状态列表');
insert into ece_lookup_classify values(5,  'GROUPING_TASKS',      '任务分组', 'sys_job_group',       '0', '将任务按类别或属性进行分组的方法',         'ece', 'admin', sysdate(), '', null, '任务分组列表');
insert into ece_lookup_classify values(6,  'SYSTEM_FEATURE',      '系统特性', 'sys_yes_no',          '0', '询问系统是否具备某种特性或功能',           'erp', 'admin', sysdate(), '', null, '系统是否列表');
insert into ece_lookup_classify values(7,  'NOTIFICATION_TYPES',  '通知类型', 'sys_notice_type',     '0', '不同类型的通知，如提醒、警告、信息更新',    'hsm', 'admin', sysdate(), '', null, '通知类型列表');
insert into ece_lookup_classify values(8,  'NOTIFICATION_STATUS', '通知状态', 'sys_notice_status',   '0', '通知的当前状态，如未读、已读、已清除',      'erp', 'admin', sysdate(), '', null, '通知状态列表');
insert into ece_lookup_classify values(9,  'OPERATION_TYPE',      '操作类型', 'sys_oper_type',       '0', '执行的具体操作种类，如添加、删除、编辑',     'ece', 'admin', sysdate(), '', null, '操作类型列表');
insert into ece_lookup_classify values(10, 'SYSTEM_STATE',        '系统状态', 'sys_common_status',   '0', '系统的整体运行状况，如正常、异常、维护中',   'hsm', 'admin', sysdate(), '', null, '登录状态列表');
insert into ece_lookup_classify values(11, 'USER_ROLE',           '用户角色', 'sys_user_role',       '0', '用户在系统中的权限和职责',                'hsm', 'admin', sysdate(), '', null, '用户角色列表');



-- ----------------------------
-- 字典数据表
-- ----------------------------
CREATE TABLE ece_lookup_items (
    `item_id`        BIGINT(20)    NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '项ID',
    `classify_code`  VARCHAR(20)   NOT NULL UNIQUE COMMENT '类别编码',
    `item_code`      VARCHAR(255)  NOT NULL COMMENT '项编码',
    `item_name_zh`   VARCHAR(255)  NOT NULL COMMENT '中文项名称',
    `item_name_en`   VARCHAR(255)  NOT NULL COMMENT '英文项名称',
    `item_type`      VARCHAR(255)  NOT NULL COMMENT '项类型',
    `item_desc`      VARCHAR(255)  NOT NULL COMMENT '项描述',
    `status`         TINYINT       DEFAULT 0 COMMENT '状态：(0正常 1停用)',
    `sort`           INT(4)        DEFAULT 0 COMMENT '项排序',
    `app_name`       VARCHAR(255)  DEFAULT NULL COMMENT '应用名称',
    `create_by`      VARCHAR(64)   DEFAULT NULL COMMENT '创建者',
    `created_date`   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_by`      VARCHAR(64)   DEFAULT NULL COMMENT '更新者',
    `updated_date`   TIMESTAMP     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `item_attr1`     VARCHAR(255)  DEFAULT NULL COMMENT '扩展属性1',
    `item_attr2`     VARCHAR(255)  DEFAULT NULL COMMENT '扩展属性2',
    `remark`         VARCHAR(500)  DEFAULT NULL COMMENT '备注',
    -- CONSTRAINT `fk_classify_code`：表示约束名
    -- FOREIGN KEY (`classify_code`) REFERENCES ece_lookup_classify(`classify_code`)：表示外键，`classify_code`是外键，引用ece_lookup_classify表中的classify_code字段
    -- ON DELETE CASCADE：表示级联删除，当ece_lookup_classify表中的classify_code字段被删除时，ece_lookup_items表中的classify_code字段也会被删除
    CONSTRAINT `fk_classify_code` FOREIGN KEY (`classify_code`) REFERENCES ece_lookup_classify(`classify_code`) ON DELETE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT '字典数据表';

insert into ece_lookup_items values (1,  'USER_GENDER',         'MALE',            '男性',    'Male',            'ENUM',    '男性用户',         0, 1, 'UserManagement',       'admin', NOW(), 'admin', NOW(), NULL,                  NULL, NULL);
insert into ece_lookup_items values (2,  'USER_GENDER',         'FEMALE',          '女性',    'Female',          'ENUM',    '女性用户',         0, 2, 'UserManagement',       'admin', NOW(), 'admin', NOW(), NULL,                  NULL, NULL);
insert into ece_lookup_items values (3,  'NOTIFICATION_TYPES',  'EMAIL',           '电子邮件', 'Email',           'ENUM',   '电子邮件通知',       0, 1, 'NotificationService', 'admin', NOW(), 'admin', NOW(), 'email_notification',  NULL, '支持发送邮件通知');
insert into ece_lookup_items values (4,  'NOTIFICATION_TYPES',  'SMS',             '短信',    'SMS',             'ENUM',    '短信通知',         0, 2, 'NotificationService',  'admin', NOW(), 'admin', NOW(), 'sms_notification',    NULL, '支持发送短信通知');
insert into ece_lookup_items values (5,  'SYSTEM_SWITCH',       'ENABLED',         '启用',    'Enabled',         'BOOLEAN', '系统功能启用',      0, 1, 'SystemConfig',         'admin', NOW(), 'admin', NOW(), NULL,                  NULL, '系统功能处于启用状态');
insert into ece_lookup_items values (6,  'SYSTEM_SWITCH',       'DISABLED',        '禁用',    'Disabled',        'BOOLEAN', '系统功能禁用',      0, 2, 'SystemConfig',         'admin', NOW(), 'admin', NOW(), NULL,                  NULL, '系统功能处于禁用状态');
insert into ece_lookup_items values (7,  'MENU_STATUS',         'OPEN',            '打开',    'Open',            'BOOLEAN', '菜单打开状态',      0, 1, 'MenuManagement',       'admin', NOW(), 'admin', NOW(), NULL,                  NULL, '菜单处于打开状态');
insert into ece_lookup_items values (8,  'MENU_STATUS',         'CLOSED',          '关闭',    'Closed',          'BOOLEAN', '菜单关闭状态',      0, 2, 'MenuManagement',       'admin', NOW(), 'admin', NOW(), NULL,                  NULL, '菜单处于关闭状态');
insert into ece_lookup_items values (9,  'TASK_STATUS',         'PENDING',         '待办',    'Pending',         'ENUM',    '任务尚未开始',      0, 1, 'TaskManagement',       'admin', NOW(), 'admin', NOW(), 'task_status',         NULL, '任务等待处理');
insert into ece_lookup_items values (10, 'TASK_STATUS',         'IN_PROGRESS',     '进行中',  'In Progress',     'ENUM',    '任务正在进行',       0, 2, 'TaskManagement',      'admin', NOW(), 'admin', NOW(), 'task_status',         NULL, '任务正在处理中');
insert into ece_lookup_items values (11, 'TASK_STATUS',         'COMPLETED',       '完成',    'Completed',       'ENUM',    '任务已经完成',      0, 3, 'TaskManagement',       'admin', NOW(), 'admin', NOW(), 'task_status',         NULL, '任务已经完成');
insert into ece_lookup_items values (12, 'GROUPING_TASKS',      'HIGH_PRIORITY',   '高优先级', 'High Priority',   'ENUM',    '高优先级的任务分组', 0, 1, 'TaskManagement',       'admin', NOW(), 'admin', NOW(), 'priority_level',      NULL, '高优先级任务');
insert into ece_lookup_items values (13, 'GROUPING_TASKS',      'MEDIUM_PRIORITY', '中优先级', 'Medium Priority', 'ENUM',    '中优先级的任务分组', 0, 2, 'TaskManagement',       'admin', NOW(), 'admin', NOW(), 'priority_level',      NULL, '中优先级任务');
insert into ece_lookup_items values (14, 'GROUPING_TASKS',      'LOW_PRIORITY',    '低优先级', 'Low Priority',    'ENUM',    '低优先级的任务分组', 0, 3, 'TaskManagement',       'admin', NOW(), 'admin', NOW(), 'priority_level',      NULL, '低优先级任务');
insert into ece_lookup_items values (15, 'OPERATION_TYPE',      'CREATE',          '创建',    'Create',          'ENUM',    '创建操作',          0, 1, 'OperationLog',         'admin', NOW(), 'admin', NOW(), 'operation_log',       NULL, '记录创建操作');
insert into ece_lookup_items values (16, 'OPERATION_TYPE',      'UPDATE',          '更新',    'Update',          'ENUM',    '更新操作',          0, 2, 'OperationLog',         'admin', NOW(), 'admin', NOW(), 'operation_log',       NULL, '记录更新操作');
insert into ece_lookup_items values (17, 'OPERATION_TYPE',      'DELETE',          '删除',    'Delete',          'ENUM',    '删除操作',          0, 3, 'OperationLog',         'admin', NOW(), 'admin', NOW(), 'operation_log',       NULL, '记录删除操作');
insert into ece_lookup_items values (18, 'SYSTEM_STATE',        'NORMAL',          '正常',    'Normal',          'ENUM',    '系统运行正常',       0, 1, 'SystemMonitor',        'admin', NOW(), 'admin', NOW(), 'system_monitor',      NULL, '系统运行状态正常');
insert into ece_lookup_items values (19, 'SYSTEM_STATE',        'MAINTENANCE',     '维护',    'Maintenance',     'ENUM',    '系统在维护中',       0, 2, 'SystemMonitor',        'admin', NOW(), 'admin', NOW(), 'system_monitor',      NULL, '系统正在进行维护');
insert into ece_lookup_items values (20, 'SYSTEM_STATE',        'FAULT',           '故障',    'Fault',           'ENUM',    '系统出现故障',       0, 3, 'SystemMonitor',        'admin', NOW(), 'admin', NOW(), 'system_monitor',      NULL, '系统出现故障');
insert into ece_lookup_items values (21, 'NOTIFICATION_STATUS', 'UNREAD',          '未读',    'Unread',          'ENUM',    '通知未被阅读',       0, 1, 'NotificationService',  'admin', NOW(), 'admin', NOW(), 'notification_status', NULL, '通知未被阅读');
insert into ece_lookup_items values (22, 'NOTIFICATION_STATUS', 'READ',            '已读',    'Read',            'ENUM',    '通知已被阅读',       0, 2, 'NotificationService',  'admin', NOW(), 'admin', NOW(), 'notification_status', NULL, '通知已被阅读');
insert into ece_lookup_items values (23, 'NOTIFICATION_STATUS', 'ARCHIVED',        '存档',    'Archived',        'ENUM',    '通知已被存档',       0, 3, 'NotificationService',  'admin', NOW(), 'admin', NOW(), 'notification_status', NULL, '通知已被存档');
insert into ece_lookup_items values (24, 'SYSTEM_FEATURE',      'YES',             '有',      'Yes',             'BOOLEAN', '系统具有此特性',     0, 1, 'SystemFeature',        'admin', NOW(), 'admin', NOW(), 'feature_flag',        NULL, '系统具有此特性');
insert into ece_lookup_items values (25, 'SYSTEM_FEATURE',      'NO',              '无',      'No',              'BOOLEAN', '系统不具有此特性',   0, 2, 'SystemFeature',        'admin', NOW(), 'admin', NOW(), 'feature_flag',        NULL, '系统不具有此特性');
insert into ece_lookup_items values (26, 'USER_ROLE',           'ADMIN',           '管理员',   'Admin',           'ENUM',    '拥有管理权限的用户', 0, 1, 'UserRole',             'admin', NOW(), 'admin', NOW(), 'role_permission',     NULL, '管理员用户');
insert into ece_lookup_items values (27, 'USER_ROLE',           'USER',            '普通用户', 'User',            'ENUM',    '普通用户',          0, 2, 'UserRole',             'admin', NOW(), 'admin', NOW(), 'role_permission',     NULL, '普通用户');
insert into ece_lookup_items values (28, 'USER_ROLE',           'GUEST',           '访客',    'Guest',           'ENUM',    '访问系统的访客',     0, 3, 'UserRole',             'admin', NOW(), 'admin', NOW(), 'role_permission',     NULL, '访客用户');
insert into ece_lookup_items values (29, 'USER_ROLE',           'AUDITOR',         '审计员',  'Auditor',         'ENUM',     '负责审计的用户',     0, 4, 'UserRole',             'admin', NOW(), 'admin', NOW(), 'role_permission',     NULL, '审计员用户');


