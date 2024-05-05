package com.example.dao;

import com.example.domain.Student;
import com.example.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;

import java.util.Collections;
import java.util.List;

/**
 * @author 游家纨绔
 */
public class StudentDaoImpl implements StudentDao {
    @Override
    public List<Student> selectStudents() {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "com.example.dao.StudentDao.selectStudents";
        // 执行SQL语句，使用SqlSession类的方法
        List<Student> students = sqlSession.selectList(sqlId);
        // 查询操作不需要开启事务，所以就不需要提交事务
        // 关闭
        sqlSession.close();
        return students;
    }

    @Override
    public List<Student> selectStudentsParam(Integer age, String name) {
        Student student = new Student();
        student.setAge(age);
        student.setName(name);
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "com.example.dao.StudentDao.selectStudentsParam";
        // 执行SQL语句，使用SqlSession类的方法
        List<Student> students = sqlSession.selectList(sqlId, student);
        // 查询操作不需要开启事务，所以就不需要提交事务
        // 关闭
        sqlSession.close();
        return students;
    }

    @Override
    public int insertStudent(Student student) {
        // 获取SqlSession对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "com.example.dao.StudentDao.insertStudent";
        // 执行SQL语句，使用SqlSession类的方法
        int nums = sqlSession.insert(sqlId, student);
        // 提交事务
        sqlSession.commit();
        // 关闭
        sqlSession.close();
        return nums;
    }
}