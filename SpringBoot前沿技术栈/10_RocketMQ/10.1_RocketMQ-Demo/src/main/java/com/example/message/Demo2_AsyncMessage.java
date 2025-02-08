package com.example.message;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-21 18:09
 * @apiNote TODO 发送异步消息
 */
@SpringBootTest(classes = Demo2_AsyncMessage.class)
public class Demo2_AsyncMessage {

    // 发送异步消息：异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
    @Test
    public void AsyncMessage() throws Exception {
        // 实例化消息生产者 -- 生产组(Async_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Async_Group");
        producer.setNamesrvAddr("localhost:9876");    // 设置NameServer的地址
        producer.start();                             // 启动Producer实例
        producer.setRetryTimesWhenSendAsyncFailed(0); // 设置重试次数

        // 根据消息数量实例化倒计时计算器（JUC）
        final CountDownLatch2 countDownLatch = new CountDownLatch2(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            String MessageKey = UUID.randomUUID().toString();
            // 创建消息，并指定Topic，Tag和消息Key与消息体（Tag和消息Key不是必写参数）
            Message msg = new Message(
                    "TopicAsync" /* Topic主题 */,
                    "TagA"       /* Tag标签 */,
                    MessageKey   /* 消息Key，用来作为业务消息标识的去重或指定 */,
                    ("Hello world"+1).getBytes(RemotingHelper.DEFAULT_CHARSET)/* 消息体 */);

            // TODO 发送消息存储到 Broker，在Broker里的每一个主题(Topic)消息默认读队列4个、写队列4个。[可以自定义队列数]
            //  循环发送的消息虽然都是相同主题，但是循环10次发送的消息并不是存放在一条写队列中，而是分别写入存储在4条写队列里。
            producer.send(msg, new SendCallback() {
                // TODO SendCallback接收异步返回结果的回调
                @Override
                public void onSuccess(SendResult sendResult) {
                    // 发送消息成功的回调函数，可以在这里处理业务逻辑
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    // 发送消息失败的回调函数
                    System.err.printf("%-10d Error %s %n", index, e);
                    e.printStackTrace();
                }
            });
            System.out.printf("循环第%s次完毕！！！%n", i);
        }
        System.out.println("异步发送消息，那么我这里先执行，不管MQ有没有执行完！");
        // 等待3s
        countDownLatch.await(3, TimeUnit.SECONDS);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }

    // 消费消息
    public static void main(String[] args) throws MQClientException {
        // 实例化消息Push消费者 -- 消费组【注意：消费分组不必与生产分组保持一致】
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("Async_Group");
        // 设置NameServer的地址
        pushConsumer.setNamesrvAddr("localhost:9876");

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        pushConsumer.subscribe("TopicAsync", "*");
        // Tag是一个简单而有用的设计，其可以来选择您想要的消息。
        // pushConsumer.subscribe("TopicTest", "TagA || TagB || TagC");

        // pushConsumer.registerMessageListener() 注册消息监听器
        // MessageListenerConcurrently 并发模式，多线程的。相当于多线程去处理从broker拉取回来的消息
        // MessageListenerOrderly 顺序模式，单线程的。单线程去处理从broker拉取回来的消息
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
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
        pushConsumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
