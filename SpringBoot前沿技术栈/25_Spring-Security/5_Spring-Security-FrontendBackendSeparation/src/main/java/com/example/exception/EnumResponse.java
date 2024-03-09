package com.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author example
 * @dateTime 2023-05-08 17:08
 * @apiNote TODO 异常枚举
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum EnumResponse {

    EXCEPTION(401, "认证失败"),
    EXCEPTION_ADVICE(403, "鉴权失败"),
    NO_EXIT_USER(404, "用户名不存在!!!");

    private Integer code;

    private String message;
}
