package com.example.config;

import com.example.pojo.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
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
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonDeserializer;
import org.springframework.kafka.support.serializer.JacksonJsonSerializer;

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
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JacksonJsonSerializer.class);
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
        // 关键配置：设置确认模式为 MANUAL
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }

    // 配置 StringDeserializer
    private ConsumerFactory<String, String> consumerStringDeserializerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "string-group");
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
        // 关键配置：设置确认模式为 MANUAL
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL);
        return factory;
    }

    // 配置 JsonDeserializer
    private ConsumerFactory<String, User> consumerJsonDeserializerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "json-group");

        // ✅ 关键修改：使用 ErrorHandlingDeserializer 包装 JacksonJsonDeserializer
        // 当遇到无法解析的消息时，它会委托给配置的默认反序列化器处理，或者抛出特定异常供监听器处理
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        // 指定实际的 delegate (代理) 反序列化器
        configProps.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        configProps.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JacksonJsonDeserializer.class);

        // 可选：配置具体的目标类型，防止类型擦除问题
        configProps.put(JacksonJsonDeserializer.TRUSTED_PACKAGES, "*");
        // 或者明确指定类 (Spring Kafka 2.3+)
        // props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.example.User");

        return new DefaultKafkaConsumerFactory<>(configProps);
    }

}
