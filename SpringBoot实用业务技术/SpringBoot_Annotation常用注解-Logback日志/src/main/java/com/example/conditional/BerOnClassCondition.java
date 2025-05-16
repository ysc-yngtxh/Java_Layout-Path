package com.example.conditional;

import com.example.annotation.BerConditionalOnClass;
import java.util.Map;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-02 09:10
 * @apiNote TODO
 */
public class BerOnClassCondition implements Condition {

	// 参数ConditionContext 是一个上下文对象，提供了访问 Spring 容器的功能。
	// 参数AnnotatedTypeMetadata 是用来获取 @Conditional 注解中的元数据信息的。
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 1. 获取 @BerConditionalOnClass 注解对象
		Map<String, Object> attributes =
				metadata.getAnnotationAttributes(BerConditionalOnClass.class.getName());

		// 2. 获取类加载器
		ClassLoader classLoader = context.getClassLoader();

		// 3. 拿到 @BerConditionalOnClass 中的lenient属性
		boolean lenient = (boolean) attributes.get("lenient");

		// 4. 拿到 @BerConditionalOnClass 中的value属性
		Class<?>[] classes = (Class<?>[]) attributes.get("value");
		for (Class<?> clazz : classes) {
			// 判断类是否加载成功
			if (!isClassPresent(clazz.getName(), classLoader, lenient)) {
				return false;
			}
		}

		// 5. 拿到 @BerConditionalOnClass 中的name属性
		String[] classNames = (String[]) attributes.get("name");
		for (String className : classNames) {
			if (StringUtils.hasText(className)) {
				// 判断类是否加载成功
				if (!isClassPresent(className, classLoader, lenient)) {
					return false;
				}
			}
		}

		return true;
	}

	// 判断类是否加载成功
	private boolean isClassPresent(String className, ClassLoader classLoader, boolean lenient) {
		try {
			// 使用 ClassUtils.forName() 方法来加载类
			ClassUtils.forName(className, classLoader);
			// 加载到了特定的类名，则符合条件，返回为 true
			return true;
		} catch (Throwable ex) {
			// 如果加载类过程出现异常，且lenient 属性为 false，则抛出异常；lenient 属性为 true，则返回为 false
			if (!lenient) {
				throw new IllegalStateException("Could not load class: " + className, ex);
			}
			return false;
		}
	}
}
