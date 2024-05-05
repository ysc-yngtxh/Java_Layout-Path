package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Employee)实体类
 * @author 游家纨绔
 * @since 2023-09-02 14:47:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = -19667533095635153L;

    private Integer id;

    private String name;

    private Integer age;

    private String sex;
}

