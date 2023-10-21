package com.example.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Consumer {

    private static final String NAME_SERVER_ADDR = "localhost:9876";

    public static void main(String[] args) throws Exception {
        String groupConsumer = null;
        String topic = null;
        System.err.println("请输入数字，选择合适的消费组、主题：\n[1] Provider1_Sync、[2] Provider2_ASync、" +
                "[3] Provider3_List、[4] Provider4_Order、[5] Provider5_Oneway、[6] Provider6_Delay");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int nextInt = scanner.nextInt();
            if (nextInt > 0) {
                switch (nextInt) {
                    case 1 -> {
                        groupConsumer = "Sync_Provider_Group";
                        topic = "TopicSync";
                        break;
                    }
                    case 2 -> {
                        groupConsumer = "Async_Provider_Group";
                        topic = "TopicAsync";
                        break;
                    }
                    case 3 -> {
                        groupConsumer = "ListMessage_Provider_Group";
                        topic = "TopicList";
                        break;
                    }
                    case 4 -> {
                        groupConsumer = "Order_Provider_Group";
                        topic = "TopicOrder";
                        break;
                    }
                    case 5 -> {
                        groupConsumer = "Oneway_Provider_Group";
                        topic = "TopicOneway";
                        break;
                    }
                    case 6 -> {
                        groupConsumer = "DelayMessage_Provider_Group";
                        topic = "TestDelay";
                        break;
                    }
                }
                // 调用相应消费组的消息进行消费
                Consumption(groupConsumer, topic);
            }
        }
    }

    private static void Consumption(String groupConsumer, String Topic) throws MQClientException, IOException {
        // 实例化Push消费者 -- 消费组
        DefaultMQPushConsumer pushConsumer = new DefaultMQPushConsumer(groupConsumer);
        // 实例化Push消费者，虽然API是过时的，但我们也需要去了解。
        DefaultMQPullConsumer pullConsumer = new DefaultMQPullConsumer(groupConsumer);
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
        pushConsumer.setNamesrvAddr(NAME_SERVER_ADDR);

        // 订阅一个或者多个Topic，以及Tag来过滤需要消费的消息
        pushConsumer.subscribe(Topic, "*");
        // TODO Tag是一个简单而有用的设计，其可以来选择您想要的消息。
        // consumer.subscribe("TopicTest", "TagA || TagB || TagC");

        // 注册回调实现类来处理从broker拉取回来的消息
        pushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                // MessageExt：是一个消息接收通配符，不管发送的是String还是对象，都可接收，当然也可以像上面明确指定类型（我建议还是指定类型较方便）
                // System.out.printf：支持使用字符信息的格式化
                System.out.printf("%s 接收新消息: %s %n",
                        Thread.currentThread().getName(), msgs.stream().map(MessageExt::getBody).map(String::new).toList());
                // 标记该消息已经被成功消费
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        // 启动消费者实例
        pushConsumer.start();
        System.out.printf("Consumer Started.%n");
        System.in.read();
    }
}