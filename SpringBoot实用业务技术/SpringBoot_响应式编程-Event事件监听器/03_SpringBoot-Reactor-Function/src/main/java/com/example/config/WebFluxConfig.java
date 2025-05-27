package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.PathMatchConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {

	@Override
	public void configureHttpMessageCodecs(ServerCodecConfigurer configurer) {
		// 配置消息编解码器
		WebFluxConfigurer.super.configureHttpMessageCodecs(configurer);
	}

	@Override
	public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
		// 配置参数解析器
		WebFluxConfigurer.super.configureArgumentResolvers(configurer);
	}

	@Override
	public void configurePathMatching(PathMatchConfigurer configurer) {
		// 配置路径匹配
		WebFluxConfigurer.super.configurePathMatching(configurer);
	}
}
