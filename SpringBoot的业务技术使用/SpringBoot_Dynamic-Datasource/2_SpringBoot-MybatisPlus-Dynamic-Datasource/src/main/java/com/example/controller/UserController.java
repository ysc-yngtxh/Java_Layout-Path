package com.example.controller;

import com.example.entity.User;
import com.example.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * (User)表控制层
 * @author 游家纨绔
 * @since 2023-09-02 22:23:03
 */
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequestMapping("/findMasterByIds")
    public List<User> findByMasterIds() {
        return userService.findByMasterIds();
    }

    @RequestMapping("/findSlave_1ByIds")
    public List<User> findSlave_1ByIds() {
        return userService.findSlave_1ByIds();
    }

}