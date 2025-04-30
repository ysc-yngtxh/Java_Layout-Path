package com.example.annotation;

import com.example.conditional.BerOnClassCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-02 08:25
 * @apiNote TODO 自定义判断类是否存在(手动实现@ConditionalOnClass注解功能）
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
// @Conditional 注解，用于根据特定条件决定是否注册Bean。其注解属性value()表示需要判断的条件类。
@Conditional(BerOnClassCondition.class)
public @interface BerConditionalOnClass {

    Class<?>[] value() default {};

    String[] name() default {};

    /**
     * 是否启用宽松模式（当类加载失败时是否抛出异常）
     * 默认false，安全模式下类加载失败只返回false不抛异常
     */
    boolean lenient() default false;
}
