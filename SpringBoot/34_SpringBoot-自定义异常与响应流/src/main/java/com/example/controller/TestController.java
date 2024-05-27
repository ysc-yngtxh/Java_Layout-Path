package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 游家纨绔
 */
@RestController
@RequestMapping("/user")
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> queryUser(@PathVariable("id") Integer id){
        return ResponseEntity.ok(userService.queryUser2(id));
    }

    @GetMapping("/ysc")
    public ResponseEntity<List<User>> queryUser(User user){
        return ResponseEntity.ok(userService.queryUser3(user));
    }

    @PostMapping
    public ResponseEntity<String> saveUser1(User user){
        userService.saveUser1(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("不好意思，添加商品失败！");
    }

    @PostMapping("/Enpty")
    public ResponseEntity<Void> saveUser2(User user){
        userService.saveUser2(user);
        // 因为我们不需要返回值，所以可以不去写body，写上build会更合适
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
