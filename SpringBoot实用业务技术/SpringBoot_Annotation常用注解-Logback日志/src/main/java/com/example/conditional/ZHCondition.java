package com.example.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ZHCondition implements Condition {
    // ConditionContext.getBeanFactory()可以获取到IOC使用的BeanFactory。
    // ConditionContext.getClassLoader()可以获取到它的类加载器。
    // ConditionContext.getEnvironment()可以获取到当前的环境信息。
    // ConditionContext.getRegistry()可以获取到Bean定义的注册类。
    // ConditionContext.getResourceLoader()可以获取ResourceLoader所加载的资源。
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        // user.language=ZH Java默认语言 表示中文返回 true
        return conditionContext.getEnvironment().getProperty("user.language").contains("zh");
        // return conditionContext.getEnvironment().getProperty("os.name").contains("mac");
    }
}