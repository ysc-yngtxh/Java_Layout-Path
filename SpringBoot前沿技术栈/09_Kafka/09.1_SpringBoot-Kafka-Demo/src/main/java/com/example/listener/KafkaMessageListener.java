package com.example.listener;

import com.example.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import tools.jackson.databind.ObjectMapper;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-23 23:00
 * @apiNote TODO
 */
@Slf4j
@Configuration
public class KafkaMessageListener {

    // topics：指定要监听的主题，groupId：指定消费者组ID
	@KafkaListener(topics = "my-topic", groupId = "my-group")
	public void consume(@Payload String message, Acknowledgment ack,
                        // 1. 获取分区号
                        @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                        // 2. 获取偏移量
                        @Header(KafkaHeaders.OFFSET) long offset,
                        // 可选：获取 topic 名称
                        @Header(KafkaHeaders.RECEIVED_TOPIC) String topic) {
        // 模拟业务处理
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        log.info("{} 收到消息: {}，Partition：{}，Offset：{}", topic, message, partition, offset);

        // 如果配置了 ack-mode: MANUAL，这里需要手动提交偏移量.
        ack.acknowledge();  // 手动确认消息已消费，避免重复消费
	}

	// 监听带 Header 的消息
	@KafkaListener(topics = "headers-topic")
	public void consumeWithHeaders(@Payload String message, Acknowledgment ack,
                                   // 1. 获取分区号
                                   @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                                   // 2. 获取偏移量
                                   @Header(KafkaHeaders.OFFSET) long offset,
                                   // 可选：获取 topic 名称
                                   @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                                   // 选择自定义的 Header
	                               @Header(name = "user", required = false) byte[] userHeaderBytes) {
        // ⚠️：headers-topic 监听器方法是按照单条消息（@Payload String）定义的，且未指定专用的单条模式 containerFactory。
        //     application.yml 中全局开启了 listener.type: batch（批量监听模式）。这种模式会不匹配导致 Spring Kafka 无法正确解析消息头。
        //     因此，listener.type: single（单条监听模式）是默认且推荐的模式，可以实现 Header 传值。
        String userHeader = "不存在 Header";
        if (userHeaderBytes != null && userHeaderBytes.length != 0 ) {
            userHeader = new String(userHeaderBytes, StandardCharsets.UTF_8);
        }
		System.out.println(topic + " 收到消息: " + message + "，Partition：" + partition + "，Offset：" + offset + "， Header 'user': " + userHeader);

        // 如果配置了 ack-mode: MANUAL，这里需要手动提交偏移量.
        ack.acknowledge();  // 手动确认消息已消费，避免重复消费
	}

    // 监听消息，接收批量消息
    @KafkaListener(topics = "batch-topic", containerFactory = "batchFactory")
    public void consumeBatchMessages(List<ConsumerRecord<String, User>> records, Acknowledgment ack
                                     // @Payload List<User> messages
                                     // 不支持在同一个方法签名中同时混合使用 @Payload List<User>（反序列化后的值列表）和 List<ConsumerRecord>（原始记录列表）。
    ) {
        for (ConsumerRecord<String, User> record : records) {
            log.info("批量消息 -> Topic: {}, Partition: {}, Offset: {}, User: {}",
                    record.topic(), record.partition(), record.offset(), record.value());
        }

        // 如果配置了 ack-mode: MANUAL，这里需要手动提交偏移量.
        ack.acknowledge();  // 手动确认消息已消费，避免重复消费
    }

    // 监听消息，结合自定义过滤器过滤消息
    @KafkaListener(topics = "filtered-topic", groupId = "json-group", containerFactory = "batchFactory", filter = "kafkaMessageFilter")
    public void consumeFilteredMessage(List<ConsumerRecord<String, User>> records, Acknowledgment ack
                                       // @Payload List<User> messages
                                       // 不支持在同一个方法签名中同时混合使用 @Payload List<User>（反序列化后的值列表）和 List<ConsumerRecord>（原始记录列表）。
    ) {
        for (ConsumerRecord<String, User> record : records) {
            log.info("过滤批量消息 -> Topic: {}, Partition: {}, Offset: {}, User: {}",
                    record.topic(), record.partition(), record.offset(), record.value());
        }

        // 如果配置了 ack-mode: MANUAL，这里需要手动提交偏移量.
        ack.acknowledge();  // 手动确认消息已消费，避免重复消费
    }

}
