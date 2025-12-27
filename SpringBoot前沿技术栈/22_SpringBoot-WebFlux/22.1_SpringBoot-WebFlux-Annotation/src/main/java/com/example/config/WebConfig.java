package com.example.config;

import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Slf4j
@Configuration
// 启用 WebFlux 功能
@EnableWebFlux
// 启用 R2DBC 仓库，指定基础包路径
@EnableR2dbcRepositories(basePackages = "com.example.repository")
public class WebConfig implements WebFluxConfigurer {  // @WebFluxTest 会自动配置这个类
    
    // 1. 添加自定义转换器（Converter）
    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 注册时间字符串到 LocalDateTime的转换器
        registry.addConverter(new StringToLocalDateTimeConverter());

        // 注册格式化器（Formatter）
        registry.addFormatter(new LocalDateTimeFormatter());
    }

    // 2. 配置CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:3000")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
    }

    
    // 时间字符串到 LocalDateTime 的转换器
    static class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {
        @Override
        public LocalDateTime convert(String source) {
            log.info("Converting LocalDateTime: {}", source);
            return LocalDateTime.parse(source, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        }
    }
    
    // LocalDateTime 格式化器
    static class LocalDateTimeFormatter implements Formatter<LocalDateTime> {
        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        @Override
        public LocalDateTime parse(@NonNull String text, @NonNull Locale locale) {
            log.info("Parsing LocalDateTime: {}", text);
            return LocalDateTime.parse(text, formatter);
        }
        
        @Override
        public @NonNull String print(LocalDateTime object, @NonNull Locale locale) {
            log.info("Printing LocalDateTime: {}", object);
            return object.format(formatter);
        }
    }

}
