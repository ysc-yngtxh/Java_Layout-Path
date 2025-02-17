package com.example.handler;

import com.example.listener.DynamicMessageListener;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-17 19:00
 * @apiNote TODO 消息处理器注册中心
 */
@Component
public class HandlerRegistry {

    private final DynamicMessageListener dynamicMessageListener;

    public HandlerRegistry(DynamicMessageListener dynamicMessageListener) {
        this.dynamicMessageListener = dynamicMessageListener;
    }

    // 提供动态注册方法
    public void addHandler(String messageType, MessageHandler handler) {
        dynamicMessageListener.registerHandler(messageType, handler);
    }

    // 提供动态注销方法
    public void removeHandler(String messageType) {
        dynamicMessageListener.unregisterHandler(messageType);
    }

    @PostConstruct
    public void initHandlers() {
        // 注册订单处理器
        dynamicMessageListener.registerHandler("order", (payload, channel, deliveryTag) -> {
            Order order = (Order) payload;
            // 处理订单逻辑...
            channel.basicAck(deliveryTag, false);
        });

        // 注册支付处理器
        dynamicMessageListener.registerHandler("payment", (payload, channel, deliveryTag) -> {
            Payment payment = (Payment) payload;
            // 处理支付逻辑...
            channel.basicAck(deliveryTag, false);
        });
    }
}