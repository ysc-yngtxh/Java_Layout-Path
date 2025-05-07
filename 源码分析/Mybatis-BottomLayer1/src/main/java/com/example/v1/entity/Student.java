package com.example.v1.entity;

import java.io.Serial;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Student)实体类
 * @author 游家纨绔
 * @since 2024-04-04 23:58:33
 */
@Data
@ToString
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 884930802771289886L;

    private Integer id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 年龄
     */
    private String age;
}
