package com.example.controller;

import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-07 21:30
 * @apiNote TODO 流式查询
 */
@Controller
public class CursorController {

    @Autowired
    private StudentService studentService;

    // 流式查询
    @RequestMapping("/streamingQuery1")
    public @ResponseBody void streamingQuery() {
        studentService.streamingQuery1();
    }


    // 启动项目时添加 JVM内存参数 -Xmx50M 来进行模拟。
    // 数据库中有10W条数据，使用游标查询Cursor可以保持正常执行。
    @RequestMapping("/streamingQuery2")
    public @ResponseBody void streamingQuery2() {
        studentService.streamingQuery2();
    }


    // 我们可以通过在启动项目时添加 JVM内存参数 -Xmx50M 来进行模拟。
    // 数据库中有10W条数据，使用这个方法就会出现OOM(内存溢出)。
    // 这种查询数据写入文件，原理还是一次性把所需数据查询出来放入内存，然后写入文件。
    // 因此当数据量足够大的时候，很容易就会出现OOM(内存溢出)。
    @RequestMapping("/ResultHandler")
    public @ResponseBody void ResultHandler() {
        // 调用Mapper方法，开始流式查询
        studentService.selectStudent();
    }
}
