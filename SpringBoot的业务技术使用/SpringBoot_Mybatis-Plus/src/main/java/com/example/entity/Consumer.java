package com.example.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * (Consumer)实体类
 * @author 游家纨绔
 * @since 2023-08-29 06:30:48
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Consumer extends Model<Consumer> {
    @Serial
    private static final long serialVersionUID = 692869694057194050L;
    /**
     * 主键Id
     */
    private Integer id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 别名
     */
    private String alias;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 地址
     */
    private String address;
    /**
     * 邮件
     */
    private String email;
    /**
     * 逻辑删除
     */
    private Integer deleteFlag;
    /**
     * 创建时间
     */
    private Date createdDate;
    /**
     * 更新时间
     */
    private Date updatedDate;
}

