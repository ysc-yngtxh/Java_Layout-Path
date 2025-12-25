package com.example.components;

import lombok.NonNull;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware {

	private static ApplicationContext APPLICATION_CONTEXT;

	public static ApplicationContext getApplicationContext() {
		return APPLICATION_CONTEXT;
	}

	@Override
	public void setApplicationContext(@NonNull ApplicationContext applicationContext) {
		APPLICATION_CONTEXT = applicationContext;
	}

}
