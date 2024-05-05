package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 游家纨绔
 * @RequestMapping:
 *   value:所有请求地址的公共部分，叫做模块名称
 *   位置：放在类的上面
 */
@Controller
@RequestMapping("/test")
public class MyController {

    /**
     * @RequestMapping:请求映射
     *            属性：method，表示请求的方式。它的值RequestMethod类枚举值。
     *                 例如表示get请求方式：RequestMethod.GET
     *                       post请求方式：RequestMethod.POST
     */

    // 这里就可以省略 "/test" ,而在类上面定义模块的名称，比如 "user"... 这样子会更方便调用和管理
    @RequestMapping(value= "/some.do",method= RequestMethod.GET)
    public ModelAndView doSome(HttpServletRequest request, // 参数在方法的形参中
                               HttpServletResponse response,
                               HttpSession session){

        ModelAndView mv = new ModelAndView();

        // 在浏览器页面的地址栏上加入参数 ?name=*** 可以在页面上得到显示
        mv.addObject("msg", "欢迎使用SpringMvc做web开发：" + request.getParameter("name"));
        mv.addObject("fun", "执行的是doSome方法");
        mv.setViewName("show");
        return mv;
    }

    @RequestMapping(value = "/other.do",method = RequestMethod.POST)
    public ModelAndView doOther(HttpServletRequest request,
                                HttpServletResponse response,
                                HttpSession session){
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "欢迎使用SpringMvc做web开发");
        mv.addObject("fun", "执行的是doOther方法");

        mv.setViewName("other");

        return mv;
    }
}
