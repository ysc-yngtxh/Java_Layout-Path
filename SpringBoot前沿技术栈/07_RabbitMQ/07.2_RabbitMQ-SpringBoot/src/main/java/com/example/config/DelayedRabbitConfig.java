package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class DelayedRabbitConfig {

    // 安装插件的交换机
    @Bean("delayedExchange")
    public CustomExchange delayedExchange() { // 这个表示自定义类型的交换机
        HashMap<String, Object> map = new HashMap<>(1);
        // 定义交换机分发消息类型，direct、fanout、topic、header
        map.put("x-delayed-type", "direct");
        // 交换机名称  交换机类型  是否持久化  是否自动删除  其他
        return new CustomExchange("delayedExchange", "x-delayed-message", true, false, map);
    }

    // 安装插件的队列
    @Bean("delayedQueue")
    public Queue delayedQueue() {
        // 队列名称  是否持久化  是否排外  是否自动删除
        return new Queue("delayedQueue", true, false, false);
    }

    // 配置一个安装插件的交换机与队列的绑定
    @Bean
    public Binding delayedBinding(@Qualifier("delayedExchange") CustomExchange customExchange, // 注意这里交换机类型
                                  @Qualifier("delayedQueue") Queue directQueue) {
        return BindingBuilder.bind(directQueue).to(customExchange).with("delayedRoutingKey").noargs();
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
           noargs()：构建。因为这里的交换机是自己自定义的，所以需要构建。
        */
    }
}
