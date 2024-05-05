package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = "confirmQueue")
    public void sendMessage(Message message) {
        String msg = new String(message.getBody());
        log.info("正常队列接收到的消息：{}", msg);
    }
}
