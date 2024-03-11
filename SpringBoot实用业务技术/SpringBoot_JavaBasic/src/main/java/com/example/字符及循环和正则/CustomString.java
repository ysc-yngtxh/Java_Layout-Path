package com.example.字符及循环和正则;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/27 23:44
 */
public class CustomString {
    public static final Log log = LogFactory.get(CustomString.class);
    public static void main(String[] args) {

        // TODO 判断字符串是否为空
        String str = null;
        boolean hasText = StringUtils.hasText(str);
        log.info(String.valueOf(hasText));


        // TODO 字符串格式化
        String item = "%s，你会一直喜欢我吗?";
        String format = String.format(Locale.ROOT, item, "叶诗琪");
        log.debug(format);

        System.out.printf("%s，你会一直喜欢我吗?%n", "叶诗琪");
    }
}
