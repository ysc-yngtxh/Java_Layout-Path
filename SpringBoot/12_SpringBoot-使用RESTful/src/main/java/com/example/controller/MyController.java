package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController   // 这个注解相当于@Controller和@ResponseBody
public class MyController {
    @RequestMapping(value="/student/detail/{id}/{name}")  // RESTful只是一种风格，不是要求也不是规范。
    public Object query(@PathVariable("id") Integer id,
                        @PathVariable("name") String name){

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("id", id);
        retMap.put("name", name);
        return "retMap";
    }

    @GetMapping(value="/student/detail/{id}/{name}")
    public Object query1(@PathVariable("id") Integer id,
                         @PathVariable("name") String name){

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("id", id);
        retMap.put("name", name);
        return "retMap";
    }

    // 为避免在控制类中出现请求地址一样，但请求数据不一样的时候。浏览器进行访问时，会出现路径迷糊的错误
    // 所以，通常在RESTful风格中方法的请求方法会按增删改查的请求方式来区分

    /* RESTful架构是对MVC架构改进后所形成的一种架构，通过使用事先定义好的接口与不同的服务联系起来。
     * 在RESTful架构中，浏览器使用POST，DELETE，PUT和GET四种请求方式分别对指定的URL资源进行增删改查操作。
     * 因此，RESTful是通过URI实现对资源的管理及访问，具有扩展性强、结构清晰的特点。
     * RESTful架构将服务器分成前端服务器和后端服务器两部分，前端服务器为用户提供无模型的视图；
     * 后端服务器为前端服务器提供接口。浏览器向前端服务器请求视图，通过视图中包含的AJAX函数发起接口请求获取模型。
     * 项目开发引入RESTful架构，利于团队并行开发。在RESTful架构中，将多数HTTP请求转移到前端服务器上，降低服务器的负荷，
     * 使视图获取后端模型失败也能呈现。但RESTful架构却不适用于所有的项目，当项目比较小时无需使用RESTful架构，项目变得更加复杂。*/
}
