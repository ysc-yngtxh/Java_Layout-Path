package com.example.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-22 10:01
 * @apiNote TODO 实现SpringBoot简易的RocketMQ监听
 */
@Component
@RocketMQMessageListener(
        topic = "bootTestTopic",                    // 主题
        consumerGroup = "springboot-consumer-group") // 消费者组
public class MiniMsgListener implements RocketMQListener<MessageExt> {

    // 这个重写方法就是消费者消费消息的方法。没有返回值(消费状态)。
    // 这个方法正常执行，表示消费成功；如果出现异常，消息拒收，并且进行重试
    @Override
    public void onMessage(MessageExt msg) {
        System.out.println(new String(msg.getBody(), StandardCharsets.UTF_8));
    }
}
