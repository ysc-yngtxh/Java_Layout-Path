package com.example.filter;

import com.alibaba.fastjson2.JSON;
import com.example.common.Constants;
import com.example.utils.MultiReadHttpServletRequest;
import com.example.utils.MultiReadHttpServletResponse;
import com.example.vo.User;
import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.util.StreamUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author 游家纨绔
 * @dateTime 2023-04-29 16:29
 * @apiNote TODO 自定义过滤器
 */
@Slf4j
@Component
public class CustomerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getContentType() != null && request.getContentType().contains(Constants.REQUEST_CONTEXT_TYPE)) {
            MultiReadHttpServletRequest multiRequest = new MultiReadHttpServletRequest(request);
            MultiReadHttpServletResponse multiResponse = new MultiReadHttpServletResponse(response);
            User user = JSON.parseObject(multiRequest.getBodyString(multiRequest), User.class);
            // 获取到请求体中的参数，进行过滤请求参数逻辑（例如在这里进行参数校验等）
            // ......
            // 又或者在这里就获取token，校验token是否过期
            StopWatch stopWatch = new StopWatch();
            try {
                stopWatch.start();
                logRequestBody(multiRequest);
                filterChain.doFilter(multiRequest, multiResponse);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                stopWatch.stop();
                logResponseBody(multiRequest, multiResponse, stopWatch);
            }
        } else {
            /**
             * 执行到这里：
             *    如果是Get请求，则该请求已经被内部解析过二进制文件流，并将请求参数封装的数据类 Parameters 中
             *    如果是Post请求且非 application/json 类型，则该请求没有被解析过，且没有将数据封装的数据类 Parameters 中
             */
            String str = new String(StreamUtils.copyToByteArray(request.getInputStream()));
            String userName = request.getParameter("userName");
            log.info("请求参数userName={}，请求流\n{}", userName, str);
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 获取请求体中的路径以及参数信息进行日志打印
     * @param multiRequest
     */
    private static void logRequestBody(@NotNull MultiReadHttpServletRequest multiRequest) {
        BufferedReader bf = null;
        StringBuilder sb = new StringBuilder();
        try {
            bf = multiRequest.getReader();
            String str = null;
            while ((str = bf.readLine()) != null) {
                sb.append(str);
            }
            String requestURI = multiRequest.getRequestURI();
            String jsonToJsonStr = sb.toString();
            // 或者 String jsonToJsonStr = multiRequest.getJsonToJsonStr(multiRequest);
            Constants.REQUEST_URL.put(requestURI, requestURI);
            System.out.println("============================请求"+requestURI+"进入过滤器===========================");
            log.info("{} 接收到的参数:{}", requestURI, jsonToJsonStr);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取响应体中的返回参数信息进行打印日志
     * @param multiRequest
     * @param multiResponse
     * @param stopWatch
     */
    private static void logResponseBody(@NotNull MultiReadHttpServletRequest multiRequest, @NotNull MultiReadHttpServletResponse multiResponse, @NotNull StopWatch stopWatch) {
        try {
            long totalTimeMillis = stopWatch.getTotalTimeMillis();
            byte[] body = multiResponse.getBody();
            String str = new String(body, multiResponse.getCharacterEncoding());
            System.out.println("=============================响应体进入过滤器============================");
            log.info("{} 请求总耗时 {} 毫秒，返回参数为 \n{}"
                    , Constants.REQUEST_URL.get(multiRequest.getRequestURI())
                    , totalTimeMillis
                    , str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
