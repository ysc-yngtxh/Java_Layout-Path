CREATE TABLE `t_dict`(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `dict_name`   varchar(255) DEFAULT NULL COMMENT '字典名称',
    `dict_code`   varchar(255) DEFAULT NULL COMMENT '字典编码',
    `description` varchar(255) DEFAULT NULL COMMENT '描述',
    `del_flag`    varchar(255) DEFAULT NULL COMMENT '逻辑删除',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典表';
INSERT INTO `db_database`.`t_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`) VALUES (1, '支付方式', 'PAY_METHOD', '支付方式', '0');
INSERT INTO `db_database`.`t_dict` (`id`, `dict_name`, `dict_code`, `description`, `del_flag`) VALUES (2, '订单状态', 'ORDER_STATUS', '订单状态', '0');


CREATE TABLE `t_dict_item`(
    `id`          int NOT NULL AUTO_INCREMENT COMMENT '主键Id',
    `dict_id`     int NOT NULL COMMENT '字典Id',
    `item_text`   varchar(255) DEFAULT NULL COMMENT '子项内容',
    `item_value`  varchar(255) DEFAULT NULL COMMENT '子项值',
    `description` varchar(255) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典子项表';
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (1, 1, '支付宝', 'ZFB', '支付宝');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (2, 1, '微信', 'WX', '微信');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (3, 1, '银联', 'YL', '银联');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (4, 2, '待付款', 'WAIT_BUYER_PAY', '待付款');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (5, 2, '交易成功', 'TRADE_SUCCESS', '交易成功');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (6, 2, '订单取消成功', 'CANCEL_SUCCESS', '订单取消成功');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (7, 2, '退款成功', 'REFUSE_SUCCESS', '退款成功');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (8, 2, '退款失败', 'REFUSE_FAIL', '退款失败');
INSERT INTO `db_database`.`t_dict_item` (`id`, `dict_id`, `item_text`, `item_value`, `description`) VALUES (9, 2, '订单已关闭', 'TRADE_CLOSED', '订单已关闭');
