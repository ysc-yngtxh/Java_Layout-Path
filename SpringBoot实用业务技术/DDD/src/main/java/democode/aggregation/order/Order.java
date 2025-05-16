package democode.aggregation.order;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
public class Order {

	private long orderId;
	private BigDecimal amount;
	private Address address;
	private List<OrderItem> orderItems;
	private Agreement agreement;

	// 更新收货地址，整体覆盖
	public void updateAddress(Address address) {
		this.address = address;
	}

	// 订单商品发生变更，查找替换
	public void updateOrderItems(OrderItem orderItem) {
		// 1. 从 orderItems 找到对应的 orderItem 然后替换
		// 2. 重新计算总价
	}

	/**
	 * 申请退款
	 */
	public void refunding() {
		agreement.refunding();
	}

	/**
	 * 原则上，返回给外部的引用，都应该防止间接被修改
	 *
	 * @return
	 */
	public List<OrderItem> getOrderItems() {
		return Collections.unmodifiableList(orderItems);
	}
}
