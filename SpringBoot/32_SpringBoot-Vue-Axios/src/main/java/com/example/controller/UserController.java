package com.example.controller;

import com.example.entity.User;
import com.example.service.ConsumerService;
import com.example.utils.JwtUtils;
import com.example.vo.PageVo;
import com.example.vo.ResponseVo;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2023-07-09 09:17:24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private ConsumerService consumerService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, Object> map) {
        String username = map.get("username").toString();
        String password = map.get("password").toString();
        boolean check = consumerService.check(username, password);
        if (check) {
            String token = JwtUtils.getJwtsToken(username, password);
            return ResponseEntity.ok(ResponseVo.success(200, token, "登录成功"));
        }
        return ResponseEntity.ok(ResponseVo.fail(401, "登录失败"));
    }

    @GetMapping("/selectPage")
    public ResponseEntity<?> queryPage(@RequestParam Integer page, @RequestParam Integer size) {
        List<User> userListPage = consumerService.queryPage(page, size);
        if (CollectionUtils.isEmpty(userListPage)) {
            return ResponseEntity.ok(ResponseVo.fail(400, "输入页数超过拥有数据分页页数"));
        }
        Integer countAll = consumerService.countAll();
        PageVo info = PageVo.info(userListPage, countAll);
        return ResponseEntity.ok(ResponseVo.success(200, info, "分页数据返回成功"));
    }
}
