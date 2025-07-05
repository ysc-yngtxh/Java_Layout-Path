package com.example.transactionalInAsync.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.transactional.entity.Student;

/**
 * (Student)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-11-11 17:30:00
 */
public interface StudentAsyncService extends IService<Student> {

	void saveAndAsync();

	void saveAndAsyncException();

	default void saveAndAsyncScheme1() {
		throw new RuntimeException();
	}
	default void saveAndAsyncScheme2() {
		throw new RuntimeException();
	}
	default void saveAndAsyncScheme3() {
		throw new RuntimeException();
	}

}
