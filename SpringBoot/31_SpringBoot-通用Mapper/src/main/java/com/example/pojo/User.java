package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author 游家纨绔
 */
@Data               // 属于Lombok依赖的注解，提供类的set,get，toString方法
@AllArgsConstructor // 属于Lombok依赖的注解，提供类的全参构造
@NoArgsConstructor  // 属于Lombok依赖的注解，提供类的无参构造
@Table(name="consumer")  // 属于通用mapper依赖的注解，用于表示连接的是哪张表
public class User {

    @Id         // 属于通用mapper依赖的注解，用于标注主键
    // @KeySql  // 属于通用mapper依赖的注解，用于表示字段的自增
    private Integer id;

    private String username;  // 用户名
    private String password;  // 密码
    private String name;      // 姓名
    private Integer age;      // 年龄
    private Integer sex;      // 性别  1为男性，2为女性

    @Transient  // 代表的是瞬时的，不会持久化到数据库，也不会参与SQL语句的执行
    private String note;      // 备注
}
