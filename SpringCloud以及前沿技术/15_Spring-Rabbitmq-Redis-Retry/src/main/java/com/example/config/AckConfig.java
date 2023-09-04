package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class AckConfig implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnsCallback(this);
    }

    // 消息发布确认的回调方法
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String id = correlationData != null ? correlationData.getId() : "";
        if (ack) {
            log.info("交换机已经收到Id为：{}的消息",id);
        } else {
            log.info("交换机还未收到Id为：{}的消息，由于原因:{}",id,cause);
        }
    }

    // 消息路由失败的回退处理
    @Override
    public void returnedMessage(ReturnedMessage returned) {
        // 请注意!如果你使用了延迟队列插件，那么一定会调用该callback方法，因为数据并没有发布出去，而是提交在交换器中，过期时间到了才发布出去，
        // 并非是bug！你可以用if进行判断交换机名称来捕捉该报错
        if("integrationExchange".equals(returned.getExchange()) || "OrderExchange".equals(returned.getExchange())){
            return;
        }
        log.error("消息:{}，被交换机{}退回，退回原因：{}，路由{}"
                , new String(returned.getMessage().getBody())
                , returned.getExchange()
                , returned.getReplyText()
                , returned.getRoutingKey()
        );
    }
}
