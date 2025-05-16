package com.example.entity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Timetable)实体类
 *
 * @author 游家纨绔
 * @since 2024-06-30 19:00:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Timetable implements Serializable {
	@Serial
	private static final long serialVersionUID = -10198259057004572L;

	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 课程id
	 */
	private Integer courseId;
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
	private LocalDateTime start;
	/**
	 * 下课时间
	 */
	private LocalDateTime finish;
	/**
	 * 删除字段
	 */
	private Integer deleteFlag;
}
