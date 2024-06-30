package com.example.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Timetable)实体类
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:04:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timetable implements Serializable {

    private static final long serialVersionUID = -10198259057004572L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer objectId;
    /**
     * 课程id
     */
    private Integer code;
    /**
     * 区别老师和学生
     */
    private String tenant;
    /**
     * 星期
     */
    private Integer week;
    /**
     * 课程上课时间
     */
    private Integer start;
    /**
     * 下课时间
     */
    private Long finish;
    /**
     * 删除字段
     */
    private Integer deleteFlag;
}

