package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import java.io.UnsupportedEncodingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author 游家纨绔
 */
@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public User query(@PathVariable("id") Integer id
                    , @RequestHeader(value = "Authorization", required = false) String authorization) throws UnsupportedEncodingException {
        // TimeUnit.SECONDS.sleep(8);
        if (!Objects.isNull(authorization)) {
            String decode = URLDecoder.decode(authorization, String.valueOf(StandardCharsets.UTF_8));
            System.out.println(decode);
        }
        return userService.queryById(id);
    }

    @GetMapping("/time")
    public String numberTime() throws InterruptedException {
        // TimeUnit.SECONDS.sleep(8);
        return "到达目标服务器的时间：" + System.currentTimeMillis();
    }
}
