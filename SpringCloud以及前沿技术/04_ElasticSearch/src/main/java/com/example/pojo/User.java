package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author 游家纨绔
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="user",shards =1,replicas = 1)
public class User {

    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Text,analyzer="ik_max_word")
    private String name;

    @Field(type=FieldType.Keyword)
    private String price;

    @Field(type = FieldType.Integer)
    private  Integer age;
}
