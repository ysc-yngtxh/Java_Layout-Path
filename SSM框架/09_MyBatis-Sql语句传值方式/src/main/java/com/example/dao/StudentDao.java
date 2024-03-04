package com.example.dao;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 游家纨绔
 */
public interface StudentDao {

    // TODO 当SQL中需要传入多个参数的时候，要将where过滤条件字段对应好传参值，否则可能会出现字段值传递错误的情况。

    /**
     * 一个简单类型的参数：单个参数，mybatis可以直接传入，不会出现字段值不符情况
     */
    Student selectStudents(Integer id);

    /**
     * 多个参数：当多个参数的时候，就需要将传参赋值给指定的字段，避免传值错误。
     *         命名参数，在形参定义的前面加入 @Param("自定义参数名称")
     */
    List<Student> selectMultiParam(@Param("myName") String name,
                                   @Param("myAge") Integer age);

    /**
     * 多个参数：使用Java对象作为接口中方法的参数
     */
    List<Student> selectMultiObject(Student student);
}
