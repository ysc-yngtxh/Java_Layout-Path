package com.example.utils;

import com.example.config.WxLocaleConfigs;
import com.example.config.AppI18nConfig;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author 游家纨绔
 * @version 1.0
 * @date 2023/3/9 23:19
 */
@Slf4j
public class I18nUtil{
    private static final Logger logger = LoggerFactory.getLogger(I18nUtil.class);
    private static Properties prop = null;

    /**
     * 第一种实现方式
     * 使用 application.properties 文件配置国际化语言
     */
    public static Properties loadI18nProp() {
        if (prop != null) {
            return prop;
        }
        try {
            // build I18nUtil prop
            String i18n = AppI18nConfig.getAppConfig().getI18n();
            // MessageFormat的format函数能够先分析消息的指定位置，并直接在这些位置插入相应的值。
            // 这种方式比采用正则表达式查找占位符的方法（如String.format）更高效，性能更优秀。
            String i18nFile = MessageFormat.format("i18n/messages_{0}.properties", i18n);
            // String format = String.format("i18n/messages_%s.properties", i18n);

            // load prop
            Resource resource = new ClassPathResource(i18nFile);
            EncodedResource encodedResource = new EncodedResource(resource, StandardCharsets.UTF_8);
            prop = PropertiesLoaderUtils.loadProperties(encodedResource);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return prop;
    }
    public static String getResourceLanguageString(String code) {
        return loadI18nProp().getProperty(code);
    }

    public static String getResourceLanguageArgsString(String code, Object... args) {
        String property = loadI18nProp().getProperty(code);
        return MessageFormat.format(property, args);
    }

    public static String getResourceLanguageMultString(String... keys) {
        Map<String, String> map = new HashMap<>();

        Properties prop = loadI18nProp();
        if (keys != null && keys.length > 0) {
            for (String key : keys) {
                map.put(key, prop.getProperty(key));
            }
        } else {
            for (String key: prop.stringPropertyNames()) {
                map.put(key, prop.getProperty(key));
            }
        }
        return map.values().stream().collect(Collectors.joining(","));
    }


    /** 第二种实现方式
     *  获取 国际化后内容信息
     *
     * @param code 国际化key
     * @return 国际化后内容信息
     */
    public static String getMessage(String code, Object... args) {
        Locale locale = WxLocaleConfigs.getWxLocaleConfigs().getLocal();
        return getMessage(code, args, locale);
    }

    /**
     * 获取指定语言中的国际化信息，如果没有则走英文
     *
     * @param code 国际化 key
     * @param lang 语言参数
     * @return 国际化后内容信息
     */
    public static String getMessage(String code, String lang) {
        Locale locale;
        if (!StringUtils.hasText(lang)) {
            locale = Locale.US;
        } else {
            try {
                var split = lang.split("_");
                locale = new Locale(split[0], split[1]);
            } catch (Exception e) {
                locale = Locale.US;
            }
        }
        return getMessage(code, null, locale);
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.toString());
        messageSource.setBasename(AppI18nConfig.getAppConfig().getBaseName());
        String content;
        try {
            content = messageSource.getMessage(code, args, locale);
        } catch (Exception e) {
            log.error("国际化参数获取失败 ===> {},{}", e.getMessage(), e);
            content = code;
        }
        return content;
    }
}
