package com.example.controller;

import com.example.pojo.po.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: 用户权限访问接口
 * @date 2022/07/30 20:53
 */
@Controller
public class UserController {

    // TODO 需要注意的是：以下的 PreAuthorize、Secured、PostAuthorize也可以在 Service层 使用，效果是一样的

    @RequestMapping("/message1")
    // 权限注解，即：访问这个接口前需要当前用户有 'message' 权限
    @PreAuthorize("hasAuthority('message')")
    public @ResponseBody String test1() {
        return "Hello World";
    }

    @RequestMapping("/message2")
    // 权限注解，即：访问这个接口前需要当前用户有 'message' 权限，或者有 'ADMIN' 角色
    @PreAuthorize("hasAuthority('message') or hasRole('ADMIN')")
    public @ResponseBody String test2() {
        return "Hello World";
    }

    @RequestMapping("/message3")
    // 只有具有 ADMIN 角色的用户才能访问此方法
    @Secured("ROLE_ADMIN")
    public void adminOnlyMethod() {
        System.out.println("This is an admin-only method.");
    }

    @RequestMapping("/message4")
    // 具有 USER 或 ADMIN 角色的用户可以访问此方法
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void userOrAdminMethod() {
        System.out.println("This method can be accessed by users with USER or ADMIN roles.");
    }

    @RequestMapping("/message5")
    // 确保方法返回值不为 null（避免空指针异常），检查返回对象的 id 字段是否等于当前登录用户的 id（即：只允许用户访问自己的数据）
    @PostAuthorize("returnObject != null and returnObject.id == authentication.principal.user.id")
    public @ResponseBody User getUserById(@Param("id") String id) {
        // 假设这里从数据库中获取用户
        return User.builder().id(Long.parseLong(id)).build();
    }

    @RequestMapping("/spel")
    // TODO 这里使用的是SpringEL表达式，简称spel表达式
    @PreAuthorize("@spel.hasAuthorization()")
    public @ResponseBody String role() {
        return "无丝竹之乱耳，无案牍之劳形;";
    }
}
