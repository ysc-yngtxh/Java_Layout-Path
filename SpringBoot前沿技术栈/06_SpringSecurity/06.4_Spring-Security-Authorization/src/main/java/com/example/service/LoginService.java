package com.example.service;

import com.example.pojo.vo.ResponseResult;

public interface LoginService {
    String login();

    ResponseResult<String> logout();
}
