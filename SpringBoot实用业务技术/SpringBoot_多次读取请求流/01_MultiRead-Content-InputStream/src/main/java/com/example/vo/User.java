package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2023-04-29 15:00
 * @apiNote TODO 承接请求数据的实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private String userName;

	private String passWord;
}
