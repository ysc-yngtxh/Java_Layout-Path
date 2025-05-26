package com.example.annotation;

import java.util.Map;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-02 10:00:00
 * @apiNote TODO
 */
public class CheckClassOnCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// AnnotatedTypeMetadata是用来获取 @Conditional 注解中的元数据信息的。
		Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(SelfConditionalOnClass.class.getName());
		// 拿到 @SelfConditionalOnClass 中的value属性
		String value = annotationAttributes.get("value").toString();
		try {
			// 类加载器进行加载，加载到了特定的类名，符合条件则 true
			context.getClassLoader().loadClass(value);
		} catch (ClassNotFoundException e) {
			// 出现异常，表示不存在 指定类名，返回为 false
			return false;
		}
		return true;
	}

}
