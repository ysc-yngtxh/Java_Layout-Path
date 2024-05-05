package com.example.死信队列;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.example.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 死信消费者C2
 */
public class Consumer2 {
    // 死信队列名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) {
        try {
            // 通过封装类获取Channel信道
            Channel channel = RabbitMqUtils.getChannel();

            System.out.println("等待接收消息......");

            DeliverCallback callback = (consumerTag, message) -> {
                System.out.println("Consumer2接受的消息是：" + new String(message.getBody(), StandardCharsets.UTF_8));
            };
            // 第二个参数为true：开启自动应答
            channel.basicConsume(DEAD_QUEUE, true, callback, consumer -> {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
