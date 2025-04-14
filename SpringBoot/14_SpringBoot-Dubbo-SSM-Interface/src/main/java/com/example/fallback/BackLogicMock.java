package com.example.fallback;

import com.example.domain.Student;
import com.example.service.StudentService;

/**
 * @Author 游家纨绔
 * @Description TODO 降级服务
 * @Date 2025-04-13 19:30:00
 */
public class BackLogicMock implements StudentService {

	@Override
	public Student queryStudentById(Integer id) {
		// 当服务不可用时返回默认值
		return Student.builder()
				      .id(0)
		              .name("降级用户")
		              .age(100)
		              .build();
	}

	@Override
	public Integer queryAllStudentCount() {
		return 0;
	}
}
