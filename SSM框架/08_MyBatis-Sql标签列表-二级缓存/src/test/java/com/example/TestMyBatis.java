package com.example;

import com.example.mapper.StudentDao;
import com.example.pojo.Student;
import com.example.utils.MyBatisUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestMyBatis {

    // <if>标签语句的使用
    @Test
    public void testSelectLikeOne() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("李四");
        student.setAge(18);
        List<Student> students = dao.selectStudentIf(student);
        for (Student stu : students) {
            System.out.println("if===" + stu);
        }
        sqlSession.close();
    }

    // <where>标签配合<if>语句的使用
    @Test
    public void testSelectLikeTwo() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("李四");
        student.setAge(18);
        List<Student> students = dao.selectStudentWhere(student);
        for (Student stu : students) {
            System.out.println("where===" + stu);
        }
        sqlSession.close();
    }

    // <trim>标签的使用，替代<where>、<set>
    @Test
    public void selectStudentTrim() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setName("李四");
        student.setAge(18);
        List<Student> students = dao.selectStudentTrim(student);
        for (Student stu : students) {
            System.out.println("trim===" + stu);
        }
        sqlSession.close();
    }

    // <choose>标签的使用
    @Test
    public void selectStudentChoose() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setAge(18);
        List<Student> students = dao.selectStudentChoose(student);
        for (Student stu : students) {
            System.out.println("choose===" + stu);
        }
        sqlSession.close();
    }

    // foreach，利用代码方式遍历数据组装Sql。
    @Test
    public void testFor() {
        List<Integer> list = new ArrayList<>();
        list.add(1001);
        list.add(1002);
        list.add(1003);

        String sql = "select * from student where id in";

        StringBuilder builder = new StringBuilder();

        // 添加开始的
        builder.append("(");
        for (Integer i : list) {
            builder.append(i).append(",");
        }
        builder.deleteCharAt(builder.length()-1);
        // 循环结尾
        builder.append(")");
        sql = sql+builder;
        System.out.println("sql==" + sql);
    }

    // <foreach>标签 用法1
    @Test
    public void testForEachOne() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);

        List<Student> students = dao.selectForEachOne(list);
        for (Student stu : students) {
            System.out.println("foreach--one===" + stu);
        }
    }

    // <foreach>标签 用法2
    @Test
    public void testForEachTwo() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Integer[] s = new Integer[]{11, 2};

        List<Student> students = dao.selectForEachTwo(s);
        for (Student stu : students) {
            System.out.println("foreach--two===" + stu);
        }
    }

    // <foreach>标签 用法3
    @Test
    public void testForEachThree() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        List<Student> stulist = new ArrayList<>();
        Student s = new Student();
        s.setId(11);
        stulist.add(s);

        s = new Student();
        s.setId(15);
        stulist.add(s);

        List<Student> students = dao.selectForEachThree(stulist);
        for (Student stu : students) {
            System.out.println("foreach--three===" + stu);
        }
    }

    // pageHelper的用法
    @Test
    public void testAll() {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 加入PageHelper的方法，分页。
        // pageNum： 第几页，从1开始
        // pageSize：一页中有多少行数据
        PageHelper.startPage(2, 3);

        List<Student> students = dao.selectAll();
        PageInfo<Student> info = new PageInfo<>(students);
        System.err.println("Page" + info);
        for (Student stu : students) {
            System.out.println("foreach--one=== " + stu);
        }
    }

    // mybatis中的一级缓存
    @Test
    public void testOne1() {
        // 获取 SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        // 获取 Mapper 对象
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 第一次查询Id为16的数据
        Student stu1 = dao.selectIdOne(16);
        System.out.println(stu1);
        System.out.println("----------------------分割线-----------------------");

        // 第二次查询Id为16的数据
        Student stu2 = dao.selectIdOne(16);
        System.out.println(stu2);

        sqlSession.close();
    }

    // 手动清除一级缓存
    @Test
    public void testOne2() {
        // 获取 SqlSession
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        // 获取 Mapper 对象
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        // 第一次查询Id为16的数据
        Student stu1 = dao.selectIdOne(16);
        System.out.println(stu1);
        System.out.println("----------------------分割线-----------------------");

        // 手动清除一级缓存
        sqlSession.clearCache();

        // 第二次查询Id为16的数据
        Student stu2 = dao.selectIdOne(16);
        System.out.println(stu2);

        sqlSession.close();
    }

    // 通过 XML文件 开启 Mybatis 中的二级缓存场景【注意这种方式需要注释掉StudentDao类上的注解 @CacheNamespace】
    @Test
    public void testTwo1() {
        // 获取 SqlSession
        SqlSession sqlSession1 = MyBatisUtils.getSqlSession();
        // 获取 Mapper 对象
        StudentDao dao1 = sqlSession1.getMapper(StudentDao.class);
        // 第一次查询Id为1的数据
        Student stu1 = dao1.selectIdTwo(1);
        System.out.println(stu1);
        sqlSession1.close();

        System.out.println("----------------------分割线-----------------------");

        // 获取 SqlSession
        SqlSession sqlSession2 = MyBatisUtils.getSqlSession();
        // 获取 Mapper 对象
        StudentDao dao2 = sqlSession2.getMapper(StudentDao.class);
        // 第二次查询Id为1的数据
        Student stu2 = dao2.selectIdTwo(1);
        System.out.println(stu2);
        sqlSession2.close();
    }

    // 通过 注解方式 开启 Mybatis 中的二级缓存的场景
    @Test
    public void testTwo2() {
        // 获取 SqlSession
        SqlSession sqlSession1 = MyBatisUtils.getSqlSession();
        // 获取 Mapper 对象
        StudentDao dao1 = sqlSession1.getMapper(StudentDao.class);
        // 第一次查询Id为1的数据
        Student stu1 = dao1.selectIdThree(1);
        System.out.println(stu1);
        sqlSession1.close();

        System.out.println("----------------------分割线-----------------------");

        // 获取 SqlSession
        SqlSession sqlSession2 = MyBatisUtils.getSqlSession();
        // 获取StudentDao的mapper
        StudentDao dao2 = sqlSession2.getMapper(StudentDao.class);
        // 第二次查询Id为1的数据
        Student stu2 = dao2.selectIdThree(1);
        System.out.println(stu2);
        sqlSession2.close();
    }
}
