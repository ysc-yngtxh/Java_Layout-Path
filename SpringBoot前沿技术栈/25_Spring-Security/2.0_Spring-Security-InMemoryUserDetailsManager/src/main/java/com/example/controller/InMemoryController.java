package com.example.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2023-05-17 08:07
 * @apiNote TODO 控制层
 */
@Controller
public class InMemoryController {

    @RequestMapping("/admin")
    public ResponseEntity<String> UserAdmin(){
        return ResponseEntity.ok().body("当前用户拥有admin权限");
    }

    @RequestMapping("/manager")
    public ResponseEntity<String> UserManager(){
        return ResponseEntity.ok().body("当前用户拥有manager权限");
    }
}
