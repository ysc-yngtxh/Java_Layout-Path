package com.example.controller;

import com.example.entity.TbUser;
import com.example.service.TbUserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户表(TbUser)表控制层
 *
 * @author 游家纨绔
 * @since 2024-08-03 16:25:15
 */
@RestController
public class TbUserController {

    @Resource
    private TbUserService tbUserService;

    /**
     * {
     *    "id": 0,
     *    "username": "Cai Anqi",
     *    "password": "6fAhNBFNAZ6jIVoR1vQwyIjUjNVteJ",
     *    "phone": "18808372185",
     *    "created": "",
     *    "code": 123456
     * }
     */
    @RequestMapping("/tbUser")
    public List<TbUser> queryByPage(@RequestBody @Validated TbUser tbUser) {
        return Collections.emptyList();
    }

}

