package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-07-09 10:27
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
public class ResponseVo {

    private Integer code;

    private Object data;

    private String message;

    public ResponseVo(Integer code, String message){
        this.code = code;
        this.message = message;
    }

    public static ResponseVo success(Integer code, Object data, String message){
        return new ResponseVo(code, data, message);
    }
    public static ResponseVo fail(Integer code, String message){
        return new ResponseVo(code, message);
    }
}
