package com.example.service;

import com.example.dto.ResponseResult;
import com.example.entity.SysUser;

public interface LoginService {

    SysUser findByUser(String userName);

    ResponseResult<String> logout();
}
