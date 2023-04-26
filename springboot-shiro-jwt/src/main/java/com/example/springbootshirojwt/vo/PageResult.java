package com.example.springbootshirojwt.vo;

import lombok.Data;

import java.util.List;

//View Object
@Data
public class PageResult<T> {

    private Long total;         //总条数
    private Integer totalPage;  //总页数
    private List<T> items;      //当前页数量集合

    public PageResult() {
    }

    public PageResult(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(Long total, Integer totalPage, List<T> items) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = items;
    }
}
