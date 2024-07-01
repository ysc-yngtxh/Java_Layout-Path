package com.example.pojo;

import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
public class Data {

    private Long id;
    private String content;
 
    public Data(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}