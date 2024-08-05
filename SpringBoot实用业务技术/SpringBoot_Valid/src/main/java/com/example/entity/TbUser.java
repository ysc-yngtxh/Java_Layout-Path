package com.example.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 用户表(TbUser)实体类
 *
 * @author 游家纨绔
 * @since 2024-08-03 16:25:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TbUser implements Serializable {

    private static final long serialVersionUID = -98875421927312539L;

    private Long id;

    @NotBlank(message = "请输入名称")
    @Length(message = "名称不能超过个 {max} 字符", max = 10)
    private String username;

    @NotNull(message = "请输入密码")
    @Range(message = "密码范围为 {min} 到 {max} 之间", min = 10, max = 100)
    private String password;

    @NotEmpty(message = "请输入手机号")
    @Size(message = "手机号长度为 {min} 到 {max} 位", min = 9, max = 11)
    private String phone;

    private Date created;

    @Max(message = "code is not larger than Integer.MAX_VALUE", value = Integer.MAX_VALUE)
    private transient Long code;
}

