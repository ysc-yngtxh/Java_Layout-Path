package com.example.mapper;

import com.example.pojo.Course;
import com.example.pojo.Department;
import com.example.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 游家纨绔
 */
@Mapper // 扫描mapper接口到Spring容器
public interface SelectResultMapMapper {

    Student selectStudentWithProfile(Integer id);

    Department departmentWithStudents(Integer did);

    Student studentWithCourses(Integer sid);

}
