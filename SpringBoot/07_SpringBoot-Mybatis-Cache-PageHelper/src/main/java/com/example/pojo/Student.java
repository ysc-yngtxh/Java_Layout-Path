package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * @author 游家纨绔
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = 604285744980857806L;

    private Integer id;

	private String name;

	private Integer teacherId;

	private String email;

	private Integer age;

    private Integer departmentId;

}
