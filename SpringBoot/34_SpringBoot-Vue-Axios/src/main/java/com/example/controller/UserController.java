package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import com.example.utils.JwtUtil;
import com.example.vo.PageVo;
import com.example.vo.ResponseVo;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * (User)表控制层
 *
 * @author 游家纨绔
 * @since 2023-07-09 09:00:00
 */
@RestController
@RequestMapping("/user")
public class UserController {

	/**
	 * 服务对象
	 */
	@Resource
	private UserService userService;

	// 登录
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, Object> map) {
		String username = map.get("username").toString();
		String password = map.get("password").toString();
		boolean check = userService.check(username, password);
		if (check) {
			Map<String, Object> strMap = Map.of("username", username, "password", password);
			String token = JwtUtil.createJwt(strMap);
			return ResponseEntity.ok(ResponseVo.success(200, token, "登录成功"));
		}
		return ResponseEntity.ok(ResponseVo.fail(401, "登录失败"));
	}

	// 查询所有用户
	@GetMapping("/selectPage")
	public ResponseEntity<?> queryPage(
			@RequestParam(required = false, defaultValue = "1") Integer page,
			@RequestParam(required = false, defaultValue = "10") Integer size) {
		List<User> userListPage = userService.queryPage(page, size);
		if (CollectionUtils.isEmpty(userListPage)) {
			return ResponseEntity.ok(ResponseVo.fail(400, "输入页数超过拥有数据分页页数"));
		}
		Integer countAll = userService.countAll();
		PageVo info = PageVo.info(userListPage, countAll);
		return ResponseEntity.ok(ResponseVo.success(200, info, "分页数据返回成功"));
	}

	// 编辑用户
	@PostMapping("/updateUser")
	public ResponseEntity<?> edit(@RequestBody User user) {
		int updatedUser = userService.updateUser(user);
		if (updatedUser > 0) {
			return ResponseEntity.ok(ResponseVo.success(200, null, "编辑数据成功"));
		}
		return ResponseEntity.ok(ResponseVo.fail(400, "用户信息更新失败"));
	}

	// 删除用户
	@DeleteMapping("/deleteUser")
	public ResponseEntity<?> delete(@RequestParam("id") Integer userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok(ResponseVo.success(200, null, "删除数据成功"));
	}

}
