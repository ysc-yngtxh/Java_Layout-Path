package com.example.ddd.infrastucture.repository;

import com.example.ddd.domain.repository.OrderRepository;
import com.example.ddd.domain.valobj.OrderItem;
import com.example.ddd.infrastucture.convertor.OrderItemToOrderItemPO;
import com.example.ddd.infrastucture.convertor.OrderToOrderPO;
import com.example.ddd.infrastucture.dao.OrderDDDDao;
import com.example.ddd.infrastucture.dao.OrderItemDDDDao;
import com.example.ddd.domain.entity.OrderDDD;
import com.example.ddd.infrastucture.po.OrderPO;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO Order仓库
 */
@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderDDDDao orderDDDDao;
    private final OrderItemDDDDao orderItemDDDDao;
    private final OrderToOrderPO orderToOrderPO;
    private final OrderItemToOrderItemPO orderItemToOrderitemPO;

    public OrderRepositoryImpl(OrderDDDDao orderDDDDao, OrderItemDDDDao orderItemDDDDao,
                               OrderToOrderPO orderToOrderPO,
                               OrderItemToOrderItemPO orderItemToOrderitemPO) {
        this.orderDDDDao = orderDDDDao;
        this.orderItemDDDDao = orderItemDDDDao;
        this.orderToOrderPO = orderToOrderPO;
        this.orderItemToOrderitemPO = orderItemToOrderitemPO;
    }

    @Override
    public void save(OrderDDD orderDDD){
        // 在此处通过Order实体，创建数据对象  new OrderPO();  new OrderItemPO();
        OrderPO orderPO = orderToOrderPO.toOrderPO(orderDDD);
        // orderDDDDao => 存储订单数据
        orderDDDDao.insert(orderPO);
    }

    @Override
    public OrderDDD findById(Long orderId) {
        // 找到数据对象，OrderPO
        OrderPO orderPO = orderDDDDao.findByOrderId(orderId);
        // 找到数据对象，OrderItemPO
        Set<OrderItem> orderItemSet =
                orderItemDDDDao.getOrderItems(orderId).stream().map(orderItemToOrderitemPO::toOrderItem).collect(Collectors.toSet());
        // 组合返回，order实体
        return new OrderDDD(orderPO.getOrderId(), orderPO.getBuyerId(), orderPO.getSellerId(), orderPO.getShippingFee(), orderPO.getDiscountAmount(), orderPO.getAddress(), orderItemSet);
    }
}
