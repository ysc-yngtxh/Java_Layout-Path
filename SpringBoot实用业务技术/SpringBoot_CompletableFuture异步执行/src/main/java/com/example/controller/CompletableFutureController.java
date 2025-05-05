package com.example.controller;

import com.example.entity.Brand;
import com.example.entity.Courses;
import com.example.service.BrandService;
import com.example.service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 游家纨绔
 * @since 2023-08-18 23:15:44
 */
@RestController
public class CompletableFutureController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private CoursesService coursesService;

    // 简单的预加载类接口，用于 SpringBoot 项目启动第一次请求时间比较慢的预加载接口
    @RequestMapping("/load")
    public String load() {
        brandService.getOne(1);
        return "已成功调用加载接口！";
    }


    // TODO 使用 CompletableFuture 的应用场景：
    //   1、在一个接口中调用多个已存在的查询，这个时候可以整合在一起并进行优化查询时间
    //   2、需要分别调用多个RPC接口，无先后顺序关系，数据处理是需要同时依赖多个接口的返回数据

    // 正常的串行程序执行
    @RequestMapping("/serial")
    public Brand serial() throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Brand brand = brandService.getOne(1);
        // 延长查询时间，模拟数据量大或者远程调用耗时的场景
        TimeUnit.MILLISECONDS.sleep(300);
        Courses courses = coursesService.getOne(1);
        // 延长查询时间，模拟数据量大或者远程调用耗时的场景
        TimeUnit.MILLISECONDS.sleep(200);
        brand.setCourses(courses);

        stopWatch.stop();
        System.out.println("正常耗时：" + stopWatch.getTotalTimeMillis());

        return brand;
    }

    // 使用 CompletableFuture 异步执行后合并执行结果
    @RequestMapping("/completableFuture")
    public Brand completableFuture() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CompletableFuture<Brand> cf1 = CompletableFuture.supplyAsync(() -> {
            Brand brand = brandService.getOne(1);
            // 延长查询时间，模拟数据量大或者远程调用耗时的场景
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return brand;
        });
        CompletableFuture<Courses> cf2 = CompletableFuture.supplyAsync(() -> {
            Courses courses = coursesService.getOne(1);
            // 延长查询时间，模拟数据量大或者远程调用耗时的场景
            try {
                TimeUnit.MILLISECONDS.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return courses;
        });
        CompletableFuture<Brand> combine = cf1.thenCombine(cf2, (a, b) -> {
            a.setCourses(b);
            return a;
        });

        Brand brand = combine.get();
        stopWatch.stop();
        System.out.println("CompletableFuture耗时：" + stopWatch.getTotalTimeMillis());

        return brand;
    }
}
