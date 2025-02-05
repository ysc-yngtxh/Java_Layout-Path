package com.example.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UseData {

    private Long id;
    private String content;
 
    public UseData(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}