package com.cn.leyou.pojo;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="tb_brand")
public class Brand {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private String name;     //品牌名称
    private String image;    //品牌图片
    private Character letter;   //Character类型相当于char类型的包装类
}
