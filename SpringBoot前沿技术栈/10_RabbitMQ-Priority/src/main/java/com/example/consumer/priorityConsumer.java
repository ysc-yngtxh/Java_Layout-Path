package com.example.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class priorityConsumer {

    @RabbitListener(queues = "priorityQueue")
    public void priority(Message message){
        log.info("接收到的消息：{}", new String(message.getBody()));
    }
}
