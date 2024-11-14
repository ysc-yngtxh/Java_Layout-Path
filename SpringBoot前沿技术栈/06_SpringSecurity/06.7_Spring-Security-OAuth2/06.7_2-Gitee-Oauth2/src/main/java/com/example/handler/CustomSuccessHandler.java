// package com.example.handler;
//
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import lombok.SneakyThrows;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
// import org.springframework.stereotype.Component;
//
// @Component
// public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//     @Override
//     public void onAuthenticationSuccess(HttpServletRequest request,
//                                         HttpServletResponse response,
//                                         Authentication authentication) {
//         // 自定义成功处理逻辑
//         try {
//             super.onAuthenticationSuccess(request, response, authentication);
//         } catch (IOException e) {
//             throw new RuntimeException(e);
//         } catch (ServletException e) {
//             throw new RuntimeException(e);
//         }
//     }
// }