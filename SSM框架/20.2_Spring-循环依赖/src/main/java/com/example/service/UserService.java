package com.example.service;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 22:08
 * @apiNote TODO
 */
public class UserService {
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public UserService(OrderService orderService) {
        this.orderService = orderService;
    }

    public UserService() {
    }
}
