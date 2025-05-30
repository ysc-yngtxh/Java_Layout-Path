
-- ----------------------------
-- 产品管理表
-- ----------------------------
DROP TABLE IF EXISTS `ece_product`;
CREATE TABLE ece_product(
    `product_id`     BIGINT         AUTO_INCREMENT PRIMARY KEY COMMENT '产品ID',
    `name`           VARCHAR(255)   NOT NULL COMMENT '产品名称',
    `description`    TEXT           COMMENT '产品描述',
    `category`       VARCHAR(100)   COMMENT '产品类别',
    `price`          DECIMAL(10, 2) NOT NULL COMMENT '产品价格',
    `stock_quantity` INT            NOT NULL COMMENT '库存数量',
    `image_url`      VARCHAR(255)   COMMENT '产品图片的 URL',
    `created_at`     TIMESTAMP      DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     TIMESTAMP      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `status`         ENUM('AVAILABLE', 'OUT_OF_STOCK', 'DISCONTINUED')
                                    DEFAULT 'AVAILABLE' COMMENT '产品状态(可用,缺货,停用)，默认为 AVAILABLE'
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT = '产品管理表';

INSERT INTO ece_product VALUES (1, '智能手表',     '一款具有健康监测功能的智能手表',    '智能穿戴', 1299.99, 100, 'https://example.com/images/smartwatch.jpg', NOW(), NULL, 'AVAILABLE');
INSERT INTO ece_product VALUES (2, '无线耳机',     '高音质无线蓝牙耳机，支持主动降噪',   '音频设备', 799.99, 50, 'https://example.com/images/earphones.jpg',   NOW(), NULL, 'AVAILABLE');
INSERT INTO ece_product VALUES (3, '便携式充电宝', '容量为20000mAh的大容量便携式充电宝', '移动电源', 399.99, 200, 'https://example.com/images/powerbank.jpg',  NOW(), NULL, 'AVAILABLE');


-- ----------------------------
-- 产品评论表
-- ----------------------------
DROP TABLE IF EXISTS `ece_product_review`;
CREATE TABLE ece_product_review(
    review_id  BIGINT    AUTO_INCREMENT PRIMARY KEY COMMENT '评论ID',
    product_id BIGINT    NOT NULL COMMENT '产品ID',
    user_id    BIGINT    NOT NULL COMMENT '用户ID',
    -- CHECK 约束用于确保列中的值满足特定条件。InnoDB 存储引擎中支持 CHECK 约束，但在 MyISAM 存储引擎中则不支持。
    rating     INT       CHECK (rating >= 1 AND rating <= 5) COMMENT '评分',
    comment    TEXT      COMMENT '评论内容',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    FOREIGN KEY (product_id) REFERENCES ece_product (product_id),
    FOREIGN KEY (user_id) REFERENCES ece_user (id)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT = '产品评论表';

INSERT INTO ece_product_review VALUES (1, 1, 1, 5, '手表非常棒，功能齐全，外观时尚！', NOW(), NULL);
INSERT INTO ece_product_review VALUES (2, 1, 2, 4, '使用体验良好，电池续航稍短。',    NOW(), NULL);
INSERT INTO ece_product_review VALUES (3, 2, 3, 3, '音质不错，但是耳塞有点小。',      NOW(), NULL);
INSERT INTO ece_product_review VALUES (4, 3, 4, 5, '容量大，出差必备神器！',         NOW(), NULL);
