package com.example.controller;

import com.example.vo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    /**
     * 逐个接受请求参数：
     *   要求：处理器(控制器)方法的形参数名和请求中参数名必须一致
     *        同名的请求参数赋值给同名的形参
     *
     * 框架接受请求参数
     *   1、使用request对象接收请求参数
     *      String strName = request.getParameter("name");
     *      String strAge = request.getParameter("age");
     *   2、springMvc框架通过 DispatcherServlet 调用MyController的doSome()方法
     *      调用方法时，按名称对应，把接收的参数赋值给形参
     *      doSome(strName,Integer.valueOf(strAge))
     *      框架会提供类型转换的功能，能把String转为int,long,float,double等类型
     */
    @RequestMapping(value= "/doSome.do", method=RequestMethod.GET)
    public ModelAndView doSome(String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName", name);
        mv.addObject("myAge", age);
        mv.setViewName("show");
        return mv;
    }

    /** POST请求方式：用户在输入中文时会出现乱码状况。所以，在servlet里我们会选用这条语句让浏览器使用这条字符编码
     *              request.setCharacterEncoding("utf-8");
     *              但是在框架中，我们选用过滤器的方式进行改变字符编码
     */
    @RequestMapping(value= "/doOther.do", method= RequestMethod.POST)
    public ModelAndView doOther(String name, Integer age) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName", name);
        mv.addObject("myAge", age);
        mv.setViewName("show");
        return mv;
    }

    /** 请求中参数名和处理器方法的形参名不一样
     *  @RequestParam：解决请求中参数名形参名不一样的问题
     *     属性：1、value：例如：用户请求的参数名(index.xml中提交的参数名--rname)与处理器方法的形参名(name)不一样会导致报错400
     *                        所以，这个时候就需要在value值上说明rname
     *          2、require 是一个Boolean。required = false表示该参数可以不传，required在一个请求中默认值是为true。
     *          3、true：表示请求中必须包含此参数。例如：：如果是get请求地址栏上要有请求参数(要不然会报错400)
     *          4、400状态码：畸形的请求语法、无效的请求信息帧或者虚拟的请求路由
     *     位置：在处理器方法的形参定义的前面
     */
    @RequestMapping(value= "/First.do")
    public ModelAndView First(@RequestParam(value="rname", required = false) String name,
                              @RequestParam(value="rage", required = false) Integer age) {

        ModelAndView mv = new ModelAndView();
        mv.addObject("myName", name);
        mv.addObject("myAge", age);
        // show是视图文件的逻辑名称
        mv.setViewName("show");
        return mv;
    }

    /** 对象接收参数：
     *    1、处理器方法形参是Java对象，这个对象的属性名和请求中参数名一样的
     *    2、框架会创建形参的Java对象，给属性赋值，请求中的参数是name，框架会调用setName()
     *    3、对象接收参数方法是没有@RequestParam注解的，因为形参是一个对象，不需要改变参数名
     */
    @RequestMapping("/Out.do")
    public ModelAndView Out(Student MyStudent) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("myName", MyStudent.getName());
        mv.addObject("myAge", MyStudent.getAge());
        mv.addObject("myStudent", MyStudent);
        // show是视图文件的逻辑名称
        mv.setViewName("show");
        return mv;
    }
}
