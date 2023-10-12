package com.youshicheng.pojo;

import lombok.Data;
import lombok.ToString;

/**
 * @author 游家纨绔
 */
@Data
@ToString
public class User {

    private Integer id;

    private String username;  // 用户名
    private String password;  // 密码
    private String name;      // 姓名
    private Integer age;      // 年龄
    private Integer sex;      // 性别  1为男性，2为女性

    private String note;      // 备注
}
