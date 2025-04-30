package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Courses)表实体类
 *
 * @author makejava
 * @since 2023-08-18 23:25:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("courses")
public class Courses extends Model<Courses> {

    @Serial
    private static final long serialVersionUID = 7582516928460081442L;

    // 主键课程id
    @TableId("cid")
    private Integer cid;

    // 课程编码
    private Integer code;

    // 课程名称
    private String cname;

    // 专业名称
    private String zyname;

    // 任课教师
    private String teacher;

    // 授课班级
    private String tclass;

    // 删除字段
    private Integer deleteFlag;

}
