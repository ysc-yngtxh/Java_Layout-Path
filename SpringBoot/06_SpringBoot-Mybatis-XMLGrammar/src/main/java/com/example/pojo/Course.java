package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程表 实体类
 * 对应数据库表：db_course
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    /**
     * 课程ID（主键，自增）
     */
    private Integer id;

    /**
     * 课程代码（唯一，非空）
     */
    private String courseCode;

    /**
     * 课程名称（非空）
     */
    private String courseName;

    /**
     * 学分（精确到1位小数，默认1.0）
     */
    private BigDecimal credit;

    /**
     * 课时数（默认36）
     */
    private Integer hours;

    /**
     * 授课教师ID（可为空）
     */
    private Integer teacherId;

    /**
     * 最大学生数（默认50）
     */
    private Integer maxStudents;

    /**
     * 课程描述（可为空）
     */
    private String description;

    /**
     * 课程状态：ACTIVE, INACTIVE, ARCHIVED（默认ACTIVE）
     * 建议配合自定义枚举使用，这里暂用String
     */
    private String status;

    /**
     * 创建时间（默认当前时间）
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间（默认当前时间，更新时自动刷新）
     */
    private LocalDateTime updatedAt;

}
