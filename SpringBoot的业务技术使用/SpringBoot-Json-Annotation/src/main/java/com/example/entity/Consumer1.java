package com.example.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * (Consumer1)实体类
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
 * NON_NULL ：注解的字段为null不序列化（除了 Optional 类、AtomicReference 类）
 * NON_ABSENT ：注解的字段为null的时候不序列化
 * NON_EMPTY ：注解的字段为null或为空不序列化
 * NON_DEFAULT ：字段是默认值的话就不序列化
 * USE_DEFAULTS ：字段是默认值的话就进行序列化
 * CUSTOM ：自定义排除序列化规则
 */
@JsonInclude(JsonInclude.Include.NON_NULL)  // 实际效果就是返回给前端的的Json 字符串中 值为 null 的字段不显示
@JsonIgnoreProperties({"age", "deleteFlag"})  // 序列化和反序列化,定义的属性忽略掉
@JsonPropertyOrder({"consumerId", "username", "password", "alias", "age", "sex", "phone", "address"
        , "deleteFlag", "date", "price", "optional", "atomicReference", "supplier"})  // 可以指定json映射名称属性在 json 字符串中的顺序
public class Consumer1 implements Serializable {
    @Serial
    private static final long serialVersionUID = 424381199466784776L;

    @JsonProperty("consumerId")  // 指定某个属性和json映射的名称
    private Integer id;

    private String username;

    private String password;

    @JsonIgnore  // 用来完全忽略被注解的字段和方法对应的属性
    private String alias;

    private Integer age;

    private String sex;

    private String phone;

    private String address;

    private Integer deleteFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss SSS", timezone = "GMT+8")  // 格式化 时间数据
    private Date date;

    /**
     * Style.CURRENCY：货币类型
     * Style.NUMBER：正常数字类型
     * Style.PERCENT：百分比类型
     */
    @JsonFormat(pattern = "#.##%")  // 格式化 数字数据
    private double price;

    // Optional 类是一个可以为null的容器对象
    private Optional<String> optional;

    // AtomicReference 类是提供了对象引用的非阻塞原子性读写操作，可以为 null 的包装类
    private AtomicReference<String> atomicReference;

    private Supplier supplier;
}

