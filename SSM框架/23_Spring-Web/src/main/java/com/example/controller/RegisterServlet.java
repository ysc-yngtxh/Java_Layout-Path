package com.example.controller;

import com.example.pojo.Student;
import com.example.service.StudentService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 游家纨绔
 */
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = -2571317285336844645L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 表示将请求包中的数据字符集改换成utf-8的形式，因为使用的post形式，不加这条语句，使用中文会出现乱码
        request.setCharacterEncoding("utf-8");

        String strId = request.getParameter("id");
        String strName = request.getParameter("name");
        String strEmail = request.getParameter("email");
        String strAge = request.getParameter("age");

        /**
           创建spring的容器对象
           ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
           System.out.println("容器对象的信息" + ac);
           使用这种方法，也就是每一次被调用就需要创建一个容器对象，麻烦且占用空间
        */

        WebApplicationContext ac = null;
        /*
          获取ServletContext中的容器对象，创建好的容器对象，拿来就用
          String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
          Object attr = getServletContext().getAttribute(key);
          if(attr != null){
              ac = (WebApplicationContext) attr;
          }
        */

        // 使用框架中的方法，获取容器对象
        ServletContext sc = getServletContext();
        ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        System.out.println("容器对象的信息" + ac);

        // 获取servlet
        StudentService service = ac.getBean("studentService", StudentService.class);
        Student student = new Student();
        student.setId(Integer.parseInt(strId));
        student.setName(strName);
        student.setEmail(strEmail);
        student.setAge(Integer.valueOf(strAge));
        service.addStudent(student);

        // 给一个结果页面
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
