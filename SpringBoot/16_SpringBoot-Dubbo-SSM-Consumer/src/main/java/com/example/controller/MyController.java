package com.example.controller;

import com.example.domain.Student;
import com.example.service.StudentService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

	// @DubboReference 是dubbo的注解，和@Autowired 和@Resource 一样都是表示注入。
	// 只不过注入的是分布式远程服务的对象，用于标记这个服务具体使用了提供者的哪个接口实现，需要dubbo配置使用。
	//
	// 参数名	         类型	  默认值	                      说明
	// interfaceClass	Class	 字段类型	       服务接口类（通常可省略，自动推断）
	// version	        String	   ""	              服务版本号（用于多版本共存）
	// group	        String	   ""	           服务分组（用于区分不同环境/逻辑分组）
	// check	        boolean	  true	      启动时检查提供者是否存在（设为false可加速启动）
	// timeout	        int	     1000（ms）	            方法调用超时时间
	// retries	        int	       2	           失败重试次数（不包含第一次调用）
	// loadbalance	    String	 "random"	  负载均衡策略（可选：random/roundrobin/leastactive/consistenthash）
	// cluster	        String	"failover"	  集群容错模式（可选：failover/failsafe/failfast/failback/forking）
	// mock	            String	   ""	            服务降级时的 Mock 类全限定名
	// async	        boolean	  false	         是否异步调用（需返回 CompletableFuture）
	@DubboReference(interfaceName = "com.example.service.StudentService",
			mock = "com.example.fallback.BackLogicMock",
			version = "1.0.0", group = "dev", check = false, timeout = 5000)
	private StudentService studentService; // 不启动提供者服务，来模拟服务不可用进行的降级测试


	// TODO 返回值是Jsp视图
	//      ⚠️：使用Jsp文件：1、需要配置视图解析器 2、需要在Pom文件中提供编译Jsp文件的配置
	@RequestMapping("/student/detail/{id}")
	public String studentDetail(@PathVariable("id") Integer id, Model model) {
		Student student = studentService.queryStudentById(id);
		model.addAttribute("student", student);
		return "studentDetail";
	}


	@GetMapping("/student/all/count")
	public @ResponseBody Object allStudentCount() {
		Integer allStudentCount = studentService.queryAllStudentCount();
		return "学生总人数为：" + allStudentCount;
	}
}
