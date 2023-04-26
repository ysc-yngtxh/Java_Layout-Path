package com.example.springbootshirojwt.service.impl;

import com.example.springbootshirojwt.mapper.ShiroJwtMapper;
import com.example.springbootshirojwt.pojo.User;
import com.example.springbootshirojwt.service.ShiroJwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShiroJwtServiceImpl implements ShiroJwtService {

    @Autowired
    private ShiroJwtMapper shiroJwtMapper;

    @Override
    public User queryByName(String name) {

        return shiroJwtMapper.queryByName(name);
    }
}
