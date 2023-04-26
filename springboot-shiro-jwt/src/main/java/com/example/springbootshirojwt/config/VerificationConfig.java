package com.example.springbootshirojwt.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 验证码配置类
 *
 * Kaptcha 简介
 * Kaptcha 是一个可高度配置的实用验证码生成工具，可自由配置的选项如：
 *    验证码的字体
 *    验证码字体的大小
 *    验证码字体的字体颜色
 *    验证码内容的范围(数字，字母，中文汉字！)
 *    验证码图片的大小，边框，边框粗细，边框颜色
 *    验证码的干扰线
 *    验证码的样式(鱼眼样式、3D、普通模糊、...)
 *
 * @author zhoubin
 * @since 1.0.0
 */
@Configuration
public class VerificationConfig {

    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        //验证码生成器
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        //配置
        Properties properties = new Properties();
        //是否有边框
        properties.setProperty("kaptcha.border", "yes");
        //设置边框颜色
        properties.setProperty("kaptcha.border.color", "105,179,90");
        //边框粗细度，默认为1
        // properties.setProperty("kaptcha.border.thickness","1");
        //验证码
        properties.setProperty("kaptcha.session.key", "code");
        //验证码文本字符颜色 默认为黑色
        properties.setProperty("kaptcha.textproducer.font.color", "blue");
        //设置字体样式
        properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
        //字体大小，默认40
        properties.setProperty("kaptcha.textproducer.font.size", "30");
        //验证码文本字符内容范围 默认为abced2345678gfynmnpwx
        properties.setProperty("kaptcha.textproducer.char.string", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789");
        //字符长度，默认为5
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字符间距 默认为2
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //验证码图片宽度 默认为200
        properties.setProperty("kaptcha.image.width", "100");
        //验证码图片高度 默认为40
        properties.setProperty("kaptcha.image.height", "40");

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
