package com.example.mybatis2.entity;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author 游家纨绔
 * @since 2024-04-04 23:58:33
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 884930802771289886L;

    private Integer id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 年龄
     */
    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

