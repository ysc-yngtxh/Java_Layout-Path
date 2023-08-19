package com.example.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * (Consumer4)实体类
 *
 * @author 游家纨绔
 * @since 2023-08-19 17:30:49
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/**
 * ALWAYS ：默认策略，任何情况下都序列化该字段
 * NON_NULL ：注解的字段为null不序列化
 * NON_ABSENT ：注解的字段为null的时候不序列化
 * NON_EMPTY ：注解的字段为null或为空不序列化
 * NON_DEFAULT ：字段是默认值的话就不序列化
 * USE_DEFAULTS ：字段是默认值的话就进行序列化
 * CUSTOM ：自定义排除序列化规则
 */
@JsonInclude( JsonInclude.Include.NON_NULL )  // 实际效果就是返回给前端的的Json 字符串中 值为 null 的字段不显示
public class Consumer4 implements Serializable {
    @Serial
    private static final long serialVersionUID = 424381199466784776L;

    private Integer id;

    private String username;

    private String password;

    private String alias;

    private Integer age;

    private String sex;

    private String phone;

    private String address;

    private Integer deleteFlag;

    private Optional<String> optional;

    private AtomicReference<String> atomicReference;

}

