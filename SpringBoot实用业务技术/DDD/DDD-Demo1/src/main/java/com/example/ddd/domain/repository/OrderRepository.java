package com.example.ddd.domain.repository;

import com.example.ddd.domain.entity.Order;
import com.example.ddd.infrastucture.po.OrderPO;

import java.util.List;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 * @Description TODO Order仓库
 */
public interface OrderRepository {

    void save(Order order);

    Order findById(Long id);
}
