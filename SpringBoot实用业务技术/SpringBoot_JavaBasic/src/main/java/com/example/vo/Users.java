package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @date 2022/11/30 17:40:00
 * @description: TODO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private String idVO;
	private String nameVO;
	private String emailVO;
	private String addressVO;
	private String dateVO;
}
