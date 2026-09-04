package com.example.pojo;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {
	@Serial
    private static final long serialVersionUID = -6109567371297192663L;

	private Integer id;

	private String name;

	private Integer teacherId;

	private String email;

	private Integer age;

    private Integer departmentId;

    private transient StudentProfile studentProfile;

    private transient List<Course> courseList;
}
