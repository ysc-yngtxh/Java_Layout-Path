package com.example.v2.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-04 23:50:00
 * @apiNote TODO 自定义注解
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface Param {

	String value();

}
