package com.example.interceptor;

import com.example.service.IpCountService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-20 21:30:00
 * @apiNote TODO 轮子拦截器
 */
public class IpCountInterceptor implements HandlerInterceptor {

	@Autowired
	private IpCountService ipCountService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		ipCountService.IpCount();
		return true;
	}
}
