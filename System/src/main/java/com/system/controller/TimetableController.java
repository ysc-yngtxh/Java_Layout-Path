package com.system.controller;

import com.system.pojo.Timetable;
import com.system.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/14 14:39
 */
@Controller
public class TimetableController {
    @Autowired
    @Qualifier("timetableServiceImpl")
    private TimetableService service;

    //根据用户id查询课程表
    @RequestMapping( value = "/selectOne")
    @ResponseBody
    public List<Timetable> doSelectOne(int id){
        System.out.println("id:" + id);
        if (id != 0) {
            List<Timetable> timetable = service.findAll(id);
            System.out.println(timetable.toString());
            return timetable;
        }
        return null;
    }

    //将课程表展示至页面
/*    @RequestMapping( "/timeTable")
    public ModelAndView doTimetable(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("show");
        return mv;
    }*/


}
