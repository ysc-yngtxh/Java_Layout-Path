package com.example.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import javax.crypto.spec.PSource;
import java.util.concurrent.TimeUnit;

/**
 * 2、发送异步消息：异步消息通常用在对响应时间敏感的业务场景，即发送端不能容忍长时间地等待Broker的响应。
 */
public class Producer2_AsyncMessage {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者 -- 生产组(Async_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Async_Provider_Group");
        producer.setNamesrvAddr("localhost:9876");    // 设置NameServer的地址
        producer.start();                             // 启动Producer实例
        producer.setRetryTimesWhenSendAsyncFailed(0); // 设置重试次数

        // 根据消息数量实例化倒计时计算器（JUC）
        final CountDownLatch2 countDownLatch = new CountDownLatch2(10);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            // 创建消息，并指定Topic，Tag和消息Key与消息体
            Message msg = new Message(
                    "TopicAsync" /* Topic主题 */,
                    "TagA"       /* Tag标签 */,
                    "OrderID188" /* 消息Key */,
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
        }
        System.out.println("异步发送消息，那么我这里先执行，不管MQ有没有执行完！");
        // 等待3s
        countDownLatch.await(3, TimeUnit.SECONDS);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}