package com.example.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration  // 标注这是个配置类，同时注入进spring容器
public class BootRabbitMQConfig {

    // 配置一个Direct类型的普通交换机
    @Bean("bootDirectExchange")
    public DirectExchange directExchange() {
        // 交换机名称   是否持久化  是否自动删除
        return new DirectExchange("bootDirectExchange", true, false);
    }

    // 配置一个普通队列A  TTL 为 10S
    @Bean("bootDirectQueueA")
    public Queue directQueueA() {
        // TODO 第一种写法
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("x-dead-letter-exchange", "deadExchange");      // 设置死信交换机
        map.put("x-dead-letter-routing-key", "deadRoutingKey"); // 设置死信routing-key
        map.put("x-message-ttl", 10000); // 设置消息过期时间
        // map.put("x-queue-mode", "lazy"); // 设置为惰性队列
        // map.put("x-max-length", 10);     // 设置消息队列的长度
        // 参数1、队列名称  2、是否持久化  3、是否排外  4、如果队列空了是否自动删除  5、死信设置
        return new Queue("bootDirectQueueA", true, false, false, map);
    }

    // 配置一个普通队列B  TTL 为 40S
    @Bean("bootDirectQueueB")
    public Queue directQueueB() {
        // TODO 第二种写法
        return QueueBuilder
                // .nonDurable("bootDirectQueueB") 交换机不进行持久化
                .durable("bootDirectQueueB")
                // 设置队列最大长度
                .withArgument("x-max-length", 10)
                // 死信交换机声明
                .withArgument("x-dead-letter-exchange", "deadExchange")
                // 死信消息的路由key
                .withArgument("x-dead-letter-routing-key", "deadRoutingKey")
                // 消息过期时间设置 超出时间未消费成为死信
                .withArgument("x-message-ttl", 40000)
                // .withArgument("x-queue-mode", "lazy") 设置为惰性队列
                // .exclusive() 是否排外
                // .autoDelete() 如果队列空了是否自动删除
                .build();
    }

    // 配置一个普通队列C  TTL不配置 延迟队列时长掌握在生产者手里
    @Bean("bootDirectQueueC")
    public Queue directQueueC() {
        // TODO 第三种写法
        return QueueBuilder
                .durable("bootDirectQueueC")
                // .maxLength(10) // 设置队列最大长度
                .deadLetterExchange("deadExchange")  // 死信交换机声明
                .deadLetterRoutingKey("deadRoutingKey") // 死信消息的路由key
                // .ttl(40000) // 消息过期时间设置 超出时间未消费成为死信
                // .lazy() // 设置为惰性队列
                .build();
    }

    // 配置一个普通队列A和普通交换机的绑定
    @Bean
    public Binding directBindingA(@Qualifier("bootDirectExchange") DirectExchange directExchange,
                                  @Qualifier("bootDirectQueueA") Queue directQueueA) {
        return BindingBuilder.bind(directQueueA).to(directExchange).with("bootDirectRoutingKeyA");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }

    // 配置一个普通队列B和普通交换机的绑定
    @Bean
    public Binding directBindingB(@Qualifier("bootDirectExchange") DirectExchange directExchange,
                                  @Qualifier("bootDirectQueueB") Queue directQueueB) {
        return BindingBuilder.bind(directQueueB).to(directExchange).with("bootDirectRoutingKeyB");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }

    // 配置一个普通队列C和普通交换机的绑定
    @Bean
    public Binding directBindingC(@Qualifier("bootDirectExchange") DirectExchange directExchange,
                                  @Qualifier("bootDirectQueueC") Queue directQueueC) {
        return BindingBuilder.bind(directQueueC).to(directExchange).with("bootDirectRoutingKeyC");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
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
    public Binding deadBinding(@Qualifier("deadExchange") DirectExchange directExchange,
                               @Qualifier("deadQueue") Queue directQueue) {
        return BindingBuilder.bind(directQueue).to(directExchange).with("deadRoutingKey");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("deadRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }
}
