package com.example.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class ENCondition implements Condition {

	public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
		// user.language=EN 表示英文返回 true
		String property = conditionContext.getEnvironment().getProperty("user.language");
		return property.contains("en");
	}
}
