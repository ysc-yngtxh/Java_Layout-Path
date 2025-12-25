package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @Column("id")
    private Integer id;

    @Column("user_name")
    private String userName;

    @Column("email")
    private String email;

    @Column("full_name")
    private String fullName;

    @Column("active")
    private boolean active;
}
