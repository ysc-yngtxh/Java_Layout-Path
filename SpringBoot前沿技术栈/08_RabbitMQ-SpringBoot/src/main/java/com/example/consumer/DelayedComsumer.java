package com.example.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于插件的 延迟信息
 */
@Slf4j
@Component
public class DelayedComsumer {

    // 接收消息。这里监听的是基于插件的队列，这里当做是消费者
    @RabbitListener(queues = "delayedQueue")
    public void receive(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{},发送一条消息给两个TTL队列：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }
}
