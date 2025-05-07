package com.example.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-04-23 23:25:35
 */
@Component
public class CustomFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		// 在分布式系统，并且是一个集群系统情况下，需要定位那台机器的服务出现问题，怎么办？
		// 因此是需要在日志中添加 链路Id 作为标识进行判断，方便定位错误所在。
		// 通常会在网关中的请求里添加相应的请求头参数【"X-Trace-ID", 链路Id】，如下网关部分代码：
		// if (traceId == null) {
		//  	CustomNamedThreadLocal.startTransaction();
		//  	traceId = CustomNamedThreadLocal.getTransactionId();
		// }
		//
		String traceId = ((HttpServletRequest) request).getHeader("X-Trace-ID");
		// 在日志中附加链路Id（结合 SLF4J MDC）
		MDC.put("traceId", traceId);
		try {
			filterChain.doFilter(request, response);
		} finally {
			MDC.remove("traceId"); // 清理
		}
	}
}
