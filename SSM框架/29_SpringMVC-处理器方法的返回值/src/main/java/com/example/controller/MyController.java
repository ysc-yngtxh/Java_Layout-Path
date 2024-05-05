package com.example.controller;

import com.example.vo.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 */
@Controller
public class  MyController {

    /**
     * 处理器方法返回String--表示逻辑视图名称，需要配置视图解析器
     */
    @RequestMapping(value="/returnString-view.do", method= RequestMethod.POST)
    public String doReturnView(HttpServletRequest request, String name, Integer age){

        System.out.println("doReturnView,name=" + name + "  age=" + age);
        request.setAttribute("myname",name);
        request.setAttribute("myage",age);
        // show：逻辑视图名称，项目中配置了视图解析器
        // 框架对视图执行forward转发操作
        return "show";
    }


    // 这是正常的Ajax请求流程
    @RequestMapping(value="/returnVoid-ajax.do")
    public void doReturnVoidAjax(HttpServletResponse response, String name, Integer age) throws IOException {
        System.out.println("doReturnView,name=" + name + "  age=" + age);
        // 处理Ajax，使用json做数据的格式
        // service调用完成了，使用Student表示处理结果
        Student student = new Student();
        student.setName(name);
        student.setAge(age);

        String json = "";
        // 把结果的对象转为json格式的数据
        if(student != null){
            ObjectMapper om = new ObjectMapper();
            json = om.writeValueAsString(student);
            System.out.println("student转换的json===" + json);
        }

        // 输出数据，响应Ajax的请求
        response.setContentType("application/json;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.println(json);
        pw.flush();
        pw.close();
    }


    /**
     * 处理器方法返回一个Student，通过框架转为json,响应Ajax请求
     * @ResponseBody:
     *       作用：那处理器方法返回对象转为json后，通过HttpServletResponse输出给浏览器
     *       位置：方法的定义上面。和其他的注解没有顺序的关系
     */
    //使用框架后，发送的Ajax请求
    @RequestMapping(value="/returnStudentJson.do")
    @ResponseBody
    public Student doStudentJsonObject(String name, Integer age){
        //调用service，获取请求结果数据，Student对象表示结果数据
        Student student = new Student();
        student.setName("曹玉敏同学");
        student.setAge(20);
        return student;
    }
    /*当使用框架后，就只需要简单的几步就可以完成。比如
             String json = "";
             if(student!=null){
                 ObjectMapper om = new ObjectMapper();
                 json = om.writeValueAsString(student);
                 System.out.println("student转换的json==="+json);
             }
             这几步在springmvc.xml中注册驱动<mvc:annotation-driven/>

             response.setContentType("application/json;charset=utf-8");
             PrintWriter pw = response.getWriter();
             pw.println(json);
             pw.flush();
             pw.close();
             这几步就只需要在方法头前加上@ResponseBody注解
    */


    /**
     * list类型的Ajax
     */
    @RequestMapping(value="/returnList.do")
    @ResponseBody
    public List<Student> doOut(){
        List<Student> list = new ArrayList();
        Student student = new Student();
        student.setName("曹玉敏同学");
        student.setAge(20);
        list.add(student);

        Student students = new Student();
        students.setName("曹玉敏我喜欢你哦");
        students.setAge(22);
        list.add(students);

        return list;
    }

    /**
     * 区分返回值String是数据，还是视图，看有没有@ResponseBody注解
     * 如果有@ResponseBody注解，返回String就是数据，反之就是视图
     *
     * 默认使用"text/plain;charset=ISO-8859-1"作为contextType，导致中文有乱码
     * 解决方案：给RequestMapping增加一个属性 produces,使用这个属性指定新的contextType
     */
    @RequestMapping(value="/returnString1.do")
    @ResponseBody
    public String doFirst1(){
        return "Hello SpringMVC 曹玉敏你个大傻子!";
        // 这里不显示事件句柄是因为返回类型是json，但是默认使用"text/plain;charset=ISO-8859-1"作为contextType，导致无法显示
    }
    @RequestMapping(value="/returnString2.do")
    @ResponseBody
    public String doFirst2(){
        return "Hello SpringMVC 曹玉敏你个大傻子!";
        // 这里显示，但是事件句柄有中文乱码。是因为我在脚本中写上了返回值是文本类型，所以能显示，但是因为字符集不符，中文乱码
    }
    @RequestMapping(value="/returnString3.do", produces = "text/plain;charset=utf-8")
    @ResponseBody
    public String doFirst3(){
        return "Hello SpringMVC 曹玉敏你个大傻子!";
        // 这里我在RequestMapping中加入produces = "text/plain;charset=utf-8"语句，所以事件句柄显示正常
    }

}
