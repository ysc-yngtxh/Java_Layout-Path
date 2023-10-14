package com.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.Date;

/**
 * @author 游家纨绔
 */
@Data               // 属于Lombok依赖的注解，提供类的set,get，toString方法
@AllArgsConstructor // 属于Lombok依赖的注解，提供类的全参构造
@NoArgsConstructor  // 属于Lombok依赖的注解，提供类的无参构造
@TableName("tb_consumer")  // 属于通用mapper依赖的注解，用于表示连接的是哪张表
public class User {
 
    @TableId         // 属于MybatisPlus依赖的注解，用于标注主键
    private Integer id; // 主键Id

    private Integer superiorId; // 上级Id
    private String userName;    // 用户名
    private String passWord;    // 密码
    private String alias;       // 别名
    private Integer age;        // 年龄
    private Integer sex;        // 性别
    private String phone;       // 手机号
    private String address;     // 地址
    private String email;       // 邮件
    private Integer deleteFlag; // 逻辑删除
    private Date createdDate;   // 创建时间
    private Date updatedDate;   // 更新时间
}
