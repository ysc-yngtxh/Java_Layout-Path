package com.example.mq.annotation;

import com.example.mq.config.MQConfig;
import com.example.mq.listener.MQTransactionListener;
import com.example.mq.handler.impl.DefaultMQHandler;
import com.example.mq.handler.impl.DefaultMQTransactionHandler;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 开启RocketMQ注解
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({MQConfig.class,
        MQTransactionListener.class,
        DefaultMQHandler.class,
        DefaultMQTransactionHandler.class})
public @interface EnableRocketmq {
}
