package com.example.config;

import com.example.pojo.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.Message;
import org.apache.kafka.common.serialization.ListDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    // KafkaTemplate<String, String>
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(config);
        return new KafkaTemplate<>(producerFactory);
    }

    // KafkaTemplate<String, User>
    @Bean
    public KafkaTemplate<String, User> userKafkaTemplate() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        ProducerFactory<String, User> factory = new DefaultKafkaProducerFactory<>(config);
        return new KafkaTemplate<>(factory);
    }

    // KafkaTemplate<String, String> 支持事务
    @Bean
    public KafkaTemplate<String, String> kafkaTransactionalTemplate() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 【关键】启用事务支持，必须设置 transactionIdPrefix
        config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tx-string-id");
        // 如果需要在运行时动态切换事务ID，也可以使用 factory.setTransactionIdPrefix("tx-string-");

        // 推荐：开启幂等性，配合事务使用更安全
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(config);
        return new KafkaTemplate<>(producerFactory);
    }



    // 配置 Consumer
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> stringFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerStringDeserializerFactory());
        // 开启批量监听为 true，这样才会把多条消息聚合成 List 传给监听器
        factory.setBatchListener(true);
        return factory;
    }

    // 配置 StringDeserializer
    private ConsumerFactory<String, String> consumerStringDeserializerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "my-group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(configProps);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, User> batchFactory() {
        ConcurrentKafkaListenerContainerFactory<String, User> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerJsonDeserializerFactory());
        // 开启批量监听为 true，这样才会把多条消息聚合成 List 传给监听器
        factory.setBatchListener(true);
        return factory;
    }

    // 配置 JsonDeserializer
    private ConsumerFactory<String, User> consumerJsonDeserializerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "json-group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        // ✅ 关键修改 1: 构造函数传入 User.class，作为默认类型（解决 No type information 问题）
        JsonDeserializer<User> deserializer = new JsonDeserializer<>(User.class);

        // ✅ 关键修改 2: 添加信任包（解决 not in the trusted packages 问题）
        // 方式 A: 信任特定包 (推荐)
        deserializer.addTrustedPackages("com.example");
        return new DefaultKafkaConsumerFactory<>(configProps,
                new StringDeserializer(),
                deserializer
        );
    }

}
