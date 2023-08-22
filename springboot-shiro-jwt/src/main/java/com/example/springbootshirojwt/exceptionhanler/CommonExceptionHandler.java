package com.example.springbootshirojwt.exceptionhanler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  // @ControllerAdvice中的方法全局应用于所有控制器
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)  // 异常种类
    public ResponseEntity<ExceptionResult> handlerException(LyException e){
        return ResponseEntity.ok().body(new ExceptionResult(e.getExceptionEnum()));
    }
}
