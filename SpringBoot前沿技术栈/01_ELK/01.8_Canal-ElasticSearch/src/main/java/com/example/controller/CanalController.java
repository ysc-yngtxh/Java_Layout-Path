package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.example.pojo.CanalPO;
import com.example.service.CanalService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * (CanalPO)表控制层
 *
 * @author 游家纨绔
 * @since 2022-03-05 11:30:00
 */
@RestController
@RequestMapping("canal")
public class CanalController {

	@Autowired
	private CanalService canalService;

	@Autowired
	private RestHighLevelClient highLevelClient;

	/**
	 * mybatis根据id查询数据
	 *
	 * @param id
	 * @return
	 */
	@GetMapping("/search/{id}")
	public ResponseEntity<CanalPO> queryByPage(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(this.canalService.query(id));
	}


	/**
	 * mybatis查询所有数据
	 *
	 * @return
	 */
	@GetMapping("/search")
	public ResponseEntity<List<CanalPO>> queryById() {
		// return ResponseEntity.ok(this.testyscService.queryList());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.canalService.queryList());
	}

	/**
	 * 添加数据
	 *
	 * @param testysc
	 * @return
	 */
	@PostMapping("/add")
	public ResponseEntity<String> add(CanalPO testysc) {
		this.canalService.save(testysc);
		return ResponseEntity.status(HttpStatus.OK).body("添加数据成功！");
	}

	/**
	 * 更新数据
	 *
	 * @param testysc
	 * @return
	 */
	@PutMapping("/update")
	public ResponseEntity<String> edit(CanalPO testysc) {
		this.canalService.update(testysc);
		return ResponseEntity.status(HttpStatus.OK).body("更新数据成功！");
	}

	/**
	 * 删除数据
	 *
	 * @param id
	 * @return
	 */
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id) {
		return ResponseEntity.ok(!this.canalService.remove(id));
	}

	/**
	 * Elasticsearch实时搜索
	 *
	 * @return
	 * @throws IOException
	 */
	@GetMapping("/es7")
	public ResponseEntity<List> Es() throws IOException {
		// 创建Es搜索请求
		SearchRequest searchRequest = new SearchRequest("testysc");
		// 搜索构建器
		SearchSourceBuilder source = new SearchSourceBuilder();
		// 查询条件，我们可以使用QueryBuilder工具来实现
		MatchAllQueryBuilder query = new MatchAllQueryBuilder();

		source.query(query);
		searchRequest.source(source);
		SearchResponse search = highLevelClient.search(searchRequest, RequestOptions.DEFAULT);

		// 打印搜索结果的响应体
		System.out.println(JSON.toJSONString(search.getHits().getHits()));

        /*上述的的打印结果是一连串的斑驳信息，但我们只要其中的数据信息。可以通过for循环得到我们需要的信息，但是无法返回给前端
          所以这里我通过stream把Es中的驳杂信息去掉，再转为List集合 */
		List<Map<String, Object>> collect = Arrays.stream(search.getHits().getHits())
		                                          .map(SearchHit::getSourceAsMap)
		                                          .collect(toList());

		System.out.println(collect);

		return ResponseEntity.ok(collect);
	}

}
