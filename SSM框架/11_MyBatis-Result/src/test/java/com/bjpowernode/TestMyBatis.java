package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.MyBatisUtils;
import com.bjpowernode.vo.MyStudent;
import com.bjpowernode.vo.ViewStudent;
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

        System.out.println("5 students="+students);
        sqlSession.close();
    }

    @Test//返回Map
    public void testSelectMap(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Map<Object,Object> map = dao.selectMapById(1001);
        System.out.println("Map=="+map);
        sqlSession.close();
    }

    @Test/*列名和属性名不一样：第一种方法*/
    public void testSelectAll(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> students = dao.selectAllStudent();
        for (Student stu:students
             ) {
            System.out.println("学生="+stu);
        }
        sqlSession.close();
    }

    @Test/*列名和属性名不一样：第二种方法*/
    public void testSelectDiffcol(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<MyStudent> students = dao.selectDiffcol();
        for (MyStudent stu:students
        ) {
            System.out.println("###学生="+stu);
        }
        sqlSession.close();
    }

    @Test/*模糊查询的第一种方式*/
    public void testSelectLikeone(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        //准备好Like的内容
        String name = "%李%";
        List<Student> students = dao.selectLikeOne(name);

        for (Student stu:students
        ) {
            System.out.println("###学生="+stu);
        }
        sqlSession.close();
    }

    @Test/*模糊查询的第二种方式*/
    public void testSelectLiketwo(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        //准备好Like的内容
        String name = "李";
        List<Student> students = dao.selectLikeTwo(name);

        for (Student stu:students
        ) {
            System.out.println("###学生="+stu);
        }
        sqlSession.close();
    }
}
