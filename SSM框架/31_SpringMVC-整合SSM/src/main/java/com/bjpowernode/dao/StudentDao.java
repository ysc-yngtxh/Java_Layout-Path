package com.bjpowernode.dao;

import com.bjpowernode.domain.Student;
import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

    int insertStudent(Student student);
    List<Student> selectStudents();
}
