package com.example.ddd.application.service.impl;

import com.example.ddd.domain.entity.OrderDDD;
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
public class OrderDDDServiceImpl {

    private final OrderRepository orderRepository;

    public OrderDDDServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * 创建订单
     * @param buyerId
     * @param sellerId
     * @param orderItems
     */
    public void createOrder(int buyerId, int sellerId, Set<OrderItem> orderItems){
        OrderDDD orderDDD = new OrderDDD(1L, buyerId, sellerId, new Address(), orderItems);
        // 运费不随订单其它信息一同构造，因为运费可能在后期会进行修改，因此提供一个设置运费的方法
        orderDDD.setShippingFee(BigDecimal.TEN);
        orderRepository.save(orderDDD);
    }

    /**
     * 更新收货地址
     * @param orderId
     */
    public void updateAddress(long orderId, Address address){
        OrderDDD orderDDD = orderRepository.findById(orderId);
        orderDDD.updateAddress(address);
        orderRepository.save(orderDDD);
    }

    /**
     * 设置优惠
     * @param orderId
     * @param discountAmount
     */
    public void setDiscount(long orderId, BigDecimal discountAmount){
        OrderDDD orderDDD = orderRepository.findById(orderId);
        orderDDD.setDiscount(discountAmount);
        orderRepository.save(orderDDD);
    }
}
