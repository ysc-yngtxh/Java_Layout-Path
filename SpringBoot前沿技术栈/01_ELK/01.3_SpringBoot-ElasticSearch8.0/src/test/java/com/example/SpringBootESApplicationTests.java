package com.example;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.example.pojo.Produce;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexInformation;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.index.Settings;

@SpringBootTest
class SpringBootESApplicationTests {

	// ElasticsearchTemplate 已弃用，请使用 ElasticsearchOperations
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;


	/********************************************* 索引操作 ********************************************/

	// 创建索引
	@Test
	void createIndex() {
		IndexOperations indexOperations = elasticsearchOperations.indexOps(Produce.class);
		// 设置索引基本信息
		Map<String, Object> settings = new HashMap<>();
		Map<String, Object> index = new HashMap<>();
		settings.put("index", index);
		index.put("number_of_shards", 3);
		index.put("number_of_replicas", 1);

		Document mapping = indexOperations.createMapping(Produce.class);
		indexOperations.create(settings, mapping);
	}

	// 查看索引信息
	@Test
	void indexInfo() {
		// 查看索引完整信息
		IndexOperations indexOperations = elasticsearchOperations.indexOps(Produce.class);
		List<IndexInformation> informations = indexOperations.getInformation();
		informations.forEach(indexInformation -> System.out.println(JSON.toJSONString(indexInformation, JSONWriter.Feature.PrettyFormat)));

		// 查看索引映射信息
		Map<String, Object> mapping = indexOperations.getMapping();
		System.out.println("------------mapping----------------");
		System.out.println(JSON.toJSONString(mapping, JSONWriter.Feature.PrettyFormat));

		// 查看索引设置信息
		Settings settings = indexOperations.getSettings(true);
		System.out.println("------------settings----------------");
		System.out.println(JSON.toJSONString(settings, JSONWriter.Feature.PrettyFormat));
	}

	// 删除索引
	@Test
	void deleteIndex() {
		IndexOperations indexOperations = elasticsearchOperations.indexOps(Produce.class);
		if (indexOperations.exists()) {
			indexOperations.delete();
		}
	}


	/********************************************* 增删改查 ********************************************/

	// 增
	@Test
	void save() {
		Produce produce = Produce.builder().name("金铲铲").price("156").build();
		// 即支持单个对象新增，也支持批量新增
		Produce savedEntity = elasticsearchOperations.save(produce);
		System.out.println("获取ES插入数据的Id：" + savedEntity.getId());
	}

	// 查
	@Test
	void findById() {
		Produce produce = elasticsearchOperations.get("4", Produce.class);
		System.out.println("查询ES中的数据：" + produce);
	}

	// 删
	@Test
	void deleteById() {
		String result = elasticsearchOperations.delete("99", Produce.class);
		System.out.println("删除ES中指定Id的数据：" + result);
	}

	// 改
	@Test
	void updateById() {
		Produce produce = Produce.builder().id("1").name("银锅锅").price("156").build();
		// 根据Id更新数据
		Produce updateEntity = elasticsearchOperations.save(produce);
		System.out.println("删除ES中指定Id的数据：" + updateEntity);
	}

}
