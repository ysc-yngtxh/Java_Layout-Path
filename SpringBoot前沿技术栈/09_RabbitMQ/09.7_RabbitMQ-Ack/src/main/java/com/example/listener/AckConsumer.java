package com.example.listener;

import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AckConsumer {

    // 接收消息。
    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "ackQueue")
    public void receive1(Message message, Channel channel) throws IOException {
        String msg = new String(message.getBody());

        log.info("当前时间：{},监听器接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);

        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
