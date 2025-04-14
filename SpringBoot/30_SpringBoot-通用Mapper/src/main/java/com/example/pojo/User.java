package com.example.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 */
@Data                   // 属于Lombok依赖的注解，提供类的set、get、toString方法
@AllArgsConstructor     // 属于Lombok依赖的注解，提供类的全参构造
@NoArgsConstructor      // 属于Lombok依赖的注解，提供类的无参构造
@Table(name="db_user")  // 属于通用mapper依赖的注解，用于表示连接的是哪张表
public class User {

    @Id         // 属于通用mapper依赖的注解，用于标注主键
    // @KeySql  // 属于通用mapper依赖的注解，用于表示字段的自增
    private Integer id;

    private Integer superiorId; // 上级Id
    private String userName;    // 用户名
    private String passWord;    // 密码
    private String alias;       // 别名
    private Integer age;        // 年龄
    private Integer sex;        // 性别（0为女性，1为男性）
    private String phone;       // 手机号
    private String address;     // 地址
    private String email;       // 邮件
    private Integer deleteFlag; // 逻辑删除（0:存在；1:注销）
    private Date createdDate;   // 创建时间
    private Date updatedDate;   // 更新时间

    @Transient                  // @Transient注解代表的是瞬时的，不会持久化到数据库，也不会参与SQL语句的执行
    private String note;        // 备注
}
