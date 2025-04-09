package com.example.controller;

import com.example.pojo.SSMStudent;
import com.example.service.StudentService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author 游家纨绔
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Resource
    private StudentService service;

    // 注册学生
    @RequestMapping(value = "/addStudent.do")
    public ModelAndView addStudent(SSMStudent SSMStudent) {
        String tips = "注册失败";
        // 调用service处理student
        int nums = service.addStudent(SSMStudent);
        if (nums > 0) {
            // 注册成功
            tips= "学生 【" + SSMStudent.getName() + "】注册成功";
        }
        // 添加数据
        ModelAndView mv = new ModelAndView();
        mv.addObject("tips", tips);
        // 指定结果页面
        mv.setViewName("result");
        return mv;
    }

    // 处理查询学生，响应Ajax
    @RequestMapping("/queryStudent.do")
    @ResponseBody
    public List<SSMStudent> queryStudent() {
        // 参数检查，简单的数据处理
        List<SSMStudent> SSMStudents = service.findStudents();
        return SSMStudents;
    }

    /**
     * 处理器方法返回ModelAndView，实现转发forward
     * 语法：setViewName("forward:视图文件完整路径")
     * forward特点：不和视图解析器一同使用，就当项目中没有视图解析器
     */
    @RequestMapping(value="/doForward.do")
    public ModelAndView doSome() {
        // 处理some.do请求了，相当于service调用处理完成了
        ModelAndView mv = new ModelAndView();
        mv.addObject("msg", "欢迎使用springmvc做web开发");
        mv.addObject("fun", "执行的是doSome方法");
        // 显示转发
        // mv.setViewName("show");
        mv.setViewName("forward:/hello.jsp"); // 请求转发
        // 这种写法方式与视图解析器最大的区别就是可以访问不在/WEB-INF/view/包下的资源文件
        return mv;
    }

    /**
     * 处理器方法返回ModelAndView，实现重定向redirect
     *  语法：setViewName("redirect:视图完整路径")
     *  redirect特点：不和视图解析器一起使用，就当项目中没有视图解析器
     *
     * 框架对重定向的操作：
     *  1、框架会把Model中的简单类型的数据，转为string使用，作为hello.jsp的get请求参数使用。
     *    目的是在doRedirect.do 和 hello.jsp两次请求之间传递数据
     *  2、在目标hello.jsp页面可以使用参数集合对象 ${param}获取请求参数值 ${param.myName}
     *  3、重定向不能访问/WEB-INF资源
     */
    @RequestMapping(value="/doRedirect.do")
    public ModelAndView doWithRedirect(@RequestParam("name") String name, @RequestParam("age") Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName", name);
        mv.addObject("myAge", age);
        // 重定向
        mv.setViewName("redirect:/hello.jsp");
        return mv;
    }
}
