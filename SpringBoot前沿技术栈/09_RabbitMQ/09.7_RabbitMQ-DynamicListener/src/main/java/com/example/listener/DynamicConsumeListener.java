package com.example.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 队列监听类（动态）
 */
@Slf4j
@Configuration
public class DynamicConsumeListener {

    /**
     * 消息监听容器。
     * 使用 SimpleMessageListenerContainer 实现动态监听
     * @param connectionFactory 连接工厂
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setMessageListener(message -> {
            log.info("ConsumerMessageListen，动态监听器收到消息: {}", message.toString());
        });
        container.setErrorHandler(throwable -> {
            log.error("ConsumerMessageListen，消息监听发生错误: {}", throwable.getMessage());
        });
        return container;
    }
}