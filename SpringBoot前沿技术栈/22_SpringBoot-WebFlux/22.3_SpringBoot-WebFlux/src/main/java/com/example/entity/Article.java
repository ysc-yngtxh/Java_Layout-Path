package com.example.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章实体
 */
@Data
@Document(collection = "articles")
public class Article {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private List<String> tags;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
