package com.example.ddd.application.service.impl;

import com.example.ddd.domain.entity.Order;
import com.example.ddd.domain.repository.OrderRepository;
import com.example.ddd.domain.valobj.Address;
import com.example.ddd.domain.valobj.OrderItem;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-22 下午11:42
 * @apiNote TODO
 */
@Service
public class OrderServiceImpl {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 创建订单
     * @param buyerId
     * @param sellerId
     * @param orderItems
     */
    public void createOrder(int buyerId, int sellerId, Set<OrderItem> orderItems){
        Order order = new Order(1L, buyerId, sellerId, new Address(), orderItems);
        // 运费不随订单其它信息一同构造，因为运费可能在后期会进行修改，因此提供一个设置运费的方法
        order.setShippingFee(BigDecimal.TEN);
        orderRepository.save(order);
    }

    /**
     * 更新收货地址
     * @param orderId
     */
    public void updateAddress(long orderId, Address address){
        Order order = orderRepository.findById(orderId);
        order.updateAddress(address);
        orderRepository.save(order);
    }

    /**
     * 设置优惠
     * @param orderId
     * @param discountAmount
     */
    public void setDiscount(long orderId, BigDecimal discountAmount){
        Order order = orderRepository.findById(orderId);
        order.setDiscount(discountAmount);
        orderRepository.save(order);
    }
}
