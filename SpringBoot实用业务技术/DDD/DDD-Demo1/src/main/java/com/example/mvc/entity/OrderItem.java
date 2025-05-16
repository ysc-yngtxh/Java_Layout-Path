package com.example.mvc.entity;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @description TODO 订单商品信息
 */
@Data
@AllArgsConstructor
public class OrderItem {

	private long orderId;
	private long itemId;
	private BigDecimal price;
}
