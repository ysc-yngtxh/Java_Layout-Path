package com.example.transactional.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.transactional.entity.Student;

/**
 * (Student)表服务接口
 *
 * @author 游家纨绔
 * @since 2023-11-11 17:30:00
 */
public interface StudentService extends IService<Student> {

	void saveUser();

	void saveSigUser();

	void saveAllUser();

}
