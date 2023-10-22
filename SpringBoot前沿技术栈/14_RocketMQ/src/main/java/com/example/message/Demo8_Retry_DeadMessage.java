package com.example.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-21 23:03
 * @apiNote TODO 重试与死信
 */
@SpringBootTest(classes = Demo8_Retry_DeadMessage.class)
public class Demo8_Retry_DeadMessage {

    /**
     * 重试时间间隔： 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     * 默认重试16次(并发模式)、顺序模式下(Integer.MAX_VALUE)次
     * 如果重试都失败 -- 会变成死信消息，放到一个死信主题(%DLQ%Retry_Provider_Group) 【 %DLQ% 为固定写法 】
     */
    @Test
    public void retryProvider() throws Exception {
        // 实例化消息生产者 -- 生产组(Sync_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Retry_Dead_Group");
        producer.setNamesrvAddr("localhost:9876"); // 设置NameServer的地址
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 10; i++) {
            String MessageKey = UUID.randomUUID().toString();
            // 创建消息，并指定Topic，Tag和消息Key与消息体（Tag和消息Key不是必写参数）
            Message msg = new Message("TopicRetry", "TagRetry", MessageKey, ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)
            );
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s %n", sendResult);      // 通过sendResult返回消息是否成功送达
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

    // 消费消息
    public static void main(String[] args) throws MQClientException {
        // 实例化消息Push消费者 -- 消费组
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("Retry_Dead_Group");
        pushConsumer.setNamesrvAddr("localhost:9876");
        pushConsumer.subscribe("TopicRetry", "*");
        // 设置重试次数
        pushConsumer.setMaxReconsumeTimes(2);
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                System.out.printf("%s 接收队列的新消息: %s %n",
                        Thread.currentThread().getName(), list.stream().map(MessageExt::getBody).map(String::new).toList());
                // TODO 业务报错了，比如可以在cache中返回 RECONSUME_LATER ，就会进行重试，重试达到上限该消息变为死信
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
        // 启动消费者实例
        pushConsumer.start();
    }

    // -------------- 处理消费者业务报错重试失败的第一种方案：监听死信消息，记录信息通知人工处理
    public static void main1(String[] args) throws MQClientException {
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("Retry_Dead_Group");
        pushConsumer.setNamesrvAddr("localhost:9876");
        // TODO 注意这里订阅的主题应该是死信主题：%DLQ%Retry_Dead_Group
        pushConsumer.subscribe("%DLQ%Retry_Dead_Group", "*");
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                System.out.println("记录到特别的文件中或者mysql通知人工处理");
                // 因为消息进入到了死信中，所以可以在这里 记录到特别的文件中、mysql通知人工处理
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        pushConsumer.start();
    }

    // -------------- 处理消费者业务报错重试失败的第二种方案：不使用死信，直接逻辑代码转人工处理
    public static void main2(String[] args) throws MQClientException {
        // 实例化消息Push消费者 -- 消费组
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("Retry_Dead_Group");
        // 设置NameServer的地址
        pushConsumer.setNamesrvAddr("localhost:9876");
        pushConsumer.subscribe("Retry_Dead_Group", "*");
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext context) {
                try {
                    // 业务代码......
                } catch (Exception e) {
                    // 获取重试次数
                    int reconsumeTimes = list.get(0).getReconsumeTimes();
                    if (reconsumeTimes > 3) {
                        // 不用再重试了
                        System.out.println("记录到特别的文件中或者mysql通知人工处理");
                        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    }
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; // 重试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者实例
        pushConsumer.start();
    }
}
