package com.example.service;

import com.example.domain.Student;

import java.util.List;

public interface StudentService {

    // 测试mybatis的一级缓存
    Student queryStudentByIdCacheL1(Integer id);

    // 使用replace into方式新增或更新
    int insertReplaceInto(Student student);

    // 使用insert ignore into，存在就忽略新增
    int insertIgnoreInto(Student student);

    // 使用on duplicate key update方式新增或更新
    int insertDuplicateKeyUpdate(Student student);

    // 使用on duplicate key update方式批量新增或更新
    int insertDuplicateKeyUpdateBatch(List<Student> list);

    // 多条语句合并操作实现的新增或更新
    int insertOrUpdateOneUserInfo(Student student);
}
