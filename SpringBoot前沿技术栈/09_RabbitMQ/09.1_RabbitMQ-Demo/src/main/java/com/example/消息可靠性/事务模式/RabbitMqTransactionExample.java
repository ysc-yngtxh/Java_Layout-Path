package com.example.消息可靠性.事务模式;

import com.example.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-10 16:33
 * @apiNote TODO
 */
public class RabbitMqTransactionExample {

    private static final String QUEUE_NAME = "queue_name";

    public static void main(String[] args) {
        Channel channel = null;
        try {
            // 获取信道
            channel = RabbitMqUtils.getChannel();
            // 声明(创建)队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 开启事务
            channel.txSelect();

            String message = "Hello, RabbitMQ!";

            // 发布消息到队列
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            // 提交事务
            channel.txCommit();
            System.out.println("Message sent successfully");
        } catch (Exception e) {
            // 回滚事务
            try {
                channel.txRollback();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Failed to send message");
            e.printStackTrace();
        }
    }
}