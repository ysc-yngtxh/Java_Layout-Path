package com.example.service;

import com.example.domain.ResponseResult;
import com.example.entity.SysUser;

import java.util.Map;

public interface LoginService {

    SysUser findByUser(String userName);

    ResponseResult<String> logout();
}
