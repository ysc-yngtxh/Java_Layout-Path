package com.example.provider;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * 5、发送集合消息
 */
public class Producer3_ListMessage {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者 -- 生产组(ListMessage_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("ListMessage_Provider_Group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        ArrayList<Message> list = new ArrayList<>();
        list.add(new Message("TopicList", "TagA", ("Hello TagA").getBytes(StandardCharsets.UTF_8)));
        list.add(new Message("TopicList", "TagB", ("Hello TagB").getBytes(StandardCharsets.UTF_8)));
        list.add(new Message("TopicList", "TagC", ("Hello TagC").getBytes(StandardCharsets.UTF_8)));
        // TODO 发送 Collection集合 消息到Broker。发送的 Collection集合 消息会被存储在同一个队列里。
        //  可以这么理解：每执行一次send()方法，相当于重新选择队列写入存储消息。
        SendResult sendResult = producer.send(list);
        // 通过sendResult返回消息是否成功送达
        System.out.printf("%s%n", sendResult);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }
}
