package com.example.pojo;

import java.util.Date;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 游家纨绔
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "db_user")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
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

    @Column(name = "delete_flag")
    private Boolean deleteFlag; // 逻辑删除（0:存在；1:注销）

    private Date createdDate;   // 创建时间
    private Date updatedDate;   // 更新时间
}
