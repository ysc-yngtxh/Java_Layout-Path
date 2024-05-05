package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于插件的 延迟信息
 */
@Slf4j
@Component
public class DelayedConsumer {

    // 接收消息。这里监听的是基于插件的队列，这里当做是消费者
    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "delayedQueue")
    public void receive(Message message) {
        String msg = new String(message.getBody());

        log.info("当前时间：{},基于插件队列接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }
}
