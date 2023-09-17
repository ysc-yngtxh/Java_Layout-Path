package com.example.springbootshirojwt.service;

import com.example.springbootshirojwt.pojo.User;

public interface ShiroJwtService {

    User queryByName(String name);
}
