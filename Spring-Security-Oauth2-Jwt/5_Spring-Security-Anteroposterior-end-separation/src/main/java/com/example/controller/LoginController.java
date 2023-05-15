package com.example.controller;

import com.example.domain.ResponseResult;
import com.example.entity.SysUser;
import com.example.service.impl.LoginServiceImpl;
import com.example.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游诗成
 * @date 2022/07/03
 * @apiNote
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginServiceImpl loginService;

    // 这里作为登录成功的首页，携带token凭证给前端
    @PostMapping(value = "/toLogin")
    public ResponseEntity<ResponseResult<String>> index(@RequestBody User user) {
        String loginJwt = loginService.login(user);
        ResponseResult<String> result = new ResponseResult<>(200,"登录成功！",loginJwt);
        return ResponseEntity.ok().body(result);
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

    @GetMapping("/logout")
    public @ResponseBody ResponseResult<String> logout(){
        return loginService.logout();
    }
}
