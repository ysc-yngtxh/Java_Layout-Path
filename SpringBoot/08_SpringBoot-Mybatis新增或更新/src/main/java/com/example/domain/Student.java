package com.example.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
	private static final long serialVersionUID = 2458336762628477632L;

	private Integer id;

	private String name;

	private String email;

	private Integer age;

	private transient Integer count;
}
