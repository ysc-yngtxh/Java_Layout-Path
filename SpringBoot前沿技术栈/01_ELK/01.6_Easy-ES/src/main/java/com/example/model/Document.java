package com.example.model;

import lombok.Data;
import org.dromara.easyes.annotation.IndexName;

/**
 * ES数据模型
 **/
@Data
@IndexName("document")
public class Document {

    /**
     * es中的唯一id
     */
    private String id;

    /**
     * 文档标题
     */
    private String title;
    /**
     * 文档内容
     */
    private String content;
}
