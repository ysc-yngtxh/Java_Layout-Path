package com.example.annotation;

import com.example.execution.MyBatisSqlInterceptorConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 全局开启SqlInterceptor
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MyBatisSqlInterceptorConfiguration.class) // MyBatisSqlInterceptorConfiguration注入Spring 容器
public @interface EnableSqlInterceptor {

}