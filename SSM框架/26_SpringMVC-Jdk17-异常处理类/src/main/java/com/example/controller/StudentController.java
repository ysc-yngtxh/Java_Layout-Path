package com.example.controller;

import com.example.exception.custom.AgeException;
import com.example.exception.MyUserException;
import com.example.exception.custom.NameException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 */
@Controller
public class StudentController {

    @RequestMapping(value="/some.do")
    public ModelAndView doSome(String name,Integer age) throws MyUserException {
        ModelAndView mv = new ModelAndView();
        // try{
              // 根据请求参数抛出异常
              if(!"zs".equals(name)){
                  throw new NameException("姓名不正确！！！");
              }
              if(age==null || age>80){
                  throw new AgeException("年龄比较大！！！");
              }
        // } catch(Exception e){
        //    e.printStackTrace();
        // }
        mv.addObject("myname",name);
        mv.addObject("myage",age);
        mv.setViewName("show");
        return mv;
    }
}
