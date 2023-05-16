package com.example.service;

import com.example.dto.ResponseResult;

public interface LoginService {
    String login();

    ResponseResult<String> logout();
}
