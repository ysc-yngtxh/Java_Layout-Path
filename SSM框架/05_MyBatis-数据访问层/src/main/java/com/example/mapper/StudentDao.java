package com.example.mapper;

import com.example.pojo.Student;

import java.util.List;

/**
 * @author 游家纨绔
 */
// 接口操作student表
public interface StudentDao {

    // 查询student表的所有的数据
    List<Student> selectStudents();

    List<Student> selectStudentsParam(Integer age, String name);

    // 插入方法
    //     参数：student，表示要插入到数据库的数据
    //     返回值：int，表示insert操作后的 影响数据库的行数
    int insertStudent(Student student);
}
