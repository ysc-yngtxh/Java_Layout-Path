package com.example.mapper;

import com.example.pojo.Student;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

    int insertStudent(Student student);

    List<Student> selectStudents();
}
