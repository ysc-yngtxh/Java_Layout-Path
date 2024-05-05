package com.example.pojo;

import java.io.Serializable;

/**
 * 订单实体类
 */
public class Orders implements Serializable {
    private Integer id;
    private String orderTime;
    private Double money;

    private Integer uid; // 外键
    private User user;   // 一个订单属于一个用户

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderTime='" + orderTime + '\'' +
                ", money=" + money +
                ", uid=" + uid +
                ", user=" + user +
                '}';
    }
}
