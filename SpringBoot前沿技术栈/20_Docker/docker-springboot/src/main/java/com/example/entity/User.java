package com.example.entity;

import lombok.Data;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2023-07-22 08:45:19
 */
@Data
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 951144171385750844L;
    
    private Integer id;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 性别
     */
    private String sex;
    /**
     * 地址
     */
    private String address;
}

