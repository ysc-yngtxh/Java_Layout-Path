package com.example.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * (User)实体类
 *
 * @author 游家纨绔
 * @since 2024-09-11 22:37:34
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID = 311930963439804215L;

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

