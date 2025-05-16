package com.example.advice;

import com.example.advice.exception.LyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 游家纨绔
 */
@ControllerAdvice  // @ControllerAdvice中的方法全局应用于所有控制器
public class HandlerAdvice {

	// 标注异常种类  就是在运行过程中所遇到的此种异常种类，会返回一下
	@ExceptionHandler(value = LyException.class)
	public ResponseEntity<ExceptionResult> handlerException(LyException e) {
		return ResponseEntity.status(e.getUserEnum().getCord())
		                     .body(new ExceptionResult(e.getUserEnum()));
	}
}
