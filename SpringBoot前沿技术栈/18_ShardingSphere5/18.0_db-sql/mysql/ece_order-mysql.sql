
-- ----------------------------
-- 订单管理表
-- ----------------------------
DROP TABLE IF EXISTS `ece_order`;
CREATE TABLE ece_order(
    `order_id`         BIGINT         AUTO_INCREMENT PRIMARY KEY COMMENT '订单ID',
    `user_id`          BIGINT         NOT NULL COMMENT '用户ID',
    `order_number`     VARCHAR(50)    NOT NULL UNIQUE COMMENT '订单编号',
    `order_date`       TIMESTAMP      DEFAULT CURRENT_TIMESTAMP COMMENT '订单日期，默认为当前时间',
    `total_amount`     DECIMAL(10, 2) NOT NULL COMMENT '订单总金额',
    `status`           ENUM('PENDING', 'PROCESSING', 'SHIPPED', 'DELIVERED', 'CANCELLED')
                                      DEFAULT 'PENDING' COMMENT '订单状态(等待,正在处理,已发货,已交付,取消)',
    `shopping_address` TEXT(255)      COMMENT '收货地址',
    `billing_address`  TEXT(255)      COMMENT '账单地址',
    `created_at`       TIMESTAMP      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`       TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (`user_id`) REFERENCES ece_user (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT = '订单管理表';

INSERT INTO ece_order VALUES (1, 1, 'ORD20240919001', NOW(), 2099.98, 'PENDING',   '北京市朝阳区某路123号',   '北京市朝阳区某路123号',  NOW(), NULL);
INSERT INTO ece_order VALUES (2, 2, 'ORD20240919002', NOW(), 799.99,  'DELIVERED', '上海市浦东新区某街456号', '上海市浦东新区某街456号', NOW(), NULL);


-- ----------------------------
-- 订单商品项表
-- ----------------------------
DROP TABLE IF EXISTS `ece_order_item`;
CREATE TABLE ece_order_item(
    `order_item_id` BIGINT         AUTO_INCREMENT PRIMARY KEY COMMENT '订单商品项ID',
    `order_id`      BIGINT         NOT NULL COMMENT '订单ID',
    `product_id`    BIGINT         NOT NULL COMMENT '产品ID',
    `quantity`      INT            NOT NULL COMMENT '库存',
    `price`         DECIMAL(10, 2) NOT NULL COMMENT '商品价格',
    `created_at`    TIMESTAMP      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`    TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (`order_id`) REFERENCES ece_order (`order_id`),
    FOREIGN KEY (`product_id`) REFERENCES ece_product (`product_id`)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT = '订单商品项表';

INSERT INTO ece_order_item VALUES (1, 1, 1, 999, 1299.99, NOW(), NULL);
INSERT INTO ece_order_item VALUES (2, 1, 2, 999, 399.99, NOW(), NULL);
INSERT INTO ece_order_item VALUES (3, 2, 3, 999, 799.99, NOW(), NULL);
