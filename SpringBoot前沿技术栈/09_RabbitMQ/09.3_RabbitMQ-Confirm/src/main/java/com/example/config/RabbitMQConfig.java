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
public class RabbitMQConfig {

    // 配置一个Direct类型的普通交换机
    @Bean("confirmDirectExchange")
    public DirectExchange directExchange(){
        // 交换机名称   是否持久化  是否自动删除
        return new DirectExchange("confirmDirectExchange", true, false);
    }
    // 配置一个普通队列
    @Bean("confirmDirectQueue")
    public Queue directQueue() {
        return QueueBuilder
                .durable("confirmDirectQueue")
                // .maxLength(10) // 设置队列最大长度
                .deadLetterExchange("deadExchange")  // 死信交换机声明
                .deadLetterRoutingKey("deadRoutingKey") // 死信消息的路由key
                // .ttl(40000) // 消息过期时间设置 超出时间未消费成为死信
                .build();
    }
    // 配置一个普通队列和普通交换机的绑定
    @Bean
    public Binding directBindingC(@Qualifier("confirmDirectExchange") DirectExchange confirmDirectExchange,
                                  @Qualifier("confirmDirectQueue") Queue confirmDirectQueue) {
        return BindingBuilder.bind(confirmDirectQueue).to(confirmDirectExchange).with("confirmDirectRoutingKey");
        /*
           BindingBuilder.bind(confirmDirectQueue)：指定队列
           to(confirmDirectExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("confirmDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }


    // 配置一个Direct类型的死信交换机
    @Bean("deadExchange")
    public DirectExchange deadExchange() {
        return new DirectExchange("deadExchange", true, false);
    }
    // 配置一个死信队列
    @Bean("deadQueue")
    public Queue deadQueue() {
        return new Queue("deadQueue");
    }
    // 配置一个死信队列和死信交换机的绑定
    @Bean
    public Binding deadBinding(@Qualifier("deadExchange") DirectExchange deadExchange,
                               @Qualifier("deadQueue") Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("deadRoutingKey");
        /*
           BindingBuilder.bind(deadQueue)：指定队列
           to(deadExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("deadRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }
}
