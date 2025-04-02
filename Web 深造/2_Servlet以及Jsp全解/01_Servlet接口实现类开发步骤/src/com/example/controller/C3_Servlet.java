package com.example.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * idea快捷功能：
 * Run --> Edit Configurations --> Tomcat server --> server --> on "Update" action 和 on frame deactivation都选上Update classes and resources
 * (表示的意思是动态资源和静态资源更新后会立即生效，不用再进行重启Tomcat。直接在浏览器界面进行刷新，可以实现资源的更新)
 */
public class C3_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String result3 = "http://www.baidu.com";  // "http://www.baidu.com?username=abmit"

        // 通过响应对象，将地址赋值给响应头中location属性：[响应头  location="http://www.baidu.com"]
        // 浏览器在接收到响应包之后，如果发现响应头中存在location属性，自动通过地址栏向location指定网站发送请求
        response.sendRedirect(result3); //

    }
}
