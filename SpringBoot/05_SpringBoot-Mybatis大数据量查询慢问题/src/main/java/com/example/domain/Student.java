package com.example.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Student implements Serializable {
    private static final long serialVersionUID = -6315216601909901078L;

    private Integer id;

    private String name;

    private String email;

    private Integer age;
}