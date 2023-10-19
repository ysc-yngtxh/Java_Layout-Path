package com.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 通用结果返回
 */
@Data
@NoArgsConstructor
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message) {
        this(code, message, null);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success() {
        Result response = new Result(200, "操作成功");
        return response;
    }
    public static <T> Result<T> success(String message) {
        Result response = new Result(200, message);
        return response;
    }
    public static <T> Result<T> success(T date) {
        Result response = new Result(200, "操作成功", date);
        return response;
    }
    public static <T> Result<T> success(String message, T date) {
        Result response = new Result(200, message, date);
        return response;
    }

    public static <T> Result<T> failed() {
        Result response = new Result(500, "操作失败");
        return response;
    }

    public static <T> Result<T> failed(Integer code, String message, T date) {
        Result response = new Result(code, message, date);
        return response;
    }

    public static <T> Result<T> failed(Integer code, String message) {
        Result response = new Result(code, message);
        return response;
    }
}
