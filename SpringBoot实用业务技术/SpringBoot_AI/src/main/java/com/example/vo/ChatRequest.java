package com.example.vo;

import lombok.Data;

@Data
public class ChatRequest {

    private String message;

    private Double temperature; // 可选，控制生成文本的随机性(0-2)

    private Integer maxTokens;  // 可选，限制生成的最大token数

}
