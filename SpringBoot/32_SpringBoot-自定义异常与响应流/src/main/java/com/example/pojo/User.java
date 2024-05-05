package com.example.pojo;

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
@Table(name = "consumer")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username; // 用户名
    private String password; // 密码，加密存储
    private Integer age;
    private String name;
    private Integer sex;

}
