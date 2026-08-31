package com.example.pojo;

import java.io.Serial;
import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * @author 游家纨绔
 */
@Data
@Builder
public class Student implements Serializable {
	@Serial
    private static final long serialVersionUID = -6109567371297192663L;

	private Integer id;

	private String name;

	private String email;

	private Integer age;
}
