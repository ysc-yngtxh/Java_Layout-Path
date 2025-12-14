package com.example.repository;

import com.example.entity.Article;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

/**
 * MongoDB响应式Repository
 */
public interface ArticleRepository extends ReactiveMongoRepository<Article, String> {

    Flux<Article> findByAuthor(String author);

    Flux<Article> findByTagsContaining(String tag);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    Flux<Article> searchByTitle(String keyword);
}
