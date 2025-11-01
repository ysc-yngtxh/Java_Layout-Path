package com.example.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用户表(EceUser)实体类
 *
 * @author 游家纨绔
 * @since 2024-09-20 21:15:00
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EceUser implements Serializable {
    @Serial
    private static final long serialVersionUID = 893554974075705049L;

    /**
     * 用户ID
     */
    private Long id;
    /**
     * eceId
     */
    private Integer eceId;
    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 生日
     */
    private LocalDateTime birthday;
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
    /**
     * 创建时间
     */
    private LocalDateTime createDate;
    /**
     * 更新时间
     */
    private LocalDateTime updateDate;
    /**
     * 用户状态
     */
    private String status;
}
