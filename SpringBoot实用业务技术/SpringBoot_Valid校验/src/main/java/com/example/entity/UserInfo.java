package com.example.entity;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import jakarta.validation.groups.Default;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-10 09:00
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

	private Long id;

	private String sex;

	@Range(message = "年龄范围为 {min} 到 {max} 之间", min = 10, max = 100, groups = {User.Student.class, User.Teacher.class})
	private Integer age;

	@DecimalMax(value = "2.00", message = "身高不能超过2.00", groups = {Default.class, User.Student.class})
	private Float height;

	// 日期必须在当前日期的过去
	@Past(message = "生日日期birthday必须是过去时间")
	private LocalDateTime birthday;

	// 日期必须在当前日期的未来
	@Future(message = "日期future必须是未来时间")
	private LocalDateTime future;
}
