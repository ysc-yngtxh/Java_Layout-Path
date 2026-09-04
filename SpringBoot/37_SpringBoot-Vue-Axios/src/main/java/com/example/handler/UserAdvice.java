package com.example.handler;

import com.example.vo.ResponseVo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 游家纨绔
 * @dateTime 2023-07-11 09:00:00
 * @apiNote TODO 全局异常捕捉
 */

@ControllerAdvice
public class UserAdvice {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseVo<?>> returnData() {
		return ResponseEntity.ok(ResponseVo.fail(401, "用户验证失败，请重新登录"));
	}

}
