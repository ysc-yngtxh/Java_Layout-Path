package com.example.controller;

import com.example.entity.User;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-10 22:00
 * @apiNote TODO
 */
@RestController
public class ValidController {

    // TODO 注解 @Valid 只能校验默认分组(即Default.class)，同时 @Valid 不支持分组的功能。
    //      并且，想实现嵌套校验功能，需要在嵌套的属性中添加 @Valid 注解
    @RequestMapping("/valid")
    public List<User> queryByPage(@RequestBody @Valid User user) {
        return Collections.emptyList();
    }
}
