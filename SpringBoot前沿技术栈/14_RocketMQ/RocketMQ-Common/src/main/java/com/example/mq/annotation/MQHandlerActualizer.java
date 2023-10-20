package com.example.mq.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-20 00:00
 * @apiNote TODO 消息处理类注解（根据topic和tag区分不同消息处理类）
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Service
public @interface MQHandlerActualizer {

    /**
     * 消息主题
     */
    String topic() default "";

    /**
     * 消息标签,如果是该主题下所有的标签，使用“*”
     */
    String[] tags() default "*";

    /**
     * 消息处理类备注
     **/
    String remark() default "";
}
