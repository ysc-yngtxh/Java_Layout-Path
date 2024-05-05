package com.example.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 基于死信队列 的延迟消息
 */
@Slf4j
@Component
public class DeadLetterConsumer {

    // 接收消息。这里监听的是死信队列，因为没有消费者，最终的消息都会流到死信中
    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "deadQueue")
    public void receive(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }
}
