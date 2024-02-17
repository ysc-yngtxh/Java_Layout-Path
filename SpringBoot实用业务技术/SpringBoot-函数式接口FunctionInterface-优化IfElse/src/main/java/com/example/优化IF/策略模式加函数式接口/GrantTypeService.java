package com.example.优化IF.策略模式加函数式接口;

import org.springframework.stereotype.Service;

@Service
public class GrantTypeService {

    // 红包的发放方式
    public String redPaper(String resourceType){
        return "每周末9点发放";
    }

    // 购物券的发放方式
    public String shopping(String resourceType){
        return "每周三9点发放";
    }

    // qq会员的发放方式
    public String QQVip(String resourceType){
        return "每周一0点开始秒杀";
    }
}