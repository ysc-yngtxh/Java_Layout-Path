package com.example.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 游家纨绔
 */
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -6109567371297192663L;

    private Integer id;

    private String name;

    private String email;

    private Integer age;
}
