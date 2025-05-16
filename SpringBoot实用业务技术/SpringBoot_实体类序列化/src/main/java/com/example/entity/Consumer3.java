package com.example.entity;

import com.example.utils.GenderDataDeserializer;
import com.example.utils.GenderJsonSerializer;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Consumer3 实体类
 *
 * @author 游家纨绔
 * @since 2023-08-19 17:30:00
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
/**
 * ALWAYS     ：默认策略，任何情况下都序列化该字段
 * NON_NULL   ：注解的字段为 null 的不序列化（排除 Optional 类、AtomicReference 类）
 * NON_ABSENT ：注解的字段为 null 的不序列化（包括 Optional 类、AtomicReference 类）
 * NON_EMPTY  ：注解的字段为 null 或 为空 不序列化
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)  // 实际效果就是返回给前端的的Json字符串中值为 null 和 空 的字段不显示
// @JsonPropertyOrder 可以指定json映射名称属性在 json 字符串中的顺序
@JsonPropertyOrder({"consumerId", "username", "password", "optional", "atomicReference", "supplier", "gender", "properties", "propertiesJson"})
public class Consumer3 implements Serializable {
	@Serial
	private static final long serialVersionUID = 424381199466784776L;

	@JsonProperty("consumerId")  // 指定某个属性和json映射的名称
	private Integer id;

	private String username;

	private String password;

	// Optional 类是一个可以为null的容器对象
	private Optional<String> optional;

	// AtomicReference 类是提供了对象引用的非阻塞原子性读写操作，可以为 null 的包装类
	private AtomicReference<String> atomicReference;

	private Supplier supplier;

	// @JsonDeserialize 用于属性或者setter方法上，用于在反序列化时可以嵌入我们自定义的代码逻辑
	@JsonDeserialize(using = GenderDataDeserializer.class)
	@JsonSerialize(using = GenderJsonSerializer.class)
	private Integer gender;

	// 反序列化成实体对象Consumer3时有不对应的属性时加上该注解，不存在的属性不会丢弃而是会放到当前标注这个注解的Map中去
	@JsonAnySetter
	private Map<String, Object> properties;

	@JsonAnyGetter  // 序列化时Map加上该属性可以将map中的属性序列化为指定字符串
	private Map<String, Object> propertiesJson;
}
