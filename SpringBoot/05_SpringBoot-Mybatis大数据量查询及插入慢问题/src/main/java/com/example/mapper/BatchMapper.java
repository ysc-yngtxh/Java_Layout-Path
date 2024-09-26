package com.example.mapper;

import com.example.domain.Student;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-15 22:39
 * @apiNote TODO 批处理
 */
public interface BatchMapper {

    void batchInsert(Student list);
    void insertForeach(List<Student> list);
}
