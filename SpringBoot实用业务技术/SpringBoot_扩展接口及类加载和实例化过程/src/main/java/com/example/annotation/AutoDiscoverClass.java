package com.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 只允许该注解可以标注类，方法
@Target({ElementType.TYPE, ElementType.METHOD})
// 表示该注解被保存在class文件中，并且可以被反射机制读取
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoDiscoverClass {}
