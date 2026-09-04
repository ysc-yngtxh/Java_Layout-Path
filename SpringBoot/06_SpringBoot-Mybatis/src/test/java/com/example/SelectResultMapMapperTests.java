package com.example;

import com.example.mapper.SelectResultMapMapper;
import com.example.pojo.Department;
import com.example.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectResultMapMapperTests {

    @Autowired
    private SelectResultMapMapper selectResultMapMapper;

    // 一名学生对应一份档案（一对一）
	@Test
	public void selectStudentWithProfile() {
        Student student = selectResultMapMapper.selectStudentWithProfile(1);
        System.out.println("学生 = " + student);
	}

    // 一个院系对应多名学生 （一对多）
	@Test
	public void departmentWithStudents() {
        Department department = selectResultMapMapper.departmentWithStudents(101);
        System.out.println("院系 = " + department);
	}

    // 使用 resultMap 标签，用以 Java类属性 与 DB中的字段映射
	@Test
	public void studentWithCourses() {
        Student student = selectResultMapMapper.studentWithCourses(1);
        System.out.println("学生 = " + student);
	}

}
