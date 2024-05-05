package com.example.ddd.infrastucture.convertor;

import com.example.ddd.domain.entity.Order;
import com.example.ddd.infrastucture.po.OrderPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mappings;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-21 下午11:36
 * @apiNote TODO
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderToOrderPO {

    @Mappings({
            @Mapping(target = "orderId", source = "orderId"),
            @Mapping(target = "buyerId", source = "buyerId"),
            @Mapping(target = "sellerId", source = "sellerId"),
            @Mapping(target = "shippingFee", source = "shippingFee"),
            @Mapping(target = "discountAmount", source = "discountAmount"),
            @Mapping(target = "address", source = "address")
    })
    OrderPO toOrderPO(Order order);
}
