package com.example.dddmessage.interfaces.web;

import lombok.Builder;
import lombok.Data;

/**
 * @author 游家纨绔
 * @since 2024-04-18 07:20:00
 */
@Data
@Builder
public class Response<T> {
    public Status status;
    public String msg;
    public T data;

    public static <T> Response<T> ok() {
        return Response.<T>builder().status(Status.SUCCESS).build();
    }

    public static <T> Response<T> ok(T data) {
        return Response.<T>builder().status(Status.SUCCESS).data(data).build();
    }

    public static <T> Response<T> failed(String msg) {
        return Response.<T>builder().status(Status.FAILED).msg(msg).build();
    }

    public enum Status {
        SUCCESS, FAILED
    }
}