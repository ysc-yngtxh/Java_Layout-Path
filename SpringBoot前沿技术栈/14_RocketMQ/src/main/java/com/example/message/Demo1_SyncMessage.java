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
 * @dateTime 2023-10-21 18:09
 * @apiNote TODO 发送同步消息
 */
@SpringBootTest(classes = Demo1_SyncMessage.class)
public class Demo1_SyncMessage {

    // 发送同步消息：这种可靠性同步地发送方式使用的比较广泛，比如：重要的消息通知，短信通知。
    @Test
    public void SyncMessage() throws Exception {
        // 实例化消息生产者 -- 生产组(Sync_Group)
        DefaultMQProducer producer = new DefaultMQProducer("Sync_Group");
        producer.setNamesrvAddr("localhost:9876"); // 设置NameServer的地址
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 10; i++) {
            String MessageKey = UUID.randomUUID().toString();
            // 创建消息，并指定Topic，Tag和消息Key与消息体（Tag和消息Key不是必写参数）
            Message msg = new Message(
                    "TopicSync" /* Topic主题 */,
                    "TagA"      /* Tag标签 */,
                    MessageKey  /* 消息Key，用来作为业务消息标识的去重或指定 */,
                    ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息体 */
            );
            // TODO 发送消息存储到 Broker，在Broker里的每一个主题(Topic)消息默认读队列4个、写队列4个。[可以自定义队列数]
            //  循环发送的消息虽然都是相同主题，但是循环10次发送的消息并不是存放在一条写队列中，而是分别写入存储在4条写队列里。
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s %n", sendResult);      // 通过sendResult返回消息是否成功送达
        }
        // 如果不再发送消息，关闭Producer实例。
        producer.shutdown();
    }


    // 消费消息
    public static void main(String[] args) throws MQClientException {
        // 实例化消息Push消费者 -- 消费组
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer("Sync_Group");
        // 实例化Push消费者，虽然API是过时的，但我们也需要去了解。
        // DefaultMQPullConsumer pullConsumer = new DefaultMQPullConsumer("Sync_Group");
        // TODO
        //  1、Push是MQ主动推送消息给客户端
        //     优点：及时性好；
        //     缺点：客户端没有做好流控，一旦服务端推送大量消息到客户端时，就会导致客户端消息堆积甚至崩溃。
        //  2、Pull是客户端主动到服务端拉取数据
        //     优点：可以依据自己的消费能力进行消费；
        //     缺点：拉取的频率需要用户自己控制，拉取频繁容易造成服务端和客户端的压力，拉取间隔长又容易造成消费不及时。
        //  3、Push模式也是基于Pull模式的，只是客户端内部封装了API。
        //     一般场景下，上游消息生产量小或者均速的时候，选择Push模式；
        //     在特殊场景下，例如电商大促，抢优惠券等场景可以选择Pull模式

        // 设置NameServer的地址
        pushConsumer.setNamesrvAddr("localhost:9876");

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        pushConsumer.subscribe("TopicSync", "*");
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
