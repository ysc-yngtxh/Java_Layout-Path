package com.example.service;

import com.example.domain.Student;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-16 10:09
 * @apiNote TODO
 */
public interface BatchService {

    void insertForeach(List<Student> list);

    void batchInsert(List<Student> list);

    void batchInsertForeach(List<Student> list);
}
