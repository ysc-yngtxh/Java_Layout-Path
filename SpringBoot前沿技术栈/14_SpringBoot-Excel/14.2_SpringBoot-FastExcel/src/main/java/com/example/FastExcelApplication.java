package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FastExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastExcelApplication.class, args);
    }

    /**
     * FastExcel 是由原 EasyExcel 作者创建的新项目。这个框架在处理 Excel 文件时的高性能表现，而不仅仅是简单易用。
     * 当前 FastExcel 底层使用 poi 作为基础包，如果您的项目中已经有 poi 相关组件，需要您手动排除 poi 的相关 jar 包。
     *
     * EasyExcel 与 FastExcel 的区别
     *     FastExcel 支持所有 EasyExcel 的功能，但是 FastExcel 的性能更好，更稳定。
     *     FastExcel 与 EasyExcel 的 API 完全一致，可以无缝切换。
     *     FastExcel 会持续的更新，修复 bug，优化性能，增加新功能。
     */
}
