package com.example;

import com.alibaba.fastjson.JSON;
import com.example.domain.MsgModel;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
class RocketBootApplicationTests {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Test
    void contextLoads1() {
        // 1、同步消息  参数（主题、消息体）
        rocketMQTemplate.syncSend("bootTestTopic", "我是bootTest的一个同步消息");
        // 消费者通过第一个参数字符中的 : 进行分割 -- 前面的是主题，后面的是Tag标签
        rocketMQTemplate.syncSend("bootTestTopic:Tag", "我是bootTest的一个带有Tag的同步消息");

        // 2、异步消息
        rocketMQTemplate.asyncSend("bootTestTopic", "我是bootTest的一个异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {}
            @Override
            public void onException(Throwable throwable) {}
        });

        // 3、单向消息（只负责发送消息，不等待应答，不关心发送结果，如日志）
        rocketMQTemplate.sendOneWay("bootTestTopic", "我是bootTest的一个单向消息");

        // 4、延迟消息
        Message<String> msg = MessageBuilder.withPayload("我是一个延迟消息").build();
        // 主题、消息体、超时时长、延时等级
        rocketMQTemplate.syncSend("bootMsTopic", msg, 3000, 3);

        // 5、顺序消息
        List<MsgModel> msgModelList = Arrays.asList(
                new MsgModel("华为meta60pro", 1, "下单"),
                new MsgModel("华为meta60pro", 1, "付款"),
                new MsgModel("华为meta60pro", 1, "物流"),
                new MsgModel("华为问界M7", 2, "下单"),
                new MsgModel("华为问界M7", 2, "付款"),
                new MsgModel("华为问界M7", 2, "物流")
        );
        msgModelList.forEach(msgModel -> {
            rocketMQTemplate.syncSendOrderly("bootOrderlyTopic"
                                           , JSON.toJSONString(msgModel)
                                           , msgModel.getOrderSn()
            );
        });

        // 6、带有消息头的消息
        Message<String> msgKey = MessageBuilder.withPayload("我是一个带消息Key的消息")
                                .setHeader(RocketMQHeaders.KEYS, "qwertyuiop")
                                .build();
        rocketMQTemplate.syncSend("bootKeyTopic", msgKey);
    }

    @Test
    void contextLoads2() {
        SendResult sendResult = rocketMQTemplate.syncSend("bootBatchTopic", "我是测试消息！！！");
        System.out.printf("RocketMQ 消息发送到Broker，Broker会将消息进行持久化处理。" +
                          "持久化成功后，Broker给生产者响应消息写入结果（ACK响应）。通过返回的结果判断是否成功送达。" +
                          "返回的结果为：%s %n"
                        , sendResult.getSendStatus()
        );
        System.out.printf("消息 Id（RocketMQ系统生成） = %s %n", sendResult.getMsgId());
        System.out.printf("消息队列 = %s %n", sendResult.getMessageQueue());
    }
}
