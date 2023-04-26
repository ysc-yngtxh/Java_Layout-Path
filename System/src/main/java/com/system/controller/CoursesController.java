package com.system.controller;

import com.system.pojo.Courses;
import com.system.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/16 17:26
 */
@Controller
public class CoursesController {
    @Autowired
    @Qualifier("coursesServiceImpl")
    private CoursesService service;

    //已选课程信息
    @RequestMapping( "/coursesDate")
    public ModelAndView doSelectCoursesDate(HttpSession session){
        Integer id = (Integer) session.getAttribute("id");
        System.out.println(id);
        if (id != 0) {
            List<Courses> courses = service.selectUserDate(id);
            System.out.println("courses:"+courses);
            session.setAttribute("courses",courses);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("courseDate");
        return mv;
    }

    //已选课程信息 --> 展示至页面
/*    @RequestMapping( "/findCourses")
    public ModelAndView doDate(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("courseDate");
        return mv;
    }*/

}
