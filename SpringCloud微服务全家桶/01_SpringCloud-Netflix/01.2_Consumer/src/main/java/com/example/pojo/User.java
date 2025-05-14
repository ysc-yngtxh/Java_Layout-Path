package com.example.pojo;

import java.util.Date;
import lombok.Data;
import lombok.ToString;

/**
 * @author 游家纨绔
 */
@Data
@ToString
public class User {

    /**
     * 主键Id
     */
    private Integer id;
    /**
     * 上级Id
     */
    private Integer superiorId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
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
