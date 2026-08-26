package com.example.controller;

import com.example.service.EmbeddingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2026-04-02 07:50
 * @apiNote TODO
 */
@Slf4j
@RestController
@CrossOrigin(origins = "*")  // 允许跨域访问
public class EmbeddingController {

    @Autowired
    private EmbeddingModel embeddingModel;
    // @Autowired
    // private ZhiPuAiEmbeddingModel embeddingModel2;

    @Autowired
    private EmbeddingService embeddingService;

    @GetMapping(value = "/embedding")
    public Map<String, Object> embedding(@RequestParam(value = "message", defaultValue = "给我讲个笑话") String message) {
        float[] embed = embeddingModel.embed(message);
        return Map.of("message", message, "embedding", embed);
    }

    @GetMapping(value = "/similarity")
    public String similarity(@RequestParam(value = "message", defaultValue = "给我讲个笑话") String message) {
        // 调用 service 方法来找出最相似的文本
        return embeddingService.queryBastMatch(message);
    }

}
