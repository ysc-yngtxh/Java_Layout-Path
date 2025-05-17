package com.example.common;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-07 下午7:00:00
 * @apiNote TODO
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Invocation implements Serializable {
	@Serial
	private static final long serialVersionUID = 3068662031159582351L;

	private String interfaceName;
	private String methodName;
	private Class<?>[] parameterTypes;
	private Object[] parameters;
	private String version;

}
