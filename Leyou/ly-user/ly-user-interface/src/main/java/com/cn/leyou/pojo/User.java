package com.cn.leyou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Table(name="tb_user")
@Data
public class User {

    @Id
    @KeySql
    private Long id;

    @NotEmpty(message="用户名不能为空")
    @Length(min=4,max=32,message="用户名长度必须在4~32位")
    private String username; //用户名

    @Length(min=4,max=32,message="密码长度必须在4~32位")
    @JsonIgnore//这个注解是用于在json序列化时将pojo中的一些属性忽略掉，返回的json数据即不包含该属性
    private String password; //密码

    private String phone;  //电话

    private Date created; //创建时间

    @JsonIgnore
    private String salt;  //密码的盐值
}
