package com.example.controller;

import com.example.mapper.DocumentMapper;
import com.example.model.Document;
import org.dromara.easyes.core.conditions.select.LambdaEsQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EasyESController {

	// Easy-Es底层用的就是 ElasticSearch 官方提供的 RestHighLevelClient
	// 我们只是对 RestHighLevelClient 做了增强，并没有改变减少或是削弱它原有的功能，所以您无需担心拓展性.

	// Easy-Es底层用了ES官方的RestHighLevelClient，所以对ES版本有要求：
	// 要求ES和RestHighLevelClient JAR依赖版本必须为7.14.0，至于es客户端实际测下来7.X任意版本都可以很好的兼容.
	// 但是ElasticSearch客户端8.X任意版本都兼容不了Easy-Es

	@Autowired
	private DocumentMapper documentMapper;

	@GetMapping("/createIndex")
	public Boolean createIndex() {
		// 1.初始化-> 创建索引(相当于mysql中的表)
		return documentMapper.createIndex();
	}

	@GetMapping("/insert")
	public Integer insert() {
		// 2.初始化-> 新增数据
		Document document = new Document();
		document.setTitle("老汉");
		document.setContent("推*技术过硬");
		return documentMapper.insert(document);
	}

	@GetMapping("/search")
	public List<Document> search() {
		// 3.查询出所有标题为老汉的文档列表
		LambdaEsQueryWrapper<Document> wrapper = new LambdaEsQueryWrapper<>();
		wrapper.eq(Document::getTitle, "老汉");
		return documentMapper.selectList(wrapper);
	}
}
