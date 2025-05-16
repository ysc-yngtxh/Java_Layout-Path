package com.example.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-01 11:50:00
 * @apiNote TODO 测试拦截器
 */
public class DefinitionInterceptor implements HandlerInterceptor {
	public final Logger log = LoggerFactory.getLogger(DefinitionInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("================================进入拦截器==============================");

		ContentCachingRequestWrapper requestWrapper = (ContentCachingRequestWrapper) request;
		// 获取缓存的数据
		String user = requestWrapper.getParameter("userName");
		log.info("拦截器获取参数{}", user);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
