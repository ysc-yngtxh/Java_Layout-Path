package com.example.annocation;

import com.example.handler.CustomBlockExceptionHandlerPage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-11 10:30
 * @apiNote TODO 定义熔断降级异常处理是否开启
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
// 可选择 自定义熔断降级异常处理 模式
//    1、返回响应流：CustomBlockExceptionHandler
//    2、跳转到页面：CustomBlockExceptionHandlerPage）
@Import(CustomBlockExceptionHandlerPage.class)
public @interface EnableCustomBlockException {}
