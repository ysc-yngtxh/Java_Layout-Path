package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Consumer)实体类
 *
 * @author makejava
 * @since 2023-07-10 23:54:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumer implements Serializable {
    @Serial
    private static final long serialVersionUID = -25637857384418168L;
    
    private Integer id;
    
    private String username;
    
    private String password;
    
    private String alias;
    
    private Integer age;
    
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    
    private String address;
    
    private Integer deleteFlag;
}

