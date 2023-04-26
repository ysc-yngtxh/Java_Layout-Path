package com.cn.leyou.controller;

import com.cn.leyou.service.UserService;
import com.cn.leyou.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 校验数据
     * 想一想：如果校验数据仅仅是前端去处理会发生什么情况？
     *     如果仅仅是前端去进行校验，可能会拦截数据，避免数据库的泄露。
     *     但是万一前端校验后由于各种网络影响因素，导致数据传到了后端，这个时候如果没有校验数据的服务，很有可能会导致数据库的泄露
     *     后果不堪设想。所以后端也必须要有一个校验数据的功能。
     * @param data
     * @param type
     * @return
     */
    @GetMapping("/chexk/{data}/{type}")
    public ResponseEntity<Boolean> checkData(
            @PathVariable("data") String data, @PathVariable("type") Integer type){
        return ResponseEntity.ok(userService.checkData(data,type));
    }

    @PostMapping("/code")
    public ResponseEntity<Void> sendCode(@PathVariable("phone") String phone){
        userService.sendCode(phone);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 注册
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid User user, BindingResult result, @RequestParam("code") String code){
        //@Valid注解是用于校验数据的

        if(result.hasFieldErrors()){
            throw new RuntimeException(result.getFieldErrors().stream()
                    .map(e -> e.getDefaultMessage()).collect(Collectors.joining("|")));
            //自定义异常
        }

        userService.register(user,code);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 根据用户和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/query")
    public ResponseEntity<User> queryUserByUsername(
            @RequestParam("username") String username,
            @RequestParam("password") String password
    ){
        return ResponseEntity.ok(userService.queryUserByUsername(username,password));
    }


    //有返回内容就用body，没有的话就用builder
}
