package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Student)表实体类
 * @author 游家纨绔
 * @since 2023-09-02 21:59:40
 */
@Data
public class Student implements Serializable {
    @Serial
    private static final long serialVersionUID = 1568054283732686032L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String name;

    private String email;

    private Integer age;
}

