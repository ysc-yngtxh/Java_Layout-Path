package com.example.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 */
@Slf4j
@Component
public class LoginFilter extends ZuulFilter {

    // 定义一个令牌桶，每秒产生2个令牌，即每秒最多处理2个请求
    private static final RateLimiter RATE_LIMITER = RateLimiter.create(2);

    // 过滤器类型
    @Override
    public String filterType() {
        // Filter种类：有前置过滤、路由后过滤、后置过滤、异常过滤。
        return FilterConstants.PRE_TYPE;  // 这里选择的是一个前置类型的过滤器
    }

    // 过滤器顺序
    @Override
    public int filterOrder() {
        return 0;
    }

    // 要不要过滤
    @Override
    public boolean shouldFilter() {
        return true;
    }

    // 过滤逻辑
    @Override
    public Object run() {
        // 获取请求上下文
        RequestContext ctx = RequestContext.getCurrentContext();
        // 获取request
        HttpServletRequest request = (HttpServletRequest) ctx.getRequest();
        // 获取请求参数access-token
        String token = request.getParameter("token");
        log.info(token);
        // 判断是否存在
        if (StringUtils.isEmpty(token)) {
            // 不存在，未登录，则拦截
            ctx.setSendZuulResponse(false);
            // 返回403
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            // 把提示信息显示到 页面
            try {
                ctx.getResponse().getWriter().print("token is invalid");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /** 令牌桶限流
             首先我们会有个桶，如果里面没有满那么就会以一定 固定的速率 会往里面放令牌，一个请求过来首先要从桶中获取令牌，
             如果没有获取到，那么这个请求就拒绝，如果获取到那么就放行。*/

        // if (!RATE_LIMITER.tryAcquire()) {
        //     log.warn("访问量超载");
        //     // 指定当前请求未通过过滤
        //     ctx.setSendZuulResponse(false);
        //     // 向客户端返回响应码429，请求数量过多
        //     ctx.setResponseStatusCode(429);
        //     return false;
        // }

        // 返回值目前是没有什么意义的，所以返回null就可以了
        return null;
    }
}
