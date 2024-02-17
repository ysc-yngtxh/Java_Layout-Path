package com.example.优化IF.函数式接口加Map;

import org.springframework.stereotype.Service;

@Service
public class GrantTypeService {

    public String redPaper(String resourceType){
        // 红包的发放方式
        return "每周末9点发放";
    }
    public String shopping(String resourceType){
        // 购物券的发放方式
        return "每周三9点发放";
    }
    public String QQVip(String resourceType){
        // qq会员的发放方式
        return "每周一0点开始秒杀";
    }
}