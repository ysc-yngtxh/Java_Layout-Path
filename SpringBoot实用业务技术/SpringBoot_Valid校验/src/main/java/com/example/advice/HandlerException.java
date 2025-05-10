package com.example.advice;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-05 23:40
 * @apiNote TODO
 */
@ControllerAdvice
public class HandlerException {

    // ConstraintViolationException
    // 触发场景：
    //     主要用于方法级别或类级别的验证
    //     当使用@Validated注解标注类，并在方法参数上使用约束注解时触发
    // 验证时机：
    //     通过AOP代理在方法调用时进行验证
    //     属于Java Bean Validation(JSR-380)规范层面的验证
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            String field = violation.getPropertyPath().toString();
            errors.put(field, violation.getMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }


    // MethodArgumentNotValidException
    // 触发场景：
    //     主要用于Spring MVC控制器方法的参数验证
    //     当使用@Valid或@Validated注解标注方法参数时触发
    // 验证时机：
    //     在数据绑定后立即进行验证
    //     属于Spring框架层面的验证
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody String handlerException(MethodArgumentNotValidException exception) {
        List<ObjectError> allErrors = exception.getBindingResult().getAllErrors();
        String result = String.join(" | ", allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList()));
        // 另外写法：
        // String result = allErrors.stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(" | "));
        return result;
    }
}
