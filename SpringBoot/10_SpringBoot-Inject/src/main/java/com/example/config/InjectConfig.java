package com.example.config;

import com.example.mapper.StudentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 游家纨绔
 * @dateTime 2024-05-12 12:00:00
 * @apiNote TODO
 */
@Configuration
public class InjectConfig {

    @Bean
    public SetterService userService(StudentMapper studentMapper) {
        SetterServiceImpl userService = new SetterServiceImpl();
        userService.setStudentMapper(studentMapper);  // 手动调用 setter
        return userService;
    }

}
