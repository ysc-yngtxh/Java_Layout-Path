package com.example.pojo;

import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-16 23:29
 * @apiNote TODO 图书模型
 */
@Data
public class BookModel {

    /**
     * 图书名称
     */
    private String bookName;

    /**
     * 作者信息
     */
    private AuthorModel author;
}
