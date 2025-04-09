package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 */
@Controller
public class StudentController {

    @RequestMapping(value="/some.do")
    public ModelAndView doSome(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        System.out.println("===执行MyController中的doSome()方法===");
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName", name);
        mv.addObject("myAge", age);
        mv.setViewName("show");
        return mv;
    }
}
