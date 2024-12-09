package com.example.service;

import com.example.pojo.vo.ResponseResult;
import com.example.pojo.entity.SysUser;

public interface LoginService {

    SysUser findByUser(String userName);

    ResponseResult<String> logout();
}
