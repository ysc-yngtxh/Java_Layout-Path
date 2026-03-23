package com.example.filter;

import com.example.pojo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class KafkaMessageFilter implements RecordFilterStrategy<String, String> {

    @SneakyThrows
    @Override
    public boolean filter(ConsumerRecord<String, String> consumerRecord) {
        // 返回值含义：
        // true  -> 丢弃该消息 (Skip/Ignore)
        // false -> 处理该消息 (Process)

        ObjectMapper objectMapper = new ObjectMapper();
        User user = objectMapper.readValue(consumerRecord.value(), User.class);

        // 示例：过滤掉空消息
        if (Objects.isNull(user)) {
            return true;
        }

        // 示例：过滤掉值为 "IGNORE_ME" 的消息
        if ("游家纨绔2".equals(user.getName())) {
            System.out.println("过滤掉消息: " + user.getName());
            return true;
        }

        return false; // 其他消息正常处理
    }
}
