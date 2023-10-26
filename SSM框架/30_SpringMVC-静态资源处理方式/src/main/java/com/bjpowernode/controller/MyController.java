package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 */
@Controller
public class MyController {

    @RequestMapping(value="/user/some.do")
    public ModelAndView doSome(String name, Integer age) {

        System.out.println("doSome name="+name+"  age="+age);
        ModelAndView mv = new ModelAndView();
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        mv.setViewName("show");
        return mv;
    }
}
