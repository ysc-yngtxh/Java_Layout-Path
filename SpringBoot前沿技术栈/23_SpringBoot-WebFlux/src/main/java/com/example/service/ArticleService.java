package com.example.service;

import com.example.entity.Article;
import com.example.pojo.ArticleSearchCriteria;
import com.example.pojo.AuthorStatus;
import com.example.repository.ArticleRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 文章服务
 */
@Service
public class ArticleService {

	@Autowired
	private ArticleRepository articleRepository;

	@Autowired
	private ReactiveMongoTemplate mongoTemplate;

	public Flux<Article> findAll() {
		return articleRepository.findAll();
	}

	public Mono<Article> findById(String id) {
		return articleRepository.findById(id);
	}

	public Mono<Article> create(Article article) {
		article.setCreatedAt(LocalDateTime.now());
		article.setUpdatedAt(LocalDateTime.now());
		return articleRepository.save(article);
	}

	/**
	 * 复杂查询
	 */
	public Flux<Article> search(ArticleSearchCriteria criteria) {
		Query query = new Query();

		if (criteria.getKeyword() != null) {
			query.addCriteria(Criteria.where("title")
			                          .regex(criteria.getKeyword(), "i"));
		}

		if (criteria.getAuthor() != null) {
			query.addCriteria(Criteria.where("author").is(criteria.getAuthor()));
		}

		if (criteria.getTags() != null && !criteria.getTags().isEmpty()) {
			query.addCriteria(Criteria.where("tags").all(criteria.getTags()));
		}

		return mongoTemplate.find(query, Article.class);
	}

	/**
	 * 聚合查询
	 */
	public Flux<AuthorStatus> getAuthorStatus() {
		return mongoTemplate.aggregate(
				Aggregation.newAggregation(
						Aggregation.group("author")
						           .count().as("articleCount")
						           .first("author").as("author")
				),
				"articles",
				AuthorStatus.class
		);
	}
}
