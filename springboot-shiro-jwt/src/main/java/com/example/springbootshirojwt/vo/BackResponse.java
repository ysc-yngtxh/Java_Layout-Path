package com.example.springbootshirojwt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class BackResponse {

    //状态码
    private int code;

    //消息内容
    private String message;

    //返回数据
    private Object obj;


    public BackResponse success(int code,String message){
        return new BackResponse(code,message,null);
    }

    public BackResponse success(int code,String message, Object obj){
        return new BackResponse(code,message,obj);
    }

    public BackResponse error(int code,String message){
        return new BackResponse(code,message,null);
    }

    public BackResponse error(int code,String message, Object obj){
        return new BackResponse(code,message,obj);
    }
}
