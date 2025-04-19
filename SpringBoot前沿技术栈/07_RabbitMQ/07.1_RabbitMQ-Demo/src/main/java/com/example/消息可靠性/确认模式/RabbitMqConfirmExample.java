package com.example.消息可靠性.确认模式;

import com.example.RabbitMqUtils;
import com.rabbitmq.client.Channel;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-10 16:36
 * @apiNote TODO
 */
public class RabbitMqConfirmExample {
    private static final String QUEUE_NAME = "queue_name";

    public static void main(String[] args) {
        try {
            Channel channel = RabbitMqUtils.getChannel();

            // 设置信道为 confirm 确认模式
            channel.confirmSelect();

            String message = "Hello, RabbitMQ!";

            // 发布消息到队列
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            // waitForConfirms() 方法等待确认
            if (channel.waitForConfirms()) {
                System.out.println("Message sent successfully");
            } else {
                System.out.println("Failed to send message");
            }
        } catch (Exception e) {
            System.out.println("Failed to send message");
            e.printStackTrace();
        }
    }
}
