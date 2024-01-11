package com.example.annocationi;

import com.example.handler.CustomBlockExceptionHandlerPage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-11 10:27
 * @apiNote TODO 开启自定义熔断降级异常处理(跳转到页面)
 */
@Retention(RetentionPolicy.RUNTIME)
@Import(CustomBlockExceptionHandlerPage.class)
public @interface EnableCustomBlockExceptionHandlerPage {

}
