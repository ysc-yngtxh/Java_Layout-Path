package com.bjpowernode.Service;

import com.bjpowernode.domain.Student;
import com.bjpowernode.dao.StudentDao;

import java.util.List;

/**
 * @author 游家纨绔
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;

    //使用set注入，赋值
    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public void addStudent(Student student) {
        studentDao.insertStudent(student);
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
}
