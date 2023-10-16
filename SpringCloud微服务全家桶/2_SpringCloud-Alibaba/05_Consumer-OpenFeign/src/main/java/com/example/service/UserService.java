package com.example.service;

import com.example.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-15 00:24
 * @apiNote TODO
 */
@FeignClient(value = "nacos-provider", path = "/user")
public interface UserService {
    User queryById(Integer id);
}
