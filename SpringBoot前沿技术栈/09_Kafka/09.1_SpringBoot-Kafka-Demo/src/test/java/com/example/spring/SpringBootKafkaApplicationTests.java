package com.example.spring;

import com.example.pojo.User;
import com.example.service.KafkaProducerService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
@SpringBootTest
@EmbeddedKafka(topics = "test-topic", count = 4, ports = {9092,9093,9094,9095})
class SpringBootKafkaApplicationTests {

    @Autowired
    private KafkaProducerService producer;

    // @Test
    // void testSendMessageSync() {
    //     User user = new User(1, "Hello Kafka", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    //     producer.sendBatchMessages("test-topic", List.of(user));
    //
    //     ConsumerRecord<String, User> record = KafkaTestUtils.getSingleRecord((k, v) -> {
    //
    //     }, "test-topic");
    //     assertNotNull(record);
    // }

    @Test
    public void contextLoads()throws IOException {
        System.in.read();
    }

}
