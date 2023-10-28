package com.youshicheng.死信队列;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.youshicheng.RabbitMqUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 生产者
 */
public class Producer {
    // 普通交换器名称
    public static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws IOException {
        // 通过封装类获取Channel信道
        Channel channel = RabbitMqUtils.getChannel();
        // 死信消息 设置TTL时间  单位是ms  一旦超过10秒没被消费就会成为死信
        AMQP.BasicProperties properties = new AMQP.BasicProperties().builder().expiration("10000").build();
        /**
         * 这里为什么在生产者设置过期时间不在消费者那儿设置呢？
         *     因为你想想，万一有多个设置延迟队列的需求存在，就需要手动在消费者那儿增加队列，麻烦
         *     所以将过期时间设置在生产者这儿，过期时间由用户决定。完美👏
        */

        for (int i = 1; i < 11; i++) {
            String message = "info--" + i;
            System.out.println("开始生产消息"+i);
            // 普通交换器、routing-key、死信设置、消息
            channel.basicPublish(NORMAL_EXCHANGE, "ZhangSan", properties, message.getBytes(StandardCharsets.UTF_8));
        }
    }
}
