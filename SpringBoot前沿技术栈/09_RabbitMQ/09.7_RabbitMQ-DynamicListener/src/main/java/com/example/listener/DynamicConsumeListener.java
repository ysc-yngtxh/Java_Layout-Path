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
     * 使用SimpleMessageListenerContainer实现动态监听
     * @param connectionFactory 连接工厂
     * @return SimpleMessageListenerContainer 实例
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setMessageListener(message -> {
            log.info("ConsumerMessageListen，动态监听器收到消息: {}", message.toString());
        });
        return container;
    }
}