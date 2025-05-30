package com.example.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-18 10:00
 * @apiNote TODO OpenFeign拦截器
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // 添加 Openfeign 请求中的Header属性，例如将获取的Token传递到其他服务
        // header属性中文会乱码，因此需要 URLEncoder 编码，然后再到请求服务中解码才能取到正确的中文数据
        requestTemplate.header(HttpHeaders.AUTHORIZATION, URLEncoder.encode("这里是Token密钥", StandardCharsets.UTF_8));
    }
}
