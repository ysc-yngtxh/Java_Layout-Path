package com.example.conditional;

import com.example.annotation.BerConditionalOnClass;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-02 09:17
 * @apiNote TODO
 */
public class BerOnClassCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        // AnnotatedTypeMetadata是用来获取 @Conditional 注解中的元数据信息的。
        Map<String, Object> annotationAttributes =
                metadata.getAnnotationAttributes(BerConditionalOnClass.class.getName());
        // 1. 拿到 @BerConditionalOnClass 中的value属性
        String className = annotationAttributes.get("value").toString();

        // 2. 类加载器进行加载
        try {
            // 加载到了特定的类名，则符合条件 true
            context.getClassLoader().loadClass(className);
        } catch (ClassNotFoundException e) {
            // 加载不到，则不符合条件 false
            return false;
        }
        return true;
    }
}
