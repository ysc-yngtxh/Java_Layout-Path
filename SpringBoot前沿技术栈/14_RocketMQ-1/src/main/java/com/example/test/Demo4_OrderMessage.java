package com.example.test;

import com.example.domain.MsgModel;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-21 18:09
 * @apiNote TODO 发送同步消息
 */
@SpringBootTest(classes = Demo4_OrderMessage.class)
public class Demo4_OrderMessage {
    /**
     * Broker里的每一个主题(Topic)消息默认读队列4个、写队列4个。
     * <p>
     * 同一主题(Topic)不考虑标签(Tag)情况下，每次生产者发送消息写入的可能不是一个队列。
     * 消费者订阅 pushConsumer.subscribe(Topic, "*"); 去消费这个主题数据，该先去消费默认四个队列中的哪个队列呢？
     * <p>
     * 假设：同一主题下，生产消息的依次写入，存储队列为[2, 3, 1, 0]；但是消费时多线程轮询方式读取，消费队列是[0, 1, 2, 3]，
     * 就会造成一个读取数据顺序错误，从而造成业务混乱。
     * <p>
     * 方案：将多线程读取方式换为单线程
     * 4、顺序发送消息：发送时要确保有序。如何保证有序？
     */
    private static final List<MsgModel> msgModelList = Arrays.asList(
            new MsgModel("华为meta60pro", 1, "下单"),
            new MsgModel("华为meta60pro", 1, "付款"),
            new MsgModel("华为meta60pro", 1, "物流"),
            new MsgModel("华为问界M7", 2, "下单"),
            new MsgModel("华为问界M7", 2, "付款"),
            new MsgModel("华为问界M7", 2, "物流")
    );


    @Test
    public void OrderMessage() throws Exception {
        // 实例化消息生产者 -- 生产组(Order_Provider_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Order_Provider_Group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("localhost:9876");
        // 启动Producer实例
        producer.start();
        // 正常发送消息，选择的队列是根据MQ规则来着，不是顺序存储四个写队列的
        // 发送顺序消息，发送时要确保有序，并且要发到同一个队列下面去
        msgModelList.forEach(msgModel -> {
            Message msg = new Message("TopicOrder", "TagOrder", msgModel.toString().getBytes(StandardCharsets.UTF_8));
            try {
                // TODO 发送消息存储到Broker，在Broker里的每一个主题(Topic)消息默认读队列4个、写队列4个。[可以自定义队列数]
                //  循环发送的消息虽然都是相同主题，但是循环发送的消息并不是存放在一条写队列中，而是分别写入存储在4条写队列里。
                producer.send(msg, new MessageQueueSelector() {
                    // TODO 该方法主要是通过第二个参数实现相同的订单号去相同的队列，而不会分散在其他队列里
                    //      producer.send()第一个参数：发送的消息（存入队列中的数据）
                    //      producer.send()第二个参数：选择当前写入的数据存入哪个写队列
                    //      producer.send()第三个参数：用于回调给producer.send()第二个参数中重写select()方法参数
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object obj) {
                        // List<MessageQueue> list：表示该主题下的队列集合，默认读队列数是4，写队列数为4（生产者是写队列）
                        // Message message：就是执行producer.send()第一个参数(写入的消息)回调到这里
                        // Object obj：就是producer.send()第三个参数回调到这里
                        System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
                        System.out.println(obj);
                        // 通常obj是一条消息的标识，将标识通过哈希算法得到的值，然后取队列数的模。最后得到的就是当前消息应当分配的队列
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
            } catch (MQClientException | InterruptedException | MQBrokerException | RemotingException e) {
                throw new RuntimeException(e);
            }
        });
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
        System.out.println("发送完成");
    }

    // 消费消息
    public static void main(String[] args) throws MQClientException {
        // 实例化消息Push消费者 -- 消费组
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("Order_Provider_Group");
        // 设置NameServer的地址
        pushConsumer.setNamesrvAddr("localhost:9876");

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        pushConsumer.subscribe("TopicOrder", "*");
        // Tag是一个简单而有用的设计，其可以来选择您想要的消息。
        // pushConsumer.subscribe("TopicTest", "TagA || TagB || TagC");

        // pushConsumer.registerMessageListener() 注册消息监听器
        // MessageListenerConcurrently 并发模式，多线程的。相当于多线程去处理从broker拉取回来的消息
        // TODO MessageListenerOrderly 顺序模式，单线程的。单线程去处理从broker拉取回来的消息
        pushConsumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                // MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型（我建议还是指定类型较方便）
                // System.out.printf：支持使用字符信息的格式化
                // 标记该消息已经被成功消费
                list.forEach(msg -> {
                    System.out.printf("%s 接收队列%s新消息: %s %n",
                            Thread.currentThread().getName(), list.stream().map(MessageExt::getQueueId).toList(),
                            list.stream().map(MessageExt::getBody).map(String::new).toList());
                });
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });
        // 启动消费者实例
        pushConsumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
