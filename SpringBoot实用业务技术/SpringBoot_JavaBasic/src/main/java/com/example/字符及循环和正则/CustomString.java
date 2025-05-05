package com.example.字符及循环和正则;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/27 23:44
 */
public class CustomString {
    public static final Logger log = LoggerFactory.getLogger(CustomString.class);

    static {
        // 默认情况下，SLF4J 的日志级别是 INFO。强制设置日志级别为 DEBUG（适用于 Logback）
        ((ch.qos.logback.classic.Logger) log).setLevel(ch.qos.logback.classic.Level.DEBUG);
    }

    public static void main(String[] args) {
        // TODO 判断字符串是否为空
        String str = null;
        boolean hasText = StringUtils.hasText(str);
        log.info(String.valueOf(hasText));


        // TODO 字符串格式化
        String item = "%s，你会一直喜欢我吗?";
        // join() 用于将多个字符串用指定的分隔符连接起来。
        String joinStr = String.join("会吗？", item, null);
        String format = String.format(Locale.ROOT, joinStr, "叶诗琪");
        log.info(format);

        System.out.printf("%s，你会一直喜欢我吗?%n", "叶诗琪");


        // TODO Guava的字符串工具
        // 创建一个Joiner实例
        Joiner joiner = Joiner.on(", ").skipNulls();
        // 拼接字符串
        String result = joiner.join("Alice", "Bob", null, "David");
        System.out.println(result);  // 输出："Alice, Bob, David"


        // 创建一个Splitter实例
        Splitter splitter = Splitter.on(',').trimResults().omitEmptyStrings();
        // 分割字符串
        Iterable<String> parts = splitter.split("Alice, Bob,  , David");
        for (String part : parts) {
            System.out.println(part);  // 输出："Alice", "Bob", "David"
        }

        String input = "Hello123World456";
        // 使用CharMatcher删除所有数字
        String result1 = CharMatcher.digit().removeFrom(input);
        System.out.println(result1);  // 输出："HelloWorld"
    }
}
