package com.example.pojo;

import lombok.Data;

import java.util.List;

@Data
public class ArticleSearchCriteria {
    private String keyword;
    private String author;
    private List<String> tags;
}
