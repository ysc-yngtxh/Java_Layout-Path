package com.example.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import org.apache.catalina.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import tools.jackson.databind.DefaultTyping;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.jsontype.BasicPolymorphicTypeValidator;

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
        ObjectMapper obm = JsonMapper.builder()
                // 设置 序列化/反序列化 时哪些成员可见。
                //    PropertyAccessor.ALL：所有访问器类型（包括字段、getter、setter、构造器等）
                //    JsonAutoDetect.Visibility.ANY：所有可见性级别都允许（包括 private、public、protected 等）
                .changeDefaultVisibility(visibilityChecker ->
                        visibilityChecker.withVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                )
                // 激活默认类型信息（创建类型白名单验证器，限制允许反序列化的类。）
                .activateDefaultTyping(
                        // 创建一个安全的验证器，限定允许的子类型白名单
                        BasicPolymorphicTypeValidator.builder()
                                .allowIfSubType("com.example") // 允许你项目中的包
                                .allowIfSubType("java.util")   // 允许一些常用的JDK类
                                .build(),
                        // DefaultTyping.NON_FINAL：对于所有非 final 类（即可以被继承的类）在序列化时添加类型信息
                        // 例如：String、Integer 等 final 类不会添加类型信息，但自定义的 User 类会添加
                        DefaultTyping.NON_FINAL
                )
                .build();

        GenericJacksonJsonRedisSerializer jacksonJsonRedisSerializer =
                new GenericJacksonJsonRedisSerializer(obm);
        // 针对 User 类型的序列化器
        JacksonJsonRedisSerializer<User> userSerializer =
                new JacksonJsonRedisSerializer<>(obm, User.class);


        // 设置Redis的String数据类型key部分，序列化方式为 StringRedisSerializer
        template.setKeySerializer(new StringRedisSerializer());
        // 设置Redis的String数据类型value部分，序列化方式为 jacksonJsonRedisSerializer
        template.setValueSerializer(jacksonJsonRedisSerializer);

        // 设置Redis的hash数据类型key部分，序列化方式为 StringRedisSerializer
        template.setHashKeySerializer(new StringRedisSerializer());
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
