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
@FeignClient(value = "nacos-provider", path = "/provider", fallback = UserServiceImpl.class)
// 这个注解启用Feign组件，value值是服务ip，fallback是启动熔断服务，这个时候UserServiceImpl就是熔断后的降级处理
public interface UserService {

    @GetMapping("/{id}")
    User queryById(@PathVariable("id") Integer id);
}
