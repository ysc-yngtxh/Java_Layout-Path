package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.Date;

/**
 * @author 游家纨绔
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="user")
@Setting(shards = 5, replicas = 1)
public class User {

    @Field(type = FieldType.Integer)
    private Integer id;

    @Field(type = FieldType.Text,analyzer="ik_max_word")
    private String name;

    @Field(type=FieldType.Keyword)
    private String price;

    @Field(type = FieldType.Integer)
    private  Integer age;

    @Field(type = FieldType.Date, format = DateFormat.basic_date_time)
    private Date date;
}
