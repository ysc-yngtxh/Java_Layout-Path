package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Student;
import java.util.List;

/**
 * (Student)表服务接口
 * @author 游家纨绔
 * @since 2023-09-02 22:00:00
 */
public interface StudentService extends IService<Student> {

	List<Student> findMasterByIds();

	List<Student> findSlave_1ByIds();
}
