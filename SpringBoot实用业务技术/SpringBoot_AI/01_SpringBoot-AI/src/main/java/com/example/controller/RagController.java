package com.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2026-04-02 22:40
 * @apiNote TODO
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")  // 允许跨域访问
public class RagController {


    // @GetMapping(value = "/rag/ask")
    // public Map<String, Object> rag(@RequestParam(value = "question") String question) {
    //
    // }
}
