package com.example.demo.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于死信队列 的延迟消息
 */
@Slf4j
@Component
public class DeadletterConsumer {

    // 接收消息。这里监听的是死信队列，因为没有消费者，最终的消息都会流到死信中
    @RabbitListener(queues = "deadQueue")
    public void receive(Message message , Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{},发送一条消息给两个TTL队列：{}"
                ,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                ,msg);
    }
}
