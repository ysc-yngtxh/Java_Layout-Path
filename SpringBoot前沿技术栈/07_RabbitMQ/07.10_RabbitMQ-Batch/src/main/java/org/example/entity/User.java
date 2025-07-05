package org.example.entity;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-04 09:40
 * @apiNote TODO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
	@Serial
	private static final long serialVersionUID = 6306420919819323452L;

	private int id;
	private String name;
}
