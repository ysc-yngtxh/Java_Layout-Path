package com.example.advice;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-05 23:40
 * @apiNote TODO
 */
@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handlerException(MethodArgumentNotValidException e){
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        String result = String.join(" | ", allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        // String result = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" | "));
        return ResponseEntity.ok(result);
    }
}
