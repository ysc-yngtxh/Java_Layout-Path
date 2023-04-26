package com.example.springbootshirojwt.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {

    private Integer id;

    /**
     *  用户名
     */
    private String name;

    /**
     *  密码
     */
    private String pwd;

    /**
     *  权限
     */
    private String perms;

    @Transient
    private String code;

}
