package com.example.dao;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

    /**
     * 一个简单类型的参数：
     *      简单类型：mybatis把Java的基本数据类型和String都叫简单类型。
     *      在mapper文件获取简单类型的第一个参数的值，使用#{任意字符}
     */
    Student selectStudents(Integer id);

    /**
     * 多个参数：命名参数，在形参定义的前面加入 @Param("自定义参数名称")
     */
    List<Student> selectMultiParam(@Param("myname") String name,
                                   @Param("myage") Integer age);

    /**
     * 多个参数：使用Java对象作为接口中方法的参数
     */
    List<Student> selectMultiObject(Student student);
}
