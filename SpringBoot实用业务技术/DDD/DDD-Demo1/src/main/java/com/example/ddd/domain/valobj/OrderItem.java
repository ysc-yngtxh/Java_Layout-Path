package com.example.ddd.domain.valobj;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import java.math.BigDecimal;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Desccription TODO 订单商品值对象
 */
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class OrderItem {
    private long orderId;
    private long itemId;
    private BigDecimal price;
}
