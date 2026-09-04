package com.example;

import com.example.mapper.FindLevelTwoMapper;
import com.example.pojo.Student;
import com.example.vo.StudentVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.catalina.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class PageHelperApplicationTests {

    @Autowired
    private FindLevelTwoMapper findLevelTwoMapper;


    // TODO PageHelper 分页插件
    //      1. PageHelper.startPage() 方法必须紧跟在你需要分页的 MyBatis 查询方法之前，否则分页不会生效。
    //      2. PageHelper 使用 ThreadLocal 存储分页参数。为了保证线程安全，建议在 finally 代码块中调用 PageHelper.clearPage() 清理上下文，或者使用 Lambda 表达式方式，它会在执行后自动清理。
    @Test
    public void testPageHelper() {
        Page<Student> page = null;
        try {
            // 1. 开启分页，参数为页码和每页大小
            page = PageHelper.startPage(2, 3);
            // 2. 执行查询（紧跟在 startPage 后的第一个查询会被分页）
            List<Student> students = findLevelTwoMapper.selectAll();
            System.out.println(new PageInfo<>(students));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            // 3. 清除分页参数，避免影响后续查询
            if (page != null) {
                page.close(); // 或者 PageHelper.clearPage()，两者效果相同
            }
        }
    }


    // TODO PageHelper 分页插件 Lambda 表达式方式（分页上下文自动清理，无需 finally）
    @Test
    public void testPageHelper2() {
        Page<Student> page = PageHelper.<Student>startPage(2, 3)
                .doSelectPage(() -> findLevelTwoMapper.selectAll());
        System.out.println(new PageInfo<>(page));
    }


    // TODO PageHelper 分页插件 --- 查询到的实体对象（POJO）转换为数据传输对象（DTO）
    @Test
    public void testPageHelper3() {
        try (Page<Student> page = PageHelper.startPage(2, 3)) {
            // 1. 开启分页并查询 POJO 列表
            List<Student> studentPojoList = findLevelTwoMapper.selectAll();

            // 2. 将 POJO 列表转换为 VO 列表
            List<StudentVO> studentVOList = studentPojoList.stream()
                    .map(StudentVO::new)
                    .toList();

            // 3. 用原始的 POJO 列表构建 PageInfo，以保留分页信息
            PageInfo<Student> pageInfo = new PageInfo<>(studentPojoList);

            // 4. 创建新的 PageInfo<StudentVO> 并复制属性
            PageInfo<StudentVO> voPageInfo = new PageInfo<>();
            BeanUtils.copyProperties(pageInfo, voPageInfo, "list");  // 排除 list 属性
            voPageInfo.setList(studentVOList);

            PageInfo<StudentVO> info = new PageInfo<>(voPageInfo.getList());
            System.out.println(info);
        } catch (BeansException e) {
            throw new RuntimeException(e);
        }
    }

}
