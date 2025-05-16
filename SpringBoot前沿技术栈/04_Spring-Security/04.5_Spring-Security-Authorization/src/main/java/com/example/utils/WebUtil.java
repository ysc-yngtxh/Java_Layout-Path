package com.example.utils;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 游家纨绔
 * @date 2022-07-06 14:30
 * @apiNote
 */
public class WebUtil {

	/**
	 * 将字符串传渲染到客户端
	 */
	public static void renderText(HttpServletResponse response, String string) {
		PrintWriter writer = null;
		try {
			response.setStatus(200);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.print(string);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			assert writer != null;
			writer.flush();
			writer.close();
		}
	}
}
