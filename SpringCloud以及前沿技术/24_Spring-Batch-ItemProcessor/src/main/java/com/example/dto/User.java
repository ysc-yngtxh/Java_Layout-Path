package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-02 18:20
 * @apiNote TODO 用户类
 */
@Data
public class User {

    private long Id;
    @NotBlank(message = "用户名不能为null或空值")
    private String name;
    private int age;
}
