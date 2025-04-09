package com.example.mapper;

import com.example.pojo.SSMStudent;
import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

    int insertStudent(SSMStudent SSMStudent);

    List<SSMStudent> selectStudents();
}
