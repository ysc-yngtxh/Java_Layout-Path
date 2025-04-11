package com.example.mapper;

import com.example.domain.Student;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface StudentMapper {

    // XML文件的返回值类型为Map（即 column名称 对应Map的key值，column值 对应Map的value值）
    Map<String, Object> selectByIdBackMap(Integer id);


    // XML文件的返回值类型为Map，但有额外的 @MapKey 注解。
    // 返回一个key为name值，value为Map的Map集合；相当于Map<String, Map>
    @MapKey("name")
    Map<String, Student> selectByPrimaryKeyMap(Integer id);


    // XML文件的返回值类型为Student（即 column 与 column值 对应Student的属性及属性值）
    Student selectByIdBackStudent(Integer id);


    // XML文件的返回值类型为Student，但有额外的 @MapKey 注解。
    // 返回一个key为name值，value为Student对象的Map集合；相当于Map<String, Student>
    @MapKey("name")
    Map<String, Student> selectByPrimaryKeyStudent(Integer id);


    /** 区别于 selectByPrimaryKeyMap 查询语句，即xml文件中返回值不同就会导致取value都能取到，
     *  selectByPrimaryKeyMap
     *      查询语句结果的value值无法通过Student实体类get方法获取详细属性值，除非强转成Map类型，通过get属性的方式获取
     *  selectByPrimaryKeyStudent
     *      查询语句结果的value值是可以通过Student实体类get方法获取详细属性值：比如 getName()
     *
     * 简单思考一下就知道：
     *   selectByPrimaryKeyMap 返回类型是Map，所以语句结果的value值只能强转成Map类型，通过get("xxx")方式获取
     *   selectByPrimaryKeyStudent 返回类型是Student，语句结果的value值可以通过Student实体类get方法获取详细属性值
     * 这里的爆红不影响项目启动，只是我们安装的箭头插件提示
     */
}
