package com.example.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 23:29
 * @apiNote TODO 学生模型
 */
@Data
public class StudentModel {

    /** ID */
    protected String id;

    /** 创建时间 */
    protected Date createTime;

    /** 姓名 */
    private String name;

    /** 年龄 */
    private Integer age;

    /** 学号 */
    private String studentNo;

    /** 创建人 */
    private String createUser;

    /** 创建人ID */
    private String createUserId;

    /** 状态 */
    private Integer status;

    /** 图书信息 */
    private BookModel book;
}
