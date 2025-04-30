package com.example.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * 自定义本地解析器
 *  默认使用的环境解析器是 AcceptHeaderLocaleResolver
 *  自定义语言环境解析器，然后在引入到WebMvc配置类中，来替代原有的语言环境解析器
 */
@Slf4j
@Configuration
public class WxLocaleConfigs implements LocaleResolver,InitializingBean {

    @Getter
    private static WxLocaleConfigs wxLocaleConfigs;

    @Autowired
    private HttpServletRequest request;

    /**
     * 从HttpServletRequest中获取Locale
     * @param httpServletRequest    httpServletRequest
     * @return                      语言Local
     */
    @Override
    public @NonNull Locale resolveLocale(HttpServletRequest httpServletRequest) {
        // 获取请求中的请求头Header参数
        // 这里有坑,不能使用getParameter()方法,因为LocaleResolver只实现了五种场景解析,可以看源码
        // 其中不包括参数解析,所以不要把请求语言放入参数中
        String language = httpServletRequest.getHeader("X-Language");
        // 如果没有就使用默认的（根据主机的语言环境生成一个 Locale)
        Locale locale = LocaleContextHolder.getLocale();
        // 如果请求的链接中携带了 国际化的参数
        if (StringUtils.hasText(language)){
            // zh_CN
            String[] s = language.split("_");
            // 国家，地区
            locale = new Locale(s[0], s[1]);
        }
        return locale;
    }

    public Locale getLocal() {
        return resolveLocale(request);
    }

    /**
     * 用于实现Locale的切换。比如SessionLocaleResolver获取Locale的方式是从session中读取，
     * 但如果用户想要切换其展示的样式(由英文切换为中文)，那么这里的setLocale()方法就提供了这样一种可能
     * 显式调用LocaleResolver.setLocale()来修改用户的区域
     * @param request               HttpServletRequest
     * @param httpServletResponse   HttpServletResponse
     * @param locale                locale
     */
    @Override
    public void setLocale(@NonNull HttpServletRequest request, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {}


    /**
     * 除了显式调用LocaleResolver.setLocale()来修改用户的区域之外，
     * 还可以将LocaleChangeInterceptor拦截器应用到处理程序映射中，它会发现当前HTTP请求中出现的特殊参数。
     * 其中的参数名称可以通过拦截器的paramName属性进行自定义。如果这种参数出现在当前请求中，拦截器就会根据参数值来改变用户的区域。
     * 简单来说：请求头中出现了”x-language“参数，那么请求就会改变Locale的信息，即会调用重写的resolveLocale()方法
     */
    @Bean
    public WebMvcConfigurer localeInterceptor() {
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(@NonNull InterceptorRegistry registry) {
                LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
                localeInterceptor.setParamName("X-Language");
                registry.addInterceptor(localeInterceptor).order(-1);
            }
        };
    }

    @Override
    public void afterPropertiesSet() {
        wxLocaleConfigs = this;
    }
}
