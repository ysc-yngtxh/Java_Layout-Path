package com.example.advice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-25 15:27
 * @apiNote TODO
 */
@RestControllerAdvice
public class SqlExceptionAdvice {

    @ExceptionHandler(SqlException.class)
    public String ExceptionResult(SqlException se) {
        return se.getSqlEnum().getMessage();
    }
}
