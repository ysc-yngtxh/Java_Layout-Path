package com.example.ddd.domain.repository;

import com.example.ddd.domain.entity.OrderDDD;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO Order仓库
 */
public interface OrderRepository {

    void save(OrderDDD orderDDD);

    OrderDDD findById(Long id);
}
