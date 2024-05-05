package com.example.mvc.entity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @description TODO 订单信息
 */
@Data
public class Order {
    private long orderId;
    private int buyerId;
    private int sellerId;
    private BigDecimal amount;
    private BigDecimal shippingFee;
    private BigDecimal discountAmount;
    private BigDecimal payAmount;
    private String address;
}
