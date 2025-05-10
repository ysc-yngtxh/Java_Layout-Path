package com.example.annotation;

import com.example.validator.IsLeekValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @desc TODO 自定义校验注解
 */
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
// 通过在自定义注解上添加 @Constraint 注解，可以将该注解标记为一个自定义约束注解。
// 同时，需要指定一个实现了 ConstraintValidator 接口的验证器类，用于验证该注解所标记的字段或参数是否符合自定义的校验规则。
@Constraint(validatedBy = IsLeekValidator.class)
public @interface IsLeek {

    /**
     * 是否强制校验
     *
     * @return 是否强制校验的boolean值
     */
    boolean required() default true;

    /**
     * 校验不通过时的报错信息
     *
     * @return 校验不通过时的报错信息
     */
    String message() default "此用户不是韭零后，无法开户！";

    /**
     * 将validator进行分类，不同的类group中会执行不同的validator操作
     *
     * @return validator的分类类型
     */
    Class<?>[] groups() default {};

    /**
     * 主要是针对bean，很少使用
     *
     * @return 负载
     */
    Class<? extends Payload>[] payload() default {};
}
