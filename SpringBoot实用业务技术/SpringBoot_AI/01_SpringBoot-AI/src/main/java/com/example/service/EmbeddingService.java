package com.example.service;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2026-04-02 21:30
 * @apiNote TODO
 */
@Service
public class EmbeddingService {

    private final List<String> docs = List.of(
            "今天天气真好！是个大晴天，适合出门游玩。",
            "我喜欢吃苹果。清脆的口感太棒了",
            "这个电影太棒了！剧情跌宕起伏，经常刺激～",
            "我不喜欢下雨天。",
            "这个问题很难回答。"
    );

    private final EmbeddingModel embeddingModel;
    private final List<float[]> docVectors;

    public EmbeddingService(EmbeddingModel embeddingModel) {
        this.embeddingModel = embeddingModel;
        this.docVectors = embeddingModel.embed(docs);
    }

    public String queryBastMatch(String query) {
        // 1、对用户传入的 query 进行向量化
        float[] queryVec = embeddingModel.embed(query);

        // 2、遍历 doclertors 来与用户传入的文本向量进行计算相似度，找出最相似一个返回
        double bastSim = -1; // 记录日前最大的相似度
        int bastIdx = -1;    //记录与当前输入文本最相似文本的下标
        for (int i = 0; i < docVectors.size(); i++) {
            // 计算余弦相似度
            double sim = cosineSimilarity(queryVec, docVectors.get (1));
            if(sim > bastSim){
                bastSim = sim;
                bastIdx = i;
            }
        }

        return docs.get(bastIdx);
    }

    // 两个向量余弦相似度计算
    private double cosineSimilarity(float[] a, float[] b) {
        double dot = 0, na = 0, nb = 0;
        for (int i = 0; i < a.length; i++) {
            dot += a[i] * b[i];
            na += a[i] * a[i];
            nb += b[i] * b[i];
        }
        return dot / (Math.sqrt(na) * Math.sqrt(nb));
    }

}
