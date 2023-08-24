package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表(TbUser)实体类
 *
 * @author 游家纨绔
 * @since 2023-08-24 23:42:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUser implements Serializable {
    private static final long serialVersionUID = -77721830271963325L;

    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码，加密存储
     */
    private String password;
    /**
     * 注册手机号
     */
    private String phone;
    /**
     * 创建时间
     */
    private Date created;
}

