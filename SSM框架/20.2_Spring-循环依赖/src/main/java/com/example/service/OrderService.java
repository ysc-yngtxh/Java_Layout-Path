package com.example.service;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 22:10
 * @apiNote TODO
 */
public class OrderService {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public OrderService() {
    }

    public OrderService(UserService userService) {
        this.userService = userService;
    }
}
