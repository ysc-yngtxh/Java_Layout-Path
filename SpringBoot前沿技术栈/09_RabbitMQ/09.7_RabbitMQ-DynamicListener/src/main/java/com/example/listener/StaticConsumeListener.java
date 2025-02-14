package com.example.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

/**
 * 队列监听类（静态）
 */
@Slf4j
@Configuration
public class StaticConsumeListener {

    /**
     * 监听指定队列，名称：rabbitQueue2
     * @param message 消息内容
     * @param channel 信道
     */
    @RabbitListener(queues = "rabbitQueue2")
    public void consume(Message message, Channel channel) {
        log.info("StaticConsumeListener，收到消息: {}", message.toString());
    }
}