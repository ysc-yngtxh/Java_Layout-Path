package com.example.pojo.dto;

import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-14 23:41
 * @apiNote TODO 映射用户信息
 */
@Data
public class User {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String password;
}
