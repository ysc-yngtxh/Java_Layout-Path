package com.example.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-18 22:45
 * @apiNote TODO Headers 类型交换机控制器
 */
@RestController
public class HeadersController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("/headers1/{msg}")
    public void header(@PathVariable String msg) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("queue", "headerQueue1");
        // 使用 Headers 交换机时，设置的头属性不进行对齐
        messageProperties.setHeader("bindType", "头属性不进行对齐");
        Message message = new Message(msg.getBytes(), messageProperties);
        System.out.println("发送消息：" + msg);
        rabbitTemplate.convertAndSend("headerExchange", null, message);
    }

    @RequestMapping("/headers2/{msg}")
    public void header2(@PathVariable String msg) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("queue", "headerQueue2");
        // 使用 Headers 交换机时，设置的头属性不进行对齐
        messageProperties.setHeader("bindType", "头属性不进行对齐");
        Message message = new Message(msg.getBytes(), messageProperties);
        System.out.println("发送消息：" + msg);
        rabbitTemplate.convertAndSend("headerExchange", null, message);
    }
}
