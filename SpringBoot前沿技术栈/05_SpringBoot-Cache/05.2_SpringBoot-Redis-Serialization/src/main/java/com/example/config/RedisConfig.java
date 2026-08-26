package com.example.config;

import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.json.JsonMapper;

/**
 * 描述：redis配置类
 * redis序列化方式选择：
 *     1、(默认的)JdkSerializationRedisSerializer序列化方式，其编码为 ISO-8859-1，会出现乱码问题；
 *     2、StringRedisSerializer序列化方式，其编码为 UTF-8，可以解决乱码问题；
 */
@Configuration
public class RedisConfig {

    // 初始化一个 RedisTemplate<Object, Object> 对象
    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        // 获取Redis的连接操作对象
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // TODO SpringBoot版本 4 之前，使用Jackson的序列化写法
        // Jackson2JsonRedisSerializer jackson2JsonRedisSerializer1 = new Jackson2JsonRedisSerializer(Object.class);
        // jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper());
        // Jackson2JsonRedisSerializer jackson2JsonRedisSerializer2 = new Jackson2JsonRedisSerializer(Object.class);
        // jackson2JsonRedisSerializer.setObjectMapper(new ObjectMapper(), Object.class);

        // TODO SpringBoot版本 4 开始，使用 Jackson 的序列化
        GenericJacksonJsonRedisSerializer jacksonJsonRedisSerializer =
                new GenericJacksonJsonRedisSerializer(new JsonMapper());
        // 针对 User 类型的序列化器
        JacksonJsonRedisSerializer<User> userSerializer =
                new JacksonJsonRedisSerializer<>(JsonMapper.builder().build(), User.class);

        RedisSerializer stringSerializer = new StringRedisSerializer();

        // 设置Redis的String数据类型key部分，序列化方式为 StringRedisSerializer
        template.setKeySerializer(stringSerializer);
        // 设置Redis的String数据类型value部分，序列化方式为 jacksonJsonRedisSerializer
        template.setValueSerializer(jacksonJsonRedisSerializer);

        // 设置Redis的hash数据类型key部分，序列化方式为 StringRedisSerializer
        template.setHashKeySerializer(stringSerializer);
        // 设置Redis的hash数据类型value部分，序列化方式为 jacksonJsonRedisSerializer
        template.setHashValueSerializer(jacksonJsonRedisSerializer);

        // 推荐使用 GenericToStringSerializer 作为类型序列化，比Jackson2JsonRedisSerializer兼容性更好
        // template.setHashKeySerializer(new GenericToStringSerializer<>(Integer.class));
        // template.setHashValueSerializer(new GenericToStringSerializer<>(File.class));

        // Redis初始化
        template.afterPropertiesSet();
        return template;
    }
}
