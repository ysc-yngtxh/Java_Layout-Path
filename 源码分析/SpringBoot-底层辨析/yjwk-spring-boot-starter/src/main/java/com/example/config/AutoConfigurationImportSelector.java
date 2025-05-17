package com.example.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-06 07:30:00
 * @apiNote TODO
 */
public class AutoConfigurationImportSelector implements DeferredImportSelector {

	// 返回的数组中是自动配置的类（包括 SpringBoot 的自动配置，还有第三方的自动配置类）
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[0];
	}

}
