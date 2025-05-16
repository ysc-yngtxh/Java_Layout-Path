package com.example.ddd.infrastucture.po;

import com.example.ddd.domain.valobj.Address;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO 持久化--PO层
 */
@Data
public class OrderPO {

	private long orderId;
	private int buyerId;
	private int sellerId;
	private BigDecimal shippingFee;
	private BigDecimal discountAmount;
	private Address address;
}
