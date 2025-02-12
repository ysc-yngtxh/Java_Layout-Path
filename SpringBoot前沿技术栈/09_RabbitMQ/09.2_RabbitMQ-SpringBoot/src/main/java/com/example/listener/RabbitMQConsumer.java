package com.example.listener;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Component
public class RabbitMQConsumer {

    // 接收消息。这里监听的是基于插件的队列，这里当做是第一个消费者
    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "delayedQueue")
    public void receive1(Message message) {
        String msg = new String(message.getBody());

        log.info("当前时间：{},基于插件队列 -- 第一个监听器接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }

    // 接收消息。这里监听的是基于插件的队列，这里当做是第二个消费者
    @RabbitListener(queues = "delayedQueue")
    public void receive2(Message message) {
        String msg = new String(message.getBody());

        log.info("当前时间：{},基于插件队列 -- 第二个监听器接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }

    // TODO ⬆︎⬆︎⬆︎ 以上两个监听器都监听的是同一个队列，但是消息只会被其中一个消费者消费。
    //      并且是实现轮询的方式对消息进行消费，不存在重复消费。


    // 接收消息。这里监听的是死信队列，因为没有消费者，最终的消息都会流到死信中
    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "deadQueue")
    public void receive3(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }
}
