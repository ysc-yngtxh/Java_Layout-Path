package com.example.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-08 16:50
 * @apiNote TODO 全局异常处理
 */
@ControllerAdvice
public class HandlerException {
    private final Logger log = LoggerFactory.getLogger(HandlerException.class);

    // SpringSecurity是基于过滤器实现认证，
    // 针对异常的处理 ExceptionTranslationFilter 是 SpringSecurity 中专门负责处理异常的过滤器，
    // 默认情况下，这个过滤器已经被自动加载到过滤器链中,所以SpringSecurity的异常在到达controllerAdvice之前已被处理
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> exception(CustomException ce) {
        log.debug("执行全局异常");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(ce.getEnumResponse().getMessage());
    }
}
