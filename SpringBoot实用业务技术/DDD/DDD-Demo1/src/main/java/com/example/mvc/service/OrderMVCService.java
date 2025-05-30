package com.example.mvc.service;

import com.example.ddd.domain.valobj.Address;
import com.example.mvc.entity.OrderItem;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-22 下午11:30
 * @apiNote TODO
 */
public interface OrderMVCService {

	void createOrder(int buyerId, int sellerId, List<OrderItem> orderItems);

	void updateAddress(long orderId, Address address);

	void setDiscount(long orderId, BigDecimal discountAmount);
}
