package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackupsConfig {

    // 交换机
    @Bean("backupsExchange")
    public DirectExchange confirmExchange(){
        return ExchangeBuilder
                .directExchange("backupsExchange")
                .durable(true)
                // 以下两种写法都表示声明备用交换机。"alternate-exchange"为固定写法，"fanoutExchange"为备用交换机的名称
                // .alternate("fanoutExchange")
                // .withArgument("alternate-exchange", "fanoutExchange")
                .build();
    }
    // 队列
    @Bean("backupsQueue")
    public Queue directQueue(){
        return QueueBuilder.durable("backupsQueue").build();
    }
    // 绑定交换机和队列
    @Bean
    public Binding queueBinding(@Qualifier("backupsExchange") DirectExchange backupsExchange,
                                @Qualifier("backupsQueue") Queue backupsQueue){
        return BindingBuilder.bind(backupsQueue).to(backupsExchange).with("backupsRoutingKey");
    }


    // 备份交换机
    @Bean("fanoutExchange")
    public FanoutExchange fanoutExchange(){
        return ExchangeBuilder.fanoutExchange("fanoutExchange").durable(true).build();
    }
    // 备份队列
    @Bean("fanoutQueue")
    public Queue fanoutQueue(){
        return QueueBuilder.durable("fanoutQueue").build();
    }
    // 警告队列
    @Bean("warningQueue")
    public Queue waringQueue(){
        return QueueBuilder.durable("warningQueue").build();
    }
    // 绑定备份交换机和备份队列
    @Bean
    public Binding fanoutBinding(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                 @Qualifier("fanoutQueue") Queue fanoutQueue){
        // 这里没有routingKey,因为使用的是广播(Fanout)类型交换机，我对自己交换机类型下的所有队列开放进行广播，还需要什么路由key？
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }
    // 绑定备份交换机和警告队列
    @Bean
    public Binding warningBinding(@Qualifier("fanoutExchange") FanoutExchange fanoutExchange,
                                  @Qualifier("warningQueue") Queue warningQueue){
        // 这里没有routingKey,因为使用的是广播(Fanout)类型交换机，我对自己交换机类型下的所有队列开放进行广播，还需要什么路由key？
        return BindingBuilder.bind(warningQueue).to(fanoutExchange);
    }
}
