package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/ttl")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    // 1、开始发消息：在队列中设置的消息延迟
    @GetMapping("/sendMsg/{message}")
    public void sendMsg(@PathVariable String message){
        log.info("当前时间：{},发送一条消息给两个TTL队列：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , message);
        rabbitTemplate.convertAndSend("bootDirectExchange", "bootDirectRoutingKeyA", "消息来自ttl为10s的队列：" + message);
        rabbitTemplate.convertAndSend("bootDirectExchange", "bootDirectRoutingKeyB", "消息来自ttl为40s的队列：" + message);

        /* 这里随便访问一下：http://localhost:8080/ttl/sendMsg/你好
           可以发现在死信队列中会依次出现 bootDirectQueueA、bootDirectQueueB，间隔时间30s。
           虽然延迟队列很好用，但是想想，如果延迟队列需求很多，那是不是也要声明需要新的队列。
           所以，为了优化延迟队列，我们可以选择在生产者上加延迟时间，想延迟多久用户决定。
        */
    }


    // 手动消费延迟队列中的消息
    @GetMapping("/receive")
    public void receive() {
        Object received = rabbitTemplate.receiveAndConvert("bootDirectQueueB");
        log.info("当前时间：{},手动消费延迟队列中的消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , received);
    }


    // 2、开始发消息：在生产者中设置的消息过期时长
    @GetMapping("/sendExpirationMsg/{message}/{ttlTime}")
    public void sendMsg(@PathVariable String message, @PathVariable Integer ttlTime){
        log.info("当前时间：{},发送一条时长{}毫秒TTL信息给队列bootDirectQueueC：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , ttlTime
                , message);
        // 第四个参数：对消息进行配置
        rabbitTemplate.convertAndSend("bootDirectExchange", "bootDirectRoutingKeyC", message, msg -> {
            // 发送消息的时候 延长时长。
            // 在消息的TTL倒计时期间，如果消息被消费者正常消费，那么TTL机制就不会起作用。
            // 然而，如果消息在TTL倒计时结束之前没有被消费，那么这条消息就会变为死信。
            msg.getMessageProperties().setExpiration( String.valueOf(ttlTime) );
            return msg;
        });

        /* 这种情况下虽然只需要注册一个队列，便可在用户端设置延迟时长。但是，出现了一个很严重的问题！
           依次发起请求：http://localhost:8080/ttl/sendExpirationMsg/你好1/20000
                       http://localhost:8080/ttl/sendExpirationMsg/你好2/2000
               我们会发现死信队列中依次出现的是 '你好1'、'你好2'。这就很奇怪，因为'你好2'延迟时间明显短于'你好1'
            因为RabbitMQ只会检查第一个消息是否过期，如果过期，则丢到死信队列。
            如果第一个消息的延时时长很长，而第二个消息的延时时长很短，第二个消息并不会优先得到执行
         */
    }


    // 3、开始发消息（基于插件的）    路径参数：/sendDelayMsg/{消息}/{延迟的时间}
    @GetMapping("/sendDelayMsg/{message}/{delayTime}")
    public void sendDelayMsg(@PathVariable String message, @PathVariable Integer delayTime) {
        log.info("当前时间：{},发送一条时长{}毫秒信息给延迟队列delayerQueue：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , delayTime
                , message);
        rabbitTemplate.convertAndSend("delayedExchange", "delayedRoutingKey", message, msg -> {
            // 发送消息的时候 延长时长
            // 死信队列的延迟是在队列中进行的，而基于插件的延迟队列是在交换机中实现的
            msg.getMessageProperties().setDelayLong(Long.valueOf(delayTime));
            return msg;
        });
    }
}
