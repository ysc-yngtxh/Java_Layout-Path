package com.example.普通队列;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 * @author 游家纨绔
 */
public class Consumer {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) {

        // TODO 在已经创建好队列情况下，创建消费者连接，指定名为"simple_queue"的队列消费消息。

        try {
            ConnectionFactory factory = new ConnectionFactory(); // 定义连接工厂
            // 获取到连接
            Connection connection = factory.newConnection();
            // 从连接中创建通道，使用通道才能完成消息相关的操作
            Channel channel = connection.createChannel();

            // 定义队列的消费者
            DefaultConsumer consumer = new DefaultConsumer(channel) {
                // 获取消息，并且处理，这个方法类似事件监听，如果有消息的时候，会被自动调用
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    // body 即消息体
                    String msg = new String(body);
                    System.out.println("[x] received:" + msg + "!");
                    // 手动进行ACK。ACK:消息确认机制
                    // channel.basicAck(envelope.getDeliveryTag(),false);
                    // 如果消息不太重要，丢失没多大影响，那么自动ACK比较方便。
                    // 但是消息非常重要的话，那么最好在消费完成后手动ACK，否则接收消息后就自动ACK.RabbitMQ就会把消息从队列中删除。如果此时消费者宕机，那么消息就丢失了。
                }
            };
            // 监听队列，第二个参数：true为自动应答
            channel.basicConsume(QUEUE_NAME, true, consumer);
        /*
          队列中的参数说明：
             参数1：为当前消费者需要监听的队列名，队列名必须要与发送时的队列名完全一致，否则接收不到消息
             参数2：为是否自动进行消息确认.true表示自动进行ACK，false表示手动ACK
             参数3：回调方法,为消息接收者的标签，用于多个消费者同时监听一个队列时用于确认不同消费者
             参数4：为消息接收的回调方法这个方法中具体完成对消息的处理代码
          注意：
               使用了basicConsumer方法以后，会启动一个线程在持续的监听队列，
               如果队列中有新的消息进入则会自动接收消息，因此不能关闭连接和通道对象，也就不需要finally语句
        */

            // 一旦在消费者的消息队列中关闭连接和通道对象，就会造成消息还没来得及接收就关闭了。
            // 所以这里为了能持续监听指定队列消息，这里暂不使用close()方法断开连接
            // channel.close();
            // connection.close();
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
