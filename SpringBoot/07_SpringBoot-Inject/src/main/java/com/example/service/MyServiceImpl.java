package com.example.service;

import com.example.domain.Student;
import com.example.mapper.StudentMapper;
import org.springframework.stereotype.Service;

@Service
public class MyServiceImpl implements MyService {

	// TODO 使用构造方法注入
	private final StudentMapper studentMapper;

	// TODO 参考Bean生命周期过程：实例化、属性注入、初始化、Bean的使用、Bean的销毁
	//  在我们Bean实例化过程中：首先会在缓存中查找是否有该Bean的实例，如果有则直接返回，
	//                       如果没有则创建Bean实例（通常通过反射调用无参构造，或者有参构造来创建实例）
	//  而这里 MyServiceImpl 很明显是通过有参构造创建Bean实例的。因此，我们是需要 stuMapper 实例注入用以配合的。
	//  所以SpringBoot会先去容器中查找是否有该 stuMapper 的实例，如果没有则创建该Bean实例。
	//  并在SpringBoot创建好 stuMapper 的代理对象后，返回其代理实例交给 MyServiceImpl ，用于有参构造创建实例。
	public MyServiceImpl(StudentMapper stuMapper) {
		this.studentMapper = stuMapper;
	}

	@Override
	public int queryStudentById(Student student) {
		int i = studentMapper.updateByPrimaryKeySelective(student);
		return i;
	}

	public Student query(Integer id) {
		return studentMapper.selectByPrimaryKey(id);
	}
}
