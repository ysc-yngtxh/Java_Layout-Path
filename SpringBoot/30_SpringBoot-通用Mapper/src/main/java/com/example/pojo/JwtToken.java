package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtToken {

    // 状态码
    private int code;

    // 消息内容
    private String msg;

    // 返回数据
    private Object obj;

    public JwtToken success(String message) {
        return new JwtToken(200, message, null);
    }

    public JwtToken success(String message, Object obj) {
        return new JwtToken(200, message, obj);
    }

    public JwtToken error(String message) {
        return new JwtToken(403, message, null);
    }

    public JwtToken error(String message, Object obj) {
        return new JwtToken(500, message, obj);
    }
}
