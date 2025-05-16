package com.example.controller;

import com.example.pojo.po.User;
import com.example.security.bo.LoginUserDetails;
import com.example.service.impl.LoginServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 * @date 2022-07-03 14:30
 * @apiNote
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

	private final LoginServiceImpl loginService;


	// 这里需要注意的是，如果我在Controller层中不去定义"/"路径，那么当我访问 http://localhost:8080 的时候，
	// spring boot找不到对应路径"/"的资源访问，就会去访问默认欢迎资源文件index.html
	// 但现在我加上了"/"路径，那么一开始的页面就将会是login.html
	@RequestMapping({"/toLoginForm", "/"})
	public String toLoginForm() {
		return "login";
	}


	@RequestMapping(value = "/user/login", method = RequestMethod.POST)
	public @ResponseBody void login(@RequestBody User user) {
		// 这里只是提供一个表单提交接口，实际并不会执行这个接口的业务逻辑
		System.out.println("提交路径");
	}

	@RequestMapping(value = "/user/index")
	public ModelAndView index() {
		String loginJwt = loginService.login();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("token", loginJwt);
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@RequestMapping(value = "/user/img")
	public ResponseEntity<byte[]> getAvatar() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		byte[] avatar = loginUserDetails.getUser().getAvatar();
		return ResponseEntity.ok()
		                     .contentType(MediaType.IMAGE_JPEG) // 根据实际类型调整
		                     .body(avatar);
	}

	@GetMapping("/level1/{id}")
	public String level1(@PathVariable("id") int id) {
		return "view/level1" + "/" + id;
	}

	@GetMapping("/level2/{id}")
	public String level2(@PathVariable("id") int id) {
		return "view/level2" + "/" + id;
	}

	@GetMapping("/level3/{id}")
	public String level3(@PathVariable("id") int id) {
		return "view/level3" + "/" + id;
	}


	// 注销接口
	@GetMapping("/cancellation")
	public @ResponseBody void cancellation() {
		// 这里只是提供一个表单注销接口，实际并不会执行这个接口的业务逻辑
		System.out.println("注销路径");
	}
}
