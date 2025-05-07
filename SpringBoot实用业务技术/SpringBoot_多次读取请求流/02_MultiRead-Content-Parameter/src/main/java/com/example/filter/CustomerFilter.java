package com.example.filter;

import io.micrometer.core.instrument.util.IOUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 游家纨绔
 * @dateTime 2023-04-29 16:29
 * @apiNote TODO 自定义过滤器
 */
@Component
public class CustomerFilter extends OncePerRequestFilter {

    public final Logger log = LoggerFactory.getLogger(CustomerFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);
        try {
            // 必须要先读取一遍请求流，才能将参数缓存到 ContentCachingRequestWrapper 类中
            // 或者使用 getInputStream() 方法读取流也是一样的
            String str = requestWrapper.getParameter("userName");
            log.info("========请求过滤器参数值{}========", str);
            String user = new String(requestWrapper.getContentAsByteArray());
            log.info("过滤器缓存参数{}", user);
            filterChain.doFilter(requestWrapper, responseWrapper);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * ContentCachingResponseWrapper通过从响应输出流中读取响应主体来缓存响应主体。因此，流变为空就会导致返回的视图都没有。
             * ContentCachingResponseWrapper.copyBodyToResponse() 应当将响应写回到输出流
             */
            responseWrapper.copyBodyToResponse();
            log.info("========响应数据\n{}\n========", new String(requestWrapper.getContentAsByteArray()));
        }
    }
}
