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

/**
 * 品牌表(Brand)表控制层
 *
 * @author makejava
 * @since 2023-08-18 23:15:44
 */
@RestController
public class CompletableFutureController {

    @Autowired
    private BrandService brandService;
    @Autowired
    private CoursesService coursesService;

    // TODO 使用 CompletableFuture 的应用场景：比如在一个接口中调用

    // 正常的串行程序执行
    @RequestMapping("/serial")
    public Brand serial(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Brand brand = brandService.getOne(1);
        Courses courses = coursesService.getOne(1);
        brand.setCourses(courses);

        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getLastTaskTimeMillis());

        return brand;
    }

    // 使用 CompletableFuture 异步执行后合并执行结果
    @RequestMapping("/completableFuture")
    public Brand completableFuture() throws ExecutionException, InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CompletableFuture<Brand> cf1 = CompletableFuture.supplyAsync(() -> {
            Brand brand = brandService.getOne(1);
            return brand;
        });
        CompletableFuture<Courses> cf2 = CompletableFuture.supplyAsync(() -> {
            Courses courses = coursesService.getOne(1);
            return courses;
        });
        CompletableFuture<Brand> combine = cf1.thenCombine(cf2, (a, b) -> {
            a.setCourses(b);
            return a;
        });

        stopWatch.stop();
        System.out.println("耗时：" + stopWatch.getLastTaskTimeMillis());

        return combine.get();
    }

}

