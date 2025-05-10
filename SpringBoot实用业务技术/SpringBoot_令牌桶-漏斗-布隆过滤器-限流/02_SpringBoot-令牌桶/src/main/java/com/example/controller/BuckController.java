package com.example.controller;

import com.example.annotation.BucketAnnotation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-08 17:00
 * @apiNote TODO 控制层
 */
@Controller
public class BuckController {

    @BucketAnnotation
    @RequestMapping(value = "/bucket")
    public ResponseEntity<String> bucket() {
        return ResponseEntity.ok("访问成功");
    }
}
