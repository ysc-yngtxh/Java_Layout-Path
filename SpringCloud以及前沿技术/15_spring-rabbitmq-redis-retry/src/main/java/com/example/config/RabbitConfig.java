package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class RabbitConfig {
    //基于 插件的队列 （避免用户设置延时队列造成消息等待----延时短的消息 等待 延时长的消息消费）
    @Bean("OrderExchange")
    public CustomExchange OrderExChange(){
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("x-delayed-type","direct");
        //交换机名称  交换机类型  是否持久化   是否自动删除  其他
        return new CustomExchange("OrderExchange","x-delayed-message",true,false,map);
    }
    @Bean("OrderQueue")
    public Queue Orderqueue(){
        Map<String, Object> map = new HashMap<>(3);
        map.put("x-dead-lettle-exchange","yscdeadExchange");
        map.put("x-dead-lettle-routing-key","yscdeadroutingkey");
        return new Queue("OrderQueue",true,false,false,map);
    }
    @Bean
    public Binding Orderbinding(@Qualifier("OrderExchange") CustomExchange integrationExchange,
                           @Qualifier("OrderQueue") Queue integrationQueue){
        return BindingBuilder.bind(integrationQueue).to(integrationExchange).with("Orderroutingkey").noargs();
    }

    //基于 插件的队列 （避免用户设置延时队列造成消息等待----延时短的消息 等待 延时长的消息消费）
    @Bean("integrationExchange")
    public CustomExchange exchange(){
        HashMap<String, Object> map = new HashMap<>(1);
        map.put("x-delayed-type","direct");
        //交换机名称  交换机类型  是否持久化   是否自动删除  其他
        return new CustomExchange("integrationExchange","x-delayed-message",true,false,map);
    }
    @Bean("integrationQueue")
    public Queue queue(){
        Map<String, Object> map = new HashMap<>(3);
        map.put("x-dead-lettle-exchange","yscdeadExchange");
        map.put("x-dead-lettle-routing-key","yscdeadroutingkey");
        return new Queue("integrationQueue",true,false,false,map);
    }
    @Bean
    public Binding binding(@Qualifier("integrationExchange") CustomExchange integrationExchange,
                           @Qualifier("integrationQueue") Queue integrationQueue){
        return BindingBuilder.bind(integrationQueue).to(integrationExchange).with("integrationroutingkey").noargs();
    }


    //死信队列
    @Bean("yscdeadExchange")
    public DirectExchange yscdeadexchange(){
        return new DirectExchange("yscdeadExchange");
    }
    @Bean("yscdeadQueue")
    public Queue yscdeadqueue(){
        return new Queue("yscdeadQueue");
    }
    @Bean
    public Binding yscdeadbinding(@Qualifier("yscdeadExchange") DirectExchange Exchange,
                           @Qualifier("yscdeadQueue") Queue Queue){
        return BindingBuilder.bind(Queue).to(Exchange).with("yscdeadroutingkey");
    }


}
