package com.example;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import com.example.pojo.VectorEntity;
import com.example.services.EsVectorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class VectorESApplicationTests {

	// ElasticsearchTemplate 已弃用，请使用 ElasticsearchOperations
	@Autowired
	private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private EsVectorService service;


    @Test
    public void createVectorIndex() {
        String indexName = "vector_index";
        IndexCoordinates indexCoordinates = IndexCoordinates.of(indexName);

        // 2. 构建索引信息
        IndexOperations indexOps = elasticsearchOperations.indexOps(indexCoordinates);

        // 3. 如果索引已存在，先删除（可选）
        if (indexOps.exists()) indexOps.delete();

        indexOps.create();

        // 1. 构建 mappings properties
        Map<String, Property> properties = new HashMap<>();
        // title: text
        properties.put("title", Property.of(p -> p
                .text(t -> t
                        .analyzer("ik_max_word")  // 必须指定分词器
                        .searchAnalyzer("ik_smart") // 搜索时使用
                )
        ));
        // properties.put("title", Property.of(p -> p.text(t -> t)));
        // // content: text
        // properties.put("content", Property.of(p -> p.text(t -> t)));
        // // vector: dense_vector (768维, 余弦相似度)
        // properties.put("vector", Property.of(p -> p
        //         .denseVector(dv -> dv
        //                 .dims(768)               // 维度 768
        //                 .index(true)             // 开启索引
        //                 .similarity(DenseVectorSimilarity.Cosine) // 余弦相似度
        //                 .indexOptions(io -> io.type(DenseVectorIndexOptionsType.Hnsw).m(16).efConstruction(100))
        //         )
        // ));

        // 4. 创建索引
        Document document = Document.create();
        document.put("properties", properties);
        indexOps.putMapping(document);

        System.out.println("索引 " + indexName + " 创建成功！");
    }

    /**
     * 创建向量索引 vector_index（方式一：组装 DSL 参数）
     *
     * PUT /vector_index
     * {
     *   "mappings": {
     *     "properties": {
     *       "title": { "type": "text" },
     *       "content": { "type": "text" },
     *       "vector": {
     *         "type": "dense_vector",
     *         "dims": 768,
     *         "index": true,
     *         "similarity": "cosine",
     *         "index_options": {
     *           "type": "hnsw",
     *           "m": 16,
     *           "ef_construction": 100
     *         }
     *       }
     *     }
     *   }
     * }
     */
    @Test
    public void createVectorIndex0() {
        String indexName = "vector_index";
        IndexCoordinates indexCoordinates = IndexCoordinates.of(indexName);

        IndexOperations indexOps = elasticsearchOperations.indexOps(indexCoordinates);

        // 如果索引已存在，先删除
        if (indexOps.exists()) indexOps.delete();

        // 1. 先创建索引
        indexOps.create();

        // 2. 再设置 mappings
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        properties.put("title", Map.of("type", "text"));
        properties.put("content", Map.of("type", "text"));
        properties.put("vector", Map.of(
                "type", "dense_vector",
                "dims", 768,
                "index", true,
                "similarity", "cosine",
                "index_options", Map.of(
                        "type", "hnsw",
                        "m", 16,
                        "ef_construction", 100
                )
        ));
        map.put("properties", properties);

        // 3. 设置 mapping
        indexOps.putMapping(Document.from(map));

        System.out.println("索引 " + indexName + " 创建成功！");
    }

    /**
     * 创建向量索引 vector_index（方式二：使用实体类配置属性）
     */
    @Test
    public void createVectorIndex1() {
        // 1、获取 IndexOperations
        IndexOperations indexOps = elasticsearchOperations.indexOps(VectorEntity.class);

        // 2、如果索引已存在，先删除
        if (indexOps.exists()) {
            indexOps.delete();
            System.out.println("删除已存在的索引: vector_index");
        }

        // 3、根据实体类配置创建索引
        indexOps.create();
        indexOps.putMapping();  // 应用映射配置

        System.out.println("索引 vector_index 创建成功！");
    }


    @Test
    public void testInsert() throws Exception {
        service.insertVector("Elasticsearch 9.x 教程", "9.x 向量检索使用方法");
        service.insertVector("Java 编程", "SpringBoot 连接 ES 9.x");
        service.insertVector("AI 大模型", "BGE 向量生成");
        System.out.println("写入成功");
    }

    @Test
    public void testSearch() throws Exception {
        System.out.println(service.searchVector("ES 9.x 向量", 1));
    }

}
