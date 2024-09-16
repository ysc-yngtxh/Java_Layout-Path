package com.example.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * (EceUser)实体类
 *
 * @author makejava
 * @since 2024-09-16 13:12:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EceUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -12368305365031341L;
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 生日时间
     */
    private Date birthday;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private String sex;
    /**
     * 地址
     */
    private String address;
}

