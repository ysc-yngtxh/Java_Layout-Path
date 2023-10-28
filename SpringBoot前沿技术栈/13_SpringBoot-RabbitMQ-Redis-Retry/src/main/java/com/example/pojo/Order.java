package com.example.join;

import java.io.Serializable;

public class Order implements Serializable {

    private static final long serialVersionUID = -2378574269516834695L;
    private String orderId; // 订单id

    private Integer orderStatus; // 订单状态 0：未支付，1：已支付，2：订单已取消

    private String orderName; // 订单名字

    public Order() {
    }

    public Order(String orderId, Integer orderStatus, String orderName) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.orderName = orderName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", orderStatus=" + orderStatus +
                ", orderName='" + orderName + '\'' +
                '}';
    }
}