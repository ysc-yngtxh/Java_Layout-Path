package com.example.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.components.SpringContextHolder;
import com.example.mapper.ConsumerDao;
import com.example.entity.TbConsumer;
import com.example.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 * @dateTime 2023-07-11 06:57
 * @apiNote TODO 拦截器
 */
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authorization = request.getHeader("X-Token");
        if (StringUtils.hasText(authorization)) {
            // 获取该用户密码
            String username = JwtUtils.getUsername(authorization);
            ConsumerDao consumerDao = (ConsumerDao) SpringContextHolder.getApplicationContext()
                                                                       .getBean("consumerDao");
            String pwd = consumerDao.selectOne(new LambdaQueryWrapper<TbConsumer>()
                                               .eq(TbConsumer::getUserName, username)).getPassWord();
            return JwtUtils.verifyToken(authorization, pwd);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
