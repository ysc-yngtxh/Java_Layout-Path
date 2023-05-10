package com.example.exception;

/**
 * @author youshicheng
 * @dateTime 2023-05-08 16:49
 * @apiNote TODO 全局异常处理
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {

    private final Logger log = LoggerFactory.getLogger(HandlerException.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> exception(CustomException ce){
        log.debug("执行全局异常");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ce.getEnumResponse().getMessage());
    }
}
