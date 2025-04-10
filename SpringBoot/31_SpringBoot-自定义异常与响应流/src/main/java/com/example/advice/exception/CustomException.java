package com.example.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 游家纨绔
 * @dateTime 2024-09-26 21:35
 * @apiNote TODO
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
// 标注状态码：即接口抛出该异常时响应的状态码为 304
@ResponseStatus(value = HttpStatus.NOT_MODIFIED, reason = "参数错误")
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1928685874042929655L;

    private String message;
}
