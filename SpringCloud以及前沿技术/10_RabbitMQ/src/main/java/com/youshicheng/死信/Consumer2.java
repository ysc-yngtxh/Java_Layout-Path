package com.youshicheng.死信;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.youshicheng.RabbitMqUtils;

import java.io.IOException;

/**
 * 死信消费者C2
 */
public class Consumer2 {
    //死信队列名称
    public static final String DEAD_QUEUE = "dead_queue";

    public static void main(String[] args) {
        try {
            //通过封装类获取Channel信道
            Channel channel = RabbitMqUtils.getChannel();

            System.out.println("等待接收消息......");

            DeliverCallback callback = (consumerTag, message) -> {
                System.out.println("Consumer2接受的消息是：" + new String(message.getBody(),"UTF-8"));
            };
            channel.basicConsume(DEAD_QUEUE, true, callback, consumer -> {});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
