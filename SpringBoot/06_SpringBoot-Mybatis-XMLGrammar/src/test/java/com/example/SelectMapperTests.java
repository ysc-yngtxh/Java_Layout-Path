package com.example;

import com.example.mapper.SelectMapper;
import com.example.pojo.Course;
import com.example.pojo.Student;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectMapperTests {

    @Autowired
    private SelectMapper selectMapper;

    // 使用 resultMap 标签，用以 Java类属性 与 DB中的字段映射
	@Test
	public void selectByPrimaryKey() {
		Course course = selectMapper.selectByPrimaryKey(1);
		System.out.println("课程 = " + course);
	}

    // 使用 别名，用以 DB中的字段 映射 Java类属性
	@Test
	public void selectByPrimaryKeyToAliasProperty() {
		Course course = selectMapper.selectByPrimaryKeyToAliasProperty(1);
		System.out.println("课程 = " + course);
	}

    // 使用 Map 作为返回值类型
	@Test
	public void selectByPrimaryKeyToMap() {
		Map<Object, Object> courseMap = selectMapper.selectByPrimaryKeyToMap(1);
		System.out.println("课程 = " + courseMap);
	}

    // Mybatis 的映射 Sql 方法参数为 Map 类型
	@Test
	public void selectByMap() {
		List<Student> Students = selectMapper.selectByMap(
                Map.of("name", "敏敏", "age", 22)
        );
		for (Student stu : Students) {
			System.out.println("学生 = " + stu);
		}
	}

    // Mybatis 的映射 Sql 方法参数为 Java对象
	@Test
	public void selectByObject() {
        Student student = Student.builder().name("敏敏").age(22).build();
        List<Student> Students = selectMapper.selectByObject(student);
		for (Student stu : Students) {
			System.out.println("学生 = " + stu);
		}
	}

	// 单个参数可以直接通过 #{参数名}、#{_parameter} 传递参数值
	// MyBatis 可以直接将单个参数作为 SQL 语句的参数，无需额外的映射。
	@Test
	public void selectStudentsSingleParam() {
		List<Student> Students = selectMapper.selectStudentsSingleParam("敏敏");
		for (Student stu : Students) {
			System.out.println("学生 = " + stu);
		}
	}

	// 多个参数，Mybatis 是没办法自己判断哪个参数值与 Sql占位符 相对应，不主动做额外处理执行查询是会报错的。
	// 问题原因：在 Java 8 之前，方法的参数名在编译后会丢失，字节码中只保留参数的类型信息，而不保留参数的实际名称。
	//         这是因为 Java 编译器默认不会将参数名写入 .class 文件中，主要是为了减少字节码的大小和提高性能。
	//         从 Java 8 开始，Java 引入了 -parameters 编译选项，允许将方法的参数名保留在编译后的字节码中。
	//         这样，通过反射就可以获取到方法的实际参数名，而不是默认的 arg0、arg1 这样的占位符名称。
	// 解决方案：可以在编译时加上 -parameters 参数来保留方法参数名。
	//         方法一、在IDEA中设置：File -> Settings -> Build, Execution, Deployment
	//                                 -> Compiler -> Java Compiler
	//                                 -> Additional command line parameters: -parameters
	//         方法二、在pom.xml中设置：
	//                <build>
	//                    <plugins>
	//                        <plugin>
	//                            <groupId>org.apache.maven.plugins</groupId>
	//                            <artifactId>maven-compiler-plugin</artifactId>
	//                            <configuration>
	//                                <compilerArgs>
	//                                    <arg>-parameters</arg>
	//                                </compilerArgs>
	//                            </configuration>
	//                        </plugin>
	//                    </plugins>
	//                </build>
	@Test
	public void selectStudentsPropertyParam() {
		// Mybatis 中通过反射获取到方法的参数名称：method.getParameters()
		// jdk1.8之前：通过反射获取到的参数名为[arg0、arg1]、[param1、param2]
		// jdk1.8之后：可以在编译时添加 -parameters 参数来保留方法参数名。
		//            这样通过反射获取到的参数名为参数的变量名，即：name、age。。。
		List<Student> Students = selectMapper.selectStudentsPropertyParam("敏敏", 22);
		for (Student stu : Students) {
			System.out.println("学生 = " + stu);
		}
	}

	// 多个参数，通过参数名为 [arg0、arg1]、[param1、pram2] 传递参数值
	// 注意⚠️：当使用了 -parameters 编译选项，Mybatis通过反射获取到的参数名为参数的变量名，即：name、age。。。
	//        因此[arg0、arg1]方式传递参数值失效，但[param1、pram2] 参数值还可使用。
	@Test
	public void selectStudentsReflect() {
		// 这种写法可读性不高，不推荐使用
		List<Student> Students = selectMapper.selectStudentsReflect("敏敏", 22);
		for (Student stu : Students) {
			System.out.println("学生 = " + stu);
		}
	}

	// 多个参数，通过注解 @Param 指定映射
	@Test
	public void selectStudentsAnnotationParam() {
		// 所以，基于上述存在的问题：当查询中存在多个参数值时，需要使用注解 @Param 进行指定映射
		List<Student> Students = selectMapper.selectStudentsAnnotationParam("敏敏", 22);
		for (Student stu : Students) {
			System.out.println("学生 = " + stu);
		}
	}

}
