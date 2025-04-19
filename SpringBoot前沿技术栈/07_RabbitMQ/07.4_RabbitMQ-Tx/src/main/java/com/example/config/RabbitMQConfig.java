package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-10 23:01
 * @apiNote TODO
 */
@Configuration
public class RabbitMQConfig {

    // 配置一个Direct类型的普通交换机
    @Bean("txDirectExchange")
    public DirectExchange directExchange() {
        // 交换机名称   是否持久化  是否自动删除
        return new DirectExchange("txDirectExchange", true, false);
    }

    // 配置一个普通队列
    @Bean("txDirectQueue")
    public Queue directQueue() {
        return QueueBuilder
                .durable("txDirectQueue")
                // .maxLength(10) // 设置队列最大长度
                // .deadLetterExchange("deadExchange")  // 死信交换机声明
                // .deadLetterRoutingKey("deadRoutingKey") // 死信消息的路由key
                // .ttl(40000) // 消息过期时间设置 超出时间未消费成为死信
                .build();
    }

    // 配置一个普通队列和普通交换机的绑定
    @Bean
    public Binding directBindingC(@Qualifier("txDirectExchange") DirectExchange txDirectExchange,
                                  @Qualifier("txDirectQueue") Queue txDirectQueue) {
        return BindingBuilder.bind(txDirectQueue).to(txDirectExchange).with("txDirectRoutingKey");
        /*
           BindingBuilder.bind(txDirectQueue)：指定队列
           to(txDirectExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("txDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }
}
