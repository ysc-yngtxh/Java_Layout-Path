package com.example.provider;

import com.example.domain.MsgModel;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 4、顺序发送消息：
 */
public class Producer4_OrderMessage {
    private static final List<MsgModel> msgModelList = Arrays.asList(
            new MsgModel("华为meta60pro", 1, "下单"),
            new MsgModel("华为meta60pro", 1, "付款"),
            new MsgModel("华为meta60pro", 1, "物流"),
            new MsgModel("华为问界M7", 2, "下单"),
            new MsgModel("华为问界M7", 2, "付款"),
            new MsgModel("华为问界M7", 2, "物流")
    );
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者 -- 生产组(Oneway_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Order_Provider_Group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        // 根据消息数量实例化倒计时计算器（JUC）
        final CountDownLatch2 countDownLatch = new CountDownLatch2(10);
        // 发送顺序消息，发送时要确保有序，并且要发到同一个队列下面去
        msgModelList.forEach(msgModel -> {
            Message msg = new Message(
                    "TopicOrder"  /* Topic */,
                    "TagOrder"    /* Tag */,
                    msgModel.toString().getBytes(StandardCharsets.UTF_8) /* Message body */
            );
            // 发相同的订单号去相同的队列
            try {
                producer.send(msg, new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object obj) {
                        // List<MessageQueue> list：表示该主题下的队列数，默认读队列数是4，写队列数为4（生产者是写队列）
                        // Message message：就是执行producer.send()第一个参数，回调到这里
                        // Object obj：就是producer.send()第三个参数，回调到这里
                        System.out.println(message);
                        System.out.println(obj);
                        // 在这里选择队列
                        int hashCode = obj.toString().hashCode();
                        // 4(哈希算法得到的数) % 4(队列数) = 0
                        // 5(哈希算法得到的数) % 4(队列数) = 1
                        // 6(哈希算法得到的数) % 4(队列数) = 2
                        // 7(哈希算法得到的数) % 4(队列数) = 3
                        // 8(哈希算法得到的数) % 4(队列数) = 0  周期性函数
                        int i = Math.abs(hashCode % list.size());
                        return list.get(i);
                    }
                }, msgModel.getOrderSn());
                countDownLatch.countDown();
            } catch (MQClientException | InterruptedException | MQBrokerException | RemotingException e) {
                throw new RuntimeException(e);
            }
        });
        countDownLatch.await(5, TimeUnit.SECONDS);
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
        System.out.println("发送完成");
    }
}
