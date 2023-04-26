package com.cn.leyou.advice;

import com.cn.leyou.exception.LyException;
import com.cn.leyou.vo.ExceptionResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  //@ControllerAdvice中的方法全局应用于所有控制器
public class CommonExceptionHandler {

    @ExceptionHandler(RuntimeException.class)  //异常种类
    public ResponseEntity<ExceptionResult> handlerException(LyException e){
        return ResponseEntity.status(e.getExceptionEnum().getCord()).body(new ExceptionResult(e.getExceptionEnum()));
    }
}
