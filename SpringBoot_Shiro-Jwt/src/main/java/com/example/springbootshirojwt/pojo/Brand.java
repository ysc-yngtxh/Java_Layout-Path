package com.example.springbootshirojwt.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "tb_brand")
public class Brand {

    @Id
    private Long id;
    private String name;     // 品牌名称
    private String image;    // 品牌图片
    private Character letter;   // Character类型相当于char类型的包装类
}
