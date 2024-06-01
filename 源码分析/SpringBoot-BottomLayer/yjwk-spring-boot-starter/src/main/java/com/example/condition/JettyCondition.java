package com.example.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-01 22:45
 * @apiNote TODO
 */
public class JettyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            context.getClassLoader().loadClass("org.eclipse.jetty.server.Server");
        } catch (ClassNotFoundException e) {
            // 出现异常，表示不存在 Jetty 类，返回为 false
            return false;
        }
        return true;
    }
}
