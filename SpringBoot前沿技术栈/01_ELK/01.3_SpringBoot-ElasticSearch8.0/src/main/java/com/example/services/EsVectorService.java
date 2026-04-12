package com.example.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.KnnQuery;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.pojo.VectorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EsVectorService {

    @Autowired
    private ElasticsearchClient client;

    // ==============================
    // ① 模拟生成 768 维向量
    // 真实项目：对接 BGE / M3E / OpenAI Embedding
    // ==============================
    // ==============================
    public List<Float> generateVector(String text) {
        List<Float> vector = new ArrayList<>(768);

        // 1. 用文本内容生成稳定 hash（保证相同内容=相同向量）
        int seed = text == null ? 0 : text.hashCode();
        Random random = new Random(seed); // 关键：用内容做种子！

        // 2. 生成 768 维 -1 ~ 1 标准向量
        for (int i = 0; i < 768; i++) {
            vector.add(random.nextFloat() * 2 - 1);
        }

        return vector;
    }

    // ==============================
    // ② 写入向量数据到 ES 9.x
    // ==============================
    public String insertVector(String title, String content) throws Exception {
        List<Float> vector = generateVector(content);

        VectorEntity doc = new VectorEntity();
        doc.setTitle(title);
        doc.setContent(content);
        doc.setVector(vector);

        IndexRequest<VectorEntity> request = IndexRequest.of(i -> i
                .index("vector_index")
                .document(doc)
        );

        return client.index(request).id();
    }

    // ==============================
    // ③ 向量语义检索（kNN） ES9.x 标准写法
    // ==============================
    public List<VectorEntity> searchVector(String query, int topN) throws Exception {
        List<Float> queryVector = generateVector(query);

        // 🔥 ES9.x 正确 kNN 查询
        KnnQuery knnQuery = KnnQuery.of(k -> k
                .field("vector")
                .queryVector(queryVector)
                .k(topN) // k值表示
                .numCandidates(50)
        );

        SearchResponse<VectorEntity> response = client.search(s -> s
                        .index("vector_index")
                        .query(q -> q.knn(knnQuery)) // 这里是关键！！！
                        .source(source -> source.filter(f -> f.excludes("vector")))
                , VectorEntity.class
        );

        List<VectorEntity> result = new ArrayList<>();
        for (Hit<VectorEntity> hit : response.hits().hits()) {
            VectorEntity doc = hit.source();
            doc.setId(hit.id());
            result.add(doc);
        }
        return result;
    }

}
