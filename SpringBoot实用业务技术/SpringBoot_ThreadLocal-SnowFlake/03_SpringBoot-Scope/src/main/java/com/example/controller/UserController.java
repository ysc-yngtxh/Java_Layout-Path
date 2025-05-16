package com.example.controller;

import com.example.pool.ScopeThreadPoolExecutor;
import com.example.utils.AuthScope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/rest")
public class UserController {

	// TODO 通过Filter和Scope实现Request粒度的Scope上下文。
	//  思路是：通过注入一个拦截器，在进入拦截器后开启Scope作为一个请求的上下文，
	//         解析Request对象获取获取相关状态信息(如登陆用户)，并在Scope中设置，在离开拦截器时关闭Scope。
	//  ⚠️：建议在理解该组件之前先去看看text包中的代码示例

	// curl --location --request GET 'localhost:8080/rest/getLoginUser' --header 'Cookie: login_user=zhangsan'
	@GetMapping("/getLoginUser")
	public String getLoginUser() {
		return AuthScope.getLoginUser();
	}

	// curl --location --request GET 'localhost:8080/rest/getLoginUserInThreadPool' --header 'Cookie: login_user=zhangsan'
	@GetMapping("/getLoginUserInThreadPool")
	public String getLoginUserInThreadPool() {
		ScopeThreadPoolExecutor executor = ScopeThreadPoolExecutor.newFixedThreadPool(4);
		executor.execute(() -> {
			log.info("get login user in thread pool: {}", AuthScope.getLoginUser());
		});

		return AuthScope.getLoginUser();
	}
}
