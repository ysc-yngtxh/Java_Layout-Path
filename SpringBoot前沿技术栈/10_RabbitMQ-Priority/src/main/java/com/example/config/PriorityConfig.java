package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class PriorityConfig {

    // 交换机
    @Bean("priorityExchange")
    public DirectExchange exChange(){
        return new DirectExchange("priorityExchange", true, false);
    }

    // 队列
    @Bean("priorityQueue")
    public Queue directQueue(){
        Map<String, Object> map = new HashMap<>(1);
        // 这里设置最大优先级为10。RabbitMq中优先级0~255，但我们没必要设置那么大，越大越耗内存占CPU
        map.put("x-max-priority", 10);
        return new Queue("priorityQueue", true, false, false, map);
    }

    // 绑定交换机和队列
    @Bean
    public Binding queueBinding(@Qualifier("priorityExchange") DirectExchange directExchange,
                                @Qualifier("priorityQueue") Queue directQueue){
        return BindingBuilder.bind(directQueue).to(directExchange).with("priorityRoutingKey");
    }
}
