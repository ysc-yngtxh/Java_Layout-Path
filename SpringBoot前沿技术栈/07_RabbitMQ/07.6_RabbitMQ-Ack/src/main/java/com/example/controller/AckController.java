package com.example.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AckController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/ack/{message}/{ttl}")
    public void sendMsg(@PathVariable String message, @PathVariable Long ttl) {
        log.info("当前时间：{},发送一条消息给需要「手动确认消息」队列：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , message);
        rabbitTemplate.convertAndSend("ackExchange"
                                    , "ackRoutingKey"
                                    , "消息来自于用户定义延迟" + ttl + "s的队列：" + message
                                    , msg -> {
                                        // 设置消息的延迟时间
                                        msg.getMessageProperties().setDelayLong(ttl*1000);
                                        return msg;
                                    }
        );
    }
}
