package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  // 标注这是个配置类，同时注入进spring容器
public class AckConfig {

    // 配置一个Direct类型的普通交换机
    @Bean("ackExchange")
    public DirectExchange directExchange(){
        // 交换机名称   是否持久化  是否自动删除
        return new DirectExchange("ackExchange", true, false);
    }

    // 配置一个普通队列  TTL不配置 延迟队列时长掌握在生产者手里
    @Bean("ackQueue")
    public Queue directQueueC(){
        return QueueBuilder
                .durable("ackQueue")
                .build();
    }

    // 配置一个普通队列C和普通交换机的绑定
    @Bean
    public Binding directBindingC(@Qualifier("ackExchange") DirectExchange ackExchange,
                                  @Qualifier("ackQueue") Queue ackQueue){
        return BindingBuilder.bind(ackQueue).to(ackExchange).with("ackRoutingKey");
        /*
           BindingBuilder.bind(ackQueue)：指定队列
           to(ackExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("ackRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }
}
