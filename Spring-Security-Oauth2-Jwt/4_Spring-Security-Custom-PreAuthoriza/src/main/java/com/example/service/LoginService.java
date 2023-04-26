package com.example.service;

import com.example.domain.ResponseResult;

import java.util.Map;

public interface LoginService {
    Map<Object, Object> login();

    ResponseResult<Void> logout();
}
