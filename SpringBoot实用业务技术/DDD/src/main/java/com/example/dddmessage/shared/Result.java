package com.example.dddmessage.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Result<T> {

    private Error error;
    private T data;

    public boolean isSuccess(){
        return error == null;
    }

    public static <T> Result<T> success(){
        return new Result<>();
    }

    public static <T> Result<T> create(T data){
        return new Result<>(null, data);
    }

    public static <T> Result<T> create(Error error){
        return new Result<>(error, null);
    }

    public static <T> Result<T> create(Error error,T data){
        return new Result<>(error, data);
    }
}