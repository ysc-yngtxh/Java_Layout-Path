package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class PriorityController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 这里操作的话，先将监听priorityQueue队列的方法注释掉，再发消息到队列。最后再打开注释进行消费，可以发现i=5优先消费
    @GetMapping("/priority/{message}")
    public void send(@PathVariable String message) {
        for(int i = 1; i < 11; i++) {
            CorrelationData data = new CorrelationData(i + "");
            if (i == 5) {
                rabbitTemplate.convertAndSend("priorityExchange"
                        , "priorityRoutingKey"
                        , message + i
                        , msg -> {
                            msg.getMessageProperties().setPriority(5);
                            return msg;
                        }
                        , data);
            } else {
                rabbitTemplate.convertAndSend("priorityExchange"
                        , "priorityRoutingKey"
                        , message + i
                        , msg -> {
                            msg.getMessageProperties().setPriority(1);
                            return msg;
                        }
                        , data);
            }
        }
    }
}
