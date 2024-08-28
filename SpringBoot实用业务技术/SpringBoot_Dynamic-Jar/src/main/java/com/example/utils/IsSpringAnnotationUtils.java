package com.example.utils;

import java.lang.reflect.Modifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 * @author 游家纨绔
 * @date 2024/02/22 17:54
 * @Desc TODO 判断该类是否有Spring注解的工具类
 **/
public class IsSpringAnnotationUtils {

    // 注册xxljob执行器的操作是仿照的xxljob中的XxlJobSpringExecutor的注册方法。

    private static Logger logger = LoggerFactory.getLogger(IsSpringAnnotationUtils.class);
    /**
     * 判断一个类是否有 Spring 核心注解
     *
     * @param clazz 要检查的类
     * @return true 如果该类上添加了相应的 Spring 注解；否则返回 false
     */
    public static boolean hasSpringAnnotation(Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        // 是否是接口
        if (clazz.isInterface()) {
            return false;
        }
        // 是否是抽象类
        if (Modifier.isAbstract(clazz.getModifiers())) {
            return false;
        }

        try {
            if (clazz.getAnnotation(Component.class) != null ||
                clazz.getAnnotation(Repository.class) != null ||
                clazz.getAnnotation(Service.class) != null ||
                clazz.getAnnotation(Controller.class) != null ||
                clazz.getAnnotation(Configuration.class) != null
               ) {
                return true;
            }
        } catch (Exception e) {
            logger.error("出现异常：{}",e.getMessage());
        }
        return false;
    }
}