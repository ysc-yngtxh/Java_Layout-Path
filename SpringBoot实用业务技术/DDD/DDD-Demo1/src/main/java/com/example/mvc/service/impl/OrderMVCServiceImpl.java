package com.example.mvc.service.impl;

import com.alibaba.fastjson2.JSON;
import com.example.mvc.dao.OrderItemMVCDao;
import com.example.mvc.dao.OrderMVCDao;
import com.example.mvc.entity.Address;
import com.example.mvc.entity.OrderItem;
import com.example.mvc.entity.OrderMVC;
import com.example.mvc.service.OrderMVCService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @description TODO 业务规则直接散落在Service层，实体对象只作为数据的载体，代码无法直接反应业务
 */
@Service
public class OrderMVCServiceImpl implements OrderMVCService {

	private final OrderMVCDao orderMVCDao;
	private final OrderItemMVCDao orderItemMVCDao;

	public OrderMVCServiceImpl(OrderMVCDao orderMVCDao, OrderItemMVCDao orderItemMVCDao) {
		this.orderMVCDao = orderMVCDao;
		this.orderItemMVCDao = orderItemMVCDao;
	}

	/**
	 * 创建订单
	 *
	 * @param buyerId
	 * @param sellerId
	 * @param orderItems
	 */
	public void createOrder(int buyerId, int sellerId, List<OrderItem> orderItems) {
		// 新建一个Order数据对象
		OrderMVC orderMVC = new OrderMVC();
		orderMVC.setOrderId(1L);
		// 算订单总金额
		BigDecimal amount = orderItems.stream()
		                              .map(OrderItem::getPrice)
		                              .reduce(BigDecimal.ZERO, BigDecimal::add);
		orderMVC.setAmount(amount);
		// 运费
		orderMVC.setShippingFee(BigDecimal.TEN);
		// 优惠金额
		orderMVC.setDiscountAmount(BigDecimal.ZERO);
		// 支付总额 = 订单总额 + 运费 - 优惠金额
		BigDecimal payAmount = orderMVC.getAmount().add(orderMVC.getShippingFee()).subtract(orderMVC.getDiscountAmount());
		orderMVC.setPayAmount(payAmount);
		// 设置买、卖家
		orderMVC.setBuyerId(buyerId);
		orderMVC.setSellerId(sellerId);
		// 设置收获地址
		orderMVC.setAddress(JSON.toJSONString(new Address()));
		// 写库
		orderMVCDao.insert(orderMVC);
		orderItems.forEach(orderItemMVCDao::insert);
	}

	@Override
	public void updateAddress(long orderId, com.example.ddd.domain.valobj.Address address) {

	}

	/**
	 * 更新收货地址
	 *
	 * @param orderId
	 */
	public void updateAddress(long orderId, Address address) {
		// 新建一个Order数据对象
		OrderMVC orderMVC = new OrderMVC();
		orderMVC.setOrderId(orderId);
		orderMVC.setAddress(JSON.toJSONString(address));
		// orderMVCDao => 通过主键更新订单信息
		orderMVCDao.updateByPrimaryKey(orderMVC);
	}

	/**
	 * 设置优惠
	 *
	 * @param orderId
	 * @param discountAmount
	 */
	public void setDiscount(long orderId, BigDecimal discountAmount) {
		OrderMVC orderMVC = orderMVCDao.find(orderId);
		orderMVC.setDiscountAmount(discountAmount);
		// 从新计算支付金额
		BigDecimal payAmount = orderMVC.getAmount().add(orderMVC.getShippingFee()).subtract(discountAmount);
		orderMVC.setPayAmount(payAmount);
		// orderMVCDao => 通过主键更新订单信息
		orderMVCDao.updateByPrimaryKey(orderMVC);
	}
}
