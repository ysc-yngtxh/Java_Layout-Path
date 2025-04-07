package com.example.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * 角色实体类
 */
public class SSMRole implements Serializable {
    @Serial
    private static final long serialVersionUID = -7073889195974955098L;

    private Integer id;
    private String roleName;
    private String roleDesc;

    private List<SSMUser> userList; // 一个角色对应多个用户: 一对多

    @Override
    public String toString() {
        return "SSMRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", userList=" + userList +
                '}';
    }
}
