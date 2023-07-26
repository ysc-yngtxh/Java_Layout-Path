package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfirmConfig {

    // 交换机
    @Bean("confirmExchange")
    public DirectExchange exChange(){
        return ExchangeBuilder.directExchange("confirmExchange").durable(true)
                .withArgument("alternate-exchange","fanoutExchange").build();
    }
    // 队列
    @Bean("confirmQueue")
    public Queue directQueue(){
        return new Queue("confirmQueue",true,false,false);
    }
    // 绑定交换机和队列
    @Bean
    public Binding queueBinding(@Qualifier("confirmExchange") DirectExchange directExchange,
                                @Qualifier("confirmQueue") Queue directQueue){
        return BindingBuilder.bind(directQueue).to(directExchange).with("confirmroutingkey");
    }


    // 备份交换机
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchang(){
        return new FanoutExchange("fanoutExchange",true,false);
    }
    // 备份队列
    @Bean("fanoutQueue")
    public Queue fanoutQueue(){
        return new Queue("fanoutQueue");
    }
    // 警告队列
    @Bean("warningQueue")
    public Queue waringQueue(){
        return new Queue("warningQueue");
    }
    // 绑定备份交换机和备份队列
    @Bean
    public Binding fanoutBinding(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                @Qualifier("fanoutQueue") Queue fanoutQueue){
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);  //这里没有routingkey,因为使用的是广播(Fanouot)类型交换机
    }
    // 绑定备份交换机和警告队列
    @Bean
    public Binding warningBinding(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                 @Qualifier("warningQueue") Queue warningQueue){
        return BindingBuilder.bind(warningQueue).to(fanoutExchange);  //这里没有routingkey,因为使用的是广播(Fanouot)类型交换机
    }
}
