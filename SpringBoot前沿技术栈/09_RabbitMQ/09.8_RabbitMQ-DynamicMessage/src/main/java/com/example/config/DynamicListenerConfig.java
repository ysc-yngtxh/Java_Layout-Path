package com.example.config;

import com.example.listener.DynamicMessageListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-17 19:00
 * @apiNote TODO RabbitMQ动态监听配置
 */
@Slf4j
@Configuration
public class DynamicListenerConfig {

    /**
     * 消息监听容器。使用 SimpleMessageListenerContainer 实现动态监听
     * @param connectionFactory 连接工厂
     */
    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory,
                                                                   DynamicMessageListener dynamicMessageListener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        // 监听消息的消费逻辑
        container.setMessageListener(dynamicMessageListener);

        // 使用内置错误处理器：处理消息在消费过程中的异常
        // container.setErrorHandler(new ConditionalRejectingErrorHandler());
        // 自定义消息消费过程中的异常处理
        // container.setErrorHandler(errorHandlerToDead());

        // 添加重试拦截器
        // container.setAdviceChain(retryInterceptor());

        return container;
    }

    /**
     * 动态消息监听器。消息处理逻辑
     * @param jsonMessageConverter 消息转换器
     */
    @Bean
    public DynamicMessageListener dynamicMessageListener(MessageConverter jsonMessageConverter) {
        return new DynamicMessageListener(jsonMessageConverter);
    }
}