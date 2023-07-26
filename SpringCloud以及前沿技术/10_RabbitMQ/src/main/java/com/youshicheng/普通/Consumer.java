package com.youshicheng.普通;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 消费者
 *
 * @author 游家纨绔
 */
public class Consumer {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();// 定义连接工厂
        // TODO IP、端口，账号信息等都有默认的，可以不写，也可以自定义到想要的地址和账号中
        factory.setHost("localhost");// 设置服务地址
        factory.setPort(5672); // 端口

        // 设置账号信息，用户名，密码，vhost
        // factory.setVirtualHost("/guest");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();// 通过工程获取连接
        Channel channel = connection.createChannel();// 从连接中创建通道，使用通道才能完成消息相关的操作
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);// 声明(创建)队列
              /*
                 队列中的参数说明：
                    参数1：队列名取值任意
                    参数2：是否为持久化的队列
                    参数3：是否排外  如果排外了则这个队列只允许一个消费者监听
                    参数4：是否自动删除了，如果为true则表示当队列中没有消息，也没有消费者链接时就会自动删除这个队列
                    参数5：为队列的死信设置，通常为null即可
                 注意：
                    1、声明队列时，这个队列明称如果已经存在则放弃声明，如果队列不存在则会声明一个新的队列
                    2、队列名可以取值任意，但是要与消息接收时完全一致
                    3、这行代码是可有可无的但是一定要在发送消息前确认队列名已经存在在RabbitMQ中
               */

        /** 声明交换机,这里没用到交换机，所以不声明交换机
         channel.exchangeDeclare(QUEUE_NAME, "direct", true);
         交换机的参数说明:
         参数1：为队列的名称取值任意
         参数2：为交换机的类型 取值为 direct, fanout, topic, headers
         参数3：为是否为持久化交换机

         channel.queueBind(QUEUE_NAME,QUEUE_NAME, "ysc");
         绑定交换机与队列：
         参数1：队列名称
         参数2：交换机名称
         参数3：routing-key
         */

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

        channel.close();
        connection.close();// 一旦在消费者的消息队列中关闭连接和通道对象，就会造成消息还没来得及接收就关闭了
    }
}
