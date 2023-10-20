package com.example.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.ArrayList;

/**
 * 5、发送集合消息
 */
public class Producer5_ListMessage {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者 -- 生产组(ListMessage_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("ListMessage_Provider_Group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        ArrayList<Message> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message(
                    "TopicList" /* Topic */,
                    "TagA" /* Tag */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            list.add(msg);
        }
        // 发送消息到一个Broker
        SendResult sendResult = producer.send(list);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
