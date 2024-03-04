package com.example;

import com.example.dao.StudentDao;
import com.example.domain.Student;
import com.example.util.MyBatisUtils;
import com.example.vo.MyStudent;
import com.example.vo.ViewStudent;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestMyBatis {
    @Test
    public void testSelectByStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        ViewStudent students = dao.selectStudentByView(5);

        System.out.println("students=" + students);
        sqlSession.close();
    }

    // 返回Map
    @Test
    public void testSelectMap(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Map<Object,Object> map = dao.selectMapById(1);
        System.out.println("Map==" + map);
        sqlSession.close();
    }

    // 列名和属性名不一样：第一种方法
    @Test
    public void testSelectAll(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.selectAllStudent();
        for (Student stu : students) {
            System.out.println("学生=" + stu);
        }
        sqlSession.close();
    }

    // 列名和属性名不一样：第二种方法
    @Test
    public void testSelectDiffCol(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<MyStudent> students = dao.selectDiffCol();
        for (MyStudent stu : students) {
            System.out.println("###学生=" + stu);
        }
        sqlSession.close();
    }

    // 模糊查询的第一种方式
    @Test
    public void testSelectLikeOne(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 准备好Like的内容
        String name = "%李%";
        List<Student> students = dao.selectLikeOne(name);

        for (Student stu : students) {
            System.out.println("###学生=" + stu);
        }
        sqlSession.close();
    }

    // 模糊查询的第二种方式
    @Test
    public void testSelectLikeTwo(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 准备好Like的内容
        String name = "李";
        List<Student> students = dao.selectLikeTwo(name);

        for (Student stu : students) {
            System.out.println("###学生=" + stu);
        }
        sqlSession.close();
    }

    // 模糊查询的第三种方式
    @Test
    public void testSelectLikeThree(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 准备好Like的内容
        String name = "李";
        List<Student> students = dao.selectLikeThree(name);

        for (Student stu : students) {
            System.out.println("###学生=" + stu);
        }
        sqlSession.close();
    }
}
