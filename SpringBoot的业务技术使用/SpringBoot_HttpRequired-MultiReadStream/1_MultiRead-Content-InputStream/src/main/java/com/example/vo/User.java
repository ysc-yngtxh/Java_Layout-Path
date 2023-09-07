package com.example.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2023-04-29 15:57
 * @apiNote TODO 承接请求数据的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;

    private String passWord;
}
