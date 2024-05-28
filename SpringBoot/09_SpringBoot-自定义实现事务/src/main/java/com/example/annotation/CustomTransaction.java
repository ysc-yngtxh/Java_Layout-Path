package com.example.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: 游家纨绔
 * @create: 2024-05-27 22:56
 * @description: 自定义事务注解
 **/
@Inherited
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomTransaction {

    // 指定异常回滚
    Class<? extends Throwable>[] rollbackFor() default {};
}