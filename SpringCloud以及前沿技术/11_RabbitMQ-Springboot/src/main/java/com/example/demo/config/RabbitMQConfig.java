package com.example.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration  // 标注这是个配置类，同时注入进spring容器
public class RabbitMQConfig {

    // 配置一个Direct类型的普通交换机
    @Bean("directExchange")
    public DirectExchange directExchange(){
        // 交换机名称   是否持久化  是否自动删除
        return new DirectExchange("bootDirectExchange", true, false);
    }

    // 配置一个普通队列A  TTL 为 10S
    @Bean("directQueueA")
    public Queue directQueueA(){
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("x-dead-letter-exchange", "deadExchange");  // 设置死信交换机
        map.put("x-dead-letter-routing-key", "deadroutingkey");// 设置死信routing-key
        map.put("x-message-ttl", 10000); // 设置消息过期时间
        // map.put("x-max-length", 10); // 设置消息队列的长度
        // 参数1、队列名称  2、是否持久化   3、是否排外  4、如果队列空了是否自动删除   5、死信设置
        return new Queue("bootDirectQueueA", true, false, false, map);
    }

    // 配置一个普通队列B  TTL 为 40S
    @Bean("directQueueB")
    public Queue directQueueB(){
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("x-dead-letter-exchange","deadExchange");  // 设置死信交换机
        map.put("x-dead-letter-routing-key","deadroutingkey");// 设置死信routingkey
        map.put("x-message-ttl",40000); // 设置消息过期时间
        // map.put("x-max-length",10); // 设置消息队列的长度
        // 参数1、队列名称  2、是否持久化   3、是否排外  4、如果队列空了是否自动删除   5、死信设置
        return new Queue("bootDirectQueueB",true,false,false,map);
    }

    // 配置一个普通队列B  TTL不配置 延迟队列时长掌握在生产者手里
    @Bean("directQueueC")
    public Queue directQueueC(){
        HashMap<String, Object> map = new HashMap<>(3);
        map.put("x-dead-letter-exchange","deadExchange");  // 设置死信交换机
        map.put("x-dead-letter-routing-key","deadroutingkey");// 设置死信routingkey
        // map.put("x-message-ttl",40000); // 设置消息过期时间
        // map.put("x-max-length",10); // 设置消息队列的长度
        // 参数1、队列名称  2、是否持久化   3、是否排外  4、如果队列空了是否自动删除   5、死信设置
        return new Queue("bootDirectQueueC",true,false,false,map);
    }

    // 配置一个普通队列和普通交换机的绑定
    @Bean
    public Binding directBindingA(@Qualifier("directExchange") DirectExchange directExchange,
                                 @Qualifier("directQueueA") Queue directQueueA){

        return BindingBuilder.bind(directQueueA).to(directExchange).with("bootDirectRoutingKeyA");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }

    // 配置一个普通队列和普通交换机的绑定
    @Bean
    public Binding directBindingB(@Qualifier("directExchange") DirectExchange directExchange,
                                  @Qualifier("directQueueB") Queue directQueueB){

        return BindingBuilder.bind(directQueueB).to(directExchange).with("bootDirectRoutingKeyB");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }

    // 配置一个普通队列和普通交换机的绑定
    @Bean
    public Binding directBindingC(@Qualifier("directExchange") DirectExchange directExchange,
                                  @Qualifier("directQueueC") Queue directQueueC){

        return BindingBuilder.bind(directQueueC).to(directExchange).with("bootDirectRoutingKeyC");
        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }

    // 配置一个Direct类型的死信交换机
    @Bean("deadExchange")
    public DirectExchange deadExchange(){
        return new DirectExchange("deadExchange",true,false);
    }

    // 配置一个死信队列
    @Bean("deadQueue")
    public Queue deadQueue(){
        return new Queue("deadQueue");
    }

    // 配置一个死信队列和死信交换机的绑定
    @Bean
    public Binding deadBinding(@Qualifier("deadExchange") DirectExchange directExchange,
                               @Qualifier("deadQueue") Queue directQueue){

        return BindingBuilder.bind(directQueue).to(directExchange).with("deadroutingkey");

        /*
           BindingBuilder.bind(directQueue)：指定队列
           to(directExchange)：to英语翻译为到，达。所以这句话的意思是指定队列到交换机
           with("bootDirectRoutingKey")：根据routingKey的值来绑定队列和交换机
        */
    }
}
