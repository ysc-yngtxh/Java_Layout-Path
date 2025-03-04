package com.example.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-11-13 21:56
 * @apiNote TODO
 */
@Controller
public class UserController {

    @RequestMapping("/user/info")
    public @ResponseBody String index() {
        return "index";
    }
}
