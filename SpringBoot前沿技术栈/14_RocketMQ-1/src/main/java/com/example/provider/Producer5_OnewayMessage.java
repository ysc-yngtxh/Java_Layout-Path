package com.example.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 3、单向发送消息：主要用在不特别关心发送结果的场景，这种方式吞吐量很大，但是有消息丢失的风险。例如日志发送--丢就丢了不重要。
 */
public class Producer5_OnewayMessage {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者 -- 生产组(Oneway_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Oneway_Provider_Group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("TopicOneway", "TagA", ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */);
            // TODO 发送消息存储到 Broker，在Broker里的每一个主题(Topic)消息默认读队列4个、写队列4个。[可以自定义队列数]
            //  循环发送的消息虽然都是相同主题，但是循环发送的消息并不是存放在一条写队列中，而是分别写入存储在4条写队列里。
            producer.sendOneway(msg);  // 发送单向消息，没有任何返回结果
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}