
-- ----------------------------
-- 产品管理表
-- ----------------------------
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


-- ----------------------------
-- 产品评论表
-- ----------------------------
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