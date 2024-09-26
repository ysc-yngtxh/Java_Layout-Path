package com.example.mapper;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    // 根据Id查询数据
    Student selectByPrimaryKey(Integer id);

    // 使用replace into方式新增或更新
    int insertReplaceInto(Student student);

    // 使用insert ignore into，存在就忽略新增
    int insertIgnoreInto(Student student);

    // 使用on duplicate key update方式新增或更新
    int insertDuplicateKeyUpdate(Student student);

    // 使用on duplicate key update方式批量新增或更新
    int insertDuplicateKeyUpdateBatch(@Param("students") List<Student> list);

    // 多条语句合并操作实现的新增或更新
    int insertOrUpdateOneUserInfo(Student student);
}