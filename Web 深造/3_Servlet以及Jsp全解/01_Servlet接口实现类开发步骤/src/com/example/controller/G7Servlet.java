package com.example.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
  问题：
      以Get方式发送中文参数内容""时，得到正常结果：游诗成
      以Post方式发送中文参数内容""时，得到【乱码】???è?????
  问题原因：
          浏览器以Get方式发送请求，请求参数保存在【请求头】，在Http请求协议包到达Http服务器之后，第一件事就是进行解码
          请求头二进制内容由Tomcat负责解码，Tomcat9.0默认使用【utf-8】字符集，可以解释一切国家文字

          浏览器以Post方式发送请求，请求参数保存在【请求体】，在Http请求协议包到达Http服务器之后，第一件事就是进行解码
          请求体二进制内容有当前请求对象(request)负责解码，request默认使用[ISO-8859-1]字符集，一个东欧语系字符集。
          此时如果请求体参数内容是中文，将无法解码只能得到乱码
  解决方案：
          在Post请求方式下，在读取请求体内容之前，应该通知请求对象使用utf-8字符集对请求体内容进行一次重新解码
 */
public class G7Servlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通知请求体对象，使用utf-8字符集对请求体二进制内容进行一次重写解码
        request.setCharacterEncoding("utf-8");
        // 通过请求对象，读取【请求体】参数信息
        String value = request.getParameter("userName");
        System.out.println("从请求体得到参数值：" + value);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 通过请求对象，读取【请求头】参数信息
        String userName = request.getParameter("userName");
        System.out.println("从请求头得到参数值：" + userName);
    }
}
