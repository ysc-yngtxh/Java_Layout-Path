package com.example.domain;

import java.util.Date;
import lombok.Data;

@Data
public class User {

	private Integer id;

	private String username;

	private Date birthday;

	private String sex;

	private String address;
}
