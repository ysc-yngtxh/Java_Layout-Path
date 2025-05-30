package com.example;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import co.elastic.clients.elasticsearch._types.aggregations.Buckets;
import co.elastic.clients.elasticsearch._types.aggregations.StringTermsBucket;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.example.pojo.Produce;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregation;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchAggregations;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.GeoDistanceOrder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;

@Slf4j
@SpringBootTest
class SpringBootESApplicationTests2 {

	// ElasticsearchTemplate 已弃用，请使用 ElasticsearchOperations
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

	// CriteriaQuery：查询允许创建查询来搜索数据，而不需要了解Elasticsearch查询的语法或基础知识。
	//                它们允许用户通过简单地链接和组合Criteria对象来构建查询，这些对象指定搜索的文档必须满足的条件。
	// StringQuery：此类将Elasticsearch查询作为JSON字符串。
	// NativeQuery：当您具有复杂查询或无法使用Criteria API表示的查询时（例如，在构建查询和使用聚合时）要使用的类。
	//              它允许使用所有不同的co.elastic.clients.elasticsearch_类型.query_dsl。
	//              Elasticsearch库中的查询实现因此被命名为“native”。


	// CriteriaQuery：寻找附近两公里内商品类型是【电子产品】、价格在【1800～3000】的商品品牌地。
	@Test
	public void updateById() {
		// GeoPoint 是用来表示位置坐标的类：九号线星中路地铁站 121.375569, 31.163862
		GeoPoint location = new GeoPoint(31.163862, 121.375569);
		// 按地址由近到远排序，相同小区年龄从大到小排
		Sort sort = Sort
				.by(new GeoDistanceOrder("brandLocation", location))
				.ascending()
				.and(Sort.by("price").descending());

		Query query = new CriteriaQuery(
				// 查询星中路地铁站2公里内的、商品类型是【电子产品】、价格在【1800～3000】的商品品牌地。
				new Criteria("brandLocation").within(location, "2km")
				                             .and(new Criteria("type").is("电子产品"))
				                             .and(new Criteria("price").lessThan(3000).greaterThanEqual(1800))
		);
		// 可选写法
		// Query query1 = CriteriaQuery.builder(
		// 		new Criteria("brandLocation").within(location, "2km")
		//              .and(new Criteria("type").is("电子产品"))
		//              .and(new Criteria("price").lessThan(3000).greaterThanEqual(1800))
		// ).build();
		query.addSort(sort);
		SearchHits<Produce> searchHits = elasticsearchOperations.search(query, Produce.class);
		List<SearchHit<Produce>> searchHitList = searchHits.getSearchHits();

		searchHitList.forEach(searchHit -> {
			                      Produce hero = searchHit.getContent();
			                      log.info("{}\n{}", searchHit.getScore(), JSON.toJSONString(hero, JSONWriter.Feature.PrettyFormat));
		                      }
		                     );
	}


	// StringQuery：寻找上海40-60岁的老男人
	@Test
	public void stringQuery() {
		String dsl = """
				{"bool":{"must":[{"match":{"city":"上海"}},{"match":{"sex":"男"}},{"range":{"age":{"gte":40,"lte":60}}}]}}
				""";
		Query query = new StringQuery(dsl);

		List<SearchHit<Produce>> searchHitList = elasticsearchOperations.search(query, Produce.class).getSearchHits();
		searchHitList.forEach(searchHit ->
				                      System.out.println(JSON.toJSONString(searchHit.getContent(),
				                                                           JSONWriter.Feature.PrettyFormat)
				                                        )
		                     );
	}


	// NativeQuery：统计上海各区商品数量,并显示前3件商品信息
	@Test
	public void nativeQuery() {
		// 按距离和价格排序，选择最近的和最便宜的
		Sort sort = Sort.by(
				                new GeoDistanceOrder("brandLocation", new GeoPoint(31.163862, 121.375569)))
		                .ascending()
		                .and(Sort.by("price").ascending());

		Query query = NativeQuery.builder()
		                         // 定义一个名为byType，按 ‘type’ 字段分组统计的聚合。
		                         .withAggregation("byType", Aggregation.of(a -> a.terms(ta -> ta.field("type").size(100))))
		                         // 只统计商品状态为‘0’的数据
		                         .withQuery(q -> q.match(m -> m.field("status").query("0")))
		                         // 每页3条，显示第一页数据
		                         .withPageable(PageRequest.of(0, 3, sort))
		                         .build();
		SearchHits<Produce> searchHits = elasticsearchOperations.search(query, Produce.class);
		// 获取聚合数据
		ElasticsearchAggregations aggregationsContainer = (ElasticsearchAggregations) searchHits.getAggregations();
		Map<String, ElasticsearchAggregation> aggregations = Objects.requireNonNull(aggregationsContainer).aggregationsAsMap();
		// 获取指名称的聚合
		ElasticsearchAggregation aggregation = aggregations.get("byType");
		Buckets<StringTermsBucket> buckets = aggregation.aggregation().getAggregate().sterms().buckets();
		// 打印聚合信息
		buckets.array().forEach(bucket -> log.info("\n区名:{}\n人数：{}", bucket.key().stringValue(), bucket.docCount()));
		// 打印聚合命中记录信息
		List<SearchHit<Produce>> searchHitList = searchHits.getSearchHits();
		searchHitList.forEach(searchHit -> System.out.println(JSON.toJSONString(searchHit.getContent(), JSONWriter.Feature.PrettyFormat)));
	}

}
