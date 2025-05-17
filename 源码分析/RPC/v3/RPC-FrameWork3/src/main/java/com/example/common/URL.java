package com.example.common;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-08 上午10:50:00
 * @apiNote TODO
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class URL implements Serializable {
	@Serial
	private static final long serialVersionUID = 3951293658407109323L;

	private String hostName;
	private Integer port;
}
