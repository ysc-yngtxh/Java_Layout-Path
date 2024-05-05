package com.example.controller;

import com.example.domain.Student;
import com.example.service.BatchService;
import com.example.service.StudentService;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-15 22:43
 * @apiNote TODO 批处理插入
 */
@Controller
public class BatchController {

    @Autowired
    private BatchService batchService;

    // 不使用Mybatis的批处理方式，并进行分批进行多值插入(多值插入条数控制在100左右)。相当于：1次连接 + (1W/100)次IO操作
    @RequestMapping("/insertForeach") // 1.76s
    public @ResponseBody void insertForeach() {
        List<Student> list = new ArrayList<>();
        for (int i = 1; i < 10000; i++) {
            list.add(Student.builder().name("大灰狼"+i).email(i+"@163.com").age(i).build());
        }
        batchService.insertForeach(list);
    }

    // 使用Mybatis的批处理方式，且分批次的进行批处理。相当于：(1W/1000)次连接 + (1W/1000)次IO操作
    @RequestMapping("/batch") // 313ms
    public @ResponseBody void batch() {
        List<Student> list = new ArrayList<>();
        for (int i = 1; i < 10000; i++) {
            list.add(Student.builder().name("大灰狼"+i).email(i+"@163.com").age(i).build());
        }
        List<List<Student>> partition = Lists.partition(list, 1000);
        // 使用并行流执行插入操作。这一步也可替换为线程池来操作
        partition.parallelStream().forEach(batchService::batchInsert);
    }

    // 使用Mybatis的批处理方式，且分批次的进行批处理，并且还多值插入。相当于：(1W/1000)次连接 + (1W/1000)次IO操作
    @RequestMapping("/batchForeach") // 2.83s
    public @ResponseBody void batchForeach() {
        List<Student> list = new ArrayList<>();
        for (int i = 1; i < 10000; i++) {
            list.add(Student.builder().name("大灰狼"+i).email(i+"@163.com").age(i).build());
        }
        // XML解析foreach 遇到数量大，性能瓶颈。表的列数较多（超过20），建议遍历100个就ok了，不要太高。
        List<List<Student>> partition = Lists.partition(list, 1000);
        partition.parallelStream().forEach(batchService::batchInsertForeach);
    }

    // TODO 总结：
    // 1. 小数据量，使用foreach(多值插入)性能是优于批处理方式。
    // 2. 大数据量，使用批处理方式，不使用foreach进行多值插入，效率更高。
    //    因为 多值插入(多value值) 也是需要在Mybatis中进行解析(参数对应换位符)
    //    IO操作，在Mysql服务器还是要解析其 多值插入 语句。
    //    所以批处理方式，不使用foreach进行多值插入，效率更高。
}
