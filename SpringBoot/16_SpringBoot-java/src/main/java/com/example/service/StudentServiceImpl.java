package com.example.service;

import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public String sayHello() {
        return "Say Hello";
    }
}
