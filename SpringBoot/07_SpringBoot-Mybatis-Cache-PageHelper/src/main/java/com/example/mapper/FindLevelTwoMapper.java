package com.example.mapper;

import com.example.pojo.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface FindLevelTwoMapper {

	Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    @Select("""
             select
                 id,
                 name,
                 teacher_id AS teacherId,
                 email,
                 age,
                 department_id AS departmentId
             from db_student
    """)
    List<Student> selectAll();

}
