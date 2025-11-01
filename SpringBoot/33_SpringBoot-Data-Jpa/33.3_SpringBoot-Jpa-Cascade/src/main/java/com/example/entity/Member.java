package com.example.entity;

import com.example.entity.pk.MemberKey;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Table;
import lombok.Data;

@Data
@Table(name="member")
public class Member extends MemberKey implements Serializable {
	private static final long serialVersionUID = -2268520671243573103L;

	private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Date dateOfBirth;

    private Date registrationDate;

    private String status;

    private Date createdAt;

    private Date updatedAt;
}
