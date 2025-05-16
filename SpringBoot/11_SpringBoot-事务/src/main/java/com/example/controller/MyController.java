package com.example.controller;

import com.example.domain.Student;
import com.example.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	// TODO 使用Setter方式注入
	private MyService myService;

	@Autowired
	public void setMyService(MyService myService) {
		this.myService = myService;
	}

	@Transactional
	@RequestMapping("/student")
	public @ResponseBody Object student(Integer id, String name) {
		Student student = new Student();
		student.setId(id);
		student.setName(name);
		int updateCount = myService.queryStudentById(student);  // 正确的Java语法命令

		// int i = 10/0;   // 错误的语法命令

		return "修改学生编号" + id + "的姓名修改结果" + updateCount;

		/* 按道理来说，一个类中出现了语法上的错误，是无法继续执行下去的。
		 * 用户在浏览器地址栏上输入的地址确实也无法访问，但是却可以把数据改写到数据库中
		 * 也就是说，这个错误只能影响到SpringBoot一部分功能，但是另一方面的功能不受影响。(一半成功一半失败)
		 * 这个时候，我们再来想一下，什么叫事务：把一组命令放在一起执行，保证操作的原子性，要么同时成功，要么同时失败。
		 *
		 * 这个时候我们只需要在方法上加上一个事务注解：@Transactional   完美解决。。。鼓掌！！！
		 */
	}

	@RequestMapping("/students")
	public @ResponseBody Student student(Integer id) {
		Student query = myService.query(id);
		return query;
	}
}
