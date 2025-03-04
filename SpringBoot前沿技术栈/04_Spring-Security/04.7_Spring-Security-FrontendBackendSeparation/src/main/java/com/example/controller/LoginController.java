package com.example.controller;

import com.example.config.MyAuthorizationProperties;
import com.example.pojo.vo.ResponseResult;
import com.example.pojo.entity.SysUser;
import com.example.service.LoginService;
import com.example.pojo.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Value("${security.saveLoginTime}")
    private String time;


    @PostMapping("/find/user")
    @ResponseBody
    public ResponseEntity<ResponseResult<SysUser>> findByUser(@RequestBody(required = false) User user){
        System.out.println("=========" + time);
        MyAuthorizationProperties properties = new MyAuthorizationProperties();
        System.out.println(properties.getTokenExpireTime());
        return ResponseEntity.ok()
                .body(responseResult.success("访问成功",
                        loginService.findByUser(user.getUserName())));
    }


    @PostMapping("/logout")
    public @ResponseBody ResponseResult<String> logout(){
        return loginService.logout();
    }
}
