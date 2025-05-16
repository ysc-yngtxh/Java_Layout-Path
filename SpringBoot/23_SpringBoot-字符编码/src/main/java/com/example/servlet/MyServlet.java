package com.example.servlet;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 游家纨绔
 */
@WebServlet(urlPatterns = "/servlet")
public class MyServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.getWriter().println("世界你好！Hello World");
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().flush();
		resp.getWriter().close();

		// 还需要在配置文件中进行字符集配置
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}
}
