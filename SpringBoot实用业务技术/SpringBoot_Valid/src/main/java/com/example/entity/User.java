package com.example.entity;

import com.example.annotation.IsLeek;
import jakarta.validation.Valid;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

/**
 * 用户表(User)实体类
 *
 * @author 游家纨绔
 * @since 2024-08-03 16:25:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -98875421927312539L;

    @IsLeek
    private String account;

    @NotBlank(message = "请输入名称，不能为空格符号")
    @Length(message = "名称不能超过个 {max} 字符", max = 10, groups = {Student.class})
    private String username;

    @NotNull(message = "请输入密码，不能为null")
    @Range(message = "密码范围为 {min} 到 {max} 之间", min = 10, max = 100, groups = {Default.class, Student.class, Teacher.class})
    private String password;

    @NotEmpty(message = "请输入手机号，不能为空")
    @Size(message = "手机号长度为 {min} 到 {max} 位", min = 9, max = 11, groups = {Teacher.class})
    private String phone;

    private Date created;

    @Max(message = "代码不大于 Integer.MAX_VALUE", value = Integer.MAX_VALUE)
    private transient Long code;

    @Email(message = "请提供一个有效的电子邮件地址")
    private String email;

    @AssertTrue(message = "属性valid只能为true", groups = {Student.class})
    private boolean valid;

    @AssertFalse(message = "属性deleted只能为false", groups = {Teacher.class})
    private boolean deleted;

    @Pattern(regexp = "^[1-9]]\\d*$", message = "regex参数值必须是正整数")
    private String regex;

    @NotNull(message = "无法嵌套校验的属性nonNestingUserInfo不能为null")
    private UserInfo nonNestingUserInfo;

    @Valid
    @NotNull(message = "可以嵌套校验的属性nestingUserInfo不能为null")
    private UserInfo nestingUserInfo;


    public interface Student{}
    public interface Teacher{}
}

