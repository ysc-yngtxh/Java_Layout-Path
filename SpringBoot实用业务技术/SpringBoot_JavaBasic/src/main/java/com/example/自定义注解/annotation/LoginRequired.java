package com.example.自定义注解.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {

	// 使用自定义注解：要么在拦截器中利用反射机制获取自定义注解信息并操作
	//              要么就是使用Spring的AOP切面编程进行操作(还是需要利用到反射机制)

	// 一般自定义注解使用场景：参数是否校验，访问路径是否白名单。。。。。

	String value() default "threeLevel";
}
