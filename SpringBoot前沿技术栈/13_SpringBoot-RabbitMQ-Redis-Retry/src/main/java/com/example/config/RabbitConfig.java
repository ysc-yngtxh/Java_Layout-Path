package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class RabbitConfig {
    // 基于 插件的队列 （避免用户设置延时队列造成消息等待----延时短的消息 等待 延时长的消息消费）
    @Bean("orderExchange")
    public CustomExchange OrderExChange(){
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("x-delayed-type", "direct");
        // 交换机名称  交换机类型  是否持久化   是否自动删除  其他
        return new CustomExchange("orderExchange", "x-delayed-message", true, false, map);
    }
    @Bean("orderQueue")
    public Queue Orderqueue(){
        return QueueBuilder
                .durable("orderQueue") // 设置持久化的队列名
                .deadLetterExchange("simpleDeadExchange")    // 设置死信交换机
                .deadLetterRoutingKey("simpleDeadRoutingKey")// 设置死信路由
                .build();
    }
    @Bean
    public Binding Orderbinding(@Qualifier("orderExchange") CustomExchange integrationExchange,
                                @Qualifier("orderQueue") Queue integrationQueue){
        return BindingBuilder.bind(integrationQueue).to(integrationExchange).with("orderRoutingKey").noargs();
    }


    // 基于 插件的队列 （避免用户设置延时队列造成消息等待----延时短的消息 等待 延时长的消息消费）
    @Bean("integrationExchange")
    public CustomExchange exchange(){
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("x-delayed-type", "direct");
        // 交换机名称  交换机类型  是否持久化   是否自动删除  其他
        return new CustomExchange("integrationExchange", "x-delayed-message", true, false, map);
    }
    @Bean("integrationQueue")
    public Queue queue(){
        Map<String, Object> map = new HashMap<>(3);
        map.put("x-dead-letter-exchange", "simpleDeadExchange");
        map.put("x-dead-letter-routing-key", "simpleDeadRoutingKey");
        return new Queue("integrationQueue", true, false, false, map);
    }
    @Bean
    public Binding binding(@Qualifier("integrationExchange") CustomExchange integrationExchange,
                           @Qualifier("integrationQueue") Queue integrationQueue){
        return BindingBuilder.bind(integrationQueue).to(integrationExchange).with("integrationRoutingKey").noargs();
    }


    // 死信队列
    @Bean("simpleDeadExchange")
    public DirectExchange simpleDeadExchange(){
        return new DirectExchange("simpleDeadExchange");
    }
    @Bean("simpleDeadQueue")
    public Queue simpleDeadQueue(){
        return new Queue("simpleDeadQueue");
    }
    @Bean
    public Binding simpleDeadRoutingKey(@Qualifier("simpleDeadExchange") DirectExchange directExchange,
                                        @Qualifier("simpleDeadQueue") Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with("simpleDeadRoutingKey");
    }
}
