package com.example.spring;

import com.example.pojo.User;
import com.example.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@Slf4j
@SpringBootTest
@EmbeddedKafka(topics = "test-topic", count = 1, ports = {9092})
class SpringBootKafkaApplicationTests {

    @Autowired
    private KafkaProducerService producer;

    @Test
    void testSendMessageSync() {
        // 1. 构造数据
        String expectedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        User expectedUser = new User(1, "Hello Kafka", expectedTime);
        String topic = "test-topic";

        // 2. 发送消息
        producer.sendBatchMessages(topic, List.of(expectedUser));

        // 3. 配置并创建 Consumer (在调用工具类之前完成)
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 使用随机组ID避免冲突
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false); // 关闭自动提交，改为手动提交偏移量

        // 关键：配置反序列化器
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JacksonJsonDeserializer.class);
        props.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "com.example.pojo"); // 请替换为你的实际包名
        props.put(JacksonJsonDeserializer.VALUE_DEFAULT_TYPE, User.class.getName());

        // 4. 使用 try-with-resources 确保 Consumer 自动关闭
        // 显式声明泛型 <String, User>
        try (Consumer<String, User> consumer = new KafkaConsumer<>(props)) {

            // 订阅主题 (getSingleRecord 内部可能也会做，但显式订阅更稳妥，视你的工具类实现而定)
            // 如果 getSingleRecord 内部没有订阅逻辑，这里必须加上
            consumer.subscribe(Collections.singletonList(topic));

            // 5. 调用工具类，传入已经创建好的 consumer 对象
            ConsumerRecord<String, User> record = KafkaTestUtils.getSingleRecord(consumer, topic);

            // 6. 断言验证
            assertAll("验证接收到的消息",
                    () -> assertNotNull(record, "未在规定时间内接收到消息"),
                    () -> assertEquals(topic, record.topic(), "Topic 不匹配"),
                    () -> assertNotNull(record.value(), "消息体为空"),
                    () -> assertEquals(expectedUser.getId(), record.value().getId(), "用户 ID 不匹配"),
                    () -> assertEquals(expectedUser.getName(), record.value().getName(), "用户名称不匹配")
            );
            System.out.println("接收到的消息: " + record.value());

            // 7. 手动提交偏移量，提交当前 poll 到的所有消息 (同步)
            consumer.commitSync();
        } catch (Exception e) {
            // 可选：捕获超时或其他异常并失败测试
            fail("测试执行失败: " + e.getMessage(), e);
        }
    }


    @Test
    public void contextLoads()throws IOException {
        System.in.read();
    }

}
