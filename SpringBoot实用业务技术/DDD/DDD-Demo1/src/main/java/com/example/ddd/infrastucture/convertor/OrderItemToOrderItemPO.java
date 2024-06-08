package com.example.ddd.infrastucture.convertor;

import com.example.ddd.domain.valobj.OrderItem;
import com.example.ddd.infrastucture.po.OrderItemPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-21 下午11:48
 * @apiNote TODO
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderItemToOrderItemPO {

    @Mappings({
            @Mapping(target = "orderId", source = "orderId"),
            @Mapping(target = "itemId", source = "itemId"),
            @Mapping(target = "price", source = "price")
    })
    OrderItemPO toOrderItemPO(OrderItem orderItem);


    @Mappings({
            @Mapping(target = "orderId", source = "orderId"),
            @Mapping(target = "itemId", source = "itemId"),
            @Mapping(target = "price", source = "price")
    })
    OrderItem toOrderItem(OrderItemPO orderItemPO);
}
