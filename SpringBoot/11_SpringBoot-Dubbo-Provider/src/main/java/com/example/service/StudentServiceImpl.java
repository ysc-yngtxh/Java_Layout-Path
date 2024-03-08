package com.example.service;

import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 */
@Component
@Service(interfaceClass = StudentService.class, version = "1.0.0", timeout = 15000)
public class StudentServiceImpl implements StudentService {

    @Override
    public Integer queryAllStudentCount(String count) {
        // 调用数据持久层
        return 1250;
    }
}
