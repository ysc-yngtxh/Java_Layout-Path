package com.example.pojo;

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
@Table(name = "tb_consumer")
public class User {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Integer id;
    private String username; // 用户名
    private String password; // 密码，加密存储
    private String alias;
    private Integer age;
    private String sex;
    private String phone;
    private String address;
    @Column(name = "delete_flag")
    private boolean deleteFlag;

}
