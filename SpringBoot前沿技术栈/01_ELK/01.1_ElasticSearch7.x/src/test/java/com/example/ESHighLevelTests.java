package com.example;

import com.alibaba.fastjson.JSON;
import com.example.pojo.User;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.core.TimeValue;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.xcontent.XContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class ESHighLevelTests {

	// TODO 从 ElasticSearch7.17 版本开始，ES集成Java的高级客户端：Java High Level REST Client 废弃了
	@Autowired
	private RestHighLevelClient hlClient;  // 废弃的API

	@Test
	void contextLoads() throws IOException {
		// 创建索引名为"ysc"的请求
		CreateIndexRequest request = new CreateIndexRequest("ysc");
		// 设置主分片、副本
		request.settings(Settings.builder().put("number_of_shards", 5).put("number_of_replicas", 1));
		// 客户端执行请求后请求 IndicesClient ,获得响应
		CreateIndexResponse Response = hlClient.indices().create(request, RequestOptions.DEFAULT);

		// 关闭 ES 客户端
		hlClient.close();
	}

	// 测试获取索引，判断其是否存在
	@Test
	void testExistIndex() throws IOException {
		GetIndexRequest request = new GetIndexRequest("ysc");
		boolean exists = hlClient.indices().exists(request, RequestOptions.DEFAULT);
		System.out.println(exists);

		// 关闭 ES 客户端
		hlClient.close();
	}

	// 测试删除索引
	@Test
	void testDeleteIndex() throws IOException {
		DeleteIndexRequest request = new DeleteIndexRequest("ysc");
		AcknowledgedResponse delete = hlClient.indices().delete(request, RequestOptions.DEFAULT);
		System.out.println(delete.isAcknowledged());

		// 关闭 ES 客户端
		hlClient.close();
	}

	// 测试添加文档(插入)
	@Test
	void testAddDocument() throws IOException {
		// 创建对象
		User user = new User();

		// 创建文档请求，获取索引名为"user"的对象（如果没有则会自行创建）
		IndexRequest request = new IndexRequest("user");

		// 规则 PUT /user/_doc/1000
		request.id("1000");
		request.timeout(TimeValue.timeValueSeconds(1)); // timeout用于请求超时设置
		request.timeout("1s");   // timeout用于请求超时设置

       /*
        * ----------设置ES插入后的刷新策略------------
        * RefreshPolicy # IMMEDIATE:
            请求向ElasticSearch提交了数据，立即进行数据刷新，然后再结束请求。
            优点：实时性高、操作延时短。
            缺点：资源消耗高。
          RefreshPolicy # WAIT_UNTIL:
            请求向ElasticSearch提交了数据，等待数据完成刷新，然后再结束请求。
            优点：实时性高、操作延时长。
            缺点：资源消耗低。
          RefreshPolicy # NONE:
            默认策略。
            请求向ElasticSearch提交了数据，不关系数据是否已经完成刷新，直接结束请求。
            优点：操作延时短、资源消耗低。
            缺点：实时性低。
        * */
		request = request.setRefreshPolicy(WriteRequest.RefreshPolicy.IMMEDIATE);

		// 将我们的数据放入请求 json。这里数据user为空，所以索引名为 'user' 的并不会创建其属性properties
		request.source(JSON.toJSONString(user), XContentType.JSON);

        /*
          方法参数为true则表示创建一个索引，如果id重复，索引操作失败
          方法参数为false则表示更新索引
         */
		request.create(true);
		// 这种写法同上 request.create(true) 一个意思
		// request.opType(DocWriteRequest.OpType.CREATE);
		// 客户端发送请求。这里可能会报错：原因在于加入依赖是ElasticSearch7，但客户端是8。导致版本冲突引起的报错
		IndexResponse indexResponse = hlClient.index(request, RequestOptions.DEFAULT);

		System.out.println(indexResponse.toString());
		System.out.println(indexResponse.status());

		// 关闭 ES 客户端
		hlClient.close();
	}


	// 获取文档 判断是否存在 GET /user/_doc/1000
	@Test
	void testIsExists() throws IOException {
		// GetIndexRequest表示获取索引请求，GetRequest表示获取文档请求
		GetRequest getRequest = new GetRequest("user", "1000");
		// 不获取返回的_source的上下文了
		/**
		 * getRequest.fetchSourceContext(new FetchSourceContext(false));
		 * getRequest.storedFields("_none_");
		 */

		GetResponse response = hlClient.get(getRequest, RequestOptions.DEFAULT);
		System.out.println(response.getSourceAsString());

		boolean exists = hlClient.exists(getRequest, RequestOptions.DEFAULT);
		System.out.println(exists);

		// 关闭 ES 客户端
		hlClient.close();
	}

	// 获得文档的信息 GET /user/_doc/10
	@Test
	void testGetDocument() throws IOException {
		GetRequest getRequest = new GetRequest("user", "10");
		GetResponse getResponse = hlClient.get(getRequest, RequestOptions.DEFAULT);
		System.out.println(getResponse.getSourceAsString());  // 打印文档的内容

		// 关闭 ES 客户端
		hlClient.close();
	}

	// 更新文档的信息 PUT /user/_doc/1000
	@Test
	void testPutDocument() throws IOException {
		UpdateRequest updateRequest = new UpdateRequest("user", "1000");
		updateRequest.timeout("1s");  // timeout用于请求超时设置

		User user = new User(1012, "狂神说", "20000", 18, new Date());
		updateRequest.doc(JSON.toJSONString(user), XContentType.JSON);

		UpdateResponse updateResponse = hlClient.update(updateRequest, RequestOptions.DEFAULT);
		System.out.println(updateResponse.status());  // 打印文档的内容

		// 关闭 ES 客户端
		hlClient.close();
	}


	// 删除文档的信息
	@Test
	void testDeleteDocument() throws IOException {
		DeleteRequest deleteRequest = new DeleteRequest("user", "1000");
		deleteRequest.timeout("1s"); // timeout用于请求超时设置

		DeleteResponse delete = hlClient.delete(deleteRequest, RequestOptions.DEFAULT);
		System.out.println(delete.status()); // 打印文档的内容

		// 关闭 ES 客户端
		hlClient.close();
	}


	// 特殊的，真的项目一般都会批量插入数据
	@Test
	void testBulkRequest() throws IOException {
		BulkRequest bulkRequest = new BulkRequest();
		bulkRequest.timeout("10s");  // timeout用于请求超时设置

		ArrayList<User> userList = new ArrayList<>();
		userList.add(new User(999, "游家纨绔", "4520", 3, new Date()));
		userList.add(new User(1000, "邵荣珍", "2454", 3, new Date()));
		userList.add(new User(1001, "陈嘉琪", "45000", 3, new Date()));
		userList.add(new User(1002, "李晶晶", "54000", 3, new Date()));
		userList.add(new User(1003, "张宝丹", "25400", 3, new Date()));
		userList.add(new User(1004, "李安琪", "20521", 3, new Date()));
		userList.add(new User(1005, "邱淑贞", "20000", 3, new Date()));
		userList.add(new User(1006, "王祖贤", "404040", 3, new Date()));
		userList.add(new User(1007, "翁美玲", "4040", 3, new Date()));
		userList.add(new User(1008, "小透明", "75027", 3, new Date()));
		userList.add(new User(1009, "赵丽颖", "457", 24, new Date()));
		userList.add(new User(1010, "游鹏", "075", 24, new Date()));
		userList.add(new User(1011, "宁航", "78", 24, new Date()));

		for (int i = 0; i < userList.size(); i++) {  // 请求体，响应体内容都是JSON格式
			bulkRequest.add(
					new IndexRequest("user")
							.id("" + (i + 1))
							.source(JSON.toJSONString(userList.get(i)), XContentType.JSON)
			               );
		}
		BulkResponse bulk = hlClient.bulk(bulkRequest, RequestOptions.DEFAULT);
		System.out.println(bulk.hasFailures());  // 是否失败，返回false表示成功！

		// 关闭 ES 客户端
		hlClient.close();
	}

	// 查询
	// SearchRequest  搜索请求
	// SearchSourceBuilder 条件构造
	// MatchAllQueryBuilder 匹配所有
	// xxx  QueryBuilder  对应我们刚才
	@Test
	void testSearch() throws IOException {
		// 获取目标文档对象
		SearchRequest request = new SearchRequest("user");
		// 搜索构建器
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 查询条件，我们可以使用QueryBuilder工具来实现
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "邵荣珍");

		sourceBuilder.query(matchQueryBuilder);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS)); // timeout用于请求超时设置

		request.source(sourceBuilder);
		SearchResponse searchResponse = hlClient.search(request, RequestOptions.DEFAULT);

		System.out.println(JSON.toJSONString(searchResponse.getHits())); // 请求体，响应体内容都是JSON格式
		System.out.println("==================");

		for (SearchHit documentFields : searchResponse.getHits().getHits()) {
			System.out.println(documentFields.getSourceAsMap());
		}

		// 关闭 ES 客户端
		hlClient.close();
	}

	// HighlightBuilder 构建高亮
	// TermQueryBuilder  精确查询
	@Test
	void testSearchHigh() throws IOException {
		// 获取目标文档对象
		SearchRequest request = new SearchRequest("user");
		// 搜索构建器
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

		TermQueryBuilder title = QueryBuilders.termQuery("name", "成");
		sourceBuilder.query(title);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

		// 构建高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();  // 高亮构建器
		highlightBuilder.field("name");                        // 高亮的字段
		highlightBuilder.requireFieldMatch(false);    // 是否多个字段都高亮
		highlightBuilder.preTags("<span style='color:red'>");  // 前缀后缀
		highlightBuilder.postTags("</span>");
		sourceBuilder.highlighter(highlightBuilder);

		request.source(sourceBuilder);
		SearchResponse search = hlClient.search(request, RequestOptions.DEFAULT);

		System.out.println(JSON.toJSONString(search.getHits()));

		// 解析结果
		for (SearchHit hit : search.getHits().getHits()) {
			// 解析高亮的字段
			// 获取高亮字段
			Map<String, HighlightField> highlightFields = hit.getHighlightFields();
			System.out.println("==========" + highlightFields);

			HighlightField name = highlightFields.get("name");
			System.out.println("==name==" + name);

			System.out.println(hit.getSourceAsMap());  // 原本的结果

			// 将原来的字段替换为高亮字段即可
			if (name != null) {
				String s = Arrays.toString(name.getFragments());
				System.out.println(s);
			}
		}

		// 关闭 ES 客户端
		hlClient.close();
	}

	// 排序分页
	@Test
	void testSearchOrder() throws IOException {
		// 获取目标文档对象
		SearchRequest searchRequest = new SearchRequest("user");
		// 搜索构建器
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 排序、分页条件
		sourceBuilder.sort("id", SortOrder.ASC);
		sourceBuilder.from(5); // 表示偏移量，不表示第几页。就是总共有13条数据，from是12，那么就会从第13条数据开始
		sourceBuilder.size(7);
		// 将构建器交给目标文档对象
		searchRequest.source(sourceBuilder);
		// 获取到响应消息
		SearchResponse search = hlClient.search(searchRequest, RequestOptions.DEFAULT);

		System.out.println(JSON.toJSONString(search.getHits()));
		System.out.println("==================");

		for (SearchHit documentFields : search.getHits().getHits()) {
			System.out.println(documentFields.getSourceAsMap());
		}

		// 关闭 ES 客户端
		hlClient.close();
	}


	// 汇总搜索内容
	@Test
	void testSummary() throws IOException {
		// 搜索文档请求
		SearchRequest searchRequest = new SearchRequest("user");
		// 搜索构建器
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 模糊查询
		MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "李");
		// 将查询条件交给构建器
		sourceBuilder.query(matchQueryBuilder);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
		// 进行排序
		sourceBuilder.sort("id", SortOrder.ASC);
		// 进行分页
		sourceBuilder.from(0);
		sourceBuilder.size(5);
		// 构建高亮
		HighlightBuilder highlightBuilder = new HighlightBuilder();
		// 构建高亮的字段
		highlightBuilder.field("name");
		// 是否多个字段都高亮
		highlightBuilder.requireFieldMatch(true);
		// 前缀后缀
		highlightBuilder.preTags("<span style='color:red'>");
		highlightBuilder.postTags("</span>");
		// 将构建高亮方式交给构建器
		sourceBuilder.highlighter(highlightBuilder);
		sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
		// 构建器交给文档对象
		searchRequest.source(sourceBuilder);
		// 返回响应
		SearchResponse response = hlClient.search(searchRequest, RequestOptions.DEFAULT);

		// 解析结果
		for (SearchHit searchHit : response.getHits().getHits()) {
			System.out.println(searchHit.getSourceAsMap());
			System.out.println(Arrays.toString(searchHit.getHighlightFields().get("name").getFragments()));
		}

		// 关闭 ES 客户端
		hlClient.close();
	}


	// 聚合查询：度量--最大值
	@Test
	void aggregationMax() throws IOException {
		// SearchRequest request = new SearchRequest("user"); 源码效果一样
		SearchRequest request = new SearchRequest().indices("user");
		// 搜索构建器
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 聚合构建器："maxAge"表示给聚合查询最大值起的名字；"age"表示聚合查询字段
		MaxAggregationBuilder field = AggregationBuilders.max("maxAge").field("age");

		sourceBuilder.aggregation(field);
		// 客户端发送请求，获取响应对象
		SearchResponse response = hlClient.search(request.source(sourceBuilder), RequestOptions.DEFAULT);
		// 打印响应结果
		System.out.println(response);

		// 关闭 ES 客户端
		hlClient.close();
	}


	// 聚合查询：桶--分组统计
	@Test
	void aggregationTerm() throws IOException {
		// SearchRequest request = new SearchRequest("user"); 源码效果一样
		SearchRequest request = new SearchRequest().indices("user");
		// 搜索构建器
		SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
		// 聚合构建器："ageGroupBy"表示给聚合查询分组起的名字
		TermsAggregationBuilder field = AggregationBuilders.terms("ageGroupBy").field("age");

		sourceBuilder.aggregation(field);
		// 客户端发送请求，获取响应对象
		SearchResponse response = hlClient.search(request.source(sourceBuilder), RequestOptions.DEFAULT);
		// 打印响应结果
		SearchHits hits = response.getHits();
		System.out.println(response);

		// 关闭 ES 客户端
		hlClient.close();
	}
}
