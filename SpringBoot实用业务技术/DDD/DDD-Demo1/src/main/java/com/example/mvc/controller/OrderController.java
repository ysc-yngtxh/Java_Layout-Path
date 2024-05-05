package com.example.mvc.controller;

import com.example.mvc.entity.OrderItem;
import com.example.mvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-22 下午11:33
 * @apiNote TODO
 */
@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @RequestMapping("/order")
    public void createOrder() {
        OrderItem orderItem = new OrderItem(1L, 1L, new BigDecimal("2"));
        orderService.createOrder(1, 2, Collections.singletonList(orderItem));
    }
}
