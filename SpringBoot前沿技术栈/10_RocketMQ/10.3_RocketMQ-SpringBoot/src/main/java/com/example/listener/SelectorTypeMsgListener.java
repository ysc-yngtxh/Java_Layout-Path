package com.example.listener;

import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.annotation.SelectorType;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-22 10:01
 * @apiNote TODO 带有Tag消息的监听器
 */
@Component
@RocketMQMessageListener(
        topic = "bootTestTopic",                     // 主题
        consumerGroup = "springboot-listener-group", // 消费者组
        selectorType = SelectorType.TAG,            // 选择器类型(tag过滤模式)
        selectorExpression = "tagA || tagB")        // 只监听消息标签为 tagA 或 tagB 的消息
// @RocketMQMessageListener(
//         topic = "bootTestTopic",                     // 主题
//         consumerGroup = "springboot-listener-group", // 消费者组
//         selectorType = SelectorType.SQL92,           // 选择器类型(SQL92过滤模式)使用这种方式需要broker.conf中开启EnablePropertyFilter=true
//         selectorExpression = "a in(3,5,7)")          // 只监听消息标签值为 (3,5,7) 的消息
public class SelectorTypeMsgListener implements RocketMQListener<MessageExt> {

    // 这个重写方法就是消费者消费消息的方法。没有返回值(消费状态)。
    // 这个方法正常执行，表示消费成功；如果出现异常，消息拒收，并且进行重试
    @Override
    public void onMessage(MessageExt msg) {
        System.out.println(new String(msg.getBody(), StandardCharsets.UTF_8));
    }
}
