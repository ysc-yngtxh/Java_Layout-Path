package com.example.annotation;

import com.example.config.SqlAnnotationConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

// 全局开启 自定义Interceptor
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(SqlAnnotationConfig.class) // SqlAnnotationConfig类 注入Spring 容器
public @interface EnableSqlInterceptor {}
