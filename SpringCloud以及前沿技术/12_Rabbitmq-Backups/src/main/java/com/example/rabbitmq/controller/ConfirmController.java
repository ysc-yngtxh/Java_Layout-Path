package com.example.rabbitmq.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/confirm")
public class ConfirmController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //开始发消息  测试确认
    @GetMapping("/sendMessage/{message}")
    public void sendConfirm(@PathVariable String message){
        CorrelationData correlationData1 = new CorrelationData("1");
        rabbitTemplate.convertAndSend("confirmExchange","confirmroutingkey",message,correlationData1);
        log.info("发送消息内容：{}",message);

        CorrelationData correlationData2 = new CorrelationData("2");
        rabbitTemplate.convertAndSend("confirmExchange","confirmroutingkey2",message,correlationData2);
        log.info("发送消息内容：{}",message);
    }
}
