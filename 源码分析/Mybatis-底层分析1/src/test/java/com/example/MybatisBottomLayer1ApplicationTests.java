package com.example;

import com.example.v1.entity.Student;
import com.example.v1.factory.MapperProxyFactoryV1;
import com.example.v1.mapper.StudentMapperV1;
import com.example.v2.factory.MapperProxyFactoryV2;
import com.example.v2.mapper.StudentMapperV2;
import com.example.v3.factory.MapperProxyFactoryV3;
import com.example.v3.mapper.StudentMapperV3;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MybatisBottomLayer1ApplicationTests {

	// 测试方法，测试功能
	@Test
	public void mybatis1() {
		StudentMapperV1 mapper = MapperProxyFactoryV1.getMapper(StudentMapperV1.class);
		List<Student> studentList = mapper.queryUser("张三", 20);
		System.out.println(studentList);
	}

	@Test
	public void mybatis2() {
		StudentMapperV2 mapper = MapperProxyFactoryV2.getMapper(StudentMapperV2.class);
		System.out.println(mapper.queryUser("赵六", 19));
		System.out.println(mapper.queryUserById(7));
	}

	@Test
	public void mybatis3() {
		StudentMapperV3 mapper = MapperProxyFactoryV3.getMapper(StudentMapperV3.class);
		System.out.println(mapper.queryUser("吴十", 22));
		System.out.println(mapper.queryUserById(7));
	}

}
