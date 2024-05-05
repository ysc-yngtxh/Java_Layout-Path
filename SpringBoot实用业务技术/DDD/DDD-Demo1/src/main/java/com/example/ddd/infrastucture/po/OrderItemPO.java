package com.example.ddd.infrastucture.po;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO 持久化--PO层
 */
@Data
public class OrderItemPO {
    private long orderId;
    private long itemId;
    private BigDecimal price;
}
