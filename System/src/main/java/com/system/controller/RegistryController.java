package com.system.controller;

import cn.hutool.core.date.DateUtil;
import com.system.pojo.Registry;
import com.system.pojo.Timetable;
import com.system.service.RegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author cym
 * @version 1.0
 * @description: TODO
 * @date 2022/9/7 15:54
 */
@Controller
public class RegistryController {

    @Autowired
    @Qualifier("registryServiceImpl")
    private RegistryService service;

    //登录
//    @PostMapping("/login.do")
    @RequestMapping(value = "/login")
    public ModelAndView doLogin(Registry registry, HttpSession session){

        String login = "no";
        Registry registryOne = service.selectUser(registry.getUsername());
        if(!Objects.equals(registryOne.getTenant(), "0") &&
                registryOne.getPassword().equals(registry.getPassword()) &&
                registryOne.getTenant().equals(registry.getTenant())){
            login = "yes";
        }
        session.setAttribute("login",login);
        session.setAttribute("id",registryOne.getId());
        ModelAndView mv = new ModelAndView();
        mv.addObject("login",login);
        mv.setViewName("frame");
        return mv;
    }

    //注册
    @RequestMapping(value = "/insert",method = RequestMethod.POST)
    @ResponseBody
    public String doInsert(Registry registry) {
        registry.setDeleteFlag((short) 0);
        String dateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        //将String格式的日期转为Date类型
        Date date = DateUtil.parse(dateTime);
        registry.setDateTime(date);
        registry.setUpdateTime(date);
        int num = service.insertUser(registry);
        System.out.println(registry);
        String flag = "no";
        if(num==1){
            flag = "yes";
        }
        return flag;
    }

    //用户基本信息查询
    @RequestMapping( "/selectUserBase")
    @ResponseBody
    public Registry doSelectUserId(int id){
        if (id != 0) {
            Registry registries = service.selectUserId(id);
            return registries;
        }
        return null;
    }

    //用户信息查询 --> 展示至页面
    /*@RequestMapping( "/base")
    public ModelAndView doSelectUserBase(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("userDate");
        return mv;
    }*/

    //用户基本信息查询
    @RequestMapping( "/logOut")
    public ModelAndView doSelectUserId(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("forward:/index.jsp");
        return modelAndView;
    }

}
