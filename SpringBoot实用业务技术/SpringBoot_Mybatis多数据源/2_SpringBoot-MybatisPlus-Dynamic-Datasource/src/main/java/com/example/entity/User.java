package com.example.entity;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (User)表实体类
 * @author 游家纨绔
 * @since 2023-09-02 22:23:03
 */
@Data
public class User implements Serializable{
    @Serial
    private static final long serialVersionUID = 6315928930985335023L;

    private Integer id;

    private String name;

    private String pwd;

    private String perms;
}

