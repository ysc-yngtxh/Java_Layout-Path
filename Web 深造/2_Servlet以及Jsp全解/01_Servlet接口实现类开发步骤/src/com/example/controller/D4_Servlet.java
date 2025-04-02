package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 游家纨绔
 */
public class D4_Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {}

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // 1、通过请求对象，读取【请求行】中【url】信息
        String url = request.getRequestURL().toString();
        // 2、通过请求对象，读取【请求行】中【method】信息
        String method = request.getMethod();
        // 3、通过请求对象，读取【请求行】中uri信息
        /* URI：资源文件精准定位地址，在请求行并没有URI这个属性
         *      实际上URI中截取一个字符串，这个字符串格式“/网站名/资源文件名”
         *      URI用于让Http服务器对被访问的资源文件进行定位
         */
        String uri = request.getRequestURI();
        System.out.println("URI " + uri);   // URI /myWeb/five
        System.out.println("URL " + url);   // URL http://localhost:8080/myWeb/five
        System.out.println("method " + method);  // method GET
    }
}
