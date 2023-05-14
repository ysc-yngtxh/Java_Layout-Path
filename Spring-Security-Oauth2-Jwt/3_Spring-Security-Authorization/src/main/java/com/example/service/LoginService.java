package com.example.service;

import com.example.domain.ResponseResult;

import java.util.Map;

public interface LoginService {
    String login();

    ResponseResult<String> logout();
}
