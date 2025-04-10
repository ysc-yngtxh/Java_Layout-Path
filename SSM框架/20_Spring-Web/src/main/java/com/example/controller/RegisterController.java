package com.example.controller;

import com.example.pojo.Student;
import com.example.service.StudentService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * @author 游家纨绔
 */
public class RegisterController extends HttpServlet {
    private static final long serialVersionUID = -2571317285336844645L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 表示将请求包中的数据字符集改换成utf-8的形式，因为使用的post形式，不加这条语句，使用中文会出现乱码
        request.setCharacterEncoding("utf-8");

        String strId = request.getParameter("id");
        String strName = request.getParameter("name");
        String strEmail = request.getParameter("email");
        String strAge = request.getParameter("age");

        WebApplicationContext ac = null;

        /** 1、创建Spring的容器对象（这种方法，每一次被调用就需要创建一个容器对象，麻烦且占用空间）
         *    ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
         *    System.out.println("容器对象的信息" + ac);
         *
         *  2、获取ServletContext中的容器对象，创建好的容器对象，拿来就用
         *    String key = WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE;
         *    Object attr = getServletContext().getAttribute(key);
         *    if(attr != null) {
         *        ac = (WebApplicationContext) attr;
         *    }
         *    System.out.println("容器对象的信息" + ac);
         */

        // 使用框架中的方法，获取容器对象
        ServletContext sc = getServletContext();
        ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc);
        System.out.println("容器对象的信息" + ac);

        // 获取servlet
        StudentService service = ac.getBean("studentService", StudentService.class);
        Student Student = new Student();
        // Student.setId(Integer.parseInt(strId));
        Student.setName(strName);
        Student.setEmail(strEmail);
        Student.setAge(Integer.valueOf(strAge));
        service.addStudent(Student);

        // 给一个结果页面（请求转发）
        request.getRequestDispatcher("/result.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {}
}
