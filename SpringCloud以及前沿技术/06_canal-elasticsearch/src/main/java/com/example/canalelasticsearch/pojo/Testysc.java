package com.example.canalelasticsearch.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (Testysc)实体类
 *
 * @author makejava
 * @since 2022-03-05 11:30:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "testysc")
public class Testysc {

    @Id
    private Integer id;
    
    private String name;
    
    private String address;

    @Override
    public String toString() {
        return "Testysc{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

