package com.example.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 */
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = -7073889195974955098L;

    private Integer id;
    private String role_name;
    private String role_desc;

    private List<User> userList; // 一个角色对应多个用户: 一对多

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role_name='" + role_name + '\'' +
                ", role_desc='" + role_desc + '\'' +
                ", userList=" + userList +
                '}';
    }
}