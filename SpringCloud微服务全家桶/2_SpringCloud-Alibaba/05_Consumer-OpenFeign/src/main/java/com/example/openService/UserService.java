package com.example.openService;

import com.example.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-15 00:24
 * @apiNote TODO
 */
@FeignClient(value = "nacos-provider", path = "/provider")
public interface UserService {

    @GetMapping("/{id}")
    User queryById(@PathVariable("id") Integer id);
}
