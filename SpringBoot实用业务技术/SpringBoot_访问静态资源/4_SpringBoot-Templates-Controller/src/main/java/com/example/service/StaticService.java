package com.example.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @dateTime 2024-01-12 17:16
 * @apiNote TODO
 */
@Service
public class StaticService {

    @SneakyThrows
    public void staticResult(HttpServletRequest request, HttpServletResponse response) {
        // 这里请求转发 可以访问静态资源，也可以访问接口路径
        // request.getRequestDispatcher("/login").forward(request, response);
        request.getRequestDispatcher("result.html").forward(request, response);
    }

    @SneakyThrows
    public void redirectResult(HttpServletResponse response) {
        // 这里重定向 可以访问静态资源，也可以访问接口路径
        // response.sendRedirect("/login");
        response.sendRedirect("result.html");
    }
}
