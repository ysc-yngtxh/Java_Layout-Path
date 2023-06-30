package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Employee)实体类
 *
 * @author makejava
 * @since 2023-06-27 23:55:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {
    @Serial
    private static final long serialVersionUID = 716079599264900512L;
    
    private Integer id;
    
    private String name;
    
    private Integer age;
    
    private Integer sex;

}

