package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@Configuration
// 启用 WebFlux 功能
@EnableWebFlux
public class WebConfig implements WebFluxConfigurer {  // @WebFluxTest 会自动配置这个类

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // 1. 配置CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    // 2. 添加自定义转换器（Converter）与格式化器（Formatter）。
    // 注意⚠️：这里注册的组件采用的是责任链模式：FormatterRegistry → ConverterRegistry，且 后注册的优先级更高。
    //        因此：请求参数 →  ConverterRegistry → FormatterRegistry → 默认转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 注册 LocalDateTime 到 Instant 的转换器
        registry.addConverter(new LocalDateTimeToInstantConverter());
        // 注册格式化器（Formatter）
        registry.addFormatter(new LocalDateTimeFormatter());
    }

    // LocalDateTime 到 Instant 的转换器
    static class LocalDateTimeToInstantConverter implements Converter<LocalDateTime, Instant> {
        @Override
        public Instant convert(LocalDateTime source) {
            log.info("🔵Before Converter Instant: {}", source);
            return source.atZone(ZoneId.systemDefault()).toInstant();
        }
    }
    
    // LocalDateTime 格式化器
    static class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
        @Override
        public LocalDateTime parse(@NonNull String text, @NonNull Locale locale) {
            log.info("🟢Before Parsing LocalDateTime: {}", text);
            return LocalDateTime.parse(text, formatter);
        }

        @Override
        public @NonNull String print(LocalDateTime object, @NonNull Locale locale) {
            log.info("🟢Before Printing LocalDateTime: {}", object);
            return object.format(formatter);
        }
    }

}
