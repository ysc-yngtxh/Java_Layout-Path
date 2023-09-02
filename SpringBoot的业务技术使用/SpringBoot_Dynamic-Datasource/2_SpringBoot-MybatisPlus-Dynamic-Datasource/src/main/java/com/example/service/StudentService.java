package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.Student;
import com.example.entity.User;

import java.util.List;

/**
 * (Student)表服务接口
 * @author 游家纨绔
 * @since 2023-09-02 21:59:40
 */
public interface StudentService extends IService<Student> {
    List<Student> findMasterByIds();
    List<Student> findSlave_1ByIds();
}

