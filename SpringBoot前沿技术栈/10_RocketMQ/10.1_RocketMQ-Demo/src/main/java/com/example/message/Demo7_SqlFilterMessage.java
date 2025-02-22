package com.example.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-21 18:09
 * @apiNote TODO 过滤消息
 */
@SpringBootTest(classes = Demo7_SqlFilterMessage.class)
public class Demo7_SqlFilterMessage {
    // 过滤消息：通过在发送消息时放置用户属性，消费时订阅属性范围
    @Test
    public void SqlFilterMessage() throws Exception {
        // 实例化消息生产者 -- 生产组(SqlFilterConsumer)
        DefaultMQProducer producer = new DefaultMQProducer("SqlFilterConsumer");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicSqlFilter", "TagSqlFilter", ("Hello RocketMQ " + i).getBytes(StandardCharsets.UTF_8));
            // TODO 发送消息时，通过putUserProperty来设置消息的属性
            msg.putUserProperty("a", String.valueOf(i));
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            System.out.printf("RocketMQ 消息发送到Broker，Broker会将消息进行持久化处理。" +
                    "持久化成功后，Broker给生产者响应消息写入结果（ACK响应）。通过返回的结果判断是否成功送达。" +
                    "返回的结果为：%s %n", sendResult.getSendStatus());
            System.out.printf("%s %n", sendResult);      // 打印返回结果
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

    // 消费消息
    public static void main(String[] args) throws MQClientException {
        // 实例化消费者 -- 消费组(SqlFilterConsumer)【注意：消费分组不必与生产分组保持一致】
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("SqlFilterConsumer");
        // 设置NameServer的地址
        consumer.setNamesrvAddr("localhost:9876");
        // TODO 订阅Topic 通过发送消息时设置的属性来进行过滤，使用SQL表达式筛选消息。
        //      需要在broker.conf文件中添加属性 enablePropertyFilter=true；
        //      然后重新启动broker，并指定刚刚修改的conf配置文件
        //      sh mqbroker -n localhost:9876 -c ../conf/broker.conf
        consumer.subscribe("TopicSqlFilter", MessageSelector.bySql("a between 0 and 3"));
        // pushConsumer.registerMessageListener() 注册消息监听器
        // MessageListenerConcurrently 并发模式，多线程的。相当于多线程去处理从broker拉取回来的消息
        // MessageListenerOrderly 顺序模式，单线程的。单线程去处理从broker拉取回来的消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                // MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型（我建议还是指定类型较方便）
                // System.out.printf：支持使用字符信息的格式化
                System.out.printf("%s 接收队列%s的新消息: %s %n",
                        Thread.currentThread().getName(), list.stream().map(MessageExt::getQueueId).toList(),
                        list.stream().map(MessageExt::getBody).map(String::new).toList());
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者实例
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
