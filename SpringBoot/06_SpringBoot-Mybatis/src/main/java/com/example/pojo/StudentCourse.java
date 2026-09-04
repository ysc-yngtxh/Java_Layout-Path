package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 学生选课表 实体类
 * 对应数据库表：db_student_course
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

    /**
     * 记录ID（主键，自增）
     */
    private Integer id;

    /**
     * 学生ID（外键，关联 db_student.id）
     */
    private Integer studentId;

    /**
     * 课程ID（外键，关联 db_course.id）
     */
    private Integer courseId;

    /**
     * 学年，格式如：2023-2024
     */
    private String academicYear;

    /**
     * 学期（SPRING, SUMMER, FALL, WINTER）
     */
    private Semester semester;

    /**
     * 成绩（保留两位小数）
     */
    private BigDecimal score;

    /**
     * 绩点（保留两位小数）
     */
    private BigDecimal gradePoint;

    /**
     * 选课状态（REGISTERED, IN_PROGRESS, COMPLETED, DROPPED, FAILED）
     * 默认 REGISTERED
     */
    private EnrollmentStatus status;

    /**
     * 选课时间（默认当前时间）
     */
    private LocalDateTime registeredAt;

    /**
     * 完成时间（可为空）
     */
    private LocalDateTime completedAt;

    /**
     * 创建时间（默认当前时间）
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间（默认当前时间，更新时自动刷新）
     */
    private LocalDateTime updatedAt;

    /**
     * 学期枚举
     */
    public enum Semester {
        SPRING, SUMMER, FALL, WINTER
    }

    /**
     * 选课状态枚举
     */
    public enum EnrollmentStatus {
        REGISTERED, IN_PROGRESS, COMPLETED, DROPPED, FAILED
    }
}
