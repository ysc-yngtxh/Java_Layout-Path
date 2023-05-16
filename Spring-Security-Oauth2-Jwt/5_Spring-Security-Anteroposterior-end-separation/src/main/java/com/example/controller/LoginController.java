package com.example.controller;

import com.example.domain.ResponseResult;
import com.example.entity.SysUser;
import com.example.service.LoginService;
import com.example.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游诗成
 * @date 2022/07/03
 * @apiNote
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    private final ResponseResult<SysUser> responseResult;

    @PostMapping("/find/user")
    @ResponseBody
    public ResponseEntity<ResponseResult<SysUser>> findBtUser(@RequestBody User user){
        return ResponseEntity.ok()
                .body(responseResult.success("访问成功",
                        loginService.findByUser(user.getUserName())));
    }


    @PostMapping("/logout")
    public @ResponseBody ResponseResult<String> logout(){
        return loginService.logout();
    }
}
