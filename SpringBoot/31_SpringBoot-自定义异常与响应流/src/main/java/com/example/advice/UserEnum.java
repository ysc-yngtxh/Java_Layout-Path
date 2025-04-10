package com.example.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserEnum {

    // 注释
    NO_INSERT(201, "新增商品失败!"),
    NO_URL(404, "无法访问到资源");

    private Integer cord;
    private String msg;

}
