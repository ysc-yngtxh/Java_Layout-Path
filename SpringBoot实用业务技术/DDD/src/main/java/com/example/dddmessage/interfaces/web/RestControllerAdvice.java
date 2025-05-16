package com.example.dddmessage.interfaces.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 统一的异常处理类， 通过不同的异常类别，包装不同的 {@link Response } 对象
 *
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@org.springframework.web.bind.annotation.RestControllerAdvice
public class RestControllerAdvice {
	private final Logger logger = LoggerFactory.getLogger(RestControllerAdvice.class);

	/**
	 * 错误处理
	 *
	 * @param throwable           异常
	 * @param httpServletRequest  http 请求对象
	 * @param httpServletResponse http 响应对象
	 *                            return   {@link Response}
	 */
	@ExceptionHandler(Throwable.class)
	public Response<Void> errorHandler(Throwable throwable, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		// 可以根据异常类别，做判断，包装不同的响应
		Response<Void> response = Response.failed(throwable.getMessage());
		httpServletResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		StringBuilder builder = new StringBuilder();
		builder.append("Controller ErrorHandler")
		       .append(" , ").append(httpServletRequest.getMethod()).append(" ").append(httpServletRequest.getRequestURI())
		       .append(" , errorInfo ").append(throwable.getMessage())
		       .append(" , responseHttpStatus [").append(httpServletResponse.getStatus()).append("]")
		       .append(" , ").append(response.toString());
		logger.error(builder.toString(), throwable);
		return response;
	}
}
