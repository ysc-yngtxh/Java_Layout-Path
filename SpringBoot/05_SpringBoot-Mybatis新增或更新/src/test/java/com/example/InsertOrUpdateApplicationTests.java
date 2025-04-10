package com.example;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class InsertOrUpdateApplicationTests {

    @Autowired
    private StudentService studentService;

    @Test
    void contextLoads() {
        studentService.queryStudentByIdCacheL1(1);
    }

    // 使用replace into方式新增或更新
    @Test
    public void replaceInto() {
        Student student = Student.builder()
                .name("黄河以北，我曹最美").email("456@qq.com").age(22)
                .build();
        int insertReplaceInto = studentService.insertReplaceInto(student);
        System.out.println(insertReplaceInto);
    }

    // 使用Ignore，存在就忽略新增
    @Test
    public void ignore() {
        Student student = Student.builder()
                .id(12).name("天大地大，敏敏胡话").email("456@qq.com").age(22)
                .build();
        int insertIgnoreInto = studentService.insertIgnoreInto(student);
        System.out.println(insertIgnoreInto);
    }

    // 使用on duplicate key update方式新增或更新
    @Test
    public void duplicate() {
        Student student = Student.builder()
                .name("终是庄周梦了蝶，你是恩赐亦是劫").email("456@qq.com").age(22)
                .build();
        int insertedDuplicateKeyUpdate = studentService.insertDuplicateKeyUpdate(student);
        System.out.println(insertedDuplicateKeyUpdate);
    }

    // 使用on duplicate key update方式批量新增或更新
    @Test
    public void duplicateBatch() {
        List<Student> list = new ArrayList<Student>() {{
            add(Student.builder()
                    .name("终是霸王别了姬，对着敏敏打飞机").email("567@qq.com").age(22)
                    .build()
            );
            add(Student.builder()
                    .name("终是悟空入了魔，踏破凌霄灭了佛").email("678@qq.com").age(22)
                    .build()
            );
            add(Student.builder()
                    .name("终是紫霞错了情，忘了情也忘了你").email("789@qq.com").age(22)
                    .build()
            );
        }};
        int insertDuplicateKeyUpdateBatch = studentService.insertDuplicateKeyUpdateBatch(list);
        System.out.println(insertDuplicateKeyUpdateBatch);
    }

    // 多条语句合并操作实现的新增或更新
    @Test
    public void insertOrUpdateOneUserInfo() {
        Student student = Student.builder()
                .name("终是妲己祸了国，万里江山诉蹉跎").email("101112@qq.com").age(22)
                .build();
        int insertOrUpdateOneUserInfo = studentService.insertOrUpdateOneUserInfo(student);
        System.out.println(insertOrUpdateOneUserInfo);
    }
}
