package com.example.vo;

import lombok.Data;

@Data
public class ChatResponse {

    private String reply;

    private String model;

    private Long tokensUsed;

}
