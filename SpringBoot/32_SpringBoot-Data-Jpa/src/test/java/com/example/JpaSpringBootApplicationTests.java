package com.example;

import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

@SpringBootTest
class JpaSpringBootApplicationTests {

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;

	@Test
	void studentFindAll() {
		studentRepository.findAll().forEach(System.out::println);
	}

	@Test
	void teacherFindByName1() {
		teacherRepository.findTeacher1("张伟").forEach(System.out::println);
	}

	@Test
	void teacherFindByName2() {
		teacherRepository.findTeacher2("张伟").forEach(System.out::println);
	}

	@Test
	void teacherFindById() {
		Sort sort1 = Sort.by(Sort.Direction.ASC, "age");
		teacherRepository.getTeachers(52, sort1).forEach(System.out::println);
	}

}
