package com.example.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-04-24 12:30:00
 */
@Slf4j
@Component
public class MDCFilter implements Filter {

	// 在Spring Boot中使用MDC（Mapped Diagnostic Context）可以为日志添加上下文信息（如请求ID、用户信息等），
	// 尤其在多线程或异步任务场景下，帮助跟踪请求链路。
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		try {
			// 设置MDC值
			MDC.put("username", "游家纨绔");
			log.info("进入过滤器");
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			// 请求完成后清除所有MDC值（重要！避免内存泄漏）
			MDC.clear();
		}
	}

}
