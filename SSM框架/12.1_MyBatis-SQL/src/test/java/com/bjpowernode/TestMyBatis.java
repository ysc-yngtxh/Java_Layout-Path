package com.bjpowernode;

import com.bjpowernode.dao.StudentDao;
import com.bjpowernode.domain.Student;
import com.bjpowernode.util.MyBatisUtils;
import com.bjpowernode.vo.MyStudent;
import com.bjpowernode.vo.ViewStudent;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestMyBatis {



    @Test/*if语句的使用*/
    public void testSelectLikeOne(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("李四");
        student.setAge(18);
        List<Student> students = dao.selectStudentIf(student);
        for (Student stu :students
             ) {
            System.out.println("if==="+stu);
        }
        sqlSession.close();
    }

    @Test/*where配合if语句的使用*/
    public void testSelectLikeTwo(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("李四");
        student.setAge(18);
        List<Student> students = dao.selectStudentWhere(student);
        for (Student stu :students
        ) {
            System.out.println("where==="+stu);
        }
        sqlSession.close();
    }

    @Test/*foreach在不写配置文件情况下*/
    public void testFor(){
        List<Integer> list = new ArrayList<>();
        list.add(1001);
        list.add(1002);
        list.add(1003);

        String sql = "select * from student where id in";

        StringBuilder builder = new StringBuilder("");

        //添加开始的
        builder.append("(");
        for (Integer i:list
             ) {
            builder.append(i+",");
        }
        builder.deleteCharAt(builder.length()-1);
        //循环结尾
        builder.append(")");
        sql = sql+builder.toString();
        System.out.println("sql=="+sql);
    }

    @Test/*foreach 用法1*/
    public void testforEachOne(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Integer> list = new ArrayList<>();
        list.add(1001);
        list.add(1002);
        list.add(1003);

        List<Student> students = dao.selectForEachOne(list);
        for (Student stu :students
        ) {
            System.out.println("foreach--one==="+stu);
        }
    }

    @Test/*foreach 用法2*/
    public void testforEachTwo(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> stulist = new ArrayList<>();
        Student s = new Student();
        s.setId(1001);
        stulist.add(s);

        s=new Student();
        s.setId(1005);
        stulist.add(s);

        List<Student> students = dao.selectForEachTwo(stulist);
        for (Student stu :students
        ) {
            System.out.println("foreach--two==="+stu);
        }
    }

    @Test/*pageHelper的用法*/
    public void testAll(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        //加入PageHelper的方法，分页。
        //pageNum:第几页，从1开始
        //pageSize:一页中有多少行数据
        PageHelper.startPage(2,3);

        List<Student> students = dao.selectAll();
        PageInfo info = new PageInfo(students);
        System.out.println(info);
        for (Student stu :students
        ) {
            System.out.println("foreach--one==="+stu);
        }
    }

    @Test/*mybatis中的一级缓存*/
    public void testone(){
        //获取session
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        //获取StudentDao的mapper
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        //第一次查询Id为1006的数据
        Student stu1 = dao.selectIdone(1006);
        System.out.println(stu1);
        System.out.println("----------------------分割线-----------------------");

        //第二次查询Id为1006的数据
        Student stu2 = dao.selectIdone(1006);
        System.out.println(stu2);

        sqlSession.close();
    }

    @Test/*mybatis中的二级缓存*/
    public void testtwo(){
        //获取session
        SqlSession sqlSession1 = MyBatisUtils.getSqlSession();
        //获取StudentDao的mapper
        StudentDao dao1 = sqlSession1.getMapper(StudentDao.class);
        //第一次查询Id为1006的数据
        Student stu1 = dao1.selectIdtwo(1);
        System.out.println(stu1);
        sqlSession1.close();

        System.out.println("----------------------分割线-----------------------");

        //获取session
        SqlSession sqlSession2 = MyBatisUtils.getSqlSession();
        //获取StudentDao的mapper
        StudentDao dao2 = sqlSession2.getMapper(StudentDao.class);
        //第二次查询Id为1006的数据
        Student stu2 = dao2.selectIdtwo(1);
        System.out.println(stu2);
        sqlSession2.close();
    }
}
