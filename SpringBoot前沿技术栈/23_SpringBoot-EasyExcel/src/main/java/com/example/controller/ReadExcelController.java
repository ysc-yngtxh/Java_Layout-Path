package com.example.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import com.example.pojo.read.ReadDemo1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 08:21
 * @apiNote TODO 读取 Excel
 */
@RestController
public class ReadExcelController {
    private final Logger log = LoggerFactory.getLogger(ReadExcelController.class);

    // TODO 默认一行行的读取excel，所以需要创建excel一行一行的回调监听器
    @RequestMapping("/upload")
    public String onUpLoad(MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream()
                , ReadDemo1.class
                , new PageReadListener<ReadDemo1>(dataList -> {
                    for (ReadDemo1 readDemo1Data : dataList) {
                        log.info("读取到一条数据{}", JSON.toJSONString(readDemo1Data));
                    }
                })).sheet().doRead();
        return "上传成功！";
    }
}
