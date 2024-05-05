package com.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.Builder;
import lombok.Data;

/**
 * @author 游家纨绔
 * @dateTime 2023-08-19 23:24
 * @apiNote TODO Consumer 实体类的 子级实体
 */
@Data
@Builder
@JsonIgnoreType  // 该类作为别的类的属性时，该属性忽略序列化和反序列化。与@JsonIgnore效果相似
public class Supplier {

    private Integer id;
}
