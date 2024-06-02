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
 * @apiNote TODO 自定义判断类是否存在注解
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
// 使用 @Conditional 注解，执行判断逻辑，促使自定义注解是否生效
@Conditional(BerOnClassCondition.class)
public @interface BerConditionalOnClass {

    String value() default "";
}
