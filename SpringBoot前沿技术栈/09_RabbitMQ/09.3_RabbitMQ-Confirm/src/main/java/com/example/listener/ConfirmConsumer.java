package com.example.listener;

import com.rabbitmq.client.Channel;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConfirmConsumer {

    // 接收消息。这里监听的是确认队列
    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "confirmDirectQueue")
    public void receiveDrivect(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{},消息确认队列监听到消息：{}。准备消费～～～"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }

    // 接收消息。这里监听的是死信队列，因为没有消费者，最终的消息都会流到死信中
    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "deadQueue")
    public void receive(Message message, Channel channel){
        String msg = new String(message.getBody());
        log.info("当前时间：{},死信队列接收到消息：{}。准备消费～～～"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }
}