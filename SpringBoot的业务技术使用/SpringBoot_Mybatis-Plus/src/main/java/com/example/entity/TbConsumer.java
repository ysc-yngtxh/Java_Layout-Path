package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * (TbConsumer)实体类
 * @author 游家纨绔
 * @since 2023-08-29 06:30:48
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonPropertyOrder({"id", "superiorId", "userName", "passWord", "alias", "age", "sex", "phone", "address", "email", "deleteFlag", "createdDate", "updatedDate", "json"})
@TableName(value = "consumer", keepGlobalPrefix = true)
public class TbConsumer implements Serializable {
    @Serial
    private static final long serialVersionUID = 692869694057194050L;

    // 主键Id
    private Integer id;
    // 上级Id
    private Integer superiorId;
    // 用户名
    private String userName;
    // 密码
    private String passWord;
    // 别名
    private String alias;
    // 年龄
    private Integer age;
    // 性别
    private Integer sex;
    // 手机号
    private String phone;
    // 地址
    private String address;
    // 邮件
    private String email;
    // 逻辑删除
    private Integer deleteFlag;
    // 创建时间
    private Date createdDate;
    // 更新时间
    private Date updatedDate;
    // 更新时间
    private String json;
}

