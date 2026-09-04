package com.example;

import com.example.mapper.SelectTagMapper;
import com.example.pojo.Student;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SelectTagMapperTests {

    @Autowired
    private SelectTagMapper selectTagMapper;


    // <if>标签语句的使用
    @Test
    public void testSelectLikeOne() {
        List<Student> Students = selectTagMapper.selectStudentIfTag("李四", 18);
        for (Student stu : Students) {
            System.out.println("if===" + stu);
        }
    }

    // <where>标签配合<if>语句的使用
    @Test
    public void testSelectLikeTwo() {
        List<Student> Students = selectTagMapper.selectStudentWhereTag("李四", 18);
        for (Student stu : Students) {
            System.out.println("where===" + stu);
        }
    }

    // <trim>标签的使用，替代<where>、<set>
    @Test
    public void selectStudentTrim() {
        List<Student> Students = selectTagMapper.selectStudentTrimTag("李四", 18, null);
        for (Student stu : Students) {
            System.out.println("trim===" + stu);
        }
    }

    // <choose>标签的使用
    @Test
    public void selectStudentChoose() {
        List<Student> Students = selectTagMapper.selectStudentChooseTag("李四", 18, null);
        for (Student stu : Students) {
            System.out.println("choose===" + stu);
        }
    }

    // <foreach>标签 用法1
    @Test
    public void testForEachOne() {
        List<Student> Students = selectTagMapper.selectForListForeachTag(List.of(11, 12, 13));
        for (Student stu : Students) {
            System.out.println("foreach--one===" + stu);
        }
    }

    // <foreach>标签 用法2
    @Test
    public void testForEachTwo() {
        List<Student> Students = selectTagMapper.selectForArrayForeachTag(new Integer[]{11, 2});
        for (Student stu : Students) {
            System.out.println("foreach--two===" + stu);
        }
    }

    // <foreach>标签 用法3
    @Test
    public void testForEachThree() {
        List<Student> stuList = List.of(
                Student.builder().id(11).build(),
                Student.builder().id(15).build()
        );

        List<Student> Students = selectTagMapper.selectForObjectForeachTag(stuList);
        for (Student stu : Students) {
            System.out.println("foreach--three===" + stu);
        }
    }

}
