package com.example;

import com.example.entity.Student;
import com.example.entity.Teacher;
import com.example.repository.StudentRepository;
import com.example.repository.TeacherRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

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
		teacherRepository.findTeacherOnJPQL1("张伟").forEach(System.out::println);
	}
	@Test
	void getTeachers1() {
		teacherRepository.findTeacherOnJPQL2(52).forEach(System.out::println);
	}


	@Test
	void teacherFindByName2() {
		teacherRepository.findTeacherOnNative1("张伟").forEach(System.out::println);
	}
	@Test
	void getTeachers2() {
		teacherRepository.findTeacherOnNative2(52, 1).forEach(System.out::println);
	}


	@Test
	void findByEmailAndStatus() {
		teacherRepository.findTeacherUseJPQLSpEL1("wangfang@university.edu.cn", "王芳").forEach(System.out::println);
	}
	@Test
	void getTeachersFromStudentInfo() {
		Student student = Student.builder().age(40).name("游家纨绔").build();
		teacherRepository.findTeacherUseJPQLSpEL2(student).forEach(System.out::println);
	}
	@Test
	void findUsersByMinAge() {
		teacherRepository.findTeacherUseJPQLSpEL3(28).forEach(System.out::println);
	}


	@Test
	void getTeachersAndSort() {
		Sort sort1 = Sort.by(Sort.Direction.ASC, "age");
		teacherRepository.getTeachersAndSort(52, sort1).forEach(System.out::println);
	}
	@Test
	void getTeachersWithCTE() {
		teacherRepository.getTeachersWithCTE(28).forEach(System.out::println);
	}
}
