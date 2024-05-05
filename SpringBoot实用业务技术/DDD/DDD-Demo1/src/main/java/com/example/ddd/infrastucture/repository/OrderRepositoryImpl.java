package com.example.ddd.infrastucture.repository;

import com.example.ddd.domain.repository.OrderRepository;
import com.example.ddd.domain.valobj.OrderItem;
import com.example.ddd.infrastucture.convertor.OrderItemToOrderItemPO;
import com.example.ddd.infrastucture.convertor.OrderToOrderPO;
import com.example.ddd.infrastucture.dao.OrderDao;
import com.example.ddd.infrastucture.dao.OrderItemDao;
import com.example.ddd.domain.entity.Order;
import com.example.ddd.infrastucture.po.OrderPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO Order仓库
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDao orderDao;
    private final OrderItemDao orderItemDao;
    private final OrderToOrderPO orderToOrderPO;
    private final OrderItemToOrderItemPO orderItemToOrderitemPO;

    public OrderRepositoryImpl(OrderDao orderDao, OrderItemDao orderItemDao,
                               OrderToOrderPO orderToOrderPO,
                               OrderItemToOrderItemPO orderItemToOrderitemPO) {
        this.orderDao = orderDao;
        this.orderItemDao = orderItemDao;
        this.orderToOrderPO = orderToOrderPO;
        this.orderItemToOrderitemPO = orderItemToOrderitemPO;
    }

    @Override
    public void save(Order order){
        // 在此处通过Order实体，创建数据对象  new OrderPO();  new OrderItemPO();
        OrderPO orderPO = orderToOrderPO.toOrderPO(order);
        // orderDao => 存储订单数据
        orderDao.insert(orderPO);
    }

    @Override
    public Order findById(Long orderId) {
        // 找到数据对象，OrderPO
        OrderPO orderPO = orderDao.findByOrderId(orderId);
        // 找到数据对象，OrderItemPO
        Set<OrderItem> orderItemSet =
                orderItemDao.getOrderItems(orderId).stream().map(orderItemToOrderitemPO::toOrderItem).collect(Collectors.toSet());
        // 组合返回，order实体
        return new Order(orderPO.getOrderId(), orderPO.getBuyerId(), orderPO.getSellerId(), orderPO.getShippingFee(), orderPO.getDiscountAmount(), orderPO.getAddress(), orderItemSet);
    }
}
