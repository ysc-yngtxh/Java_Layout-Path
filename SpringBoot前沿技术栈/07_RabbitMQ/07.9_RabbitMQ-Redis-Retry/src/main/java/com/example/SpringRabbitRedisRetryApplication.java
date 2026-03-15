package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@SpringBootApplication
public class SpringRabbitRedisRetryApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRabbitRedisRetryApplication.class, args);
    }

    /**
     * RabbitMQ 本身不默认提供消息消费的重试机制，但常见的Spring生态工具（如Spring AMQP）会提供重试支持。
     * 因此，我们在引入依赖 spring-boot-starter-amqp，并在配置中启用重试，就能实现重试功能。【RocketMQ 本身就支持重试机制】
     *    当启用重试时，会在以下情况下触发：
     *    1、生产者发送消息失败时，RabbitMQ 会自动重试发送。
     *    2、消费者处理消息失败时，RabbitMQ 会自动重试消费。
     *
     *       1、消费者方法抛出未捕获的异常
     *       2、不是 AmqpRejectAndDontRequeueException (这个异常会直接拒绝且不重试)
     *
     *
     * 拓展：也可以通过添加 spring-retry 依赖来启用重试功能。
     */
}
