package com.example.listener;

import com.alibaba.fastjson2.JSON;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-22 10:19
 * @apiNote TODO 顺序消费监听器
 */
@Component
@RocketMQMessageListener(topic = "bootOrderlyTopic",  // 主题
        consumerGroup = "Orderly-group",              // 消费者组
        consumeMode = ConsumeMode.ORDERLY)            // 消费模式(并发模式，顺序模式)
public class OrderlyMsgListener implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {
        MessageExt parseMessageExt = JSON.parseObject(new String(messageExt.getBody()), MessageExt.class);
        System.out.println(parseMessageExt);
    }
}
