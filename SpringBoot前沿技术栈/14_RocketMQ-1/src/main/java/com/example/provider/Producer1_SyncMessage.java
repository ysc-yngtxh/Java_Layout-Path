package com.example.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * 1、发送同步消息：这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
 */
public class Producer1_SyncMessage {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者 -- 生产组(Sync_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Sync_Provider_Group");
        producer.setNamesrvAddr("localhost:9876"); // 设置NameServer的地址
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体（也可以不指定Tag）
            Message msg = new Message(
                    "TopicSync" /* Topic */,
                    "TagA"      /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            SendResult sendResult = producer.send(msg); // 发送消息到一个Broker
            System.out.printf("%s%n", sendResult);      // 通过sendResult返回消息是否成功送达
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}