package com.example.utils;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 游家纨绔
 * @date 2022/07/06
 * @apiNote
 */
public class WebUtil {

    /**
     * 将字符串传渲染到客户端
     */
    public static String renderText(HttpServletResponse response, String string) {
        try {
            response.setStatus(200);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
