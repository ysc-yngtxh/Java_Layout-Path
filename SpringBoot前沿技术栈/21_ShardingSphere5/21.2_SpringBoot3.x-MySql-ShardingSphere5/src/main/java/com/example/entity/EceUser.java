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
 * @author 游家纨绔
 * @since 2024-09-17 12:43:59
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EceUser implements Serializable {

    @Serial
    private static final long serialVersionUID = -935366043629512096L;

    /**
     * 主键 Id
     */
    private long id;
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
     * 生日
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
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
}

