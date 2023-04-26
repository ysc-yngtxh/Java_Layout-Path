package com.cn.leyou.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Data
@Table(name="tb_spu")
public class Spu {

    @Id
    @KeySql(useGeneratedKeys = true)
    private Long id;
    private Long brandId;  //品牌id
    private Long cid1;    //商品1级目录
    private Long cid2;    //商品2级目录
    private Long cid3;    //商品3级目录
    private String title; //标题
    private String subTitle;  //子标题
    private Boolean saleable; //是否上架

    @JsonIgnore  //这个注解表示的是可以不返回这个字段
    private Date createTime;  //创建时间
    private Date lastUpdateTime;  //最后修改时间

    @Transient//这个注解是不会序列化到数据库中的，可以把这个注解的字段理解为临时用的
    private String bname;
    @Transient
    private String cname;
    
}
