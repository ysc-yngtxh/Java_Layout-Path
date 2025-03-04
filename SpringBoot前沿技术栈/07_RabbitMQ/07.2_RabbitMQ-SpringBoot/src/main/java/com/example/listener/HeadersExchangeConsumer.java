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
public class HeadersExchangeConsumer {

    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "headerQueue1")
    public void receive1(Message message, Channel channel) {
        String msg = new String(message.getBody());

        log.info("当前时间：{},使用的 Headers 交换机【头属性满足所有条件即可】-- 监听器接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }

    // 参数说明：String message 消息； Channel channel 通道
    @RabbitListener(queues = "headerQueue2")
    public void receive2(Message message, Channel channel) {
        String msg = new String(message.getBody());

        log.info("当前时间：{},使用的 Headers 交换机【头属性满足任意条件即可】-- 监听器接收到消息：{}"
                , new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date())
                , msg);
    }

}
