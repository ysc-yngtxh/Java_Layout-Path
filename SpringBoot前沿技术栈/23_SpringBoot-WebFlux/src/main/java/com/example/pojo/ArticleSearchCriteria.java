package com.example.pojo;

import java.util.List;
import lombok.Data;

@Data
public class ArticleSearchCriteria {
    private String keyword;
    private String author;
    private List<String> tags;
}
