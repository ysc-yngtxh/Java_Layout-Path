package com.example.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:40
 * @apiNote TODO Sql语法过滤消息
 */
public class SqlFilterConsumer {

    private static final String NAME_SERVER_ADDR = "localhost:9876";

    public static void main(String[] args) throws MQBrokerException, RemotingException, InterruptedException, MQClientException {
        // 实例化消息生产者 -- 生产组(SqlFilterConsumer)
        DefaultMQProducer producer = new DefaultMQProducer("SqlFilterConsumer");
        // 设置NameServer的地址
        producer.setNamesrvAddr(NAME_SERVER_ADDR);
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(
                    "TopicTest" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(StandardCharsets.UTF_8) /* Message body */
            );
            // TODO 发送消息时，通过putUserProperty来设置消息的属性
            msg.putUserProperty("a", String.valueOf(i));
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();


        // 实例化消费者 -- 消费组(SqlFilterConsumer)
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("SqlFilterConsumer");
        // 设置NameServer的地址
        consumer.setNamesrvAddr(NAME_SERVER_ADDR);
        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        consumer.subscribe("TopicTest", "*");
        // TODO 通过发送消息时设置的属性来进行过滤，使用SQL表达式筛选消息。
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));
        // 注册回调实现类来处理从broker拉取回来的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.printf("%s Receive New Messages: %s %n", Thread.currentThread().getName(), msgs);
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者实例
        consumer.start();
    }
}
