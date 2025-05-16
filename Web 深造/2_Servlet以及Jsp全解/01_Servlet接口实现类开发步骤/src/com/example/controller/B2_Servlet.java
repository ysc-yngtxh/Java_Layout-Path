package com.example.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/* 1）问题一描述：浏览器接收到数据money是2，不是50
 *    问题原因：
 *           out.writer方法可以将【字符】,【字符串】,【ASCⅡ码】写入到响应体
 *          【ASCⅡ码】：a-----97
 *                     2-----50
 *    问题提解决方案:实际开发过程中，都是通过out.print()方法将真实数据写入响应体中
 *
 * 2）问题二描述：<br>Java<br>MySQL<br>HTML<br>浏览器在接收到响应结果时，
 *              将<br>作为文字内容在窗口展示出来，没有将<br>当作HTML标签命令来执行
 *    问题原因：浏览器在接收到响应包之后，根据【响应头中content-type】属性的值，
 *            并采用对应【编译器】对【响应体中二进制内容】进行编译处理
 *    问题提解决方案：一定要在得到输出流之前，用过响应对象对响应头中content-type属性进行一次重新赋值用于指定浏览器采用正确编译器
 *                  response.setContentType("text/html");
 */
public class B2_Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String result =  "hello world"; // 执行结果
        int money = 50;
        String result1 = "<br>Java<br>MySQL<br>HTML<br>";
        String result2 = "红烧排骨<br>糖醋里脊<br>清水白菜<br>我好想你曹家千金";

        // 设置响应头content-type
        response.setContentType("text/html;charset=utf-8");

        // --------响应对象-----------start
        // 1、通过响应对象，向Tomcat索要输出流
        PrintWriter out = response.getWriter();
        // 2、通过输出流，将执行结果以二进制形式写入到响应体
        out.write(result);
        out.write(money);
        out.print(money);
        out.print(result1);
        out.print(result2);
    }
}
