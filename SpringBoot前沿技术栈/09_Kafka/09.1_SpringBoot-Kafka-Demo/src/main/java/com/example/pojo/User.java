package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-04-15 11:30:00
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Integer id;

	private String name;

	private String age;

}
