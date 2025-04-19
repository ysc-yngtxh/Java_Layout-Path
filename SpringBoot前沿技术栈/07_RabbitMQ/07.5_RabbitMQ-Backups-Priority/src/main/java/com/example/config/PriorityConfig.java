package com.example.config;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PriorityConfig {

    // 交换机
    @Bean("priorityExchange")
    public DirectExchange priorityExchange() {
        return new DirectExchange("priorityExchange", true, false);
    }

    // 队列
    @Bean("priorityQueue")
    public Queue priorityQueue() {
        Map<String, Object> map = new HashMap<>(1);
        // 这里设置最大优先级为10。RabbitMQ中优先级0~255，但我们没必要设置那么大，越大越耗内存占CPU
        map.put("x-max-priority", 10);
        return new Queue("priorityQueue", true, false, false, map);
    }

    // 绑定交换机和队列
    @Bean
    public Binding priorityBinding(@Qualifier("priorityExchange") DirectExchange directExchange,
                                   @Qualifier("priorityQueue") Queue directQueue) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("priorityRoutingKey");
    }
}
