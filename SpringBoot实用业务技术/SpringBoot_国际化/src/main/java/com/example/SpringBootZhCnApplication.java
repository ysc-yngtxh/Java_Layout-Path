package com.example;

import com.example.utils.I18nUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class SpringBootZhCnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootZhCnApplication.class, args);
        System.out.println(I18nUtil.getResourceLanguageMultString("001", "002", "003"));
        System.out.println(
                I18nUtil.getResourceLanguageArgsString(
                        "001"
                        , I18nUtil.getResourceLanguageString("002")
                        , I18nUtil.getResourceLanguageString("003")
                )
        );
    }

    /**
     * Locale确定了一种专门的语言和区域.通过使用java.util.Locale对象来为那些区域敏感型的对象定制格式化数据以及向用户的展示.
     * Locale影响到用户界面的语言、情形映射、整理(排序)、日期和时间的格式以及货币格式.
     * Locale在很多文化背景和语言敏感型的数据操作上的要求很严格.
     * Locale(String language, String country, String variant)
     *       language：这个是一个必须的参数，初始化需要使用的语言。
     *       比如：zh（中文）、en（英语）、de（德语）等等。
     *
     *       country：国家，指定语言的国家版本。
     *       比如：CN、US、CA等等。
     *
     *       variant：个人把这个理解为类别，比如有四川话，湖南话等等，但都是中文。
     */
}
