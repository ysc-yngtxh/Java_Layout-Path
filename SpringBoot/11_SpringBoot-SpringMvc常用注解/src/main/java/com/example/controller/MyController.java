package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 */
// @Controller
@RestController   // 这个注解相当于@Controller和@ResponseBody
public class MyController {

    @RequestMapping(value="/student",method={RequestMethod.GET,RequestMethod.POST})
    // @ResponseBody
    public Object query(){
        return "啊。有意思~";
    }

    // @RequestMapping(value="/student1", method=RequestMethod.GET)
    @GetMapping(value="/student1")  // 相当于上面一句话，是查Get请求
    public Object query1(){
        return "这小妮子，以后削她";
    }

    // @RequestMapping(value="/student2", method=RequestMethod.POST)
    @PostMapping(value="/student2")  // 相当于上面一句话，是增Post请求
    public Object query2(){
        return "他妈的，气死我了。削，狠狠的削！";
    }

    /*
      客户端使用GET、POST、PUT、DELETE4个表示操作方式的动词对服务端资源进行操作：
          GET用来获取资源(查),
          POST用来新建资源(增),
          PUT用来更新资源(改),
          DELETE用来删除资源(删);

          访问的HTTP状态码：
              1xx 相关信息
              2xx 操作成功
              3xx 重定向
              4xx 客户端错误
              5xx 服务器错误
     */
}
